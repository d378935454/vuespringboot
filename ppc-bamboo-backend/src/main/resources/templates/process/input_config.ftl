<h2 class="center page-header">用户输入界面</h2>
<div id="vue" class="container">
    <div class="row">
        <div class="col-sm-12 col-md-3 col-lg-3 col-xs-12">
        </div>
        <div class="col-sm-12 col-md-6 col-lg-6 col-xs-12">
            <form class="form-horizontal" role="form" style="border:1px solid #ddd;padding:15px;">
                <div class="form-group">
                    <label class="col-xs-2 col-sm-2 control-label"></label>
                    <div class="col-xs-3 col-sm-3">
                        <label class="col-xs-8 col-sm-8 control-label">参数</label>
                    </div>
                    <div class="col-xs-3 col-sm-3">
                        <label class="col-xs-8 col-sm-8 control-label">别名</label>
                    </div>
                    <input class="btn btn-info"
                           @click="addinput" type="button" value="新增">
                </div>
                <div class="form-group" v-for="(item,index) in list">
                    <label for="lastname" class="col-xs-2 col-sm-2 control-label">参数{{index+1}}</label>
                    <div class="col-xs-3 col-sm-3">
                        <input type="text" class="form-control" placeholder="参数名" v-model="item.argument">
                    </div>
                    <div class="col-xs-3 col-sm-3">
                        <input type="text" class="form-control" placeholder="别名" v-model="item.alias">
                    </div>
                    <input class="btn .btn-warning" type="button" value="删除"
                           @click="list.splice(index, 1)">
                    <span style="color: red">{{item.tittle}}</span>
                </div>
            </form>
        </div>
        <div class="col-sm-12 col-md-12 col-lg-12 col-xs-12 center">
            <input type="button" class="btn btn-primary " value="配置"
                   @click='complete'>
            <input type="button" class="btn btn-primary" value="返回"
                   onclick='window.history.go(-1)'>
        </div>

    </div>
</div>
<script>
    var vm = new Vue({
        el: "#vue",
        data: {
            processId:"",
            stepId: "",
            configId: "",
            list: [
                {argument: "", alias: "",tittle:""}
            ]
        },
        created: function () {
            this.processId = getUrlParam("processId");
            this.stepId = getUrlParam("stepId");
            this.configId = getUrlParam("configId");
            if (this.configId) {
                var config = this.getConfig();
                var list =[];
                $.each(config.processStepArgumentDTOs, function (i, n) {
                    var arg = {argument: n.argName, alias: n.alias,tittle:""};
                    list.push(arg);
                });
                this.list = list;
            }
        },
        methods: {
            addinput: function () {
                var param = {argument: "", alias: "",tittle:""};
                this.list.push(param);
            },
            flush: function () {
                var _self = this;
                var isOk = false;
                $.each(_self.list,function (i,e) {
                    if(!isNaN(e.alias)){
                        e.tittle="不能为(数字或空)";
                        isOk=true;
                    }else {
                        e.tittle="";
                    }
                });
                if(isOk){
                    return isOk;
                }
                $.ajax({
                    url: "../../admin/config/saveInput?stepId=" + this.stepId + "&argType=OUT&configId="+this.configId,
                    contentType: "application/json",
                    type: 'POST',
                    async: false,
                    data: JSON.stringify(_self.list),
                    complete: function (jqXHR, status) {
                        sessionOut(jqXHR);
                    },
                    success: function (data) {
//                        $("#load").data("stepId", data.body.stepId);
                        toastr.success("操作成功");
                    },
                    error: function () {
                        toastr.error("操作失败");
                        isOk = true;
                    }
                });
                return isOk;
            },
            complete: function () {
                if (this.flush()) {
                    return;
                }
                location.hash = "/admin/step/stepList.htm?processId="+this.processId;
            },
            getConfig: function () {
                var _self = this;
                var config;
                $.ajax({
                    url: "../../admin/config/queryInput/" + _self.configId,
                    contentType: "application/json",
                    type: 'POST',
                    async: false,
                    complete: function (jqXHR, status) {
                        sessionOut(jqXHR);
                    },
                    success: function (data) {
                        config = data.body;
                    },
                    error: function () {
                    }
                });
                return config;
            }
        }
    });
</script>
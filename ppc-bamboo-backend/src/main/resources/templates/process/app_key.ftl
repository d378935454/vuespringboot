<div id="vue">
    <h2 class="center page-header">选择appKey</h2>
    <div class="col-sm-12 col-md-12 col-lg-12 col-xs-12">
    <div class="col-sm-12 col-md-3 col-lg-3 col-xs-12">
        </div>
    <div class="col-sm-12 col-md-6 col-lg-6 col-xs-12">
        <form role="form">
            <div class="form-group margin-top">
                <label for="name">流程名称:</label><span style="color: red">{{processName_alt}}</span>
                <input type="text" class="form-control" v-model="processName" placeholder="手机认证">
            </div>
            <div class="form-group margin-top">
                <label for="name">流程说明:</label><span style="color: red">{{processDesc_alt}}</span>
                <textarea class="form-control" rows="3" v-model="processDesc" placeholder="手机认证流程配置示例"></textarea>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 center">
                <input type="button" class="btn btn-primary" value="配置"
                       @click='complete'>
            </div>
        </form>
        </div>
    </div>
    <#--<div class="col-sm-6 col-md-6 col-lg-6 col-xs-12" style="border: 1px solid rgb(221, 221, 221)">-->
        <#--<div class="row margin-top">-->
            <#--<form class="form-inline" role="form">-->
                <#--<div class="col-sm-8 col-md-10 col-lg-10 col-xs-12">-->
                    <#--<div class="form-group" style="width: 100%">-->
                        <#--<input type="text" style="width: 100%" class="form-control" id="" v-model="searchCondition"-->
                               <#--placeholder="请输入名称">-->
                    <#--</div>-->
                <#--</div>-->
                <#--<div class="col-sm-4 col-md-2 col-lg-2 col-xs-6">-->
                    <#--<div class="form-group" style="width: 100%">-->
                        <#--<button type="button" style="width: 100%" @click="showData" class="btn btn-primary">搜索-->
                        <#--</button>-->
                    <#--</div>-->
                <#--</div>-->
            <#--</form>-->
        <#--</div>-->

        <#--<table class="table-hover table table-bordered  margin-top">-->
            <#--<thead>-->
            <#--<tr>-->
                <#--<th>姓名</th>-->
                <#--<th>appKey<span style="color: red">{{app_key_alt}}</span></th>-->
                <#--<th>选择（单选框）</th>-->
            <#--</tr>-->
            <#--</thead>-->
            <#--<tbody>-->
            <#--&lt;#&ndash;<#list ></#list>&ndash;&gt;-->
            <#--<tr v-for="(item,index) in list" @click.stop="checkRadio" :key="index">-->
                <#--<td>{{item.user.realName}}</td>-->
                <#--<td>{{item.appKey}}</td>-->
                <#--<td><input type="radio" name="ck" :key="item.id" :value="item.appKey" v-model="app_key"></td>-->
            <#--</tr>-->
            <#--<tr v-if="list.length==0">-->
                <#--<td colspan="3">抱歉，无数据</td>-->
            <#--</tr>-->
            <#--</tbody>-->
        <#--</table>-->
        <#--<div id="div_pagination"></div>-->
    <#--</div>-->
</div>
<script>
    var vm = new Vue({
        el: "#vue",
        data: {
            processId:"",
//            app_key: "",
//            app_key_alt:"",
            processName: "",
            processName_alt: "",
            processDesc: "",
            processDesc_alt: "",
//            searchCondition: "",
            list: []
        },
        created: function () {
//            this.showData();
        }
        ,
        methods: {
            flush: function () {
                if (this.checkParam()) {
                    return true;
                }
                var isOk = false;
                var _self=this;
                        $.ajax({
                    url: "../../admin/process/save",
                    contentType: "application/json",
                    type: 'GET',
                    async: false,
                    data: {
                        app_key: _self.app_key,
                        processName: _self.processName,
                        processDesc: _self.processDesc
                    },
                    success: function (data) {
//                        $("#load").data("processId",data.body.processId);
                        _self.processId=data.body.processId;
                        toastr.success("新建成功");
                    },
                    error: function () {
                        toastr.error("新建失败");
                        isOk = true;
                    }
                });
                return isOk;
            },
            complete: function () {
                if (this.flush()) {
                    return;
                }
                location.hash ="/admin/step/stepList.htm?processId="+this.processId;
//                configUrl("../../admin/step/stepList");
            },
            showData: function (page) {
                var _self = this;
                $.ajax({
                    url: "../../admin/manage/queryP",
                    contentType: "application/json",
                    type: 'GET',
                    data: {
                        pageSize: 10,
                        pageNum: isNaN(page) ? 1 : page,
                        status: "",
                        searchCondition: _self.searchCondition
                    },
                    complete: function (jqXHR, status) {
                        var data = jqXHR.responseJSON.body;
                        if (status === "success") {
                            _self.list = data.list;
                            createPageText(data, "div_pagination");
                        }
                    }
                })
            },
            /**
             * 验证出参不为空,是否重复
             */
            checkParam: function () {
                var rs=false;
                if (this.processName == "") {
                    this.processName_alt="需要选择流程名称";
                    rs= true;
                }else {
                    this.processName_alt="";
                }
                if (this.processDesc == "") {
                    this.processDesc_alt="需要选择流程说明";
                    rs= true;
                }else {
                    this.processDesc_alt="";
                }
                return  rs;
            },
            checkRadio: function (e) {
                $(e.currentTarget).find('input[type="radio"]').trigger("click");
            }
        }
    });
    function loadPage(page) {
        vm.showData(page);
    }
</script>
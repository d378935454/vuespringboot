<div id="vue">
    <h2 class="center page-header">process列表</h2>
    <div class="col-sm-12 col-md-2 col-lg-2 col-xs-12"></div>
    <div class="col-sm-12 col-md-8 col-lg-8 col-xs-12">
        <div class="row margin-top">
            <form class="form-inline" role="form">
                <div class="col-sm-8 col-md-10 col-lg-10 col-xs-12">
                    <div class="form-group" style="width: 100%">
                        <input type="text" style="width: 100%" class="form-control" id="" v-model="searchCondition"
                               placeholder="请输入名称">
                    </div>
                </div>
                <div class="col-sm-4 col-md-2 col-lg-2 col-xs-6">
                    <div class="form-group" style="width: 100%">
                        <button type="button" style="width: 100%" @click="showData" class="btn btn-primary">搜索
                        </button>
                    </div>
                </div>
            </form>
        </div>

        <table class="table-hover table table-bordered  margin-top">
            <thead>
            <tr>
                <th>流程Id</th>
                <th>流程名称</th>
                <th>流程说明</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <#--<#list ></#list>-->
            <tr v-for="(item,index) in list" :key="index">
                <td>{{item.processId}}</td>
                <td><input type="text" class="form-control" v-model="item.processName"></td>
                <td><input type="text" class="form-control" v-model="item.processDesc"></td>
                <td >
                    <button type="button" class="btn btn-info" @click="updateProcess(index)">重命名</button>
                    <button type="button" class="btn btn-primary" @click="gotoStep(item.processId)">修改</button>
                    <button type="button" class="btn btn-defalut" @click="delProcess(item.processId,index)">删除</button>
                </td>
            </tr>
            <tr v-if="list.length==0">
                <td colspan="4">抱歉，无数据</td>
            </tr>
            </tbody>
        </table>
        <div id="div_pagination"></div>
    </div>
</div>
<script>
    var vm = new Vue({
        el: "#vue",
        data: {
            searchCondition: "",
            list: []
        },
        created: function () {
            this.showData();
        }
        ,
        methods: {
            complete: function (appkey) {
                location.hash = "/admin/process/process_list.htm?appkey=" + appkey;
//                configUrl("../../admin/step/stepList");
            },
            showData: function (page) {
                var _self = this;
                $.ajax({
                    url: "../../admin/process/query",
                    contentType: "application/json",
                    type: 'GET',
                    data: {
                        pageSize: 10,
                        pageNum: isNaN(page) ? 1 : page,
                        param:_self.searchCondition
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
            gotoStep: function (processId) {
                location.hash = '/admin/step/stepList.htm?processId=' + processId;
            },
            updateProcess: function (index) {
                var _self = this;
                var process = _self.list[index];
                $.ajax({
                    url: "../../admin/process/update",
                    contentType: "application/json",
                    type: 'GET',
                    data: {
                        id: process.id,
                        processName:process.processName,
                        processDesc:process.processDesc
                    },
                    async: false,
                    complete: function (jqXHR, status) {
                        sessionOut(jqXHR);
                    },
                    success: function (data) {
                        if (data.code == 0) {
                        } else {

                            toastr.success("修改成功");
                        }
                    },
                    error: function () {
                        toastr.error("修改失败");
                    }
                });
            },
            delProcess: function (processId, index) {
                var _self = this;
                $.ajax({
                    url: "../../admin/process/delProcess/" + processId,
                    contentType: "application/json",
                    type: 'POST',
                    async: false,
                    complete: function (jqXHR, status) {
                        sessionOut(jqXHR);
                    },
                    success: function (data) {
//                        $("#load").data("stepId", data.body.stepId);
                        if (data.code == 0) {
                        } else {
                            _self.list.splice(index, 1);
                            toastr.success("删除成功");
                        }
                    },
                    error: function () {
                        toastr.error("删除失败");
                    }
                });
            }
        }
    });
    function loadPage(page) {
        vm.showData(page);
    }
</script>
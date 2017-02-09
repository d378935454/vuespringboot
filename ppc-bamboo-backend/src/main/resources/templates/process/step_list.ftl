<style>
    .table th, .table td {
        text-align: center;
        vertical-align: middle !important;
    }
</style>
<div id="vue">
    <h2 class="center page-header">节点列表</h2>

    <div class="col-sm-12 col-md-2 col-lg-2 col-xs-12">
    </div>
    <div class="col-sm-12 col-md-8 col-lg-8 col-xs-12">
        <table class="table table-hover table-bordered margin-top">
            <thead>
            <tr>
                <th>节点序号</th>
                <th>节点类型</th>
                <th>节点说明</th>
                <th>下个节点序号</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(i,index) in list">
                <td>{{i.orders}}</td>
                <td><select disabled class="form-control" v-model="i.stepType.name">
                    <option v-for="i in type" :value="i.value">{{i.name}}</option>
                </select></td>
                <td><input type="text" class="form-control" v-model="i.stepDesc"/></td>
                <td><input type="number" class="form-control" v-model.number="i.nextOrders"/></td>
                <td>
                    <button type="button" class="btn btn-info" @click="updateStep(index)">修改</button>
                    <button type="button" class="btn btn-primary" @click="gotoStep(i.stepId,i.stepType.name,'')">配置
                    </button>
                    <button type="button" class="btn btn-defalut" @click="delStep(i.stepId,index)">删除</button>
                </td>
            </tr>
            <tr v-if="list.length==0">
                <td colspan="5">无数据</td>
            </tr>
            </tbody>
        </table>
        <div id="div_pagination" class="center"></div>
    </div>
    <div class="col-sm-12 col-md-12 col-lg-12 col-xs-12 center">
        <button type="button" class="btn btn-primary" @click="complete">新建节点</button>
        <input type="button" class="btn btn-primary" value="返回"
               onclick='window.history.go(-1)'>
    </div>

    <div style="clear:both;"></div>
</div>
<script>
    function loadPage(page) {
        vm.showData(page);
    }
    var vm = new Vue({
        el: '#vue',
        data: {
            processId: "",
            stepId: "",
            list: [],
            type: [
                {name: "用户输入", value: "INPUT", url: "/admin/step/inputConfig.htm"},
                {name: "接口", value: "INTERFACE", url: "/admin/step/paramConfig.htm"},
//                {name: "本地逻辑jar", value: "JAR", url: "/admin/step/logic/JAR.htm"},
                {name: "简单逻辑（表达式）", value: "EL", url: "/admin/step/logic/EL.htm"},
                {name: "结束", value: "END", url: "/admin/step/end.htm"}
            ]
        },
        created: function () {
            var _self = this;
            this.processId = getUrlParam("processId");
            this.stepId = getUrlParam("stepId");
            $.ajax({
                url: "../../admin/step/createStepId",
                contentType: "application/json",
                type: 'GET',
                complete: function (jqXHR, status) {
                    sessionOut(jqXHR);
                }, success: function (data) {
                    _self.stepId = data.body;
                }, error: function () {
                }

            });
            this.showData();
        },
        methods: {
            showData: function (page) {
                var _self = this;
                $.ajax({
                    url: "../../admin/step/query",
                    contentType: "application/json",
                    type: 'GET',
                    data: {
                        processId: _self.processId,
                        pageSize: 10,
                        pageNum: isNaN(page) ? 1 : page
                    }, complete: function (jqXHR, status) {
                        sessionOut(jqXHR);

                    }, success: function (data) {
                        _self.list = data.body.list;
                        $("#load").data("stepNum", data.body.total);
                        createPageText(data.body, "div_pagination");
                    }, error: function () {
                    }

                })
            },
            queryStep: function (stepId) {
                var step;
                $.ajax({
                    url: "../../admin/step/queryStep/" + stepId,
                    contentType: "application/json",
                    type: 'GET',
                    async: false,
                    complete: function (jqXHR, status) {
                        sessionOut(jqXHR);
                    },
                    success: function (data) {
                        step = data.body;
                    },
                    error: function () {
                    }
                });
                return step;
            },
            gotoStep: function (stepId, key, configId) {
                var $urlMap = {
                    INPUT: {name: "用户输入", url: "/admin/step/inputConfig.htm"},
                    INTERFACE: {name: "接口", url: "/admin/step/paramConfig.htm"},
//                    JAR: {name: "本地逻辑jar", url: "/admin/step/logic/JAR.htm"},
                    EL: {name: "简单逻辑（表达式）", url: "/admin/step/logic/EL.htm"},
                    END: {name: "结束", url: "/admin/step/end.htm"}
                };
                var step = this.queryStep(stepId);
                var configId = "";
                if (step.processStepDTO) {
                    this.stepDesc = step.processStepDTO.stepDesc;
                    this.orders = step.processStepDTO.orders;
                    this.nextOrders = step.processStepDTO.nextOrders;
                    this.stepType = step.processStepDTO.stepType.name;
                    var length = step.processStepConfigDTOs.length;
                    if (length > 0) {
                        configId = step.processStepConfigDTOs[length - 1].configId;
                    }
                }
//                location.hash = '/admin/step/newStep.htm?processId=' + this.processId + "&stepId=" + stepId;
                location.hash = $urlMap[key].url + '?processId=' + this.processId + "&stepId=" + stepId + "&configId=" + configId;
            },
            updateStep: function (index) {
                var _self = this;
                var step = _self.list[index];
                $.ajax({
                    url: "../../admin/step/updateStep",
                    contentType: "application/json",
                    type: 'GET',
                    data: {
                        stepId: step.stepId,
                        stepType: step.stepType.name,
                        stepDesc: step.stepDesc,
                        nextOrders: step.nextOrders
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
            delStep: function (stepId, index) {
                var _self = this;
                $.ajax({
                    url: "../../admin/step/delStep/" + stepId,
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
            },
            complete: function () {
                location.hash = '/admin/step/newStep.htm?processId=' + this.processId + "&stepId=" + this.stepId;
            }
        }
    })
</script>
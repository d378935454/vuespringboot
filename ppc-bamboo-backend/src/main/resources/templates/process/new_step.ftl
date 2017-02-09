<div id="vue">
    <h2 class="center page-header">已选择步骤</h2>
    <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
    </div>
    <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 ">
        <div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">节点类型:</div>
            <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
                <select class="form-control" id="step_type" v-model="stepType">
                    <option v-for="i in type" :value="i.value" :data-url="i.url">{{i.name}}</option>
                </select>
            </div>
        </div>
        <div class="form-group margin-top col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">节点说明:</div>
            <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
                <input type="text" class="form-control" v-model="stepDesc">
            </div>
        </div>
    </div>
    <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
        <div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">当前序号:</div>
            <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
                <input type="text" class="form-control" readonly v-model="orders">
            </div>
        </div>
        <div class="form-group margin-top col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">下一节点序号:</div>
            <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
                <input type="number" min="0" class="form-control" v-model.number="nextOrders">
            </div>
        </div>
    </div>
    <div class="col-xs-12 col-sm-12 col-md-2 col-lg-2">
    </div>
    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 center">
        <input type="button" class="btn btn-primary" value="配置"
               @click='complete'>
        <input type="button" class="btn btn-primary" value="返回"
               onclick='window.history.go(-1)'>
    </div>
</div>
<script>
    var vm = new Vue({
        el: "#vue",
        data: {
            processId: "",
            stepId: "",
            configId: "",
            type: [
                {name: "用户输入", value: "INPUT", url: "/admin/step/inputConfig.htm"},
                {name: "接口", value: "INTERFACE", url: "/admin/step/paramConfig.htm"},
                {name: "本地逻辑jar", value: "JAR", url: "/admin/step/logic/JAR.htm"},
                {name: "简单逻辑（表达式）", value: "EL", url: "/admin/step/logic/EL.htm"},
                {name: "结束", value: "END", url: "/admin/step/end.htm"}
            ],
            stepType: "INPUT",
            stepDesc: "",
            orders: "",
            nextOrders: "",
            param: {name: ''}
        },
        created: function () {
            this.processId = getUrlParam("processId");
            this.stepId = getUrlParam("stepId");
            var step = this.queryStep();
            if (step.processStepDTO) {
                this.stepDesc = step.processStepDTO.stepDesc;
                this.orders = step.processStepDTO.orders;
                this.nextOrders = step.processStepDTO.nextOrders;
                this.stepType = step.processStepDTO.stepType.name;
                var length=step.processStepConfigDTOs.length;
                if (length > 0) {
                    this.configId = step.processStepConfigDTOs[length-1].configId;
                }
            } else {
                var stepNum =${total};
                this.orders = stepNum + 1;
                this.nextOrders = stepNum + 2;
            }
        },
        methods: {
            flush: function () {
                var isOk = false;
                $.ajax({
                    url: "../../admin/step/save",
                    contentType: "application/json",
                    type: 'GET',
                    async: false,
                    data: {
                        processId: this.processId,
                        stepId: this.stepId,
                        stepType: this.stepType,
                        stepDesc: this.stepDesc,
                        orders: this.orders,
                        nextOrders: this.nextOrders
                    },
                    complete: function (jqXHR, status) {
                        sessionOut(jqXHR);
                    },
                    success: function (data) {
                        $("#load").data("stepId", data.body.stepId);
                        toastr.success("操作成功");
                    },
                    error: function () {
                        toastr.error("操作失败");
                        isOk = true;
                    }
                });
                return isOk;
            },
            queryStep: function () {
                var _self = this;
                var step;
                $.ajax({
                    url: "../../admin/step/queryStep/" + _self.stepId,
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
            complete: function () {
                if (this.flush()) {
                    return;
                }
                location.hash = $("#step_type option:selected").data("url")
                        + "?processId=" + this.processId
                        + "&stepId=" + this.stepId
                        + "&configId=" + this.configId;
            }
        }
    });
</script>
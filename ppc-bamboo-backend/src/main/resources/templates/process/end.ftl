<h2 class="center page-header">结束页面</h2>
<div id="vue" class="container">
    <div class="row">
        <div class="col-sm-12 col-md-6 col-lg-6 col-xs-12">
            <div class="form-group col-xs-12 col-sm-12">
                <div class="col-xs-2 col-sm-2"></div>
                <label class="col-xs-4 col-sm-4 control-label">参数名</label>
                <div class="col-sm-4">
                    <label class="col-xs-4 col-sm-4 control-label">别名</label>
                </div>
                <div class="col-xs-2 col-sm-2">
                    <button class="btn btn-primary" type="button"
                            @click="addinput">新增
                    </button>
                </div>
            </div>

            <div class="form-group col-xs-12 col-sm-12" v-for="(i,index) in inputList" @click.stop="checkRadio">
                <div class="col-xs-2 col-sm-2">
                    <input type="radio" class="control-label" name="a" v-model="pick" :value="index">
                </div>
                <div class="col-xs-4 col-sm-4">
                    <input class="form-control" v-model="i.argument"/>
                </div>
                <div class="col-xs-4 col-sm-4">
                    <input type="text" class="form-control" readonly id="lastname" v-model="i.show"
                           placeholder="">
                    <input type="hidden" v-model="i.alias">
                </div>
                <div class="col-xs-2 col-sm-2">
                    <input class="btn .btn-warning" type="button" value="删除"
                           @click="inputList.splice(index, 1)">
                </div>
            </div>

        </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 center">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

                <div class="col-sm-12 col-md-3 col-lg-3 col-xs-12">
                    <button class="btn btn-primary" type="button"
                            @click="pushArg">关联
                    </button>
                </div>
                <div class="col-sm-12 col-md-9 col-lg-9 col-xs-12">
                    <div class="col-sm-12 col-md-6 col-lg-6 col-xs-12">
                        <textarea type="text" class="form-control" v-model="argparam" readonly
                                  style="width:60%;display:inline-block;"></textarea>
                    </div>
                    <h4 class="col-sm-12 col-md-6 col-lg-6 col-xs-12" style="color: red">{{argtitle}}</h4>
                </div>

            </div>
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 center">
                <div class="col-sm-12 col-md-3 col-lg-3 col-xs-12 ">
                    <select id="arg" onchange="updateArgMap(vm)">
                        <option v-for="i in argList" :value="i.value" selected>{{i.text}}</option>
                    </select>
                </div>
                <div class="col-sm-12 col-md-9 col-lg-9 col-xs-12" style="border:1px solid #ddd;">
                    <template v-if="typeof(argMap) == 'object'">
                        <arg-map :data="argMap"></arg-map>
                    </template>
                    <template v-else>
                        <ul @click="argparam=argMap">
                            <li style="cursor: pointer">
                                {{argMap}}
                            </li>
                        </ul>
                    </template>

                </div>
                <div style="clear:both;"></div>
            </div>
        </div>
        <div class="col-sm-12 col-md-12 col-lg-12 col-xs-12 center">
            <input type="button" class="btn btn-primary " value="结束"
                   @click='complete'>
            <input type="button" class="btn btn-primary" value="返回"
                   onclick='window.history.go(-1)'>
        </div>
    </div>
</div>
<script>
    function updateArgMap(vm) {
        var $this = $("#arg");
        var arg = vm.argList[$this.val()];
        if (arg.rs) {//为接口
            vm.argMap = $.parseJSON(arg.rs);
        } else {
            vm.argMap = arg.text;
        }
        vm.argparam = ""
    }
    var vm = new Vue({
        el: '#vue',
        data: {
            processId: "",
            stepId: "",
            configId: "",
            inputList: [
                {argument: "", alias: ""}
            ],
            pick: 0,
            argList: [],
            argMap: "",
            argparam: "",
            argtitle: ""
        },
        created: function () {
            var _self = this;
            this.processId = getUrlParam("processId");
            this.stepId = getUrlParam("stepId");
            this.configId = getUrlParam("configId");
            if (this.configId) {
                var config = this.getConfig();
                var list = [];
                $.each(config.processStepArgumentDTOs, function (i, n) {
                    var arg = {
                        argument: n.argName,
                        alias: n.alias,
                        show: isNaN(n.alias) ? n.alias : n.argRules
                    };
                    list.push(arg);
                });
                this.inputList = list;
            }
            var arg = this.getArgment();
            var argConfig = arg.configs;
            var arguments = arg.arguments;
            $.each(argConfig, function (i, n) {
                var $param = {
                    text: n[1],
                    value: _self.argList.length,
                    id: n[0],
                    rs: n[2]
                };
                _self.argList.push($param);
            });
            $.each(arguments, function (i, n) {
                var $param = {
                    text: n.alias,
                    value: _self.argList.length
                };
                _self.argList.push($param);
            });
        },
        mounted: function () {
            $("#load select").select2({});
            updateArgMap(this);
        },
        methods: {
            addinput: function () {
                var param = {argument: "", alias: "", show: ""};
                this.inputList.push(param);
            },
            flush: function () {
                var _self = this;
                var isOk = false;
                $.ajax({
                    url: "../../admin/config/saveEnd?stepId=" + this.stepId + "&argType=INPUT&configId=" + this.configId,
                    contentType: "application/json",
                    type: 'POST',
                    async: false,
                    data: JSON.stringify(_self.inputList),
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
                location.hash = "/admin/step/stepList.htm?processId=" + this.processId;
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
            },
            /**
             * 得到出参信息
             * @returns {*}
             */
            getArgment: function () {
                var _self = this;
                var argment;
                $.ajax({
                    url: "../../admin/config/query_Arg",
                    contentType: "application/json",
                    type: 'GET',
                    data: {
                        processId: _self.processId,
                        stepId: _self.stepId
                    },
                    async: false,
                    success: function (data) {
                        argment = data.body;
                    },
                    error: function () {
                    }
                });
                return argment;
            },
            pushArg: function () {
                var _self = this;
                if (_self.argparam == "") {
                    _self.argtitle = "请选择";
                    return;
                }
                _self.argtitle = "";
                //添加到数据库为出参
                var $this = $("#arg");
                var arg = vm.argList[$this.val()];
                if (arg.rs) {//为接口
                    if (this.createOutParam(arg.id, _self.argparam, _self.pick)) {
                        return;
                    }

                } else {//复制到别名
                    _self.inputList[_self.pick].alias = _self.argparam;
                    _self.inputList[_self.pick].show = _self.argparam;
                }
            },
            createOutParam: function (configId, argparam, pick) {
                var _self = this;
                var isOk = false;
                $.ajax({
                    url: "../../admin/config/createOutParam?configId=" + configId + "&argparam=" + argparam,
                    contentType: "application/json",
                    type: 'POST',
                    async: false,
                    success: function (data) {
//                        $("#load").data("stepId", data.body.stepId);
                        if (data.code == 0) {
                            isOk = true;
                        } else {
                            toastr.success("新建成功");
                            _self.inputList[pick].alias = data.body.id;
                            _self.inputList[pick].show = argparam;
                        }
                    },
                    error: function () {
                        toastr.error("新建失败");
                        isOk = true;
                    }
                });
                return isOk;
            },
            checkRadio: function (e) {
                $(e.currentTarget).find('input[type="radio"]').trigger("click");
            }
        },
        components: {
            // <my-component> 将只在父模板可用
            'arg-map': {
                name: 'param-mp',
                template: '<ul>' +
                '<li v-for="(val,key) in data" @click.stop="checkParam($event)" :data-key="key" style="cursor:pointer">' +
                '{{key}}' +
                '<param-mp :data="val" v-if="is_obj(val)"></param-mp>' +
                '</li>' +
                '</ul>',
                props: {data: {}},
                methods: {
                    is_obj: function (val) {
                        return Object.prototype.toString.call(val) === '[object Object]'
                    },
                    checkParam: function (event) {
                        if (event.target.localName != 'li') {
                            return;
                        }
                        vm.argparam = this.installParam($(event.currentTarget), '').substring(1);
                    },
                    installParam: function (u, param) {
                        param = "." + u.data("key");
                        var parent = u.parent().parent('li');
                        if (parent.length > 0) {
                            param = this.installParam(parent, param) + param;
                        }

                        return param;
                    }
                }
            }
        }
    })
</script>
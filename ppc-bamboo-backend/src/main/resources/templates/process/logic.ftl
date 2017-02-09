<h2 class="center page-header">逻辑判断配置</h2>
<div id="vue" class="container">
    <div class="row">
        <h1>{{type.desc}}</h1>
        <div class="col-sm-12 col-md-3 col-lg-3 col-xs-12">
            <form class="form-horizontal" role="form" v-show="type.value==2">
                <div class="form-group margin-top">
                    <label for="lastname" class="col-xs-4 col-sm-4 control-label">包名</label>
                    <div class="col-xs-4 col-sm-4">
                        <input type="text" class="form-control" v-model="com" id="lastname" placeholder="包名">
                    </div>
                </div>
                <div class="form-group">
                    <label for="lastname" class="col-xs-4 col-sm-4 control-label">函数名</label>
                    <div class="col-xs-4 col-sm-4">
                        <input type="text" class="form-control" v-model="method" id="lastname" placeholder="函数名">
                    </div>
                </div>
            </form>
            <form class="form-horizontal" role="form" v-show="type.value==3">
                <div class="form-group margin-top">
                    <label for="lastname" class="col-xs-4 col-sm-4 control-label">逻辑表达式</label>
                    <div class="col-xs-12 col-sm-12">
                        <textarea class="form-control" rows="5" v-model="EL" placeholder="逻辑表达式"></textarea>
                    </div>
                </div>
            <#--<div class="form-group">-->
            <#--<label for="lastname" class="col-xs-4 col-sm-4 control-label">参数2</label>-->
            <#--<div class="col-xs-4 col-sm-4">-->
            <#--<input type="text" class="form-control" id="lastname" placeholder="别名2">-->
            <#--</div>-->
            <#--<input class="col-xs-4 col-sm-4" type="checkbox">-->
            <#--</div>-->
            </form>
        </div>
        <div class="col-sm-12 col-md-5 col-lg-5 col-xs-12">
            <div class="col-sm-12 col-md-12 col-lg-12 col-xs-12">
                <div class="form-group col-sm-12 col-md-12 col-lg-12 col-xs-12">
                    <div class="col-xs-2 col-sm-2 control-label">

                    </div>
                    <label class="col-xs-4 col-sm-4 control-label">参数名</label>
                    <div class="col-sm-4">
                        <label>别名</label>
                    </div>
                    <div class="col-xs-2 col-sm-2">
                        <input class="btn btn-info "
                               @click="addarginput" type="button" value="新增">
                    </div>
                </div>
                <div class="form-group col-sm-12 col-md-12 col-lg-12 col-xs-12" v-for="(i,index) in inputList"
                     @click.stop="checkRadio">
                    <div class="col-xs-2 col-sm-2">
                        <input type="radio" class="control-label" name="a" v-model="pick" :value="index">
                    </div>
                    <div class="col-xs-4 col-sm-4 control-label">

                        <input type="text" class="form-control" id="lastname" v-model="i.name"
                               placeholder="">
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
                <#--<div class="col-xs-4 col-sm-4">-->
                <#--<select class="">-->
                <#--<option>不关联</option>-->
                <#--<option>节点1-用户输入-别名</option>-->
                <#--&lt;#&ndash;<#list list as l><option value="${l.api}">${l.api}-${l.apiName}</option></#list>&ndash;&gt;-->
                <#--</select>-->
                <#--</div>-->
                </div>
            </div>
        </div>
        <div class="col-sm-12 col-md-4 col-lg-4 col-xs-12">
            <div class="col-sm-12 col-md-10 col-lg-10 col-xs-12">
                <div class="form-group col-md-12 col-lg-12">
                    <label for="lastname" class="col-xs-3 col-sm-3 control-label"></label>
                    <label class="col-xs-3 col-sm-3 control-label">
                        返回码
                    </label>
                    <label class="col-xs-3 col-sm-3 control-label">
                        下一节点
                    </label>
                    <div class="col-xs-3 col-sm-3">
                        <input class="btn btn-info"
                               @click="addinput" type="button" value="新增">
                    </div>
                </div>
                <div class="form-group col-md-12 col-lg-12" v-for="(item,index) in codeList">
                    <label for="lastname" class="col-xs-3 col-sm-3 control-label">返回结果{{index+1}}</label>
                    <div class="col-xs-3 col-sm-3">
                        <input type="text" class="form-control" placeholder="returncode" v-model.trim="item.returnCode">
                    </div>
                    <div class="col-xs-3 col-sm-3">
                        <input type="number" class="form-control" placeholder="nextOrder"
                               v-model.number="item.nextOrder">
                    </div>
                    <div class="col-xs-3 col-sm-3">
                        <input class="btn .btn-warning" type="button" value="删除"
                               @click="codeList.splice(index, 1)">
                    </div>
                </div>
            </div>
            <div style="clear:both;"></div>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

            <div class="col-xs-12 col-sm-12 col-md-9 col-lg-9"></div>
            <div class="col-xs-12 col-sm-12 col-md-3 col-lg-3">
                <div class="col-sm-12 col-md-8 col-lg-8 col-xs-12">
                    <button class="btn btn-primary" type="button"
                            @click="pushArg">关联
                    </button>
                    <input type="text" class="form-control" v-model="argparam" readonly
                           style="width:60%;display:inline-block;">
                </div>
                <div class="col-sm-12 col-md-4 col-lg-4 col-xs-12">
                    <h4 style="color: red">{{argtitle}}</h4>
                </div>
            </div>

        </div>
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 center">
            <div class="col-sm-12 col-md-5 col-lg-5 col-xs-12 ">
            </div>
            <div class="col-sm-12 col-md-7 col-lg-7 col-xs-12">
                <div class="col-sm-12 col-md-5 col-lg-5 col-xs-12 ">
                    <select id="arg" onchange="updateArgMap(vm)">
                        <option v-for="i in argList" :value="i.value" selected>{{i.text}}</option>
                    </select>
                </div>
                <div class="col-sm-12 col-md-7 col-lg-7 col-xs-12" style="border:1px solid #ddd;">
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
            </div>
            <div style="clear:both;"></div>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 center">
            <input type="button" class="btn btn-primary" value="配置"
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
        el: "#vue",
        data: {
            processId: "",
            stepId: "",
            configId: "",
            EL: "",
            com: "",
            method: "",
            codeList: [
                {returnCode: "", nextOrder: ""}
            ],
            inputList: [
                {name: "", alias: ""}
            ],
            type: {
                name: '${stepType}', value: '${stepType.value}', desc: '${stepType.desc}'
            },
            pick: 0,
            argList: [],
            argMap: "",
            argparam: "",
            argtitle: ""
        },
        computed: {
            JarMethod: function () {
                return this.type.name == "EL" ? this.EL : (this.com + ";" + this.method);
            }
        }
        ,
        created: function () {
            var _self = this;
            this.processId = getUrlParam("processId");
            this.stepId = getUrlParam("stepId");
            this.configId = getUrlParam("configId");
            if (this.configId) {
                var config = this.getConfig();
                if (this.type.name == "JAR") {
                    var jarMethod = config.processStepConfigDTOs.jarMethod.split(";");
                    this.com = jarMethod[0];
                    this.method = jarMethod[1];

                } else {
                    this.EL = config.processStepConfigDTOs.jarMethod;
                }

                var argList = [];
                $.each(config.processStepArgumentDTOs, function (i, n) {
                    var arg = {
                        name: n.argName == null ? "" : n.argName,
                        alias: n.alias,
                        show: isNaN(n.alias) ? n.alias : n.argRules
                    };
                    argList.push(arg);
                });
                this.inputList = argList;
                var codeList = [];
                $.each(config.processStepLogicDTOs, function (i, n) {
                    var arg = {returnCode: n.returnCode, nextOrder: n.nextOrders};
                    codeList.push(arg);
                });
                this.codeList = codeList;

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
        }
        ,
        methods: {
            addinput: function () {
                var param = {returnCode: "", nextOrder: ''};
                this.codeList.push(param);
            },
            addarginput: function () {
                var arg = {name: "", alias: ""};
                this.inputList.push(arg);
            },
            flush: function () {
                var _self = this;
                var isOk = false;
                var list = {inputList: _self.inputList, codeList: _self.codeList, jarMethod: this.JarMethod};
                $.ajax({
                    url: "../../admin/config/saveLogic?stepId=" + _self.stepId + "&configId=" + this.configId,
                    contentType: "application/json",
                    type: 'POST',
                    async: false,
                    data: JSON.stringify(list),
                    complete: function (jqXHR, status) {
                        sessionOut(jqXHR);
                    },
                    success: function (data) {
//                        $("#load").data("stepId", data.body.stepId);
                        if (data.code == 0) {
                            isOk = true;
                        } else {
                            toastr.success("操作成功");
                        }
                    },
                    error: function () {
                        toastr.error("操作失败");
                        isOk = true;
                    }
                });
                return isOk;
            }
            ,
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
                    url: "../../admin/config/queryLogic/" + this.stepId + "/" + _self.configId,
                    contentType: "application/json",
                    type: 'POST',
                    async: false,
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
                    complete: function (jqXHR, status) {
                        sessionOut(jqXHR);
                    },
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
                    _self.inputList[_self.pick].show=_self.argparam;
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
                    complete: function (jqXHR, status) {
                        sessionOut(jqXHR);
                    },
                    success: function (data) {
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
        }
        ,
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

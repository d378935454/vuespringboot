<h2 class="center page-header">接口配置</h2>
<div id="vue" class="container">
    <div class="row">
        <div class="col-sm-12 col-md-12 col-lg-12 col-xs-12 center ">
            <label>接口名称:</label>
            <select id="api" id="e2" onchange="select2Change(vm);initList(vm)">
                <option v-for="i in interfaceList" :value="i.api">{{i.api + "-" + i.apiName }}</option>
            </select>
        </div>
        <div class="col-sm-12 col-md-12 col-lg-12 col-xs-12 margin-top-50" style="border:1px solid #ddd;padding:15px;">
            <div class="col-sm-12 col-md-6 col-lg-6 col-xs-12 ">
                    <h4>入参</h4>
                    <form class="form-horizontal" role="form" >
                        <div class="form-group">
                            <label class="col-xs-3 col-sm-4 control-label">参数名</label>
                            <div class="col-sm-4">
                                <label class="col-xs-4 col-sm-4 control-label">别名</label>
                            </div>
                        </div>
                        <div class="form-group" v-for="(i,index) in inputList" @click.stop="checkRadio">
                            <div class="col-xs-2 col-sm-2">
                                <input type="radio" class="control-label" name="a" v-model="pick" :value="index">
                            </div>
                            <label for="lastname" class="col-xs-2 col-sm-2 control-label">{{i.argument}}</label>
                            <div class="col-xs-4 col-sm-4">
                                <input type="text" class="form-control" readonly id="lastname" v-model="i.show"
                                       placeholder="">
                                <input type="hidden" v-model="i.argumentName">
                            </div>
                        </div>
                    </form>
            </div>
            <div class="col-sm-12 col-md-6 col-lg-6 col-xs-12 center">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

                    <div class="col-sm-12 col-md-3 col-lg-3 col-xs-12">
                        <button class="btn btn-primary" type="button"
                                @click="pushArg">关联
                        </button>
                    </div>
                    <div class="col-sm-12 col-md-9 col-lg-9 col-xs-12">
                        <div class="col-sm-12 col-md-6 col-lg-6 col-xs-12">
                        <textarea cols="3" type="text" class="form-control" v-model="argparam" readonly
                                  style="width:60%;display:inline-block;"></textarea>
                        </div>
                        <h4 class="col-sm-12 col-md-6 col-lg-6 col-xs-12" style="color: red">{{argtitle}}</h4>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 center">
                    <div class="col-sm-12 col-md-7 col-lg-7 col-xs-12">
                        <div class="col-sm-12 col-md-3 col-lg-3 col-xs-12 ">
                            <select id="arg" onchange="updateArgMap(vm)">
                                <option v-for="i in argList" :value="i.value" selected>{{i.text}}</option>
                            </select>
                        </div>
                        <div class="col-sm-12 col-md-9 col-lg-9 col-xs-12" style="border:1px solid #ddd;">
                            <template v-if="typeof(argMap) == 'object'">
                                <arg-map data="argMap"></arg-map>
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
            </div>
        </div>
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 center">
            <div style="border:1px solid #ddd;padding:15px;">
                <div class="col-sm-12 col-md-12 col-lg-12 col-xs-12">
                    <div class="col-sm-12 col-md-2 col-lg-2 col-xs-12">
                        <h4>出参</h4>
                    </div>
                    <div class="col-sm-12 col-md-5 col-lg-5 col-xs-12">

                    </div>
                    <div class="col-sm-12 col-md-5 col-lg-5 col-xs-12">
                        <div class="col-sm-12 col-md-8 col-lg-8 col-xs-12">
                            <button class="btn btn-primary" type="button"
                                    @click="addparam">新增
                            </button>
                            <input type="text" class="form-control" v-model="param" readonly
                                   style="width:60%;display:inline-block;">
                        </div>
                        <div class="col-sm-12 col-md-4 col-lg-4 col-xs-12">
                            <h4 style="color: red">{{title}}</h4>
                        </div>
                    </div>
                </div>
                <div class="col-sm-12 col-md-7 col-lg-7 col-xs-12">
                    <form class="form-horizontal" role="form" style="border:1px solid #ddd;padding:15px;">
                        <div class="form-group">
                            <label class="col-xs-5 col-sm-5 control-label">参数名</label>
                            <div class="col-xs-7 col-sm-7">
                                <label class="col-xs-4 col-sm-4 control-label">别名</label>
                            </div>
                        </div>
                        <div class="form-group" v-for="(i,index) in outList">
                            <div class="col-xs-5 col-sm-5 ">
                                <textarea rows="3" class="form-control" :value="i.argument" readonly></textarea>
                            </div>
                            <div class="col-xs-5 col-sm-5">
                                <input type="text" class="form-control" v-model="i.argumentName" placeholder="别名">
                                <span style="color: red">{{i.tittle}}</span>
                            </div>
                            <div class="col-xs-2 col-sm-2">
                                <button type="button" class="btn btn-defalut" @click="outList.splice(index, 1)">删除
                                </button>
                            </div>
                        </div>

                    </form>
                </div>
                <div class="col-sm-12 col-md-5 col-lg-5 col-xs-12">
                    <div style="border:1px solid #ddd;">
                        <param-map :data="map"></param-map>
                    </div>
                </div>
                <div style="clear:both;"></div>
            </div>
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 center">
                <input type="button" class="btn btn-primary" value="配置" @click='complete'>
                <input type="button" class="btn btn-primary" value="返回"
                       onclick='window.history.go(-1)'>
            </div>
        </div>
    </div>
    <select2 :arg-list="argList" v-model="test"></select2>asdasdas123213
</div>
<script type="text/x-template" id="select2-template">
    <select>
        <slot></slot>
        <option v-for="i in argList" :value="i.value" selected>{{i.text}}</option>
    </select>
</script>
<script>
    function select2Change(vue) {
        var $val = $('#api').val();
        vue.inter = $val;
        var inter = vue.interfaceList.filter(function (element, i) {
            return element.api == $val;
        })[0];
        vue.map = $.parseJSON(inter.argList.filter(function (element) {
            return element.argumentType == 1;
        })[0].argument);
        var inputList = inter.argList.filter(function (element) {
            return element.argumentType == 0;
        });
        $.each(inputList, function (i, e) {
            e.argumentName = "";
            e.show = "";
        });
        vue.inputList = inputList;
        vue.outList = [];
    }
    function initList(vue) {
        var $val = $('#api').val();
        vue.inter = $val;
        var inter = vue.interfaceList.filter(function (element, i) {
            return element.api == $val;
        })[0];
        vue.map = $.parseJSON(inter.argList.filter(function (element) {
            return element.argumentType == 1;
        })[0].argument);
    }
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
            test:"",
            processId: "",
            stepId: "",
            configId: "",
            interfaceList: [],
            inter: "",
            map: {},
            inputList: [],
            pick: 0,
            outList: [],
            param: "",
            title: "",
            argList: [],
            argMap: "",
            argparam: "",
            argtitle: ""
        },
        created: function () {
            var _self = this;
            $.ajax({
                type: "post",
                url: "../../admin/config/query_apiList",
                data: {type: 0},
                async: false,
                dataType: "json",
                success: function (data) {
                    _self.interfaceList = data.apiList;
                    if (data.apiList.length > 0) {
                        _self.inter = data.apiList[0].api;
                    }
                }
            });
            this.processId = getUrlParam("processId");
            this.stepId = getUrlParam("stepId");
            this.configId = getUrlParam("configId");
            if (this.configId) {
                var config = this.getConfig();
                _self.inter = config.processStepConfigDTOs.api;
                var List = config.processStepArgumentDTOs;
                $.each(List, function (i, n) {
                    if (n.argType.name == "INPUT") {
                        _self.inputList.push({
                            argument: n.argName,
                            argumentName: n.alias,
                            argType: "INPUT",
                            show: isNaN(n.alias) ? n.alias : n.argRules
                        });
                    } else {
                        _self.outList.push({argument: n.argName, argumentName: n.alias, argType: "OUT", tittle: ""});
                    }
                })
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
            $("#e2").val(this.inter);
            $("#api").select2({});
            $("#arg").select2({});
            if (this.configId) {
                initList(this);
            } else {
                select2Change(this);
            }
            updateArgMap(this);
        },
        methods: {
            addparam: function () {
                if (this.checkParam()) {
                    return;
                }
                var param = {argument: this.param, argumentName: "", argType: "OUT", tittle: ""};
                this.outList.push(param);
            },
            /**
             * 验证出参不为空,是否重复
             */
            checkParam: function () {
                var param = this.param;
                var size = this.outList.filter(function (item) {
                    return item.name == param;
                }).length;
                if (param == "") {
                    this.title = "入参不能为空";
                    return true;
                } else if (size > 0) {
                    this.title = "入参已存在";
                    return true;
                } else {
                    this.title = "";
                }
            },
            flush: function () {
                var _self = this;
                var isOk = false;
                $.each(_self.outList, function (i, e) {
                    if (!isNaN(e.argumentName)) {
                        e.tittle = "不能为(数字或空)";
                        isOk = true;
                    } else {
                        e.tittle = "";
                    }
                });
                if (isOk) {
                    return isOk;
                }
                var list = {inputList: _self.inputList, outList: _self.outList};
                $.ajax({
                    url: "../../admin/config/saveInterface?stepId=" + _self.stepId + "&apiId=" + this.inter + "&configId=" + this.configId,
                    contentType: "application/json",
                    type: 'POST',
                    async: false,
                    data: JSON.stringify(list),
                    success: function (data) {
//                        $("#load").data("stepId", data.body.stepId);
                        if (data.code == 0) {
                            isOk = true;
                        } else {
                            toastr.success("新建成功");
                        }
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
                location.hash = "/admin/step/stepList.htm?processId=" + this.processId;
            },
            /**
             * 得到config信息
             * @returns {*}
             */
            getConfig: function () {
                var _self = this;
                var config;
                $.ajax({
                    url: "../../admin/config/queryInterface/" + _self.stepId + "/" + _self.configId,
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
                    _self.inputList[_self.pick].argumentName = _self.argparam;
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
                            _self.inputList[pick].argumentName = data.body.id;
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
            'param-map': {
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
                        vm.param = this.installParam($(event.currentTarget), '').substring(1);
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
            },
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
            },
            'select2': {
                props: ['argList'],
                template: '#select2-template',
                mounted: function () {
                    var vm = this;
                    $(this.$el)
                            .val(this.value)
                            // init select2
                            .select2({data: this.options})
                            // emit event on change.
                            .on('change', function () {
                                vm.$emit('input', this.value)
                            })
                },
//                watch: {
//                    value: function (value) {
//                        // update value
//                        $(this.$el).val(value)
//                    },
//                    options: function (options) {
//                        // update options
//                        $(this.$el).select2({data: options})
//                    }
//                },
                destroyed: function () {
                    $(this.$el).off().select2('destroy')
                }
            }

        }
    });
</script>
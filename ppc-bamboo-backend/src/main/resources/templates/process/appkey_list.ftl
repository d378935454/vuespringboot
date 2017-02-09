<div id="vue">
    <h2 class="center page-header">appKey列表</h2>
    <div class="col-sm-12 col-md-3 col-lg-3 col-xs-12"></div>
    <div class="col-sm-12 col-md-6 col-lg-6 col-xs-12" >
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
                <th>姓名</th>
                <th>appKey</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <#--<#list ></#list>-->
            <tr v-for="(item,index) in list" :key="index">
                <td>{{item.user.realName}}</td>
                <td>{{item.appKey}}</td>
                <td><input type="button" class="btn btn-primary" value="查看" @click="complete(item.appKey)"></td>
            </tr>
            <tr v-if="list.length==0">
                <td colspan="3">抱歉，无数据</td>
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
                location.hash ="/admin/process/process_list.htm?appkey="+appkey;
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
                        sessionOut(jqXHR);
                        var data = jqXHR.responseJSON.body;
                        if (status === "success") {
                            _self.list = data.list;
                            createPageText(data, "div_pagination");
                        }
                    }
                })
            }
        }
    });
    function loadPage(page) {
        vm.showData(page);
    }
</script>
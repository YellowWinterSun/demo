<%--
  User: HuangDongYang<huangdy@pvc123.com>
  Date: 2018/6/15
  Time: 18:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="A fully featured admin theme which can be used to build CRM, CMS, etc.">
        <meta name="author" content="Coderthemes">

        <!-- App favicon -->
        <link rel="shortcut icon" href="/static/template/assets/images/favicon.ico">
        <!-- App title -->
        <title>用户管理</title>

        <!-- Sweet Alert -->
        <link href="/static/template/plugins/bootstrap-sweetalert/sweet-alert.css" rel="stylesheet" type="text/css">

        <!-- date range picker -->
        <link href="/static/template/plugins/timepicker/bootstrap-timepicker.min.css" rel="stylesheet">
        <link href="/static/template/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.min.css" rel="stylesheet">
        <link href="/static/template/plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css" rel="stylesheet">
        <link href="/static/template/plugins/clockpicker/css/bootstrap-clockpicker.min.css" rel="stylesheet">
        <link href="/static/template/plugins/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

        <link href="/static/template/plugins/bootstrap-select/css/bootstrap-select.min.css" rel="stylesheet" />

        <!-- App css -->
        <link href="/static/template/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="/static/template/assets/css/core.css" rel="stylesheet" type="text/css" />
        <link href="/static/template/assets/css/components.css" rel="stylesheet" type="text/css" />
        <link href="/static/template/assets/css/icons.css" rel="stylesheet" type="text/css" />
        <link href="/static/template/assets/css/pages.css" rel="stylesheet" type="text/css" />
        <link href="/static/template/assets/css/menu.css" rel="stylesheet" type="text/css" />
        <link href="/static/template/assets/css/responsive.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="/static/template/plugins/switchery/switchery.min.css">


        <script src="/static/template/assets/js/modernizr.min.js"></script>

        <!-- bootstrap-table -->
        <link rel="stylesheet" href="/static/css/bootstrap-table.min.css" />

        <style type="text/css">
            #dy_table td{
                 white-space:nowrap;    //td内容不换行
            }
        </style>
    </head>


    <body class="fixed-left">
        
        <!-- ========================================= -->
        <!-- modal -->

        <!-- 增加user，modal -->
        <div id="dy_addModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;" data-backdrop="static">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title">新增用户</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-5">
                                <div class="form-group">
                                    <label for="" class="control-label">姓名<span class="text-danger">*</span></label>
                                    <input type="text" name="name" class="form-control" required id="" placeholder="">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="" class="control-label">性别<span class="text-danger">*</span></label>
                                    <select name="sex" class="form-control">
                                        <option>男</option>
                                        <option>女</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label for="" class="control-label">工号<span class="text-danger">*</span></label>
                                    <input type="text" name="no" required  class="form-control" id="" placeholder="">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="" class="control-label">手机号码<span class="text-danger">*</span></label>
                                    <input type="text" name="phone" class="form-control" required id="" placeholder="">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="" class="control-label">企业邮箱<span class="text-danger">*</span></label>
                                    <input type="text" name="email" class="form-control" required id="" placeholder="">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="" class="control-label">入职时间<span class="text-danger">*</span></label>
                                    <input type="text" class="form-control dy_datepicker" name="entrydate" required placeholder="yyyy-mm-dd" id="">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="" class="control-label">直属上司</label>
                                    <select class="selectpicker form-control" data-live-search="true" data-max-options="1">
                                        <option value="no1">上司1</option>
                                        <option value="no2">上司2</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="" class="control-label">岗位<span class="text-danger">*</span></label>
                                    <select multiple="" class="selectpicker form-control" data-live-search="true" data-max-options="1">
                                        <optgroup label="研发部">
                                            <option>岗位1</option>
                                            <option>岗位2</option>
                                            <option>岗位3</option>
                                        </optgroup>
                                        <optgroup label="市场部">
                                            <option>岗位4</option>
                                            <option>岗位5</option>
                                            <option>岗位6</option>
                                        </optgroup>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <span class="text-right text-pink">密码默认:monda6 </span>
                        <button type="button" class="btn btn-default waves-effect" data-dismiss="modal">Close</button>
                        <button type="button" id="dy_addModal_addBtn" class="btn btn-info waves-effect waves-light">Add</button>
                    </div>
                </div>
            </div>
        </div><!-- /.modal -->

        <!-- 修改user，modal -->
        <div id="dy_updateModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;" data-backdrop="static">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title">修改用户</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" disabled name="id" required />
                        <div class="row">
                            <div class="col-md-5">
                                <div class="form-group">
                                    <label for="" class="control-label">姓名<span class="text-danger">*</span></label>
                                    <input type="text" name="name" class="form-control" required id="" placeholder="">
                                </div>
                            </div>
                            <div class="col-md-3">
                                <div class="form-group">
                                    <label for="" class="control-label">性别<span class="text-danger">*</span></label>
                                    <select name="sex" class="form-control">
                                        <option>男</option>
                                        <option>女</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <label for="" class="control-label">工号<span class="text-danger">*</span></label>
                                    <input type="text" disabled name="no" required  class="form-control" id="" placeholder="">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="" class="control-label">手机号码<span class="text-danger">*</span></label>
                                    <input type="text" name="phone" class="form-control" required id="" placeholder="">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="" class="control-label">企业邮箱<span class="text-danger">*</span></label>
                                    <input type="text" name="email" class="form-control" required id="" placeholder="">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="" class="control-label">入职时间<span class="text-danger">*</span></label>
                                    <input type="text" class="form-control dy_datepicker" name="entrydate" required placeholder="yyyy-mm-dd" id="">
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="" class="control-label">直属上司</label>
                                    <select class="selectpicker form-control" data-live-search="true" data-max-options="1">
                                        <option value="no1">上司1</option>
                                        <option value="no2">上司2</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="" class="control-label">岗位<span class="text-danger">*</span></label>
                                    <select multiple="" class="selectpicker form-control" data-live-search="true" data-max-options="1">
                                        <optgroup label="研发部">
                                            <option>岗位1</option>
                                            <option>岗位2</option>
                                            <option>岗位3</option>
                                        </optgroup>
                                        <optgroup label="市场部">
                                            <option>岗位4</option>
                                            <option>岗位5</option>
                                            <option>岗位6</option>
                                        </optgroup>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default waves-effect" data-dismiss="modal">Close</button>
                        <button type="button" id="dy_updateModal_submitBtn" class="btn btn-info waves-effect waves-light">Update</button>
                    </div>
                </div>
            </div>
        </div><!-- /.modal -->

        <!-- 重置密码，modal -->
        <div id="dy_resetPwdModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;" data-backdrop="static">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title">密码重置</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" disabled name="id" required />
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="" class="control-label">新密码<span class="text-danger">*</span></label>
                                    <input type="password" name="password" class="form-control" required id="" placeholder="">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="" class="control-label">再次确认密码<span class="text-danger">*</span></label>
                                    <input type="password" name="password2" class="form-control" required id="" placeholder="">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default waves-effect" data-dismiss="modal">Close</button>
                        <button type="button" id="dy_resetPwdModal_submitBtn" class="btn btn-info waves-effect waves-light">Update</button>
                    </div>
                </div>
            </div>
        </div><!-- /.modal -->

        <!-- end of defind modal -->

        <!-- Begin page -->
        <div id="wrapper">

            <%@ include file="../header/header.jsp" %>

            <!-- ============================================================== -->
            <!-- Start right Content here -->
            <!-- ============================================================== -->
            <div class="content-page">
                <!-- Start content -->
                <div class="content">
                    <div class="container">

                        

                        <div class="row">
                            <div class="col-sm-12">
                                <div class="card-box" style="margin-top:10px;">
                                    <div id="dy_toolbar" class="btn-group">
                                        <button id="dy_addBtn" title="增加用户" type="button" class="btn btn-default">
                                            <i class="mdi mdi-account-plus" aria-hidden="true"></i>
                                        </button>
                                        <button id="dy_updateBtn" title="修改用户" type="button" class="btn btn-default">
                                            <i class="mdi mdi-account-settings-variant" aria-hidden="true"></i>
                                        </button>
                                        <button id="dy_resetPwdBtn" title="重置密码" type="button" class="btn btn-default">
                                            <i class="mdi mdi-account-key" aria-hidden="true"></i>
                                        </button>
                                        <button id="dy_delBtn" title="删除用户" type="button" class="btn btn-icon waves-effect waves-light btn-danger m-b-5">
                                            <i class="glyphicon glyphicon-trash" aria-hidden="true"></i>
                                        </button>
                                    </div>
                                    

                                    <div class="panel panel-border panel-color panel-purple">
                                        <!-- Default panel contents -->
                                        <div class="panel-heading">
                                            <h3 class="panel-title">用户管理</h3>
                                        </div>
                                        <div class="panel-body">

                                            <div class="portlet">
                                                <div class="portlet-heading portlet-default">
                                                    <h3 class="portlet-title text-dark">
                                                        查询条件
                                                    </h3>
                                                    <div class="portlet-widgets">
                                                        <span class="divider"></span>
                                                        <a data-toggle="collapse" data-parent="#accordion1" href="#bg-default" class="collapsed" aria-expanded="false"><i class="ion-minus-round"></i></a>
                                                        <span class="divider"></span>
                                                    </div>
                                                    <div class="clearfix"></div>
                                                </div>
                                                <div id="bg-default" class="panel-collapse collapse">
                                                    <div class="portlet-body">
                                                        <div class="p-20">
                                                            <form id="formSearch" action="#" data-parsley-validate novalidate>
                                                                <div class="form-group">
                                                                    <label for="dy_search_name">姓名</label>
                                                                    <input type="text" parsley-trigger="change" required
                                                                           placeholder="Enter name" class="form-control" id="dy_search_name">
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="dy_search_phone">手机号码</label>
                                                                    <input type="text" parsley-trigger="change" required
                                                                           placeholder="Enter phone" class="form-control" id="dy_search_phone">
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="dy_search_no">工号</label>
                                                                    <input type="text" parsley-trigger="change" required
                                                                           placeholder="Enter phone" class="form-control" id="dy_search_no">
                                                                </div>
                                                                <div class="form-group text-right m-b-0" style="display:none;">
                                                                    <button id="dy_searchBtn" class="btn btn-primary  w-md waves-effect m-b-5" type="button">
                                                                        查询
                                                                    </button>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Table -->
                                        <table class="table table-default" id="dy_table"></table>
                                    </div>
                                </div>
                            </div>
                        </div>


                    </div> <!-- container -->

                </div> <!-- content -->

                <footer class="footer text-right">
                    
                </footer>

            </div>


            <!-- ============================================================== -->
            <!-- End Right content here -->
            <!-- ============================================================== -->


            

        </div>
        <!-- END wrapper -->



        <script>
            var resizefunc = [];
        </script>

        <!-- jQuery  -->
        <script src="/static/template/assets/js/jquery.min.js"></script>
        <script src="/static/template/assets/js/bootstrap.min.js"></script>
        <script src="/static/template/assets/js/detect.js"></script>
        <script src="/static/template/assets/js/fastclick.js"></script>
        <script src="/static/template/assets/js/jquery.blockUI.js"></script>
        <script src="/static/template/assets/js/waves.js"></script>
        <script src="/static/template/assets/js/jquery.slimscroll.js"></script>
        <script src="/static/template/assets/js/jquery.scrollTo.min.js"></script>
        <script src="/static/template/plugins/switchery/switchery.min.js"></script>

        <!-- Counter js  -->
        <script src="/static/template/plugins/waypoints/jquery.waypoints.min.js"></script>
        <script src="/static/template/plugins/counterup/jquery.counterup.min.js"></script>

        <!-- Flot chart js -->
        <script src="/static/template/plugins/flot-chart/jquery.flot.min.js"></script>
        <script src="/static/template/plugins/flot-chart/jquery.flot.time.js"></script>
        <script src="/static/template/plugins/flot-chart/jquery.flot.tooltip.min.js"></script>
        <script src="/static/template/plugins/flot-chart/jquery.flot.resize.js"></script>
        <script src="/static/template/plugins/flot-chart/jquery.flot.pie.js"></script>
        <script src="/static/template/plugins/flot-chart/jquery.flot.selection.js"></script>
        <script src="/static/template/plugins/flot-chart/jquery.flot.crosshair.js"></script>

        <script src="/static/template/plugins/moment/moment.js"></script>
        <script src="/static/template/plugins/timepicker/bootstrap-timepicker.js"></script>
        <script src="/static/template/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.min.js"></script>
        <script src="/static/template/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
        <script src="/static/template/plugins/clockpicker/js/bootstrap-clockpicker.min.js"></script>
        <script src="/static/template/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>

        <script src="/static/template/plugins/bootstrap-select/js/bootstrap-select.min.js" type="text/javascript"></script>

        <!-- Dashboard init -->
        <script src="/static/template/assets/pages/jquery.dashboard_2.js"></script>

        <!-- App js -->
        <script src="/static/template/assets/js/jquery.core.js"></script>
        <script src="/static/template/assets/js/jquery.app.js"></script>

        <!-- bootstrap-table.js -->
        <script src="/static/js/bootstrap-table.min.js"></script>
        <script src="/static/js/bootstrap-table-zh-CN.min.js"></script>
        <script src="/static/js/bootstrap-table-filter-control.min.js"></script>

        <!-- Sweet-Alert  -->
        <script src="/static/template/plugins/bootstrap-sweetalert/sweet-alert.min.js"></script>

        <!-- 引入header.js公共JS -->
        <script type="text/javascript" src="/static/jsp_js/header.js"></script>

        <script src="/static/jsp_js/userManage.js"></script>

        

    </body>
</html>
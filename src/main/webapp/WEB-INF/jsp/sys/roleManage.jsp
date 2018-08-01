<%--
  User: HuangDongYang<huangdy@pvc123.com>
  Date: 2018/7/5
  Time: 13:39
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
        <title>角色权限管理</title>

        <!-- Sweet Alert -->
        <link href="/static/template/plugins/bootstrap-sweetalert/sweet-alert.css" rel="stylesheet" type="text/css">

        <!-- date range picker -->
        <link href="/static/template/plugins/timepicker/bootstrap-timepicker.min.css" rel="stylesheet">
        <link href="/static/template/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.min.css" rel="stylesheet">
        <link href="/static/template/plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css" rel="stylesheet">
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

		<!-- Plugins css-->
        <link href="/static/template/plugins/bootstrap-tagsinput/css/bootstrap-tagsinput.css" rel="stylesheet" />
        <link href="/static/template/plugins/multiselect/css/multi-select.css"  rel="stylesheet" type="text/css" />
        <link href="/static/template/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
        <link href="/static/template/plugins/bootstrap-select/css/bootstrap-select.min.css" rel="stylesheet" />
        <link href="/static/template/plugins/bootstrap-touchspin/css/jquery.bootstrap-touchspin.min.css" rel="stylesheet" />


        <script src="/static/template/assets/js/modernizr.min.js"></script>

        <!-- bootstrap-table -->
        <link rel="stylesheet" href="/static/css/bootstrap-table.min.css" />

        <style type="text/css">
            #dy_table td{
                 white-space:nowrap;    //td内容不换行
            }
            hr{
                margin-top:0;
                margin-bottom: 0;
            }
            .dy_checkboxDiv{
                overflow-y: scroll;
                min-height:20em;
                max-height: 20em;
                font-size: 1.2em;

                
            }
            .dy_checkboxDiv .checkbox{
                cursor: pointer;
            }
            .dy_checkboxDiv .checkbox:hover{
                background-color:#4bd39621;
            }
        </style>
    </head>


    <body class="fixed-left">
        
        <!-- ========================================= -->
        <!-- modal -->

        <!-- 为角色新增用户 -->
        <div id="dy_roleUserAddModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;" data-backdrop="static">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title">为角色新增用户</h4>
                    </div>
                    <div class="modal-body">
                        
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="" class="control-label">选择新增用户</label>
                                    <select multiple class="dy_selectpicker form-control" data-live-search="true" data-selected-text-format="count > 6">
                                        <option value="">上司1</option>
                                        <option value="">上司2</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default waves-effect" data-dismiss="modal">Close</button>
                        <button type="button" name="submit" class="btn btn-info waves-effect waves-light">Ok</button>
                    </div>
                </div>
            </div>
        </div><!-- /.modal -->

        <!-- 增加角色modal -->
        <div id="dy_addRoleModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;" data-backdrop="static">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title">增加新角色</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="" class="control-label">新角色名称<span class="text-danger">*</span></label>
                                    <input type="text" name="name" class="form-control" required id="" placeholder="">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="" class="control-label">角色描述<span class="text-danger">*</span></label>
                                    <!-- <input type="text" name="remark" class="form-control" required="" > -->
                                    <textarea name="remark" maxlength="255" class="form-control maxLengthTextarea" required="" rows="10"></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default waves-effect" data-dismiss="modal">Close</button>
                        <button type="button" name="submit" class="btn btn-info waves-effect waves-light">Add</button>
                    </div>
                </div>
            </div>
        </div><!-- /.modal -->

        <!-- 修改角色modal -->
        <div id="dy_updateRoleModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;" data-backdrop="static">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title">修改角色</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" disabled name="id" required />
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="" class="control-label">角色名称<span class="text-danger">*</span></label>
                                    <input type="text" name="name" class="form-control" required id="" placeholder="">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="" class="control-label">角色描述<span class="text-danger">*</span></label>
                                    <!-- <input type="text" name="remark" class="form-control" required="" > -->
                                    <textarea name="remark" maxlength="255" class="form-control maxLengthTextarea" required="" rows="10"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="" class="control-label">状态<span class="text-danger">*</span></label>
                                    <select type="text" name="status" class="form-control" >
                                        <option value='NORMAL'>正常</option>
                                        <option value='DISABLED'>禁用</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default waves-effect" data-dismiss="modal">Close</button>
                        <button type="button" name="submit" class="btn btn-info waves-effect waves-light">Update</button>
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
                    	<div class="card-box m-t-10" style="min-height:600px;">
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="form-group">
                                        <label for="exampleInputEmail1" class="text-purple"><i class="mdi mdi-account-circle"></i> 选择角色</label>
                                        <select id="dy_selectRole" class="selectpicker form-control" data-live-search="true" data-max-options="1">
                                            <option>角色1</option>
                                            <option>角色2</option>
                                            <option>角色3</option>
                                            <option>角色4</option>
                                            <option>角色5</option>
                                        </select>
                                    </div>
                                    <div class="btn-group m-b-10">
                                        <button id="dy_addRoleBtn" type="button" class="btn btn-default waves-effect"><i class="glyphicon glyphicon-plus"></i> 增加角色</button>
                                        <button id="dy_updateRoleBtn" type="button" class="btn btn-default waves-effect"><i class="mdi mdi-pencil"></i> 修改</button>
                                        <button id="dy_deleteRole" type="button" class="btn btn-default waves-effect"><i class="glyphicon glyphicon-minus"></i> 删除角色</button>
                                    </div>
                                </div>
                                <div class="col-md-8">
                                    <div class="card-box" id="dy_roleInfoDiv">
                                        <h4>角色描述: </h4>
                                        <p class="text-muted" style="text-indent: 2em;" ></p>
                                        <h5>角色状态: <span class="label label-primary" ></span></h5>
                                    </div>
                                </div>
                            </div>
                            <hr>
                            <h4 style="margin-top:10px;" class="text-purple"><i class="mdi mdi-account-settings"></i> 当前角色的用户列表</h4>
                            <div class="row">
                                <div id="dy_toolbar" class="btn-group">
                                    <button id="dy_addRoleUser" title="增加角色用户" type="button" class="btn btn-default">
                                        <i class="glyphicon glyphicon-plus" aria-hidden="true"> 增加角色用户</i>
                                    </button>
                                    <button id="dy_deleteRoleUser" title="去除角色用户" type="button" class="btn btn-default">
                                        <i class="glyphicon glyphicon-minus" aria-hidden="true"> 去除角色用户</i>
                                    </button>
                                </div>
                                <div class="col-md-12">
                                    <div class="table-responsive">
                                        <table id="dy_table" class="table table-hover m-0">
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <hr style="margin-top:10px;">
                            <h4 class="text-purple"><i class="mdi mdi-checkbox-multiple-marked-circle-outline"></i> 当前角色的权限控制</h4>
                            <p class="text-muted">你可以通过点击选择对应的权限，点击中间按钮即可进行两边的权限分配</p>
                            <button id="dy_updateUrl" type="button" class="btn btn-default waves-effect btn-sm"><i class="fa fa-spin fa-refresh"></i> <span>更新</span></button>
                            <div class="row m-t-10">
                                <div class="col-md-6">
                                    <div class="card-box">

                                        <h4 class="header-title m-t-0 m-b-30">已拥有的权限</h4>

                                        <div id="dy_checkbox_left" class="dy_checkboxDiv">
                                            <!-- <div class="checkbox checkbox-success checkbox-circle">
                                                <input id="" type="checkbox" >
                                                <label for="">
                                                    Success
                                                </label>
                                            </div>
                                            <div class="checkbox checkbox-success checkbox-circle">
                                                <input id="" type="checkbox" >
                                                <label for="">
                                                    Success
                                                </label>
                                            </div> -->
                                            
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="card-box">

                                        <h4 class="header-title m-t-0 m-b-30">未拥有的权限</h4>

                                        <div id="dy_checkbox_right" class="dy_checkboxDiv">
                                            <!-- <div class="checkbox checkbox-warning checkbox-circle">
                                                <input id="" type="checkbox" >
                                                <label for="">
                                                    Warning
                                                </label>
                                            </div>
                                            <div class="checkbox checkbox-warning checkbox-circle">
                                                <input id="" type="checkbox" >
                                                <label for="">
                                                    Warning
                                                </label>
                                            </div> -->
                                            
                                        </div>
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
        <script src="/static/template/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>

        <script src="/static/template/plugins/bootstrap-select/js/bootstrap-select.min.js" type="text/javascript"></script>
        <script src="/static/template/plugins/bootstrap-tagsinput/js/bootstrap-tagsinput.min.js"></script>
        <script type="text/javascript" src="/static/template/plugins/multiselect/js/jquery.multi-select.js"></script>
        <script type="text/javascript" src="/static/template/plugins/jquery-quicksearch/jquery.quicksearch.js"></script>
        <script src="/static/template/plugins/select2/js/select2.min.js" type="text/javascript"></script>

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

        <script src="/static/template/plugins/bootstrap-maxlength/bootstrap-maxlength.min.js" type="text/javascript"></script>

        <!-- 引入header.js公共JS -->
        <script type="text/javascript" src="/static/jsp_js/header.js"></script>

        <script src="/static/jsp_js/roleManage.js"></script>

        

    </body>
</html>
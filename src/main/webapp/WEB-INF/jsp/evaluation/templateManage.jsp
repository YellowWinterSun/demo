<%--
  User: HuangDongYang<huangdy@pvc123.com>
  Date: 2018/6/21
  Time: 8:38
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
        <title>绩效考核模板管理</title>

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

		<!-- ION Slider -->
        <link href="/static/template/plugins/ion-rangeslider/ion.rangeSlider.css" rel="stylesheet" type="text/css"/>
        <link href="/static/template/plugins/ion-rangeslider/ion.rangeSlider.skinModern.css" rel="stylesheet" type="text/css"/>

        <script src="/static/template/assets/js/modernizr.min.js"></script>

        <!--Form Wizard-->
        <link rel="stylesheet" type="text/css" href="/static/template/plugins/jquery.steps/css/jquery.steps.css" />

        <!-- bootstrap-table -->
        <link rel="stylesheet" href="/static/css/bootstrap-table.min.css" />

        <style type="text/css">
            #dy_table td{
                 white-space:nowrap;    //td内容不换行
            }

            .dy_tab2_floatDiv,.dy_tab1_floatDiv{
            	cursor:pointer;	//悬浮鼠标手
            }
        </style>
    </head>


    <body class="fixed-left">
        
        <!-- ========================================= -->
        <!-- modal -->

        <!-- 修改考核项目MODAL -->
        <div id="dy_item_updateModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;" >
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title">考核项目信息</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                        	<input type="hidden" name="id" required />
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="" class="control-label">考核项目名<span class="text-danger">*</span></label>
                                    <input type="text" name="name" class="form-control" required id="" placeholder="">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="" class="control-label">定义及评分标准<span class="text-danger">*</span></label>
                                    <textarea name="content" class="form-control" rows="10"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="" class="control-label">权重<span class="text-danger">*</span></label>
                                    <input type="text" name="weight" class="form-control dy_rangeInput">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="" class="control-label">考核维度<span class="text-danger">*</span></label>
                                    <select class="selectpicker form-control" data-live-search="true" data-max-options="1">
                                        <option>部门1</option>
                                        <option>部门2</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default waves-effect" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-danger waves-effect dy_modalDeleteBtn waves-light">Delete</button>
                        <button type="button" class="btn btn-primary waves-effect dy_modalSubmitBtn waves-light">Update</button>
                    </div>
                </div>
            </div>
        </div><!-- /.modal -->

        <!-- 增加考核项目MODAL -->
        <div id="dy_item_addModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;" >
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title">新增考核项目信息</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="" class="control-label">考核项目名<span class="text-danger">*</span></label>
                                    <input type="text" name="name" class="form-control" required id="" placeholder="">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="" class="control-label">定义及评分标准<span class="text-danger">*</span></label>
                                    <textarea name="content" class="form-control" rows="10"></textarea>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="" class="control-label">权重<span class="text-danger">*</span></label>
                                    <input type="text" name="weight" class="form-control dy_rangeInput">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="" class="control-label">考核维度<span class="text-danger">*</span></label>
                                    <select class="selectpicker form-control" data-live-search="true" data-max-options="1">
                                        <option>部门1</option>
                                        <option>部门2</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default waves-effect" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary waves-effect dy_modalSubmitBtn waves-light">Add</button>
                    </div>
                </div>
            </div>
        </div><!-- /.modal -->

        <!-- 选择考核项目modal -->
        <div id="dy_item_selectModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;" data-backdrop="static">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title">为考核模板选择<b>考核项目</b></h4>
                    </div>
                    <div class="modal-body">
                        <div class="portlet">
                            <div class="portlet-heading portlet-default">
                                <h3 class="portlet-title text-dark">
                                    考核维度1
                                </h3>
                                <div class="portlet-widgets">
                                    <span class="divider"></span>
                                    <a data-toggle="collapse" data-parent="#accordion1" href="#dy_bg-default1" class="collapsed" aria-expanded="false"><i class="ion-minus-round"></i></a>
                                    <span class="divider"></span>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                            <div id="dy_bg-default1" class="panel-collapse collapse">
                                <div class="portlet-body">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="card-box widget-box-two widget-two-primary dy_tab1_floatDiv" itemId=''>
                                                <i class="mdi mdi-check widget-two-icon" style="display:none;"></i>
                                                <div class="wigdet-two-content">
                                                    <h5 class="m-0 text-uppercase font-600 font-secondary text-primary" title="">
                                                        考核项目:
                                                    </h5>
                                                    <p class="text-muted text-dark" style="text-indent:2em;">文化认同、工作态度、行为表现.</p>
                                                    <h5 class="m-0 text-uppercase font-600 font-secondary text-primary">
                                                        定义及评分标准:
                                                    </h5>
                                                    <p class="text-muted text-dark" style="text-indent:2em;">
                                                        认同公司鸟人精神及“三成四风”文化理念；心态积极，主动承担，工作具认真、主动、热情，无抱怨、拖延、推诿；服从与配合度好，无违反公司制度行为，无因工作态度而引发的失误或投诉。  
                                                    </p>
                                                    <p class="text-muted m-0 text-primary">权重:<b data-plugin="counterup">35</b>%</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <div class="alert alert-icon alert-warning alert-dismissible fade in" role="alert">
                            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>
                            <i class="mdi mdi-alert"></i>
                            <strong>选择完考核项目后，必须点"Select-OK！"按钮才可生效</strong> 
                        </div>
                        <button type="button" class="btn btn-warning waves-effect waves-light" name="reset">Reset!</button>
                        <button type="button" class="btn btn-default waves-effect waves-light" name="refresh">Refresh!</button>
                        <button type="button" class="btn btn-default waves-effect" data-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary waves-effect waves-light" name="ok">Select-OK!</button>
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
                        		<div class="card-box dy_min_height">
                                    <ul class="nav nav-tabs navtab-bg nav-justified">
                                        <li class="">
                                            <a href="#dy_tab1" data-toggle="tab" aria-expanded="false">
                                                <span class="visible-xs"><i class="fa fa-home"></i></span>
                                                <span class="hidden-xs">绩效考核表模板</span>
                                            </a>
                                        </li>
                                        <li class="">
                                            <a href="#dy_tab2" data-toggle="tab" aria-expanded="true">
                                                <span class="visible-xs"><i class="fa fa-user"></i></span>
                                                <span class="hidden-xs">考核项目</span>
                                            </a>
                                        </li>
                                    </ul>
                                    <div class="tab-content">
                                        <div class="tab-pane" id="dy_tab1">
                                            <div class="row">
					                            <div class="col-sm-12">
					                                <div class="card-box" style="margin-top:10px;">

					                                    <div id="dy_toolbar" class="btn-group">
					                                        <button id="dy_tab1_addBtn" title="添加模板" type="button" class="btn btn-default">
					                                            <i class="mdi mdi-table-column-plus-before" aria-hidden="true"></i>
					                                        </button>
					                                        <button id="dy_tab1_updateBtn" title="编辑模板" type="button" class="btn btn-default">
					                                            <i class="mdi mdi-table-edit" aria-hidden="true"></i>
					                                        </button>
                                                            <button id="dy_tab1_showBtn" title="展示模板" type="button" class="btn btn-primary">
                                                                <i class="mdi mdi-airplay" aria-hidden="true"></i>
                                                            </button>
					                                        <button id="dy_tab1_delBtn" title="删除模板" type="button" class="btn btn-icon waves-effect waves-light btn-danger m-b-5">
					                                            <i class="mdi mdi-table-row-remove" aria-hidden="true"></i>
					                                        </button>
					                                    </div>
					                                    

					                                    <div id="dy_tab1_panel1" class="panel panel-border panel-color panel-purple">
					                                        <!-- Default panel contents -->
					                                        <div class="panel-heading">
					                                            <h3 class="panel-title">部门岗位管理</h3>
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
					                                                        <div class="row">
					                                                        	<div class="col-sm-12">
					                                                        		<div class="p-20">
							                                                            <form id="dy_tab1_formSearch" action="#" data-parsley-validate novalidate>
							                                                                <div class="form-group">
							                                                                    <label for="">筛选岗位</label>
							                                                                    <!-- <input type="text" parsley-trigger="change" required
							                                                                           placeholder="Enter name" class="form-control" id="dy_search_departmentName"> -->
							                                                                    <select name="" class="selectpicker form-control" id="dy_tab1_search_jobName" data-max-options="1" data-live-search="true">
							                                                                    	<optgroup label="">
							                                                                    		<option value="">全部</option>
							                                                                    	</optgroup>
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
							                                                                <div class="form-group">
							                                                                    <label for="">模板名称</label>
							                                                                    <input type="text" parsley-trigger="change" required
							                                                                           placeholder="" class="form-control" id="dy_tab1_search_name">
							                                                                </div>
							                                                                <div class="form-group text-right m-b-0" style="display:none;">
							                                                                    <button id="dy_tab2_searchBtn" class="btn btn-primary  w-md waves-effect m-b-5" type="button">
							                                                                        查询
							                                                                    </button>
							                                                                </div>
							                                                            </form>
							                                                        </div>
					                                                        	</div>
					                                                        </div>
					                                                    </div>
					                                                </div>
					                                            </div>
					                                        </div>

					                                        <!-- Table -->
					                                        <table class="table-colored table-purple" id="dy_table"></table>
					                                    </div>

                                                        <!-- panel-add -->
                                                        <div  id="dy_tab1_panel2" class="panel panel-border panel-success" style="display:none;">
                                                            <!-- Default panel contents -->
                                                            <div class="panel-heading">
                                                                <h3 class="panel-title">增加绩效考核模板</h3>
                                                            </div>
                                                            <div class="panel-body">
                                                                <p class="text-muted font-13 m-b-10">
                                                                    增加绩效考核模板
                                                                </p>

                                                                <div class="p-20">
                                                                    <form action="#" data-parsley-validate novalidate>
                                                                        <input type="hidden" name="id" />
                                                                        <div class="form-group">
                                                                            <label for="">绩效考核模板名称<span class="text-danger">*</span></label>
                                                                            <input type="text" name="name" parsley-trigger="change" required
                                                                                   placeholder="Enter name" class="form-control">
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label for="">考核岗位<span class="text-danger">*</span></label>
                                                                            <select name="jobId" class="selectpicker form-control" data-max-options="1" data-live-search="true">
                                                                                <optgroup label="">
                                                                                    <option value="">全部</option>
                                                                                </optgroup>
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
                                                                        <div class="form-group">
                                                                            <label for=""><u id="dy_u_itemModal" style="color:blue;cursor:pointer;">选择考核项目</u><span class='text-danger'> (选择完考核项目后必须点击Ok按钮才生效)</span> : 当前总权重 <b name='weightB'>0</b>%</label>
                                                                            <input type="hidden" name="itemIds" parsley-trigger="change"
                                                                                   placeholder="" class="form-control">
                                                                        </div>
                                                                        
                                                                        <div class="form-group text-right m-b-0">
                                                                            <button class="btn btn-primary waves-effect waves-light" name="submit" type="button">
                                                                                Submit
                                                                            </button>
                                                                            <button type="button" class="btn btn-default waves-effect m-l-5" name="cancel">
                                                                                Cancel
                                                                            </button>
                                                                        </div>

                                                                    </form>
                                                                </div>
                                                            </div>
                                                        </div>

					                                </div>
					                            </div>
					                        </div>
                                        </div>
                                        <div class="tab-pane" id="dy_tab2">
                                        	<div class="row">
                                        		<div class="col-sm-12">
                                        			<div class="input-group">
                                                        <span class="input-group-btn">
                                                        <button id="dy_tab2_addBtn" type="button" class="btn waves-effect waves-light btn-default" title='增加考核项目'><i class="fa fa-plus"></i></button>
                                                        </span>
                                                        <div class="col-sm-6">
                                                        	<select id="dy_tab2_searchDimensionality" class="selectpicker form-control" data-live-search="true" data-max-options="1">
						                                        <option value=''>全部</option>
						                                        <option>考核维度1</option>
						                                        <option>考核维度2</option>
						                                    </select>
                                                        </div>
                                                        <div class="col-sm-3">
                                                       	 	<input type="text" id="dy_tab2_searchName" name="example-input1-group2" class="form-control" placeholder="输入考核项目名...进行搜索">
                                                   	 	</div>
                                                    </div>
                                        		</div>
                                        	</div>
                                        	<div class="clearfix"></div>
                                        	<h3> <i class="glyphicon glyphicon-th-large"></i> 考核项目一览</h3>

                                            <div id="dy_tab2_floatRow" class="row" style="margin-top:10px;">
                                            	
                                            	<div class="col-lg-4" style="float:left;">
                                            		<div class="card-box widget-box-two widget-two-primary dy_tab2_floatDiv" itemId=''>
					                                    <div class="wigdet-two-content">
					                                        <h5 class="m-0 text-uppercase font-600 font-secondary text-primary" title="">
					                                        	考核项目:
															</h5>
															<p class="text-muted text-dark" style="text-indent:2em;">文化认同、工作态度、行为表现.</p>
					                                        <h5 class="m-0 text-uppercase font-600 font-secondary text-primary">
					                                        	定义及评分标准:
															</h5>
															<p class="text-muted text-dark" style="text-indent:2em;">
																认同公司鸟人精神及“三成四风”文化理念；心态积极，主动承担，工作具认真、主动、热情，无抱怨、拖延、推诿；服从与配合度好，无违反公司制度行为，无因工作态度而引发的失误或投诉。	
															</p>
					                                        <p class="text-muted m-0 text-primary">权重:<b data-plugin="counterup">35</b>%</p>
					                                    </div>
					                                </div>
                                            	</div>

                                            	<!-- ... -->
                                            </div>
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

        <script src="/static/template/plugins/ion-rangeslider/ion.rangeSlider.min.js"></script>

        <!--wizard initialization-->
        <!--Form Wizard-->
        <script src="/static/template/plugins/jquery.steps/js/jquery.steps.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="/static/template/plugins/jquery-validation/js/jquery.validate.min.js"></script>

        <!-- 引入header.js公共JS -->
        <script type="text/javascript" src="/static/jsp_js/header.js"></script>

        <script src="/static/jsp_js/templateManage.js"></script>

        

    </body>
</html>
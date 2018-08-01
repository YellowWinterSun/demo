<%--
  User: HuangDongYang<huangdy@pvc123.com>
  Date: 2018/6/29
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
    <title>绩效考核表总览</title>

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

    <!-- Tooltipster css -->
    <link rel="stylesheet" href="/static/template/plugins/tooltipster/tooltipster.bundle.min.css">

    <!-- bootstrap-table -->
    <link rel="stylesheet" href="/static/css/bootstrap-table.min.css" />

    <style type="text/css">
        #dy_table td{
            white-space:nowrap;    //td内容不换行
        }
        .width10{
        	width:10%;
        }
        .width15{
        	width:15%;
        }
    </style>
</head>


<body class="fixed-left">

<!-- ========================================= -->
<!-- modal -->

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

                <div class="card-box" style="margin-top:10px;">
                    <div class="panel panel-border panel-color panel-purple">
                        <!-- Default panel contents -->
                        <div class="panel-heading">
                            <h3 class="panel-title">绩效考核表总览</h3>
                        </div>
                        <div class="panel-body p-b-0">
                            <h4 class="text-uppercase">
                                <i class="ion-arrow-down-b"></i> &nbsp;绩效考核表总览
                            </h4>
                            <p class="text-muted" style="text-indent: 0rem;">条件筛选</p>
                            <!-- <div style="width:10%;" class="border"></div> -->

	                    	<div class="row m-t-10">
	                    		
		                    	<div class="btn-group m-l-10">
			                        <button type="button" title="筛选条件" class="btn btn-primary waves-light" disabled>
		                               <i class="fa fa-arrow-circle-right"></i>
		                            </button>
		                    		<input id="dy_searchYear" type="text" placeholder="筛选年份" class="btn btn-default dy_datepickerYear dy_enterListener width10"/>
		                    		<input id="dy_searchMonth" type="text" placeholder="筛选月份" class="btn btn-default dy_datepickerMonth dy_enterListener width10"/>
		                    		<div class="btn-group">
		                                <button dydo id="dy_searchStatus" statusValue="" type="button" class="btn btn-default dropdown-toggle waves-effect" data-toggle="dropdown" aria-expanded="false"><span>考核状态</span> <span class="caret"></span> </button>
		                                <ul class="dropdown-menu">
		                                	<li><a class="dy_statusBtn" statusValue="" href="javascript:void(0);">全部</a></li>
		                                    <li><a class="dy_statusBtn" statusValue="HR" href="javascript:void(0);">考核完毕</a></li>
		                                    <li><a class="dy_statusBtn" statusValue="BOSS2" href="javascript:void(0);">待最终审核</a></li>
		                                    <li><a class="dy_statusBtn" statusValue="BOSS1" href="javascript:void(0);">待部门总监审核</a></li>
		                                    <li><a class="dy_statusBtn" statusValue="INIT" href="javascript:void(0);">待直属上司审核</a></li>
		                                </ul>
		                            </div>
		                    		<input dydo id="dy_searchName" type="text" placeholder="输入考核人姓名" class="btn btn-default dy_enterListener width15"/>
		                    		<input dydo id="dy_searchNo" type="text" placeholder="输入考核人工号" class="btn btn-default dy_enterListener width15"/>
		                    	</div>
	                    	</div>

	                    	<div class="row m-t-5">
	                    		<div class="col-md-3">
	                    			<div class="form-group">
	                    				<label for="">筛选部门</label>
	                    				<select id="dy_searchDepartment" class="selectpicker" data-live-search="true" data-max-options="1">
		                                    <option value=''>全部</option>
	                                        <option>部门1</option>
	                                        <option>部门2</option>
	                                        <option>部门3</option>
	                                    
	                                        <option>部门4</option>
	                                        <option>部门5</option>
	                                        <option>部门6</option>
		                                    
		                                </select>
	                    			</div>
	                    		</div>
	                    		<div class="col-md-3">
	                    			<div class="form-group">
	                    				<label for="">筛选岗位</label>
	                    				<select id="dy_searchJob" class="selectpicker" data-live-search="true" data-max-options="1">
	                    					<option value="">全部</option>
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
	                    		<div class="col-md-3">
	                    			<div class="form-group">
                                        <label>筛选部门时，是否包含子部门</label>

                                        <div class="checkbox checkbox-pink">
                                            <input type="checkbox" name="" id="dy_searchRecurve" value="ski" data-parsley-mincheck="2" data-parsley-multiple="hobbies">
                                            <label for="dy_searchRecurve"> 包含子部门 </label>
                                        </div>

                                    </div>
	                    		</div>
	                    	</div>
	                    	<div class="row m-t-10">
	                    		<div class="col-md-8">
		                    		<div class="form-group">
		                    			<label for="">功能按钮</label>
		                    			<div class="btn-group m-l-10">
											<button id="dy_openBtn" title="查看绩效考核表" type="button" class="btn btn-default">
					                            <i class="mdi mdi-file-find" aria-hidden="true"> 查看考核表</i>
					                        </button>
                                            <button id="dy_returnExamine" title="生成部门绩效考核汇总表" type="button" class="btn btn-primary">
                                                <i class="mdi mdi-table-large" aria-hidden="true"> 绩效考核表管理</i>
                                            </button>
					                        <button id="dy_openDepartmentExamine" title="生成部门绩效考核汇总表" type="button" class="btn btn-default">
					                            <i class="mdi mdi-table-large" aria-hidden="true"> 生成部门绩效考核汇总表</i>
					                        </button>
                                            <button id="dy_exportTable" title="导出EXCEL" type="button" class="btn btn-info" style="display:none;">
                                                <i class="mdi mdi-printer" aria-hidden="true"> 导出EXCEL</i>
                                            </button>
				                    	</div>
		                    		</div>
	                    		</div>
	                    	</div>

	                    	<!-- <div style="width:100%;" class="border m-t-10"></div> -->
                        </div>

                        <!-- Table -->
                        <table class="table-colored table-purple" id="dy_table"></table>
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
<script src="/static/js/bootstrap-table-export.js"></script>
<script src="/static/js/bootstrap-table-filter-control.min.js"></script>

<!-- jquery Export -->
<script src="/static/js/pdfmake.min.js"></script>
<script src="/static/js/vfs_fonts.js"></script>
<script src="/static/js/FileSaver.min.js"></script>
<script src="/static/js/jspdf.min.js"></script>
<script src="/static/js/jspdf.plugin.autotable.js"></script>
<script src="/static/js/tableExport.js"></script>

<!-- Sweet-Alert  -->
<script src="/static/template/plugins/bootstrap-sweetalert/sweet-alert.min.js"></script>

<script src="/static/template/plugins/ion-rangeslider/ion.rangeSlider.min.js"></script>

<!--wizard initialization-->
<!--Form Wizard-->
<script src="/static/template/plugins/jquery.steps/js/jquery.steps.min.js" type="text/javascript"></script>
<script type="text/javascript" src="/static/template/plugins/jquery-validation/js/jquery.validate.min.js"></script>

<!-- Tooltipster js -->
<script src="/static/template/plugins/tooltipster/tooltipster.bundle.min.js"></script>
<script src="/static/template/assets/pages/jquery.tooltipster.js"></script>

<!-- 引入header.js公共JS -->
        <script type="text/javascript" src="/static/jsp_js/header.js"></script>

<script type="text/javascript" src="/static/jsp_js/viewExamine.js"></script>


</body>
</html>
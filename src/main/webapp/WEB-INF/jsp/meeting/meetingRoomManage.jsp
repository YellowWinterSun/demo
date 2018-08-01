<%--
  User: HuangDongYang<huangdy@pvc123.com>
  Date: 2018/7/4
  Time: 8:20
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
    <title>会议室管理</title>

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

    <!-- fileInput -->
    <link rel="stylesheet" href="/static/css/fileinput.min.css"/>

    <!-- bootstrap-table-edittable -->
    <link rel="stylesheet" href="/static/css/bootstrap-editable.css"/>

    <style type="text/css">
        /*#dy_table td{
            white-space:nowrap;    //td内容不换行
        }*/
        .width10{
        	width:10%;
        }
        .width15{
        	width:15%;
        }
        .dy_tdImg{
        	height:5em;
        	transition: height 1s;
        	transition-delay:0.2s;
        	-webkit-transition: height 1s; /* Safari */
        	-webkit-transition-delay:0.2s;
        }
        .dy_tdImg:hover{
        	height:15em;
        	cursor:pointer;
        }
    </style>
</head>


<body class="fixed-left">

<!-- ========================================= -->
<!-- modal -->
        <div id="dy_addRoomModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;" data-backdrop="static">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title">新增会议室</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                        	<div class="col-md-12">
                                    <div class="form-group">
                                        <label for="">会议室名称 *</label>
                                        <input name="name" required type="text" class="form-control" placeholder="">
                                    </div>
                                    <div class="form-group">
                                        <label for="">会议室描述 *</label>
                                        <!-- <input name="remark" required type="text" class="form-control" placeholder=""> -->
                                        <textarea maxlength="255" name="remark" required class="form-control maxLengthTextarea" rows="6"></textarea>
                                    </div>
                                    <div class="form-group">
                                        <label for="">可容纳人数 *</label>
                                        <input name="size" required type="number" class="form-control" placeholder="">
                                    </div>
                                    <div class="checkbox checkbox-primary">
                                        <input id="dy_addRoomModal_checkbox" type="checkbox">
                                        <label class="text-primary" for="dy_addRoomModal_checkbox">
                                            使用系统默认的展示图
                                        </label>
                                    </div>
                                <form role="form" onsubmit="return false;" enctype="multipart/form-data">
                                    <div class="form-group m-t-10" id="dy_addRoomModal_fileDiv">
                                        <label for="">上传会议室展示图 (建议统一<mark class="text-primary">4：3</mark>)</label>
                                        <input type="file" name="fileimg" id="fileimg" class="form-control dy_fileInput" placeholder="">
                                    </div>
                                </form>

                            </div> <!-- end col -->
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default waves-effect" data-dismiss="modal">Close</button>
                        <button name="toSubmit" type="button" class="btn btn-primary waves-effect waves-light">提交</button>
                    </div>
                </div>
            </div>
        </div><!-- /.modal -->

        <div id="dy_updateImgModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;" data-backdrop="static">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title">更新展示图</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                        	<div class="col-md-12">
                                <input type="hidden" name="id"/>
                                <form role="form" onsubmit="return false;" enctype="multipart/form-data">
                                    <div class="form-group m-t-10">
                                        <label for="">上传会议室展示图 (建议统一<mark class="text-primary">4：3</mark>)(如果上传的图片过大，需要刷新浏览器以清除浏览器缓存的图片)</label>
                                        <input type="file" name="fileimg2" id="fileimg2" class="form-control dy_fileInput" placeholder="">
                                    </div>
                                </form>

                            </div> <!-- end col -->
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default waves-effect" data-dismiss="modal">Close</button>
                        <button name="toSubmit" type="button" class="btn btn-primary waves-effect waves-light">提交</button>
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

                <div class="card-box" style="margin-top:10px;">
                    <div id="dy_toolbar" class="btn-group">
                    	<button id="dy_addRoomBtn" title="添加会议室" type="button" class="btn btn-default">
                            <i class="ion-android-add" aria-hidden="true"> 添加</i>
                        </button>
                        <button id="dy_changeStatusBtn" title="改变会议室状态" type="button" class="btn btn-default">
                            <i class="mdi mdi-cached" aria-hidden="true"> 改变状态</i>
                        </button>
                        <button id="dy_deleteBtn" title="删除会议室" type="button" class="btn btn-danger">
                            <i class="ion-trash-b" aria-hidden="true"></i>
                        </button>
                    </div>
                    

                    <div class="panel panel-border panel-color panel-purple">
                        <!-- Default panel contents -->
                        <div class="panel-heading">
                            <h3 class="panel-title"></h3>
                        </div>
                        <div class="panel-body">
                            <h4 class="text-uppercase">
                                <i class="ion-arrow-down-b"></i> &nbsp;会议室管理
                            </h4>

	                    	<div class="row m-t-5">
	                    		<div class="col-md-5">
	                    			<div class="form-group">
	                    				<label for="">筛选会议室</label>
		                                <input id="dy_searchRemark" type="text" placeholder="搜索会议室简述" class="btn btn-default form-control"/>
	                    			</div>
	                    		</div>
	                    		<div class="col-md-2">
	                    			<div class="form-group">
	                    				<label for="">筛选状态</label>
	                    				<select id="dy_searchStatus" class="selectpicker form-control" data-max-options="1">
	                    					<option value=''>全部</option>
	                    					<option value='NORMAL' data-icon="mdi mdi-check-circle text-primary">正常</option>
	                    					<option value='DISABLED' data-icon="mdi mdi-close-circle text-danger">禁用</option>
		                                </select>
	                    			</div>
	                    		</div>
	                    	</div>
	                    	<p> 有虚线的字体，可点击进行编辑 </p>

	                    	<p class="m-t-0 p-t-0"> 鼠标移动到图片上方，可查看图片大图，如果点击图片，可以更换图片 </p>
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
<!-- Jquery filer js -->
<script src="/static/template/plugins/jquery.filer/js/jquery.filer.min.js"></script>

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

<!-- Tooltipster js -->
<script src="/static/template/plugins/tooltipster/tooltipster.bundle.min.js"></script>
<script src="/static/template/assets/pages/jquery.tooltipster.js"></script>

<!-- fileInput js -->
<script type="text/javascript" src="/static/js/fileinput.min.js"></script>

<!-- bootstrap-table-editable js -->
<script type="text/javascript" src="/static/js/bootstrap-editable.min.js"></script>
<script type="text/javascript" src="/static/js/bootstrap-table-editable.js"></script>

<script src="/static/template/plugins/bootstrap-maxlength/bootstrap-maxlength.min.js" type="text/javascript"></script>

<!-- 引入header.js公共JS -->
<script type="text/javascript" src="/static/jsp_js/header.js"></script>


<script type="text/javascript" src="/static/jsp_js/meetingRoomManage.js"></script>


</body>
</html>
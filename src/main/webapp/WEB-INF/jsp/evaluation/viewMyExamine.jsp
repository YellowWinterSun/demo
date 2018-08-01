<%--
  User: HuangDongYang<huangdy@pvc123.com>
  Date: 2018/6/28
  Time: 11:48
  查看本人绩效考核情况
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
    <title>查看本人绩效考核情况</title>

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
                    <div id="dy_toolbar" class="btn-group">
                        <button id="dy_openBtn" title="查看绩效考核表" type="button" class="btn btn-default">
                            <i class="mdi mdi-file-find" aria-hidden="true"></i>
                        </button>
                    </div>
                    

                    <div class="panel panel-border panel-color panel-purple">
                        <!-- Default panel contents -->
                        <div class="panel-heading">
                            <h3 class="panel-title">查看本人绩效考核表</h3>
                        </div>
                        <div class="panel-body">
                            <h4 class="text-uppercase">
                                <i class="ion-arrow-down-b"></i> &nbsp;我的绩效考核表
                            </h4>
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

<!-- 引入header.js公共JS -->
        <script type="text/javascript" src="/static/jsp_js/header.js"></script>

<script type="text/javascript" src="/static/jsp_js/viewMyExamine.js"></script>




</body>
</html>
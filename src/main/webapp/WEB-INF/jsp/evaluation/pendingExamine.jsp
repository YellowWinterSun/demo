<%--
  User: HuangDongYang<huangdy@pvc123.com>
  Date: 2018/6/27
  Time: 12:21
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
        <title>待处理的绩效考核表</title>

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
            .dy_ul{
            	font-size:1.8rem;
            	margin-left:4rem;
            }
            .dy_ul li{
            	margin-top:1rem;
            	font-size:1.5rem;
            }
            .dy_exam_boss1,.dy_exam_boss2,.dy_exam_hr{
            	cursor:pointer;	//悬浮鼠标手
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

                        <div class="row">
                        	<div class="col-sm-12">
                        		<div class="card-box" style="margin-top:10px;">
                        			<h2><i class="glyphicon glyphicon-list-alt"></i> <span class="text-dark">你未处理的绩效考核表</span></h2>

                        			<c:if test="${fn:length(requestScope.list1) == 0 && fn:length(requestScope.list2) == 0 && fn:length(requestScope.list3) == 0}">
                        				<h3 class="text-primary"><i class="fa fa-spin fa-spinner"></i> 你没有需要处理的绩效考核表</h3>
                        			</c:if>

                        			<c:if test="${fn:length(requestScope.list1) > 0}">
                        				<h3><i class="mdi mdi-numeric-1-box"></i> <span class="text-dark">您是其直属上司，为其填写你的评分</span></h3>
                        			<div class="clearfix"></div>

                                     <ul class="list-unstyled dy_ul" >
                                         <c:forEach items="${requestScope.list1}" var="item">
                                             <li>
                                                 <i class="mdi mdi-emoticon-cool"></i><u class="dy_exam_boss1" officialEvaluationId="<c:out value="${item.id}"/> "><mark><c:out value="${item.userName}"/>&nbsp;-&nbsp;<c:out value="${item.jobName}"/>绩效考核表</mark></u>
                                             </li>
                                         </c:forEach>

                                     </ul>
                                    </c:if>

                                    <c:if test="${fn:length(requestScope.list2) > 0}">
                                        <h3><i class="mdi mdi-numeric-2-box"></i> <span class="text-dark">您是其部门负责人，为考核人填写特别分</span></h3>
                                        <div class="clearfix"></div>

                                        <ul class="list-unstyled dy_ul" >
                                            <c:forEach items="${requestScope.list2}" var="item">
                                                <li>
                                                    <i class="mdi mdi-emoticon-cool"></i><u class="dy_exam_boss2" officialEvaluationId="<c:out value="${item.id}"/> "><mark><c:out value="${item.userName}"/>&nbsp;-&nbsp;<c:out value="${item.jobName}"/>绩效考核表</mark></u>
                                                </li>
                                            </c:forEach>

                                        </ul>
                                    </c:if>

                                    <c:if test="${fn:length(requestScope.list3) > 0}">
                                        <h3><i class="mdi mdi-numeric-3-box"></i> <span class="text-dark">您是人事负责人，审核最终结果</span></h3>
                                        <div class="clearfix"></div>

                                        <ul class="list-unstyled dy_ul" >
                                            <c:forEach items="${requestScope.list3}" var="item">
                                                <li>
                                                    <i class="mdi mdi-emoticon-cool"></i><u class="dy_exam_hr" officialEvaluationId="<c:out value="${item.id}"/> "><mark><c:out value="${item.userName}"/>&nbsp;-&nbsp;<c:out value="${item.jobName}"/>绩效考核表</mark></u>
                                                </li>
                                            </c:forEach>

                                        </ul>
                                    </c:if>


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

        <script>
        	$(function(){
	
				//考核模板点击事件
				$(document).on('click', '.dy_exam_boss1', function(){
					var id = $(this).attr('officialEvaluationId');
					console.log(id);

					window.open("/evaluation/examine/pendingExamine/examineBoss1TableShow?officialEvaluationId=" + id);
				});

				$(document).on('click', '.dy_exam_boss2', function(){
					var id = $(this).attr('officialEvaluationId');
					console.log(id);

					window.open("/evaluation/examine/pendingExamine/examineBoss2TableShow?officialEvaluationId=" + id);
				});

				$(document).on('click', '.dy_exam_hr', function(){
					var id = $(this).attr('officialEvaluationId');
					console.log(id);

					window.open("/evaluation/examine/pendingExamine/examineHrTableShow?officialEvaluationId=" + id);
				});
			});
        </script>

        

    </body>
</html>
<%--
  User: HuangDongYang<huangdy@pvc123.com>
  Date: 2018/7/2
  Time: 9:27
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
        <title>预约会议室</title>

        <!--venobox lightbox-->
        <link rel="stylesheet" href="/static/template/plugins/magnific-popup/css/magnific-popup.css"/>

        <!--Form Wizard-->
        <link rel="stylesheet" type="text/css" href="/static/template/plugins/jquery.steps/css/jquery.steps.css" />

        <!-- App css -->
        <link href="/static/template/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="/static/template/assets/css/core.css" rel="stylesheet" type="text/css" />
        <link href="/static/template/assets/css/components.css" rel="stylesheet" type="text/css" />
        <link href="/static/template/assets/css/icons.css" rel="stylesheet" type="text/css" />
        <link href="/static/template/assets/css/pages.css" rel="stylesheet" type="text/css" />
        <link href="/static/template/assets/css/menu.css" rel="stylesheet" type="text/css" />
        <link href="/static/template/assets/css/responsive.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="/static/template/plugins/switchery/switchery.min.css">

		<!-- Sweet Alert -->
	    <link href="/static/template/plugins/bootstrap-sweetalert/sweet-alert.css" rel="stylesheet" type="text/css">

	    <!-- date range picker -->
	    <link href="/static/template/plugins/timepicker/bootstrap-timepicker.min.css" rel="stylesheet">
	    <link href="/static/template/plugins/bootstrap-colorpicker/css/bootstrap-colorpicker.min.css" rel="stylesheet">
	    <link href="/static/template/plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css" rel="stylesheet">
	    <link href="/static/template/plugins/clockpicker/css/bootstrap-clockpicker.min.css" rel="stylesheet">
	    <link href="/static/template/plugins/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

	    <link href="/static/template/plugins/bootstrap-select/css/bootstrap-select.min.css" rel="stylesheet" />

	    <!-- ION Slider -->
	    <link href="/static/template/plugins/ion-rangeslider/ion.rangeSlider.css" rel="stylesheet" type="text/css"/>
	    <link href="/static/template/plugins/ion-rangeslider/ion.rangeSlider.skinModern.css" rel="stylesheet" type="text/css"/>

	    <!-- Tooltipster css -->
        <link rel="stylesheet" href="/static/template/plugins/tooltipster/tooltipster.bundle.min.css">


        <script src="/static/template/assets/js/modernizr.min.js"></script>

        <style>
        	.dy_showP{
        		overflow: hidden;
			    text-overflow: ellipsis;
			    display: -webkit-box;
			    -webkit-line-clamp: 5;
			    -webkit-box-orient: vertical;
        	}
        	.dy_boxShadow{
        		box-shadow: 0px 0px 7px 0px #04040459;
        	}
        	.portfolioFilter a.current {
			  	color: #4bd396;
			  	font-size:1.8rem;
			}
			.width30{
				width:30%;
			}
			.dy_height8em{
				height:8em;
			}
			.popover {
			    width:20em;
			}
			
    	</style>
    </head>


    <body class="fixed-left">

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
							<div class="col-xs-12">
								<div class="page-title-box">
                                    <h4 class="page-title"><i class="fa fa-comment-o"></i> 预约会议室 </h4>
                                    
                                    <div class="clearfix"></div>
                                </div>
							</div>
						</div>
                        <!-- end row -->


                        <!-- SECTION FILTER
                        ================================================== -->
                        <div class="row m-b-10">
                            <div class="col-lg-12 col-md-12 col-sm-12 ">
                                <div class="card-box">
                            		<h4 class="m-t-0 header-title m-b-30"><b>我要预约会议室</b></h4>

									<form id="basic-form" action="#">
                                        <div>
                                            <h3>选择会议室</h3>
                                            <section>
                                                <div class="form-group clearfix">
                                                    <label class="col-lg-2 control-label " for="">选择会议室 *</label>
                                                    <div class="col-lg-6">
                                                        <select changeListener="true" class="form-control required dy_selectpicker" data-live-search="true" data-max-options="1">
                                                            <c:forEach items="${requestScope.listRoom}" var="item">
                                                                <c:choose>
                                                                    <c:when test="${requestScope.roomId != null && requestScope.roomId == item.id}">
                                                                        <option selected value="<c:out value="${item.id}"/>"><c:out value="${item.name}"/></option>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <option value="<c:out value="${item.id}"/>"><c:out value="${item.name}"/></option>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>
                                                        	<%--<option value="会议室1">会议室1</option>--%>
				                                            <%--<option>会议室2</option>--%>
				                                            <%--<option>会议室3</option>--%>
				                                            <%--<option>会议室1</option>--%>
				                                            <%--<option>会议室2</option>--%>
				                                            <%--<option>会议室3</option>--%>
				                                            <%--<option>会议室1</option>--%>
				                                            <%--<option>会议室2</option>--%>
				                                            <%--<option>会议室3</option>--%>
				                                            <%--<option>会议室1</option>--%>
				                                            <%--<option>会议室2</option>--%>
				                                            <%--<option>会议室3</option>--%>
				                                            <%--<option>会议室1</option>--%>
				                                            <%--<option>会议室2</option>--%>
				                                            <%--<option>会议室2</option>--%>
				                                            <%--<option>会议室3</option>--%>
				                                            <%--<option>会议室1</option>--%>
				                                            <%--<option>会议室2</option>--%>
				                                            <%--<option>会议室2</option>--%>
				                                            <%--<option>会议室3</option>--%>
				                                            <%--<option>会议室1</option>--%>
				                                            <%--<option>会议室2</option>--%>
				                                            <%--<option>会议室end</option>--%>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="form-group clearfix">
                                                    <label class="col-lg-10 control-label " for=""> <a href="/meeting/toRoomShowJsp" target="_blank"><span class="text-danger">*</span> 不知道有什么会议室？</a></label>
                                                </div>
                                                <div style="height:200px;"></div>
                                            </section>
                                            <h3>填写预约时间</h3>
                                            <section>
                                            	<div class="row">
                                            		<div class="col-md-3">
                                            			<label>选择日期</label>
                                                        <div class="input-group" style="">
                                                            <div id="datepicker-inline"></div>
                                                            <input changeListener="true" type="hidden" class="dy_datepicker" name="">
                                                        </div><!-- input-group -->
                                            		</div>
                                            		<div class="col-md-9">
                                            			<div class="demo-box">
			                                                <h5 class="header-title m-t-0">你选择的日期</h5>
			                                                <p id="dy_step2_dateP" class="font-15 m-b-5 text-primary" style="font-weight: bold;">
			                                                    2018-7-2e
			                                                </p>

			                                                <div class="">
			                                                    <div class="form-group">
			                                                        <label>会议起始时间</label>
			                                                        <div class="input-group">
			                                                            <input changeListener="true" id="" type="text" class="dy_timepicker form-control">
			                                                            <span class="input-group-addon"><i class="mdi mdi-clock"></i></span>
			                                                        </div><!-- input-group -->
			                                                    </div>

			                                                    <div class="form-group">
			                                                        <label>会议结束时间</label>
			                                                        <div class="input-group">
			                                                            <input changeListener="true" id="" type="text" class="dy_timepicker form-control">
			                                                            <span class="input-group-addon"><i class="mdi mdi-clock"></i></span>
			                                                        </div><!-- input-group -->
			                                                    </div>
			                                                </div>
			                                                <div>
			                                                	<div class="form-group col-md-6 m-l-0 p-l-0">
			                                                    	<a href="javascript:void(0);" role="button" id="dy_step2_checkBtn" checkOk="false" class="btn btn-primary waves-effect waves-light"><i class="fa fa-spin fa-spinner" style="width: auto;height: auto; line-height: 1px; margin-right: 10px;"></i> <span>检查预约时间是否冲突</span></a>
			                                                    </div>
			                                                </div>
			                                            </div>
                                            		</div>
                                            	</div>
                                                

                                            </section>
                                            <h3>选择参会人员</h3>
                                            <section>
                                                <div class="form-group clearfix">
                                                    <label class="col-lg-2 control-label " for="">选择参会人员 </label>
                                                    <div class="col-lg-10" >
                                                        <select style="" multiple class="form-control required dy_selectpicker" data-live-search="true" data-selected-text-format="count > 6">
                                                        	<c:forEach items="${requestScope.listUser}" var="item">
                                                                <option value="<c:out value="${item.no}"/>"><c:out value="${item.name}"/>(<c:out value="${item.no}"/>)</option>
                                                            </c:forEach>
                                                            <%--<option value="1">员工1</option>--%>
				                                            <%--<option value="2">员工2</option>--%>
				                                            <%--<option value="3">员工3</option>--%>
				                                            <%--<option value="4">员工3</option>--%>
				                                            <%--<option value="5">员工3</option>--%>
				                                            <%--<option value="6">员工3</option>--%>
				                                            <%--<option value="7">员工3</option>--%>
				                                            <%--<option value="8">员工3</option>--%>
				                                            <%--<option value="9">员工3</option>--%>
				                                            <%--<option value="0">员工3</option>--%>
				                                            <%--<option value="">员工3</option>--%>
				                                            <%--<option value="">员工3</option>--%>
				                                            <%--<option value="">员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工3</option>--%>
				                                            <%--<option>员工END</option>--%>
                                                        </select>
                                                    </div>
                                                </div>
                                                <div style="width:100%;height:220px;">
                                                </div>
                                            </section>
                                            <h3>提交</h3>
                                            <section>
                                                <div class="form-group clearfix">
                                                    <div id="dy_step4_info" class="col-lg-12">
                                                        <p class="text-success"><i class="fa fa-check-square-o"></i> <span>完成第一步</span></p>
                                                        <p class="text-danger"><i class="fa fa-minus-square"></i> <span>第二步有误，请审核填写内容</span></p>
                                                        <p class="text-warning"><i class="fa fa-minus-square"></i> <span>第三步未选择参会人员</span></p>
                                                    </div>
                                                </div>
                                                <div class="form-group clearfix">
                                                	<div class="col-lg-12">
                                                		<div class="checkbox checkbox-primary">
                                                            <input id="dy_checkbox_alert" type="checkbox" checked>
                                                            <label for="dy_checkbox_alert" class="text-primary">
                                                                预约成功后，立即通知一次全体参会人员.
                                                            </label>
                                                        </div>
                                                	</div>
                                                </div>
                                            </section>
                                        </div>
                                    </form>

                            	</div>
                            </div>
                        </div>

                    </div> <!-- container -->

                </div> <!-- content -->

                <footer class="footer text-right">
                    2018 © MONDA GROUP.
                </footer>

            </div>


            <!-- ============================================================== -->
            <!-- End Right content here -->
            <!-- ============================================================== -->


            <!-- Right Sidebar -->
            <div class="side-bar right-bar">
                <a href="javascript:void(0);" class="right-bar-toggle">
                    <i class="mdi mdi-close-circle-outline"></i>
                </a>
                <h4 class="">Settings</h4>
                <div class="setting-list nicescroll">
                    <div class="row m-t-20">
                        <div class="col-xs-8">
                            <h5 class="m-0">Notifications</h5>
                            <p class="text-muted m-b-0"><small>Do you need them?</small></p>
                        </div>
                        <div class="col-xs-4 text-right">
                            <input type="checkbox" checked data-plugin="switchery" data-color="#7fc1fc" data-size="small"/>
                        </div>
                    </div>

                    <div class="row m-t-20">
                        <div class="col-xs-8">
                            <h5 class="m-0">API Access</h5>
                            <p class="m-b-0 text-muted"><small>Enable/Disable access</small></p>
                        </div>
                        <div class="col-xs-4 text-right">
                            <input type="checkbox" checked data-plugin="switchery" data-color="#7fc1fc" data-size="small"/>
                        </div>
                    </div>

                    <div class="row m-t-20">
                        <div class="col-xs-8">
                            <h5 class="m-0">Auto Updates</h5>
                            <p class="m-b-0 text-muted"><small>Keep up to date</small></p>
                        </div>
                        <div class="col-xs-4 text-right">
                            <input type="checkbox" checked data-plugin="switchery" data-color="#7fc1fc" data-size="small"/>
                        </div>
                    </div>

                    <div class="row m-t-20">
                        <div class="col-xs-8">
                            <h5 class="m-0">Online Status</h5>
                            <p class="m-b-0 text-muted"><small>Show your status to all</small></p>
                        </div>
                        <div class="col-xs-4 text-right">
                            <input type="checkbox" checked data-plugin="switchery" data-color="#7fc1fc" data-size="small"/>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /Right-bar -->

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

        <!-- isotope filter plugin -->
        <script type="text/javascript" src="/static/template/plugins/isotope/js/isotope.pkgd.min.js"></script>

        <!-- Magnific popup -->
        <script type="text/javascript" src="/static/template/plugins/magnific-popup/js/jquery.magnific-popup.min.js"></script>

        <!-- Sweet-Alert  -->
		<script src="/static/template/plugins/bootstrap-sweetalert/sweet-alert.min.js"></script>

		<script src="/static/template/plugins/ion-rangeslider/ion.rangeSlider.min.js"></script>

        <!-- App js -->
        <script src="/static/template/assets/js/jquery.core.js"></script>
        <script src="/static/template/assets/js/jquery.app.js"></script>

        <!-- Tooltipster js -->
        <script src="/static/template/plugins/tooltipster/tooltipster.bundle.min.js"></script>

        <!--Form Wizard-->
        <script src="/static/template/plugins/jquery.steps/js/jquery.steps.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="/static/template/plugins/jquery-validation/js/jquery.validate.min.js"></script>

        <!-- 时间控件 -->
        <script src="/static/template/plugins/timepicker/bootstrap-timepicker.js"></script>
		<script src="/static/template/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>

        <!-- bootstrap-select -->
        <script src="/static/template/plugins/bootstrap-select/js/bootstrap-select.min.js" type="text/javascript"></script>

        <!-- 引入header.js公共JS -->
        <script type="text/javascript" src="/static/jsp_js/header.js"></script>

        <script type="text/javascript" src="/static/jsp_js/orderMeeting.js"></script>

    </body>
</html>
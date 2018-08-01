<%--
  User: HuangDongYang<huangdy@pvc123.com>
  Date: 2018/6/30
  Time: 18:24
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
        <title>公司会议室一览</title>

        <!--venobox lightbox-->
        <link rel="stylesheet" href="/static/template/plugins/magnific-popup/css/magnific-popup.css"/>

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
            .dy_height8em{
                height:8em;
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
			
			.popover {
			    width:25em;
			    max-width:25em;
                /*max-height:20em;*/
                /*overflow: scroll;*/
			}
			.popover .popover-title{
				color: #0c0c0c
			}
            .popover .popover-content{
                overflow-y:scroll;
                max-height:20em;
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
                                    <h4 class="page-title"><i class="fa fa-comment-o"></i> 公司会议室 </h4>
                                    
                                    <div class="clearfix"></div>
                                </div>
							</div>
						</div>
                        <!-- end row -->


                        <!-- SECTION FILTER
                        ================================================== -->
                        <div class="row m-b-10">
                            <div class="col-lg-12 col-md-12 col-sm-12 ">
                                <div class="btn-group" style="width:100%;">
			                        <button type="button" title="筛选条件" class="btn btn-primary waves-light" disabled>
		                               <i class="fa fa-arrow-circle-right"></i>
		                            </button>
		                    		<input id="dy_searchName" type="text" placeholder="搜索会议室名" class="btn btn-default dy_enterListener "/>
		                    		<input id="dy_searchRemark" type="text" placeholder="搜索会议室简述" class="btn btn-default dy_enterListener width30"/>
		                    		<input id="dy_searchSize" type="number" placeholder="需要容纳的人数" class="btn btn-default dy_enterListener "/>
		                    	</div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-lg-12 col-md-12 col-sm-12 ">
                                <div class="portfolioFilter">
                                    <a href="#" data-filter="*" class="current waves-effect waves-success">All</a>
                                    <!-- <a href="#" data-filter=".natural" class="waves-effect waves-success">Natural</a>
                                    <a href="#" data-filter=".creative" class="waves-effect waves-success">Creative</a>
                                    <a href="#" data-filter=".personal" class="waves-effect waves-success">Personal</a>
                                    <a href="#" data-filter=".photography" class="waves-effect waves-success">Photography</a>
 -->
                                    <a href="javascript:void(0);" data-filter=".dy_unUse" class="waves-effect waves-success">空闲中</a>
                                    <a href="javascript:void(0);" data-filter=".dy_ordering" class="waves-effect waves-success">已预约</a>
                                    <a href="javascript:void(0);" data-filter=".dy_sizeLess10" class="waves-effect waves-success">容纳人数 &lt; 10</a>
                                    <a href="javascript:void(0);" data-filter=".dy_sizeLess20" class="waves-effect waves-success">容纳人数 &lt; 20</a>
                                    <a href="javascript:void(0);" data-filter=".dy_sizeGreater50" class="waves-effect waves-success">容纳人数 &gt; 50</a>

                                </div>
                            </div>
                        </div>

                        <div class="row" style="display: none;">
                            <form id="dy_orderForm" method="post" action="/orderMeeting/toOrder" >
                                <input type="hidden" name="roomId"/>
                                <button type="submit"></button>
                            </form>
                        </div>

                        <div class="row port">
                            <div class="portfolioContainer" id="dy_meetingroom_container">

                                <!-- <div class="dy_item col-sm-6 col-md-3 natural personal dy_ordering m-b-10">
                                    <div class="thumb">
                                        <a href="/jettyPath/meetingroom-default.jpg" class="image-popup" title="Screenshot-1">
                                            <img src="/jettyPath/meetingroom-default.jpg" class="thumb-img" alt="work-thumbnail">
                                        </a>
                                        <div class="gal-detail">
                                            <h4>洽谈室1</h4>
                                            <p class="text-muted dy_itemSize">
                                                <i class="mdi mdi-account-settings"></i>&nbsp;5人
                                            </p>
                                            <div style="height:8em;">
	                                            <p class="text-muted dy_showP">
	                                                <i class="mdi mdi-presentation"></i>&nbsp;一张圆桌，五张椅子，一台投影仪，一个白板，有空调有饮水机
	                                            </p>
                                        	</div>
                                            <p roomId=''>
                                                <a href="javascript:void(0);" class="btn btn-primary waves-effect waves-light" role="button"><i class=" fa-spin fa fa-hourglass-o" style="width: auto;height: auto; line-height: 1px; margin-right: 10px;"></i>2人已预约</a>
                                                <a href="javascript:void(0);" class="btn btn-default waves-effect waves-light m-l-5" role="button">我要预约</a>
                                            </p>
                                        </div>
                                    </div>
                                </div> -->

                               

                            </div><!-- end portfoliocontainer-->
                        </div> <!-- End row -->
                        <div style="margin-top:10em;"></div>

                    </div> <!-- container -->

                </div> <!-- content -->

                <footer class="footer text-right">
                    2018 © MONDA GROUP.
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

        <!-- 引入header.js公共JS -->
        <script type="text/javascript" src="/static/jsp_js/header.js"></script>

        <script type="text/javascript" src="/static/jsp_js/meetingRoomShow.js"></script>

    </body>
</html>
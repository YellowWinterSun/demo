<%--
  User: HuangDongYang<huangdy@pvc123.com>
  Date: 2018/6/28
  Time: 11:42
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
        <link rel="shortcut icon" href="assets/images/favicon.ico">
        <!-- App title -->
        <title>关于开发者</title>

        <!-- App css -->
        <link href="/static/template/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="/static/template/assets/css/core.css" rel="stylesheet" type="text/css" />
        <link href="/static/template/assets/css/components.css" rel="stylesheet" type="text/css" />
        <link href="/static/template/assets/css/icons.css" rel="stylesheet" type="text/css" />
        <link href="/static/template/assets/css/pages.css" rel="stylesheet" type="text/css" />
        <link href="/static/template/assets/css/menu.css" rel="stylesheet" type="text/css" />
        <link href="/static/template/assets/css/responsive.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="/static/template/plugins/switchery/switchery.min.css">

        <!-- HTML5 Shiv and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->

        <script src="/static/template/assets/js/modernizr.min.js"></script>

    </head>


    <body class="fixed-left">

        <!-- Begin page -->
        <div id="wrapper">

            <%@ include file="header/header.jsp" %>

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
                                    <h4 class="page-title">About Us </h4>
                                    
                                    <div class="clearfix"></div>
                                </div>
							</div>
						</div>
                        <!-- end row -->

                        <div class="row">
                            <div class="col-md-10 col-md-offset-1">
                                <div class="p-20">
                                    <div class="text-center">
                                        <h3 class="m-b-30 m-t-20">结语</h3>
                                        <p>
                                            项目在原需求的基础上，按照自己的想法多加了一点功能。最终在规定开发时间内，完成项目的开发工作。并且项目能够完成所有的需求。但项目仍有很多改进的空间
                                        </p>
                                        <p>
                                            如前端方面的UI设计，能够更加合理的布局以及色调的搭配，来让使用者更加舒服，方便的来操作本系统。
                                        </p>
                                        <p>
                                            如业务逻辑方便，还需要加强。如何让整个系统业务流程更加合理，舒服，方便值得我后期去思考。在自行测试的过程中，发现了一些比较卡手的操作，都针对的按自己想法做了修改。但是在实际项目中，会时刻保持跟需求方沟通。由于这是考核项目，题目要求也提到考察开发者的想法，因此我在本次考核中，理解业务后就着手自己的想法进行开发 。
                                        </p>
                                        <p>
                                            如后台方面，在开发的时候已经非常注意代码的质量，但仍有很大的改进空间，这些改进都能提升后台性能。
                                        </p>
                                        <p>
                                            数据库方面，在初设计表的时候就考虑到很多东西。索引是前期设计的时候创建的，但是会根据后期实际项目投入使用的时候，来进行索引的更改。在数据类型设计方面，尽量节省空间。在使用UNDESIGNED的数据类型时，发现在JAVA端映射的时候，会损失掉那部分因为无符号而扩增的整数范围，这个在后期会自行思考，并请问前辈。
                                        </p>
                                    </div>

                                    <div class="m-t-50 p-t-10">
                                        <h4 class="text-uppercase">系统采用的技术</h4>
                                        <div class="border m-b-30"></div>

                                        <div class="row">
                                            <div class="col-sm-4">
                                                <div class="about-features-box text-center">
                                                    <div class="feature-icon">
                                                        <i class="ion-coffee"></i>
                                                    </div>
                                                    <h4>Maven</h4>

                                                    <p class="text-muted">软件项目管理工具，在本项目中用到了该技术的项目构建，依赖包管理和打包</p>
                                                </div>
                                            </div>

                                            <div class="col-sm-4">
                                                <div class="about-features-box text-center">
                                                    <div class="feature-icon">
                                                        <i class="ion-images"></i>
                                                    </div>
                                                    <h4>Spring SpringMVC</h4>

                                                    <p class="text-muted">Spring是当今Web项目主流成熟的框架，SpringMVC更是代替了原来的Servlet，Struts.两者搭配使用，能够 进行很完美的融合</p>
                                                </div>
                                            </div>

                                            <div class="col-sm-4">
                                                <div class="about-features-box text-center">
                                                    <div class="feature-icon">
                                                        <i class="ion-settings"></i>
                                                    </div>
                                                    <h4>MyBatis MySQL</h4>

                                                    <p class="text-muted">MyBatis是我最喜欢的持久层框架，原因有两点：第一，它能优化SQL；第二，逆向工程工具舒服；虽然它相比Hibernate，经常写SQL会很麻烦，降低开发效率，但我觉得就我而言，我用MyBatis的开发效率，除了前期准备工作会比较漫长外，在后期开发的时候基本是无感的，而且能优化SQL和动态SQL才是重中之重。</p>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- end row -->

                                        <div class="row">
                                            <div class="col-sm-4">
                                                <div class="about-features-box text-center">
                                                    <div class="feature-icon">
                                                        <i class="ion-paper-airplane"></i>
                                                    </div>
                                                    <h4>Jetty</h4>

                                                    <p class="text-muted">以前都是用Tomcat，对Jetty没啥了解</p>
                                                </div>
                                            </div>

                                            <div class="col-sm-4">
                                                <div class="about-features-box text-center">
                                                    <div class="feature-icon">
                                                        <i class="ion-map"></i>
                                                    </div>
                                                    <h4>BootStrap全家桶</h4>

                                                    <p class="text-muted">作为一个Java后台开发工程师，目前又经常做Web开发，前端方面选取了BootStrap作为我的武器。因此我的Web系统，都是一股浓浓的Bootstrap味道= =。除此之外，还学了Bootstrap-select,Bootstrap-table,Bootstrap-table-editable,Bootstrap-select2,Bootstrap-fileInput,Bootstrap-tableExport ....etc.</p>
                                                </div>
                                            </div>

                                            <div class="col-sm-4">
                                                <div class="about-features-box text-center">
                                                    <div class="feature-icon">
                                                        <i class="ion-cube"></i>
                                                    </div>
                                                    <h4>JQuery</h4>

                                                    <p class="text-muted">感谢学校最牛逼的前端同学，教了我半年前端.</p>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- end row -->
                                    </div>
                                    <!-- end services -->


                                    <div class="m-t-50 p-t-10">
                                        <h4 class="text-uppercase">开发者</h4>
                                        <div class="border m-b-30"></div>

                                        <div class="row about-team text-center">

                                            <!-- team-member -->
                                            <div class="col-sm-4 col-sm-offset-4">
                                                <div class="about-team-member">
                                                    <img src="/static/image/userIcon.jpg" alt="team-member" class="img-responsive img-circle">
                                                    <h4>Huangdy</h4>
                                                    <p>Java</p>
                                                </div>
                                            </div>

                                        </div>
                                        <!-- end row -->

                                    </div>
                                    <!-- end services -->


                                </div> <!-- end p-20 -->
                            </div> <!-- end col -->
                        </div>
                        <!-- end row -->


                    </div> <!-- container -->

                </div> <!-- content -->

                <footer class="footer text-right">
                    2016 © Zircos.
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

        <!-- App js -->
        <script src="/static/template/assets/js/jquery.core.js"></script>
        <script src="/static/template/assets/js/jquery.app.js"></script>

        <!-- 引入header.js公共JS -->
        <script type="text/javascript" src="/static/jsp_js/header.js"></script>
    </body>
</html>
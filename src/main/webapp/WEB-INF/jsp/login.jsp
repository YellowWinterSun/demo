<%--
  User: HuangDongYang<huangdy@pvc123.com>
  Date: 2018/6/15
  Time: 9:37
  登录页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html class="account-pages-bg">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="A fully featured admin theme which can be used to build CRM, CMS, etc.">
        <meta name="author" content="Coderthemes">

        <!-- App favicon -->
        <link rel="shortcut icon" href="static/template/assets/images/favicon.ico">
        <!-- App title -->
        <title>登录界面</title>

        <!-- Sweet Alert -->
        <link href="static/template/plugins/bootstrap-sweetalert/sweet-alert.css" rel="stylesheet" type="text/css">

        <!-- App css -->
        <link href="static/template/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="static/template/assets/css/core.css" rel="stylesheet" type="text/css" />
        <link href="static/template/assets/css/components.css" rel="stylesheet" type="text/css" />
        <link href="static/template/assets/css/icons.css" rel="stylesheet" type="text/css" />
        <link href="static/template/assets/css/pages.css" rel="stylesheet" type="text/css" />
        <link href="static/template/assets/css/menu.css" rel="stylesheet" type="text/css" />
        <link href="static/template/assets/css/responsive.css" rel="stylesheet" type="text/css" />

        <!-- HTML5 Shiv and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->

        <script src="static/template/assets/js/modernizr.min.js"></script>

    </head>


    <body class="bg-transparent">

        <!-- HOME -->
        <section>
            <div class="container-alt">
                <div class="row">
                    <div class="col-sm-12">

                        <div class="wrapper-page">

                            <div class="m-t-40 account-pages">
                                <div class="text-center account-logo-box">
                                    <h2 class="text-uppercase">
                                        <a href="javascript:void(0);" class="text-success">
                                            <!-- <span><img src="static/image/monda_logo.png" alt="" height="36"></span> -->
                                            <span style="color:white;">绩效考核与会议室预约系统</span>
                                        </a>
                                    </h2>
                                    <!--<h4 class="text-uppercase font-bold m-b-0">Sign In</h4>-->
                                </div>
                                <div class="account-content" style="background-color:white;">
                                    <form class="form-horizontal" action="#" id="login_form">

                                        <div class="form-group ">
                                            <div class="col-xs-12">
                                                <input name="phone" class="form-control" tip="请填写手机号码" type="text" required="" placeholder="手机号码">
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <input name="password" class="form-control" tip="请输入密码" type="password" required="" placeholder="密码" >
                                            </div>
                                        </div>

                                        <div class="form-group text-center m-t-30">
                                            <div class="col-sm-12">
                                                <a href="javascript:void(0);" class="text-muted"><i class="fa fa-lock m-r-5"></i> 忘记密码请联系管理员</a>
                                            </div>
                                        </div>

                                        <div class="form-group account-btn text-center m-t-10">
                                            <div class="col-xs-12">
                                                <button id="dy_loginBtn" class="btn w-md btn-bordered btn-danger waves-effect waves-light" type="button">Log In</button>
                                            </div>
                                        </div>

                                    </form>

                                    <div class="clearfix"></div>

                                </div>
                            </div>
                            <!-- end card-box-->

                        </div>
                        <!-- end wrapper -->

                    </div>
                </div>
            </div>
          </section>
          <!-- END HOME -->

        <script>
            var resizefunc = [];
        </script>

        <!-- jQuery  -->
        <script src="static/template/assets/js/jquery.min.js"></script>
        <script src="static/template/assets/js/bootstrap.min.js"></script>
        <script src="static/template/assets/js/detect.js"></script>
        <script src="static/template/assets/js/fastclick.js"></script>
        <script src="static/template/assets/js/jquery.blockUI.js"></script>
        <script src="static/template/assets/js/waves.js"></script>
        <script src="static/template/assets/js/jquery.slimscroll.js"></script>
        <script src="static/template/assets/js/jquery.scrollTo.min.js"></script>

        <script src="static/template/plugins/switchery/switchery.min.js"></script>

        <!-- Sweet-Alert  -->
        <script src="static/template/plugins/bootstrap-sweetalert/sweet-alert.min.js"></script>
        <script src="static/template/assets/pages/jquery.sweet-alert.init.js"></script>

        <!-- App js -->
        <script src="static/template/assets/js/jquery.core.js"></script>
        <script src="static/template/assets/js/jquery.app.js"></script>

        <!-- this jsp js -->
        <script type="text/javascript" src="static/jsp_js/login.js"></script>

    </body>
</html>
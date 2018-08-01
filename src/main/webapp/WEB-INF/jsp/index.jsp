<%--
  User: HuangDongYang<huangdy@pvc123.com>
  Date: 2018/6/15
  Time: 13:31
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
        <title>系统主页</title>

        <!-- date range picker -->
        <link href="/static/template/plugins/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet">

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

            <!-- jstl的路径必须以文件目录为基准，不是以Web环境路径为基准 -->
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
                                    <h4 class="page-title"> 系统主页 </h4>
                                    
                                    <div class="clearfix"></div>
                                </div>
                            </div>
                        </div>
                        <!-- end row -->

                        <div class="row">
                            <div class="col-md-10 col-md-offset-1">
                                <div class="p-20">
                                    <div class="text-center">
                                        <h2 class="m-b-30 m-t-20">欢迎来到绩效考核与会议室预约系统</h2>
                                    </div>
                                    <div class="text-left">
                                        <h4 class="m-b-10">项目:绩效考核和会议室预约系统</h4>
                                        <h4 class="m-b-5">项目背景:</h4>
                                        <h4 class="m-b-5">痛点一:</h4>
                                        <p style="text-indent: 2rem;" class="m-b-20">
                                            我们每个月都要交考核表，现在我们都是通过线下考核，考核人员把自评表填好之后发给直属上司，直属上司完成上级考核分数和评语之后把所有的考核表打包发给部门总监；总监完成“部门负责人”考核项之后统计总分数发送给HR部门。这样不仅效率低下，而且也不利于存档和查找，员工想看看上级对他的考评分数和考核评语都非常麻烦。
                                        </p>

                                        <h4 class="m-b-5">痛点二:</h4>
                                        <p style="text-indent: 2rem;" class="m-b-20">
                                            我们的会议室比较多，为了保证会议室的有序使用，现在员工用会议室需要预约。现在公司采用的是线下登记预约，非常不方便。第一，每次要预约需要跑到前台去预约，有时候跑过去还不一定能够预约到，这很浪费时间。第二、由于是线下人工预约，有时候由于工作人员的疏忽，经常导致多人预约的时间重合，耽误工作。
                                        </p>

                                        <h4 class="m-b-5">解决方案:</h4>
                                        <p style="text-indent: 2rem;" class="m-b-20">
                                            设计，开发一套解决绩效考核，会议室预约问题的系统。实现无纸化办公。
                                        </p>

                                        <h4 class="m-b-10">需求简要设计:</h4>
                                        <h4 class="m-b-5">一、 公共模块:</h4>
                                        <p style="text-indent: 2rem;">
                                            <ol>
                                                <li>
                                                    用户系统，需要实现用户，角色，权限自定义功能，权限的控制精确到每个操作。注册用户必须填写，姓名，手机号码，部门，工号，企业邮箱，以手机号码作为登录名。
                                                </li>
                                                <li>
                                                    用户和角色是多对多的对应关系（见ER图），一个用户可以有多重角色，他的权限为所有角色权限合并叠加。
                                                </li>
                                                <li>
                                                    部门管理，岗位管理，实现部门，岗位的增删查改
                                                </li>
                                            </ol>
                                        </p>

                                        <h4 class="m-b-5 m-t-20">二、 绩效考核:需求实现整个考核流程</h4>
                                        <p style="text-indent: 2rem;">
                                            <ol>
                                                <li>
                                                    实现考核模板的添加，编辑，考核选项的添加，修改，删除。考核模板见附件的excel文档
                                                </li>
                                                <li>
                                                    提交绩效考核，员工在每个月月底可以提交当月的绩效考核表，填好自评分。根据每个人的角色自动加载他所绑定的考核模板
                                                </li>
                                                <li>
                                                    直属上司评分，员工的直属上司可以看到他的所有下属提交的绩效考核，然后对每个人的绩效考核进行上级评分，并添加评语（选填）。同时上级可以看到下级所有的绩效考核历史存档，并提供相应的查询功能呢（提供姓名，月份等筛选条件查询）
                                                </li>
                                                <li>
                                                    直属上级评分之后，考核表自动进入部门总监考评流程，直属上级没有评分的考核表不得出现在部门总监的考核列表中。部门总监可以申请特殊加减分，并提交申请理由，在部门总监完成考核评分提交之后，整个考核表将不允许再修改，同时以邮件的形式发送到被考核人的企业邮箱（暂时先用邮箱，可以先提供短信接口，随时替换）。查询权限同第3条。
                                                </li>
                                                <li>
                                                    部门总监提交之后HR部门通过对特殊加减分的审核，可以对考核的总分进行修改。HR部门审核之后，这个考核分数就不允许再修改，将作为绩效考核的最终分数。
                                                </li>
                                                <li>
                                                    考核总分的计算公式为：<b style="color:red;">（自评 * 20% + 上级评分 * 80% ）+ 特殊加减分 </b>
                                                </li>
                                                <li>
                                                    所有的绩效考核存档需要提供打印功能（有兴趣的同学可以试着做导出excel和导出pdf或者图片的功能）
                                                </li>
                                                <li>
                                                    考核表中所有的签名使用系统自动填充（有兴趣的同学可以提供个性化签名实现，即让用户自己上传自己设计的签名图片，以后所有的签名直接用图片签名替换）
                                                </li>
                                            </ol>
                                        </p>

                                        <h4 class="m-b-5 m-t-20">三、 会议室预约:实现会议室预约到使用的整个流程</h4>
                                        <p style="text-indent: 2rem;">
                                            <ol>
                                                <li>
                                                    会议室的CRUD功能。
                                                </li>
                                                <li>
                                                    新增预约，预约会议室要做好逻辑判断，解决预约时间重叠的问题。根据我们目前的实际情况，预约时间精确到分钟，不能跨天，比如不能预约从今天下午2点，到明天上午10点。
                                                </li>
                                                <li>
                                                    预约的时候必须选中参会人员。预约成功之后需要给每个参会人员发邮件（同时需要提供短信接口，以便随时替换）确定会议的时间，地点，参会人员。
                                                </li>
                                                <li>
                                                    分别在会议开始前1个小时，30分钟的时候发送邮件提醒所有参会人员准时参与会议。
                                                </li>
                                                <li>
                                                    在会议时间到期之后自动将预约的状态设置为“已过期”
                                                </li>
                                            </ol>
                                        </p>

                                        <h4 class="m-b-5 m-t-25">备注：</h4>
                                        <p style="text-indent: 2rem;">
                                            <ol>
                                                <li>
                                                   以上只是简要需求，具体细节部分和原型都没有，考大家自主发挥，主要是考察大家的基础开发能力，以及产品开发应变能力。没有详细需求，细节部分靠大家脑补，没有特殊要求，大家可以使用各种方式去实现，开发框架使用SSM（见附件demo.zip），前端部分自己想办法解决，可以寻求前端人员的协助。
                                                </li>
                                                <li>
                                                    <b style="color:red;"></b>
                                                </li>
                                            </ol>
                                        </p>
                                        <p class="m-b-5">
                                            另外，此次考核项目要严格按照 《Java开发手册》 的规范去进行开发。
                                        </p>
                                        <p class="m-b-5">
                                            
                                        </p>
                                    </div>
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
        <script src="/static/template/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>


        <!-- Dashboard init -->
        <script src="/static/template/assets/pages/jquery.dashboard_2.js"></script>

        <!-- App js -->
        <script src="/static/template/assets/js/jquery.core.js"></script>
        <script src="/static/template/assets/js/jquery.app.js"></script>

        <!-- 引入header.js公共JS -->
        <script type="text/javascript" src="/static/jsp_js/header.js"></script>

    </body>
</html>

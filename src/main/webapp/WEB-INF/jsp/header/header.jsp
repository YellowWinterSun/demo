<%--
  User: HuangDongYang<huangdy@pvc123.com>
  Date: 2018/6/15
  Time: 15:01
  网站页面的公共部分.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!-- head-modal -->

    <!-- 增加user，modal -->
    <div id="dy_header_userInfoModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;" >
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title"></h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="">
                                <ul class="nav nav-tabs">
                                    <li class="active">
                                        <a href="#dy_header_tab1_pane1" data-toggle="tab" aria-expanded="true">
                                            <span class="visible-xs"><i class="fa fa-home"></i></span>
                                            <span class="hidden-xs">我的信息</span>
                                        </a>
                                    </li>
                                    <li class="">
                                        <a href="#dy_header_tab1_pane2" data-toggle="tab" aria-expanded="false">
                                            <span class="visible-xs"><i class="fa fa-user"></i></span>
                                            <span class="hidden-xs">直属上司</span>
                                        </a>
                                    </li>
                                    <li class="">
                                        <a href="#dy_header_tab1_pane3" data-toggle="tab" aria-expanded="false">
                                            <span class="visible-xs"><i class="fa fa-envelope-o"></i></span>
                                            <span class="hidden-xs">部门总监</span>
                                        </a>
                                    </li>
                                </ul>
                                <div class="tab-content">
                                    <div class="tab-pane active" id="dy_header_tab1_pane1">
                                        <div class="text-center card-box">
                                            <div class="member-card">
                                                <div class="thumb-xl member-thumb m-b-10 center-block">
                                                    <img src="/static/image/userIcon.jpg" class="img-circle img-thumbnail" alt="profile-image">
                                                    <i class="mdi mdi-star-circle member-star text-muted" title="unverified user"></i>
                                                </div>

                                                <div name="info-title" class="">
                                                    <h4 class="m-b-5">暂无</h4>
                                                    <p class="text-muted"> </p>
                                                </div>
                                                <hr>
                                                <div name="info-body" class="text-left">
                                                    <p class="text-muted font-13"><strong>工号 :</strong> <span class="m-l-15"></span></p>

                                                    <p class="text-muted font-13"><strong>Phone :</strong><span class="m-l-15"></span></p>

                                                    <p class="text-muted font-13"><strong>Email :</strong> <span class="m-l-15"></span></p>

                                                    <p class="text-muted font-13"><strong>入职日期 :</strong> <span class="m-l-15"></span></p>
                                                </div>
                                                <hr>
                                                <div class="input-group">
                                                    <button type="button" title="重置密码" class="btn waves-effect waves-light btn-info"><i class="mdi mdi-key"></i> 重置密码</button>
                                                    <div style="display:none;">
                                                        <input type="password" name="resetPwd1" style="width:30%;" class="btn btn-default" placeholder="重置密码">
                                                        <input type="password" name="resetPwd2" style="width:30%;" class="btn btn-default" placeholder="再次确认密码">
                                                        <button type="button" class="btn waves-effect waves-light btn-danger"><i class="mdi mdi-check-all"></i></button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tab-pane" id="dy_header_tab1_pane2">
                                        <div class="text-center card-box">
                                            <div class="member-card">
                                                <div class="thumb-xl member-thumb m-b-10 center-block">
                                                    <img src="/static/image/userIcon.jpg" class="img-circle img-thumbnail" alt="profile-image">
                                                    <i class="mdi mdi-star-circle member-star text-muted" title="unverified user"></i>
                                                </div>

                                                <div name="info-title" class="">
                                                    <h4 class="m-b-5">暂无</h4>
                                                    <p class="text-muted">  </p>
                                                </div>
                                                <hr>
                                                <div name="info-body" class="text-left">
                                                    <p class="text-muted font-13"><strong>工号 :</strong> <span class="m-l-15"></span></p>

                                                    <p class="text-muted font-13"><strong>Phone :</strong><span class="m-l-15"></span></p>

                                                    <p class="text-muted font-13"><strong>Email :</strong> <span class="m-l-15"></span></p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tab-pane" id="dy_header_tab1_pane3">
                                        <div class="text-center card-box">
                                            <div class="member-card">
                                                <div class="thumb-xl member-thumb m-b-10 center-block">
                                                    <img src="/static/image/userIcon.jpg" class="img-circle img-thumbnail" alt="profile-image">
                                                    <i class="mdi mdi-star-circle member-star text-muted" title="unverified user"></i>
                                                </div>

                                                <div name="info-title" class="">
                                                    <h4 class="m-b-5">暂无</h4>
                                                    <p class="text-muted"></p>
                                                </div>
                                                <hr>
                                                <div name="info-body" class="text-left">
                                                    <p class="text-muted font-13"><strong>工号 :</strong> <span class="m-l-15"></span></p>

                                                    <p class="text-muted font-13"><strong>Phone :</strong><span class="m-l-15"></span></p>

                                                    <p class="text-muted font-13"><strong>Email :</strong> <span class="m-l-15"></span></p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                </div>
            </div>
        </div>
    </div><!-- /.modal -->

<!-- end of modal -->

<!-- Top Bar Start -->
<div class="topbar">

    <!-- LOGO -->
    <div class="topbar-left">
        <!-- <a href="/index" class="logo"><span>盟大<span>集团</span></span><i class="mdi mdi-layers"></i></a> -->
        <!-- Image logo -->
        <a href="/index" class="logo">
                        <span>
                            <img src="/static/image/monda_logo.png" alt="" height="40">
                        </span>
            <i>
                <img src="/static/template/assets/images/logo_sm.png" alt="" height="28">
            </i>
        </a>
    </div>

    <!-- Button mobile view to collapse sidebar menu -->
    <div class="navbar navbar-default" role="navigation">
        <div class="container">

            <!-- Navbar-left -->
            <ul class="nav navbar-nav navbar-left">
                <li>
                    <button class="button-menu-mobile open-left waves-effect">
                        <i class="mdi mdi-menu"></i>
                    </button>
                </li>
                <!-- <li class="hidden-xs">
                    <form role="search" class="app-search">
                        <input type="text" placeholder="Search..."
                               class="form-control">
                        <a href=""><i class="fa fa-search"></i></a>
                    </form>
                </li> -->
            </ul>

            <!-- Right(Notification) -->
            <ul class="nav navbar-nav navbar-right">
                <!-- 提醒 -->
                <li>
                    <a href="#" class="right-menu-item dropdown-toggle" data-toggle="dropdown">
                        <i class="mdi mdi-bell"></i>
                        <span class="badge up bg-success" id="dy_header_alertNum">0</span>
                    </a>

                    <ul id="dy_header_alertUl" class="dropdown-menu dropdown-menu-right arrow-dropdown-menu arrow-menu-right dropdown-lg user-list notify-list">
                        <li>
                            <h5>通知</h5>
                        </li>
                        <li style="display:none;">
                            <a href="/evaluation/examine" class="user-list-item" >
                                <div class="icon bg-primary">
                                    <i class="mdi mdi-account"></i>
                                </div>
                                <div class="user-desc">
                                    <span class="name">你可以进行绩效考核啦</span>
                                    <span class="time">进行本月绩效考核</span>
                                </div>
                            </a>
                        </li>
                        <li style="display:none;">
                            <a href="/evaluation/viewMyExamine" class="user-list-item">
                                <div class="icon bg-primary">
                                    <i class="fa fa-spin fa-circle-o-notch"></i>
                                </div>
                                <div class="user-desc">
                                    <span class="name" title="...">进行中...</span>
                                    <span class="time">本月绩效考核</span>
                                </div>
                            </a>
                        </li>
                        <li style="display:none;">
                            <a href="/evaluation/examine/pendingExamine" class="user-list-item">
                                <div class="icon bg-warning">
                                    <i class="mdi mdi-comment"></i>
                                </div>
                                <div class="user-desc">
                                    <span class="name" id="dy_header_alertPendingExamine" title="...">...</span>
                                    <span class="time">有未处理的绩效考核表</span>
                                </div>
                            </a>
                        </li>
                        <!-- 告知用户今天是否有会议要进行 -->
                        <li style="display:none;">
                            <a href="/orderMeeting/toMyOrderJsp" class="user-list-item">
                                <div class="icon bg-danger">
                                    <i class="fa-spin mdi mdi-clock-alert"></i>
                                </div>
                                <div class="user-desc" style="white-space: normal;">
                                    <span class="name">你有需要参加的会议，请留意</span>
                                    <span class="time">最近的一次会议时间为：16:30</span>
                                </div>
                            </a>
                        </li>
                        <li class="all-msgs text-center">
                            <p class="m-0"><a href="javascript:void(0);">查看完整的通知</a></p>
                        </li>
                    </ul>
                </li>

                <li>
                    <a href="javascript:void(0);" class="right-bar-toggle right-menu-item">
                        <i class="mdi mdi-settings"></i>
                    </a>
                </li>

                <li class="dropdown user-box">
                    <a href="" class="dropdown-toggle waves-effect user-link" data-toggle="dropdown" aria-expanded="true">
                        <!-- <img src="/static/template/assets/images/users/avatar-1.jpg" alt="user-img" class="img-circle user-img"> -->
                        <img src="/static/image/userIcon.jpg" alt="user-img" class="img-circle user-img">
                    </a>

                    <ul class="dropdown-menu dropdown-menu-right arrow-dropdown-menu arrow-menu-right user-list notify-list">
                        <li>
                            <h5 style="font-family: sans-serif;" class="text-purple"><b> ${user.name}&nbsp;${user.no} </b></h5>
                        </li>
                        <!-- <li><a href="javascript:void(0)"><i class="ti-user m-r-5"></i> 个人信息</a></li> -->
                        <li><a id="dy_header_openUserInfoBtn" href="javascript:void(0)"><i class="fa fa-spin fa-cog m-r-5"></i> 个人信息</a></li>
                        <li><a href="/logout" class="text-danger"><i class="ti-power-off m-r-5"></i> 注销</a></li>
                    </ul>
                </li>

            </ul> <!-- end navbar-right -->

        </div><!-- end container -->
    </div><!-- end navbar -->
</div>
<!-- Top Bar End -->

<!-- Right Sidebar -->
<div class="side-bar right-bar">
    <a href="javascript:void(0);" class="right-bar-toggle">
        <i class="mdi mdi-close-circle-outline"></i>
    </a>
    <h4 class=""><i class="mdi mdi-settings fa-spin"></i> 系统服务</h4>
    <div class="setting-list nicescroll">

        <div class="row m-t-20">
            <div class="col-xs-8">
                <h5 class="m-0">动态刷新通知栏</h5>
                <p class="m-b-0 text-muted"><small>启动该功能，可以动态获取系统通知。但会降低响应性能，如果发现网站卡顿，请关闭该功能</small></p>
            </div>
            <div class="col-xs-4 text-right">
                <input id="dy_header_autoUpdateBtn" type="checkbox" data-plugin="switchery" data-color="#7fc1fc" data-size="small"/>
            </div>
        </div>

        
    </div>
</div>
<!-- /Right-bar -->


<!-- ========== Left Sidebar Start ========== -->
<div class="left side-menu">
    <div class="sidebar-inner slimscrollleft">

        <!--- Sidemenu -->
        <div id="sidebar-menu">
            <ul>
                <li class="menu-title">功能菜单</li>

                <li class="has_sub" dy_url_subLi>
                    <a href="javascript:void(0);" class="waves-effect"><i class="mdi mdi-view-dashboard"></i><span> 系统管理 </span> <span class="menu-arrow"></span> </a>
                    <ul class="list-unstyled">
                        <li dy_urlValid dy_url="/sys/user"><a href="/sys/user" >用户管理</a></li>
                        <li dy_urlValid dy_url="/sys/job"><a href="/sys/job" >部门岗位管理</a></li>
                        <li dy_urlValid dy_url="/sys/role"><a href="/sys/role" >角色权限管理</a></li>
                        <li dy_urlValid dy_url="/sys/url"><a href="/sys/url" >权限控制</a></li>
                    </ul>
                </li>

                <li class="menu-title"></li>

                <li class="has_sub">
                    <a href="javascript:void(0);" class="waves-effect"><i class="mdi mdi-layers"></i><span> 绩效考核系统 </span> <span class="menu-arrow"></span></a>
                    <ul class="list-unstyled">
                        <li><a href="/evaluation/viewMyExamine">查看本人绩效考核情况</a></li>
                        <li><a href="/evaluation/viewMyExamine/viewMyChildExamine">查看下属绩效考核情况</a></li>
                        <li><a href="/evaluation/examine"><span style="display:none;" id="dy_header_newIcon2" class="label label-danger pull-right">New</span>进行本月绩效考核</a></li>
                        <li><a href="/evaluation/examine/pendingExamine"><span style="display:none;" id="dy_header_newIcon1" class="label label-danger pull-right">New</span><span>待处理的绩效考核表</span></a></li>
                        <li dy_urlValid dy_url="/evaluation/viewExamine"><a href="/evaluation/viewExamine">绩效考核汇总管理</a></li>

                        <li dy_urlValid dy_url="/evaluation/templateManage"><a href="/evaluation/templateManage">绩效考核模板管理</a></li>
                    </ul>
                </li>

                <li class="menu-title"></li>

                <li class="has_sub">
                    <a href="javascript:void(0)" class="waves-effect"><i class="mdi mdi-calendar"></i><span> 会议室预约 </span> <span class="menu-arrow"></span></a>
                    <ul class="list-unstyled">
                        <li><a href="/meeting/toRoomShowJsp">公司会议室一览</a></li>
                        <li><a href="/orderMeeting/toOrder">我要预约</a></li>
                        <li><a href="/orderMeeting/toMyOrderJsp"><span style="display:none;" id="dy_header_newIcon3" class="pull-right"><i class="text-warning fa-spin fa fa-history"></i></span>我的会议情况</a></li>
                        <li dy_urlValid dy_url="/orderMeeting/toOrderRecordJsp"><a href="/orderMeeting/toOrderRecordJsp">会议室预约记录</a></li>
                        <li dy_urlValid dy_url="/meeting/toRoomManageJsp"><a href="/meeting/toRoomManageJsp">会议室管理</a></li>
                    </ul>
                </li>

                <li class="menu-title"></li>

                <li class="menu-title">More</li>

                <li class="has_sub">
                    <a href="javascript:void(0);" class="waves-effect"><i class="mdi mdi-comment-text-outline"></i><span> 关于本系统 </span> <span class="menu-arrow"></span></a>
                    <ul class="list-unstyled">
                        <li ><a href="/dyTimeline">开发进行中</a></li>
                        <li ><a href="/aboutUsJsp">关于开发者</a></li>
                    </ul>
                </li>

            </ul>
        </div>
        <!-- Sidebar -->
        <div class="clearfix"></div>

        <div class="help-box">
            <h5 class="text-muted m-t-0">有问题吗 ?</h5>
            <p class=""><span class="text-custom">Email:</span> <br/> huangdy@pvc123.com</p>
            <p class="m-b-0"><span class="text-custom">Note:</span> <br/> 关于本系统有任何满意和不满意的地方，请联系我</p>
        </div>

    </div>
    <!-- Sidebar -left -->

</div>
<!-- Left Sidebar End -->


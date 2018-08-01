<%--
  User: HuangDongYang<huangdy@pvc123.com>
  Date: 2018/6/28
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>人力行政最终审核...</title>
    
    <!-- App css -->
    <link href="/static/template/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="/static/template/assets/css/icons.css" rel="stylesheet" type="text/css" />

    <!-- Sweet Alert -->
    <link href="/static/template/plugins/bootstrap-sweetalert/sweet-alert.css" rel="stylesheet" type="text/css">

    <!-- ION Slider -->
    <link href="/static/template/plugins/ion-rangeslider/ion.rangeSlider.css" rel="stylesheet" type="text/css"/>
    <link href="/static/template/plugins/ion-rangeslider/ion.rangeSlider.skinModern.css" rel="stylesheet" type="text/css"/>

    <script src="/static/template/assets/js/modernizr.min.js"></script>

    <style>
        .black{
            border: 2px solid #737373;
        }
        table{
            width: 100%;
        }
        table,th{
            position: relative;
            font-size:14px;
            text-align: center;
        }
        td{
            height: 40px;
        }
        table img{
            height: 50px;
            position: absolute;
            top: 0;
            left: 0;
        }
        .font10{
            font-size: 10px;
        }
        .height70{
            height: 70px;
        }
        .height80{
            height: 80px;
        }
        .fontFat{
            font-weight: 600;
        }
        .topLine{
            height: 50px;
        }
        .topTitle{
            font-size:21px;
            font-weight: 600;
            text-align: center;
            padding-left: 180px;
        }
        .relative{
            position: relative;
        }
        .absolute{
            position: absolute;
        }
        .ques{
            top: 0;
            left: 45%;
        }
        .footBox{
            position: absolute;
            line-height: 70px;
            width: 30%;
        }
        .foot1{
            top: 0;
            left: 30%;
        }
        .foot2{
            top: 0;
            left: 60%;
        }
        .foot{
            top: 0;
            left: 0%;
        }
        .dy_editTd{
            cursor:pointer;
            background-color: #f7e0e0;
        }

    </style>
</head>
<body>
<!-- modal -->

    
<!-- end of modal -->

<div class="container">

    <c:choose>
        <c:when test="${requestScope.doIt == false}">
            <h2>${requestScope.msg}</h2>
        </c:when>
        <c:when test="${requestScope.doIt == true}">
            <input type="hidden" style="display:none;" id="officialEvaluationId" value="${requestScope.official.id}"/>
            <!-- 模块Begin -->
            <div class="row" >
                <div class="col-md-10 col-md-offset-1">
                    <!-- 头部无边框部分表格 Begin-->
                    <table>
                        <tr>
                            <td colspan="9" class="topLine">
                                <img src="/static/image/monda_logo.png" alt="">
                                <div  class="topTitle">${requestScope.official.jobName}&nbsp;&nbsp;${requestScope.official.year}年&nbsp;${requestScope.official.month}&nbsp;月份绩效考核表</div>
                            </td>
                        </tr>
                        <tr class="text-left fontFat">
                            <td colspan="3">部门 : ${requestScope.official.departmentName}</td>
                            <td colspan="2">岗位 : ${requestScope.official.jobName}</td>
                            <td colspan="4">姓名 : ${requestScope.official.userName}</td>
                        </tr>
                    </table>
                    <!-- 头部无边框部分表格 End-->
                    <!-- 主体table Begin-->
                    <table border="2px" bordercolor="0x4C96FF" align = "center">
                        <tr>
                            <td colspan="9"><h5 class="fontFat">绩效考核及评分明细</h5></td>
                        </tr>
                        <tr>
                            <th rowspan="2" style="width: 3.5%">序号</th>
                            <th rowspan="2" style="width: 13%">考核维度</th>
                            <th rowspan="2" style="width: 13%">考核项目</th>
                            <th rowspan="2" style="width: 4%">权重</th>
                            <th rowspan="2" style="width: 35%" colspan="2">定义及评分标准</th>
                            <th rowspan="2" style="width: 18.5%">情况列举<br>（个人填）</th>
                            <th colspan="2" style="width: 13%">考核评分</th>
                        </tr>
                        <tr>
                            <th>自评分</th>
                            <th>直接上级</th>
                        </tr>

                        <c:forEach items="${requestScope.items}" var="item" varStatus="status">

                            <tr>
                                <td><c:out value="${status.index + 1}"/></td>
                                <c:if test="${item.first == true}">
                                    <td rowspan="<c:out value="${item.itemNum}"/>"><c:out value="${item.dimensionalityName}"/></td>
                                </c:if>
                                <td tdName="name"><c:out value="${item.name}" /></td>
                                <td tdName="weight"><c:out value="${item.weight}" />%</td>
                                <td colspan="2" class="text-left" tdName="content" ><c:out value="${item.content}"/></td>
                                <td><c:out value="${item.selfContent}" /></td>
                                <td tdName="selfGrade"><c:out value="${item.selfGrade}" /></td>
                                <td tdName="boss1Grade"><c:out value="${item.bossGrade}"/></td>
                            </tr>

                        </c:forEach>

                        <tr>
                            <td colspan="6">小计</td>
                            <td></td>
                            <td id="dy_totalGrade"></td>
                            <td id="dy_totalBoss1Grade"></td>
                        </tr>
                        <tr>
                            <td colspan="2"><b>上级评语</b></td>
                            <td tdName="boss1Remark" colspan="7" class="height80">${requestScope.official.boss1Remark}</td>
                        </tr>
                        <tr>
                            <td colspan="2"><b>部门负责人评语</b></td>
                            <td tdName="boss2Remark" colspan="7" class="height80">${requestScope.official.boss2Remark}</td>
                        </tr>
                        <tr>
                            <td rowspan="2" colspan="2"><b>特别加/减分</b><p class="font10">
                                （部门负责人填写，特别加减分最高20分，由人力行政部审批后生效）</p></td>
                            <td tdName="ex" dyContent="" dyEx="${requestScope.official.exGrade}" colspan="7" class="relative height80"><b class="absolute ques">特别加/减分理由：</b><b style="color:red;">${requestScope.official.exGrade}分。 </b> ${requestScope.official.exReason}</td>
                        </tr>
                        <tr>
                            <td colspan="2"><b>部门负责人：</b></td>
                            <td  style="width: 25%">${requestScope.official.boss2Name}</td>
                            <td><b>人力行政部：</b></td>
                            <td colspan="3"></td>
                        </tr>
                        <tr>
                            <td colspan="2"><b>最终得分</b></td>
                            <td colspan="4">
                                自评分*20%+直接上级评分*80%+特殊加/减分
                                <p class="font10">（部门负责人对上级评分进行修正的，以部门负责人分数为准）</p>
                            </td>
                            <td id="finalGrade1"></td>
                            <td colspan="2" id="finalGrade2"></td>
                        </tr>
                        <tr>
                            <td colspan="9" class="relative height70">
                                <div class="footBox foot">
                                    <div class="absolute foot"><b>自评人：</b> ${requestScope.official.userName}</div>
                                </div>
                                <div class="footBox foot1">
                                    <div class="absolute foot"><b>直接上级：</b> ${requestScope.official.boss1Name}</div>
                                </div>
                                <div class="footBox foot2">
                                    <div class="absolute foot"><b>部门负责人：</b> ${requestScope.official.boss2Name}</div>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="row">
                <div class="col-md-10 col-md-offset-1 text-right">
                    <div id="dy_toolbar" class="btn-group">
                    	<button id="dy_disagreeBtn" title="特别分审核不通过" type="button" class="btn btn-danger">
                            <i class="mdi mdi-emoticon-sad" aria-hidden="true"></i> 特别分审核不通过
                        </button>
                        <button id="dy_submitBtn" title="提交" type="button" class="btn btn-primary">
                            <i class="mdi mdi-checkbox-multiple-marked-circle-outline" aria-hidden="true"></i> 特别分审核通过
                        </button>
                        
                    </div>
                </div>
            </div>
        </c:when>
    </c:choose>


</div>

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

    <!-- Sweet-Alert  -->
    <script src="/static/template/plugins/bootstrap-sweetalert/sweet-alert.min.js"></script>

    <script src="/static/template/plugins/ion-rangeslider/ion.rangeSlider.min.js"></script>

    <script src="/static/jsp_js/examineHrTableShow.js"></script>
</body>
</html>
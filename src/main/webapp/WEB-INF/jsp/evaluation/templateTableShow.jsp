<%--
  User: HuangDongYang<huangdy@pvc123.com>
  Date: 2018/6/26
  Time: 13:26
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
	<title>绩效考核表模板展示</title>
	<link rel="stylesheet" href="/static/bootstrap-3.3.7-dist/css/bootstrap.min.css">

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
	</style>
</head>
<body>
	<div class="container">

	<!-- 模块Begin -->
		<div class="row" >
			<div class="col-md-10 col-md-offset-1">
				<!-- 头部无边框部分表格 Begin-->
				<table>  
					<tr>
						<td colspan="9" class="topLine">
							<img src="/static/image/monda_logo.png" alt="">
							<div  class="topTitle">${requestScope.template.name}&nbsp;&nbsp;&nbsp;&nbsp;年&nbsp;&nbsp;&nbsp;月份绩效考核表</div>
						</td>
					</tr>
					<tr class="text-left fontFat">
						<td colspan="3">部门 : ${requestScope.template.departmentName}</td>
						<td colspan="2">岗位 : ${requestScope.template.jobName}</td>
						<td colspan="4">姓名 : </td>
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
						<th rowspan="2" style="width: 40%" colspan="2">定义及评分标准</th>
						<th rowspan="2" style="width: 13.5%">情况列举<br>（个人填）</th>
						<th colspan="2" style="width: 13%">考核评分</th>
					</tr>
					<tr>
						<th>自评分</th>
						<th>直接上级</th>
					</tr>

					<c:forEach items="${requestScope.items}" var="item" varStatus="status">

						<tr>
							<td><c:out value="${status.index + 1}"/> </td>
							<c:if test="${item.first == true}">
								<td rowspan="<c:out value="${item.itemNum}"/>"><c:out value="${item.dimensionalityName}"/></td>
							</c:if>
							<td><c:out value="${item.name}"/></td>
							<td><c:out value="${item.weight}"/>%</td>
							<td colspan="2" class="text-left">
								<c:out value="${item.content}"/>
							</td>
							<td></td>
							<td></td>
							<td></td>
						</tr>

					</c:forEach>

					<!-- <tr>
						<td>1</td>
						<td rowspan="3">工作结果指标</td>
						<td>项目研发</td>
						<td>25%</td>
						<td  colspan="2" class="text-left">
							是否能在规定的时间内独立完成新项目，新功能的研发工作，以及对已有功能的完善和升级。
							是否及时完成任务。
							S.提前完成并为项目做出突出贡献
							A.提前完成
							B.基本能按时完成
							C.没有按时完成</td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>2</td>
						<td> bug及处理</td>
						<td>25%</td>
						<td colspan="2" class="text-left"></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>3</td>
						<td>代码质量</td>
						<td>10%</td>
						<td colspan="2" class="text-left"></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>4</td>
						<td rowspan="3">工作结果指标</td>
						<td>主动性和责任心</td>
						<td>5%</td>
						<td colspan="2" class="text-left"></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>5</td>
						<td>节流和支撑</td>
						<td>5%</td>
						<td colspan="2" class="text-left"></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>6</td>
						<td>学习与分享</td>
						<td>10%</td>
						<td colspan="2" class="text-left">学习与分享情况如何？本月你学习了什么新的技术？做了怎样有影响力</td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>7</td>
						<td>工作表现</td>
						<td>文化认同、工作态度、行为表现</td>
						<td>5%</td>
						<td colspan="2" class="text-left">认同公司鸟人精神及“三成四风”文化理念；心态积极，主动承担，工作具认真、主动、热情，无抱怨、拖延、推诿；服从与配合度好，无违反公司制度行为，无因工作态度而引发的失误或投诉。</td>
						<td></td>
						<td></td>
						<td></td>
					</tr> -->
					<tr>
						<td colspan="6">小计</td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="2"><b>上级评语</b></td>
						<td colspan="7" class="height80"></td>
					</tr>
					<tr>
						<td colspan="2"><b>部门负责人评语</b></td>
						<td colspan="7" class="height80"></td>
					</tr>
					<tr>
						<td rowspan="2" colspan="2"><b>特别加/减分</b><p class="font10">
（部门负责人填写，特别加减分最高20分，由人力行政部审批后生效）</p></td>
						<td colspan="7" class="relative height80"><b class="absolute ques">特别加/减分理由：</b></td>
					</tr>
					<tr>
						<td colspan="2"><b>部门负责人：  </b></td>
						<td  style="width: 25%"></td>
						<td><b>人力行政部：</b></td>
						<td colspan="3"></td>
					</tr>
					<tr>
						<td colspan="2"><b>最终得分</b></td>
						<td colspan="4">
							自评分*20%+直接上级评分*80%+特殊加/减分
							<p class="font10">（部门负责人对上级评分进行修正的，以部门负责人分数为准）</p>
						</td>
						<td></td>
						<td colspan="2"></td>
					</tr>
					<tr>
						<td colspan="9" class="relative height70">
							<div class="footBox foot">
								<div class="absolute foot"><b>自评人：</b></div>
							</div>
							<div class="footBox foot1">
								<div class="absolute foot"><b>直接上级：</b></div>
							</div>
							<div class="footBox foot2">
								<div class="absolute foot"><b>部门负责人：</b></div>
							</div>
						</td>
					</tr>
				</table>  
			</div>
		</div>
	</div>
</body>
</html>
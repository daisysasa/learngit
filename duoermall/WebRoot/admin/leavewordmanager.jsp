<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.xs.bll.*,com.xs.util.PagerMetal"%>
<%@ page import="com.xs.entity.*"%>
<%@page import="com.xs.dal.DALBase"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ include file="law.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="xs" uri="/xspager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>在线留言信息</title>
<link href="<%=SystemParam.getSiteRoot() %>/admin/css/web2table.css" rel="stylesheet" type="text/css" />
 <link href="<%=SystemParam.getSiteRoot() %>/admin/css/layout.css" rel="stylesheet" type="text/css" />
 <link href="<%=SystemParam.getSiteRoot() %>/admin/css/menu.css" rel="stylesheet" type="text/css" />
    
<script type="text/javascript"
	src="<%=SystemParam.getSiteRoot()%>/webui/jquery/jquery-1.9.0.js"></script>
<link
	href="<%=SystemParam.getSiteRoot()%>/webui/artDialog/skins/default.css"
	rel="stylesheet" type="text/css" />
<script
	src="<%=SystemParam.getSiteRoot()%>/webui/artDialog/jquery.artDialog.source.js"
	type="text/javascript"></script>
<script
	src="<%=SystemParam.getSiteRoot()%>/webui/artDialog/iframeTools.source.js"
	type="text/javascript"></script>
<link
	href="<%=SystemParam.getSiteRoot()%>/webui/treetable/skin/jquery.treetable.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=SystemParam.getSiteRoot()%>/webui/treetable/skin/jquery.treetable.theme.default.css"
	rel="stylesheet" type="text/css" />
<script
	src="<%=SystemParam.getSiteRoot()%>/webui/treetable/js/jquery.treetable.js"
	type="text/javascript"></script>
<script type="text/javascript">
			$(function() {
			    $("#btnDelete").click(function(){
			        if($(".check:checked").length<1)
			        {
			           $.dialog.alert("请选择需要删除的记录");
			           return;
			        } 
			        $(".check:checked").each(function(index,domEle){
			             var id=$(domEle).val();
			             $.dialog.confirm("你确定要注销在线留言信息?", function(){
				             window.location.href=encodeURI('<%=SystemParam.getSiteRoot()%>/admin/leavewordmanager.do?actiontype=delete&id='+ id);
						  });
						});
					});
					$("#btnCheckAll").click(function() {
						var ischeck = false;
						$(".check").each(function() {
							if ($(this).is(":checked")) {
								$(this).prop("checked", "");
								ischeck = false;
							} else {
								$(this).prop("checked", "true");
								ischeck = true;
							}
						});
						if ($(this).text() == "选择记录")
							$(this).text("取消选择");
						else
							$(this).text("选择记录");
					})
	});
</script>
</head>
<body >
	
			<div class="search-title">
				<h2>在线留言管理</h2>
				<div class="description">
					
				</div>
			</div>
			<!-- 搜索控件开始 -->
			<div class="search-options">
				<form id="searchForm"
					action="<%=SystemParam.getSiteRoot()%>/admin/leavewordmanager.do"
					method="post">
					<table class="grid" cellspacing="1" width="100%">
						<tbody>
							<tr>
								<td>标题 <input name="title" value="${title}"
									class="input-txt" type="text" id="title" /> <input
									type="hidden" name="actiontype" value="search" /> <input
									type="hidden" name="seedid" value="${seedid}" />
									<div class="ui-button">
										<input type="submit" value="搜索" id="btnSearch"
											class="ui-button-text" />
									</div></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
			<!-- 搜索控件结束 -->
			<div class="clear"></div>
			<div class="action-details">
				
					<a href="#" id="btnCheckAll" class="action-button"><img
						src="images/submit.gif">选择
					</a>
				 <a href="#" id="btnDelete" class="action-button"><img
						src="images/del.gif">删除
					</a>
				
			</div>
			<table id="module" width="100%" border="0" cellspacing="0"
				cellpadding="0" class="ui-record-table">
				<thead>
					<tr>
						<th>选择</th>
						<th><b>标题</b>
						</th>
						<th><b>留言人</b>
						</th>
						<th><b>留言时间</b>
						</th>
						<th>
						  <b>状态</b>
						</th>
						<th>
						   <b>内容</b>
						</th>
						
						
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if
						test="${listleaveword== null || fn:length(listleaveword) == 0}">
						<tr>
							<td colspan="20">没有相关在线留言信息</td>
						</tr>
					</c:if>
					<c:forEach var="temleaveword" items="${listleaveword}">
						<tr>
							<td>&nbsp<input id="chk${temleaveword.id}" class="check"
								name="chk${temleaveword.id}" type="checkbox"
								value='${temleaveword.id}'>
							</td>
							<td>${temleaveword.title}</td>
							<td>${temleaveword.lwren}</td>
							<td>${temleaveword.pubtime}</td>
							
							
							<td>${temleaveword.status==1?"已回复":"待回复"}</td>
							<td>${temleaveword.dcontent}</td>
							<td>
								<a class="chakan" href="leaveworddetails.jsp?id=${temleaveword.id}&seedid=302">查看</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="clear"></div>
			<xs:pager id="pager1" attcheform="searchForm" />
	
</body>
</html>

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
<title>资讯信息信息</title>
 
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
<script src="<%=SystemParam.getSiteRoot()%>/webui/combo/combo.js"
			type="text/javascript"></script>
<script type="text/javascript">
			$(function() {
			    $("#btnDelete").click(function(){
			        if($(".check:checked").length<1)
			        {
			           $.dialog.alert("请选择需要删除的资讯信息");
			           return;
			        } 
			        $(".check:checked").each(function(index,domEle){
			             var id=$(domEle).val();
			             $.dialog.confirm("你确定要注销资讯信息信息?", function(){
				             window.location.href=encodeURI('<%=SystemParam.getSiteRoot()%>/admin/xinximanager.do?actiontype=delete&id='+ id);
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
				<h2>新闻资讯管理</h2>
				<div class="description">
					<a href="<%=SystemParam.getSiteRoot()%>/admin/xinximanager.do?actiontype=load&seedid=202">发布新闻资讯
					</a>
				</div>
			</div>
			<!-- 搜索控件开始 -->
			<div class="search-options">
				<form id="searchForm"
					action="<%=SystemParam.getSiteRoot()%>/admin/xinximanager.do"
					method="post">
					<table class="grid" cellspacing="1" width="100%">
						<tbody>
							<tr>
								<td>资讯标题 <input name="title" value="${title}"
									class="input-txt" type="text" id="title" /> 
									<input type="hidden" name="actiontype" value="search" />
									   新闻分类<span class="search-form-item-content">
					                            <a textfieldid="hidLanmuname" valuefieldid="hidLanmuid" window_width="800" window_height="400"
					                                window_title="系统栏目" url="<%=SystemParam.getSiteRoot() %>/admin/lanmuchoose.jsp" class="ui-open-trigger ui-select-middle-trigger"
					                                href="#" style="width: 120px;">栏目名称 </a>
					                            <input name="lanmuid" validate="{required:true,messages:{required:'请选择栏目'}}" type="hidden" id="hidLanmuid" runat="server" value="${lanmuid}" />
					                            <input name="lanmuming" type="hidden" id="hidLanmuname" runat="server" value="${lanmuming}" />
					                   </span> 
									<input type="hidden" name="seedid" value="${seedid}" />
									<div class="ui-button">
										<input type="submit" value="搜索" id="btnSearch" class="ui-button-text" />
									</div>
								</td>
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
					</a> <a href="#" id="btnDelete" class="action-button"><img
						src="images/del.gif">删除
					</a>
				
			</div>
			<table id="module" width="100%" border="0" cellspacing="0"
				cellpadding="0" class="ui-record-table">
				<thead>
					<tr>
						<th>选择</th>
						<th style="width:260px;overflow:hidden;"><b>标题</b>
						</th>
						<th><b>发布人</b>
						</th>
						<th><b>来源</b>
						</th>
						<th><b>发布时间</b>
						</th>
						<th><b>点击次数</b>
						</th>
					
						<th><b>所属栏目</b>
						</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${listxinxi== null || fn:length(listxinxi) == 0}">
						<tr>
							<td colspan="20">没有相关资讯信息信息</td>
						</tr>
					</c:if>
					<c:forEach var="temxinxi" items="${listxinxi}">
						<tr>
							<td>&nbsp<input id="chk${temxinxi.id}" class="check"
								name="chk${temxinxi.id}" type="checkbox" value='${temxinxi.id}'>
							</td>
							<td>${temxinxi.title}</td>
							<td>${temxinxi.pubren}</td>
							<td>${temxinxi.laiyuan}</td>
							<td>${temxinxi.pubtime}</td>
							<td>${temxinxi.clickcount}</td>
							
							
							<td>${temxinxi.lanmuming}(${temxinxi.lanmuid})</td>
							<td><a class="edit"
								href="<%=SystemParam.getSiteRoot() %>/admin/xinximanager.do?actiontype=load&id=${temxinxi.id}&seedid=202">修改</a>
								<a class="chakan" target="_blank" href="<%=SystemParam.getSiteRoot() %>/e/xinxiinfo.jsp?id=${temxinxi.id}">查看</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="clear"></div>
			<xs:pager id="pager1" attcheform="searchForm" />
		
	
</body>
</html>

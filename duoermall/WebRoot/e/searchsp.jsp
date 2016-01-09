

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.xs.uibuilder.*" %>
<%@page
	import="com.xs.bll.*,java.util.*,java.text.*,com.xs.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品搜索</title>
<link rel="stylesheet"
	href="<%=SystemParam.getSiteRoot()%>/e/css/index.css" type="text/css"></link>

<link href="<%=SystemParam.getSiteRoot()%>/admin/css/web2table.css"
	rel="stylesheet" type="text/css" />

<link rel="stylesheet"
	href="<%=SystemParam.getSiteRoot()%>/e/css/lanmu.css" type="text/css"></link>

<script
	src="<%=SystemParam.getSiteRoot()%>/webui/jquery/jquery-1.5.2.min.js"
	type="text/javascript"></script>



<script type="text/javascript">
	$(function() {

		$(".tabcontainer dl dt").each(function(index, dom) {

			$(this).mouseenter(function() {

				$(".tabcontainer dl dt").removeClass("on");
				$(this).addClass("on");
				$(".tabcontainer dl dd").hide().eq(index).show();

			})
		})

	});
</script>

</head>
<body>

	<jsp:include page="head.jsp"></jsp:include>


	<div class="fn-clear"></div>
	<div class="wrap">



		<div class="tabcontainer">

			<dl>

				<div class="fn-clear"></div>
				
				<form  name="form1" method="post" action="<%=SystemParam.getSiteRoot() %>/e/searchsp.jsp" id="form1">
				<table width="100%">

					<tr>
						<td>商品名称:<input type="text" name="spname" class="input-txt" />
							<div class="ui-button">
								<input type="submit" name="spname" class="ui-button-text"
									value="搜索" />
							</div></td>
					</tr>
				</table>
				
				</form>
				
				
				<%
				   String spname=request.getParameter("spname");
				   if(spname!=null)
				   {
				%>
				     <%=new ShangpinBuilder().search(spname, "fn-show")%>

                  <%} %>

			</dl>
		</div>





		<div class="fn-clear"></div>




		<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>
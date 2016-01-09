<%@ page language="java" import="com.xs.bll.SystemParam" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.xs.bll.*,com.xs.entity.*"%>
 
 <script type="text/javascript">
  
    $(function(){
      
    	  var headid = '<%=request.getParameter("hid")%>';
          if (headid != 'null') {

              $("#" + headid).addClass("current");
          } else {

              $("#h101").addClass("current");
          }
      $(".exit").click(function(){
          
            var pageurl_pagescope= $("#pageurl_pagescope").val();
            
            $.ajax({
		                     
		              url:encodeURI('<%=SystemParam.getSiteRoot()%>/admin/huiyuanmanager.do?actiontype=exit'),
					   method:'get',
					   success:function(){
					      window.location.reload();
					  },
					  error:function(xmhttprequest,status,excetpion){
					     $.alert("系统错误，错误编码"+status);
					  }
		     })

      })
    })

</script>
 
<%
      Huiyuan temhy=(Huiyuan)request.getSession().getAttribute("huiyuan");
      if(temhy!=null)
         request.setAttribute("huiyuan", temhy);
         
     

 %>

  
  
<div class="index-toolbar">
  <div class="wrap">
  
            
    <%if(temhy!=null){ %>
             欢迎你<a href="<%=SystemParam.getSiteRoot() %>/e/huiyuan/index.jsp">${huiyuan.accountname }</a>
     <a class="exit" style="cursor:pointer">退出</a>|
    <%} else{%>
      <a href="<%=SystemParam.getSiteRoot() %>/e/login.jsp">用户登录</a>|
    <%} %>
     <a href="<%=SystemParam.getSiteRoot() %>/e/register.jsp">用户注册</a>|
     <a href="<%=SystemParam.getSiteRoot()%>/admin/login.jsp">系统后台</a>
  </div>
  
   
</div>
 <div >
  
      <img src="<%=SystemParam.getSiteRoot() %>/e/images/banner.jpg" width="100%" height="120px" >
 </div>
  
 <div class="juhong-menubar">
<div class="wrap">
	<div   class="juhong-menucontent">
	
	   <ul >
	      <li id="h101" >
	      
	         <a href="<%=SystemParam.getSiteRoot() %>/e/index.jsp?hid=h101"  class="current">首页</a>
	      
	      </li>
	       <li class="line"></li>
	       
	       <li id="h102">
	      
	         <a href="<%=SystemParam.getSiteRoot() %>/e/shangpinlist.jsp?spcid=1&hid=h102">洁面</a>
	      
	      </li>
	       <li class="line"></li>
	      <li id="h103">
	      
	         <a href="<%=SystemParam.getSiteRoot() %>/e/shangpinlist.jsp?spcid=2&hid=h103">爽肤水</a>
	      
	      </li>
	       <li class="line"></li>
	       
	        <li id="h104">
	      
	         <a href="<%=SystemParam.getSiteRoot() %>/e/shangpinlist.jsp?spcid=3&hid=h104">面霜</a>
	      
	      </li>
	       <li class="line"></li>
	        <li id="h105">
	      
	         <a href="<%=SystemParam.getSiteRoot() %>/e/shangpinlist.jsp?spcid=4&hid=h105">沐浴</a>
	      
	      </li>
	       <li class="line"></li>
	       
	       <li id="h106">
	      
	         <a href="<%=SystemParam.getSiteRoot() %>/e/shangpinlist.jsp?spcid=5&hid=h106">奢侈美妆</a>
	      
	      </li>
	       <li class="line"></li>
	      <li id="h107">
	      
	         <a href="<%=SystemParam.getSiteRoot() %>/e/shopcart.jsp?hid=h107"  >购物车</a>
	      
	      </li>
	       <li class="line"></li>
	      <li id="h108">
	      
	         <a href="<%=SystemParam.getSiteRoot() %>/e/searchsp.jsp?hid=h108"  >商品搜索</a>
	      
	      </li>
	      <li class="line"></li>
	    
	      <li id="h109">
	      
	         <a href="<%=SystemParam.getSiteRoot() %>/e/leavewordinfo.jsp?hid=h109"  >在线留言</a>
	      
	      </li>
	   
	   </ul>
	  
	</div>
	
</div>
</div>
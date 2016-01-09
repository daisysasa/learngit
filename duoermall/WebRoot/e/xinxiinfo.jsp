<%@page import="com.xs.bll.*"%>
<%@page import="com.xs.entity.*,com.xs.dal.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <%
  
            String id=request.getParameter("id");
            if(id!=null){
            
                Xinxi xinxi=(Xinxi)DALBase.load(Xinxi.class,new Integer(id));
                
                if(xinxi!=null)
                   request.setAttribute("xinxi",xinxi);
                
            }
  
   %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统首页</title>
<link rel="stylesheet" href="css/index.css" type="text/css"></link>

<link rel="stylesheet" href="css/register.css" type="text/css"></link>

<link rel="stylesheet" href="css/leaveword.css" type="text/css"></link>


    <script src="<%=SystemParam.getSiteRoot() %>/webui/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>
    <script type="text/javascript">
    
            $(function(){
               
                 $("#btnAgree").click(function(){
		                  
		                  var temaccountname= $("#hidCurrenthy").val();
		                  var temreurl=$("#reUrl").val();
		                  if(temaccountname==""){
		                          
	                         window.location.href="login.jsp?reurl="+temreurl;
	                         return;
	                        
	                      }
	                      var   xinxiid=$(this).attr("xinxiid");
	                      $.ajax({
		                     
		                        url:encodeURI('<%=SystemParam.getSiteRoot()%>/admin/xinximanager.do?actiontype=agree&id='+xinxiid),
					            method:'get',
					            
					            success:function(data){
					               $("#btnAgree").html("<span>"+data+"</span>");
					            },
					            error:function(xmhttprequest,status,excetpion){
					                alert("系统错误，错误编码"+status);
					            }
		                  })
	                      
                  })
                  
                   $("#btnAgainst").click(function(){
		                  
		                  var temaccountname= $("#hidCurrenthy").val();
		                  var temreurl=$("#reUrl").val();
		                  if(temaccountname==""){
		                          
	                         window.location.href="login.jsp?reurl="+temreurl;
	                         return;
	                        
	                      }
	                      var   xinxiid=$(this).attr("xinxiid");
	                      $.ajax({
		                     
		                        url:encodeURI('<%=SystemParam.getSiteRoot()%>/admin/xinximanager.do?actiontype=against&id='+xinxiid),
					            method:'get',
					            
					            success:function(data){
					                $("#btnAgainst").html("<span>"+data+"</span>");
					            },
					            error:function(xmhttprequest,status,excetpion){
					                alert("系统错误，错误编码"+status);
					            }
		                  })
	                      
                  })
                 
            })
    
    
    </script>
    
    
    
   

    

</head>
<body>

	<jsp:include page="head.jsp"></jsp:include>
	
	<div class="wrap">
	  <div class="cover-title">
                   当前位置：<a href="<%=SystemParam.getSiteRoot() %>/e/index.jsp">首页</a> &gt;&gt; <a>系统信息</a>
      </div>
	</div>
	<input type="hidden" id="reUrl" name="reurl" value="/e/xinxiinfo.jsp?id=<%=id%>"/>

	<div class="fn-clear"></div>
	
	<div class="wrap">
		<div style="width:810px" class="fn-left">
		 
		   <div class="info">
                    <h1>
                           ${xinxi.title}
                    </h1>
                    <h5>
                                                         来源: ${xinxi.pubren}
                                                        浏览:<span id="count">2次</span>
                                                         发布时间：${xinxi.pubtime}
                    </h5>
                    <div class="news-content">
                        <p>
                            ${xinxi.dcontent}  
                        </p>
                       
                    </div>
                     <div class="diaocha" >
                            <a xinxiid="<%=id %>" id="btnAgree"><span>${xinxi.agreecount}</span></a>
                            <a xinxiid="<%=id %>" id="btnAgainst" class="against"><span>${xinxi.againstcount}</span></a>
                    </div>
                        
                </div>
                
                <jsp:include page="comment.jsp?commenttype=<=%news%>"></jsp:include>
		
		</div>
		 <div class="fn-left" style="width:310px;">
	         <div class="blue-box" >
                <div class="blue-box-title">推荐资讯</div>
			    <%=new LanmuBuilder().buildTuijianTextContent("blue-box-content") %>
            </div>
            <div class="blue-box" >
                <div class="blue-box-title">最新资讯</div>
			    <%=new LanmuBuilder().buildZuixinTextContent("blue-box-content") %>
            </div>
	     </div>

	</div>
	
	<div class="fn-clear"></div>


	<jsp:include page="bottom.jsp"></jsp:include>



</body>
</html>
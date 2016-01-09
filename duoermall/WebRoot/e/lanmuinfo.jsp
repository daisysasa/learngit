
<%@page import="com.xs.bll.*,java.util.*"%>
<%@page import="com.xs.entity.*,com.xs.dal.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <%
  
            String id=request.getParameter("lanmuid");
            if(id!=null){
            
                Lanmu lanmu=(Lanmu)DALBase.load("lanmu","where id="+id);
               
                if(lanmu!=null)
                   request.setAttribute("lanmu",lanmu);
                
            }
            LanmuBuilder lmb=new LanmuBuilder();
            List<Xinxi> xinxilist=lmb.findXinxiByLanmu(new Integer(id));
            
            
            
  
   %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统栏目信息</title>
<link rel="stylesheet" href="css/index.css" type="text/css"></link>
<script src="<%=SystemParam.getSiteRoot() %>/webui/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>

  
    

</head>
<body>

	<jsp:include page="head.jsp"></jsp:include>
	
	<div class="wrap">
	  <div class="cover-title">
                       当前位置：<a href="index.jsp">首页</a> &gt;&gt; <a>${lanmu.title}</a>
      </div>
	</div>
	

	<div class="fn-clear"></div>
	
	<div class="wrap">
		<div class="directory">
		  
		     <div class="directory-title">
		          <div class="introduce"><a href="lanmuinfo.jsp?lanmuid=${lanmu.id}">${lanmu.title}</a></div>
     	     </div>
     	     <div class="directory-content">
     	     
     	         <ul>
     	            <% 
     	              for(Iterator it=xinxilist.iterator();it.hasNext();){ 
     	              
     	                  Xinxi temxinxi=(Xinxi)it.next();
     	            
     	            %>
     	                 
     	            <li>
     	                <div class="pic">
     	                   <a><img src="<%=temxinxi.getTupian2() %>"/></a>
     	                </div>
     	                <div class="text">
     	                    <div class="text-title"><a href="<%=SystemParam.getSiteRoot()%>/e/xinxiinfo.jsp?id=<%=temxinxi.getId()%>"><%=temxinxi.getTitle() %></a></div>
     	                    <div class="subtitle">
     	                                                                发布时间:<span><%=temxinxi.getPubtime().toString() %></span>
     	                                                                 发布人:<span><%=temxinxi.getPubren() %></span>  
     	                                                                浏览次数: <span><%=temxinxi.getClickcount() %></span>                                     
     	                                                                
     	                    
     	                    </div>
     	                    <div class="text-content">
                                  <%=temxinxi.getZhaiyao()%>                                                       
                             </div>
     	                </div>
     	            </li>
     	            <%} %>
     	         </ul>
     	        
     	     </div>
		  
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
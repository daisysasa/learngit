

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.xs.uibuilder.*" %>
<%@page import="com.xs.bll.*,java.util.*,java.text.*,com.xs.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统首页</title>
<link rel="stylesheet" href="<%=SystemParam.getSiteRoot() %>/e/css/index.css" type="text/css"></link>
<link href="<%=SystemParam.getSiteRoot() %>/e/css/box.css"  rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="<%=SystemParam.getSiteRoot() %>/e/css/jquery.iFadeSlide.css" type="text/css"></link>
<link rel="stylesheet" href="<%=SystemParam.getSiteRoot() %>/e/css/lanmu.css" type="text/css"></link>

<script src="<%=SystemParam.getSiteRoot() %>/webui/jquery/jquery-1.5.2.min.js" type="text/javascript"></script>

    <script src="<%=SystemParam.getSiteRoot() %>/e/js/jquery.iFadeSldie.js" type="text/javascript"></script>

    <script type="text/javascript">
        $(function () {


            
            $('div#slide_c').iFadeSlide({
                field: $('div#slide_c img'),
                icocon: $('div.ico_c'),
                outTime: 100,
                inTime: 200
            });
            
            $(".collapsed").each(function(i,dom){
	             
	             
	             $(this).click(function(){
		             var blockid= $(this).attr("blockid");
		             if($("#"+blockid).is(":hidden")){
		               
		               $(this).attr("src","images/collapsed_no.gif");
		               $("#"+blockid).show();
		             
		             }else
		             {
		                $(this).attr("src","images/collapsed_yes.gif");
		               $("#"+blockid).hide(); 
		             }
	             });
	             
	         
	         
	         });
            
           $(".tabcontainer dl dt").each(function(index,dom){
               
               
                  $(this).mouseenter(function(){
                   
                      
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
	<div class="wrap">
		<div class="sidebar fn-left"
			style="height:316px; width:262px; padding-right: 0px; background: #fff;">
			<h3>系统公告</h3>
			<%=NoticeService.getNotice() %>

		</div>
		<div class="fn-left">
		
		     <%=FocusgraphicBLL.getFocusgraphic() %>
          
		</div>

		

	</div>
	<div class="fn-clear">
	</div>
  
	<div class="fn-clear"></div>
	<div class="wrap">
	   <%ShangpinBuilder  shangpinbuilder=new ShangpinBuilder(); %>
	   <%=shangpinbuilder.buildHotSale()%>
	 
	     
	   <%=shangpinbuilder.buildImageShangpin(1, 10) %>
	    
	  
	 
       
       <%=shangpinbuilder.buildImageShangpin(2, 10) %>
       
       <%=shangpinbuilder.buildImageShangpin(3, 10) %>
       
       <%=shangpinbuilder.buildImageShangpin(4, 10) %>
       
       <%=shangpinbuilder.buildImageShangpin(5, 10) %>
	  
	</div>
	
	  
	</div>

	


	<jsp:include page="bottom.jsp"></jsp:include>



</body>
</html>
<%@page import="com.xs.bll.FriendlinkBLL"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
  
  <div class="wrap">
		<div class="frendlink">
			<strong>友情链接：</strong>
			
			  <%=FriendlinkBLL.getFriendLink() %>

		</div>


	</div>
	<div class="fn-clear"></div>
	<div>
		<div id="footer_bg">
			<div id="footer">朵儿美妆商城</div>
		</div>
	</div>
	
</div>
	

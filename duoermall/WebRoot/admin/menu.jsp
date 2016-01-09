<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.xs.bll.*"%>

<script type="text/javascript">
     
   $(function(){

	   var autoSize = function () {


           $("#side-menu").css("height", $(document).height() - $("#sys-head").height());
          // $("#side-menu").css("overflow-y","auto");
//           var leftheight = $('#side-menu').height();
//           var rightheight = $('#sys-main').height();

//           if (leftheight < rightheight) {
//                 $('#side-menu').css('height', rightheight + 'px')
//             } else {
//               $('#sys-main').css('height', leftheight + 'px')
//            }

       }
       autoSize();
       $(window).resize(autoSize);
       $("#side-menu  li dd a").click(function () {
           $("#side-menu  li dd a").removeClass("current");
           $(this).addClass("current");

       })
	})
</script>

<ul class="left-menu" id="side-menu" style="height: 612px;">
	
	
	
	
	<li>
		<dl>
			<dt class="t1">
				<em></em>用户管理
			</dt>
			<dd>
				<a id="101" target="main"  href="<%=SystemParam.getSiteRoot() %>/admin/usersmanager.do?actiontype=get&seedid=101">用户管理</a>
				
				<a id="102" target="main"  href="<%=SystemParam.getSiteRoot() %>/admin/huiyuanmanager.do?actiontype=get&seedid=102">会员管理</a>
				

			</dd>
		</dl></li>
	<li>
		<dl>
			<dt class="t1">
				<em></em>商品管理
			</dt>
			<dd>
				<a id="201"  target="main" href="<%=SystemParam.getSiteRoot() %>/admin/shangpinmanager.do?actiontype=get&seedid=201" target="main">商品管理</a>
			</dd>
			<dd>
				<a  id="202"  target="main"  href="<%=SystemParam.getSiteRoot() %>/admin/shangpinmanager.do?actiontype=load&seedid=202">发布商品</a>
			</dd>
			
			<dd>
				<a id="203"  target="main"  target="main" href="<%=SystemParam.getSiteRoot() %>/admin/spcategorymanager.do?actiontype=get&seedid=203">商品类别管理</a>
			</dd>
			
			<dd>
				<a id="204"  target="main" href="<%=SystemParam.getSiteRoot() %>/admin/dingdanmanager.do?actiontype=get&seedid=204">订单管理</a>
			</dd>
		</dl>
	  </li>

	<li>
		<dl>
			<dt class="t3">
				<em></em>系统信息
			</dt>

			<dd>
				<a id="301" target="main" href="<%=SystemParam.getSiteRoot() %>/admin/noticemanager.do?actiontype=get&seedid=304">系统公告管理</a>
			</dd>
			<dd>
				<a id="302" target="main" href="<%=SystemParam.getSiteRoot() %>/admin/jiaodiantumanager.do?actiontype=get&seedid=305">焦点图管理</a>
			</dd>
			<dd>
				<a id="303" target="main" href="<%=SystemParam.getSiteRoot() %>/admin/friendlinkmanager.do?actiontype=get&seedid=306">友情链接管理</a>
			</dd>
			<dd>
				<a id="304" target="main" href="<%=SystemParam.getSiteRoot() %>/admin/leavewordmanager.do?actiontype=get&seedid=401">在线留言管理</a>
			</dd>
			<dd>
				<a id="305" target="main" href="<%=SystemParam.getSiteRoot() %>/admin/commentmanager.do?actiontype=get&seedid=402">会员评论管理</a>
			</dd>

		</dl></li>
</ul>

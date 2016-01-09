package com.xs.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SQLQuery;
import org.hibernate.Query;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.text.MessageFormat;

import com.xs.bll.*;
import com.xs.dal.*;
import com.xs.entity.*;
import com.xs.util.DaowenUIHelper;
import com.xs.util.ExtHelper;
import com.xs.util.HibernateSessionFactory;
import com.xs.util.PagerMetal;

/**************************
 * 
 * @author daowen date:2013-12-05 控制
 * 
 */
public class SpcategoryAction extends PageActionBase {

	/********************************************************
	 ****************** 信息注销监听支持*****************************
	 *********************************************************/
	public void delete() {
		String id = request.getParameter("id");
		DALBase.delete("spcategory", " where id=" + id);
		binding();
	}

	/*************************************************************
	 **************** 保存动作监听支持******************************
	 **************************************************************/
	public void save() {
		String forwardurl = request.getParameter("forwardurl");
		String mingcheng = request.getParameter("mingcheng");
		String jieshao = request.getParameter("jieshao");
		SimpleDateFormat sdfspcategory = new SimpleDateFormat("yyyy-MM-dd");
		Spcategory spcategory = new Spcategory();
		spcategory.setMingcheng(mingcheng == null ? "" : mingcheng);
		spcategory.setJieshao(jieshao == null ? "" : jieshao);
		DALBase.save(spcategory);
		// 保存附件
		// attachements(request,response,new
		// Integer(spcategory.getId()).toString());
		binding();
	}

	/******************************************************
	 *********************** 内部附件支持*********************
	 *******************************************************/
	public void attachements(HttpServletRequest request,
			HttpServletResponse response, String belongid) {
		DALBase.delete("attachement", MessageFormat.format(
				" where belongid=''{0}'' and belongtable=''spcategory'' ",
				belongid));
		String[] photos = request.getParameterValues("fileuploaded");
		if (photos == null)
			return;
		for (int i = 0; i < photos.length; i++) {
			Attachement a = new Attachement();
			a.setType("images");
			a.setPubtime(new Date());
			a.setBelongfileldname("id");
			a.setFilename(photos[i]);
			a.setBelongid(belongid);
			a.setBelongtable("spcategory");
			a.setUrl(SystemParam.getSiteRoot() + "/upload/temp/"
					+ a.getFilename());
			a.setTitle(a.getFilename());
			DALBase.save(a);
		}
	}

	/******************************************************
	 *********************** 更新内部支持*********************
	 *******************************************************/
	public void update() {
		String forwardurl = request.getParameter("forwardurl");
		String id = request.getParameter("id");
		if (id == null)
			return;
		Spcategory spcategory = (Spcategory) DALBase.load(Spcategory.class,
				new Integer(id));
		if (spcategory == null)
			return;
		String mingcheng = request.getParameter("mingcheng");
		String jieshao = request.getParameter("jieshao");
		SimpleDateFormat sdfspcategory = new SimpleDateFormat("yyyy-MM-dd");
		spcategory.setMingcheng(mingcheng);
		spcategory.setJieshao(jieshao);
		DALBase.update(spcategory);
		attachements(request, response,
				new Integer(spcategory.getId()).toString());
		binding();
	}

	/******************************************************
	 *********************** 加载内部支持*********************
	 *******************************************************/
	public void load() {
		//
		String id = request.getParameter("id");
		String actiontype = "save";
		dispatchParams(request, response);
		if (id != null) {
			Spcategory spcategory = (Spcategory) DALBase.load("spcategory",
					"where id=" + id);
			if (spcategory != null) {
				request.setAttribute("spcategory", spcategory);
			}
			actiontype = "update";
			request.setAttribute("id", id);
		}
		request.setAttribute("actiontype", actiontype);
		String forwardurl = request.getParameter("forwardurl");
		System.out.println("forwardurl=" + forwardurl);
		if (forwardurl == null) {
			forwardurl = "/admin/spcategoryadd.jsp";
		}
		try {
			request.getRequestDispatcher(forwardurl).forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/******************************************************
	 *********************** 数据绑定内部支持*********************
	 *******************************************************/
	public void binding() {
		String filter = "where 1=1 ";
		String mingcheng = request.getParameter("mingcheng");
		if (mingcheng != null)
			filter += "  and mingcheng like '%" + mingcheng + "%'  ";
		//
		int pageindex = 1;
		int pagesize = 10;
		// 获取当前分页
		String currentpageindex = request.getParameter("currentpageindex");
		// 当前页面尺寸
		String currentpagesize = request.getParameter("pagesize");
		// 设置当前页
		if (currentpageindex != null)
			pageindex = new Integer(currentpageindex);
		// 设置当前页尺寸
		if (currentpagesize != null)
			pagesize = new Integer(currentpagesize);
		List<Spcategory> listspcategory = DALBase.getPageEnity("spcategory",
				filter, pageindex, pagesize);
		int recordscount = DALBase.getRecordCount("spcategory",
				filter == null ? "" : filter);
		request.setAttribute("listspcategory", listspcategory);
		PagerMetal pm = new PagerMetal(recordscount);
		// 设置尺寸
		pm.setPagesize(pagesize);
		// 设置当前显示页
		pm.setCurpageindex(pageindex);
		// 设置分页信息
		request.setAttribute("pagermetal", pm);
		// 分发请求参数
		dispatchParams(request, response);
		String forwardurl = request.getParameter("forwardurl");
		System.out.println("forwardurl=" + forwardurl);
		if (forwardurl == null) {
			forwardurl = "/admin/spcategorymanager.jsp";
		}
		try {
			request.getRequestDispatcher(forwardurl).forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

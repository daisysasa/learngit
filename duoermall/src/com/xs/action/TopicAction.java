package com.xs.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SQLQuery;
import org.hibernate.Query;

import com.xs.bll.*;
import com.xs.dal.*;
import com.xs.entity.*;
import com.xs.util.DaowenUIHelper;
import com.xs.util.ExtHelper;
import com.xs.util.HibernateSessionFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.text.MessageFormat;

public class TopicAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String actiontype = request.getParameter("actiontype");
		if (actiontype.equals("getTopics")) {
			getTopics(request, response);
		}
		if (actiontype.equals("loadTopic")) {
			loadTopic(mapping, form, request, response);
		}
		if (actiontype.equals("operOver")) {
			if (request.getParameter("command").equals("update"))
				updateTopic(mapping, form, request, response);
			if (request.getParameter("command").equals("add"))
				addTopic(mapping, form, request, response);
		}
		if (actiontype.equals("delete")) {
			deleteTopic(mapping, form, request, response);
		}
		if (actiontype.equals("getComboJson")) {
			getComboJson(mapping, form, request, response);
		}
		if (actiontype.equals("getSerialno")) {
			getSerialno(mapping, form, request, response);
		}
		return null;
	}

	private void deleteTopic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		
		deleteTopic(new Integer(id));
	}
	///删除主题
	private void    deleteTopic(int topicid)
	{
		
		DALBase.delete("topic","where id="+topicid);
		//删除主题附件
		DALBase.delete("attachement", MessageFormat.format(
				" where belongid=''{0}'' and belongtable=''topic'' ",
				topicid));
		//删除主题帖子
	    List<Topic> list=DALBase.getEntity("topic", "where replyid='"+topicid+"'");
	    
	    for(Topic topic : list)
	    {
	    	DALBase.delete("attachement", MessageFormat.format(
					" where belongid=''{0}'' and belongtable=''topic'' ",
					topic.getId()));
	    	DALBase.delete(topic);
	    }
		
		
		
		
	}

	private void addTopic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String pubren = request.getParameter("pubren");
		String pubtime = request.getParameter("pubtime");

		String dcontent = request.getParameter("dcontent");
		String bkid = request.getParameter("bkid");
		String bkname = request.getParameter("bkname");
		String istopic = request.getParameter("istopic");
		String replyid = request.getParameter("replyid");
		
		SimpleDateFormat sdftopic = new SimpleDateFormat("yyyy-MM-dd");
		Topic topic = new Topic();
		topic.setTitle(title);
		topic.setPubren(pubren);
		
		topic.setPubtime(new Date());
		
		topic.setClickcount(0);
		topic.setReplycount(0);
		topic.setLastreplyor("");
		topic.setLastreplyor("");
		topic.setDcontent(dcontent);
		topic.setBkname(bkname);
		topic.setIstopic(istopic);
		topic.setReplyid(replyid);
		topic.setBkid(bkid);
		DALBase.save(topic);
		attachements(request, response, new Integer(topic.getId()).toString());
		
		String reurl=request.getParameter("reurl");
		try {
			response.sendRedirect(reurl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private void attachements(HttpServletRequest request,
			HttpServletResponse response, String newsid) {
		DALBase.delete("attachement", MessageFormat.format(
				" where belongid=''{0}'' and belongtable=''topic'' ", newsid));
		String[] photos = request.getParameterValues("fileuploaded");
		String id = request.getParameter("id");
		if (photos == null)
			return;
		for (int i = 0; i < photos.length; i++) {
			Attachement a = new Attachement();
			a.setType("images");
			a.setPubtime(new Date());
			a.setBelongfileldname("id");
			a.setFilename(photos[i]);
			a.setBelongid(newsid);
			a.setBelongtable("topic");
			a.setUrl(SystemParam.getSiteRoot() + "/upload/temp/"
					+ a.getFilename());
			a.setTitle(a.getFilename());
			DALBase.save(a);
		}
	}

	private void getComboJson(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		BLLBase<Topic> bll = new BLLBase<Topic>();
		response
				.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		String bindcolumnname = request.getParameter("bindcolumnname");
		try {
			response.getWriter().write(
					bll.getComboJson("topic", "", bindcolumnname,
							bindcolumnname));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getSerialno(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddHHMMss");
		String timestamp = sdf.format(new Date());
		String prefix = request.getParameter("prefix");
		String Serialno = prefix == null ? "" : prefix + timestamp;
		response
				.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		try {
			response.getWriter().write(Serialno);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void updateTopic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		if (id == null)
			return;
		Topic topic = (Topic) DALBase.load(Topic.class, new Integer(id));
		if (topic == null)
			return;
		String title = request.getParameter("title");
		String pubren = request.getParameter("pubren");
		String pubtime = request.getParameter("pubtime");
		String clickcount = request.getParameter("clickcount");
		String replycount = request.getParameter("replycount");
		String lastreplyor = request.getParameter("lastreplyor");
		String lastreplytime = request.getParameter("lastreplytime");
		String dcontent = request.getParameter("dcontent");
		String bkname = request.getParameter("bkname");
		String istopic = request.getParameter("istopic");
		String replyid = request.getParameter("replyid");
		SimpleDateFormat sdftopic = new SimpleDateFormat("yyyy-MM-dd");
		topic.setTitle(title);
		topic.setPubren(pubren);
		try {
			topic.setPubtime(sdftopic.parse(pubtime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		topic.setClickcount(new Integer(clickcount));
		topic.setReplycount(new Integer(replycount));
		topic.setLastreplyor(lastreplyor);
		topic.setLastreplytime(lastreplytime);
		topic.setDcontent(dcontent);
		topic.setBkname(bkname);
		topic.setIstopic(istopic);
		topic.setReplyid(replyid);
		DALBase.update(topic);
		attachements(request, response, new Integer(topic.getId()).toString());
	}

	private void loadTopic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		if (request.getParameter("command").equals("update")) {
			String id = request.getParameter("id");
			Topic topic = (Topic) DALBase.load(Topic.class, new Integer(id));
			request.setAttribute("topic", topic);
		}
		request.setAttribute("command", request.getParameter("command"));
		try {
			request.getRequestDispatcher("topicadd.jsp").forward(request,
					response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getTopics(HttpServletRequest request,
			HttpServletResponse response) {
		String filter = request.getParameter("filter");
		if (filter != null) {
			try {
				filter = new String(filter.getBytes("ISO8859-1"), "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			filter = "";
		}
		BLLBase bll = new BLLBase();
		String json = bll.getGridJson("topic", filter);
		System.out.println("获取帖子JSON数据:json" + json);
		response
				.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

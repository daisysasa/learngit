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
public class ReplysAction extends  Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		     String actiontype=request.getParameter("actiontype");
		     if(actiontype.equals("getReplyss")){
		    	getReplyss(request,response) ;
		     }
		     if(actiontype.equals("loadReplys")){
		    	loadReplys(mapping,form,request,response);
		     }
		     if(actiontype.equals("operOver")){
		    	 if(request.getParameter("command").equals("update"))
		    	   updateReplys(mapping,form,request,response);
		    	 if(request.getParameter("command").equals("add"))
		    		addReplys(mapping,form,request,response);
		     }
		     if(actiontype.equals("delete")){
		    	   deleteReplys(mapping ,form,request,response);
		     }
		     if(actiontype.equals("getComboJson")){
		    	getComboJson(mapping,form,request,response);
		     }
		      if(actiontype.equals("getSerialno")){
		    	getSerialno(mapping,form,request,response);
		     }
		     return null;
    }
	private void deleteReplys(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String id=request.getParameter("id");
		DALBase.delete("replys", " where id="+id);
	}
	private void addReplys(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
				        String title=request.getParameter("title");
				        String dcontent=request.getParameter("dcontent");
				        String replyren=request.getParameter("replyren");
				        String replytime=request.getParameter("replytime");
				        String tablename=request.getParameter("tablename");
				        String belongid=request.getParameter("belongid");
		     SimpleDateFormat  sdfreplys=new SimpleDateFormat("yyyy-MM-dd");
             Replys replys=new Replys();
				        replys.setTitle(title);
				        replys.setDcontent(dcontent);
				        replys.setReplyren(replyren);
						        try {
									replys.setReplytime(sdfreplys.parse(replytime));
								} catch (ParseException e) {
									e.printStackTrace();
								}
				        replys.setTablename(tablename);
				        replys.setBelongid(belongid);
		    DALBase.save(replys);
		    attachements(request,response,new Integer(replys.getId()).toString());
	}
	private void attachements(HttpServletRequest request,
			HttpServletResponse response, String newsid) {
		DALBase.delete("attachement", MessageFormat.format(
				" where belongid=''{0}'' and belongtable=''replys'' ", newsid));
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
			a.setBelongtable("replys");
			a.setUrl(SystemParam.getSiteRoot()+"/upload/temp/" + a.getFilename());
			a.setTitle(a.getFilename());
			DALBase.save(a);
		}
	}
	private void getComboJson(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		    BLLBase<Replys> bll=new BLLBase<Replys>();
		    response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		    String bindcolumnname=request.getParameter("bindcolumnname");
			  try {
				response.getWriter().write(bll.getComboJson("replys", "", bindcolumnname, bindcolumnname));
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	private void getSerialno(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddHHMMss");
		String timestamp = sdf.format(new Date());
		String prefix=request.getParameter("prefix");
		String Serialno = prefix==null?"":prefix+timestamp;
		response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		  try {
			response.getWriter().write(Serialno);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void updateReplys(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		   String id=request.getParameter("id");
		   if(id==null)
			   return;
		   Replys  replys=(Replys)DALBase.load(Replys.class,new Integer(id));
		    if(replys==null)
			   return;
				       String title=request.getParameter("title");
				       String dcontent=request.getParameter("dcontent");
				       String replyren=request.getParameter("replyren");
				       String replytime=request.getParameter("replytime");
				       String tablename=request.getParameter("tablename");
				       String belongid=request.getParameter("belongid");
			  SimpleDateFormat  sdfreplys=new SimpleDateFormat("yyyy-MM-dd");
					        replys.setTitle(title);
					        replys.setDcontent(dcontent);
					        replys.setReplyren(replyren);
						        try {
									replys.setReplytime(sdfreplys.parse(replytime));
								} catch (ParseException e) {
									e.printStackTrace();
								}
					        replys.setTablename(tablename);
					        replys.setBelongid(belongid);
		    DALBase.update(replys);
		    attachements(request,response,new Integer(replys.getId()).toString());
	}
	private void loadReplys(ActionMapping mapping, ActionForm form,HttpServletRequest request,
			HttpServletResponse response) {
		    if(request.getParameter("command").equals("update")){
		    	String id=request.getParameter("id");
		    	 Replys  replys=(Replys)DALBase.load(Replys.class,new Integer(id));
			    request.setAttribute("replys", replys);
			}
		    request.setAttribute("command", request.getParameter("command"));
		    try {
				request.getRequestDispatcher("replysadd.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public void   getReplyss(HttpServletRequest request,HttpServletResponse response){
		 String filter=request.getParameter("filter");
			if(filter!=null){
				try {
					filter = new String( filter.getBytes("ISO8859-1"),"UTF-8");
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else {
				filter="";
			}
		  BLLBase bll=new BLLBase();
		  String json=bll.getGridJson("replys", filter);	 
		  System.out.println("获取回复JSON数据:json"+json);
		  response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		  try {
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

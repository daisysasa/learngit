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

import com.xs.bll.RoleBLL;
import com.xs.dal.DALBase;
import com.xs.entity.Role;
import com.xs.util.DaowenUIHelper;
import com.xs.util.ExtHelper;
import com.xs.util.HibernateSessionFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;


public class RoleAction extends  Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		  
		     String actiontype=request.getParameter("actiontype");
		     System.out.println("actiontype="+actiontype);
		     if(actiontype.equals("getRole")){
		    	getRoles(request,response) ;
		     }
		     if(actiontype.equals("loadRole")){
		    	loadRole(mapping,form,request,response);
		     }
		     if(actiontype.equals("operOver")){
		    	 if(request.getParameter("command").equals("update"))
		    	   updateRole(mapping,form,request,response);
		    	 if(request.getParameter("command").equals("add"))
		    		addRole(mapping,form,request,response);
		     }
		     if(actiontype.equals("delete")){
		    	 
		    	   deleteRole(mapping ,form,request,response);
		    	 
		     }
		     
		     if(actiontype.equals("getComboJson")){
		    	   getComboJson(mapping,form,request,response);
		     }
		     
		     if(actiontype.equals("getDefaultRole")){
		    	 
		    	 getDefaultRole(mapping,form,request,response);
		    	 
		     }
		    
		     return null;
		
		
    }
	
	
	
	private  void   getDefaultRole(ActionMapping mapping,ActionForm form ,HttpServletRequest request,HttpServletResponse response)
	{
		  String filter=request.getParameter("filter");
		  List<Role> list=DALBase.getTopList("role", "", 1);	 
		  String rolename="";
		  
		     if(!list.isEmpty())
		    	   rolename=list.get(0).getRolename();
		     
		  System.out.println("default rolename="+rolename);
		  response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		  try {
			response.getWriter().write(rolename);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	private void getComboJson(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		    RoleBLL rolebll=new RoleBLL();
		    response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
			  try {
				response.getWriter().write(rolebll.getComboJson("role", "", "rolename", "rolename"));
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		
	}

	private void deleteRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String rolename="";
		try {
			rolename = new String(request.getParameter("rolename").getBytes("iso-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DALBase.delete("role", " where rolename='"+rolename+"'");
		DALBase.delete("acl","where rolename='"+rolename+"'");
	}

	private void addRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		    String rolename=request.getParameter("rolename");
		    Role r=new Role();
		    r.setRolename(rolename);
		    r.setDescription(request.getParameter("description"));
		    DALBase.save(r);
		    
		    /*request.setAttribute("message", "添加角色成功");
		    try {
				request.getRequestDispatcher("tips.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
	}

	private void updateRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		  

		    String rolename=request.getParameter("rolename");
		    Role r=(Role)DALBase.load(Role.class, rolename);
		    r.setDescription(request.getParameter("description"));
		    DALBase.update(r);
		    request.setAttribute("message", "��ɫ ��Ϣ���³ɹ�");
		    try {
				request.getRequestDispatcher("tips.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	private void loadRole(ActionMapping mapping, ActionForm form,HttpServletRequest request,
			HttpServletResponse response) {
	
		    if(request.getParameter("command").equals("update")){
			    String rolename="";
				try {
					System.out.println(request.getParameter("rolename"));
					rolename = new String(request.getParameter("rolename").getBytes("iso-8859-1"),"UTF-8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    System.out.println("rolename="+rolename);
			    
			    Role r=(Role)DALBase.load(Role.class, rolename);

			    request.setAttribute("role", r);
			}
		    
		   
		    request.setAttribute("command", request.getParameter("command"));
		    try {
				request.getRequestDispatcher("roleadd.jsp").forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	public void   getRoles(HttpServletRequest request,HttpServletResponse response){

		  String filter=request.getParameter("filter");
		  if(filter!=null)
		  {
			  try {
				filter=new String(filter.getBytes("ISO8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch blo-ck
				e1.printStackTrace();
			}
		  }
		  String json=DALBase.getJson("role", filter);	 
		  System.out.println(":json"+json);
		  response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		  try {
			response.getWriter().write(json);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
			
	}
}

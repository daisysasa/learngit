package com.xs.action;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

//****����������

public abstract class PageActionBase extends ActionSupport{
	
    public HttpServletResponse response;
    
    public HttpServletRequest  request;
    
	public abstract  void save(); 
	
	public abstract  void update(); 
	
	public abstract  void delete(); 
	
	public abstract  void binding(); 
	
	public abstract  void load(); 
	
	
	
	@Override
	public String execute(){
		response = (HttpServletResponse)ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
		
		request = (HttpServletRequest)ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		
		String actiontype = request.getParameter("actiontype");
		
		if (actiontype == null)
			return null;
		// ����ǻ�ȡ������
		if (actiontype.equals("get") || actiontype.equals("search")) {
			binding();
		}
		// ����
		if (actiontype.equals("load")) {
			load();
		}
		// �������
		if (actiontype.equals("save")) {
			save();
		}
		if (actiontype.equals("delete")) {
			delete();
		}
		// ���в���
		if (actiontype.equals("update")) {
			update();
		}
		onLoad();
		
		return null;
	}
	public void  onLoad(){
		
	}
	
	
	/***********************************
	 * *********************************
	 * �ַ��������
	 
	 */
	public  void  dispatchParams(HttpServletRequest request,HttpServletResponse response){
		Enumeration  params=request.getParameterNames();
		while (params.hasMoreElements())
		{
		   String paramname=params.nextElement().toString();
		   String value=request.getParameter(paramname);
		   request.setAttribute(paramname, value);
	   
		   
		}
	}
	
	
	
	
	

	
}

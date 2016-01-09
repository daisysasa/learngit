package com.xs.webcontrol;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.Iterator;

import javassist.bytecode.Opcode;

import javax.activation.DataSource;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.xs.util.NameUtil;


public class DropdownList  extends BodyTagSupport{

	
	 private String id;
	 
	 
	 
	 private String name;
	/***********
	 * ������Դ
	 */
	private   Object  datasource;
	
	
	/***
	 * ������
	 */
	private String textfieldname;
	
	
	
	/**
	 * ��ֵ��
	 * */
	private String valuefieldname;
	
	/*****
	 * ��ʽ����
	 */
	private  String cssclass;
	
	/***
	 * ��ʽ
	 */
	private  String style;
	
	
	private String  value;
	
	
	
	@Override
	public int doStartTag(){
		
      StringBuffer sb=new StringBuffer();
	  String controlname="";
	  
	  if(name!=null)
		   controlname="name=\""+name+"\"";
	  sb.append(MessageFormat.format("<select {0} id=\"{1}\" >",controlname,id));
	 
	  JspWriter out=pageContext.getOut();
	  
	  
	  try {
		out.write(sb.toString());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	   return  EVAL_BODY_INCLUDE;
	}
	
	@Override
	
	public  int doAfterBody()
	{
		
		
		if(datasource==null)
			
			return SKIP_BODY;
		
		JspWriter writer=pageContext.getOut();
		 
		StringBuffer sb=new StringBuffer();
		
		sb.append(getDsRender());
		
		try {
			writer.write(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return SKIP_BODY;
	}
	

	@Override
	public int doEndTag() {
	    
		
	      StringBuffer sb=new StringBuffer();
			
		  sb.append("</select >");
		 
		  JspWriter out=pageContext.getOut();
		  
		  
		  try {
			out.write(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    return EVAL_PAGE;
	}
	
	
	/*****
	 * ��ȡ�ؼ�����
	 * @return
	 */
	private String getDsRender(){
		
		StringBuffer sb=new StringBuffer();
		
		
			
		//��ȡ����Դ
		Iterable e=(Iterable)datasource;
		
		if(e==null)
			return "";
		
		//�������Դ��Ϊ��
		if(e!=null){
			Iterator it=e.iterator();
			//������¼��
			while (it.hasNext()){
				
				//��ȡ���ݼ�Ԫ��
				Object object=it.next();
				String optiontext="";
				String optionvalue="";
				try {
					optiontext=object.getClass().getMethod("get"+NameUtil.toFirstUpper(getTextfieldname()), null).invoke(object, null).toString();
					optionvalue=object.getClass().getMethod("get"+NameUtil.toFirstUpper(getValuefieldname()), null).invoke(object, null).toString();
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SecurityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchMethodException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String optionvaluefield="";
				if(optionvalue!=null){
					optionvaluefield=" value=\""+optionvalue+"\"";
				}
				String optionselectfield="";
				
				if(value!=null&&value.equals(optionvalue))
					optionselectfield=" selected=\"selected\"";
				
				sb.append(MessageFormat.format("<option  {1}  {2}>{0}</option>",optiontext,optionvaluefield,optionselectfield));
				
				
			}
		}
	    
		
		return sb.toString();
	}
	
	    
	public void setDatasource(Object datasource) {
		

		
		this.datasource=datasource;
//	    try {
//		  
//			this.datasource=ExpressionEvaluatorManager.evaluate("datasource", dsname.toString(), Object.class, this, pageContext);
//			
//			 
//		} catch (JspException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
	}



	public String getTextfieldname() {
		return textfieldname;
	}



	public void setTextfieldname(String textfieldname) {
		this.textfieldname = textfieldname;
	}



	public String getValuefieldname() {
		return valuefieldname;
	}



	public void setValuefieldname(String valuefieldname) {
		this.valuefieldname = valuefieldname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Object getDatasource() {
		return datasource;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCssclass() {
		return cssclass;
	}

	public void setCssclass(String cssclass) {
		this.cssclass = cssclass;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

    
    
	
	
}

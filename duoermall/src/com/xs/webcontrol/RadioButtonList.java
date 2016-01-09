package com.xs.webcontrol;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.Iterator;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.xs.util.NameUtil;

public class RadioButtonList extends BodyTagSupport {

	private String id;

	private String name;
	/***************************************************************************
	 * ������Դ
	 */
	private Object datasource;

	/***************************************************************************
	 * ������
	 */
	private String textfieldname;

	/**
	 * ��ֵ��
	 */
	private String valuefieldname;

	/***************************************************************************
	 * ��ʽ����
	 */
	private String cssclass;

	/***************************************************************************
	 * ��ʽ
	 */
	private String style;

	private String value;

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

	public int doStartTag() {

		StringBuffer sb = new StringBuffer();
		String controlname = "";

		if (name != null)
			controlname = "name=\"" + name + "\"";
		sb.append(MessageFormat.format("<div {0} id=\"{1}\">",
						controlname, id));

		JspWriter out = pageContext.getOut();

		try {
			out.write(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return EVAL_BODY_INCLUDE;
	}

	public int doAfterBody() {
		
		if (datasource == null)

			return SKIP_BODY;

		JspWriter writer = pageContext.getOut();

		StringBuffer sb = new StringBuffer();

		sb.append(getDsRender());

		try {
			writer.write(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return SKIP_BODY;
	}

	private String getDsRender() {
		
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
				String itemtext="���ƶ���text����";
				String itemvalue="���ƶ���value����";
				try {
				    if(valuefieldname!=null&&textfieldname!=null){
					   itemtext=object.getClass().getMethod("get"+NameUtil.toFirstUpper(getTextfieldname()), null).invoke(object, null).toString();
					   itemvalue=object.getClass().getMethod("get"+NameUtil.toFirstUpper(getValuefieldname()), null).invoke(object, null).toString();
				    }
				} catch (Exception e1) {
					
					e1.printStackTrace();
				} 
			
				//����radiobutton
				sb.append(buildRadiobutton(itemtext, itemvalue));
				
			}//end while
		}
	    
		
		return sb.toString();
		
		
	}
	
	private String buildRadiobutton(String text,String value){
		
		StringBuffer sb=new StringBuffer();
		
		//ֵ����
		String valueattr="";
		if(valueattr!=null){
			valueattr=" value=\""+value+"\"";
		}
		//check����
		String checkattr="";
		
		if(this.value!=null&&this.value.equals(value))
			checkattr=" checked=\"checked\"";
	    String nameattr="";
	    
	    if(name!=null)
			nameattr=" name=\""+name+"\"";
		
		sb.append(MessageFormat.format("<input {0} type=\"radio\"  {1}  {2} />{3}",nameattr,valueattr,checkattr,text));
		
		
		
		return sb.toString();
		
		
	}

	public int doEndTag() {

		StringBuffer sb = new StringBuffer();

		sb.append("</div>");

		JspWriter out = pageContext.getOut();

		try {
			out.write(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return EVAL_PAGE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getDatasource() {
		return datasource;
	}

	public void setDatasource(Object datasource) {
		this.datasource = datasource;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}

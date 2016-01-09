package com.xs.webcontrol;

import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

/*******************************************************************************
 * 
 * �б�ѡ�
 * 
 */
public class ListItem extends BodyTagSupport {

	private String value;

	/***************************************************************************
	 * ��ǰ���ؼ��Ƿ��������Դ
	 * 
	 * @return �󶨷����� ������Ϊ��
	 * 
	 */
	public ListItem(){
		
	}
	private Boolean hasBindingDatasource() {

	   Object parent=getParent();
	   
	   if(parent==null)
		   return false;
	   
	   String pclassname = getParent().getClass().getName();
	   
	   if(pclassname.equals("com.daowen.webcontrol.DropdownList"))
	   {
		   if(((DropdownList)getParent()).getDatasource()==null)
			     return false;
			else 
				return true;
		   
	   }
	   if(pclassname.equals("com.daowen.webcontrol.RadioButtonList"))
	   {
		   if(((RadioButtonList)getParent()).getDatasource()==null)
			     return false;
			else 
				return true;
		   
	   }
		 
		   
		   
		return false;
	}

	@Override
	public int doStartTag() {

		if (getParent() == null) {
			try {
				pageContext.getOut().write("");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return SKIP_BODY;
		}
		return EVAL_BODY_BUFFERED;
	}

	@Override
	public void setBodyContent(BodyContent bc) {
		super.setBodyContent(bc);

	}

	@Override
	public int doAfterBody() throws JspException {

		if (getParent() == null)
			return SKIP_BODY;
		
		String bodycontent="";
		
		JspWriter out=getBodyContent().getEnclosingWriter();
		// ���ؼ�����
		String pclassname = getParent().getClass().getName();

		System.out.println("parent control name=" + pclassname);

		// ������ؼ��Ѿ�������Դ����ô��ǰ�ؼ��������������
		if (hasBindingDatasource())

			return SKIP_BODY;
		//���ݸ��ؼ������ͽ��г�������ѡ��
		if(pclassname.equals("com.daowen.webcontrol.DropdownList"))
			
			bodycontent=buildDropdownlistItems();
		
		if(pclassname.equals("com.daowen.webcontrol.RadioButtonList"))
		{
			String temtext=getBodyContent().getString();
			String temvalue=value==null?temtext:value;
		    bodycontent=buildRadioButtonItem(temtext, temvalue);
		}
		try {
			
			out.write(bodycontent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return SKIP_BODY;

		

	}
	
	
	/************
	 * ��ʾDropdownList �ؼ�ѡ��
	 */
	private String buildDropdownlistItems(){
		
		BodyContent bc = getBodyContent();

	
		StringBuffer sb = new StringBuffer();
		
		DropdownList parent=(DropdownList)getParent();
		
		String parentvalue = parent.getValue();
		
		
        String optionvalue="";
        String temvalue=value;
        
        
		if (temvalue != null)
           
			optionvalue = "value=\"" + temvalue + "\"";
		else
		{
			temvalue=bc.getString();
			optionvalue = "value=\"" + temvalue + "\"";
		}
		
		String select="";
		//�����ǰѡ�Ϊ�ؼ�ֵ������ѡ��
		if(temvalue.equals(parentvalue))
			
			select=" selected=\"selected\" ";
		
		sb.append(MessageFormat.format("<option {1} {2} >{0}</option>", bc
				.getString(), optionvalue,select));

		return sb.toString();

		
	}
private String buildRadioButtonItem(String text,String value){
		
	StringBuffer sb=new StringBuffer();
	
	RadioButtonList rbl=(RadioButtonList)getParent();
	if(rbl==null)
		return "";
	
	String name=rbl.getName();
	
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

	@Override
	public int doEndTag() {
		return EVAL_PAGE;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}

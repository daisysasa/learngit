package com.xs.bll;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;

import com.xs.dal.DALBase;
import com.xs.entity.Leaveword;

public class LeavewordBLL {
	
	public static String getLeaveword(){
		
		StringBuffer sb=new StringBuffer();
		sb.append("<ul >");
		List list=DALBase.getEntity("leaveword", "");
		
		for(Iterator<Leaveword> it=list.iterator();it.hasNext();)
		{
		         Leaveword c=it.next();
		         sb.append("<li>");
		         sb.append("<div class='title'>");
		         sb.append(c.getTitle());
		         sb.append("</div>");
		         sb.append("<div class='content'>");
		         sb.append(c.getDcontent());
		         sb.append("����ʱ��:"+c.getPubtime().toLocaleString());
		         sb.append("������:"+c.getReplyren());
		         
		         sb.append("</div>");
		         if(c.getStatus()==1)
		         {
			         sb.append("<div class='reply'>");
			         sb.append(MessageFormat.format("<div>�ظ����ݮ�{0}</div>", c.getReplycontent()));
			         sb.append(MessageFormat.format("<span>�ظ�ʱ��{0}</span>", c.getReplytime().toLocaleString()));
			         sb.append(MessageFormat.format("<span>�ظ��ˣ�{0}</span>", c.getReplyren()));
			         sb.append("</div>");
		         }
		         
		         sb.append("</li>");
		        
		
		}
		sb.append("</ul>");
		
		return sb.toString();
		
		
		
	}
	

}

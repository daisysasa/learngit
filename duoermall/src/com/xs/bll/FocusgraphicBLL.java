package com.xs.bll;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;

import com.xs.dal.DALBase;
import com.xs.entity.Jiaodiantu;
import com.xs.entity.Notice;

public class FocusgraphicBLL {

	public static String getFocusgraphic()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("<div id=\"slide_c\">");

		List<Jiaodiantu> list = DALBase.getTopList("jiaodiantu", "", 10);
		
		for (Iterator<Jiaodiantu> it = list.iterator(); it.hasNext();) {
			Jiaodiantu n = it.next();
			sb.append(MessageFormat.format(
					"<a target=\"_blank\" href=''{0}''><img alt=\"\" title=\"\" src=\"{1}\"></a>",
					n.getHref(), n.getTupian()));
			sb.append("\r\n");
		}
		sb.append(" <div class=\"ico_c\">");
		sb.append("\r\n");
		sb.append("</div>");
		sb.append("\r\n");
		sb.append("</div>");
		
		return sb.toString();
		
	}
	
}

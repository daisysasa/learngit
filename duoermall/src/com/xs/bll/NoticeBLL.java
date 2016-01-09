package com.xs.bll;

import java.text.MessageFormat;
import java.util.*;

import com.xs.dal.DALBase;
import com.xs.entity.*;

public class NoticeBLL {
	public static String getNotice() {
		StringBuffer sb = new StringBuffer();

		List<Notice> list = DALBase.getTopList("notice", "", 10);
		sb.append("<ul>");
		sb.append("\r\n");
		for (Iterator<Notice> it = list.iterator(); it.hasNext();) {
			Notice n = it.next();
			sb.append(MessageFormat.format(
					"<li ><a href=''noticedetails.jsp?id={0}''>{1}</a></li>",
					n.getId(), n.getTitle()));
			sb.append("\r\n");
		}
		sb.append("\r\n");
		sb.append("</ul>");
		return sb.toString();

	}

}
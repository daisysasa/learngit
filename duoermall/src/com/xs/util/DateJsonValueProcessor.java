package com.xs.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;



import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DateJsonValueProcessor implements JsonValueProcessor {

	/** 
     * ��ĸ ���ڻ�ʱ��Ԫ�� ��ʾ ʾ�� <br> 
     * G Era ��־�� Text AD <br> 
     * y �� Year 1996; 96 <br> 
     * M ���е��·� Month July; Jul; 07 <br> 
     * w ���е����� Number 27 <br> 
     * W �·��е����� Number 2 <br> 
     * D ���е����� Number 189 <br> 
     * d �·��е����� Number 10 <br> 
     * F �·��е����� Number 2 <br> 
     * E �����е����� Text Tuesday; Tue<br> 
     * a Am/pm ��� Text PM <br> 
     * H һ���е�Сʱ����0-23�� Number 0 <br> 
     * k һ���е�Сʱ����1-24�� Number 24<br> 
     * K am/pm �е�Сʱ����0-11�� Number 0 <br> 
     * h am/pm �е�Сʱ����1-12�� Number 12 <br> 
     * m Сʱ�еķ����� Number 30 <br> 
     * s �����е����� Number 55 <br> 
     * S ������ Number 978 <br> 
     * z ʱ�� General time zone Pacific Standard Time; PST; GMT-08:00 <br> 
     * Z ʱ�� RFC 822 time zone -0800 <br> 
     */  
    public static final String Default_DATE_PATTERN = "yyyy-MM-dd";  
    private DateFormat dateFormat;  
  
    /** 
     *  
     */  
    public DateJsonValueProcessor(String datePattern) {  
        try {  
            dateFormat = new SimpleDateFormat(datePattern);  
        } catch (Exception e) {  
            dateFormat = new SimpleDateFormat(Default_DATE_PATTERN);   
        }  
    }  
  
    /* 
     * (non-Javadoc) 
     * @see 
     * net.sf.json.processors.JsonValueProcessor#processArrayValue(java.lang 
     * .Object, net.sf.json.JsonConfig) 
     */  
    @Override
    public Object processArrayValue(Object value, JsonConfig jsonConfig) {  
        return process(value);  
    }  
  
    /* 
     * (non-Javadoc) 
     * @see 
     * net.sf.json.processors.JsonValueProcessor#processObjectValue(java.lang 
     * .String, java.lang.Object, net.sf.json.JsonConfig) 
     */  
    @Override
    public Object processObjectValue(String key, Object value,JsonConfig jsonConfig) {  
        return process(value);  
    }  
  
    private Object process(Object value) {  
        if (value == null) {  
            return "";  
        } else {  
            return dateFormat.format((Timestamp) value);  
        }  
    }  
	
}

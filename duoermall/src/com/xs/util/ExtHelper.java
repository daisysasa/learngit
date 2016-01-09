package com.xs.util;


import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
/**
 * Title: Ext JS ������
 * Description: ��������ת��java����ΪXML�ļ���ʽ��JSON�ļ���ʽ
 * @author weijun
 * 
 */
public class ExtHelper {
	/**
	 * ͨ��List����XML����
	 * @param recordTotal ��¼��������һ����beanList�еļ�¼�����
	 * @param beanList ����bean����ļ���
	 * @return ���ɵ�XML����
	 */
	public static String getXmlFromList(long recordTotal , List beanList) {
		Total total = new Total();
		total.setResults(recordTotal);
		List results = new ArrayList();
		results.add(total);
		results.addAll(beanList);
		XStream sm = new XStream(new DomDriver());
		for (int i = 0; i < results.size(); i++) {
			Class c = results.get(i).getClass();
			String b = c.getName();
			String[] temp = b.split("\\.");
			sm.alias(temp[temp.length - 1], c);
		}
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"	+ sm.toXML(results);
		return xml;
	}
	/**
	 * ͨ��List����XML����
	 * @param beanList ����bean����ļ���
	 * @return ���ɵ�XML����
	 */
	public static String getXmlFromList(List beanList){
		return getXmlFromList(beanList.size(),beanList);
	}
	/**
	 * ͨ��List����JSON����
	 * @param recordTotal ��¼��������һ����beanList�еļ�¼�����
	 * @param beanList ����bean����ļ���
	 * @return ���ɵ�JSON����
	 */
	public static String getJsonFromList(long recordTotal , List beanList){
		TotalJson total = new TotalJson();
		total.setResults(recordTotal);
		total.setItems(beanList);
		JSONObject JsonObject = JSONObject.fromObject(total);
		return JsonObject.toString();
	}
	/**
	 * ͨ��List����JSON����
	 * @param beanList ����bean����ļ���
	 * @return ���ɵ�JSON����
	 */
	public static String getJsonFromList(List beanList){
		return getJsonFromList(beanList.size(),beanList);
	}
	/**
	 * ͨ��bean����JSON����
	 * @param bean bean����
	 * @return ���ɵ�JSON����
	 */
	public static String getJsonFromBean(Object bean){
		JSONObject JsonObject = JSONObject.fromObject(bean);
		return JsonObject.toString();
	}
}
package com.xs.webcontrol;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.xs.entity.Users;
import com.xs.util.NameUtil;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Collection userlist=new ArrayList<Users>();
	     Users u=new com.xs.entity.Users();
	     u.setUsername("u1");
	     u.setEmail("e1");
	     userlist.add(u);
	     
	     
	     Users u2=new Users();
	     u2.setUsername("u2");
	     u2.setEmail("e2");
	     userlist.add(u2);
	     
	     Iterable e=(Iterable)userlist;
			//�������Դ��Ϊ��
			if(e!=null){
				Iterator it=e.iterator();
				//������¼��
				while (it.hasNext()){
					
					//��ȡ���ݼ�Ԫ��
					Object object=it.next();
					String text="";
					String value="";
					try {
						text=object.getClass().getMethod("getUsername", null).invoke(object, null).toString();
						System.out.println("text="+text);
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
					
					
					
					
				}
			}
		    
		

	}

}

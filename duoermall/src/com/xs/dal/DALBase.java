package com.xs.dal;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.xs.bll.SystemParam;
import com.xs.entity.Attachement;
import com.xs.util.DaowenUIHelper;
import com.xs.util.HibernateSessionFactory;
import com.xs.util.NameUtil;



/**
 * 
 * @author Daowen
 * 
 * 
 * 
 * ***/

public class DALBase {
	
	 
	
	
	 public static int  getMaxIndex(String tablename ,String pk) {

			
		
		    Session s=	HibernateSessionFactory.getSession();
		    
			Transaction t= s.beginTransaction();
		    Statement st=null;
			try {
				st = s.connection().createStatement();
			} catch (HibernateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    ResultSet rs=null;
			try {
				rs = st.executeQuery(String.format("select max(%s) max from %s",pk,tablename));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
			t.commit();
			s.close();
			String max="0";
			try {
				if(rs.next())
					try {
						
						max=rs.getString("max");
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	        System.out.println("maxindex="+max);
	        if(max==null)
	        	return 0;
	        else
	          return new Integer(max) ;
			
		}
    
	 /**@author daowen
	  * 
	  * @param  tablename 
	  * @Param  filter  
	  * 
	  * ***/
	 public static List getEntity(String tablename,String filter){
		     
		     Session s=HibernateSessionFactory.getSession();
		     Transaction t=s.beginTransaction();
		     String classname=NameUtil.toFirstUpper(tablename);
		     String HQL=" from "+classname+" "+ (filter==null?"":filter);
		     System.out.println("HQL="+HQL);
		     List list=null;
		     try 
		     {
		    	 
			     
			     Query q=s.createQuery(HQL);
			     list= q.list();
			     t.commit();
			     s.close();
		     }catch(HibernateException e){
		    	 e.printStackTrace();
		    	System.out.print( e.getMessage());
		     }
		     return list;
		
	  }
	
	 
	
	  /*******
	   * @param  tablename  
	   * @param  filter 
	  
	   * *******/
	  public static String getJson(String tablename,String filter){
		  
		    return DaowenUIHelper.getJsonFromList(getEntity(tablename,filter));
	  }
	  
	  public static Boolean save(Object o){
		  
		    Session s=HibernateSessionFactory.getSession();
		    Transaction t=s.beginTransaction();
		    boolean status=true;
		    try 
		    {
			    s.save(o);
			   
			    s.flush();
			    
			    t.commit();
			    s.close();
		    }catch(HibernateException e){
		    	
		    	e.printStackTrace();
		        
		    	status=false;
		    }
		    return status;
		    
	  }
	  
	  public static Boolean update(Object o){
		  
		    Session s=HibernateSessionFactory.getSession();
		    Transaction t=s.beginTransaction();
		    boolean status=true;
		    try 
		    {
			    s.update(o);
			   
			    s.flush();
			    
			    t.commit();
			    s.close();
		    }catch(HibernateException e){
		    	e.printStackTrace();
		    	status=false;
		    }
		    return status;
		    
	  }
	  /****/
	  public static Boolean delete(Object o){
		  
		    Session s=HibernateSessionFactory.getSession();
		    Transaction t=s.beginTransaction();
		    
		    
		    boolean status=true;
		    try 
		    {
			    s.delete(o);
			    t.commit();
			    s.close();
		    }catch(HibernateException e){
		    	status=false;
		    }
		    return status;
		    
	  }
	  public static int delete(String tablename,String filter){
		  
		    Session s=HibernateSessionFactory.getSession();
		    Transaction t=s.beginTransaction();
		    String HQL="delete from "+NameUtil.toFirstUpper(tablename)+ (filter==null?"":"  "+filter);
		    System.out.println("HQL ="+HQL);
		    SQLQuery q=s.createSQLQuery(HQL);
		    
		    int i=0;
		    
		  
		    try 
		    {
			    i= q.executeUpdate();
			    t.commit();
			    s.close();
		    }catch(HibernateException e){
		    	e.printStackTrace();
		    	System.out.println(tablename+e.getMessage());
		    	i=-1;
		    }
		    return i;
		    
	  }
	  
	  public static Object load(Class type,Object key){
		  
		  Session s=HibernateSessionFactory.getSession();
		  Transaction t=s.beginTransaction();
		  Object o=null;
		  try{
		    o=s.load(type, (Serializable) key);
		  }catch(HibernateException e){
			  System.out.println(""+e.getMessage());
		  }
		  return o;
		  
	  }
	  
	  public static  Object load(String tablename,String filter){
		  List list=getEntity(tablename,filter);
		  if(list.isEmpty())
			  return null;
		  return list.get(0);
	  }
	  public static Boolean isExist(String tablename,String filter){
		  
		     List list=getEntity(tablename,filter);
		     if(list.isEmpty())
		    	 return false;
		     else
		    	 return true;
		    	 
		    		 
		  
	  }
	  /**
	   * 
	   * @author Daowen
	   * 
	 
	   * 
	   * 
	   */
	  public static List getRecords(String HQL,int pageindex,int pagesize){
		  
          Session session=HibernateSessionFactory.getSession();
		  
		  Transaction  t=session.beginTransaction();
		
		  List list=null;
		  System.out.print("SQL="+HQL);
		  try
		  {
			  Query query=session.createQuery(HQL);
			  System.out.println("first="+((pageindex-1)*pagesize));
			  System.out.println("max="+(pageindex*pagesize-1));
			  list= query.setFirstResult((pageindex-1)*pagesize).setMaxResults(pagesize).list();
		      t.commit();
		  }catch(HibernateException e){
		       e.printStackTrace();
			   System.out.print("系统出现异常"+ e.getMessage());
		 }
	      if(session.isOpen())
	    	 session.close();
		  	  
		  return  list;
		  	  
		  
		  
	  }
	  
	  
	  public static List  getPageEnity(String tablename,String filter,int pageindex,int pagesize){
		  
		  String SQL=MessageFormat.format(" from {0} {1} ",NameUtil.toFirstUpper(tablename),filter);
		  return getRecords(SQL,pageindex,pagesize);
		  
		  
	  }
	  /*****
	   * 
	   * 
	   * 
	   * @param tablename
	   * @param pageindex
	   * @param pagesize
	   * @return
	   */
	  public static List getPageEnity(String tablename,int pageindex,int pagesize){
		  
		  String SQL=MessageFormat.format(" form {0}  ",NameUtil.toFirstUpper(tablename));
		  return getRecords(SQL,pageindex,pagesize);
		  
	  }
	  
	  
	  /**
	   * 
	   * @param tablename
	   * @param filter
	   * @return
	   */
	  public static int getRecordCount(String tablename,String filter){
		 
		  Session session=HibernateSessionFactory.getSession();
		   
		 
		  Transaction t=session.beginTransaction();
		  
		  SQLQuery query=session.createSQLQuery(MessageFormat.format("select count(*) total from {0} {1}",NameUtil.toFirstUpper(tablename),filter));
		  
		  Number count=(Number) query.uniqueResult();
		  
		  t.commit();
		  if(session.isOpen())
		    session.close();
		  
		  
		  return count.intValue();
	  }
	  
	  
	  public static List runNativeSQL(String SQL){
			 
		  Session session=HibernateSessionFactory.getSession();
		   
		 
		  Transaction t=session.beginTransaction();
		  
		  SQLQuery query=session.createSQLQuery(SQL);
		  
		  List list=query.list();
		  t.commit();
		  if(session.isOpen())
		    session.close();
		 
		  return list;
	  }
	  public static List runNativeSQL(String SQL,Class c){
			 
		  Session session=HibernateSessionFactory.getSession();
		   
		 
		  Transaction t=session.beginTransaction();
		  
		  SQLQuery query=session.createSQLQuery(SQL);
		  
		  List list=query.addEntity(c).list();
		  t.commit();
		  if(session.isOpen())
		    session.close();
		 
		  return list;
	  }
	  

	public static List getTopList(String tablename, String filter, int recordcount) {
		  Session s=HibernateSessionFactory.getSession();
		     Transaction t=s.beginTransaction();
		     String classname=NameUtil.toFirstUpper(tablename);
		     String HQL=" from "+classname+" "+ (filter==null?"":filter);
		     System.out.println("顶端HQL="+HQL);
		     List list=null;
		     try 
		     {
		    	 
		     
			     Query q=s.createQuery(HQL);
			     q.setFirstResult(0);
			     q.setMaxResults(recordcount);
			     list= q.list();
			     t.commit();
			     s.close();
		     }catch(HibernateException e){
		    	 e.printStackTrace();
		    	System.out.print( e.getMessage());
		     }
		     return list;
		
	}
	  
	public static String getFirstImage(String tablename,String id) {
		
		Attachement a=(Attachement) load("attachement", " where belongtable='"+tablename+"' and belongid='"+id+"'");
		if(a!=null)
			return a.getUrl();
		else
			
			return SystemParam.getSiteRoot()+"/upload/nopic.jpg";
		
	}
	 public static String getAttachement(String tablename,String title,String linkurl) {

			Session session=HibernateSessionFactory.getSession();
			
			Connection connection=session.connection();
			StringBuffer sb=new StringBuffer();
			PreparedStatement ps=null;
			ResultSet rs =null;
			try {
				
				 String SQL=MessageFormat.format("select distinct t.id,t.{0},a.* from attachement a ,{1} t where belongtable=''{1}'' and t.id=a.belongid  ", title,tablename);
				 System.out.println("����SQL="+SQL);
				 ps=connection.prepareStatement(SQL);
				 
				 rs=ps.executeQuery();
				 while(rs.next()){
					 sb.append("<li>");
					 sb.append(MessageFormat.format("<img src=''{0}''/>", rs.getString("url")));
					 sb.append(MessageFormat.format("<strong><a href=''{0}?id={2}''>{1}</a></strong>", linkurl,rs.getString(title),rs.getInt("id")));
					 sb.append("</li>");
				 }
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(ps!=null)
					try {
						ps.close();
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
				if(connection!=null)
					try {
						connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				  
			}
			return sb.toString();
			
			

		}
	 
	 

	 public static String getImageInfo(String tablename,String title,String imagefieldname,  String linkurl) {

			Session session=HibernateSessionFactory.getSession();
			
			Connection connection=session.connection();
			StringBuffer sb=new StringBuffer();
			PreparedStatement ps=null;
			ResultSet rs =null;
			try {
				
				 String SQL=MessageFormat.format("select * from {0}  ",tablename);
				 System.out.println("����SQL="+SQL);
				 ps=connection.prepareStatement(SQL);
				 
				 rs=ps.executeQuery();
				 while(rs.next()){
					 sb.append("<li>");
					 sb.append(MessageFormat.format("<img src=''{0}''/>", rs.getString(imagefieldname)));
					 sb.append(MessageFormat.format("<strong><a href=''{0}?id={2}''>{1}</a></strong>", linkurl,rs.getString(title),rs.getInt("id")));
					 sb.append("</li>");
				 }
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(ps!=null)
					try {
						ps.close();
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
				if(connection!=null)
					try {
						connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				  
			}
			System.out.print(sb.toString());
			return sb.toString();
			
			

		}
	 
	 
	 
	 
	 public static String getTopImageInfo(String tablename,String title,String imagefieldname,  String linkurl,int topcount) {

			Session session=HibernateSessionFactory.getSession();
			
			Connection connection=session.connection();
			StringBuffer sb=new StringBuffer();
			PreparedStatement ps=null;
			ResultSet rs =null;
			try {
				
				 String SQL=MessageFormat.format("select top {0} * from {1}  ",topcount,tablename);
				 System.out.println("����SQL="+SQL);
				 ps=connection.prepareStatement(SQL);
				 
				 rs=ps.executeQuery();
				 while(rs.next()){
					 sb.append("<li>");
					 sb.append(MessageFormat.format("<img src=''{0}''/>", rs.getString(imagefieldname)));
					 sb.append(MessageFormat.format("<strong><a href=''{0}?id={2}''>{1}</a></strong>", linkurl,rs.getString(title),rs.getInt("id")));
					 sb.append("</li>");
				 }
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(ps!=null)
					try {
						ps.close();
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
				if(connection!=null)
					try {
						connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				  
			}
			System.out.print(sb.toString());
			return sb.toString();
			
			

		}
	 
	 
	
	 
	 public static String getTImageInfoBySQL(String SQL,String title,String imagefieldname,  String linkurl) {

			Session session=HibernateSessionFactory.getSession();
			
			Connection connection=session.connection();
			StringBuffer sb=new StringBuffer();
			PreparedStatement ps=null;
			ResultSet rs =null;
			try {
				
				
				 ps=connection.prepareStatement(SQL);
				 
				 rs=ps.executeQuery();
				 while(rs.next()){
					 sb.append("<li>");
					 sb.append(MessageFormat.format("<img src=''{0}''/>", rs.getString(imagefieldname)));
					 sb.append(MessageFormat.format("<strong><a href=''{0}?id={2}''>{1}</a></strong>", linkurl,rs.getString(title),rs.getInt("id")));
					 sb.append("</li>");
				 }
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(ps!=null)
					try {
						ps.close();
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
				if(connection!=null)
					try {
						connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				  
			}
			System.out.print(sb.toString());
			return sb.toString();
			
			

		}
	 
	 
	 
	
	 
	 
	

}

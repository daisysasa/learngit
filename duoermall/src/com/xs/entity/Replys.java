package com.xs.entity;
import java.util.Date;
import javax.persistence.*;
@Entity
public class Replys
{
@Id
@GeneratedValue(strategy =GenerationType.AUTO)
   private int id ;
   public int getId() 
   {
      return id;
  }
   public void setId(int id) 
   {
      this.id= id;
  }
   private String title ;
   public String getTitle() 
   {
      return title;
  }
   public void setTitle(String title) 
   {
      this.title= title;
  }
   private String dcontent ;
   public String getDcontent() 
   {
      return dcontent;
  }
   public void setDcontent(String dcontent) 
   {
      this.dcontent= dcontent;
  }
   private String replyren ;
   public String getReplyren() 
   {
      return replyren;
  }
   public void setReplyren(String replyren) 
   {
      this.replyren= replyren;
  }
   private Date replytime ;
   public Date getReplytime() 
   {
      return replytime;
  }
   public void setReplytime(Date replytime) 
   {
      this.replytime= replytime;
  }
   private String tablename ;
   public String getTablename() 
   {
      return tablename;
  }
   public void setTablename(String tablename) 
   {
      this.tablename= tablename;
  }
   private String belongid ;
   public String getBelongid() 
   {
      return belongid;
  }
   public void setBelongid(String belongid) 
   {
      this.belongid= belongid;
  }
}

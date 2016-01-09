package com.xs.entity;
import java.util.Date;
import javax.persistence.*;
@Entity
public class Spcategory
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
   private String mingcheng ;
   public String getMingcheng() 
   {
      return mingcheng;
  }
   public void setMingcheng(String mingcheng) 
   {
      this.mingcheng= mingcheng;
  }
   private String jieshao ;
   public String getJieshao() 
   {
      return jieshao;
  }
   public void setJieshao(String jieshao) 
   {
      this.jieshao= jieshao;
  }
}

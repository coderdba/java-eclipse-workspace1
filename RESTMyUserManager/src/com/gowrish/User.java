package com.gm;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
public class User implements Serializable {

   private static final long serialVersionUID = 1L;
   private String id;
   private String profile;
   private String verifyFunction;

   // instantiation methods
   public User(){}
   
   public User(String id, String profile, String verifyFunction){
      this.id = id;
      this.profile = profile;
      this.verifyFunction = verifyFunction;
   }
   
   public String getId() {
	      return id;
   }

   @XmlElement
   public void setId(String id) {
      this.id = id;
   }
 
   public String getProfile() {
       return profile;
   }
   
   @XmlElement
   public void setProfile(String profile) {
      this.profile = profile;
   }

   public String getVerifyFunction() {
       return verifyFunction;
   }
   
   @XmlElement
   public void setVerifyFunction(String verifyFunction) {
      this.verifyFunction = verifyFunction;
   }	

}

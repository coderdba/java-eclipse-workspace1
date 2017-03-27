package com.dba.ora.um;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlRootElement(name = "user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {

   private static final long serialVersionUID = 1L;
   private String id;
   private String dbServiceOrSID;
   private String dbScanOrHost;
   private String port;

   // instantiation methods
   public User(){}
   
   public User(String id, String dbServiceOrSID, String dbScanOrHost, String port){
      this.id = id;
      this.dbServiceOrSID = dbServiceOrSID;
      this.dbScanOrHost = dbScanOrHost;
      this.port = port;
   }
   
   public String getId() {
	      return id;
   }

   public void setId(String id) {
      this.id = id;
   }
 
   public String getDbServiceOrSID() {
       return dbServiceOrSID;
   }
   
   public void setDbServiceOrSID(String dbServiceOrSID) {
      this.dbServiceOrSID = dbServiceOrSID;
   }

   public String getDbScanOrHost() {
       return dbScanOrHost;
   }
   
   public void setDbScanOrHost(String dbScanOrHost) {
      this.dbScanOrHost = dbScanOrHost;
   }
   
   public String getPort() {
	   return port;
   }
   
   public void setPort(String port) {
	   this.port = port;
   }

}
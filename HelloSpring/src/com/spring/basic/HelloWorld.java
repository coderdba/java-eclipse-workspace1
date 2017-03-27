package com.spring.basic;

import org.springframework.beans.factory.*;

public class HelloWorld implements InitializingBean, DisposableBean  {
	   private String message;

	   public void setMessage(String message){
		  System.out.println("Setting Message : " + message);
	      this.message  = message;
	   }

	   public void getMessage(){
	      System.out.println("Your Message : " + message);
	   }
	   
	   // Equivalent of init()
	   public void afterPropertiesSet() {
		   System.out.println("Bean is initializing");
	   }
	   
	   // Equivalent of destroy() - note: has same name as traditional destroy()
	   public void destroy() {
		   System.out.println("Bean is getting destroyed");
	   }
	   
	}	
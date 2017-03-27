package com.spring.basic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorldApp {
   public static void main(String[] args) {
	  
	  //Use ApplicationContext if not specifically calling destroy
      //ApplicationContext context = 
	   
	  //Use AbstractApplicationContext if you have to call destroy
	   AbstractApplicationContext context = 
             new ClassPathXmlApplicationContext("Beans.xml");

      HelloWorld obj = (HelloWorld) context.getBean("helloWorld");

      obj.getMessage();
      
      // Call the destroy (initialization is automatic)
      context.registerShutdownHook();
      
      context.close();
   }
}
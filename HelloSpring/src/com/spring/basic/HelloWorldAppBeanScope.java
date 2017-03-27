package com.spring.basic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloWorldAppBeanScope {

	public static void main(String[] args) {
	      ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

	      // Call singleton bean
	      System.out.println("Calling singleton bean");
	      
	      HelloWorld objA = (HelloWorld) context.getBean("helloWorldBeanSingleton");

	      objA.setMessage("I'm object A");
	      objA.getMessage();

	      HelloWorld objB = (HelloWorld) context.getBean("helloWorldBeanSingleton");
	      objB.getMessage();
	      
	      
	      // Call prototype bean
	      System.out.println("\nCalling prototype bean");

	      objA = (HelloWorld) context.getBean("helloWorldBeanPrototype");

	      objA.setMessage("I'm object A");
	      objA.getMessage();

	      objB = (HelloWorld) context.getBean("helloWorldBeanPrototype");
	      objB.getMessage();
	}

}

package com.gm;

import java.io.IOException;
import java.util.List;


import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/UserService")
public class UserService {

   UserDao userDao = new UserDao();
   
   private static final String SUCCESS_RESULT="<result>success</result>";
   private static final String FAILURE_RESULT="<result>failure</result>";

   // LIST USERS
   
   @GET
   @Path("/users")
   @Produces(MediaType.APPLICATION_XML)
   public List<User> getUsers(){
      return userDao.getAllUsers();
   }
   
   // ADD USER
   
   /*  original
   @PUT
   @Path("/users")
   @Produces(MediaType.APPLICATION_XML)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String createUser(@FormParam("id") String id,
      @FormParam("profile") String profile,
      @FormParam("verifyFunction") String verifyFunction,
      @Context HttpServletResponse servletResponse) throws IOException{
      User user = new User(id, profile, verifyFunction);
      int result = userDao.addUser(user);
      if(result == 1){
         return SUCCESS_RESULT;
      }
      return FAILURE_RESULT;
   }
   
   */
   
// ADD USER WITH JSON INPUT

  
   // this gives unsupported media type 415
   @PUT
   @Path("/users")
   @Produces(MediaType.APPLICATION_XML)
   //@Consumes(MediaType.APPLICATION_JSON)
   @Consumes({"application/json"})
   public String createUserJson(User user)
   throws IOException {
	  System.out.println("\n\nGot new user to add with json input");
      System.out.println(user.getId());
	  
	  //System.out.println("Got user " + user.getId());
      //System.out.println("\n");
      
      int result = userDao.addUser(user);
      if(result == 1){
         return SUCCESS_RESULT;
      }
      return FAILURE_RESULT;
   }
  
   
   
   /*
   @PUT
   //@Path("/users/{id}/{profile}/{verifyFunction}")
   @Path("/users")
   @Produces(MediaType.APPLICATION_XML)
   //@Consumes(MediaType.APPLICATION_JSON)
   @Consumes({"application/json"})
   public String createUserJson2(@PathParam("id") final String id,
		   @PathParam("profile") final String profile,
   		   @PathParam("verifyFunction") final String verifyFunction) throws IOException{
      User user = new User(id, profile, verifyFunction);
	  System.out.println("\n\nCreated new user to add with json input");
      System.out.println(id);
	  
      int result = userDao.addUser(user);
      if(result == 1){
         return SUCCESS_RESULT;
      }
      return FAILURE_RESULT;
   }
   */
   
   /* Working - not with json, but with /id/profile/verifyFunction values in URL 
   @PUT
   @Path("/users/{id}/{profile}/{verifyFunction}")
   //@Path("/users")
   @Produces(MediaType.APPLICATION_XML)
   //@Consumes(MediaType.APPLICATION_JSON)
   //@Consumes({"application/json"})
   public String createUserJson2(@PathParam("id") final String id,
		   @PathParam("profile") final String profile,
   		   @PathParam("verifyFunction") final String verifyFunction) throws IOException{

      User user = new User(id, profile, verifyFunction);
	  System.out.println("\n\nCreated new user to add with json input");
      System.out.println(id);
	  
      int result = userDao.addUser(user);
      if(result == 1){
         return SUCCESS_RESULT;
      }
      return FAILURE_RESULT;
   }
   */
   
   /* 
   @PUT
   //@Path("/users/{id}/{profile}/{verifyFunction}")
   @Path("/users")
   //@Path("/users")
   @Produces(MediaType.APPLICATION_XML)
   //@Consumes(MediaType.APPLICATION_JSON)
   @Consumes({"application/json"})
   public String createUserJson2(User user) throws IOException{

      //User user = new User(id, profile, verifyFunction);
	  //System.out.println("\n\nCreated new user to add with json input");
      //System.out.println(id);
	  
      int result = userDao.addUser(user);
      if(result == 1){
         return SUCCESS_RESULT;
      }
      return FAILURE_RESULT;
   }
   */
   
   // UPDATE USER
      
   @POST
   @Path("/users")
   @Produces(MediaType.APPLICATION_XML)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String updateUser(@FormParam("id") String id,
      @FormParam("profile") String profile,
      @FormParam("verifyFunction") String verifyFunction,
      @Context HttpServletResponse servletResponse) throws IOException{
      User user = new User(id, profile, verifyFunction);
            
      int result = userDao.updateUser(user);
      if(result == 1){
         return SUCCESS_RESULT;
      }
      return FAILURE_RESULT;
   }
   
// UPDATE USER WITH JSON INPUT
   @POST
   @Path("/users")
   @Produces(MediaType.APPLICATION_XML)
   @Consumes(MediaType.APPLICATION_JSON)
   //public String updateUserJson(@FormParam("id") String id,
   public String updateUserJson(@PathParam ("id") String id,
		   						@PathParam("profile") String profile,
		   						@PathParam("verifyFunction") String verifyFunction,
		   						@Context HttpServletResponse servletResponse) throws IOException{
      User user = new User(id, profile, verifyFunction);
      
      System.out.println("\n\nCreated new user to update\n\n");
      
      int result = userDao.updateUser(user);
      if(result == 1){
         return SUCCESS_RESULT;
      }
      return FAILURE_RESULT;
   }
   
   
}

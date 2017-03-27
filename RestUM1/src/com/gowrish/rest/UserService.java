package com.gm.rest;


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
   

   // ADD USER WITH JSON INPUT
   @PUT
   @Path("/users")
   @Produces(MediaType.APPLICATION_XML)
   //@Consumes(MediaType.APPLICATION_JSON)
   @Consumes({"application/json"})
   public String createUserJson(User user)
   throws IOException {

      int result = userDao.addUser(user);
      if(result == 1){
         return SUCCESS_RESULT;
      }
      return FAILURE_RESULT;
   }
  

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
      
      int result = userDao.updateUser(user);
      if(result == 1){
         return SUCCESS_RESULT;
      }
      return FAILURE_RESULT;
   }
   
   
}

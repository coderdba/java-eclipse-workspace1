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

@Path("/NameService")
public class NameResource {

	// THIS IS WORKING  - AFTER JACKSON LIBRARIES INCLUSION
    @POST
    @Path("/post")
    //@Consumes(MediaType.APPLICATION_JSON)
    @Consumes("application/json")
    
    @Produces(MediaType.TEXT_PLAIN)
    
    public String setTest(TheName someName)
    {
        return "Hello "+ someName.getFirstname()+ " " + someName.getLastname() + " nice to meet you.";
    }
    
    /*
    // This gets null from json string with just /put - even after Jackson libraries inclusion
    // Gives 404 Not Found with /put/{Firstname}/{Lastname} - but works if we provide actual values in URL - even after jackson libraries inclusion
    @PUT
    //@Path("/put/{firstname}/{lastname}")  
    //@Path("/put/{TheName}")
    @Path("/put")
    //@Consumes(MediaType.APPLICATION_JSON)
    @Consumes("application/json")
    
    @Produces(MediaType.TEXT_PLAIN)
    
    public String putTest(@PathParam("firstname") String Firstname, @PathParam("lastname") String Lastname)
    {
        return "Hello - Firstname: " + Firstname + " Lastname: " + Lastname + " nice to meet you." ;
    }
    */
    
    //  THIS IS WORKING - AFTER JACKSON LIBRARIES INCLUSION
    @PUT
    @Path("/put")
    //@Consumes(MediaType.APPLICATION_JSON)
    @Consumes("application/json")
    
    @Produces(MediaType.TEXT_PLAIN)
    
    public String putTest(TheName name)
    {
        return "Hello - Firstname: " + name.getFirstname() + " Lastname: " + name.getLastname() + " nice to meet you." ;
    }
	
}

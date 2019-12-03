package org.example.exercise1;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @Inject
    GreetingService service;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/greeting/{name}")
    public String greeting(@PathParam("name") String name) {
        return service.greeting(name);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "test1";
    }
    
 // create a list to show some data
    private List<String> greetingsInMultipleLangs = new ArrayList<String>() {{
        add("Hello");
        add("Salut");
        add("Hola");
        add("Nǐ hǎo");
    }};
       
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/multiple")
    public List<String> multipleGreetings() {
        return greetingsInMultipleLangs;
    }

}

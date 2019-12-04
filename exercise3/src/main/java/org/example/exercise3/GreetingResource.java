package org.example.exercise3;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/greeting")
@Produces("application/json")
@Consumes("application/json")
public class GreetingResource {

    @Inject
    GreetingService service;

    @GET
    public List<String> getAll() {
        return service.getAll();
    }

    @GET
    @Path("/{id}")
    public Response getOne(@PathParam("id") Long id) {
        String entity = service.get(id.intValue());
        if (entity == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        return Response
                .status(Response.Status.OK)
                .entity(entity)
                .build();
    }

}

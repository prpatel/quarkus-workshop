package org.example.exercise4;

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
    @Produces(MediaType.TEXT_PLAIN)
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

    @POST
    public Response create(String name) {
        String entity = service.add(name);
        return Response.status(Response.Status.CREATED).entity(entity).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(String name, @PathParam("id") int id) {
        String entity = service.update(id, name);
        return Response.ok(entity).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteOne(@PathParam("id") int id) {
        String entity = service.get(id);
        if (entity == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        service.delete(id);
        return Response.noContent().build();
    }
}

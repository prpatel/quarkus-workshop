package org.example.exercise5;


import io.quarkus.panache.common.Sort;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tag")
@Produces("application/json")
@Consumes("application/json")
public class TagResource {

  @OPTIONS
  public Response opt() {
    return Response.ok().build();
  }

  @GET
  public List<Tag> getAll() {
    return Tag.listAll();
  }

  @GET
  @Path("/{id}")
  public Response getOne(@PathParam("id") Long id) {
    Tag entity = Tag.findById(id);
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
  @Transactional
  public Response create(Tag tag) {
    tag.persist();
    return Response.status(Response.Status.CREATED).entity(tag).build();
  }

  @PUT
  @Path("/{id}")
  @Transactional
  public Response update(Tag tag, @PathParam("id") Long id) {
    Tag entity = Tag.findById(id);
    entity.name = tag.name;
    entity.persist();
    return Response.ok(entity).build();
  }

  @DELETE
  @Transactional
  @Path("/{id}")
  public Response deleteOne(@PathParam("id") Long id) {
    Tag entity = Tag.findById(id);
    if (entity == null) {
      return Response
        .status(Response.Status.NOT_FOUND)
        .build();
    }
    entity.delete();
    return Response.noContent().build();
  }
}

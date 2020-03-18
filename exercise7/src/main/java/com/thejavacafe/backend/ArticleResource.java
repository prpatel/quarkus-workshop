package com.thejavacafe.backend;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import io.smallrye.reactive.messaging.annotations.Emitter;
import io.smallrye.reactive.messaging.annotations.Stream;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/article")
@Produces("application/json")
@Consumes("application/json")
public class ArticleResource {


  @Inject
  @Stream("emails") // Emit on the channel 'emails'
  Emitter<String> emails;

  @OPTIONS
  public Response opt() {
    return Response.ok().build();
  }

  @GET
  public List<Article> getAll(@QueryParam("page") Integer page) {
    PanacheQuery<Article> entities = Article.findAll(Sort.by("created"));
    if (page==null) {
      // don't paginate, return everything. potentially dangerous
    } else {
      entities
        .page(Page.of(page, 11));
    }
    return entities.list();
  }

  @GET
  @Path("/{id}")
  public Response getOne(@PathParam("id") Long id) {
    Article entity = Article.findById(id);
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
  public Response create(Article article) {
    article.persist();
    emails.send("Send an email for article with id:" + article.id);
    return Response.status(Response.Status.CREATED).entity(article).build();
  }

  @PUT
  @Path("/{id}")
  @Transactional
  public Response update(Article article, @PathParam("id") Long id) {
    Article entity = Article.findById(id);
    System.out.println("FOUND ARTICLE TO UPDATE");
    entity.title = article.title;
    entity.asciidocsource = article.asciidocsource;
    entity.likes = article.likes;
    entity.linktotweet = article.linktotweet;
    entity.subtitle = article.subtitle;
    entity.persist();
    return Response.ok(entity).build();

  }

  @DELETE
  @Transactional
  @Path("/{id}")
  public Response deleteOne(@PathParam("id") Long id) {
    Article entity = Article.findById(id);
    if (entity == null) {
      return Response
        .status(Response.Status.NOT_FOUND)
        .build();
    }
    entity.delete();
    return Response.noContent().build();
  }
}

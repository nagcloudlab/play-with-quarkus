package org.acme.quarkus.sample;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

@Path("/api/heroes")
@Produces(MediaType.APPLICATION_JSON)
public class HeroResource {

    @Inject
    HeroService heroService;

    @GET
    @Path("/random")
    public Response getRandoHero() {
        Hero hero = this.heroService.findRandomHero();
        return Response.ok(hero).build();
    }

    @GET
    public Response getAllHeroes() {
        List<Hero> heroes = this.heroService.findAllHeroes();
        return Response.ok(heroes).build();
    }

    @GET
    @Path("/{id}")
    public Response getHero(@PathParam("id") Long id) {
        Hero hero = this.heroService.findById(id);
        if (hero != null)
            return Response.ok(hero).build();
        else
            return Response.status(Status.NO_CONTENT).build();
    }

    @POST
    public Response createHero(@Valid Hero hero,@Context UriInfo uriInfo) {
        hero = heroService.persistHero(hero);
        UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(hero.id));
        return Response.created(builder.build()).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateHero(@Valid Hero hero, @PathParam("id") Long id) {
        hero = this.heroService.updateHero(hero);
        return Response.status(Status.CREATED).entity(hero).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteHero(@PathParam("id") Long id) {
        this.heroService.deteHero(id);
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/hello")
    public String hello() {
        return "hello";
    }

}
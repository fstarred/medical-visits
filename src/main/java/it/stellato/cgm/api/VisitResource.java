package it.stellato.cgm.api;

import it.stellato.cgm.entity.Visit;
import it.stellato.cgm.service.VisitService;
import org.jboss.resteasy.annotations.SseElementType;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/visits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VisitResource {

    @Inject
    VisitService service;

    @SseElementType(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("/{id}")
    public Visit get(@PathParam("id") final String id) {
        return service.get(id);
    }

    @SseElementType(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Visit add(@Valid Visit input) {
        return service.add(input);
    }

}

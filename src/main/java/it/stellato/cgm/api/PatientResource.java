package it.stellato.cgm.api;

import it.stellato.cgm.entity.Patient;
import it.stellato.cgm.service.PatientService;
import org.jboss.resteasy.annotations.SseElementType;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/patients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PatientResource {

    @Inject
    PatientService service;

    @SseElementType(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("/{id}")
    public Patient get(@PathParam("id") final String id) {
        return service.get(id);
    }

    @SseElementType(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    @Path("/{id}/visits/{visit-index}")
    public Patient.Visit get(@PathParam("id") final String id, @PathParam("visit-index") Integer index) {
        return service.getVisit(id, index);
    }

    @SseElementType(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Patient add(@Valid Patient input) {
        return service.add(input);
    }

    @SseElementType(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PUT
    @Path("/{id}/visits/{index}")
    public Patient updateVisit(@PathParam("id") final String id,
                                     @PathParam("index") final Integer visitIndex,
                                     @Valid Patient.Visit visit) {
        return service.updateVisit(id, visit, visitIndex);
    }

    @SseElementType(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    @Path("/{id}/visits/")
    public Patient addVisit(@PathParam("id") final String id,
                               @Valid Patient.Visit visit) {
        return service.addVisit(id, visit);
    }
}

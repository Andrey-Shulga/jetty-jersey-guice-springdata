package controllers;

import model.Product;
import service.Service;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/msg")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Message {

    @Inject
    private Service service;

    @GET
    public String getMessage() {

        service.save(new Product(111666L, "Phone"));
        return "{\"Hey!, I'm run\" : \"yes\"}";
    }
}

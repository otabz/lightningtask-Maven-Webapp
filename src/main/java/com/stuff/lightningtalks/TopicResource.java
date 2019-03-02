package com.stuff.lightningtalks;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/")
public class TopicResource {
	
	private static Logger logger = Logger.getLogger(TopicResource.class.getName());
	
	@Inject
	Topics topics;

	@POST
	public Response submit(BodyParams params, @Context HttpHeaders headers,
			@Context HttpServletRequest request) {
		if (!params.validate()) {
			return createResponse(Response.Status.BAD_REQUEST,
					params.getError());
		}
		return createResponse(Response.Status.CREATED,
				topics.submit(params.getSubject(),
						params.getDescription(),
						params.getEmail(),
						request.getRemoteAddr(),
						headers.getHeaderString("host"),
						headers.getHeaderString("user-agent")));
	}

	public static javax.ws.rs.core.Response createResponse(
			javax.ws.rs.core.Response.Status status, Object message) {
		return javax.ws.rs.core.Response.status(status).entity(message).build();
	}
}

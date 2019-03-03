package com.stuff.lightningtalks;

import java.util.Collection;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
	public static final String PARAM_ERROR = "{\"error\": \" %s must be unqiue\"}";
	public static final String NO_CONTENT_ERROR = "{\"error\": \" no results found\"}";
	
	@Inject
	Topics topics;
	
	@GET
	public Response topics() {
		Collection<Topic> list = topics.retrieve();
		if (list.isEmpty()) {
			return createResponse(Response.Status.NO_CONTENT,
					NO_CONTENT_ERROR);
		}
		return createResponse(Response.Status.OK, 
				topics.retrieve());
	}

	@POST
	public Response submit(BodyParams params, @Context HttpHeaders headers,
			@Context HttpServletRequest request) {
		if (params.notValid()) {
			return createResponse(Response.Status.BAD_REQUEST,
					params.getError());
		}
		
		if (topics.notUnique(params.getSubject())) {
			return createResponse(Response.Status.BAD_REQUEST,
					String.format(PARAM_ERROR, "topic"));
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

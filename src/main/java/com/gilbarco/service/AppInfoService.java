package com.gilbarco.service;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.gilbarco.model.JsonResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/app-info")
public class AppInfoService {
	
	private static final AtomicInteger counter = new AtomicInteger();
	private static Logger LOGGER = Logger.getLogger(AppInfoService.class.getName());
	@GET
	@Path("/get-no-of-calls-with-timestamp")
	public Response getNoOfHits() {
		LOGGER.info("Service No Of Hits started");
		JsonResponse response = new JsonResponse();
		response.setCalls(counter.incrementAndGet());
		String timestamp = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mmX").withZone(ZoneOffset.UTC).format(Instant.now());
		response.setTimestamp(timestamp);
		LOGGER.info("Service No Of Hits ended");
		Gson gson = new GsonBuilder().create();
		return Response.status(200).entity(gson.toJson(response)).build();
	}
 

}
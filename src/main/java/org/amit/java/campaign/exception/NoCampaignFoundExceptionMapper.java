package org.amit.java.campaign.exception;


import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.amit.java.campaign.model.ErrorMessage;

@Provider
public class NoCampaignFoundExceptionMapper implements ExceptionMapper<NoCampaignFoundException>{

	@Override
	public Response toResponse(NoCampaignFoundException exception) {
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(),404);
		return Response.status(Status.NOT_FOUND)
						.entity(errorMessage)
						.build();
	}
}

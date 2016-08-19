package org.amit.java.campaign.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.amit.java.campaign.model.ErrorMessage;

@Provider
public class MultipleActiveCampaignExceptionMapper implements ExceptionMapper<MultipleActiveCampaignException>{

	@Override
	public Response toResponse(MultipleActiveCampaignException exception) {
		ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(),409);
		return Response.status(Status.CONFLICT)
						.entity(errorMessage)
						.build();
	}
}

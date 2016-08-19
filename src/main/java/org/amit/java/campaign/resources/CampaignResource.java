package org.amit.java.campaign.resources;

import java.net.URI;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.amit.java.campaign.model.Campaign;
import org.amit.java.campaign.service.CampaignService;


@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CampaignResource {
	
	CampaignService campaignService = new CampaignService();
	
	
	@GET
	@Path("/{partnerId}")
	public List<Campaign> getCampaign(@PathParam("partnerId") String partnerId){
		return campaignService.getCampaign(partnerId,"GET");		
	}

	@POST
	public Response addMessage(@Context UriInfo uriInfo, Campaign campaign){
		
		Calendar cal = (Calendar.getInstance());
		campaign.setCreationDate(cal.getTime());
		
		cal.add(Calendar.SECOND, campaign.getDuration());
		campaign.setExpireDate(cal.getTime());
		
		List<Campaign> campaignList = campaignService.getCampaign(campaign.getPartnerId(),"POST");
		campaignList.add(campaign);
		campaignService.addCampaign(campaign.getPartnerId(),campaignList);
		
		URI uri = uriInfo.getAbsolutePathBuilder().path(campaign.getPartnerId()).build();
		return Response.created(uri).entity(campaign).build();		
	}	
}

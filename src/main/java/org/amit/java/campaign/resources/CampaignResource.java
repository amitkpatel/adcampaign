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
import org.amit.java.campaign.model.Link;
import org.amit.java.campaign.service.CampaignService;


@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CampaignResource {
	
	CampaignService campaignService = new CampaignService();

	@GET
	@Path("/getAllCampaigns/{partnerId}")
	public List<Campaign> getAllCampaigns(@Context UriInfo uriInfo, @PathParam("partnerId") String partnerId){
		
		List<Campaign> campaignList = campaignService.getCampaign(partnerId,"GETALL");
		
		return campaignList;
	}

	
	@GET
	@Path("/{partnerId}")
	public List<Campaign> getCampaign(@Context UriInfo uriInfo, @PathParam("partnerId") String partnerId){
		List<Campaign> campaignList = campaignService.getCampaign(partnerId,"GET");
		
		URI uri = uriInfo.getBaseUriBuilder().path("/getAllCampaigns/"+partnerId).build();
		Link link = new Link();
		link.sethLink(uri.toString());
		link.setRel("getAllCampaigns");
		
		for(Campaign campaign : campaignList)
		{
			campaign.setLink(link);
		}	
		return campaignList;
	}

	@POST
	public Response addCampaign(@Context UriInfo uriInfo, Campaign campaign){
		
		Calendar cal = (Calendar.getInstance());
		campaign.setCreationDate(cal.getTime());
		
		cal.add(Calendar.SECOND, campaign.getDuration());
		campaign.setExpireDate(cal.getTime());
		
		List<Campaign> campaignList = campaignService.getCampaign(campaign.getPartnerId(),"POST");
		campaignList.add(campaign);
		campaignService.addCampaign(campaign.getPartnerId(),campaignList);
		
		URI uri = uriInfo.getBaseUriBuilder().path("/getAllCampaigns/"+campaign.getPartnerId()).build();
		Link link = new Link();
		link.sethLink(uri.toString());
		link.setRel("getAllCampaigns");
		campaign.setLink(link);
		
		URI absoluteUri = uriInfo.getAbsolutePathBuilder().path(campaign.getPartnerId()).build();
		return Response.created(absoluteUri).entity(campaign).build();		
	}	
}

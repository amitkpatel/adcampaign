package org.amit.java.campaign.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Path;

import org.amit.java.campaign.database.CampaignDao;
import org.amit.java.campaign.exception.MultipleActiveCampaignException;
import org.amit.java.campaign.exception.NoActiveCampaignFoundException;
import org.amit.java.campaign.exception.NoCampaignFoundException;
import org.amit.java.campaign.model.Campaign;

@Path("campaign")
public class CampaignService {

	private Map<String, List<Campaign>> campaigns = CampaignDao.getCampaigns();
	List<Campaign> activeCampaignList = new ArrayList<>();

	public CampaignService() {

	}

	public List<Campaign> getCampaign(String partnerId, String method) {
		List<Campaign> campaignList = campaigns.get(partnerId);
		if ("POST".equals(method)) {
			// POST Method is initiated for adding campaign
			if (campaignList == null)
				return new ArrayList<Campaign>();
			if (isActiveCampaignPresent(campaignList))
				throw new MultipleActiveCampaignException(
						"Only one active campaign is allowed per PartnerId.");
			else
				return campaignList;
		}

		// GET Method is initiated to fetch campaign for input partnerId
		if (campaignList == null || campaignList.size() == 0) {
			throw new NoCampaignFoundException(
					"No Campaign found for Partner Id : " + partnerId);
		}

		boolean activaCampaignFlag = isActiveCampaignPresent(campaignList);

		if (!activaCampaignFlag) {
			throw new NoActiveCampaignFoundException(
					"No Active Campaign found for Partner Id : " + partnerId);
		}

		return activeCampaignList;
	}

	private boolean isActiveCampaignPresent(List<Campaign> campaignList) {
		boolean activaCampaignFlag = false;
		for (Campaign campaign : campaignList) {
			// If expireDate is greater than current time
			if (campaign.getExpireDate().compareTo(new Date()) > 0) {
				activaCampaignFlag = true;
				activeCampaignList.add(campaign);
			}
		}
		return activaCampaignFlag;
	}

	public void addCampaign(String partnerId, List<Campaign> campaignList) {
		campaigns.put(partnerId, campaignList);
	}
}

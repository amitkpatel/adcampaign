package org.amit.java.campaign.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.amit.java.campaign.database.CampaignDao;
import org.amit.java.campaign.exception.MultipleActiveCampaignException;
import org.amit.java.campaign.exception.NoActiveCampaignFoundException;
import org.amit.java.campaign.exception.NoCampaignFoundException;
import org.amit.java.campaign.model.Campaign;


public class CampaignService {

	private Map<String, List<Campaign>> campaigns = CampaignDao.getCampaigns();
	List<Campaign> activeCampaignList = new ArrayList<>();

	public CampaignService() {

	}

	public List<Campaign> getCampaign(String partnerId, String method) {
		List<Campaign> campaignList = campaigns.get(partnerId);
		
		//Fetch existing campaign before adding new Campaign
		if ("POST".equals(method)) {
			if (campaignList == null)
				return new ArrayList<Campaign>();

			if (isActiveCampaignPresent(campaignList))
				throw new MultipleActiveCampaignException("Only one active campaign is allowed per PartnerId.");
			else
				return campaignList;
		}
		
		//Fetch all campaigns for partnerId
		if("GETALL".equals(method)){
			validateCampaignList(partnerId, campaignList);
			return campaignList;
		}

		//Fetch only active campaigns for partnerId
		if("GET".equals(method)){
			validateCampaignList(partnerId, campaignList);

			boolean activaCampaignFlag = isActiveCampaignPresent(campaignList);

			if (!activaCampaignFlag) {
				throw new NoActiveCampaignFoundException(
						"No Active Campaign found for Partner Id : " + partnerId);
			}
		}

		return activeCampaignList;
	}

	private void validateCampaignList(String partnerId,
			List<Campaign> campaignList) {
		if (campaignList == null || campaignList.size() == 0) {
			throw new NoCampaignFoundException(
					"No Campaign found for Partner Id : " + partnerId);
		}
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

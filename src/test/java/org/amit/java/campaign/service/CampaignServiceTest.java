package org.amit.java.campaign.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.amit.java.campaign.exception.MultipleActiveCampaignException;
import org.amit.java.campaign.exception.NoActiveCampaignFoundException;
import org.amit.java.campaign.exception.NoCampaignFoundException;
import org.amit.java.campaign.database.CampaignDao;
import org.amit.java.campaign.model.Campaign;
import org.junit.Test;


public class CampaignServiceTest{
	String partnerId;
	int duration;
	String adContent;
	Date creationDate;
	Date expiryDate;
	Calendar cal = (Calendar.getInstance());
	
	private CampaignService campaignService = new CampaignService();
	private List<Campaign> campaignList = new ArrayList<>();
	private Map<String, List<Campaign>> campaignsMap = CampaignDao.getCampaigns();
	
	@Test
	public void testGetCampaign() {

		String partnerId = "Amit";
		int duration = 6000;
		String adContent = "Campaign1";
		
		Calendar cal = (Calendar.getInstance());
		Date creationDate = cal.getTime();
		cal.add(Calendar.SECOND, duration);
		Date expiryDate = cal.getTime();
				
		//put the campaigns in Map
		Campaign campaign = new Campaign(partnerId, duration, adContent,creationDate,expiryDate);
		campaignList.add(campaign);
		campaignsMap.put(partnerId,campaignList);
 
		List<Campaign> campaignList = campaignService.getCampaign(partnerId, "GET");
		Campaign responseCampaign = campaignList.get(0);
		
		assertEquals("Amit",responseCampaign.getPartnerId());
		assertEquals(6000,responseCampaign.getDuration());
		assertEquals("Campaign1",responseCampaign.getAdContent());
	}
	
	@Test
	public void testGetAllCampaign() {

		partnerId = "Jane";
		duration = 0;
		adContent = "Campaign1";

		creationDate = cal.getTime();
		cal.add(Calendar.SECOND, duration);
		expiryDate = cal.getTime();
				
		//put the campaigns in Map
		Campaign campaign = new Campaign(partnerId, duration, adContent,creationDate,expiryDate);
		campaignList = campaignService.getCampaign(partnerId, "POST");
		campaignList.add(campaign);
		campaignsMap.put(partnerId,campaignList);

		
		partnerId = "Jane";
		duration = 6000;
		adContent = "Campaign2";
		
		cal = (Calendar.getInstance());
		creationDate = cal.getTime();
		cal.add(Calendar.SECOND, duration);
		expiryDate = cal.getTime();
				
		//put the campaigns in Map
		campaign = new Campaign(partnerId, duration, adContent,creationDate,expiryDate);
		campaignList = campaignService.getCampaign(partnerId, "POST");
		campaignList.add(campaign);
		campaignsMap.put(partnerId,campaignList);
		
		assertEquals(2,campaignsMap.get(partnerId).size());
		
		List<Campaign> campaignList = campaignService.getCampaign(partnerId, "GETALL");

		assertEquals(2,campaignList.size());
		
		

		partnerId = "James";
		duration = 6000;
		adContent = "Campaign1";
		
		cal = (Calendar.getInstance());
		creationDate = cal.getTime();
		cal.add(Calendar.SECOND, duration);
		expiryDate = cal.getTime();
				
		//put the campaigns in Map
		campaign = new Campaign(partnerId, duration, adContent,creationDate,expiryDate);
		campaignList = new ArrayList<Campaign>();
		campaignList = campaignService.getCampaign(partnerId, "POST");
		campaignList.add(campaign);
		campaignsMap.put(partnerId,campaignList);

		assertEquals(1,campaignsMap.get(partnerId).size());

		campaignList = campaignService.getCampaign(partnerId, "GETALL");

		assertEquals(1,campaignList.size());
		
	}
	
	@Test
	public void testAddCampaign() {
		partnerId = "Amit";
		duration = 0;
		adContent = "Campaign1";

		creationDate = cal.getTime();
		cal.add(Calendar.SECOND, duration);
		expiryDate = cal.getTime();
				
		//put the campaigns in Map
		Campaign campaign = new Campaign(partnerId, duration, adContent,creationDate,expiryDate);
		campaignList = campaignService.getCampaign(partnerId, "POST");
		campaignList.add(campaign);
		campaignsMap.put(partnerId,campaignList);

		
		partnerId = "Amit";
		duration = 6000;
		adContent = "Campaign2";
		
		cal = (Calendar.getInstance());
		creationDate = cal.getTime();
		cal.add(Calendar.SECOND, duration);
		expiryDate = cal.getTime();
				
		//put the campaigns in Map
		campaign = new Campaign(partnerId, duration, adContent,creationDate,expiryDate);
		campaignList = campaignService.getCampaign(partnerId, "POST");
		campaignList.add(campaign);
		campaignsMap.put(partnerId,campaignList);
		
		assertEquals(2,campaignsMap.get(partnerId).size());
		

		partnerId = "Brian";
		duration = 6000;
		adContent = "Campaign1";
		
		cal = (Calendar.getInstance());
		creationDate = cal.getTime();
		cal.add(Calendar.SECOND, duration);
		expiryDate = cal.getTime();
				
		//put the campaigns in Map
		campaign = new Campaign(partnerId, duration, adContent,creationDate,expiryDate);
		campaignList = new ArrayList<Campaign>();
		campaignList = campaignService.getCampaign(partnerId, "POST");
		campaignList.add(campaign);
		campaignsMap.put(partnerId,campaignList);

		assertEquals(1,campaignsMap.get(partnerId).size());

	}

	
	@Test(expected=NoCampaignFoundException.class)
	public void testGetCampaignWhenNoCampaignFound() {

		String partnerId = "Peter";
		List<Campaign> campaignList = campaignService.getCampaign(partnerId, "GET");
		Campaign responseCampaign = campaignList.get(0);
		
		assertEquals("Peter",responseCampaign.getPartnerId());
		assertEquals(6000,responseCampaign.getDuration());
		assertEquals("Campaign1",responseCampaign.getAdContent());
	}
	
	@Test(expected=NoActiveCampaignFoundException.class)
	public void testGetCampaignWhenNoActiveCampaignFound() {

		String partnerId = "Dan";
		int duration = 0;
		String adContent = "Campaign1";
		
		Calendar cal = (Calendar.getInstance());
		Date creationDate = cal.getTime();
		cal.add(Calendar.SECOND, duration);
		Date expiryDate = cal.getTime();
				
		//put the campaigns in Map
		Campaign campaign = new Campaign(partnerId, duration, adContent,creationDate,expiryDate);
		campaignList.add(campaign);
		campaignsMap.put(partnerId,campaignList);
 
		List<Campaign> campaignList = campaignService.getCampaign(partnerId, "GET");
		Campaign responseCampaign = campaignList.get(0);
		
		assertEquals("Dan",responseCampaign.getPartnerId());
		assertEquals(0,responseCampaign.getDuration());
		assertEquals("Campaign1",responseCampaign.getAdContent());
	}
	
	@Test(expected=MultipleActiveCampaignException.class)
	public void testAddMultipleActiveCampaign() {
		partnerId = "Mike";
		duration = 6000;
		adContent = "Campaign1";

		creationDate = cal.getTime();
		cal.add(Calendar.SECOND, duration);
		expiryDate = cal.getTime();
				
		//put the campaigns in Map
		Campaign campaign = new Campaign(partnerId, duration, adContent,creationDate,expiryDate);
		campaignList = campaignService.getCampaign(partnerId, "POST");
		campaignList.add(campaign);
		campaignsMap.put(partnerId,campaignList);

		
		partnerId = "Mike";
		duration = 6000;
		adContent = "Campaign2";
		
		cal = (Calendar.getInstance());
		creationDate = cal.getTime();
		cal.add(Calendar.SECOND, duration);
		expiryDate = cal.getTime();
				
		//put the campaigns in Map
		campaign = new Campaign(partnerId, duration, adContent,creationDate,expiryDate);
		campaignList = campaignService.getCampaign(partnerId, "POST");
		campaignList.add(campaign);
		campaignsMap.put(partnerId,campaignList);
		
		assertEquals(2,campaignsMap.get(partnerId).size());
	}

}

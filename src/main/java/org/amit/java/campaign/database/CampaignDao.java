package org.amit.java.campaign.database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.amit.java.campaign.model.Campaign;

//This is a class which can initiate Database call for DB operations
public class CampaignDao {
	
	private static Map<String,List<Campaign>> compaigns = new HashMap<>();
	
	public static Map<String, List<Campaign>> getCampaigns(){
		return compaigns;
	}
	
}

package org.amit.java.campaign.exception;

public class NoActiveCampaignFoundException extends RuntimeException{

	private static final long serialVersionUID = -745063201638934565L;

	public NoActiveCampaignFoundException(){
	
	}
	
	public NoActiveCampaignFoundException(String message){
		super(message);
	}
	
}

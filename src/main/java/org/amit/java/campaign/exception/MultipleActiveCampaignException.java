package org.amit.java.campaign.exception;

public class MultipleActiveCampaignException extends RuntimeException{

	private static final long serialVersionUID = -745063201638934565L;

	public MultipleActiveCampaignException(){
	
	}
	
	public MultipleActiveCampaignException(String message){
		super(message);
	}
	
}

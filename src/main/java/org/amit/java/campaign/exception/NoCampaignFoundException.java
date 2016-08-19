package org.amit.java.campaign.exception;

public class NoCampaignFoundException extends RuntimeException{

	private static final long serialVersionUID = -745063201638934565L;

	public NoCampaignFoundException(){
	
	}
	
	public NoCampaignFoundException(String message){
		super(message);
	}
	
}

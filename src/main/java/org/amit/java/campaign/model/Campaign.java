package org.amit.java.campaign.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "campaign")
public class Campaign {
	
	private String partnerId;
	private int duration;
	private String adContent;
	private Date creationDate;
	private Date expireDate;
	
	
	public Campaign(){
		
	}
		
	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getAdContent() {
		return adContent;
	}

	public void setAdContent(String adContent) {
		this.adContent = adContent;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public void setCreationDate() {
		this.creationDate = new Date();
	}

	public void setCreationDate(Date date) {
		this.creationDate = date;
	}

}

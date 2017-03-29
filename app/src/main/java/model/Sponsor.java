package model;

import java.io.Serializable;

public class Sponsor implements Serializable
{
	private String sponsorName;
	private String sponsorDesc;
	private String sponsorLogo;
	private String categoryName;
	private String categoryColor;
	
	
	private String recordId;
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getSponsorName() {
		return sponsorName;
	}
	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
	}
	public String getSponsorDesc() {
		return sponsorDesc;
	}
	public void setSponsorDesc(String sponsorDesc) {
		this.sponsorDesc = sponsorDesc;
	}
	public String getSponsorLogo() {
		return sponsorLogo;
	}
	public void setSponsorLogo(String sponsorLogo) {
		this.sponsorLogo = sponsorLogo;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryColor() {
		return categoryColor;
	}
	public void setCategoryColor(String categoryColor) {
		this.categoryColor = categoryColor;
	}
	 
	

}

package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Agenda implements Serializable {
	
	private String id;
	private String evntID;
	private String type;
	private String day;
	private String hallsname;
	private String title;
	private String sessionid;
	private String dated;
	private String datem;
	private String datey;
	private String timein;
	private String timeout;

	private List<Chairman> chairmans=new ArrayList<>();
	private List<Topics> topics=new ArrayList<>();

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Chairman> getChairmans() {
		return chairmans;
	}
	public void setChairmans(List<Chairman> chairmans) {
		this.chairmans = chairmans;
	}
	public List<Topics> getTopics() {
		return topics;
	}
	public void setTopics(List<Topics> topics) {
		this.topics = topics;
	}

	public String getEvntID() {
		return evntID;
	}

	public void setEvntID(String evntID) {
		this.evntID = evntID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHallsname() {
		return hallsname;
	}

	public void setHallsname(String hallsname) {
		this.hallsname = hallsname;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	public String getDated() {
		return dated;
	}

	public void setDated(String dated) {
		this.dated = dated;
	}

	public String getDatem() {
		return datem;
	}

	public void setDatem(String datem) {
		this.datem = datem;
	}

	public String getDatey() {
		return datey;
	}

	public void setDatey(String datey) {
		this.datey = datey;
	}

	public String getTimein() {
		return timein;
	}

	public void setTimein(String timein) {
		this.timein = timein;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}
}

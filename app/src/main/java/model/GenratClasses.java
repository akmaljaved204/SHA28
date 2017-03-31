package model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class GenratClasses {
	
	public Agenda getAgenda(String data)
	{
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			Agenda agenda = mapper.readValue(data,Agenda.class);
			return agenda;
		}catch(Exception e){}
		
		return null;
				
	}
	
	public List<Agenda> getAgendas(String data)
	{
		ObjectMapper mapper = new ObjectMapper();
		List<Agenda> agendas=new ArrayList<>();
		try
		{
			agendas = mapper.readValue(data, new TypeReference<List<Agenda>>(){});
		return agendas;
		}catch(Exception e){}
		
		return agendas;
	}
	
	public List<Sponsor> getspossors(String data)
	{
		ObjectMapper mapper = new ObjectMapper();
		List<Sponsor> sponsors=new ArrayList<>();
		try
		{
			sponsors = mapper.readValue(data, new TypeReference<List<Sponsor>>(){});
		return sponsors;
		}catch(Exception e){}
		
		return sponsors;
	}

	public List<EventDay> getEventDays(String data)
	{
		ObjectMapper mapper = new ObjectMapper();
		List<EventDay> eventDays=new ArrayList<>();
		try
		{
			eventDays = mapper.readValue(data, new TypeReference<List<EventDay>>(){});
			return eventDays;
		}catch(Exception e){}

		return eventDays;
	}

}

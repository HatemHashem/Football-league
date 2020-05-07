package com.example.footballleague.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Season{

	@SerializedName("currentMatchday")
	private Object currentMatchday;

	@SerializedName("endDate")
	private String endDate;

	@SerializedName("availableStages")
	private List<String> availableStages;

	@SerializedName("id")
	private int id;

	@SerializedName("startDate")
	private String startDate;

	public void setCurrentMatchday(Object currentMatchday){
		this.currentMatchday = currentMatchday;
	}

	public Object getCurrentMatchday(){
		return currentMatchday;
	}

	public void setEndDate(String endDate){
		this.endDate = endDate;
	}

	public String getEndDate(){
		return endDate;
	}

	public void setAvailableStages(List<String> availableStages){
		this.availableStages = availableStages;
	}

	public List<String> getAvailableStages(){
		return availableStages;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setStartDate(String startDate){
		this.startDate = startDate;
	}

	public String getStartDate(){
		return startDate;
	}

	@Override
 	public String toString(){
		return 
			"Season{" + 
			"currentMatchday = '" + currentMatchday + '\'' + 
			",endDate = '" + endDate + '\'' + 
			",availableStages = '" + availableStages + '\'' + 
			",id = '" + id + '\'' + 
			",startDate = '" + startDate + '\'' + 
			"}";
		}
}
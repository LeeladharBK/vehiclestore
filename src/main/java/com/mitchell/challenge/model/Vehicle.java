package com.mitchell.challenge.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Min(value = 1950, message = "Year must be greater than 1950")
	@Max(value = 2050, message = "Year must be lesser than 2050")
	private int year;
	
	@NotNull(message = "Make cannot be Null")
	@NotEmpty(message = "Make cannot be empty")
	private String make;
	
	@NotNull(message = "Model cannot be Null")
	@NotEmpty(message = "Model cannot be empty")
	private String model;
	
	@JsonProperty("Id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@JsonProperty("Year")
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	@JsonProperty("Make")
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	
	@JsonProperty("Model")
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
}

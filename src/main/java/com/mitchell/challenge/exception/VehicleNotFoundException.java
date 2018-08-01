package com.mitchell.challenge.exception;

public class VehicleNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fieldName;
	private Object fieldValue;

	public VehicleNotFoundException(String fieldName, Object fieldValue) {
		super(String.format("Vehicle not found with %s : '%s'", fieldName, fieldValue));
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public String getFieldName() {
		return fieldName;
	}

	public Object getFieldValue() {
		return fieldValue;
	}
}
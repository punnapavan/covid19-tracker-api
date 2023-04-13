package com.covid.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class BaseResponse {
	
	private Boolean success;
	private List<ErrorCode> errors;
	private String message;
	
}

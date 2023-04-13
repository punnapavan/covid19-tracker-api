package com.covid.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class SummaryResponse extends BaseResponse implements Serializable {
	
	private Boolean status;
	private int positive;
	private int negative;
	private int total;
	private int dischaged;
	private int deads;

}

package com.isstech.security.websocket.model;

import lombok.Data;

import java.util.Date;

@Data
public class OutMessage {

	private String from;
	
	private String content;
	
	private Date time = new Date();

	public OutMessage(){}
	
	public OutMessage(String content){
		this.content = content;
	}

}

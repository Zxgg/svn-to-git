package com.isstech.security.websocket.model;

import lombok.Data;

import java.util.Date;

@Data
public class InMessage {
	
	//从哪里来
	private String from;
	
	//到哪里去
	private String to;
	
	private String content;
	
	private Date time;

	public InMessage(){}
	
	public InMessage(String content) {
		this.content = content;
	}
}

package com.hala.entity;

public class Message {
	
	private long id;
	private long sendId;
	private User sendUser;
	private long receiveId;
	private String title;
	private String msgContent;
	private int state;
	private String msg_date;
	
	
	public Message() {
		super();
	}
	
	
	
	


	public Message(long sendId, String title, String msgContent, String msg_date) {
		super();
		this.sendId = sendId;
		this.title = title;
		this.msgContent = msgContent;
		this.msg_date = msg_date;
	}






	public Message(long sendId, long receiveId, String title, String msgContent, int state, String msg_date) {
		super();
		this.sendId = sendId;
		this.receiveId = receiveId;
		this.title = title;
		this.msgContent = msgContent;
		this.state = state;
		this.msg_date = msg_date;
	}




	public Message(long id, long sendId, long receiveId, String title, String msgContent, int state, String msg_date) {
		super();
		this.id = id;
		this.sendId = sendId;
		this.receiveId = receiveId;
		this.title = title;
		this.msgContent = msgContent;
		this.state = state;
		this.msg_date = msg_date;
	}




	public long getId() {
		return id;
	}




	public void setId(long id) {
		this.id = id;
	}




	public long getSendId() {
		return sendId;
	}




	public void setSendId(long sendId) {
		this.sendId = sendId;
	}


	


	public User getSendUser() {
		return sendUser;
	}






	public void setSendUser(User sendUser) {
		this.sendUser = sendUser;
	}






	public long getReceiveId() {
		return receiveId;
	}




	public void setReceiveId(long receiveId) {
		this.receiveId = receiveId;
	}




	public String getTitle() {
		return title;
	}




	public void setTitle(String title) {
		this.title = title;
	}




	public String getMsgContent() {
		return msgContent;
	}




	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}




	public int getState() {
		return state;
	}




	public void setState(int state) {
		this.state = state;
	}




	public String getMsg_date() {
		return msg_date;
	}




	public void setMsg_date(String msg_date) {
		this.msg_date = msg_date;
	}




	@Override
	public String toString() {
		return "Message [id=" + id + ", sendId=" + sendId + ", receiveId=" + receiveId + ", title=" + title
				+ ", msgContent=" + msgContent + ", state=" + state + ", msg_date=" + msg_date + "]";
	}
	
	
	
	
	

}

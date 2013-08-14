package com.example.guestbook.domain.model;

import java.util.Date;

import com.google.appengine.api.users.User;

public class Greeting {
	private String guestbookName;

	private User user;

	private String content;

	private Date date;

	public Greeting() {
	}

	public Greeting(String guestbookName, User user, String content, Date date) {
		super();
		this.guestbookName = guestbookName;
		this.user = user;
		this.content = content;
		this.date = date;
	}

	public String getGuestbookName() {
		return guestbookName;
	}

	public void setGuestbookName(String guestbookName) {
		this.guestbookName = guestbookName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}

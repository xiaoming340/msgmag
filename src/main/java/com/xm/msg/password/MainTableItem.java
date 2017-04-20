package com.xm.msg.password;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MainTableItem {

	private SimpleIntegerProperty id = new SimpleIntegerProperty();
	private SimpleStringProperty label = new SimpleStringProperty();
	private SimpleStringProperty username = new SimpleStringProperty();
	private SimpleStringProperty account = new SimpleStringProperty();
	private SimpleStringProperty email = new SimpleStringProperty();
	private SimpleStringProperty phone = new SimpleStringProperty();
	private SimpleStringProperty password = new SimpleStringProperty();
	private SimpleStringProperty comment = new SimpleStringProperty();
	
	public MainTableItem() {
		id.set(0);
		label.set("");
		username.set("");
		account.set("");
		email.set("");
		phone.set("");
		password.set("");
		comment.set("");
	}

	public MainTableItem(int id, String label, String username,
			String account, String email, String phone,
			String password, String comment) {
		super();
		this.id.set(id);
		this.label.set(label);
		this.username.set(username);
		this.account.set(account);
		this.email.set(email);
		this.phone.set(phone);
		this.password.set(password);
		this.comment.set(comment);
	}

	public int getId() {
		return id.get();
	}

	public void setId(int id) {
		this.id.set(id);
	}

	public String getLabel() {
		return label.get();
	}

	public void setLabel(String label) {
		this.label.set(label);
	}

	public String getUsername() {
		return username.get();
	}

	public void setUsername(String username) {
		this.username.set(username);
	}

	public String getAccount() {
		return account.get();
	}

	public void setAccount(String account) {
		this.account.set(account);
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(String email) {
		this.email.set(email);
	}

	public String getPhone() {
		return phone.get();
	}

	public void setPhone(String phone) {
		this.phone.set(phone);
	}

	public String getPassword() {
		return password.get();
	}

	public void setPassword(String password) {
		this.password.set(password);
	}

	public String getComment() {
		return comment.get();
	}

	public void setComment(String comment) {
		this.comment.set(comment);
	}
}

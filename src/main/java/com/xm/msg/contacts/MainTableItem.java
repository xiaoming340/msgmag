package com.xm.msg.contacts;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MainTableItem {

	private SimpleIntegerProperty id = new SimpleIntegerProperty();
	private SimpleStringProperty  name = new SimpleStringProperty();
	private SimpleStringProperty  relate = new SimpleStringProperty();
	private SimpleStringProperty  phone = new SimpleStringProperty();
	private SimpleStringProperty  phoneAdd = new SimpleStringProperty();
	private SimpleStringProperty  email = new SimpleStringProperty();
	private SimpleStringProperty  address = new SimpleStringProperty();
	private SimpleStringProperty  birth = new SimpleStringProperty();
	private SimpleStringProperty  qq = new SimpleStringProperty();
	private SimpleStringProperty  weixin = new SimpleStringProperty();
	private SimpleStringProperty  school = new SimpleStringProperty();
	private SimpleStringProperty  company = new SimpleStringProperty();
	private SimpleStringProperty  identify = new SimpleStringProperty();
	private SimpleStringProperty  comment = new SimpleStringProperty();
	
	public MainTableItem() {
		id.set(0);
		name.set("");
		relate.set("");
		phone.set("");
		phoneAdd.set("");
		email.set("");
		address.set("");
		birth.set("");
		qq.set("");
		weixin.set("");
		school.set("");
		company.set("");
		identify.set("");
		comment.set("");
	}
	
	public MainTableItem(int id, String name, String relate, String phone, String phoneAdd, String email, String address, 
			String birth, String qq, String weixin, String school, String company, String identify, String comment) {
		this.id.set(id);
		this.name.set(name);
		this.relate.set(relate);
		this.phone.set(phone);
		this.phoneAdd.set(phoneAdd);
		this.email.set(email);
		this.address.set(address);
		this.birth.set(birth);
		this.qq.set(qq);
		this.weixin.set(weixin);
		this.school.set(school);
		this.company.set(company);
		this.identify.set(identify);
		this.comment.set(comment);
	}

	public int getId() {
		return this.id.get();
	}

	public void setId(int id) {
		this.id.set(id);
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public String getRelate() {
		return relate.get();
	}

	public void setRelate(String relate) {
		this.relate.set(relate);
	}

	public String getPhone() {
		return phone.get();
	}

	public void setPhone(String phone) {
		this.phone.set(phone);
	}

	public String getPhoneAdd() {
		return phoneAdd.get();
	}

	public void setPhoneAdd(String phoneAdd) {
		this.phoneAdd.set(phoneAdd);
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(String email) {
		this.email.set(email);
	}

	public String getAddress() {
		return address.get();
	}

	public void setAddress(String address) {
		this.address.set(address);
	}

	public String getBirth() {
		return birth.get();
	}

	public void setBirth(String birth) {
		this.birth.set(birth);
	}

	public String getQq() {
		return qq.get();
	}

	public void setQq(String qq) {
		this.qq.set(qq);
	}

	public String getWeixin() {
		return weixin.get();
	}

	public void setWeixin(String weixin) {
		this.weixin.set(weixin);
	}

	public String getSchool() {
		return school.get();
	}

	public void setSchool(String school) {
		this.school.set(school);
	}

	public String getCompany() {
		return company.get();
	}

	public void setCompany(String company) {
		this.company.set(company);
	}
	
	public String getIdentify() {
		return identify.get();
	}

	public void setIdentify(String identify) {
		this.identify.set(identify);
	}

	public String getComment() {
		return comment.get();
	}

	public void setComment(String comment) {
		this.comment.set(comment);
	}
	
	
}

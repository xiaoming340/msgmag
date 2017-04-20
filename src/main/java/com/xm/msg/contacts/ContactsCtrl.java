package com.xm.msg.contacts;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.xm.msg.cfg.Syscfg;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ContactsCtrl implements Initializable {
	
	@FXML private TableView<MainTableItem> mainTable;
	
	@FXML private TextField tfId;
	@FXML private TextField tfName;
	@FXML private TextField tfRelate;
	@FXML private TextField tfPhone;
	@FXML private TextField tfPhoneAdd;
	@FXML private TextField tfEmail;
	@FXML private TextArea  taAddress;
	@FXML private TextField tfBirth;
	@FXML private TextField tfQq;
	@FXML private TextField tfWeixin;
	@FXML private TextArea  taSchool;
	@FXML private TextArea  taCompany;
	@FXML private TextField tfIdentify;
	@FXML private TextArea  taComment;
	
	@FXML private TextField tfSearchName;
	@FXML private TextField tfSearchRelate;
	@FXML private TextField tfSearchAddress;
	@FXML private TextField tfSearchSchool;
	@FXML private TextField tfSearchCompany;
	@FXML private TextField tfSearchComment;
	
	@FXML private Label lb_prompt;
	
	private int currPosMainTable = -1;
	
	private void getAllData() {
		ObservableList<MainTableItem> data = mainTable.getItems();
		data.clear();
		String sql = "select * from contacts";
		ResultSet ret = null;
		try {
			ret = Syscfg.st.executeQuery(sql);
			while(ret.next()) {
				MainTableItem item = new MainTableItem();
				item.setId(ret.getInt(1));
				item.setName(ret.getString(2));
				item.setRelate(ret.getString(3));
				item.setPhone(ret.getString(4));
				item.setPhoneAdd(ret.getString(5));
				item.setEmail(ret.getString(6));
				item.setAddress(ret.getString(7));
				item.setBirth(ret.getString(8));
				item.setQq(ret.getString(9));
				item.setWeixin(ret.getString(10));
				item.setSchool(ret.getString(11));
				item.setCompany(ret.getString(12));
				item.setIdentify(ret.getString(13));
				item.setComment(ret.getString(14));
				data.add(item);
			}
			ret.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		finally{
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		getAllData();
		mainTable.getSelectionModel().selectFirst();
		mainTableSelected();
	}
	
	private void mainTableSelected() {
		ObservableList<MainTableItem> data = mainTable.getItems();
    	currPosMainTable = mainTable.getSelectionModel().getSelectedIndex();
    	if(currPosMainTable < 0 || currPosMainTable >= data.size()) {
    		currPosMainTable = -1;
    		return;
    	}
    	MainTableItem item = data.get(currPosMainTable);
    	tfId.setText("" + item.getId());
    	tfName.setText(item.getName());
    	tfRelate.setText(item.getRelate());
    	tfPhone.setText(item.getPhone());
    	tfPhoneAdd.setText(item.getPhoneAdd());
    	tfEmail.setText(item.getEmail());
    	taAddress.setText(item.getAddress());
    	tfBirth.setText(item.getBirth());
    	tfQq.setText(item.getQq());
    	tfWeixin.setText(item.getWeixin());
    	taSchool.setText(item.getSchool());
    	taCompany.setText(item.getCompany());
    	tfIdentify.setText(item.getIdentify());
    	taComment.setText(item.getComment());
	}

	@FXML
	protected void onMainTableClicked(MouseEvent value) {
		mainTableSelected();
	}
	
	
	@FXML
	protected void onMainTableKeyReleased(KeyEvent value) {
		if(value.getCode().toString().equals("UP") || value.getCode().toString().equals("DOWN")) {
			mainTableSelected();
		}
	}
	
	@FXML 
	protected void showAll(ActionEvent event) {
		getAllData();
		mainTable.getSelectionModel().selectFirst();
		mainTableSelected();
	}
	
	@FXML 
	protected void insert(ActionEvent event) {
		int id = -1;
		try {
			id = Integer.parseInt(tfId.getText());
		}catch(NumberFormatException e) {
			lb_prompt.setText("Input error : id not valid!");
			return;
		}
		ObservableList<MainTableItem> data = mainTable.getItems();
		for(int i=0; i<data.size(); i++) {
			if(data.get(i).getId() == id) {
				lb_prompt.setText("Input error : id already exist!");
				return;
			}
		}
		lb_prompt.setText("");
		String name = tfName.getText();
		String relate = tfRelate.getText();
		String phone = tfPhone.getText();
		String phoneAdd = tfPhoneAdd.getText();
		String email = tfEmail.getText();
		String address = taAddress.getText();
		String birth = tfBirth.getText();
		String qq = tfQq.getText();
		String weixin = tfWeixin.getText();
		String school = taSchool.getText();
		String company = taCompany.getText();
		String identify = tfIdentify.getText();
		String comment = taComment.getText();
		MainTableItem item = new MainTableItem(id, name, relate, phone, phoneAdd, email, address, birth, qq, weixin, school, company, identify, comment);
		String sql = "insert into contacts values(\"" + id + "\", \"" + name + "\", \"" + relate + "\", \"" + phone + "\", \"" + phoneAdd + 
				"\", \"" + email + "\", \"" + address + "\", \"" + birth + "\", \"" + qq + "\", \"" + weixin + "\", \"" + school +
				"\", \"" + company + "\", \"" + identify + "\", \"" + comment + "\")";
		//System.out.println(sql);
		try {
			Syscfg.st.execute(sql);
			data.add(item);
		} catch(SQLException e) {
			lb_prompt.setText("SQL error!");
			e.printStackTrace();
		}
	}
	
	@FXML 
	protected void update(ActionEvent event) {
		int id = -1;
		try {
			id = Integer.parseInt(tfId.getText());
		}catch(NumberFormatException e) {
			lb_prompt.setText("Input error : id not valid!");
		}
		ObservableList<MainTableItem> data = mainTable.getItems();
		if(currPosMainTable < 0 || currPosMainTable >= data.size()) {
			lb_prompt.setText("No select!");
		}
		int oldId = data.get(currPosMainTable).getId();
		if(id != oldId) {
			for(int i=0; i<data.size(); i++) {
				if(data.get(i).getId() == id) {
					lb_prompt.setText("Input error : id conflict!");
					return;
				}
			}
		}
		lb_prompt.setText("");
		String name = tfName.getText();
		String relate = tfRelate.getText();
		String phone = tfPhone.getText();
		String phoneAdd = tfPhoneAdd.getText();
		String email = tfEmail.getText();
		String address = taAddress.getText();
		String birth = tfBirth.getText();
		String qq = tfQq.getText();
		String weixin = tfWeixin.getText();
		String school = taSchool.getText();
		String company = taCompany.getText();
		String identify = tfIdentify.getText();
		String comment = taComment.getText();
		String sql = "update contacts set id = " + id + ", name = \"" + name + "\", relate = \"" + relate + "\", phone = \"" + phone +
				"\", phoneadd = \"" + phoneAdd + "\", email = \"" + email + "\", address = \"" + address + "\", birth = \"" + birth + 
				"\", qq = \"" + qq + "\", weixin = \"" + weixin + "\", school = \"" + school + "\", company = \"" + company + 
				"\", identify = \"" + identify + "\", comment = \"" + comment +
				"\" where id = " + data.get(currPosMainTable).getId();
		try {
			Syscfg.st.executeUpdate(sql);
			MainTableItem item = data.get(currPosMainTable);
			item.setId(id);
			item.setName(name);
			item.setRelate(relate);
			item.setPhone(phone);
			item.setPhoneAdd(phoneAdd);
			item.setEmail(email);
			item.setAddress(address);
			item.setBirth(birth);
			item.setQq(qq);
			item.setWeixin(weixin);
			item.setSchool(school);
			item.setCompany(company);
			item.setIdentify(identify);
			item.setComment(comment);
			data.set(currPosMainTable, item);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@FXML 
	protected void delete(ActionEvent event) {
		int id = -1;
		try {
			id = Integer.parseInt(tfId.getText());
		}catch(NumberFormatException e) {
			lb_prompt.setText("Input error : id not valid!");
		}
		ObservableList<MainTableItem> data = mainTable.getItems();
		int i = 0;
		for(i=0; i<data.size(); i++) {
			if(data.get(i).getId() == id) {
				break;
			}
		}
		if(i == data.size()) {
			lb_prompt.setText("Input error : id not exist!");
			return;
		}
		lb_prompt.setText("");
		String sql = "delete from contacts where id = " + id;
		try {
			Syscfg.st.execute(sql);
			data.remove(i);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		currPosMainTable = -1;
	}
	
	private void display() {
		//if(value.getCode().toString().equals("ENTER")) {
			ObservableList<MainTableItem> data = mainTable.getItems();
			if(data.size() > 0) {
				tfId.setText("" + data.get(0).getId());
		    	tfName.setText(data.get(0).getName());
		    	tfRelate.setText(data.get(0).getRelate());
		    	tfPhone.setText(data.get(0).getPhone());
		    	tfPhoneAdd.setText(data.get(0).getPhoneAdd());
		    	tfEmail.setText(data.get(0).getEmail());
		    	taAddress.setText(data.get(0).getAddress());
		    	tfBirth.setText(data.get(0).getBirth());
		    	tfQq.setText(data.get(0).getQq());
		    	tfWeixin.setText(data.get(0).getWeixin());
		    	taSchool.setText(data.get(0).getSchool());
		    	taCompany.setText(data.get(0).getCompany());
		    	tfIdentify.setText(data.get(0).getIdentify());
		    	taComment.setText(data.get(0).getComment());
			}
			currPosMainTable = 0;
			mainTable.getSelectionModel().selectFirst();
		//}
	}
	
	@FXML
	protected void onSearchNameKeyReleased(KeyEvent value) {
		if(value.getCode().toString().equals("ENTER")) {
			String search = tfSearchName.getText().toLowerCase();
			getAllData();
			if(!search.equals("")) {
				ObservableList<MainTableItem> data = mainTable.getItems();
				for(int i=0; i<data.size(); i++) {
					String target = data.get(i).getName().toLowerCase();
					if(!target.contains(search)) {
						data.remove(i);
						--i;
					}
				}
			}
			display();
		}
	}
	
	@FXML
	protected void onSearchRelateKeyReleased(KeyEvent value) {
		if(value.getCode().toString().equals("ENTER")) {
			String search = tfSearchRelate.getText().toLowerCase();
			getAllData();
			if(!search.equals("")) {
				ObservableList<MainTableItem> data = mainTable.getItems();
				for(int i=0; i<data.size(); i++) {
					String target = data.get(i).getRelate().toLowerCase();
					if(!target.contains(search)) {
						data.remove(i);
						--i;
					}
				}
			}
			display();
		}
	}
	
	@FXML
	protected void onSearchAddressKeyReleased(KeyEvent value) {
		if(value.getCode().toString().equals("ENTER")) {
			String search = tfSearchAddress.getText().toLowerCase();
			getAllData();
			if(!search.equals("")) {
				ObservableList<MainTableItem> data = mainTable.getItems();
				for(int i=0; i<data.size(); i++) {
					String target = data.get(i).getAddress().toLowerCase();
					if(!target.contains(search)) {
						data.remove(i);
						--i;
					}
				}
			}
			display();
		}
	}
	
	@FXML
	protected void onSearchSchoolKeyReleased(KeyEvent value) {
		if(value.getCode().toString().equals("ENTER")) {
			String search = tfSearchSchool.getText().toLowerCase();
			getAllData();
			if(!search.equals("")) {
				ObservableList<MainTableItem> data = mainTable.getItems();
				for(int i=0; i<data.size(); i++) {
					String target = data.get(i).getSchool().toLowerCase();
					if(!target.contains(search)) {
						data.remove(i);
						--i;
					}
				}
			}
			display();
		}
	}
	
	@FXML
	protected void onSearchCompanyKeyReleased(KeyEvent value) {
		if(value.getCode().toString().equals("ENTER")) {
			String search = tfSearchCompany.getText().toLowerCase();
			getAllData();
			if(!search.equals("")) {
				ObservableList<MainTableItem> data = mainTable.getItems();
				for(int i=0; i<data.size(); i++) {
					String target = data.get(i).getCompany().toLowerCase();
					if(!target.contains(search)) {
						data.remove(i);
						--i;
					}
				}
			}
			display();
		}
	}
	
	@FXML
	protected void onSearchCommentKeyReleased(KeyEvent value) {
		if(value.getCode().toString().equals("ENTER")) {
			String search = tfSearchComment.getText().toLowerCase();
			getAllData();
			if(!search.equals("")) {
				ObservableList<MainTableItem> data = mainTable.getItems();
				for(int i=0; i<data.size(); i++) {
					String target = data.get(i).getComment().toLowerCase();
					if(!target.contains(search)) {
						data.remove(i);
						--i;
					}
				}
			}
			display();
		}
	}
}

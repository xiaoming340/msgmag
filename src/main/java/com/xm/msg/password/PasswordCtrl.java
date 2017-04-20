package com.xm.msg.password;

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

public class PasswordCtrl implements Initializable {
	
	@FXML private TableView<MainTableItem> mainTable;
	
	@FXML private TextField tf_search;
	
	@FXML private TextField tf_id;
	@FXML private TextField tf_label;
	@FXML private TextField tf_username;
	@FXML private TextField tf_account;
	@FXML private TextField tf_email;
	@FXML private TextField tf_phone;
	@FXML private TextField tf_password;
	@FXML private TextArea  ta_comment;
	@FXML private Label lb_prompt;
	
	private int currPosMainTable = -1;
	
	private void getAllData() {
		ObservableList<MainTableItem> data = mainTable.getItems();
		data.clear();
		String sql = "select * from password";
		ResultSet ret = null;
		try {
			ret = Syscfg.st.executeQuery(sql);
			while(ret.next()) {
				MainTableItem item = new MainTableItem();
				item.setId(ret.getInt(1));
				item.setLabel(ret.getString(2));
				item.setUsername(ret.getString(3));
				item.setAccount(ret.getString(4));
				item.setEmail(ret.getString(5));
				item.setPhone(ret.getString(6));
				item.setPassword(ret.getString(7));
				item.setComment(ret.getString(8));
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
    	tf_id.setText("" + item.getId());
    	tf_label.setText(item.getLabel());
    	tf_username.setText(item.getUsername());
    	tf_account.setText(item.getAccount());
    	tf_email.setText(item.getEmail());
    	tf_phone.setText(item.getPhone());
    	tf_password.setText(item.getPassword());
    	ta_comment.setText(item.getComment());
	}

	@FXML
	protected void onMainTableClicked(MouseEvent value) {
		mainTableSelected();
    }
	
	@FXML
	protected void onMainTableKeyReleased(KeyEvent value) {
		//System.out.println(value.getCode().toString());
		if(value.getCode().toString().equals("UP") || value.getCode().toString().equals("DOWN")) {
			mainTableSelected();
		}
	}
	
	@FXML
	protected void onSearchKeyReleased(KeyEvent value) {
		if(value.getCode().toString().equals("ENTER")) {
			String search = tf_search.getText();
			search = search.toLowerCase();
			getAllData();
			if(!search.equals("")) {
				ObservableList<MainTableItem> data = mainTable.getItems();
				for(int i=0; i<data.size(); i++) {
					String target = data.get(i).getLabel().toLowerCase();
					if(!target.contains(search)) {
						data.remove(i);
						--i;
					}
				}
			}
			ObservableList<MainTableItem> data = mainTable.getItems();
			if(data.size() > 0) {
				tf_id.setText("" + data.get(0).getId());
		    	tf_label.setText(data.get(0).getLabel());
		    	tf_username.setText(data.get(0).getUsername());
		    	tf_account.setText(data.get(0).getAccount());
		    	tf_email.setText(data.get(0).getEmail());
		    	tf_phone.setText(data.get(0).getPhone());
		    	tf_password.setText(data.get(0).getPassword());
		    	ta_comment.setText(data.get(0).getComment());
			}
			mainTable.getSelectionModel().selectFirst();
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
			id = Integer.parseInt(tf_id.getText());
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
		String label = tf_label.getText();
		String username = tf_username.getText();
		String account = tf_account.getText();
		String email = tf_email.getText();
		String phone = tf_phone.getText();
		String password = tf_password.getText();
		String comment = ta_comment.getText();
		MainTableItem item = new MainTableItem(id, label, username, account, email, phone, password, comment);
		String sql = "insert into password values(\"" + id + "\", \"" + label + "\", \"" + username + "\", \"" + 
				account + "\", \"" + email + "\", \"" + phone + "\", \"" + password + "\", \"" + comment + "\")";
		//System.out.println(sql);
		try {
			Syscfg.st.execute(sql);
			data.add(item);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@FXML 
	protected void update(ActionEvent event) {
		int id = -1;
		try {
			id = Integer.parseInt(tf_id.getText());
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
		String label = tf_label.getText();
		String username = tf_username.getText();
		String account = tf_account.getText();
		String email = tf_email.getText();
		String phone = tf_phone.getText();
		String password = tf_password.getText();
		String comment = ta_comment.getText();
		String sql = "update password set id = " + id + ", label = \"" + label + "\", username = \"" + username + "\", account = \"" + account +
				"\", email = \"" + email + "\", phone = \"" + phone + "\", password = \"" + password + "\", comment = \"" 
				+ comment + "\" where id = " + data.get(currPosMainTable).getId();
		try {
			Syscfg.st.executeUpdate(sql);
			MainTableItem item = data.get(currPosMainTable);
			item.setId(id);
			item.setLabel(label);
			item.setUsername(username);
			item.setAccount(account);
			item.setEmail(email);
			item.setPhone(phone);
			item.setPassword(password);
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
			id = Integer.parseInt(tf_id.getText());
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
		String sql = "delete from password where id = " + id;
		try {
			Syscfg.st.execute(sql);
			data.remove(i);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		currPosMainTable = -1;
	}
}

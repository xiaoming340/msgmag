package com.xm.msg;

import java.io.IOException;

import com.xm.msg.cfg.GuiConstants;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MenuCtrl {

	@FXML protected void onMenuPassword(ActionEvent event) throws IOException {
		Parent target = (Parent) FXMLLoader.load(getClass().getResource("/com/xm/msg/password/Password.fxml"));
		Scene scene = new Scene(target, GuiConstants.passwordWidth, GuiConstants.passwordHeight);
		scene.getStylesheets().add(getClass().getResource("/com/xm/msg/system.css").toExternalForm());
		Maingui.getStage().setScene(scene);
		Maingui.getStage().setTitle("Password");
	}
	
	@FXML protected void onContacts(ActionEvent event) throws IOException {
		Parent target = (Parent) FXMLLoader.load(getClass().getResource("/com/xm/msg/contacts/Contacts.fxml"));
		Scene scene = new Scene(target, GuiConstants.contactsWidth, GuiConstants.contactsHeight);
		scene.getStylesheets().add(getClass().getResource("/com/xm/msg/system.css").toExternalForm());
		Maingui.getStage().setScene(scene);
		Maingui.getStage().setTitle("Contacts");
	}
	
	@FXML protected void onScope(ActionEvent event) throws IOException {
		Parent target = (Parent) FXMLLoader.load(getClass().getResource("/com/xm/msg/scope/Scope.fxml"));
		Scene scene = new Scene(target, GuiConstants.contactsWidth, GuiConstants.contactsHeight);
		scene.getStylesheets().add(getClass().getResource("/com/xm/msg/system.css").toExternalForm());
		Maingui.getStage().setScene(scene);
		Maingui.getStage().setTitle("Scope");
	}
}

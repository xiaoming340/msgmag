package com.xm.msg;

import java.io.IOException;

import com.xm.msg.cfg.Syscfg;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class App extends Application {
	
	@FXML private Label title;
	@FXML private TextField username;
	@FXML private PasswordField password;
	@FXML private Button login;
	
	private static Stage stage;
	
	@FXML protected void onLogin(ActionEvent event) {
		String name = username.getText();
		String pwd = password.getText();
		if(name.equals("xiaoming") && pwd.equals("111111")) {
			//System.out.println("login success");
			stage.close();
			Maingui a = new Maingui();
		}
		//else
		//	System.out.println("login failure");
	}
	
	@Override
	public void start(Stage primaryStage) {
		String computer = System.getenv().get("COMPUTERNAME");
		if(computer.toLowerCase().equals("xumingming")) {
			Maingui a = new Maingui();
			return;
		}
		
		App.stage = primaryStage;
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("Login.fxml"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root, 320, 240);
		scene.getStylesheets().add(getClass().getResource("system.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Syscfg.sysInit();
		launch(args);
	}
}


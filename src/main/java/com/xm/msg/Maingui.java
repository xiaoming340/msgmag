package com.xm.msg;

import java.io.IOException;

import com.xm.msg.cfg.GuiConstants;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Maingui {
	
	private static Stage stage;
	
	public static Stage getStage() {
		return stage;
	}

	public Maingui() {
		stage = new Stage();
		//stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Main GUI");

		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/com/xm/msg/password/Password.fxml"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root, GuiConstants.passwordWidth, GuiConstants.passwordHeight);
		scene.getStylesheets().add(getClass().getResource("/com/xm/msg/system.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
}

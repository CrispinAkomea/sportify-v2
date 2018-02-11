package com.sportify.view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class NotePopupPane {

	private Stage stage;
	private Scene scene;

	public NotePopupPane() throws IOException {
		initialize();
		stage.setResizable(false);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);
	}

	private void initialize() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/NotePopupPane.fxml"));
		Parent root = (Parent) loader.load();
		scene = new Scene(root);
		stage = new Stage();
		stage.setScene(scene);
	}

	public void show() {
		stage.showAndWait();
	}

	public void close() {
		stage.close();
	}

}

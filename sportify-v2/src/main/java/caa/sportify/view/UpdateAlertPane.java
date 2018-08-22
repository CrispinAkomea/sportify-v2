package caa.sportify.view;

import java.io.IOException;

import caa.sportify.controller.UpdateAlertPaneController;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UpdateAlertPane {

	private Stage stage;
	private Scene scene;

	public UpdateAlertPane(DoubleProperty doubleProperty, StringProperty stringProperty) throws IOException {
		initializeData(doubleProperty, stringProperty);
		stage.setResizable(false);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.initModality(Modality.APPLICATION_MODAL);
	}

	private void initializeData(DoubleProperty doubleProperty, StringProperty stringProperty) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/UpdateAlertPane.fxml"));
		Parent root = (Parent) loader.load();
		UpdateAlertPaneController updateAlertController = loader.getController();
		updateAlertController.bindUpdateProgressBar(stringProperty);
		updateAlertController.bindUpdateProgressBar(doubleProperty);
		scene = new Scene(root);
		stage = new Stage();
		stage.setScene(scene);
	}

	public void show() {
		stage.show();
	}

	public void close() {
		stage.close();
	}

}

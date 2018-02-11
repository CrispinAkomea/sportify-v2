package com.sportify.controller;

import java.sql.SQLException;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

public class UpdateAlertPaneController {

	/**************************************************************************
	 * 
	 * Private FXML Fields
	 * 
	 **************************************************************************/

	@FXML
	private Label updateLabel;
	@FXML
	private ProgressBar updateProgressBar;
	@FXML
	private Button closeButton;

	/**************************************************************************
	 * 
	 * Private Fields
	 * 
	 **************************************************************************/

	/**
	 * 
	 * Closes the note popup.
	 * 
	 */
	private final EventHandler<ActionEvent> closeAction = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			Stage stage = (Stage) closeButton.getScene().getWindow();
			stage.close();
		}
	};

	/**************************************************************************
	 * 
	 * Initialize Method
	 * 
	 **************************************************************************/

	public void initialize() throws SQLException {
		closeButton.setOnAction(closeAction);
	}

	/***************************************************************************
	 * 
	 * Getter and Setter Methods
	 * 
	 **************************************************************************/

	public void bindUpdateProgressBar(StringProperty property) {
		updateLabel.textProperty().bind(property);
	}

	public void bindUpdateProgressBar(DoubleProperty property) {
		updateProgressBar.progressProperty().bind(property);
	}

}

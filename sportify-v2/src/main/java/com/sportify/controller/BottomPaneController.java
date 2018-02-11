package com.sportify.controller;

import com.sportify.database.Standings;
import com.sportify.database.Statistics;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

/**
 * @author Crispin A.
 *
 */
public class BottomPaneController {

	/**************************************************************************
	 * 
	 * Private FXML Fields
	 * 
	 **************************************************************************/

	@FXML
	private Label updateLabel;
	@FXML
	private ProgressBar updateProgressBar;

	/**************************************************************************
	 * 
	 * Initialize Method
	 * 
	 **************************************************************************/

	public void initialize() {
		bindProgressBarToUpdates();
	}

	/***************************************************************************
	 * 
	 * Binding Methods
	 * 
	 **************************************************************************/

	/**
	 * 
	 * Binds the double property of the progress bar (updateProgressBar) to the
	 * double properties of the statistics and standings classes that indicate the
	 * progress of the statistics update and standings update respectively.
	 * 
	 */
	private void bindProgressBarToUpdates() {
		updateProgressBar.progressProperty().bind(Statistics.getInstance().getProgress().multiply(0.5)
				.add(Standings.getInstance().getProgress().multiply(0.5)));
	}

	/***************************************************************************
	 * 
	 * Getter and Setter Methods
	 * 
	 **************************************************************************/

	public Label getUpdateLabel() {
		return updateLabel;
	}

	public ProgressBar getUpdateProgressBar() {
		return updateProgressBar;
	}

	/***************************************************************************
	 * 
	 * Other Methods
	 * 
	 **************************************************************************/

	/***************************************************************************
	 * ProgressBar Methods *
	 **************************************************************************/

	public DoubleProperty getUpdateProgressProperty() {
		return updateProgressBar.progressProperty();
	}

	public double getUpdateProgress() {
		return updateProgressBar.getProgress();
	}

	public void setProgressBar() {
		updateProgressBar.setVisible(true);
	}

	public void resetProgressBar() {
		Statistics.getInstance().setProgress(0);
		Standings.getInstance().setProgress(0);
		updateProgressBar.setVisible(false);
	}

	/***************************************************************************
	 * Label Methods *
	 **************************************************************************/

	public StringProperty getUpdateLabelProperty() {
		return updateLabel.textProperty();
	}

	public void bindUpdateLabel(StringProperty property) {
		updateLabel.textProperty().bind(property);
	}

	public void unBindUpdateLabel() {
		updateLabel.textProperty().unbind();
	}

}

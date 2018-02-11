package com.sportify.controller;

import com.sportify.controller.component.FixtureSplitPaneController;

import javafx.fxml.FXML;

/**
 * @author Crispin A.
 *
 */
public class RightPaneController {

	/**************************************************************************
	 * 
	 * Private FXML Fields
	 * 
	 **************************************************************************/

	@FXML
	private FixtureSplitPaneController fixtureSplitPaneController;

	/**************************************************************************
	 * 
	 * Private Fields
	 * 
	 **************************************************************************/

	private MainController mainController;

	/**************************************************************************
	 * 
	 * Initialize Method
	 * 
	 **************************************************************************/

	/**
	 * Injection of the right pane controller (this) into controllers to allow
	 * sharing of properties amongst controllers.
	 * 
	 */
	public void initialize() {
		fixtureSplitPaneController.injectRightContoller(this);
	}

	/**************************************************************************
	 * 
	 * Controller Injection Methods
	 * 
	 **************************************************************************/

	/**
	 * Method that allows injection of the main controller (mainController).
	 * 
	 * @param mainController
	 */
	public void injectMainContoller(MainController mainController) {
		this.mainController = mainController;
	}

	/***************************************************************************
	 * 
	 * Getter and Setter Methods
	 * 
	 **************************************************************************/

	public FixtureSplitPaneController getFixtureSplitPaneController() {
		return fixtureSplitPaneController;
	}

	public MainController getMainController() {
		return mainController;
	}

}

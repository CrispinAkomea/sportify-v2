package com.sportify.controller;

import javafx.fxml.FXML;

/**
 * @author Crispin A.
 *
 */
public class MainController {

	/**************************************************************************
	 * 
	 * Private FXML Fields
	 * 
	 **************************************************************************/

	@FXML
	private TopPaneController topPaneController;
	@FXML
	private LeftPaneController leftPaneController;
	@FXML
	private CenterPaneController centerPaneController;
	@FXML
	private RightPaneController rightPaneController;
	@FXML
	private BottomPaneController bottomPaneController;

	/**************************************************************************
	 * 
	 * Initialize Method
	 * 
	 **************************************************************************/

	/**
	 * Injection of the main controller (this) into controllers to allow
	 * sharing of properties amongst controllers.
	 * 
	 */
	public void initialize() {
		topPaneController.injectMainContoller(this);
		leftPaneController.injectMainContoller(this);
		rightPaneController.injectMainContoller(this);
	}

	/***************************************************************************
	 * 
	 * Getter and Setter Methods
	 * 
	 **************************************************************************/

	public TopPaneController getTopPaneController() {
		return topPaneController;
	}

	public LeftPaneController getLeftPaneController() {
		return leftPaneController;
	}

	public CenterPaneController getCenterPaneController() {
		return centerPaneController;
	}

	public RightPaneController getRightPaneController() {
		return rightPaneController;
	}

	public BottomPaneController getBottomPaneController() {
		return bottomPaneController;
	}

}

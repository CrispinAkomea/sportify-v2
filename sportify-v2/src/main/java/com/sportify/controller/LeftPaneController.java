package com.sportify.controller;

import com.sportify.controller.component.LeagueListViewController;
import com.sportify.controller.component.OpenNotePaneController;
import com.sportify.controller.component.TeamAccordinController;

import javafx.fxml.FXML;

/**
 * @author Crispin A.
 *
 */
public class LeftPaneController {

	/**************************************************************************
	 * 
	 * Private FXML Fields
	 * 
	 **************************************************************************/

	@FXML
	private LeagueListViewController leagueListViewController;
	@FXML
	private TeamAccordinController teamAccordinController;
	@FXML
	private OpenNotePaneController openNotePaneController;

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
	 * Injection of the left pane controller (this) into controllers to allow
	 * sharing of properties amongst controllers.
	 * 
	 */
	public void initialize() {
		leagueListViewController.injectLeftContoller(this);
		teamAccordinController.injectLeftContoller(this);
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

	public LeagueListViewController getLeagueListViewController() {
		return leagueListViewController;
	}

	public TeamAccordinController getTeamAccordinController() {
		return teamAccordinController;
	}

	public OpenNotePaneController getOpenNotePaneController() {
		return openNotePaneController;
	}

	public MainController getMainController() {
		return mainController;
	}

}

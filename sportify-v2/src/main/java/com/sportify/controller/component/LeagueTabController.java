package com.sportify.controller.component;

import java.sql.SQLException;

import com.sportify.controller.CenterPaneController;
import com.sportify.model.League;
import com.sportify.model.Standing;
import com.vendor.database.DB;
import com.vendor.utility.ModelUtil;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

/**
 * @author Crispin A.
 *
 */
public class LeagueTabController {

	/**************************************************************************
	 * 
	 * Private FXML Fields
	 * 
	 **************************************************************************/

	@FXML
	private LeagueTableViewController leagueTableViewController;

	/**************************************************************************
	 * 
	 * Private Fields
	 * 
	 **************************************************************************/

	private CenterPaneController centerPaneController;
	private StringProperty leagueProperty = new SimpleStringProperty();
	private final int TAB_INDEX = 2;

	/**
	 * 
	 * Updates the leagueTableViewController TableView<Standing> leagueTableView and
	 * makes leagueTab the selected tab.
	 * 
	 */
	private ChangeListener<String> leaguePropertyChangeListener = new ChangeListener<String>() {
		@Override
		public void changed(ObservableValue<? extends String> item, String oldValue, String newValue) {
			try {
				updateLeagueTableView(newValue);
				centerPaneController.setSelectedTab(TAB_INDEX);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	};

	/**************************************************************************
	 * 
	 * Initialize Method
	 * 
	 **************************************************************************/

	public void initialize() throws SQLException {
		leagueProperty.addListener(leaguePropertyChangeListener);
	}

	/**************************************************************************
	 * 
	 * Controller Injection Methods
	 * 
	 **************************************************************************/

	public void injectCenterTabContoller(CenterPaneController centerPaneController) {
		this.centerPaneController = centerPaneController;
	}

	/***************************************************************************
	 * 
	 * Getter and Setter Methods
	 * 
	 **************************************************************************/

	public LeagueTableViewController getLeagueTableViewController() {
		return leagueTableViewController;
	}

	public void setLeagueProperty(String league) {
		this.leagueProperty.set(league);
	}

	/***************************************************************************
	 * 
	 * Other Methods
	 * 
	 **************************************************************************/

	/***************************************************************************
	 * TableView Methods *
	 **************************************************************************/

	/**
	 * 
	 * Updates leagueTableViewController TableView<Standing> leagueTableView with
	 * standing objects of the teams in the league.
	 * 
	 * @param leagueName
	 *            - name of league
	 * @throws SQLException
	 */
	private void updateLeagueTableView(String leagueName) throws SQLException {
		ObservableList<Standing> standings = FXCollections.observableArrayList();
		League league = ModelUtil.toModel((String) DB.table("Leagues").where("name", leagueName).first(), League.class);
		for (Standing standing : league.getStandings())
			standings.add(standing);
		FXCollections.sort(standings, Standing.compareP());
		leagueTableViewController.setItems(standings, standings.size(), 1);
	}

}

package com.sportify.controller.component;

import java.sql.SQLException;

import com.sportify.controller.CenterPaneController;
import com.sportify.controller.LeftPaneController;
import com.sportify.model.League;
import com.vendor.database.DB;
import com.vendor.utility.ModelUtil;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/**
 * @author Crispin A.
 *
 */
public class LeagueListViewController {

	/**************************************************************************
	 * 
	 * Private FXML Fields
	 * 
	 **************************************************************************/

	@FXML
	private ListView<String> leagueListView;

	/**************************************************************************
	 * 
	 * Private Fields
	 * 
	 **************************************************************************/

	private LeftPaneController leftPaneController;

	/**
	 * 
	 * Sets the leagueProperty value in the fixtureTa and leagueTab when the
	 * selection changes in the leagueListView.
	 * 
	 */
	private ChangeListener<String> leagueListViewChangeListener = new ChangeListener<String>() {
		@Override
		public void changed(ObservableValue<? extends String> item, String oldValue, String newValue) {
			CenterPaneController centerTab = leftPaneController.getMainController().getCenterPaneController();
			int tabIndex = centerTab.getSelectedTab();
			switch (tabIndex) {
			case 0:
				centerTab.getLeagueTabController().setLeagueProperty(newValue);
				centerTab.getFixtureTabController().setLeagueProperty(newValue);
				break;
			case 1:
				centerTab.getLeagueTabController().setLeagueProperty(newValue);
				centerTab.getFixtureTabController().setLeagueProperty(newValue);
				break;
			case 2:
				centerTab.getFixtureTabController().setLeagueProperty(newValue);
				centerTab.getLeagueTabController().setLeagueProperty(newValue);
				break;
			default:
				break;
			}
		}
	};

	/**************************************************************************
	 * 
	 * Initialize Method
	 * 
	 * Populates the list view (leagueListView) with league values.
	 * 
	 **************************************************************************/

	public void initialize() throws SQLException {
		ObservableList<String> list = FXCollections.observableArrayList();
		League[] leagues = ModelUtil.toModels((String) DB.table("Leagues").get(), League[].class);
		for (League league : leagues)
			list.add(league.getName());
		leagueListView.setItems(list);
		leagueListView.getSelectionModel().selectedItemProperty().addListener(leagueListViewChangeListener);
	}

	/**************************************************************************
	 * 
	 * Controller Injection Methods
	 * 
	 **************************************************************************/

	public void injectLeftContoller(LeftPaneController leftPaneController) {
		this.leftPaneController = leftPaneController;
	}

	/***************************************************************************
	 * 
	 * Getter and Setter Methods
	 * 
	 **************************************************************************/

	public ListView<String> getLeagueListView() {
		return leagueListView;
	}

}

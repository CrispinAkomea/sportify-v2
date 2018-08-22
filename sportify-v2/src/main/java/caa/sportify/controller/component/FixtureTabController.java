package caa.sportify.controller.component;

import java.sql.SQLException;

import com.vendor.database.DB;
import com.vendor.utility.ModelUtil;

import caa.sportify.controller.CenterPaneController;
import caa.sportify.model.Standing;
import caa.sportify.model.Team;
import caa.sportify.utility.ImageUtil;
import caa.sportify.view.FormPane;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.Image;

/**
 * @author Crispin A.
 *
 */
public class FixtureTabController {

	/**************************************************************************
	 * 
	 * Private FXML Fields
	 * 
	 **************************************************************************/

	@FXML
	private FixtureInformationController fixtureInformationController;
	@FXML
	private LeagueTableViewController leagueTableViewController;

	/**************************************************************************
	 * 
	 * Private Fields
	 * 
	 **************************************************************************/

	private CenterPaneController centerPaneController;
	private StringProperty leagueProperty = new SimpleStringProperty();
	private final int TAB_INDEX = 0;

	/**
	 * 
	 * Clears all the previous fixture data, updates the comboBoxes with teams from
	 * the newly selected league and makes fixtureTab the selected tab.
	 * 
	 */
	private final ChangeListener<String> leaguePropertyChangeListener = new ChangeListener<String>() {
		@Override
		public void changed(ObservableValue<? extends String> item, String oldValue, String newValue) {
			try {
				fixtureInformationController.clearTeamComboBoxes();
				leagueTableViewController.getLeagueTableView().getItems().clear();
				fixtureInformationController.updateTeamComboBoxes(newValue);
				centerPaneController.setSelectedTab(TAB_INDEX);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	};

	/**
	 * 
	 * Updates home team badge, the home team form, removes the old home team from
	 * the table and adds the new home team standing to the table.
	 * 
	 */
	private final ChangeListener<String> homeComboBoxPropertyChangeListener = new ChangeListener<String>() {
		@Override
		public void changed(ObservableValue<? extends String> item, String oldValue, String newValue) {
			if (newValue != null) {
				fixtureInformationController.getHomeTeamImageView()
						.setImage(new Image(ImageUtil.getBadge(this, newValue + ".png")));
				try {
					removeTeam(oldValue);
					addTeam(newValue);
					sortTeams();
					fixtureInformationController.getHomeFormStackPane().getChildren()
							.add(new FormPane(newValue));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else
				fixtureInformationController.getHomeTeamImageView().setImage(null);
		}
	};

	/**
	 * 
	 * Updates away team badge, the away team form, removes the old away team from
	 * the table and adds the new away team standing to the table.
	 * 
	 */
	private final ChangeListener<String> awayComboBoxPropertyChangeListener = new ChangeListener<String>() {
		@Override
		public void changed(ObservableValue<? extends String> item, String oldValue, String newValue) {
			if (newValue != null) {
				fixtureInformationController.getAwayTeamImageView()
						.setImage(new Image(ImageUtil.getBadge(this, newValue + ".png")));
				try {
					removeTeam(oldValue);
					addTeam(newValue);
					sortTeams();
					fixtureInformationController.getAwayFormStackPane().getChildren().add(new FormPane(newValue));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else
				fixtureInformationController.getAwayTeamImageView().setImage(null);
		}
	};

	/**************************************************************************
	 * 
	 * Initialize Method
	 * 
	 **************************************************************************/

	public void initialize() throws SQLException {
		leagueProperty.addListener(leaguePropertyChangeListener);
		fixtureInformationController.getHomeTeamComboBox().getSelectionModel().selectedItemProperty()
				.addListener(homeComboBoxPropertyChangeListener);
		fixtureInformationController.getAwayTeamComboBox().getSelectionModel().selectedItemProperty()
				.addListener(awayComboBoxPropertyChangeListener);
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

	public FixtureInformationController getFixtureInformationController() {
		return fixtureInformationController;
	}

	public LeagueTableViewController getLeagueTableViewController() {
		return leagueTableViewController;
	}

	public void setLeagueProperty(String leagueName) {
		this.leagueProperty.set(leagueName);
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
	 * Removes the standing object of the team that is passed as an argument.
	 * 
	 * @param teamName
	 *            - name of the team
	 * @throws SQLException
	 */
	private void removeTeam(String teamName) throws SQLException {
		if (teamName != null) {
			Team team = ModelUtil.toModel((String) DB.table("Teams").where("name", teamName).first(), Team.class);
			Standing standing = team.getStanding();
			leagueTableViewController.getLeagueTableView().getItems().remove(standing);
		}
	}

	/**
	 * 
	 * Adds the standing object of the team that is passed as an argument.
	 * 
	 * @param teamName
	 *            - name of the team
	 * @throws SQLException
	 */
	private void addTeam(String teamName) throws SQLException {
		Team team = ModelUtil.toModel((String) DB.table("Teams").where("name", teamName).first(), Team.class);
		Standing standing = team.getStanding();
		leagueTableViewController.getLeagueTableView().getItems().add(standing);
	}

	/**
	 * 
	 * Sorts the standing objects in the leagueTableViewController
	 * TableView<Standing> leagueTableView the table by points. In descending order.
	 * 
	 * @throws SQLException
	 */
	private void sortTeams() throws SQLException {
		ObservableList<Standing> standings = leagueTableViewController.getLeagueTableView().getItems();
		FXCollections.sort(standings, Standing.compareP());
		leagueTableViewController.setItems(standings, standings.size(), 1);
	}

}

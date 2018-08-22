package caa.sportify.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.controlsfx.control.textfield.TextFields;

import caa.sportify.database.Standings;
import caa.sportify.database.Statistics;
import caa.sportify.model.League;
import caa.sportify.model.Team;
import caa.sportify.view.UpdateAlertPane;
import caa.vendor.database.DB;
import caa.vendor.utility.ModelUtil;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Crispin A.
 *
 */
public class TopPaneController {

	/**************************************************************************
	 * 
	 * Private FXML Fields
	 * 
	 **************************************************************************/

	@FXML
	private TextField searchTextField;
	@FXML
	private Button updateButton;
	@FXML
	private SplitMenuButton menuButton;
	@FXML
	private MenuItem updateItem;
	@FXML
	private MenuItem quitItem;

	/**************************************************************************
	 * 
	 * Private Fields
	 * 
	 **************************************************************************/

	private MainController mainController;
	private final UpdateService updateService;
	private final ArrayList<String> possibleSuggestions;
	private UpdateAlertPane updateAlertPane;

	/**
	 * 
	 * When the string value of the searchTextField changes the search method is
	 * triggered.
	 * 
	 */
	private final ChangeListener<String> searchTextFieldChangeListener = new ChangeListener<String>() {
		@Override
		public void changed(ObservableValue<? extends String> item, String oldValue, String newValue) {
			if (possibleSuggestions.contains(newValue))
				try {
					search(searchTextField.getText());
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	};

	/**
	 * 
	 * Disables both the updateButton and updateItem and starts the update process
	 * which is carried out in the background by the updateService. On success it
	 * enables the updateButton and updateItem.
	 * 
	 * A binding is made between the mesageProperty of the updateService and the
	 * textProperty of the updateLabel on the bottom panel to visualize the the
	 * progress of the update.
	 * 
	 */
	private final EventHandler<ActionEvent> updateAction = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			updateButton.setDisable(true);
			updateItem.setDisable(true);
			try {
				updateAlertPane = new UpdateAlertPane(
						mainController.getBottomPaneController().getUpdateProgressProperty(),
						mainController.getBottomPaneController().getUpdateLabelProperty());
				updateAlertPane.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
			mainController.getBottomPaneController().bindUpdateLabel((StringProperty) updateService.messageProperty());
			updateService.setOnSucceeded(function -> {
				updateAlertPane.close();
				mainController.getBottomPaneController().unBindUpdateLabel();
				updateButton.setDisable(false);
				updateItem.setDisable(false);
			});
			updateService.restart();
			event.consume();
		}
	};

	/**
	 * 
	 * Closes the application.
	 * 
	 */
	private final EventHandler<ActionEvent> closeAction = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			Stage stage = (Stage) menuButton.getScene().getWindow();
			stage.close();
		}
	};

	/**************************************************************************
	 * 
	 * Constructor
	 * 
	 * Populates the arrayList (possibleSuggestions) with options that will pop up
	 * when the TextField (searchTextField) is used.
	 * 
	 * @throws SQLException
	 * 
	 **************************************************************************/

	public TopPaneController() throws SQLException {
		updateService = new UpdateService();
		possibleSuggestions = new ArrayList<String>();
		for (League league : ModelUtil.toModels((String) DB.table("Leagues").select("name").get(), League[].class))
			possibleSuggestions.add(league.getName());
		for (Team team : ModelUtil.toModels((String) DB.table("Teams").select("name").get(), Team[].class))
			possibleSuggestions.add(team.getName());
	}

	/**************************************************************************
	 * 
	 * Initialize Method
	 * 
	 **************************************************************************/

	public void initialize() throws SQLException {
		TextFields.bindAutoCompletion(searchTextField, possibleSuggestions);
		searchTextField.textProperty().addListener(searchTextFieldChangeListener);
		updateButton.setOnAction(updateAction);
		updateItem.setOnAction(updateAction);
		quitItem.setOnAction(closeAction);
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
	 * Other Methods
	 * 
	 **************************************************************************/

	/***************************************************************************
	 * TextField Methods *
	 **************************************************************************/

	/**
	 * 
	 * If the TextField (searchTextField) string value (searchText) is a league, the
	 * leagueProperty in the fixture tab and league tab is set to that league. If it
	 * is a team name, the teamProperty in the team tab is set to that team.
	 * 
	 * @param searchText
	 *            The string value of the text search made in the TextField
	 *            (searchTextField).
	 * @throws SQLException
	 */
	private void search(String searchText) throws SQLException {
		League league = ModelUtil.toModel((String) DB.table("Leagues").where("name", searchText).first(), League.class);
		boolean isLeague = (league != null);
		CenterPaneController centerTab = mainController.getCenterPaneController();
		if (isLeague) {
			switch (centerTab.getSelectedTab()) {
			case 0:
				centerTab.getLeagueTabController().setLeagueProperty(searchText);
				centerTab.getFixtureTabController().setLeagueProperty(searchText);
				break;
			case 1:
				centerTab.getLeagueTabController().setLeagueProperty(searchText);
				centerTab.getFixtureTabController().setLeagueProperty(searchText);
				break;
			case 2:
				centerTab.getFixtureTabController().setLeagueProperty(searchText);
				centerTab.getLeagueTabController().setLeagueProperty(searchText);
				break;
			default:
				break;
			}
		} else
			centerTab.getTeamTabController().setTeamProperty(searchText);
	}

	/***************************************************************************
	 * 
	 * Inner Class
	 * 
	 **************************************************************************/

	/**
	 * 
	 * Service responsible for carrying out the statistic and standing updates on
	 * the background threads. As well as updating the label on the bottom panel
	 * with the progress as a percentage.
	 *
	 */
	private final class UpdateService extends Service<Void> {

		@Override
		public Task<Void> createTask() {
			return new Task<Void>() {
				@Override
				public Void call() throws MalformedURLException, SQLException, IOException, InterruptedException {
					mainController.getBottomPaneController().setProgressBar();
					Statistics.getInstance().getProgress().addListener(function -> {
						updateMessage("Updating statistics : ("
								+ (int) (mainController.getBottomPaneController().getUpdateProgress() * 100) + "%)");
					});
					Standings.getInstance().getProgress().addListener(function -> {
						updateMessage("Updating standings: ("
								+ (int) (mainController.getBottomPaneController().getUpdateProgress() * 100) + "%)");
					});
					Statistics.getInstance().updateAll();
					Standings.getInstance().updateAll();
					mainController.getBottomPaneController().resetProgressBar();
					updateMessage("Done");
					return null;
				}
			};
		}
	}

}

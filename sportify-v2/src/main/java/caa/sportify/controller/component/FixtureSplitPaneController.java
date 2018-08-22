package caa.sportify.controller.component;

import java.sql.SQLException;

import caa.sportify.controller.RightPaneController;
import caa.sportify.model.Fixture;
import caa.sportify.model.League;
import caa.sportify.view.FixtureHBox;
import caa.vendor.database.DB;
import caa.vendor.utility.ModelUtil;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * @author Crispin A.
 *
 */
public class FixtureSplitPaneController {

	/**************************************************************************
	 * 
	 * Private FXML Fields
	 * 
	 **************************************************************************/

	@FXML
	private SplitPane fixtureSplitPane;

	/**************************************************************************
	 * 
	 * Private Fields
	 * 
	 **************************************************************************/

	private RightPaneController rightPaneController;
	private final int TAB_INDEX = 0;

	/**
	 * 
	 * Populates the fixtureTab depending on which fixture is clicked and makes the
	 * fixtureTab the selected tab.
	 * 
	 */
	private EventHandler<MouseEvent> fixtureBoxClicked = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			FixtureHBox selectedFixture = (FixtureHBox) event.getSource();
			rightPaneController.getMainController().getCenterPaneController().setSelectedTab(TAB_INDEX);
			rightPaneController.getMainController().getCenterPaneController().getFixtureTabController()
					.setLeagueProperty(selectedFixture.getLeague().getName());
			rightPaneController.getMainController().getCenterPaneController().getFixtureTabController()
					.getFixtureInformationController().setHomeTeamName(selectedFixture.getHomeTeam().getName());
			rightPaneController.getMainController().getCenterPaneController().getFixtureTabController()
					.getFixtureInformationController().setAwayTeamName(selectedFixture.getAwayTeam().getName());
			event.consume();
		}
	};

	/**************************************************************************
	 * 
	 * Initialize Method
	 * 
	 **************************************************************************/

	public void initialize() throws SQLException {
		FixtureHBox fixtureBox = null;
		VBox specificFixtures = null;
		Fixture[] fixtures = null;
		League[] leagues = ModelUtil.toModels((String) DB.table("Leagues").get(), League[].class);
		for (League league : leagues) {
			specificFixtures = new VBox();
			specificFixtures.getStyleClass().add("fixture-vbox");
			fixtures = ModelUtil.toModels((String) DB.table("Fixtures").where("division", league.getDivision()).get(),
					Fixture[].class);
			if (fixtures.length != 0)
				specificFixtures.getChildren().add(getLeagueLabel(league.getName()));
			for (Fixture fixture : fixtures) {
				fixtureBox = new FixtureHBox(fixture);
				fixtureBox.addEventHandler(MouseEvent.MOUSE_CLICKED, fixtureBoxClicked);
				specificFixtures.getChildren().add(fixtureBox);
			}
			fixtureSplitPane.getItems().add(specificFixtures);
		}
	}

	/**************************************************************************
	 * 
	 * Controller Injection Methods
	 * 
	 **************************************************************************/

	public void injectRightContoller(RightPaneController rightPaneController) {
		this.rightPaneController = rightPaneController;
	}

	/***************************************************************************
	 * 
	 * Other Methods
	 * 
	 **************************************************************************/

	private AnchorPane getLeagueLabel(String leagueName) {
		AnchorPane anchorPane = new AnchorPane();
		anchorPane.getStyleClass().add("league");
		Label leagueLabel = new Label(leagueName);
		leagueLabel.getStyleClass().add("label");
		anchorPane.getChildren().add(leagueLabel);
		return anchorPane;
	}

}

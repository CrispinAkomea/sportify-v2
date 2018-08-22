package caa.sportify.controller;

import java.sql.SQLException;

import caa.sportify.controller.component.FixtureTabController;
import caa.sportify.controller.component.LeagueTabController;
import caa.sportify.controller.component.TeamTabController;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

/**
 * @author Crispin A.
 *
 */
public class CenterPaneController {

	/**************************************************************************
	 * 
	 * Private FXML Fields
	 * 
	 **************************************************************************/

	@FXML
	private ScrollPane centerScrollPane;
	@FXML
	private AnchorPane centerAnchorPane;
	@FXML
	private TabPane centerTabPane;
	@FXML
	private FixtureTabController fixtureTabController;
	@FXML
	private TeamTabController teamTabController;
	@FXML
	private LeagueTabController leagueTabController;

	/**************************************************************************
	 * 
	 * Initialize Method
	 * 
	 **************************************************************************/

	/**
	 * Injection of the center pane controller (this) into controllers to allow
	 * sharing of properties amongst controllers.
	 * <p>
	 * Bindings made amongst the center components making them responsive to changes
	 * in width of the stage.
	 * 
	 */
	public void initialize() throws SQLException {
		fixtureTabController.injectCenterTabContoller(this);
		teamTabController.injectCenterTabContoller(this);
		leagueTabController.injectCenterTabContoller(this);
		makeResponsiveBindings();
	}

	/***************************************************************************
	 * 
	 * Binding Methods
	 * 
	 **************************************************************************/

	private void makeResponsiveBindings() {
		bindCenterAnchorPane();
		bindCenterTabPane();
		bindFixtureLeagueTableView();
	}

	/**
	 * 
	 * Binds the width property of the anchor pane (centerAnchorPane) to the width
	 * property of the scroll pane (centerScrollPane).
	 * <p>
	 * The scroll pane (centerScrollPane) is the outer most container in the center
	 * space. Subtract 13.0 for the vertical scroll bar.
	 * 
	 */
	private void bindCenterAnchorPane() {
		centerAnchorPane.prefWidthProperty().bind(centerScrollPane.widthProperty().subtract(13.0));
	}

	/**
	 * 
	 * Binds the width property of the tab pane (centerTabPane) to the width
	 * property of the anchor pane (centerAnchorPane).
	 * 
	 */
	private void bindCenterTabPane() {
		centerTabPane.prefWidthProperty().bind(centerAnchorPane.widthProperty());
	}

	/**
	 * 
	 * Binds the width property of the table views to the width property of the
	 * anchor pane (centerAnchorPane). Subtract 40.0 for the padding.
	 * 
	 */
	private void bindFixtureLeagueTableView() {
		fixtureTabController.getLeagueTableViewController().getLeagueTableView().prefWidthProperty()
				.bind(centerAnchorPane.widthProperty().subtract(40.0));
		teamTabController.getLeagueTableViewController().getLeagueTableView().prefWidthProperty()
				.bind(centerAnchorPane.widthProperty().subtract(40.0));
		teamTabController.getTeamTableViewController().getTeamTableView().prefWidthProperty()
				.bind(centerAnchorPane.widthProperty().subtract(40.0));
		leagueTabController.getLeagueTableViewController().getLeagueTableView().prefWidthProperty()
				.bind(centerAnchorPane.widthProperty().subtract(40.0));
	}

	/***************************************************************************
	 * 
	 * Getter and Setter Methods
	 * 
	 **************************************************************************/

	public ScrollPane getCenterScrollPane() {
		return centerScrollPane;
	}

	public AnchorPane getCenterAnchorPane() {
		return centerAnchorPane;
	}

	public TabPane getCenterTabPane() {
		return centerTabPane;
	}

	public FixtureTabController getFixtureTabController() {
		return fixtureTabController;
	}

	public TeamTabController getTeamTabController() {
		return teamTabController;
	}

	public LeagueTabController getLeagueTabController() {
		return leagueTabController;
	}

	/***************************************************************************
	 * 
	 * Other Methods
	 * 
	 **************************************************************************/

	/***************************************************************************
	 * TabPane Methods *
	 **************************************************************************/

	public int getSelectedTab() {
		return centerTabPane.getSelectionModel().getSelectedIndex();
	}

	public void setSelectedTab(int tabIndex) {
		centerTabPane.getSelectionModel().select(tabIndex);
	}

}

package caa.sportify.controller.component;

import java.sql.SQLException;

import com.vendor.database.DB;
import com.vendor.utility.ModelUtil;

import caa.sportify.controller.LeftPaneController;
import caa.sportify.model.League;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;

/**
 * @author Crispin A.
 *
 */
public class TeamAccordinController {

	/**************************************************************************
	 * 
	 * Private FXML Fields
	 * 
	 **************************************************************************/

	@FXML
	private Accordion teamAccordion;

	/**************************************************************************
	 * 
	 * Private Fields
	 * 
	 **************************************************************************/

	private LeftPaneController leftPaneController;

	/**
	 * 
	 * Sets the teamProperty value in the teamTab when the selection changes in the
	 * teamAccordion.
	 * 
	 */
	private ChangeListener<String> teamListViewChangeListener = new ChangeListener<String>() {
		@Override
		public void changed(ObservableValue<? extends String> item, String oldValue, String newValue) {
			leftPaneController.getMainController().getCenterPaneController().getTeamTabController()
					.setTeamProperty(newValue);
		}
	};

	/**************************************************************************
	 * 
	 * Initialize Method
	 * 
	 * Populates the Accordion (teamAccordion) with leagues for titles and teams for
	 * items in list views.
	 * 
	 * Adding the teamListViewChangeListener to every list view before its added to
	 * the accordion.
	 * 
	 **************************************************************************/

	public void initialize() throws SQLException {
		ListView<String> listView = null;
		League[] leagues = ModelUtil.toModels((String) DB.table("Leagues").get(), League[].class);
		TitledPane dropdown = null;
		for (League league : leagues) {
			listView = new ListView<String>(FXCollections.observableArrayList(league.getTeamNames()));
			listView.getSelectionModel().selectedItemProperty().addListener(teamListViewChangeListener);
			dropdown = new TitledPane(league.getName(), listView);
			teamAccordion.getPanes().add(dropdown);
		}
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

	public Accordion getTeamAccordion() {
		return teamAccordion;
	}

}

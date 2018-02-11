package com.sportify.controller.component;

import java.sql.SQLException;

import com.sportify.model.League;
import com.sportify.utility.ImageViewUtil;
import com.vendor.database.DB;
import com.vendor.utility.ModelUtil;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * @author Crispin A.
 *
 */
public class FixtureInformationController {

	/**************************************************************************
	 * 
	 * Private FXML Fields
	 * 
	 **************************************************************************/

	@FXML
	private ComboBox<String> homeTeamComboBox;
	@FXML
	private ComboBox<String> awayTeamComboBox;
	@FXML
	private ImageView homeTeamImageView;
	@FXML
	private ImageView awayTeamImageView;
	@FXML
	private StackPane homeFormStackPane;
	@FXML
	private StackPane awayFormStackPane;

	/**************************************************************************
	 * 
	 * Private Fields
	 * 
	 **************************************************************************/

	private StringProperty homeTeamProperty = new SimpleStringProperty();
	private StringProperty awayTeamProperty = new SimpleStringProperty();
	private final int BADGE_SIZE = 120;

	/**
	 * 
	 * Sets the ComboBox<String> (homeTeamComboBox) to the same value of the
	 * homeTeamProperty. Useful when homeTeamComboBox selection needs to be changed
	 * without using the actual comboBox.
	 * 
	 */
	private final ChangeListener<String> homeTeamPropertyChangeListener = new ChangeListener<String>() {
		@Override
		public void changed(ObservableValue<? extends String> item, String oldValue, String newValue) {
			homeTeamComboBox.getSelectionModel().select(newValue);
		}
	};

	/**
	 * 
	 * Sets the ComboBox<String> (awayTeamComboBox) to the same value of the
	 * awayTeamProperty. Useful when awayTeamComboBox selection needs to be changed
	 * without using the actual comboBox.
	 * 
	 */
	private final ChangeListener<String> awayTeamPropertyChangeListener = new ChangeListener<String>() {
		@Override
		public void changed(ObservableValue<? extends String> item, String oldValue, String newValue) {
			awayTeamComboBox.getSelectionModel().select(newValue);
		}
	};

	/**************************************************************************
	 * 
	 * Initialize Method
	 * 
	 **************************************************************************/

	public void initialize() throws SQLException {
		homeTeamProperty.addListener(homeTeamPropertyChangeListener);
		awayTeamProperty.addListener(awayTeamPropertyChangeListener);
		ImageViewUtil.initializeImageView(homeTeamImageView, BADGE_SIZE);
		ImageViewUtil.initializeImageView(awayTeamImageView, BADGE_SIZE);
	}

	/***************************************************************************
	 * 
	 * Getter and Setter Methods
	 * 
	 **************************************************************************/

	public ComboBox<String> getHomeTeamComboBox() {
		return homeTeamComboBox;
	}

	public ComboBox<String> getAwayTeamComboBox() {
		return awayTeamComboBox;
	}

	public ImageView getHomeTeamImageView() {
		return homeTeamImageView;
	}

	public ImageView getAwayTeamImageView() {
		return awayTeamImageView;
	}

	public StackPane getHomeFormStackPane() {
		return homeFormStackPane;
	}

	public StackPane getAwayFormStackPane() {
		return awayFormStackPane;
	}

	public void setHomeTeamName(String teamName) {
		this.homeTeamProperty.set(teamName);
	}

	public void setAwayTeamName(String teamName) {
		this.awayTeamProperty.set(teamName);
	}

	/***************************************************************************
	 * 
	 * Other Methods
	 * 
	 **************************************************************************/

	/***************************************************************************
	 * ComboBox Methods *
	 **************************************************************************/

	/**
	 * 
	 * Sets the items in the team comboBoxes to teams in the league that is passed
	 * as an argument.
	 * 
	 * @param leagueName
	 *            - name of the league
	 * @throws SQLException
	 */
	public void updateTeamComboBoxes(String leagueName) throws SQLException {
		ObservableList<String> teams = FXCollections.observableArrayList();
		League league = ModelUtil.toModel((String) DB.table("Leagues").where("name", leagueName).first(), League.class);
		for (String team : league.getTeamNames())
			teams.add(team);
		homeTeamComboBox.setItems(teams);
		awayTeamComboBox.setItems(teams);
	}

	public void clearTeamComboBoxes() {
		homeFormStackPane.getChildren().clear();
		awayFormStackPane.getChildren().clear();
	}

}

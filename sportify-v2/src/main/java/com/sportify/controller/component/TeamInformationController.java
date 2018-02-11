package com.sportify.controller.component;

import java.sql.SQLException;

import com.sportify.model.Standing;
import com.sportify.model.Team;
import com.sportify.utility.ImageUtil;
import com.sportify.utility.ImageViewUtil;
import com.sportify.utility.NumberUtil;
import com.sportify.view.FormPane;
import com.vendor.database.DB;
import com.vendor.utility.ModelUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * @author Crispin A.
 *
 */
public class TeamInformationController {

	/**************************************************************************
	 * 
	 * Private FXML Fields
	 * 
	 **************************************************************************/

	@FXML
	private ImageView teamImage;
	@FXML
	private Label teamLabel;
	@FXML
	private Label position;
	@FXML
	private Label positionLabel;
	@FXML
	private StackPane formStackPane;
	@FXML
	private Label form;
	@FXML
	private Label recordLabel;
	@FXML
	private Label record;

	/**************************************************************************
	 * 
	 * Private Fields
	 * 
	 **************************************************************************/

	private final int BADGE_SIZE = 120;

	/**************************************************************************
	 * 
	 * Initialize Method
	 * 
	 **************************************************************************/

	public void initialize() {
		ImageViewUtil.initializeImageView(teamImage, BADGE_SIZE);
	}

	/***************************************************************************
	 * 
	 * Other Methods
	 * 
	 **************************************************************************/

	/**
	 * 
	 * Makes the initially invisible labels visible and updates all the team
	 * information depending on the team passed as argument.
	 * 
	 * @param teamName-
	 *            name of team
	 * @throws SQLException
	 */
	public void updateTeamInformation(String teamName) throws SQLException {
		makeLabelsVisible();
		setTeamImageView(teamName);
		setTeamInformation(teamName);
	}

	/**
	 * 
	 * Sets the team badge.
	 * 
	 * @param teamName
	 *            - name of team
	 */
	private void setTeamImageView(String teamName) {
		teamImage.setImage(new Image(ImageUtil.getBadge(this, teamName + ".png")));
	}

	/**
	 * 
	 * Sets all the team information.
	 * 
	 * @param teamName
	 *            - name of team
	 * @throws SQLException
	 */
	private void setTeamInformation(String teamName) throws SQLException {
		teamLabel.setText(teamName);
		setStats(teamName);
		formStackPane.getChildren().clear();
		formStackPane.getChildren().add(new FormPane(teamName).getForPane());
	}

	/**
	 * 
	 * Sets the team position label and win-draw-loss record label.
	 * 
	 * @param teamName
	 *            - name of team
	 * @throws SQLException
	 */
	private void setStats(String teamName) throws SQLException {
		Team team = ModelUtil.toModel((String) DB.table("Teams").where("name", teamName).first(), Team.class);
		Standing standing = team.getStanding();
		positionLabel.setText(NumberUtil.getOrdinalFor(standing.getPosition()));
		recordLabel.setText(standing.getW() + "-" + standing.getD() + "-" + standing.getL());
	}

	private void makeLabelsVisible() {
		position.setVisible(true);
		form.setVisible(true);
		record.setVisible(true);
	}

}

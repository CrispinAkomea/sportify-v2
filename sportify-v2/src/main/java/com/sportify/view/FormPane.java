package com.sportify.view;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;

import com.sportify.model.Statistic;
import com.sportify.model.Team;
import com.vendor.database.DB;
import com.vendor.utility.ModelUtil;

import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;

public class FormPane {

	private FlowPane formPane;
	private final Team team;
	private final Statistic[] statistics;

	public FormPane(String teamName) throws SQLException {
		formPane = new FlowPane();
		team = ModelUtil.toModel((String) DB.table("Teams").where("name", teamName).first(), Team.class);
		statistics = ModelUtil.toModels((String) DB.table("Statistics").where("HomeTeam", team.getAlias())
				.orWhere("AwayTeam", team.getAlias()).orderBy("id", "desc").take(5), Statistic[].class);
		setFormBox();
	}

	private void setFormBox() throws SQLException {
		Collections.reverse(Arrays.asList(statistics));
		for (Statistic statistic : statistics) {
			addResultToFormPane(statistic);
		}
	}

	private void addResultToFormPane(Statistic statistic) {
		if (statistic.getFTR() == 'D') {
			Label draw = new Label("D");
			draw.getStyleClass().add("form-draw");
			formPane.getChildren().add(draw);
		} else if (statistic.getHomeTeam().equals(team.getAlias())) {
			if (statistic.getFTR() == 'H') {
				Label win = new Label("W");
				win.getStyleClass().add("form-win");
				formPane.getChildren().add(win);
			} else {
				Label loss = new Label("L");
				loss.getStyleClass().add("form-loss");
				formPane.getChildren().add(loss);
			}
		} else {
			if (statistic.getFTR() == 'A') {
				Label win = new Label("W");
				win.getStyleClass().add("form-win");
				formPane.getChildren().add(win);
			} else {
				Label loss = new Label("L");
				loss.getStyleClass().add("form-loss");
				formPane.getChildren().add(loss);
			}
		}
	}
	
	public FlowPane getForPane() {
		return formPane;
	}

}

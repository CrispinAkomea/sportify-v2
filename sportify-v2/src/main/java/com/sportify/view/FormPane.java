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

public class FormPane extends FlowPane {

	private Team team;
	private Statistic[] statistics;

	public FormPane(String teamName) throws SQLException {
		initialize(teamName);
	}

	private void initialize(String teamName) throws SQLException {
		team = ModelUtil.toModel((String) DB.table("Teams").where("name", teamName).first(), Team.class);
		statistics = ModelUtil.toModels((String) DB.table("Statistics").where("HomeTeam", team.getAlias())
				.orWhere("AwayTeam", team.getAlias()).orderBy("id", "desc").take(5), Statistic[].class);
		Collections.reverse(Arrays.asList(statistics));
		for (Statistic statistic : statistics) {
			addToFormPane(statistic);
		}
	}

	private void addToFormPane(Statistic statistic) {
		if (statistic.getFTR() == 'D') {
			getChildren().add(new ResultLabel("D", "form-draw"));
		} else if (statistic.getHomeTeam().equals(team.getAlias())) {
			if (statistic.getFTR() == 'H') {
				getChildren().add(new ResultLabel("W", "form-win"));
			} else {
				getChildren().add(new ResultLabel("L", "form-loss"));
			}
		} else {
			if (statistic.getFTR() == 'A') {
				getChildren().add(new ResultLabel("W", "form-win"));
			} else {
				getChildren().add(new ResultLabel("L", "form-loss"));
			}
		}
	}

}

class ResultLabel extends Label {

	public ResultLabel(String text, String style) {
		super(text);
		getStyleClass().add(style);
	}

}

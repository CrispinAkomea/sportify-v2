package caa.sportify.view;

import java.sql.SQLException;

import caa.sportify.model.Fixture;
import caa.sportify.model.League;
import caa.sportify.model.Team;
import caa.sportify.utility.ImageUtil;
import caa.vendor.database.DB;
import caa.vendor.utility.ModelUtil;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class FixtureHBox extends HBox {

	private final Fixture fixture;
	private final League league;
	private final Team homeTeam;
	private final Team awayTeam;

	public FixtureHBox(Fixture fixture) throws SQLException {
		this.fixture = fixture;
		league = ModelUtil.toModel((String) DB.table("Leagues").where("division", fixture.getDivision()).first(),
				League.class);
		homeTeam = ModelUtil.toModel((String) DB.table("Teams").where("alias", fixture.getHomeTeam()).first(),
				Team.class);
		awayTeam = ModelUtil.toModel((String) DB.table("Teams").where("alias", fixture.getAwayTeam()).first(),
				Team.class);
		this.getStyleClass().add("fixture-box");
		this.getChildren().addAll(getBadgePane(), getDetailPane());
	}

	private StackPane getBadgePane() {
		Image badge = new Image(ImageUtil.getBadge(this, this.homeTeam.getName() + ".png"));
		ImageView imageView = new ImageView(badge);
		imageView.setFitHeight(36);
		imageView.setPreserveRatio(true);
		imageView.setSmooth(true);
		imageView.setCache(true);
		StackPane left = new StackPane();
		left.setPrefWidth(70);
		left.getStyleClass().add("left");
		left.getChildren().addAll(imageView);
		return left;
	}

	private Pane getDetailPane() {
		Label game = new Label(this.fixture.getHomeTeam() + " vs " + this.fixture.getAwayTeam());
		Label stadium = new Label(this.homeTeam.getStadium());
		game.getStyleClass().add("label");
		stadium.getStyleClass().add("label");
		VBox right = new VBox();
		right.setPrefWidth(187);
		right.getStyleClass().add("right");
		right.getChildren().addAll(game, stadium);
		return right;
	}
	
	public Fixture getFixture() {
		return fixture;
	}

	public League getLeague() {
		return league;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

}

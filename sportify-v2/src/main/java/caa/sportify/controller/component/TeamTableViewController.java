package caa.sportify.controller.component;

import java.sql.SQLException;

import caa.sportify.model.Statistic;
import caa.sportify.model.Team;
import caa.vendor.database.DB;
import caa.vendor.utility.ModelUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * @author Crispin A.
 *
 */
public class TeamTableViewController {

	/**************************************************************************
	 * 
	 * Private FXML Fields
	 * 
	 **************************************************************************/

	@FXML
	private TableView<Statistic> teamTableView;
	@FXML
	private TableColumn<Statistic, Integer> weekColumn;
	@FXML
	private TableColumn<Statistic, String> dateColumn;
	@FXML
	private TableColumn<Statistic, String> gameColumn;
	@FXML
	private TableColumn<Statistic, Character> resultColumn;

	/**************************************************************************
	 * 
	 * Private Fields
	 * 
	 **************************************************************************/

	private StringProperty teamProperty = new SimpleStringProperty();
	private final int ROW_SIZE = 40;

	/**************************************************************************
	 * 
	 * Initialize Method
	 * 
	 **************************************************************************/

	public void initialize() {
		teamTableView.setFixedCellSize(ROW_SIZE);
		weekColumn.setCellFactory(column -> {
			TableCell<Statistic, Integer> cell = new TableCell<Statistic, Integer>() {
				@Override
				protected void updateItem(Integer item, boolean empty) {
					super.updateItem(item, empty);
					if (!empty) {
						setText("GW" + (this.getTableRow().getIndex() + 1) + "");
					} else {
						setText(null);
					}
				}
			};
			return cell;
		});
		dateColumn.setCellValueFactory(new PropertyValueFactory<Statistic, String>("date"));
		gameColumn.setCellFactory(tableColumn -> {
			TableCell<Statistic, String> cell = new TableCell<Statistic, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (!empty) {
						Statistic statistic = this.getTableRow().getItem();
						HBox result = new HBox();
						result.getStyleClass().add("result");
						Label homeTeam = new Label(statistic.getHomeTeam());
						homeTeam.getStyleClass().add("homeTeam");
						Label scoreline = new Label(statistic.getFTHG() + "  -  " + statistic.getFTAG());
						scoreline.getStyleClass().add("scoreline");
						Label awayTeam = new Label(statistic.getAwayTeam());
						awayTeam.getStyleClass().add("awayTeam");
						result.getChildren().addAll(homeTeam, scoreline, awayTeam);
						homeTeam.prefWidthProperty().bind((gameColumn.widthProperty().subtract(67.0)).multiply(0.5));
						awayTeam.prefWidthProperty().bind((gameColumn.widthProperty().subtract(67.0)).multiply(0.5));
						setGraphic(result);
					} else {
						setGraphic(null);
					}
				}
			};
			return cell;
		});
		resultColumn.setCellFactory(tableColumn -> {
			TableCell<Statistic, Character> cell = new TableCell<Statistic, Character>() {
				@Override
				protected void updateItem(Character item, boolean empty) {
					super.updateItem(item, empty);
					if (!empty) {
						Statistic statistic = this.getTableRow().getItem();
						Team team = null;
						try {
							team = ModelUtil.toModel(
									(String) DB.table("Teams").where("name", teamProperty.getValue()).first(),
									Team.class);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						if (statistic.getHomeTeam().equals(team.getAlias())) {
							if (statistic.getFTR() == 'H') {
								Label win = new Label("W");
								win.getStyleClass().add("form-win");
								setGraphic(win);
							} else if (statistic.getFTR() == 'D') {
								Label draw = new Label("D");
								draw.getStyleClass().add("form-draw");
								setGraphic(draw);
							} else {
								Label loss = new Label("L");
								loss.getStyleClass().add("form-loss");
								setGraphic(loss);
							}
						} else {
							if (statistic.getFTR() == 'A') {
								Label win = new Label("W");
								win.getStyleClass().add("form-win");
								setGraphic(win);
							} else if (statistic.getFTR() == 'D') {
								Label draw = new Label("D");
								draw.getStyleClass().add("form-draw");
								setGraphic(draw);
							} else {
								Label loss = new Label("L");
								loss.getStyleClass().add("form-loss");
								setGraphic(loss);
							}
						}
					} else {
						setGraphic(null);
					}
				}
			};
			return cell;
		});
	}

	/***************************************************************************
	 * 
	 * Getter and Setter Methods
	 * 
	 **************************************************************************/

	public TableView<Statistic> getTeamTableView() {
		return teamTableView;
	}

	public void bindTeamProperty(StringProperty property) {
		teamProperty.bind(property);
	}

	/***************************************************************************
	 * 
	 * Other Methods
	 * 
	 **************************************************************************/

	public void setItems(ObservableList<Statistic> items, double size, double tolerance) {
		teamTableView.setItems(items);
		teamTableView.prefHeightProperty().bind(teamTableView.fixedCellSizeProperty().multiply(size + tolerance));
		teamTableView.refresh();
		removeHeader();
	}

	private void removeHeader() {
		Pane header = (Pane) teamTableView.lookup("TableHeaderRow");
		if (header != null && header.isVisible()) {
			header.setMaxHeight(0);
			header.setMinHeight(0);
			header.setPrefHeight(0);
			header.setVisible(false);
			header.setManaged(false);
		}
	}

}

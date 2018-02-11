package com.sportify.controller.component;

import com.sportify.model.Standing;
import com.sportify.utility.ImageUtil;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Crispin
 *
 */
public class LeagueTableViewController {

	/**************************************************************************
	 * 
	 * Private FXML Fields
	 * 
	 **************************************************************************/

	@FXML
	private TableView<Standing> leagueTableView;
	@FXML
	private TableColumn<Standing, Integer> positionColumn;
	@FXML
	private TableColumn<Standing, Integer> movementColumn;
	@FXML
	private TableColumn<Standing, String> teamColumn;
	@FXML
	private TableColumn<Standing, Integer> playColumn;
	@FXML
	private TableColumn<Standing, Integer> winColumn;
	@FXML
	private TableColumn<Standing, Integer> drawColumn;
	@FXML
	private TableColumn<Standing, Integer> lossColumn;
	@FXML
	private TableColumn<Standing, Integer> gfColumn;
	@FXML
	private TableColumn<Standing, Integer> gaColumn;
	@FXML
	private TableColumn<Standing, Integer> gdColumn;
	@FXML
	private TableColumn<Standing, Integer> pointColumn;

	/**************************************************************************
	 * 
	 * Private Fields
	 * 
	 **************************************************************************/

	private final int ROW_SIZE = 40;
	private final Image greenArrow = new Image(ImageUtil.getIcon(this, "/up-15.png"));
	private final Image greyDot = new Image(ImageUtil.getIcon(this, "/circle-10.png"));
	private final Image redArrow = new Image(ImageUtil.getIcon(this, "/down-15.png"));

	/**************************************************************************
	 * 
	 * Initialize Method
	 * 
	 **************************************************************************/

	public void initialize() {
		leagueTableView.setFixedCellSize(ROW_SIZE);
		positionColumn.setCellValueFactory(new PropertyValueFactory<Standing, Integer>("position"));
		movementColumn.setCellValueFactory(new PropertyValueFactory<Standing, Integer>("movement"));
		movementColumn.setCellFactory(column -> {
			TableCell<Standing, Integer> cell = new TableCell<Standing, Integer>() {
				ImageView imageView = new ImageView();

				@Override
				protected void updateItem(Integer movement, boolean empty) {
					super.updateItem(movement, empty);
					if (empty)
						setGraphic(null);
					else {
						if (movement > 0)
							imageView.setImage(greenArrow);
						else if (movement < 0)
							imageView.setImage(redArrow);
						else
							imageView.setImage(greyDot);
						setGraphic(imageView);
						setTooltip(new Tooltip(String.valueOf(movement)));
					}
				}
			};
			return cell;
		});
		teamColumn.setCellValueFactory(new PropertyValueFactory<Standing, String>("name"));
		playColumn.setCellValueFactory(new PropertyValueFactory<Standing, Integer>("P"));
		winColumn.setCellValueFactory(new PropertyValueFactory<Standing, Integer>("W"));
		drawColumn.setCellValueFactory(new PropertyValueFactory<Standing, Integer>("D"));
		lossColumn.setCellValueFactory(new PropertyValueFactory<Standing, Integer>("L"));
		gfColumn.setCellValueFactory(new PropertyValueFactory<Standing, Integer>("GF"));
		gaColumn.setCellValueFactory(new PropertyValueFactory<Standing, Integer>("GA"));
		gdColumn.setCellValueFactory(new PropertyValueFactory<Standing, Integer>("GD"));
		pointColumn.setCellValueFactory(new PropertyValueFactory<Standing, Integer>("points"));
	}

	/***************************************************************************
	 * 
	 * Getter and Setter Methods
	 * 
	 **************************************************************************/

	public TableView<Standing> getLeagueTableView() {
		return leagueTableView;
	}

	public TableColumn<Standing, String> getTeamColumn() {
		return teamColumn;
	}

	/***************************************************************************
	 * 
	 * Other Methods
	 * 
	 **************************************************************************/

	/***************************************************************************
	 * TableView Methods *
	 **************************************************************************/

	public void setItems(ObservableList<Standing> items, double size, double tolerance) {
		leagueTableView.setItems(items);
		leagueTableView.prefHeightProperty().bind(leagueTableView.fixedCellSizeProperty().multiply(size + tolerance));
		leagueTableView.refresh();
	}

}

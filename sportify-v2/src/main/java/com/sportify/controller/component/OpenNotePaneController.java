package com.sportify.controller.component;

import com.sportify.utility.ImageUtil;
import com.sportify.view.NotePopupPane;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * @author Crispin A.
 *
 */
public class OpenNotePaneController {

	/**************************************************************************
	 * 
	 * Private FXML Fields
	 * 
	 **************************************************************************/

	@FXML
	private AnchorPane entirePane;
	@FXML
	private ImageView noteImageView;

	/**************************************************************************
	 * 
	 * Private Fields
	 * 
	 **************************************************************************/

	private NotePopupPane notePopup;
	private final Image darkPlusImage = new Image(ImageUtil.getIcon(this, "/plus-40-1.png"));
	private final Image lightPlusImage = new Image(ImageUtil.getIcon(this, "/plus-40-2.png"));

	/**
	 * 
	 * Plus image becomes lighter on mouse exit.
	 * 
	 */
	private EventHandler<MouseEvent> enterAction = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			noteImageView.setImage(lightPlusImage);
			event.consume();
		}
	};

	/**
	 * 
	 * Plus image becomes darker on mouse exit.
	 * 
	 */
	private EventHandler<MouseEvent> exitAction = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			noteImageView.setImage(darkPlusImage);
			event.consume();
		}
	};

	/**
	 * 
	 * Opens a new note when clicked.
	 * 
	 */
	private EventHandler<MouseEvent> clickAction = new EventHandler<MouseEvent>() {
		@Override
		public void handle(MouseEvent event) {
			try {
				notePopup = new NotePopupPane();
				notePopup.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
			event.consume();
		}
	};

	/**************************************************************************
	 * 
	 * Initialize Method
	 * 
	 **************************************************************************/

	public void initialize() {
		noteImageView.setImage(darkPlusImage);
		entirePane.addEventHandler(MouseEvent.MOUSE_ENTERED, enterAction);
		entirePane.addEventHandler(MouseEvent.MOUSE_EXITED, exitAction);
		entirePane.addEventHandler(MouseEvent.MOUSE_CLICKED, clickAction);
	}

}

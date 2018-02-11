package com.sportify.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * @author Crispin A.
 *
 */
public class NotePopupController {

	/**************************************************************************
	 * 
	 * Private FXML Fields
	 * 
	 **************************************************************************/

	@FXML
	private TextArea noteBody;
	@FXML
	private Button closeButton;
	@FXML
	private Button openButton;
	@FXML
	private Button saveButton;

	/**************************************************************************
	 * 
	 * Private Fields
	 * 
	 **************************************************************************/

	/**
	 * 
	 * Closes the note popup.
	 * 
	 */
	private final EventHandler<ActionEvent> closeAction = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			Stage stage = (Stage) closeButton.getScene().getWindow();
			stage.close();
		}
	};

	/**
	 * 
	 * Opens a text file in the note popup.
	 * 
	 */
	private final EventHandler<ActionEvent> openAction = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			buttonsSetDisable(true);
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open File");
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
			File file = fileChooser.showOpenDialog(null);
			if (file != null) {
				try {
					noteBody.setText(FileUtils.readFileToString(file, Charset.defaultCharset()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			buttonsSetDisable(false);
			event.consume();
		}
	};

	/**
	 * 
	 * Saves a note as a text file.
	 * 
	 */
	private final EventHandler<ActionEvent> saveAction = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			buttonsSetDisable(true);
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Save File");
			fileChooser.setInitialFileName("Sportify note.txt");
			File file = fileChooser.showSaveDialog(null);
			if (file != null) {
				try {
					saveFile(file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			buttonsSetDisable(false);
			Stage stage = (Stage) saveButton.getScene().getWindow();
			stage.close();
			event.consume();
		}
	};

	/**************************************************************************
	 * 
	 * Initialize Method
	 * 
	 **************************************************************************/

	public void initialize() {
		closeButton.setOnAction(closeAction);
		openButton.setOnAction(openAction);
		saveButton.setOnAction(saveAction);
	}

	/***************************************************************************
	 * 
	 * Other Methods
	 * 
	 * @throws IOException
	 * 
	 **************************************************************************/

	/**
	 * 
	 * Saves the text from the TextArea (noteBody) to the file.
	 * 
	 * @param file
	 *            - file to be saved
	 * @throws IOException
	 */
	private void saveFile(File file) throws IOException {
		FileUtils.writeStringToFile(file, noteBody.getText(), Charset.defaultCharset());
	}

	private void buttonsSetDisable(boolean value) {
		openButton.setDisable(value);
		saveButton.setDisable(value);
	}

}

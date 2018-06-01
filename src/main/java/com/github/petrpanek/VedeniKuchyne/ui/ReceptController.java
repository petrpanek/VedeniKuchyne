package com.github.petrpanek.VedeniKuchyne.ui;

import java.net.URL;
import java.util.ResourceBundle;

import com.github.petrpanek.VedeniKuchyne.dao.ReceptDAO;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ReceptController extends AnchorPane implements Initializable {
	
	@FXML
	private VBox vypis; 

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		for (Object[] r : ReceptDAO.getAllRecipes() ) {
			HBox item = new HBox();
			
			for (int i = 0; i < r.length; i++) {
				Label text = new Label();
				text.setText(r[i].toString());
				item.getChildren().add(text);
			}
			
			vypis.getChildren().add(item);
		}
	}

}

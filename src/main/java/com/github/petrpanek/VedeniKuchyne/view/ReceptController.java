package com.github.petrpanek.VedeniKuchyne.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.github.petrpanek.VedeniKuchyne.dao.ReceptDAO;
import com.github.petrpanek.VedeniKuchyne.logika.Recept;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ReceptController extends AnchorPane implements Initializable {
	
	@FXML private AnchorPane rootPane;
	@FXML private Button pridejRecept;
	@FXML private Button smazRecept;	
	@FXML private Button upravRecept;
	@FXML private VBox vypis;
	@FXML private ScrollPane scroll;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nactiRecepty();
		
		for (Integer mnozstvi : ReceptDAO.getAmount(6)) {
			System.out.println(mnozstvi);
		}
	}
	
	@FXML
	public void nactiPridaniReceptu(ActionEvent event) throws IOException {
		Parent pridaniReceptuParent = FXMLLoader.load(getClass().getResource("PridejRecept.fxml"));
		Scene pridaniReceptuScene = new Scene(pridaniReceptuParent);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.setScene(pridaniReceptuScene);
		appStage.show();
	}
	
	@FXML void nactiSmazaniReceptu(ActionEvent event) throws IOException {
		Parent smazaniReceptuParent = FXMLLoader.load(getClass().getResource("SmazRecept.fxml"));
		Scene smazaniReceptuScene = new Scene(smazaniReceptuParent);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.setScene(smazaniReceptuScene);
		appStage.show();
	}
	
	@FXML void nactiUpravuReceptu(ActionEvent event) throws IOException {
		Parent pridaniReceptuParent = FXMLLoader.load(getClass().getResource("UpravRecept.fxml"));
		Scene pridaniReceptuScene = new Scene(pridaniReceptuParent);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.setScene(pridaniReceptuScene);
		appStage.show();
	}
	
	public void nactiRecepty() {
		boolean isGrey = true;
		
		for (Object[] r : ReceptDAO.getAllRecipes()) {
			HBox item = new HBox();
			item.setPrefWidth(USE_COMPUTED_SIZE);
			item.setPrefHeight(100);
			
			if (isGrey) {
				item.setStyle(" -fx-background-color: #e8e8e8 ");
				isGrey = !isGrey;
			}
			
			for (int i = 0; i < r.length; i++) {
				Label text = new Label();
				text.setMinWidth(120);
				text.setMinHeight(100);
				text.setWrapText(true);
				
				if (i == 2) {
					text.setAlignment(Pos.TOP_LEFT);
				} else {
					text.setAlignment(Pos.CENTER);
				}
				
				text.setText(r[i].toString());
				item.getChildren().add(text);
			}
			
			vypis.getChildren().add(item);
			scroll.setContent(vypis);
		}
	}

}

package com.github.petrpanek.VedeniKuchyne.view;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import com.github.petrpanek.VedeniKuchyne.dao.ReceptDAO;

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
		loadRecipes();
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
	
	public void loadRecipes() {
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
				int id = (int) r[0];
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
				
				if (i == r.length - 1) {
					item.getChildren().add(setAmountIngredience(id));
					item.getChildren().add(setIngredience(id));
				}
			}
			
			vypis.getChildren().add(item);
			scroll.setContent(vypis);
		}
	}
	
	public Label setIngredience(int idReceptu) {
		Label ingredience = new Label();
		String text = String.join(", ", ReceptDAO.getIngredients(idReceptu));
		
		ingredience.setMinWidth(120);
		ingredience.setMinHeight(100);
		ingredience.setWrapText(true);
		ingredience.setAlignment(Pos.CENTER);
		ingredience.setText(text);
		
		return ingredience;
	}
	
	public Label setAmountIngredience(int idReceptu) {
		Label amount = new Label();
		StringBuilder text = new StringBuilder();
		Iterator<Integer> integerIterator = ReceptDAO.getAmount(idReceptu).iterator();
		
		while (integerIterator.hasNext()) {
			text.append(integerIterator.next());
			
			if (integerIterator.hasNext()) {
				text.append("x, ");
			} else {
				text.append("x");
			}
		}
		
		amount.setMinWidth(120);
		amount.setMinHeight(100);
		amount.setWrapText(true);
		amount.setAlignment(Pos.CENTER);
		amount.setText(text.toString());
		
		return amount;
	}

}

package com.github.petrpanek.VedeniKuchyne.view;

import java.io.IOException;

import com.github.petrpanek.VedeniKuchyne.dao.ReceptDAO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Controller pro SmazRecept.fxml
 * Controller obsahujici metody pro smazani a zruseni smazani receptu
 * 
 * @author 	Petr Panek
 * @version 1.0
 */
public class SmazReceptController extends AnchorPane {
	
	@FXML TextField idReceptu;
	@FXML Button smazBtn;
	@FXML Button zrusSmazaniBtn;
	
	/**
	 * Metoda pro smazani receptu.
	 * Ke smazani slouzi prejate ID receptu z TextFieldu.
	 * 
	 * @param event Event po stisknuti Buttonu
	 */
	@FXML
	public void smazRecept(ActionEvent event) {
		String recept = idReceptu.getText();
		
		ReceptDAO.deleteRecept(Integer.parseInt(recept));
		
		zrusSmazaniBtn.fire();
	}
	
	/**
	 * Metoda pro vraceni se na uvodni scenu.
	 * 
	 * @param event Event po stisknuti Buttonu
	 * @throws IOException V pripadne spatneho nacteni FXMLLoaderu
	 */
	@FXML
	public void zrusSmazani(ActionEvent event) throws IOException {
		Parent receptViewParent = FXMLLoader.load(getClass().getResource("ReceptView.fxml"));
		Scene receptViewScene = new Scene(receptViewParent);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.setScene(receptViewScene);
		appStage.show();
	}
}

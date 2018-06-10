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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Controller pro UpravRecept.fxml
 * Controller obsahujici metody pro ulozeni upravy a zruseni upravy receptu.
 * 
 * @author 	Petr Panek
 * @version 1.0
 */
public class UpravReceptController extends AnchorPane {

	@FXML private TextField idReceptu;
	@FXML private TextField nazevReceptu;
	@FXML private TextArea postupReceptu;
	@FXML private TextField obtiznostReceptu;
	@FXML private Button ulozUpraveniBtn;
	@FXML private Button zrusUpraveniBtn;
	
	/**
	 * Metoda pro ulozeni upravy Receptu do databaze.
	 * Metoda prevezme udaje z UI a nasledne je aktualizuje.
	 * 
	 * @param event Event po stisknuti Buttonu
	 */
	@FXML
	public void ulozUpravuReceptu(ActionEvent event) {
		int id = Integer.parseInt(idReceptu.getText());
		String nazev = nazevReceptu.getText();
		String postup = postupReceptu.getText();
		double obtiznost = Double.parseDouble(obtiznostReceptu.getText());
		
		ReceptDAO.updateRecept(id, nazev, postup, obtiznost);
		
		zrusUpraveniBtn.fire();
	}
	
	/**
	 * Metoda pro vraceni se na uvodni scenu.
	 * 
	 * @param event Event po stisknuti Buttonu
	 * @throws IOException V pripadne spatneho nacteni FXMLLoaderu
	 */
	@FXML
	public void zrusUlozeniReceptu(ActionEvent event) throws IOException {
		Parent receptViewParent = FXMLLoader.load(getClass().getResource("ReceptView.fxml"));
		Scene receptViewScene = new Scene(receptViewParent);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.setScene(receptViewScene);
		appStage.show();
	}
}

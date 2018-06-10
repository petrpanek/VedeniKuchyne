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
 * Controller pro PridejRecept.fxml
 * Controller obsahujici metody pro ulozeni a zruseni ulozeni receptu
 * 
 * @author 	Petr Panek
 * @version 1.0
 */
public class PridaniReceptuController extends AnchorPane {

	@FXML private TextField nazevReceptu;
	@FXML private TextArea postupReceptu;
	@FXML private TextField surovinyReceptu;
	@FXML private TextField pocetSurovin;
	@FXML private TextField obtiznostReceptu;
	@FXML private Button ulozReceptBtn;
	@FXML private Button zrusUlozeniBtn;
	
	/**
	 * Metoda pro ulozeni Receptu do databaze.
	 * Metoda prevezme udaje z UI a nasledne je ulozi.
	 * 
	 * @param event Event po stisknuti Buttonu
	 */
	@FXML
	public void ulozRecept(ActionEvent event) {
		String nazev = nazevReceptu.getText();
		String postup = postupReceptu.getText();
		String[] suroviny = surovinyReceptu.getText().split(",");
		String[] pocet = pocetSurovin.getText().split(",");
		double obtiznost = Double.parseDouble(obtiznostReceptu.getText());
		
		ReceptDAO.saveRecept(nazev, postup, suroviny, pocet, obtiznost);
		
		zrusUlozeniBtn.fire();
	}
	
	/**
	 * Metoda pro vraceni se na uvodni scenu.
	 * 
	 * @param event Event po stisknuti Buttonu
	 * @throws IOException V pripadne spatneho nacteni FXMLLoaderu
	 */
	@FXML
	public void zrusUlozeni(ActionEvent event) throws IOException {
		Parent receptViewParent = FXMLLoader.load(getClass().getResource("ReceptView.fxml"));
		Scene receptViewScene = new Scene(receptViewParent);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.setScene(receptViewScene);
		appStage.show();
	}
}

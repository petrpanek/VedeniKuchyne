package com.github.petrpanek.VedeniKuchyne.view;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.github.petrpanek.VedeniKuchyne.dao.ReceptDAO;
import com.github.petrpanek.VedeniKuchyne.logika.Potravina;
import com.github.petrpanek.VedeniKuchyne.logika.Recept;
import com.github.petrpanek.VedeniKuchyne.logika.ReceptPotravina;
import com.github.petrpanek.VedeniKuchyne.util.HibernateUtil;

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

public class PridaniReceptuController extends AnchorPane {

	@FXML private TextField nazevReceptu;
	@FXML private TextArea postupReceptu;
	@FXML private TextField surovinyReceptu;
	@FXML private TextField pocetSurovin;
	@FXML private TextField obtiznostReceptu;
	@FXML private Button ulozReceptBtn;
	@FXML private Button zrusUlozeniBtn;
	
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
	
	@FXML
	public void zrusUlozeni(ActionEvent event) throws IOException {
		Parent receptViewParent = FXMLLoader.load(getClass().getResource("ReceptView.fxml"));
		Scene receptViewScene = new Scene(receptViewParent);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.setScene(receptViewScene);
		appStage.show();
	}
}

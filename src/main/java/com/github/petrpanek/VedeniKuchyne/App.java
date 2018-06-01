package com.github.petrpanek.VedeniKuchyne;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application
{
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("view/ReceptView.fxml"));
			Parent root = loader.load();
			
			primaryStage.setTitle("Receptar");
			primaryStage.setMinWidth(1024);
			primaryStage.setMinHeight(600);
	        primaryStage.setScene(new Scene(root));
	        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		/*
		Session session = null;
		Transaction trns = null;
		
		Recept kulajda = new Recept("Kulajda", "Nakrajet, uvarit", 2.2);
		Potravina houska = new Potravina("Houska", 3);
		
		ReceptPotravina receptPotravina = new ReceptPotravina();
		receptPotravina.setRecept(kulajda);
		receptPotravina.setPotravina(houska);
		receptPotravina.setMnozstvi(2);
		
		session = HibernateUtil.getSessionFactory().openSession();
		trns = session.beginTransaction();
		
		session.save(receptPotravina);
		session.getTransaction().commit();
		*/
		
		launch(args);
    }

	
}

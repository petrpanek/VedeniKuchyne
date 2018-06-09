package com.github.petrpanek.VedeniKuchyne;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application
{

	Scene scene1, scene2;
	
	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("view/ReceptView.fxml"));
			Scene scene = new Scene(root);
			
			stage.setTitle("Receptar");
			stage.setMaxWidth(1024);
			stage.setMaxHeight(600);
	        stage.setScene(scene);
	        stage.show();
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

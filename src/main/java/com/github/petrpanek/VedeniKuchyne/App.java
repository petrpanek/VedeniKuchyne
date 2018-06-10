package com.github.petrpanek.VedeniKuchyne;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
*  Třída App - obsahuje metody provadejici spusteni aplikace.
*  Používá se pro spusteni aplikace a konstrukce okna.
*
*  Tato trida je soucasti aplikace receptar.
*
* @author     Petr Panek
* @version    1.0
*/
public class App extends Application
{
	 /**
	 * Metoda se stara o pocatecni nacteni uvodniho okna.
	 *
	 * @param stage Stage, na kterem se budou konstruovat jednotliva okna
	 */
	@Override
	public void start(Stage stage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("view/ReceptView.fxml"));
			Scene scene = new Scene(root);
			
			stage.setTitle("Receptar");
			stage.setMaxWidth(1024);
			stage.setMaxHeight(620);
	        stage.setScene(scene);
	        stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metoda starajici se o spusteni aplikace.
	 *
	 * @param args Argumenty pro aplikaci
	 */
	public static void main(String[] args) {
		launch(args);
    }

	
}

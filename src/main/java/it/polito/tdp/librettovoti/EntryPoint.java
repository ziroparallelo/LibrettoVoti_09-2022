 package it.polito.tdp.librettovoti;

import it.polito.tdp.librettovoti.model.Libretto;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class EntryPoint extends Application {

	
//	Il model viene creato dal controller poiché se avessi una applicazione
//	con più finestre, avrei tanti controller quante sono le finstre.
//	Per questo motivo è meglio far creare il model direttamente dall'applicazione
	
//	Quando l'applicazione passa da una finestra ad un'altra il suo controller viene
//	distrutto, viene creata un'altra vista che è instanziata su un'altro controller
	
//	IL MODELLO DEVE PERMANERE
	
    @Override
    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        
    	//Così stacco il load che mi creerà il controller
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
        Parent root = loader.load();
        //Creo il controller
        FXMLController controller = loader.getController();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        //Importo la classe Libretto ed ora posso indicare al controller
        //il modello
        Libretto model = new Libretto();
        controller.setModel(model);
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

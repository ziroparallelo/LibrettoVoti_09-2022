



package it.polito.tdp.librettovoti;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.librettovoti.model.Libretto;
import it.polito.tdp.librettovoti.model.Voto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


//Devo stare attento poiché qui sul controller il flusso
//delle informazioni, degli input, arriva in moto asincrono
//ed imprevedibile
/**
* Sample Skeleton for 'Scene.fxml' Controller Class
*/

public class FXMLController {
	
	//Va bene se il controller conosce il Modello, non il contrario
	private Libretto model;

	//E' sbagliato far creare al controller il model, esso viene crearto da fuori
	//e poi ci lavora.
	
//	private Libretto model = new Libretto();
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;
    
    @FXML // fx:id="txtStatus"
    private Label txtStatus; // Value injected by FXMLLoader

    @FXML // fx:id="cmbPunti"
    private ComboBox<Integer> cmbPunti; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtVoti"
    private TextArea txtVoti; // Value injected by FXMLLoader

    @FXML
    void handleNuovoVoto(ActionEvent event) {

//    	1. Accquisizione e controllo dati
    	String nome = txtNome.getText();
    	Integer punti = cmbPunti.getValue();
    	
    	
    	
    	//Devo svolgere i controlli di validità
    	
    	if(nome.equals("") || punti==null)
    	{
    		//errore, non posso eseguire l'operazione, devo stmamparlo in output
    		txtStatus.setText("ERRORE: occorre inserire nome e voto\n");
    		return;
    		
    		//Posso spezzarlo anche in più comandi per far visualizzare tutti
    		//gli errori
    	}
    	
//    	2. Esecuzione dell'operazione
    	
    	//Anche voto ovviamente devo importarlo
    	boolean ok = model.addVoto(new Voto(nome, punti));
    	
    	//Il controller e il modello devono parlare per oggetti
    	
//    	3. Visualizzazione/aggiornamento del risultato
    	
    	//Un altro compito del modello è quello di prendere
    	//gli input e aggiornare il model, ma poi devo far
    	//visualizzare il contenuto modificato sulla finestra
    	
    	//Deve essere il controller a gestire l'output e la stampa
//    	String contenutoLibretto = model.toString();
    	
    	//Creo un metodo che mi faccia stampare usando una lista di 
    	//voti fornita dal modello.
    	if(ok) {
    	List<Voto> voti = model.getVoti();
    	
    	txtVoti.clear();
    	txtVoti.appendText("Hai superato "+voti.size()+" esami\n");
    	for(Voto v: voti)
    		txtVoti.appendText(v.toString()+"\n");
    	
    	//E' giusto anche pulire i comandi sporchi (aggiornamento View)
    	
    	txtNome.clear();
    	cmbPunti.setValue(null);
    	txtStatus.setText("");
    	} else {
    		txtStatus.setText("ERRORE: esame già presente");
    	}
    	
    	
    	
    }
    
    public void setModel(Libretto model)
    {
    	this.model = model;
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
  
        assert txtStatus != null : "fx:id=\"txtStatus\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbPunti != null : "fx:id=\"cmbPunti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtVoti != null : "fx:id=\"txtVoti\" was not injected: check your FXML file 'Scene.fxml'.";

        cmbPunti.getItems().clear();
        
        for(int i = 18; i<=30; i++)
    		cmbPunti.getItems().add(i);
    }

}

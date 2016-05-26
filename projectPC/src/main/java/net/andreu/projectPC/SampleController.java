package net.andreu.projectPC;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.CheckBox;

public class SampleController implements Initializable  {
	@FXML
	private ComboBox<String> cbUser;
	@FXML
	private ComboBox<String> cbComanda;
	@FXML
	private ListView<String> lvComponents;
	@FXML
	private CheckBox chbRebuda;
	@FXML
	private CheckBox chbProcessant;
	@FXML
	private CheckBox chbFinalitzada;
	@FXML
	private CheckBox chbEntregada;
	@FXML
	private CheckBox chbAnulada;
	@FXML
	private Button btnDesar;

	private Connection con = null;
	private Statement consulta;
	private Comanda comandaActual;
	private ArrayList<Comanda> llistaComandes = new ArrayList<Comanda>();

	public void initialize(URL arg0, ResourceBundle arg1) {
		// obrir base de dades
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "project", "project");

			consulta = con.createStatement();
			// select a la taula d'usuaris
			ResultSet resultat = consulta.executeQuery("SELECT * FROM auth_user");
			// omplir el cbUser amb els valors de la bdd
			while (resultat.next()) {
				cbUser.getItems().addAll(resultat.getString("username"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Event Listener on ComboBox[#cbUser].onAction
	@FXML
	public void cercaComandes(ActionEvent event) {
		cbComanda.getItems().clear();
		lvComponents.getItems().clear();
		chbRebuda.setSelected(false);
		chbProcessant.setSelected(false);
		chbFinalitzada.setSelected(false);
		chbEntregada.setSelected(false);
		chbAnulada.setSelected(false);
		btnDesar.setDisable(true);
		
		String user = cbUser.getValue().toString();

		// seleccionar les comandes que pertanyen a l'usuari
		try {
			ResultSet resultat = consulta.executeQuery(
					"SELECT * FROM comandes_comanda WHERE estat != 'A' and usuari_id = (SELECT id FROM auth_user WHERE username = '" + user + "')");
			
			llistaComandes.clear();
			// posa-los al cbOrdres
			while (resultat.next()) {
				Comanda comanda = new Comanda(resultat.getInt("id_comanda"), resultat.getString("data"),
						resultat.getString("estat"));
				llistaComandes.add(comanda);
				
				String id_comanda=resultat.getString("id_comanda");
				String data=resultat.getString("data");
				String estat=resultat.getString("estat");
				cbComanda.getItems().addAll(id_comanda+" - "+data+" - "+estat);
			}
			//cbComanda.setValue("Tria una comanda");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// Event Listener on ComboBox[#cbComanda].onAction
	@FXML
	public void cercarComponets(ActionEvent event) {
		lvComponents.getItems().clear();
		chbRebuda.setSelected(false);
		chbProcessant.setSelected(false);
		chbFinalitzada.setSelected(false);
		chbEntregada.setSelected(false);
		chbAnulada.setSelected(false);
		btnDesar.setDisable(true);

		if(cbComanda.getItems().size()>0){
			int id = Integer.parseInt(cbComanda.getValue().toString().split("-")[0].trim());
			int i=0;
			boolean trobat = false;
			
			while(i< llistaComandes.size()&&!trobat){
				if(llistaComandes.get(i).getId_comanda()== id){
					comandaActual = llistaComandes.get(i);
					trobat=true;
				}			
				i++;
			}
		}


		//fer select de linies_comanda i omplir Components
		int id_comanda = comandaActual.getId_comanda();
		
		try {
			String cons  = "SELECT l.quantitat, p.nom FROM comandes_linia l inner join productes_producte p WHERE l.id_producte_id = p.id_producte and l.id_comanda_id = '" + id_comanda + "'";
			
			ResultSet resultatL = consulta.executeQuery(cons);

			String linia;
			while (resultatL.next()) {
				linia = resultatL.getString("l.quantitat")+" - " + resultatL.getString("p.nom") ;
				
				lvComponents.getItems().addAll(linia);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		switch (comandaActual.getEstat()) {
		case "R":
			chbRebuda.setSelected(true);
			break;
		case "P":
			chbProcessant.setSelected(true);
			break;
		case "F":
			chbFinalitzada.setSelected(true);
			break;
		case "E":
			chbEntregada.setSelected(true);
			break;
		case "A":
			chbAnulada.setSelected(true);
			break;
		}
	}
	
	// Event Listener on CheckBox[#chbRebuda].onMouseClicked
	@FXML
	public void SelectCheckBoxR(MouseEvent event) {
		
		chbProcessant.setSelected(false);
		chbFinalitzada.setSelected(false);
		chbEntregada.setSelected(false);
		chbAnulada.setSelected(false);
	}
	
	// Event Listener on CheckBox[#chbProcessant].onMouseClicked
	@FXML
	public void SelectCheckBoxP(MouseEvent event) {
		
		chbRebuda.setSelected(false);
		chbFinalitzada.setSelected(false);
		chbEntregada.setSelected(false);
		chbAnulada.setSelected(false);
	}
	
	// Event Listener on CheckBox[#chbFinalitzada].onMouseClicked
	@FXML
	public void SelectCheckBoxF(MouseEvent event) {
		
		chbRebuda.setSelected(false);
		chbProcessant.setSelected(false);
		chbEntregada.setSelected(false);
		chbAnulada.setSelected(false);
	}
	
	// Event Listener on CheckBox[#chbEntregada].onMouseClicked
	@FXML
	public void SelectCheckBoxE(MouseEvent event) {
		
		chbRebuda.setSelected(false);
		chbProcessant.setSelected(false);
		chbFinalitzada.setSelected(false);
		chbAnulada.setSelected(false);
	}
	
	// Event Listener on CheckBox[#chbAnulada].onMouseClicked
	@FXML
	public void SelectCheckBoxA(MouseEvent event) {
		
		chbRebuda.setSelected(false);
		chbProcessant.setSelected(false);
		chbFinalitzada.setSelected(false);
		chbEntregada.setSelected(false);
	}
	
	// Event Listener on CheckBox.onAction
	@FXML
	public void habilitaDesa(ActionEvent event) {
		
		btnDesar.setDisable(false);
	}
	
	// Event Listener on Button[#btnDesar].onMouseClicked
	@FXML
	public void desarCanvis(MouseEvent event) {
		
		String estat = "R";
		if(chbProcessant.isSelected()){
			estat = "P";
		}else if(chbFinalitzada.isSelected()){
			estat = "F";
		}else if(chbEntregada.isSelected()){
			estat = "E";
		}else if(chbAnulada.isSelected()){
			estat = "A";
		}
		String cons  = "Update comandes_comanda SET estat= '" + estat + "' WHERE id_comanda = " + comandaActual.getId_comanda();
		try {
			consulta.executeUpdate(cons);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//retornar productes si anulen la comanda
		if(estat.equals("A")){
			try {
				String cons2  = "SELECT l.quantitat, l.id_producte_id, p.stock FROM comandes_linia l inner join productes_producte p WHERE l.id_producte_id = p.id_producte and l.id_comanda_id = '" + comandaActual.getId_comanda() + "'";
				ResultSet resultatL = consulta.executeQuery(cons2);
				ArrayList<String> updates = new ArrayList<String>();
				while (resultatL.next()) {
					int estocfinal = resultatL.getInt("l.quantitat")+resultatL.getInt("p.stock");
					updates.add("Update productes_producte set stock = " + estocfinal + " WHERE id_producte = " + resultatL.getInt("l.id_producte_id" ));
				}
				
				for(String update : updates){
					consulta.executeUpdate(update);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		cercaComandes(new ActionEvent());
		
		btnDesar.setDisable(true);
	}
}

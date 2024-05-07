package controller;

import java.util.LinkedHashSet;

import javax.swing.JOptionPane;

import exception.CollectionSize0Exception;
import model.Comarca;
import model.Municipi;
import model.Partit;
import model.Provincia;
import model.ResultatModel;
import view.HomeView;
import view.LlistatComarcaView;
import view.LlistatMunicipiView;
import view.LlistatPartitView;
import view.LlistatProvinciaView;
import view.SelectResultatComarcaView;
import view.SelectResultatMunicipiView;
import view.SelectResultatPartitView;
import view.SelectResultatProvinciaView;

public class HomeController extends Controller {

	/**
	 * Metodo principal de entrada
	 */
	public void show() {
		
		// Vaciar todo el fichero al entrar
		ResultatModel mResultat = ResultatModel.getInstance();
		mResultat.clearAll();
		
		// Al entrar por primera vez, inserto el CSV a la BBDD
		super.insertarCsv("src/config/votacions.csv");
		
		// abrir ventana HomeView
		HomeView homeView = new HomeView();
		homeView.setVisible(true);
		
	}
	
	/**
	 * Metodo que carga la View asociada a la opcion proveniente de HomeView.
	 * @param opcion String de la opcion seleccionada en el JComboBox.
	 */
	public void cargarOpcion(String opcion) {
		
		ResultatModel mResultat = ResultatModel.getInstance();
		
		switch (opcion) {
	    case "1. Llistat de tots els partits":
	        //System.out.println("1");
	        
	        LinkedHashSet<Partit> arrPartit = null;
			try {
				arrPartit = mResultat.getAllPartit();
			} catch (CollectionSize0Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error en HomeController.cargarOpcion", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
	        
	        LlistatPartitView vLlistatPartit = new LlistatPartitView(new HomeView(), arrPartit);
	        vLlistatPartit.setVisible(true);
	        
	        break;
	    case "2. Llistat de tots els municipis":
	        //System.out.println("2");
	        
	        LinkedHashSet<Municipi> arrMunicipi = null;
			try {
				arrMunicipi = mResultat.getAllMunicipi();
			} catch (CollectionSize0Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error en HomeController.cargarOpcion", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
	        
	        LlistatMunicipiView vLlistatMunicipi = new LlistatMunicipiView(new HomeView(), arrMunicipi);
	        vLlistatMunicipi.setVisible(true);
	        
	        break;
	    case "3. Llistat de totes les comarques":
	    	//System.out.println("3 extra");
	    	
	    	LinkedHashSet<Comarca> arrComarca = null;
			try {
				arrComarca = mResultat.getAllComarca();
			} catch (CollectionSize0Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error en HomeController.cargarOpcion", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
	        
	        LlistatComarcaView vLlistatComarca = new LlistatComarcaView(new HomeView(), arrComarca);
	        vLlistatComarca.setVisible(true);
	    	
	    	break;
	    case "4. Llistat de totes les provincies":
	    	//System.out.println("4 extra");
	    	
	    	LinkedHashSet<Provincia> arrProvincia = null;
			try {
				arrProvincia = mResultat.getAllProvincia();
			} catch (CollectionSize0Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error en HomeController.cargarOpcion", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
	        
	        LlistatProvinciaView vLlistatProvincia = new LlistatProvinciaView(new HomeView(), arrProvincia);
	        vLlistatProvincia.setVisible(true);
	    	
	    	break;
	    case "5. Resultats per partit, en un municipi":
	        //System.out.println("5");
	        
	        LinkedHashSet<Municipi> arrMunicipi2 = null;
			try {
				arrMunicipi2 = mResultat.getAllMunicipi();
			} catch (CollectionSize0Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error en HomeController.cargarOpcion", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
	        
	        SelectResultatMunicipiView vSelectResultatMunicipi = new SelectResultatMunicipiView(new HomeView(), arrMunicipi2);
	        vSelectResultatMunicipi.setVisible(true);
	        
	        break;
	    case "6. Resultats per municipi, d'un partit":
	        //System.out.println("6");
	    	
	    	LinkedHashSet<Partit> arrPartit2 = null;
			try {
				arrPartit2 = mResultat.getAllPartit();
			} catch (CollectionSize0Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error en HomeController.cargarOpcion", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
	    	
	    	SelectResultatPartitView vSelectResultatPartit = new SelectResultatPartitView(new HomeView(), arrPartit2);
	    	vSelectResultatPartit.setVisible(true);
	        
	        break;
	    case "7. Resultats per partit en una província donada":
	        //System.out.println("7");
	    	
	    	LinkedHashSet<Provincia> arrProvincia2 = null;
			try {
				arrProvincia2 = mResultat.getAllProvincia();
			} catch (CollectionSize0Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error en HomeController.cargarOpcion", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
	    	
	    	SelectResultatProvinciaView vSelectResultatProvincia = new SelectResultatProvinciaView(new HomeView(), arrProvincia2);
	    	vSelectResultatProvincia.setVisible(true);
	        
	        break;
	    case "8. Resultats per partit en una comarca donada":
	    	//System.out.println("8");
	    	
	    	LinkedHashSet<Comarca> arrComarca2 = null;
			try {
				arrComarca2 = mResultat.getAllComarca();
			} catch (CollectionSize0Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error en HomeController.cargarOpcion", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
	    	
	    	SelectResultatComarcaView vSelectResultatComarca = new SelectResultatComarcaView(new HomeView(), arrComarca2);
	    	vSelectResultatComarca.setVisible(true);
	    	
	    	break;
	    default:
	    	JOptionPane.showMessageDialog(null, "La opcion seleccionada no existe: "+opcion, "Error en HomeController.cargarOpcion", JOptionPane.ERROR_MESSAGE);
	        break;
	}
		
	}
	
}

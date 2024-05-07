package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

import model.*;

public class Controller {

	/**
	 * Metodo para leer el CSV e insertar todo a la BBDD
	 * @param filePath Ruta del fichero CSV
	 */
	public void insertarCsv(String filePath) {
		
		ResultatModel mResultat =ResultatModel.getInstance();
		
		try {
			
			File votacionsCsv = new File(filePath);

			FileReader csvReader = new FileReader(votacionsCsv);
			
			BufferedReader bufferedReader = new BufferedReader(csvReader);

	        String line;
	        int lineCounter = 1;
	        while ((line = bufferedReader.readLine()) != null) {
	            //System.out.println(line);
	        	
	        	// guardar lo leido en la bbdd
	        	// ignorar la primera linea
	        	if (lineCounter != 1) {
	        		String[] arrLine = line.split(";");
	            	//System.out.println(Arrays.toString(arrLine));
	            	
	        		// convertir vots de String a int
	        		int vots;
	            	if (arrLine[5].equals("-")) {
	            		vots = 0;
	            	} else {
	            		vots = Integer.parseInt(arrLine[5]);
	            	}
	            	
	            	// convertir percent de String a float
	            	float percent;
	            	if (arrLine[6].equals("-")) {
	            		percent = 0.0f;
	            	} else {
	            		percent = Float.parseFloat(arrLine[6].replace(",", "."));
	            	}
	            	
	            	Resultat objLine = new Resultat(new Provincia(arrLine[0]), new Comarca(arrLine[1]), new Municipi(arrLine[2]), new Partit(arrLine[3], arrLine[4]), vots, percent);
	            	
	            	//System.out.println(objLine);
	            	
	            	// insertar cada linea en la bbdd
	            	mResultat.create(objLine);
	            	
	        	}
	        	
	        	lineCounter++;
	        }

	        bufferedReader.close();
			
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error en Controller.insertarCsv", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error en Controller.insertarCsv", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
			
		
	}
	
}

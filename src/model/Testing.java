package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;

import javax.swing.JOptionPane;

import exception.CollectionSize0Exception;

public class Testing {

	public static void main(String[] args) {
		
		/*
		 * --- --- --- --- ---
		 * Archivo de pruebas a llamadas a modelos, ignorar.
		 * La clase de entrada real es MainApp del paquete app.
		 * --- --- --- --- ---
		 */
		
		// Pruebas de Db4o CRUD
		
		/*Resultat obj = new Resultat(
				new Provincia("Barcelona"), 
				new Comarca("Alt Penedès"), 
				new Municipi("Avinyonet del Penedès"), 
				new Partit("ERC", "ESQUERRA REPUBLICANA DE CATALUNYA-SOBIRANISTES"), 
				326, 
				31.62f);*/
		
		ResultatModel mResultat = ResultatModel.getInstance();
		
		//mResultat.create(obj);
		
		LinkedHashSet<Resultat> readResultat = null;
		try {
			readResultat = mResultat.read();
		} catch (CollectionSize0Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Resultat resultat : readResultat) {
		    System.out.println(resultat);
		}
		
		//mResultat.delete(obj);
		
		//mResultat.closeDb();
		
		// pruebas de leer el fichero
		
		File votacionsCsv = new File("src/config/votacions.csv");
		
		try {
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
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error al buscar el fichero", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error al leer el fichero", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		
	}
	
}

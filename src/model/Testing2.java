package model;

import java.util.LinkedHashSet;

import exception.CollectionSize0Exception;

public class Testing2 {

	public static void main(String[] args) {
		
		/*
		 * --- --- --- --- ---
		 * Archivo de pruebas a llamadas a modelos, ignorar.
		 * La clase de entrada real es MainApp del paquete app.
		 * --- --- --- --- ---
		 */
		
		ResultatModel mResultat = ResultatModel.getInstance();
		
		/*LinkedHashSet<Partit> arrPartit = mResultat.getAllPartit();
		
		for (Partit partit : arrPartit) {
            System.out.println(partit);
        }
		
		System.out.println(arrPartit.size());*/
		
		/*LinkedHashSet<Municipi> arrMunicipi = mResultat.getAllMunicipi();
		
		for (Municipi municipi : arrMunicipi) {
            System.out.println(municipi);
        }
		
		System.out.println(arrMunicipi.size());*/
		
		LinkedHashSet<Resultat> arrResultatPartit = null;
		try {
			arrResultatPartit = mResultat.getResultatByPartit(new Partit("VOX", "VOX"));
		} catch (CollectionSize0Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Resultat resultat : arrResultatPartit) {
            System.out.println(resultat);
        }
		
		System.out.println(arrResultatPartit.size());
		
	}
	
}

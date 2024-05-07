package app;

import javax.swing.JOptionPane;

import controller.HomeController;

public class MainApp {

	// El programa se ejecuta desde esta clase.
	
	/*
	 * --- --- --- --- ---
	 * Justificación de la coleccion LinkedHashSet:
	 * 
	 * - LinkedHashSet, al igual que HashSet no permite elementos duplicados implementando 
	 *	 los metodos equals() y hashCode().
	 *
	 * - LinkedHashSet mantiene el orden de inserción. Caso contrario con HashSet.
	 * 
	 * - A diferencia de TreeSet, LinkedHashSet no ordena automáticamente los elementos 
	 *   según un criterio específico, lo que puede ser mejor al mantener el orden 
	 *   original de los elementos.
	 *   
	 * - LinkedHashSet ofrece un tiempo de búsqueda constante O(1) para operaciones como 
	 *   agregar, eliminar y buscar elementos, lo que lo hace eficiente para aplicaciones 
	 *   que requieren un acceso rápido a los elementos únicos y ordenados de una colección.
	 * 
	 * --- --- --- --- ---
	 */
	
	/**
	 * Entrada principal de la aplicación
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Solo pongo un catch general porque hago varios try catch dentro de otras clases.
		
		try {
			HomeController cHome = new HomeController();
			cHome.show();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Exception: Error Completo en App", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
	}

}

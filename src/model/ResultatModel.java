package model;

import java.util.LinkedHashSet;

import javax.swing.JOptionPane;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import exception.CollectionSize0Exception;

/**
 * Clase Modelo para gestionar los objetos Resultat y sus clases utilizadas como atributo.
 * Utiliza el patron Singleton.
 */
public class ResultatModel {
	
	private static final String DB4OFILENAME = "src/bbdd/myBbdd.db4o";
	private static ResultatModel instance;
	private ObjectContainer db;

	/**
	 * Constructor que abre el fichero de la BBDD. Es privado ya que esta clase es Singleton.
	 */
	private ResultatModel() {
		this.db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DB4OFILENAME);
	}
	
	/**
	 * Metodo Singleton para tener solo una instancia.
	 * @return Recupera la instancia.
	 */
	public static ResultatModel getInstance() {
        if (instance == null) {
            instance = new ResultatModel();
        }
        return instance;
    }
	
	@Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
	
	/**
	 * Metodo para cerrar el fichero de la BBDD. Puedes volver a abrir con openDb.
	 */
	public void closeDb() {
		this.db.close();
	}
	
	/**
	 * Metodo para abrir el fichero de la BBDD en caso de estar cerrada.
	 */
	public void openDb() {
		this.db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), DB4OFILENAME);
	}
	
	/**
	 * Metodo para guardar un objeto en la BBDD.
	 * @param obj {@link Resultat} Objeto a guardar.
	 * @return TRUE en caso de exito, o FALSE en caso de error.
	 */
	public boolean create(Resultat obj) {
		try {
            db.store(obj);
            return true;
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, e.getMessage(), "Error en ResultatModel.create()", JOptionPane.ERROR_MESSAGE);
        	e.printStackTrace();
            return false;
        }
	}
	
	/**
	 * Metodo para leer toda la BBDD, utilizando Query by Example.
	 * @return {@link LinkedHashSet} Una coleccion de Resultat.
	 * @throws CollectionSize0Exception Si la consulta no ha devuelto ningun resultado.
	 */
	public LinkedHashSet<Resultat> read() throws CollectionSize0Exception {
		LinkedHashSet<Resultat> resultatSet = new LinkedHashSet<Resultat>();

        // Creamos un objeto Resultat vacío para usarlo como ejemplo de consulta
        Resultat exampleResultat = new Resultat(null, null, null, null, 0, 0);

        // Creamos la consulta por ejemplo (Query by Example)
        ObjectSet<Resultat> resultats = db.queryByExample(exampleResultat);

        // Iteramos sobre los resultados y los agregamos al LinkedHashSet
        while (resultats.hasNext()) {
            resultatSet.add(resultats.next());
        }

        if (resultatSet.size() == 0) {
        	throw new CollectionSize0Exception("No hay resultados encontrados.");
        }
        
        return resultatSet;
	}
	
	/**
	 * Metodo para actualizar un objeto de la BBDD.
	 * @param obj {@link Resultat} Objeto a actualizar.
	 * @return TRUE en caso de exito, o FALSE en caso de error.
	 */
	public boolean update(Resultat obj) {
		try {
            // Busca el objeto en la base de datos
            ObjectSet<Resultat> resultats = db.queryByExample(obj);

            // Si el objeto existe, actualízalo
            if (resultats.hasNext()) {
                Resultat existingObj = resultats.next();
                existingObj.setProvincia(obj.getProvincia());
                existingObj.setComarca(obj.getComarca());
                existingObj.setMunicipi(obj.getMunicipi());
                existingObj.setPartit(obj.getPartit());
                existingObj.setVots(obj.getVots());
                existingObj.setPercent(obj.getPercent());

                // Almacena los cambios en la base de datos
                db.store(existingObj);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "El objeto no existe en la base de datos.",
                        "Error en ResultatModel.update()", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en ResultatModel.update()",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
	}
	
	/**
	 * Metodo para eliminar un objeto de la BBDD.
	 * @param obj {@link Resultat} Objeto a eliminar.
	 * @return TRUE en caso de exito, FALSE en caso de error.
	 */
	public boolean delete(Resultat obj) {
		try {
            // Busca el objeto en la base de datos
            ObjectSet<Resultat> resultats = db.queryByExample(obj);

            // Si el objeto existe, elimínalo
            if (resultats.hasNext()) {
                Resultat existingObj = resultats.next();
                db.delete(existingObj);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "El objeto no existe en la base de datos.",
                        "Error en ResultatModel.delete()", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error en ResultatModel.delete()",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        }
	}
	
	/**
	 * Metodo para eliminar todos los objetos de la BBDD. Elimina Resultat y sus clases dentro de sus atributos.
	 * @return TRUE en caso de exito, o FALSE en caso de error.
	 */
	public boolean clearAll() {
		try {
            Query query = db.query();
            query.constrain(Resultat.class);
            ObjectSet<Resultat> resultats = query.execute();

            // Iterar sobre los objetos Resultat y eliminarlos uno por uno
            while (resultats.hasNext()) {
                Resultat resultat = resultats.next();
                // Eliminar los objetos asociados a Resultat
                db.delete(resultat.getProvincia());
                db.delete(resultat.getComarca());
                db.delete(resultat.getMunicipi());
                db.delete(resultat.getPartit());
                // Eliminar el objeto Resultat actual
                db.delete(resultat);
            }
            return true; // Se eliminaron todos los objetos correctamente
        } catch (Exception e) {
        	JOptionPane.showMessageDialog(null, e.getMessage(), "Error en ResultatModel.clearAll()",
                    JOptionPane.ERROR_MESSAGE);
        	e.printStackTrace();
            return false; // Error al eliminar objetos
        }
	}
	
	/**
	 * Metodo para recuperar todos los objetos Partit unicos
	 * @return {@link LinkedHashSet} Coleccion con Partit.
	 * @throws CollectionSize0Exception Si la consulta no ha devuelto ningun resultado.
	 */
	public LinkedHashSet<Partit> getAllPartit() throws CollectionSize0Exception {
		LinkedHashSet<Partit> arrPartit = new LinkedHashSet<Partit>();
		
		Query query = db.query();
        query.constrain(Partit.class);
        ObjectSet<Partit> result = query.execute();
        
        while (result.hasNext()) {
        	arrPartit.add(result.next());
        }
        
        if (arrPartit.size() == 0) {
        	throw new CollectionSize0Exception("No hay partidos encontrados.");
        }
        
        return arrPartit;
	}
	
	/**
	 * Metodo para recuperar todos los objetos Municipi unicos
	 * @return {@link LinkedHashSet} Coleccion con Municipi.
	 * @throws CollectionSize0Exception Si la consulta no ha devuelto ningun resultado.
	 */
	public LinkedHashSet<Municipi> getAllMunicipi() throws CollectionSize0Exception {
		LinkedHashSet<Municipi> arrMunicipi = new LinkedHashSet<Municipi>();
		
		Query query = db.query();
        query.constrain(Municipi.class);
        ObjectSet<Municipi> result = query.execute();
        
        while (result.hasNext()) {
        	if (!result.next().getMunicipi().equals("")) {
        		arrMunicipi.add(result.next());
        	}
        }
        
        if (arrMunicipi.size() == 0) {
        	throw new CollectionSize0Exception("No hay municipios encontrados.");
        }
        
        return arrMunicipi;
	}
	
	/**
	 * Metodo para recuperar todos los objetos Comarca unicos
	 * @return {@link LinkedHashSet} Coleccion con Comarca.
	 * @throws CollectionSize0Exception Si la consulta no ha devuelto ningun resultado.
	 */
	public LinkedHashSet<Comarca> getAllComarca() throws CollectionSize0Exception {
		LinkedHashSet<Comarca> arrComarca = new LinkedHashSet<Comarca>();
		
		Query query = db.query();
        query.constrain(Comarca.class);
        ObjectSet<Comarca> result = query.execute();
        
        while (result.hasNext()) {
        	if (!result.next().getComarca().equals("")) {
        		arrComarca.add(result.next());
        	}
        }
        
        if (arrComarca.size() == 0) {
        	throw new CollectionSize0Exception("No hay comarcas encontradas.");
        }
        
        return arrComarca;
	}
	
	/**
	 * Metodo para recuperar todos los objetos Provincia unicos
	 * @return {@link LinkedHashSet} Coleccion con Provincia.
	 * @throws CollectionSize0Exception Si la consulta no ha devuelto ningun resultado.
	 */
	public LinkedHashSet<Provincia> getAllProvincia() throws CollectionSize0Exception {
		LinkedHashSet<Provincia> arrProvincia = new LinkedHashSet<Provincia>();
		
		Query query = db.query();
        query.constrain(Provincia.class);
        ObjectSet<Provincia> result = query.execute();
        
        while (result.hasNext()) {
        	if (!result.next().getProvincia().equals("")) {
        		arrProvincia.add(result.next());
        	}
        }
        
        if (arrProvincia.size() == 0) {
        	throw new CollectionSize0Exception("No hay provincias encontradas.");
        }
        
        return arrProvincia;
	}
	
	/**
     * Método para recuperar todos los Resultat asociados a un Municipi dado.
     * @param Municipi municipi El municipio del que se desean obtener los resultados.
     * @return {@link LinkedHashSet} Una colección LinkedHashSet de Resultat asociados al Municipi.
	 * @throws CollectionSize0Exception Si la consulta no ha devuelto ningun resultado.
     */
	public LinkedHashSet<Resultat> getResultatByMunicipi(Municipi municipi) throws CollectionSize0Exception {
		LinkedHashSet<Resultat> resultatSet = new LinkedHashSet<>();

        // Crear un objeto Resultat vacío para usar como ejemplo de consulta
        Resultat exampleResultat = new Resultat(null, null, municipi, null, 0, 0);

        // Crear la consulta por ejemplo (Query by Example)
        ObjectSet<Resultat> resultats = db.queryByExample(exampleResultat);

        // Iterar sobre los resultados y agregarlos al LinkedHashSet
        while (resultats.hasNext()) {
            resultatSet.add(resultats.next());
        }
        
        if (resultatSet.size() == 0) {
        	throw new CollectionSize0Exception("No hay resultados encontrados.");
        }

        return resultatSet;
	}
	
	/**
	 * Método para recuperar todos los Resultat asociados a un Partit dado.
	 * @param Partit partit El partido del que se desean obtener los resultados.
	 * @return Una colección LinkedHashSet de Resultat asociados al Partit.
	 * @throws CollectionSize0Exception Si la consulta no ha devuelto ningun resultado.
	 */
	public LinkedHashSet<Resultat> getResultatByPartit(Partit partit) throws CollectionSize0Exception {
	    LinkedHashSet<Resultat> resultatSet = new LinkedHashSet<>();

	    // Crear un objeto Resultat vacío para usar como ejemplo de consulta
	    Resultat exampleResultat = new Resultat(null, null, null, partit, 0, 0);

	    // Crear la consulta por ejemplo (Query by Example)
	    ObjectSet<Resultat> resultats = db.queryByExample(exampleResultat);

	    // Iterar sobre los resultados y agregarlos al LinkedHashSet
	    while (resultats.hasNext()) {
	        resultatSet.add(resultats.next());
	    }
	    
	    if (resultatSet.size() == 0) {
        	throw new CollectionSize0Exception("No hay resultados encontrados.");
        }

	    return resultatSet;
	}
	
	/**
	 * Método para recuperar todos los Resultat asociados a una Provincia dada.
	 * @param Provincia provincia La provincia de la que se desean obtener los resultados.
	 * @return Una colección LinkedHashSet de Resultat asociados a la Provincia.
	 * @throws CollectionSize0Exception Si la consulta no ha devuelto ningun resultado.
	 */
	public LinkedHashSet<Resultat> getResultatByProvincia(Provincia provincia) throws CollectionSize0Exception {
	    LinkedHashSet<Resultat> resultatSet = new LinkedHashSet<>();

	    // Crear un objeto Resultat vacío para usar como ejemplo de consulta
	    Resultat exampleResultat = new Resultat(provincia, null, null, null, 0, 0);

	    // Crear la consulta por ejemplo (Query by Example)
	    ObjectSet<Resultat> resultats = db.queryByExample(exampleResultat);

	    // Iterar sobre los resultados y agregarlos al LinkedHashSet
	    while (resultats.hasNext()) {
	        resultatSet.add(resultats.next());
	    }
	    
	    if (resultatSet.size() == 0) {
        	throw new CollectionSize0Exception("No hay resultados encontrados.");
        }

	    return resultatSet;
	}
	
	/**
	 * Método para recuperar todos los Resultat asociados a una Comarca dada.
	 * @param Comarca comarca La comarca de la que se desean obtener los resultados.
	 * @return Una colección LinkedHashSet de Resultat asociados a la Comarca.
	 * @throws CollectionSize0Exception Si la consulta no ha devuelto ningun resultado.
	 */
	public LinkedHashSet<Resultat> getResultatByComarca(Comarca comarca) throws CollectionSize0Exception {
	    LinkedHashSet<Resultat> resultatSet = new LinkedHashSet<>();

	    // Crear un objeto Resultat vacío para usar como ejemplo de consulta
	    Resultat exampleResultat = new Resultat(null, comarca, null, null, 0, 0);

	    // Crear la consulta por ejemplo (Query by Example)
	    ObjectSet<Resultat> resultats = db.queryByExample(exampleResultat);

	    // Iterar sobre los resultados y agregarlos al LinkedHashSet
	    while (resultats.hasNext()) {
	        resultatSet.add(resultats.next());
	    }
	    
	    if (resultatSet.size() == 0) {
        	throw new CollectionSize0Exception("No hay resultados encontrados.");
        }

	    return resultatSet;
	}
	
}

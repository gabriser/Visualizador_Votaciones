package view;

import java.awt.BorderLayout;
import java.util.LinkedHashSet;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import model.Partit;

public class LlistatPartitView extends JFrame {

	private static final long serialVersionUID = 1L;

	public LlistatPartitView(JFrame parent, LinkedHashSet<Partit> arrPartit) {
		this.setTitle("1. Llistat de tots els partits");
		this.setResizable(false);
		this.setBounds(100, 100, 800, 400);
        this.setLocationRelativeTo(parent);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Crear un modelo de tabla con columnas "Sigles" y "Partit"
        DefaultTableModel model = new DefaultTableModel(new String[]{"Sigles", "Partit"}, 0);
        // Agregar filas a partir de la colección de objetos Partit
        for (Partit partit : arrPartit) {
            model.addRow(new Object[]{partit.getSigles(), partit.getPartit()});
        }

        // Crear la tabla con el modelo de datos
        JTable table = new JTable(model);
        
        // Configurar el ancho de las columnas
	    TableColumnModel columnModel = table.getColumnModel();
	    // Ajustar el ancho de la columna "Sigles" al 40% del ancho total de la tabla
	    columnModel.getColumn(0).setPreferredWidth((int) (getWidth() * 0.4));
	    // Ajustar el ancho de la columna "Partit" al 60% del ancho total de la tabla
	    columnModel.getColumn(1).setPreferredWidth((int) (getWidth() * 0.6));

        // Añadir la tabla a un JScrollPane para que tenga barras de desplazamiento
        JScrollPane scrollPane = new JScrollPane(table);
        // Añadir el JScrollPane al contenido de la ventana
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        
	}

	
	
}

package view;

import java.awt.BorderLayout;
import java.util.LinkedHashSet;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Provincia;

public class LlistatProvinciaView extends JFrame {

	private static final long serialVersionUID = 1L;

	public LlistatProvinciaView(JFrame parent, LinkedHashSet<Provincia> arrProvincia) {
		this.setTitle("4. Llistat de totes les provincies");
		this.setResizable(false);
		this.setBounds(100, 100, 800, 400);
        this.setLocationRelativeTo(parent);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Crear un modelo de tabla con columnas "Provincia"
        DefaultTableModel model = new DefaultTableModel(new String[]{"Provincia"}, 0);
        // Agregar filas a partir de la colección de objetos Municipi
        for (Provincia provincia : arrProvincia) {
            model.addRow(new Object[]{provincia.getProvincia()});
        }
        
        // Crear la tabla con el modelo de datos
        JTable table = new JTable(model);
        
        // Añadir la tabla a un JScrollPane para que tenga barras de desplazamiento
        JScrollPane scrollPane = new JScrollPane(table);
        // Añadir el JScrollPane al contenido de la ventana
        getContentPane().add(scrollPane, BorderLayout.CENTER);
		
	}
	
}

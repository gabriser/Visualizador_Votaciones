package view;

import java.awt.BorderLayout;
import java.util.LinkedHashSet;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Comarca;

public class LlistatComarcaView extends JFrame {

	private static final long serialVersionUID = 1L;

	public LlistatComarcaView(JFrame parent, LinkedHashSet<Comarca> arrComarca) {
		this.setTitle("3. Llistat de totes les comarques");
		this.setResizable(false);
		this.setBounds(100, 100, 800, 400);
        this.setLocationRelativeTo(parent);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // Crear un modelo de tabla con columnas "Comarca"
        DefaultTableModel model = new DefaultTableModel(new String[]{"Comarca"}, 0);
        // Agregar filas a partir de la colección de objetos Municipi
        for (Comarca comarca : arrComarca) {
            model.addRow(new Object[]{comarca.getComarca()});
        }
        
        // Crear la tabla con el modelo de datos
        JTable table = new JTable(model);
        
        // Añadir la tabla a un JScrollPane para que tenga barras de desplazamiento
        JScrollPane scrollPane = new JScrollPane(table);
        // Añadir el JScrollPane al contenido de la ventana
        getContentPane().add(scrollPane, BorderLayout.CENTER);
		
	}
	
}

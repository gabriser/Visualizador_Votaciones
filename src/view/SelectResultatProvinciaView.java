package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import exception.CollectionSize0Exception;
import model.Provincia;
import model.Resultat;
import model.ResultatModel;

public class SelectResultatProvinciaView extends JFrame {

	private static final long serialVersionUID = 1L;

	public SelectResultatProvinciaView(JFrame parent, LinkedHashSet<Provincia> arrProvincia) {
		this.setTitle("7. Resultats per partit en una província donada");
		this.setResizable(false);
		this.setBounds(100, 100, 800, 400);
        this.setLocationRelativeTo(parent);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // paleta de fuentes y tamaño
 		Font latoFont = null;
 		try {
 			latoFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/lato/Lato-Regular.ttf")).deriveFont(15f);
 		} catch (FontFormatException | IOException e) {
 			JOptionPane.showMessageDialog(null, "No se ha podido cargar la fuente del programa. Cargando fuente por defecto", "IOException o FontFormatException", JOptionPane.ERROR_MESSAGE);
 		}
        
        JPanel pnl_center = new JPanel();
        this.getContentPane().add(pnl_center, BorderLayout.CENTER);
        
        DefaultComboBoxModel<String> provinciaComboBoxModel = new DefaultComboBoxModel<>();
        
        Iterator<Provincia> iterator = arrProvincia.iterator();
        while (iterator.hasNext()) {
            Provincia provincia = iterator.next();
            provinciaComboBoxModel.addElement(provincia.getProvincia());
        }
        
        JComboBox<String> cbx_select = new JComboBox<String>(provinciaComboBoxModel);
        cbx_select.setFont(latoFont);
        pnl_center.add(cbx_select);
        
        MyButton btn_send = new MyButton("Consultar");
        pnl_center.add(btn_send);
        btn_send.addMouseListener(new java.awt.event.MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		// Obtener la provincia seleccionado
                String selectedProvincia = (String) cbx_select.getSelectedItem();
                
                // Realizar la consulta al método del modelo
                ResultatModel mResultat = ResultatModel.getInstance();
                LinkedHashSet<Resultat> resultatByProvincia = null;
				try {
					resultatByProvincia = mResultat.getResultatByProvincia(new Provincia(selectedProvincia));
				} catch (CollectionSize0Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error en SelectResultatProvinciaView", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
                
                // Construir el modelo de tabla con los resultados
                DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Provincia", "Comarca", "Municipi", "Partit", "Vots", "Percent"}, 0);
                
                for (Resultat resultat : resultatByProvincia) {
                    tableModel.addRow(new Object[]{resultat.getProvincia().getProvincia(), resultat.getComarca().getComarca(),
                            resultat.getMunicipi().getMunicipi(), resultat.getPartit().getPartit(),
                            resultat.getVots(), resultat.getPercent()});
                }
                
                // Crear la tabla con el modelo de datos
                JTable table = new JTable(tableModel);
                // Ajustar el tamaño de las columnas
                table.getColumnModel().getColumn(0).setPreferredWidth(100); // Provincia
                table.getColumnModel().getColumn(1).setPreferredWidth(100); // Comarca
                table.getColumnModel().getColumn(2).setPreferredWidth(100); // Municipi
                table.getColumnModel().getColumn(3).setPreferredWidth(150); // Partit
                table.getColumnModel().getColumn(4).setPreferredWidth(50); // Vots
                table.getColumnModel().getColumn(5).setPreferredWidth(50); // Percent
                
                // Agregar la tabla a un JScrollPane para que tenga barras de desplazamiento
                JScrollPane scrollPane = new JScrollPane(table);
                
                // Limpiar el panel central y agregar el JScrollPane con la tabla
                pnl_center.removeAll();
                pnl_center.setLayout(new BorderLayout());
                pnl_center.add(scrollPane, BorderLayout.CENTER);
                pnl_center.revalidate(); // Actualizar el diseño del panel
                
        	}
        });
        
	}
	
}

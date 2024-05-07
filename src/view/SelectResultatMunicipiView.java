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
import model.Municipi;
import model.Resultat;
import model.ResultatModel;

public class SelectResultatMunicipiView extends JFrame {

	private static final long serialVersionUID = 1L;

	public SelectResultatMunicipiView(JFrame parent, LinkedHashSet<Municipi> arrMunicipi) {
		this.setTitle("5. Resultats per partit, en un municipi");
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
        
        DefaultComboBoxModel<String> municipiComboBoxModel = new DefaultComboBoxModel<>();
        
        Iterator<Municipi> iterator = arrMunicipi.iterator();
        while (iterator.hasNext()) {
            Municipi municipi = iterator.next();
            municipiComboBoxModel.addElement(municipi.getMunicipi());
        }
        
        JComboBox<String> cbx_select = new JComboBox<String>(municipiComboBoxModel);
        cbx_select.setFont(latoFont);
        pnl_center.add(cbx_select);
        
        MyButton btn_send = new MyButton("Consultar");
        pnl_center.add(btn_send);
        btn_send.addMouseListener(new java.awt.event.MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		// Obtener el municipio seleccionado
                String selectedMunicipi = (String) cbx_select.getSelectedItem();
                
                // Realizar la consulta al método del modelo
                ResultatModel mResultat = ResultatModel.getInstance();
                LinkedHashSet<Resultat> resultatByMunicipi = null;
				try {
					resultatByMunicipi = mResultat.getResultatByMunicipi(new Municipi(selectedMunicipi));
				} catch (CollectionSize0Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error en SelectResultatMunicipiView", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
                
                // Construir el modelo de tabla con los resultados
                DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Provincia", "Comarca", "Municipi", "Partit", "Vots", "Percent"}, 0);
                for (Resultat resultat : resultatByMunicipi) {
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

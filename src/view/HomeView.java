package view;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Toolkit;

import controller.Controller;
import controller.HomeController;
import model.ResultatModel;

public class HomeView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Create the application.
	 */
	public HomeView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setBounds(100, 100, 600, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Votacions");
		this.setResizable(false);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(HomeView.class.getResource("/assets/Votacions_logo.png")));
		
		// paleta de colores
		Color bgColor = new Color(180, 180, 180);
		Color textColor = new Color(255, 255, 255);
		
		// paleta de fuentes y tamaño
		Font latoFont = null;
		try {
			latoFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/lato/Lato-Regular.ttf")).deriveFont(15f);
		} catch (FontFormatException | IOException e) {
			JOptionPane.showMessageDialog(null, "No se ha podido cargar la fuente del programa. Cargando fuente por defecto", "IOException o FontFormatException", JOptionPane.ERROR_MESSAGE);
		}
		
		// barra de menu con opciones
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
        JMenu menuPrograma = new JMenu("Programa");
        menuPrograma.setFont(latoFont);
        menuBar.add(menuPrograma);
        
        JMenuItem menuItemAcercaDe = new JMenuItem("Acerca de");
        menuItemAcercaDe.setFont(latoFont);
        menuItemAcercaDe.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		AboutView acercaDeDialog = new AboutView(new JFrame());
                acercaDeDialog.setVisible(true);
        	}
        });
        menuItemAcercaDe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        
        JMenuItem menuItemSalir = new JMenuItem("Salir");
        menuItemSalir.setFont(latoFont);
        menuItemSalir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// al salir, cerrar la base de datos
        		ResultatModel mResultat = ResultatModel.getInstance();
        		mResultat.closeDb();
        		
        		System.exit(0);
        	}
        });
        menuItemSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        
        menuPrograma.add(menuItemAcercaDe);
        menuPrograma.add(menuItemSalir);
        
        // combo box selector con 5 (ahora 8) opciones
        // he ido separando opciones y ahora tengo 8
        
        JPanel pnl_center = new JPanel();
        pnl_center.setBackground(bgColor);
        this.getContentPane().add(pnl_center, BorderLayout.CENTER);
        
        String[] arr_opciones = {
        		"1. Llistat de tots els partits",
        		"2. Llistat de tots els municipis",
        		"3. Llistat de totes les comarques",
        		"4. Llistat de totes les provincies",
        		"5. Resultats per partit, en un municipi",
        		"6. Resultats per municipi, d'un partit",
        		"7. Resultats per partit en una província donada",
        		"8. Resultats per partit en una comarca donada"
        };
        JComboBox<String> cbx_select = new JComboBox<String>(arr_opciones);
        cbx_select.setFont(latoFont);
        pnl_center.add(cbx_select);
        
        MyButton btn_send = new MyButton("Consultar");
        pnl_center.add(btn_send);
        btn_send.addMouseListener(new java.awt.event.MouseAdapter() {         
        	public void mouseClicked(MouseEvent e) {
        		
        		// Aqui se selecciona la opcion
        		
        		//System.out.println("has seleccionado:");
        		//System.out.println(cbx_select.getSelectedItem());
        		HomeController cHome = new HomeController();
        		cHome.cargarOpcion(cbx_select.getSelectedItem().toString());
        	}
        });
        
        JPanel pnl_bottom = new JPanel(new GridLayout(1, 2));
        pnl_bottom.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnl_bottom.setBackground(bgColor);
        this.getContentPane().add(pnl_bottom, BorderLayout.SOUTH);
        
        // aviso de csv
        JLabel lbl_csvAviso = new JLabel("<html><p style=\"text-align: center;\">Asegúrate de que el símbolo de separación sea ; (punto y coma)</p></html>");
        lbl_csvAviso.setFont(latoFont);
        lbl_csvAviso.setForeground(textColor);
        pnl_bottom.add(lbl_csvAviso);
        
        // Configuración del botón para seleccionar un archivo
        MyButton btn_selectFile = new MyButton("Cargar un nuevo fichero CSV");
        pnl_bottom.add(btn_selectFile);
        btn_selectFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                	
                	File selectedFile = fileChooser.getSelectedFile();
                    String filePath = selectedFile.getAbsolutePath();
                    
                    // Verificar si la extensión del archivo es .csv
                    if (filePath.toLowerCase().endsWith(".csv")) {
                        //System.out.println("ruta fichero: " + filePath);
                        
                        // Borrar todos los datos antes de insertar los del nuevo archivo CSV
                        ResultatModel mResultat = ResultatModel.getInstance();
                        mResultat.clearAll();
                        
						// Insertar los datos del nuevo archivo CSV
                        Controller controller = new Controller();
                        controller.insertarCsv(filePath); // Llamar al método insertarCsv con la ruta del nuevo archivo CSV
                        
                        JOptionPane.showMessageDialog(null, "Archivo CSV cargado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        
                    } else {
                        // El archivo seleccionado no es un archivo CSV
                        JOptionPane.showMessageDialog(null, "Solo se permite cargar archivos CSV", "Aviso", JOptionPane.WARNING_MESSAGE);
                    }
                    
                    
                }
            }
        });
		
	}

}

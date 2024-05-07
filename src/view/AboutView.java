package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AboutView extends JDialog {

    private static final long serialVersionUID = 1L;

    public AboutView(JFrame parent) {
        super(parent, "Acerca de", true);
        initialize();
    }

    private void initialize() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);

        // paleta de colores
        Color backgroundColor = new Color(255, 255, 255);
        Color textColor = new Color(80, 80, 80);

        // fuente de letra
        Font latoFont = null;
        try {
            latoFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/lato/Lato-Regular.ttf")).deriveFont(15f);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se ha podido cargar la fuente del programa. Cargando fuente por defecto", "IOException o FontFormatException", JOptionPane.ERROR_MESSAGE);
        }

        // crear el logotipo
        String ruta = "src/assets/Votacions_logo.png";
        ImageIcon iconoOriginal = new ImageIcon(ruta);
        Image image = iconoOriginal.getImage();
        Image nuevaImagen = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH); // Redimensionar la imagen
        ImageIcon iconoRedimensionado = new ImageIcon(nuevaImagen);

        JLabel labelConImagen = new JLabel(iconoRedimensionado);

        // texto de información
        /*
         * He probado de poner JLabels para cada línea, pero o se deforma o no queda como quiero. Así que se queda con html.
         */
        String htmlTexto = "<html>";
        htmlTexto += "<h1>Votacions</h1>";
        htmlTexto += "<p style=\"margin-bottom: 5px;\">Versión 0324 (Marzo de 2024)</p>";
        htmlTexto += "<p style=\"margin-bottom: 5px;\">Creado por Gabriel Serrat Espejo</p>";
        htmlTexto += "<p style=\"margin-bottom: 5px;\">Logo icon: Gabriel Serrat Espejo - No license</p>";
        htmlTexto += "<p style=\"margin-bottom: 5px;\">Lato font: Lukasz Dziedzic - OFL license</p>";
        htmlTexto += "</html>";
        JLabel lbl_info = new JLabel(htmlTexto);
        lbl_info.setFont(latoFont);
        lbl_info.setForeground(textColor);
        lbl_info.setHorizontalAlignment(SwingConstants.CENTER);

        // panel principal
        JPanel pnl_main = new JPanel();
        pnl_main.setBackground(backgroundColor);
        pnl_main.add(labelConImagen);
        pnl_main.add(lbl_info);

        getContentPane().add(pnl_main);

        pack();
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }
}
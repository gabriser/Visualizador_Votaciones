package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

public class MyButton extends JButton {

	private static final long serialVersionUID = 1L;

	public MyButton(String text) {
		
		super(text);
		
		// paleta de colores
		Color buttonBgColor = new Color(102, 102, 102);
		Color buttonTextColor = new Color(255, 255, 255);
		Color buttonBgHoverColor = new Color(110, 190, 244);
		
		// paleta de fuentes y tamaño
		Font latoFont = null;
		try {
			latoFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/assets/lato/Lato-Regular.ttf")).deriveFont(15f);
		} catch (FontFormatException | IOException e) {
			JOptionPane.showMessageDialog(null, "No se ha podido cargar la fuente del programa. Cargando fuente por defecto", "IOException o FontFormatException", JOptionPane.ERROR_MESSAGE);
		}
		
		// diseño del boton
		this.setFocusable(false);
		this.setFont(latoFont);
		this.setBorder(new LineBorder(buttonBgColor, 10));
		this.setForeground(buttonTextColor);
		this.setBackground(buttonBgColor);
		
		// efecto hover
		this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
            	MyButton.this.setBackground(buttonBgHoverColor);
            	MyButton.this.setBorder(new LineBorder(buttonBgHoverColor, 10));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            	MyButton.this.setBackground(buttonBgColor);
            	MyButton.this.setBorder(new LineBorder(buttonBgColor, 10));
            }
		});
		
	}
	
}

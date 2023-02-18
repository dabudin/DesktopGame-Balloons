package game;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Globo extends JButton {
	
	public static final int ANCHO = 120;
	public static final int ALTO = 170;

	private static final long serialVersionUID = 1L;
	private Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	private int x, y, dx, dy;
	
	public Globo(String name) {	
		//init variables
		x = (int) (Math.random()*(d.getWidth()-ANCHO));
		y = (int) (Math.random()*(d.getHeight()-ALTO-40));
		dx = (int) (Math.random()*6) + 1;
		signo(dx);
		dy = (int) (Math.random()*6) + 1;
		signo(dy);
		isOneWay();
		
		//Creo boton
		setSize(ANCHO, ALTO);
		setLocation(x,y);
		setVisible(true);	
		setBorderPainted(false); //Remuevo el borde por defecto del botón		
		setFocusPainted(false); //Remuevo el borde que aparece al hacer focus al botón
		setContentAreaFilled(false); //Remuevo el color azul que aparece de fondo cuando pulso el botón
		
		String r = String.valueOf((int) (Math.random()*15) + 1);
		setIcon(new ImageIcon("img/globo"+r+".png")); //Con este método añado la imagen del globo
				
	}
	
	private int signo(int n) {
		int signo = (int) (Math.random()*2);
		if (signo == 1) {
			n = -n; 
		}
		return n;
	}
	
	private void isOneWay() {
		int rand = (int) (Math.random()*7);
		if (rand == 0) {
			int direction = (int) (Math.random()*2);
			switch (direction) {
			case 0:
				dx = 0;
				break;
			case 1: 
				dy = 0;
				break;
			}
		}
	}
	
	public void update() {
		if (x + dx + Globo.ANCHO > d.getWidth() || x+dx<0) { dx = -dx; }
		if (y + dy + Globo.ALTO + 40 > d.getHeight() || y+dy<0) { dy = -dy; }
		x += dx;
		y += dy;
		setLocation(x,y);
		
	}
	
}

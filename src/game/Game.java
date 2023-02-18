package game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;

public class Game extends JFrame implements ActionListener {
	
	private static final int NGLOBOS = 30;
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JLabel score, felicidades;
	private int npuntos = 0;
	private ArrayList<Globo> globos = new ArrayList<Globo>();
	private Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	private AudioClip clip;
	public boolean isRunning = true;

	//main
	public static void main(String[] args) {
		Game game = new Game();
		GameEngine engine = new GameEngine(game);
		engine.run();	
	}

	//Constructor juego
	public Game() {
		//ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(d.width, d.height);
		contentPane = new JPanel();
		contentPane.setLayout(null); // 'setLayout(null)' crea un layout absoluto
		
		//Label score
		score = new JLabel("Score: "+npuntos);
		score.setFont(new Font("Segoe Script", Font.BOLD, 40));
		score.setForeground(Color.ORANGE);
		score.setBounds(1680, 970, 200, 50);
		contentPane.add(score);
		
		//Label felicita
		felicidades = new JLabel("Enhorabuena !!!");
		felicidades.setFont(new Font("Gabriola", Font.ITALIC, 200));
		felicidades.setForeground(Color.PINK);
		final int FEL_WIDTH = 1050;
		final int FEL_HEIGHT = 525;
		final int FEL_X = (int) (d.getWidth()-FEL_WIDTH)/2;
		final int FEL_Y = (int) (d.getHeight()-FEL_HEIGHT)/2;
		felicidades.setBounds(FEL_X, FEL_Y, FEL_WIDTH, FEL_HEIGHT);
		contentPane.add(felicidades);
		felicidades.setVisible(false);
		
		//sonido
		loadSound();
		
		//globos
		for (int i=0; i<NGLOBOS; i++) {			
			Globo globo;
			globo = new Globo("globo"+i);
			globo.addActionListener(this);
			globos.add(globo);
			contentPane.add(globo);	
		}
		
		setContentPane(contentPane);
		setVisible(true);
	}
	
	private void loadSound() {
		File file = new File("sound/GunShot.wav");
		try {
			clip = Applet.newAudioClip(file.toURI().toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		clip.play();
		Globo globo = (Globo) e.getSource();
		contentPane.remove(globo);
		contentPane.repaint(); //Método para agilizar el refresque de la pantalla
		
		npuntos++;
		score.setText("Score: "+npuntos);
		if (npuntos == NGLOBOS) { isRunning = false; }
	}
	
	public void update() {
		for (Globo globo : globos) {
			globo.update();
		}
	}
	
	public void draw() {
		contentPane.repaint();
	}
	
	public void felicita() {
		felicidades.setVisible(true);
	}

}

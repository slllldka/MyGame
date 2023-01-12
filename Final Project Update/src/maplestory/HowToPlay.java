package maplestory;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

//class for showing how to play game
public class HowToPlay extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame frame; //frame from Maplestory class(Main class)
	private ImageIcon Logo, Control, BackGround; //GameLogo, How to Play, Background
	private JButton OKButton; //OK button
	
	public HowToPlay(JFrame _frame, JButton _ok) {
		frame = _frame;
		OKButton = _ok;
		//set contentpane
		setPreferredSize(new Dimension(1366, 768));
		setBorder(null);
		setLayout(null);
		
		//set Game Logo
		Logo = new ImageIcon(getClass().getClassLoader().getResource("Logo2.png"));
		
		//how to play (show image)
		Control = new ImageIcon(getClass().getClassLoader().getResource("HowToPlay.png"));
		
		//Background image
		BackGround = new ImageIcon(getClass().getClassLoader().getResource("Lease_Port.png"));
	}

	@Override
	public void paint(Graphics g) {
		frame.pack();
		Graphics2D g2 = (Graphics2D) g;
		Image image = createImage(1366, 768);
		if(image != null) {
			Draw(image.getGraphics());
			g2.drawImage(image, 0, 0, this);
		}
		
		repaint();
	}
	
	public void Draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		g2.drawImage(BackGround.getImage(), 0, 0, this);
		g2.drawImage(Logo.getImage(), 490, 31, this);
		g2.drawImage(Control.getImage(), 420, 205, this);
		
		paintComponents(g);
	}
	
	//setup means add to JFrame, JPanel
	public void setup() {
		frame.add(this);
		add(OKButton);
	}
	//open
	public void open() {
		setup();
		frame.setContentPane(this);
		frame.pack();
		
		Maplestory.keyConfig.setKeyBoard(this);
		grabFocus();
	}
	//close
	public void close() {
		frame.remove(this);
	}
}
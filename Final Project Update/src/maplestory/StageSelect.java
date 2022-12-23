package maplestory;
//class for selecting stages
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StageSelect extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Music
	protected Music StageSelect_Music;
	
	protected JFrame frame; //JFrame on Maplestory Class
	protected ImageIcon Logo, BackGround; //game logo, background
	protected JButton Stage1_Button, Stage2_Button, Stage3_Button, Stage4_Button; //stage buttons
	protected JButton BackButton;
	
	public StageSelect(JFrame _frame, JButton _stage1, JButton _stage2, JButton _stage3, JButton _stage4, JButton _Back) {
		StageSelect_Music = new Music("Orbis.wav", -1);
		
		frame = _frame;
		Stage1_Button = _stage1;
		Stage2_Button = _stage2;
		Stage3_Button = _stage3;
		Stage4_Button = _stage4;
		BackButton = _Back;
		
		//set contentpane
		setPreferredSize(new Dimension(1366, 768));
		setBorder(null);
		setLayout(null);
		
		//set game logo
		Logo = new ImageIcon(getClass().getClassLoader().getResource("Logo2.png"));
		
		//set background
		BackGround = new ImageIcon(getClass().getClassLoader().getResource("BlackMage.png"));
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
		
		paintComponents(g);
	}
	
	//setup means add to JFrame, JPanel
	public void setup() {
		frame.add(this);
		add(Stage1_Button);
		add(Stage2_Button);
		add(Stage3_Button);
		add(Stage4_Button);
		add(BackButton);
	}
	//open
	public void open() {
		Maplestory.StageNow = 0;
		if(!Maplestory.IsStage2Locked) {
			Stage2_Button.setEnabled(true);
		}
		if(!Maplestory.IsStage3Locked) {
			Stage3_Button.setEnabled(true);
		}
		if(!Maplestory.IsStage4Locked) {
			Stage4_Button.setEnabled(true);
		}
		
		setup();
		frame.setContentPane(this);
		if(Maplestory.isFullScreen) {
			if(Maplestory.device.getDisplayMode() != new DisplayMode(1366, 768, 32, 60)) {
				Maplestory.device.setDisplayMode(new DisplayMode(1366, 768, 32, 60));
			}
		}
		frame.pack();

		Maplestory.keysetting.Keyboard_Set(this);
		grabFocus();
		StageSelect_Music.play();
	}
	//close
	public void close() {
		StageSelect_Music.stop();
		frame.remove(this);
	}
}

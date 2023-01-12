package maplestory;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

//class for showing start screen
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Start extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame frame;
	
	private ImageIcon Logo, BackGround;
	private JButton StartButton, SettingButton, HelpButton, ExitButton;
	
	public Start(JFrame _frame, JButton _startbutton, JButton _settingbutton, JButton _helpbutton, JButton _exitbutton) {
		frame = _frame;
		StartButton = _startbutton;
		SettingButton = _settingbutton;
		HelpButton = _helpbutton;
		ExitButton = _exitbutton;
		
		//set contentpane
		setPreferredSize(new Dimension(1366, 768));
		setBorder(null);
		setLayout(null);
		
		//set game logo
		Logo = new ImageIcon(getClass().getClassLoader().getResource("Logo1.png"));
		
		//set background
		BackGround = new ImageIcon(getClass().getClassLoader().getResource("Maple_Tree_Hill.png"));
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
		g2.drawImage(Logo.getImage(), 750, 40, this);
		
		paintComponents(g);
	}
	
	//setup means add to JFrame, JPanel
	public void setup() {
		frame.add(this);
		add(StartButton);
		add(SettingButton);
		add(HelpButton);
		add(ExitButton);
	}
	//open
	public void open() {
		setup();
		frame.setContentPane(this);
		if(Maplestory.isFullScreen) {
			Maplestory.device.setDisplayMode(new DisplayMode(1366, 768, 32, 60));
		}
		frame.pack();
		
		Maplestory.keyConfig.setKeyBoard(this);
		grabFocus();
	}
	//close
	public void close() {
		frame.remove(this);
	}
}

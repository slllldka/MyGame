package maplestory;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;

public class Maplestory extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Full Screen
	protected static boolean isFullScreen;
	
	//Images
	private ImageIcon Icon = new ImageIcon(getClass().getClassLoader().getResource("Icon.png"));
	protected static Images images = new Images();

	//DB
	protected static Connection connection = null;
	protected static Statement statement = null;
	
	//Keyboard Setting
	protected static KeyBoardConfig keyConfig;
	
	//UIs
	protected static UI_Status_Bar ui_status_bar;
	protected static UI_MiniMap ui_minimap;
	protected static Shop_GeneralStore generalStore;
	protected static UI_Notice ui_notice;
	protected static UI_KeyConfig ui_keySetting;
	protected static UI_Quick_Slot ui_quick_slot;
	
	//Buttons
	private JButton StartButton, SettingButton, HelpButton, ExitButton, OKButton, BackButton, BackButton_SS;
	private JButton Stage1_Button, Stage2_Button, Stage3_Button, Stage4_Button;
	
	//Panels
	protected static Start start;
	protected static HowToPlay HTP;
	protected static StageSelect SS;
	protected static Map1 map1;
	protected static Map1_1 map1_1;
	protected static Map1_2 map1_2;
	protected static Map2 map2;
	protected static Map3 map3;
	protected static Map4 map4;
	protected static Map current_stage;
	
	//Stage Controlling Variables
	protected static int StageNow = -1; //0: Stage Select, 1 ~: Stage Number
		//Whether Stage is Locked
	protected static boolean IsStage2Locked = true;
	protected static boolean IsStage3Locked = true;
	protected static boolean IsStage4Locked = true;
	
	//player
	protected static Player player = null;
	
	//Background Musics
	protected static Music Start_Music = new Music("Start.wav", -1);
	
	//File Read, Write
	protected static File Directory = new File("./JavaProjectData"); //variable for making new folder
	protected static BufferedReader BR; //read
	protected static BufferedWriter BW; //write
	protected static int read = -1; //storing result of reading
	
	//Thread Pool
	protected static ExecutorService thread_pool;
	
	//Graphics Device
	protected static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Maplestory frame = new Maplestory();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Maplestory() {
		make_Threads();
		
		setTitle("MapleStory");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Icon.getImage());
		setResizable(false);
		
		isFullScreen = false;
		//setUndecorated(true);
		//device.setFullScreenWindow(this);
		
		dbSetup();
		
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(images.Default_CursorImg.getImage(), new Point(0, 0), " "));
		
/*		if(!Directory.exists()) {
			Directory.mkdir();
		}
		
		setLock2();
		setLock3();
		setLock4();*/

		make_StartButton();
		make_SettingButton();
		make_HelpButton();
		make_ExitButton();
		make_OKButton();
		make_BackButton();
		make_Stage_Button();
		
		make_StageSelect();
		
		player = new Player("slllldka");
		keyConfig = new KeyBoardConfig(this);
		
		make_UIs();
		makeShops();
		makeMaps();
		set_Portals();
		
		//map1_2.open(null);
		start_Start();
		Start_Music.play();
	}
	
	

	public void make_Threads() {
		thread_pool = Executors.newFixedThreadPool(1000);
	}
	
	public void dbSetup() {
		try { 
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			 System.out.println("Driver Loading Failed");
			e.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "slllldka", "slllldka");
			System.out.println("DB Connection Success");
		} catch (SQLException e) {
			 System.out.println("DB Connection Failed");
				e.printStackTrace();
		}
	}
	
	//Set IsStage2Locked
	public void setLock2() {
		try {
			BR = new BufferedReader(new FileReader("./JavaProjectData/Lock2.jw"));
			read = BR.read();
			if(read == 1) {
				IsStage2Locked = true;
			}
			else if(read == 0) {
				IsStage2Locked = false;
			}
			BR.close();
		} catch (IOException e) {
			IsStage2Locked = true;
			try {
				BW = new BufferedWriter(new FileWriter("./JavaProjectData/Lock2.jw"));
				BW.write(1);
				BW.flush();
				BW.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	//Set IsStage3Locked	
	public void setLock3() {
		try {
			BR = new BufferedReader(new FileReader("./JavaProjectData/Lock3.jw"));
			read = BR.read();
			if(read == 1) {
				IsStage3Locked = true;
			}
			else if(read == 0) {
				IsStage3Locked = false;
			}
			BR.close();
		} catch (IOException e) {
			IsStage3Locked = true;
			try {
				BW = new BufferedWriter(new FileWriter("./JavaProjectData/Lock3.jw"));
				BW.write(1);
				BW.flush();
				BW.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	public void setLock4() {
		try {
			BR = new BufferedReader(new FileReader("./JavaProjectData/Lock4.jw"));
			read = BR.read();
			if(read == 1) {
				IsStage4Locked = true;
			}
			else if(read == 0) {
				IsStage4Locked = false;
			}
			BR.close();
		} catch (IOException e) {
			IsStage4Locked = true;
			try {
				BW = new BufferedWriter(new FileWriter("./JavaProjectData/Lock4.jw"));
				BW.write(1);
				BW.flush();
				BW.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	//Make StartButton
	public void make_StartButton() {
		StartButton = new JButton("");
		StartButton.setBounds(735, 400, 200, 70);
		StartButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Start2.png")));
		StartButton.setPressedIcon(new ImageIcon(getClass().getClassLoader().getResource("Start2-1.png")));
		StartButton.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("Start2-2.png")));
		StartButton.setBorderPainted(false);
		StartButton.setFocusable(false);
		StartButton.addActionListener(new ButtonHandler());
	}
	//Make SettingButton
	public void make_SettingButton() {
		SettingButton = new JButton("");
		SettingButton.setBounds(950, 400, 200, 70);
		SettingButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Setting.png")));
		SettingButton.setPressedIcon(new ImageIcon(getClass().getClassLoader().getResource("Setting1.png")));
		SettingButton.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("Setting2.png")));
		SettingButton.setBorderPainted(false);
		SettingButton.setFocusable(false);
		SettingButton.addActionListener(new ButtonHandler());
	}
	//Make HelpButton
	public void make_HelpButton() {
		HelpButton = new JButton("");
		HelpButton.setBounds(735, 480, 200, 70);
		HelpButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Help.png")));
		HelpButton.setPressedIcon(new ImageIcon(getClass().getClassLoader().getResource("Help1.png")));
		HelpButton.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("Help2.png")));
		HelpButton.setBorderPainted(false);
		HelpButton.setFocusable(false);
		HelpButton.addActionListener(new ButtonHandler());
	}
	//Make ExitButton
	public void make_ExitButton() {
		ExitButton = new JButton("");
		ExitButton.setBounds(950, 480, 200, 70);
		ExitButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Exit.png")));
		ExitButton.setPressedIcon(new ImageIcon(getClass().getClassLoader().getResource("Exit1.png")));
		ExitButton.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("Exit2.png")));
		ExitButton.setBorderPainted(false);
		ExitButton.setFocusable(false);
		ExitButton.addActionListener(new ButtonHandler());
	}
	
	//Make OKButton
	public void make_OKButton() {
		OKButton = new JButton("");
		OKButton.setBounds(600, 500, 100, 100);
		OKButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("OKIcon.png")));
		OKButton.setPressedIcon(new ImageIcon(getClass().getClassLoader().getResource("OKPressedIcon.png")));
		OKButton.setRolloverEnabled(true);
		OKButton.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("OKRolloverIcon.png")));
		OKButton.setBorder(new LineBorder(Color.BLACK, 2, true));
		OKButton.addActionListener(new ButtonHandler());
	}
	//Make BackButton
	public void make_BackButton() {
		BackButton = new JButton("");
		BackButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource("BackIcon.png")));
		BackButton.setPressedIcon(new ImageIcon(getClass().getClassLoader().getResource("BackPressedIcon.png")));
		BackButton.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("BackRolloverIcon.png")));
		BackButton.setDisabledIcon(new ImageIcon(getClass().getClassLoader().getResource("BackPressedIcon.png")));
		BackButton.setBorderPainted(false);
		BackButton.setFocusable(false);
		BackButton.addActionListener(new ButtonHandler());
		
		BackButton_SS = new JButton("");
		BackButton_SS.setBounds(1296, 698, 70, 70);
		BackButton_SS.setIcon(new ImageIcon(getClass().getClassLoader().getResource("BackIcon.png")));
		BackButton_SS.setPressedIcon(new ImageIcon(getClass().getClassLoader().getResource("BackPressedIcon.png")));
		BackButton_SS.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("BackRolloverIcon.png")));
		BackButton_SS.setDisabledIcon(new ImageIcon(getClass().getClassLoader().getResource("BackPressedIcon.png")));
		BackButton_SS.setBorderPainted(false);
		BackButton_SS.setFocusable(false);
		BackButton_SS.addActionListener(new ButtonHandler());
	}
	//Make StageButton
	public void make_Stage_Button() {
		Stage1_Button = new JButton("");
		Stage1_Button.setBounds(144, 300, 145, 145);
		Stage1_Button.addActionListener(new ButtonHandler());
		Stage1_Button.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Stage1Icon.png")));
		Stage1_Button.setPressedIcon(new ImageIcon(getClass().getClassLoader().getResource("Stage1PressedIcon.png")));
		Stage1_Button.setRolloverEnabled(true);
		Stage1_Button.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("Stage1RolloverIcon.png")));
		Stage1_Button.setBorderPainted(false);
		Stage1_Button.setFocusable(false);
		
		Stage2_Button = new JButton("");
		Stage2_Button.setBounds(433, 300, 145, 145);
		Stage2_Button.addActionListener(new ButtonHandler());
		Stage2_Button.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Stage2Icon.png")));
		Stage2_Button.setPressedIcon(new ImageIcon(getClass().getClassLoader().getResource("Stage2PressedIcon.png")));
		Stage2_Button.setRolloverEnabled(true);
		Stage2_Button.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("Stage2RolloverIcon.png")));
		Stage2_Button.setDisabledIcon(new ImageIcon(getClass().getClassLoader().getResource("Lock.png")));
		Stage2_Button.setBorderPainted(false);
		Stage2_Button.setEnabled(false);
		Stage2_Button.setFocusable(false);
		
		Stage3_Button = new JButton("");
		Stage3_Button.setBounds(722, 300, 145, 145);
		Stage3_Button.addActionListener(new ButtonHandler());
		Stage3_Button.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Stage3Icon.png")));
		Stage3_Button.setPressedIcon(new ImageIcon(getClass().getClassLoader().getResource("Stage3PressedIcon.png")));
		Stage3_Button.setRolloverEnabled(true);
		Stage3_Button.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("Stage3RolloverIcon.png")));
		Stage3_Button.setDisabledIcon(new ImageIcon(getClass().getClassLoader().getResource("Lock.png")));
		Stage3_Button.setBorderPainted(false);
		Stage3_Button.setEnabled(false);
		Stage3_Button.setFocusable(false);
		
		Stage4_Button = new JButton("");
		Stage4_Button.setBounds(1011, 300, 145, 145);
		Stage4_Button.addActionListener(new ButtonHandler());
		Stage4_Button.setIcon(new ImageIcon(getClass().getClassLoader().getResource("Stage4Icon.png")));
		Stage4_Button.setPressedIcon(new ImageIcon(getClass().getClassLoader().getResource("Stage4PressedIcon.png")));
		Stage4_Button.setRolloverEnabled(true);
		Stage4_Button.setRolloverIcon(new ImageIcon(getClass().getClassLoader().getResource("Stage4RolloverIcon.png")));
		Stage4_Button.setDisabledIcon(new ImageIcon(getClass().getClassLoader().getResource("Lock.png")));
		Stage4_Button.setBorderPainted(false);
		Stage4_Button.setEnabled(false);
		Stage4_Button.setFocusable(false);
	}
	
	//Make Screens
	public void make_UIs() {
		ui_status_bar = new UI_Status_Bar(BackButton);
		ui_minimap = new UI_MiniMap();
		ui_keySetting = new UI_KeyConfig();
		ui_notice = new UI_Notice();
		ui_quick_slot = new UI_Quick_Slot();
	}
	
	//Make Shops
	public void makeShops() {
		generalStore = new Shop_GeneralStore();
	}
	
	//Start Start Page
	public void start_Start() {
		start = new Start(this, StartButton, SettingButton, HelpButton, ExitButton);
		start.open();
	}
	//Start HowToPlay Page
	public void start_HowToPlay() {
		HTP = new HowToPlay(this, OKButton);
		HTP.setup();
		HTP.open();
	}
	public void make_StageSelect() {
		SS = new StageSelect(this, Stage1_Button, Stage2_Button, Stage3_Button, Stage4_Button, BackButton_SS);
	}
	
	public void makeMaps() {
		makeMap1();
		makeMap1_1();
		makeMap1_2();
		makeMap2();
		makeMap3();
		makeMap4();
	}
	
	public void makeMap1() {
		map1 = new Map1(1, "Henesys.png", "Ground1_Long.png", "Foothold1.png", "Foothold2.png"
				, this, BackButton, true, null);
		map1.nearestTown = map1;
		map1.CharacterFirstImg = player.characterRightImg;
	}
	public void makeMap1_1() {
		map1_1 = new Map1_1(1, "Henesys.png", "Ground1_Long.png", "Foothold1.png", "Foothold2.png"
				, this, BackButton, false, map1);
		map1_1.CharacterFirstImg = player.characterRightImg;
	}
	public void makeMap1_2() {
		map1_2 = new Map1_2(1, "Henesys.png", "Ground1_Long.png", "Foothold1.png", "Foothold2.png"
				, this, BackButton, false, map1);
		map1_2.CharacterFirstImg = player.characterRightImg;
	}
	
	public void makeMap2() {
		map2 = new Map2(1, "Ellinia.png", "Ground2_Long.png", "Foothold3.png", "Foothold4.png"
				, this, BackButton, false, map1);
		map2.CharacterFirstImg = player.characterRightImg;
	}
	
	public void makeMap3() {
		map3 = new Map3(-1, "SleepyWood.png", "Ground3_Long.png", "Foothold5.png", "Foothold6.png"
				, this, BackButton, false, map1);
		map3.CharacterFirstImg = player.characterLeftImg;
	}
	
	public void makeMap4() {
		map4 = new Map4(1, "Ellinia.png", "Ground2_Long.png", "Foothold3.png", "Foothold4.png"
				, this, BackButton, false, map1);
		map4.CharacterFirstImg = player.characterRightImg;
	}
	//Set Link
	public void set_Portals() {
		//Make Portals
		map1.setPortal(new Portal(1725, 760, map1));
		map1.setPortal(new Portal(1650, 360, map1));
		map1_1.setPortal(new Portal(75, 1080, map1_1));
		map1_1.setPortal(new Portal(1755, 1110, map1_1));
		map1_2.setPortal(new Portal(70, 310, map1_2));
		map2.setPortal(new Portal(75, 760, map2));
		map2.setPortal(new Portal(100, 310, map2));
		map3.setPortal(new Portal(1725, 760, map3));
		map3.setPortal(new Portal(560, 620, map3));
		map4.setPortal(new Portal(75, 760, map4));

		//Set links between Portals
		map1.Portal_List.get(0).link = map1_1.Portal_List.get(0);
		map1.Portal_List.get(1).link = map2.Portal_List.get(0);
		map1_1.Portal_List.get(0).link = map1.Portal_List.get(0);
		map1_1.Portal_List.get(1).link = map1_2.Portal_List.get(0);
		map1_2.Portal_List.get(0).link = map1_1.Portal_List.get(1);
		map2.Portal_List.get(0).link = map1.Portal_List.get(1);
		map2.Portal_List.get(1).link = map3.Portal_List.get(0);
		map3.Portal_List.get(0).link = map2.Portal_List.get(1);
		map3.Portal_List.get(1).link = map4.Portal_List.get(0);
		map4.Portal_List.get(0).link = map3.Portal_List.get(1);
	}
	
	//ButtonHandling class
	public class ButtonHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//start button
			if((JButton)e.getSource() == StartButton) {
				start.close();
				Start_Music.stop();
				SS.open();
			}
			//setting button
			else if((JButton)e.getSource() == SettingButton) {
				
			}
			//help button
			else if((JButton)e.getSource() == HelpButton) {
				StageNow = -1;
				start.close();
				start_HowToPlay();
			}
			//exit button
			else if((JButton)e.getSource() == ExitButton) {
				thread_pool.shutdown();
				System.exit(0);
			}
			//ok button
			else if((JButton)e.getSource() == OKButton) {
				HTP.close();
				start_Start();
			}
			//back button
			else if((JButton)e.getSource() == BackButton) {
				Runnable runnable = new Runnable() {

					@Override
					public void run() {
						Map.Back_Pressed = true;
						if(StageNow == 1) {
							map1.close(null);
						}
						else if(StageNow == 11) {
							map1_1.close(null);
						}
						else if(StageNow == 12) {
							map1_2.close(null);
						}
						else if(StageNow == 2) {
							map2.close(null);
						}
						else if(StageNow == 3) {
							map3.close(null);
						}
						else if(StageNow == 4) {
							map4.close(null);
						}
					}
					
				};
				Maplestory.thread_pool.submit(runnable);
			}
			else if((JButton)e.getSource() == BackButton_SS) {
				SS.close();
				start_Start();
				Start_Music.play();
			}
			else {
				SS.close();
				
				//stage 1 button
				if((JButton)e.getSource() == Stage1_Button) {
					try {
						String query = "SELECT * FROM PLAYER WHERE NAME = ?";
						PreparedStatement pstat = connection.prepareStatement(query);
						ResultSet resultSet = null;
						int StageNum = -1;
						pstat.setString(1, Maplestory.player.name);
						resultSet = pstat.executeQuery();
						resultSet.next();
						StageNum = resultSet.getInt("MAP");
						
						if(StageNum == 1) {
							map1.open(null);
						}
						if(StageNum == 1_1) {
							map1_1.open(null);
						}
						if(StageNum == 1_2) {
							map1_2.open(null);
						}
						if(StageNum == 2) {
							map2.open(null);
						}
						if(StageNum == 3) {
							map3.open(null);
						}
						if(StageNum == 4) {
							map4.open(null);
						}
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				//stage 2 button
				else if((JButton)e.getSource() == Stage2_Button) {
					map2.open(null);
				}
				//stage 3 button
				else if((JButton)e.getSource() == Stage3_Button) {
					map3.open(null);
				}
				//stage 4 button
				else if((JButton)e.getSource() == Stage4_Button) {
					map4.open(null);
				}
			}
		}
		
	}
}

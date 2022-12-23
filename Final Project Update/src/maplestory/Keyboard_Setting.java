package maplestory;

import java.awt.AWTException;
import java.awt.DisplayMode;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Iterator;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Keyboard_Setting {
	private JFrame frame;
	private JPanel panel;
	private DisplayMode displaymode;
	private boolean stable = true;
	
	private boolean Ladder_Checking = false;
	private boolean Ladder_Jump_Checking = false;
	
	protected static Robot robot;
	
	private FullScreen fullScreen = new FullScreen();
	private Jump jump = new Jump();
	private LeftPress leftPress = new LeftPress();
	private LeftRelease leftRelease = new LeftRelease();
	private RightRelease rightRelease = new RightRelease();
	private RightPress rightPress = new RightPress();
	private UpPress upPress = new UpPress();
	private UpRelease upRelease = new UpRelease();
	private DownPress downPress = new DownPress();
	private DownRelease downRelease = new DownRelease();
	private AltRelease altRelease = new AltRelease();
	private AltSpacePress altspacePress = new AltSpacePress();
	private BadKeyPress badkeyPress = new BadKeyPress();
	private CtrlPress ctrlPress = new CtrlPress();
	private ShiftPress shiftPress = new ShiftPress();
	private IPress iPress = new IPress();
	private MPress mPress = new MPress();
	private ZPress zPress = new ZPress();
	private EscPress escPress = new EscPress();
	private TabPress tabPress = new TabPress();
	protected ItemUse[] itemUse = new ItemUse[8];

	public Keyboard_Setting(JFrame _frame) {
		frame = _frame;
		
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		itemUse[0] = new ItemUse();
		itemUse[1] = new ItemUse();
		itemUse[2] = new ItemUse();
		itemUse[3] = new ItemUse();
		itemUse[4] = new ItemUse();
		itemUse[5] = new ItemUse();
		itemUse[6] = new ItemUse();
		itemUse[7] = new ItemUse();
	}

	public void Add_To_Keyboard(int keyCode, String str_press) {
		//press
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(keyCode, 0), str_press);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(keyCode, InputEvent.ALT_DOWN_MASK), str_press);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(keyCode, InputEvent.CTRL_DOWN_MASK), str_press);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(keyCode, InputEvent.SHIFT_DOWN_MASK), str_press);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(keyCode, InputEvent.ALT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK), str_press);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(keyCode, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK), str_press);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(keyCode, InputEvent.SHIFT_DOWN_MASK | InputEvent.ALT_DOWN_MASK), str_press);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(keyCode, InputEvent.ALT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK), str_press);
	}
	
	public void Add_To_Keyboard(int keyCode, String str_press, String str_release) {
		//press
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(keyCode, 0, false), str_press);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(keyCode, InputEvent.ALT_DOWN_MASK, false), str_press);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(keyCode, InputEvent.CTRL_DOWN_MASK, false), str_press);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(keyCode, InputEvent.SHIFT_DOWN_MASK, false), str_press);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(keyCode, InputEvent.ALT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK, false), str_press);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(keyCode, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK, false), str_press);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(keyCode, InputEvent.SHIFT_DOWN_MASK | InputEvent.ALT_DOWN_MASK, false), str_press);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(keyCode, InputEvent.ALT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK, false), str_press);

		//release
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(keyCode, 0, true), str_release);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(keyCode, InputEvent.ALT_DOWN_MASK, true), str_release);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(keyCode, InputEvent.CTRL_DOWN_MASK, true), str_release);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(keyCode, InputEvent.SHIFT_DOWN_MASK, true), str_release);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(keyCode, InputEvent.ALT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK, true), str_release);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(keyCode, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK, true), str_release);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(keyCode, InputEvent.SHIFT_DOWN_MASK | InputEvent.ALT_DOWN_MASK, true), str_release);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(keyCode, InputEvent.ALT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK, true), str_release);
	}
	
	public void Keyboard_Set() {
		// Keyboard Event setting
		Maplestory.current_stage.setFocusTraversalKeysEnabled(false);
		Maplestory.current_stage.getActionMap().put("Jump", jump);
		Maplestory.current_stage.getActionMap().put("LeftPress", leftPress);
		Maplestory.current_stage.getActionMap().put("LeftRelease", leftRelease);
		Maplestory.current_stage.getActionMap().put("RightPress", rightPress);
		Maplestory.current_stage.getActionMap().put("RightRelease", rightRelease);
		Maplestory.current_stage.getActionMap().put("UpPress", upPress);
		Maplestory.current_stage.getActionMap().put("UpRelease", upRelease);
		Maplestory.current_stage.getActionMap().put("DownPress", downPress);
		Maplestory.current_stage.getActionMap().put("DownRelease", downRelease);
		Maplestory.current_stage.getActionMap().put("AltRelease", altRelease);
		Maplestory.current_stage.getActionMap().put("AltSpacePress", altspacePress);
		Maplestory.current_stage.getActionMap().put("BadKeyPress", badkeyPress);
		Maplestory.current_stage.getActionMap().put("CtrlPress", ctrlPress);
		Maplestory.current_stage.getActionMap().put("ShiftPress", shiftPress);
		Maplestory.current_stage.getActionMap().put("IPress", iPress);
		Maplestory.current_stage.getActionMap().put("MPress", mPress);
		Maplestory.current_stage.getActionMap().put("ZPress", zPress);
		Maplestory.current_stage.getActionMap().put("EscPress", escPress);
		Maplestory.current_stage.getActionMap().put("TabPress", tabPress);
		Maplestory.current_stage.getActionMap().put("ItemUse0", itemUse[0]);
		Maplestory.current_stage.getActionMap().put("ItemUse1", itemUse[1]);
		Maplestory.current_stage.getActionMap().put("ItemUse2", itemUse[2]);
		Maplestory.current_stage.getActionMap().put("ItemUse3", itemUse[3]);
		Maplestory.current_stage.getActionMap().put("ItemUse4", itemUse[4]);
		Maplestory.current_stage.getActionMap().put("ItemUse5", itemUse[5]);
		Maplestory.current_stage.getActionMap().put("ItemUse6", itemUse[6]);
		Maplestory.current_stage.getActionMap().put("ItemUse7", itemUse[7]);
		
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, InputEvent.ALT_DOWN_MASK, false), "AltSpacePress");
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, InputEvent.ALT_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK, false), "AltSpacePress");
		Add_To_Keyboard(KeyEvent.VK_LEFT, "LeftPress", "LeftRelease");
		Add_To_Keyboard(KeyEvent.VK_RIGHT, "RightPress", "RightRelease");
		Add_To_Keyboard(KeyEvent.VK_UP, "UpPress", "UpRelease");
		Add_To_Keyboard(KeyEvent.VK_DOWN, "DownPress", "DownRelease");
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ALT, InputEvent.ALT_DOWN_MASK, false), "Jump");
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ALT, 0, true), "AltRelease");
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_WINDOWS, 0, false), "BadKeyPress");
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0, false), "BadKeyPress");
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_CONTROL, InputEvent.ALT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK, false), "CtrlPress");
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_CONTROL, InputEvent.CTRL_DOWN_MASK, false), "CtrlPress");
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SHIFT, InputEvent.ALT_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK, false), "ShiftPress");
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SHIFT, InputEvent.SHIFT_DOWN_MASK, false), "ShiftPress");
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_I, 0, false), "IPress");
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_M, 0, false), "MPress");
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, 0, false), "ZPress");
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false), "EscPress");
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0, false), "TabPress");
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0, false), "ItemUse0");
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0, false), "ItemUse1");
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_3, 0, false), "ItemUse2");
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_4, 0, false), "ItemUse3");
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_5, 0, false), "ItemUse4");
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_6, 0, false), "ItemUse5");
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_7, 0, false), "ItemUse6");
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_8, 0, false), "ItemUse7");
	}
	
	public void Keyboard_Set(JPanel _panel) {
		panel = _panel;
		displaymode = new DisplayMode(panel.getWidth(), panel.getHeight(), 32, 60);
		panel.getActionMap().put("FullScreen", fullScreen);
		panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.ALT_DOWN_MASK, false), "FullScreen");
	}

	// below: Keyboard Event Classes
	
	//Alt+Enter (Full Screen)
	public class FullScreen extends AbstractAction{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			Runnable runnable = new Runnable() {

				@Override
				public void run() {
					stable = false;
					if(Maplestory.isFullScreen) {
						Maplestory.isFullScreen = false;
						Maplestory.device.setDisplayMode(new DisplayMode(1920, 1080, 32, 60));
						frame.dispose();
						frame.setUndecorated(false);
						Maplestory.device.setFullScreenWindow(frame);
						frame.pack();
					}
					else {
						Maplestory.isFullScreen = true;
						frame.dispose();
						frame.setUndecorated(true);
						Maplestory.device.setFullScreenWindow(frame);
						Maplestory.device.setDisplayMode(displaymode);
						frame.pack();
					}
					panel.grabFocus();
					
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					stable = true;
				}
				
			};
			
			if(stable) {
				Maplestory.thread_pool.submit(runnable);
			}
		}
		
	}
	
	// Jump Key
	public class Jump extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			Stage.AltKey = true;
			robot.keyPress(KeyEvent.VK_PAUSE);
			
			if(!Ladder_Jump_Checking) {
				Ladder_Jump_Checking = true;
				Runnable runnable = new Runnable() {

					@Override
					public void run() {
						while(true) {
							if(Maplestory.player.alive && Stage.Ladder && !Shop.isOpen) {
								if(Stage.AltKey && !Stage.DownKey && !Stage.Jump && !Stage.Attacking && !Stage.attacked) {
									if(Stage.LeftKey) {
										Maplestory.player.current_Img = Maplestory.player.characterJumpLeftImg;
										Maplestory.player.Character_LeftJump(true);
										try {
											Thread.sleep(Character.Ladder_Jump_Delay);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
									}
									else if(Stage.RightKey) {
										Maplestory.player.current_Img = Maplestory.player.characterJumpRightImg;
										Maplestory.player.Character_RightJump(true);
										try {
											Thread.sleep(Character.Ladder_Jump_Delay);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
									}
								}
							}
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					
				};
				Maplestory.thread_pool.submit(runnable);
			}
			
			if(Maplestory.player.alive && !Shop.isOpen) {
				if (Maplestory.player.IsLandable() && !Stage.Attacking && !Stage.attacked) {
					if (Stage.DownKey) {
						if (!Maplestory.player.cur_foothold.isBottom) {
							if (Maplestory.player.CharDirection == -1) {
								Maplestory.player.current_Img = Maplestory.player.characterJumpLeftImg;
							} else if (Maplestory.player.CharDirection == 1) {
								Maplestory.player.current_Img = Maplestory.player.characterJumpRightImg;
							}
							Maplestory.player.Character_DownJump();
						}
					}
					else if (Stage.LeftKey) {
						Maplestory.player.current_Img = Maplestory.player.characterJumpLeftImg;
						Maplestory.player.Character_LeftJump(false);
					}
					else if (Stage.RightKey) {
						Maplestory.player.current_Img = Maplestory.player.characterJumpRightImg;
						Maplestory.player.Character_RightJump(false);
					}
					else {
						if (Maplestory.player.CharDirection == -1) {
							Maplestory.player.current_Img = Maplestory.player.characterJumpLeftImg;
						} else if (Maplestory.player.CharDirection == 1) {
							Maplestory.player.current_Img = Maplestory.player.characterJumpRightImg;
						}
						Maplestory.player.Character_Jump();
					}
				}
			}
		}
	}

	// Left Key Press
	public class LeftPress extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			Stage.LeftKey = true;
			if(Maplestory.player.alive && !Shop.isOpen) {
				if (Stage.Attacking == false) {
					Maplestory.player.CharDirection = -1;
					if (Stage.Jump == true) {
						Maplestory.player.current_Img = Maplestory.player.characterJumpLeftImg;
					} else {
						if (!Stage.Left && !Stage.Ladder) {
							Stage.Right = false;
							Maplestory.player.current_Img = Maplestory.player.characterLeftImg;
							Maplestory.player.Character_Left();
						}
					}
				}
			}
		}
	}

	// Left Key Release
	public class LeftRelease extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			Stage.LeftKey = false;
			/*if (Stage.Attacking == false) {
				if (Stage.RightKey == true && Stage.Right == false && Stage.Jump == false) {
					Maplestory.player.current_Img = Maplestory.player.characterRightImg;
					Maplestory.player.Character_Right();
				}
			}*/
		}

	}

	// Right Key Press
	public class RightPress extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			Stage.RightKey = true;
			if(Maplestory.player.alive && !Shop.isOpen) {
				if (Stage.Attacking == false) {
					Maplestory.player.CharDirection = 1;
					if (Stage.Jump == true) {
						Maplestory.player.current_Img = Maplestory.player.characterJumpRightImg;
					} else {
						if (!Stage.Right && !Stage.Ladder) {
							Stage.Left = false;
							Maplestory.player.current_Img = Maplestory.player.characterRightImg;
							Maplestory.player.Character_Right();
						}
					}
				}
			}
		}
	}

	// Right Key Release
	public class RightRelease extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			Stage.RightKey = false;
			/*if (Stage.Attacking == false) {
				if (Stage.LeftKey == true && Stage.Left == false && Stage.Jump == false) {
					Maplestory.player.current_Img = Maplestory.player.characterLeftImg;
					Maplestory.player.Character_Left();
				}
			}*/
		}
	}

	// Up Key Press
	public class UpPress extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			Stage.UpKey = true;
			if (!Ladder_Checking) {
				Ladder_Checking = true;
				Runnable runnable = new Runnable() {

					@Override
					public void run() {
						int LadderIdx;
						while(true) {
							if(Maplestory.player.alive && !Shop.isOpen
									&& Stage.UpKey && !Stage.Up && !Stage.Down && !Stage.Attacking && !Stage.attacked && !Stage.Ladder_Jump) {
								if ((LadderIdx = Maplestory.player.IsLadderAvailable(1)) >= 0) {
									Maplestory.player.Character_LadderUp(LadderIdx);
									try {
										Thread.sleep(Character.Ladder_Jump_Delay);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
							}
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					
				};
				Maplestory.thread_pool.submit(runnable);
			}
			if(Maplestory.player.alive && !Shop.isOpen) {
				if (!Stage.Attacking) {
					for(Portal portal : Maplestory.current_stage.Portal_List) {
						if ((Maplestory.player.CharacterX + 25 >= portal.xstart)
								&& (Maplestory.player.CharacterX + 25 <= portal.xend)
								&& (portal.y >= Maplestory.player.CharacterY + 20)
								&& (portal.y <= Maplestory.player.CharacterY + 120)) {
							Runnable runnable = new Runnable() {
		
								@Override
								public void run() {
									Music Portal_Music = new Music("Portal.wav", 1);
									Portal_Music.play();
									
									Stage.Left = false;
									Stage.Right = false;
									Stage.Up = false;
									Stage.Down = false;
									Stage.Ladder = false;
		
									if (Maplestory.StageNow == 1) {
										Maplestory.stage1.close(portal);
									} else if (Maplestory.StageNow == 11) {
										/*boolean open = true;
										for(Mob mob : Maplestory.current_stage.Mob_List) {
											if(mob.alive) {
												open = false;
												break;
											}
										}
										
										if(!open) {
											UI_Status_Bar.notice = "몬스터를 모두 해치워야 합니다.";
											return;
										}
										
										if (Maplestory.IsStage2Locked) {
											Maplestory.IsStage2Locked = false;
											try {
												if (!Maplestory.Directory.exists()) {
													Maplestory.Directory.mkdir();
												}
												Maplestory.BW = new BufferedWriter(
														new FileWriter("./JavaProjectData/Lock2.jw"));
												Maplestory.BW.write(0);
												Maplestory.BW.flush();
												Maplestory.BW.close();
											} catch (IOException e1) {
												e1.printStackTrace();
											}
										}*/
										Maplestory.stage1_1.close(portal);
									} else if (Maplestory.StageNow == 2) {
										/*if (Maplestory.IsStage3Locked) {
											Maplestory.IsStage3Locked = false;
											try {
												if (!Maplestory.Directory.exists()) {
													Maplestory.Directory.mkdir();
												}
												Maplestory.BW = new BufferedWriter(
														new FileWriter("./JavaProjectData/Lock3.jw"));
												Maplestory.BW.write(0);
												Maplestory.BW.flush();
												Maplestory.BW.close();
											} catch (IOException e1) {
												e1.printStackTrace();
											}
										}*/
										Maplestory.stage2.close(portal);
									} else if (Maplestory.StageNow == 3) {
										/*if (Maplestory.IsStage4Locked) {
											Maplestory.IsStage4Locked = false;
											try {
												if (!Maplestory.Directory.exists()) {
													Maplestory.Directory.mkdir();
												}
												Maplestory.BW = new BufferedWriter(
														new FileWriter("./JavaProjectData/Lock4.jw"));
												Maplestory.BW.write(0);
												Maplestory.BW.flush();
												Maplestory.BW.close();
											} catch (IOException e1) {
												e1.printStackTrace();
											}
										}*/
										Maplestory.stage3.close(portal);
									} else if (Maplestory.StageNow == 4) {
										Maplestory.stage4.close(portal);
									}
								}
		
							};
							Maplestory.thread_pool.submit(runnable);
						}
					}
				}
			}
		}
	}

	// Up Key Release
	public class UpRelease extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			Stage.UpKey = false;
		}

	}

	// Down Key Press
	public class DownPress extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			Stage.DownKey = true;
			if(Maplestory.player.alive && !Shop.isOpen) {
				if (!Stage.Up && !Stage.Down && !Stage.Attacking && !Stage.Jump) {
					int LadderIdx;
					if ((LadderIdx = Maplestory.player.IsLadderAvailable(-1)) >= 0) {
						Maplestory.player.Character_LadderDown(LadderIdx);
					} else if (Stage.LeftKey == false && Stage.RightKey == false && Stage.Jump == false
							&& Stage.Ladder == false) {
						if (Maplestory.player.CharDirection == -1) {
							Maplestory.player.current_Img = Maplestory.player.characterProneLeftImg;
						} else if (Maplestory.player.CharDirection == 1) {
							Maplestory.player.current_Img = Maplestory.player.characterProneRightImg;
						}
					}
				}
			}

		}

	}

	// Down Key Release
	public class DownRelease extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			Stage.DownKey = false;
			if(Maplestory.player.alive && !Shop.isOpen) {
				if (Stage.Ladder == false && Stage.Attacking == false) {
					if (Stage.LeftKey == false && Stage.RightKey == false && Stage.Jump == false) {
						if (Maplestory.player.CharDirection == -1) {
							Maplestory.player.current_Img = Maplestory.player.characterLeftImg;
						} else if (Maplestory.player.CharDirection == 1) {
							Maplestory.player.current_Img = Maplestory.player.characterRightImg;
						}
					}
				}
			}

		}

	}
	
	//Alt Release
	public class AltRelease extends AbstractAction{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			Stage.AltKey = false;
		}
		
	}

	// Alt Space Press
	public class AltSpacePress extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			robot.keyPress(KeyEvent.VK_ALT);
			Maplestory.player.LevelUp();
		}
		
	}
	
	// Bad Key Press(Bad Keys: WINDOWS, F10)
	public class BadKeyPress extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}	

	}

	// Ctrl Key Press
	public class CtrlPress extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			if(Maplestory.player.alive && !Shop.isOpen) {
				if (!Stage.Attacking && !Stage.Ladder) {
					Maplestory.player.Character_Attack(1, 70, Character.CharacterWidth / 2, Character.CharacterHeight / 2,
							Character.CharacterHeight / 2, 1, 100);
				}
			}

		}

	}

	// Shift Key Press
	public class ShiftPress extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			if(Maplestory.player.alive && !Shop.isOpen) {
				if (!Stage.Attacking && !Stage.Ladder) {
					int cost = 10;
					if (Maplestory.player.MP >= cost) {
						Maplestory.player.Character_Attack(2, 200, Character.CharacterWidth / 2
								, Character.CharacterHeight / 2, Character.CharacterHeight / 2, (2+Maplestory.player.Level / 10), 120);
						Maplestory.player.MP_Use(cost);
					}
					else {
						UI_Status_Bar.notice = "스킬을 사용하는 데 필요한 MP가 부족합니다.";
					}
				}
			}

		}

	}

	// I Key Press
	public class IPress extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!Maplestory.player.inventory.getOpen()) {
				Maplestory.player.inventory.Open();
			} else {
				Maplestory.player.inventory.Close();
			}
		}

	}

	// M Key Press
	public class MPress extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			if(Maplestory.current_stage.MiniMap_Available) {
				if (!Maplestory.minimap.isOpen) {
					Maplestory.minimap.Open();
				} else {
					Maplestory.minimap.Close();
				}
			}
		}
		
	}
	
	// Z Key Press
	public class ZPress extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			Runnable runnable = new Runnable() {

				@Override
				public void run() {
					int Char_X_Center = Maplestory.player.CharacterX + Character.CharacterWidth / 2;
					int Char_Y = Maplestory.player.CharacterY + Character.CharacterHeight * 5 / 6;
					synchronized (Maplestory.current_stage.Item_List) {
						Iterator<Item> iter1 = Maplestory.current_stage.Item_List.iterator();
						while (iter1.hasNext()) {
							Item data = iter1.next();
							if (Char_X_Center + 15 > data.X_Center - data.getRawIcon().getIconWidth() / 2
									&& Char_X_Center - 15 < data.X_Center + data.getRawIcon().getIconWidth() / 2
									&& Char_Y + 10 > data.Y_Center - data.getRawIcon().getIconHeight() / 2
									&& Char_Y - 10 < data.Y_Center + data.getRawIcon().getIconHeight() / 2) {
								if (data.pickable) {
									Maplestory.player.Character_Get_Item(data, true);
									break;
								}
							}
						}
					}
				}

			};

			if (Maplestory.player.alive && !Shop.isOpen && !Stage.Attacking && !Stage.Ladder && Maplestory.player.pickable) {
				Maplestory.thread_pool.submit(runnable);
			}
		}

	}
	
	// Esc Key Press
	public class EscPress extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (Maplestory.current_stage.getComponent(0) == Maplestory.player.inventory.getScreen()) {
				if (Maplestory.player.inventory.getOpen()) {
					Maplestory.player.inventory.Close();
				}
			}
		}

	}

	// Tab Key Press
	public class TabPress extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (Maplestory.current_stage.getComponent(0) == Maplestory.player.inventory.getScreen()) {
				int type = Maplestory.player.inventory.current_type;
				if(type == 0) {
					Maplestory.player.inventory.Show_Consume();
				}
				else if(type == 1) {
					Maplestory.player.inventory.Show_Etc();
				}
				else if(type == 2) {
					Maplestory.player.inventory.Show_Install();
				}
				else if(type == 3) {
					Maplestory.player.inventory.Show_Cash();
				}
				else if(type == 4) {
					Maplestory.player.inventory.Show_Equip();
				}
			}
		}
		
	}
	
	// Item Use
	public class ItemUse extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private int item_code = -1;
		private String type = "";
		
		public void Set(int _item_code, String _type) {
			item_code = _item_code;
			type = _type;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(Maplestory.player.alive) {
				for(int i=0;i<Maplestory.player.inventory.Consume_size;i++) {
					Item item = Maplestory.player.inventory.Consume_inventory_list.get(i);
					if(item != null) {
						if((item_code == item.getItemCode()) && (type.equals(item.getType()))) {
							item.Use(i);
							break;
						}
					}
				}
			}
		}

	}
}

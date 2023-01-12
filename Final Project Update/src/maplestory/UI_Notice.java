package maplestory;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class UI_Notice extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected static final int OK = 0, CANCEL = 1;
	protected static final int WITH_TEXTFIELD = 1, WITH_OK = 2, WITH_CANCEL = 4, DIE_NOTICE = 8, QUICKSLOT_CHANGE = 9;
	
	protected static boolean isOpen = false;
	
	protected boolean bt_ok_entered = false, bt_ok_pressed = false;
	protected boolean bt_cancel_entered = false, bt_cancel_pressed = false;
	
	protected static int status = 0;
	protected static int value = 0;
	
	protected int type = 0;
	protected String noticeString = "";
	
	protected JTextField textfield;
	protected UI_Quick_Slot tempQuickSlot;
	protected JLabel bt_ok, bt_cancel;
	
	public int mouseX = 0, mouseY = 0;
	
	public UI_Notice() {
		makeButtons();
		setTextField();
		setScreenMouseEvents();
	}
	
	protected void paintComponent(Graphics g) {
		if(type == (WITH_TEXTFIELD | WITH_OK | WITH_CANCEL)) {
			g.drawImage(Maplestory.images.Notice_Top.getImage(), 0, 0, this);
			g.drawImage(Maplestory.images.Notice_Center.getImage(), 0, 20, this);
			g.drawImage(Maplestory.images.Notice_Center_Box.getImage(), 0, 36, this);
			g.drawImage(Maplestory.images.Notice_Box2.getImage(), 0, 52, this);
			g.drawImage(Maplestory.images.Notice_Box.getImage(), 0, 72, this);
			g.drawImage(Maplestory.images.Notice_Box.getImage(), 0, 88, this);
			g.drawImage(Maplestory.images.Notice_Bottom_Box.getImage(), 0, 104, this);
			
			g.setFont(new Font("돋움", Font.PLAIN, 12));
			g.setColor(Color.WHITE);
			g.drawString(noticeString, 15, 30);
		}
		else if(type == WITH_OK) {
			g.drawImage(Maplestory.images.Notice_Top.getImage(), 0, 0, this);
			g.drawImage(Maplestory.images.Notice_Center.getImage(), 0, 20, this);
			g.drawImage(Maplestory.images.Notice_Center.getImage(), 0, 32, this);
			g.drawImage(Maplestory.images.Notice_Center_Box.getImage(), 0, 48, this);
			g.drawImage(Maplestory.images.Notice_Box.getImage(), 0, 64, this);
			g.drawImage(Maplestory.images.Notice_Bottom_Box.getImage(), 0, 80, this);
			
			g.setFont(new Font("돋움", Font.PLAIN, 12));
			g.setColor(Color.WHITE);
			g.drawString(noticeString, 15, 30);
		}
		else if(type == (WITH_OK | WITH_CANCEL)) {
			g.drawImage(Maplestory.images.Notice_Top.getImage(), 0, 0, this);
			g.drawImage(Maplestory.images.Notice_Center.getImage(), 0, 20, this);
			g.drawImage(Maplestory.images.Notice_Center.getImage(), 0, 32, this);
			g.drawImage(Maplestory.images.Notice_Center_Box.getImage(), 0, 48, this);
			g.drawImage(Maplestory.images.Notice_Box.getImage(), 0, 64, this);
			g.drawImage(Maplestory.images.Notice_Bottom_Box.getImage(), 0, 80, this);
			
			g.setFont(new Font("돋움", Font.PLAIN, 12));
			g.setColor(Color.WHITE);
			g.drawString(noticeString, 15, 30);
		}
		else if(type == DIE_NOTICE) {
			g.drawImage(Maplestory.images.Notice_Die_Notice.getImage(), 0, 0, this);
		}
		else if(type == QUICKSLOT_CHANGE) {
			g.drawImage(Maplestory.images.KeySetting_QuickSlotConfig_BackGround.getImage(), 0, 0, this);
		}
		
		paintComponents(g);
	}
	
	public void setTextField() {
		textfield = new JTextField(4);
		textfield.setBounds(22, 54, 217, 16);
		textfield.setBorder(null);
		textfield.setFocusTraversalKeysEnabled(false);
		textfield.setFont(new Font("돋움", Font.PLAIN, 12));
		textfield.addActionListener(new OKHandler());
		textfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					cancelAction();
				}
			}
			
			@Override
			public void keyTyped(KeyEvent e) {
				if(!java.lang.Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
				else if(textfield.getText().length() >= 10) {
					e.consume();
				}
			}
		});
	}
	
	public void makeButtons() {
		bt_ok = new JLabel("");
		bt_ok.setSize(40, 16);
		bt_ok.setIcon(Maplestory.images.Notice_OK);
		bt_ok.setDisabledIcon(Maplestory.images.Notice_OK_Disabled);
		bt_ok.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				bt_ok_entered = true;
				if(!bt_ok_pressed) {
					bt_ok.setIcon(Maplestory.images.Notice_OK_Rollover);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				bt_ok_entered = false;
				if(!bt_ok_pressed) {
					bt_ok.setIcon(Maplestory.images.Notice_OK);
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					bt_ok_pressed = true;
					bt_ok.setIcon(Maplestory.images.Notice_OK_Pressed);
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					bt_ok_pressed = false;
					if(bt_ok_entered) {
						bt_ok.setIcon(Maplestory.images.Notice_OK_Rollover);
						okAction();
					}
					else {
						bt_ok.setIcon(Maplestory.images.Notice_OK);
					}
				}
			}
		});
		
		bt_cancel = new JLabel("");
		bt_cancel.setSize(40, 16);
		bt_cancel.setIcon(Maplestory.images.Notice_Cancel);
		bt_cancel.setDisabledIcon(Maplestory.images.Notice_Cancel_Disabled);
		bt_cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				bt_cancel_entered = true;
				if(!bt_cancel_pressed) {
					bt_cancel.setIcon(Maplestory.images.Notice_Cancel_Rollover);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				bt_cancel_entered = false;
				if(!bt_cancel_pressed) {
					bt_cancel.setIcon(Maplestory.images.Notice_Cancel);
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					bt_cancel_pressed = true;
					bt_cancel.setIcon(Maplestory.images.Notice_Cancel_Pressed);
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					bt_cancel_pressed = false;
					if(bt_cancel_entered) {
						bt_cancel.setIcon(Maplestory.images.Notice_Cancel_Rollover);
						cancelAction();
					}
					else {
						bt_cancel.setIcon(Maplestory.images.Notice_Cancel);
					}
				}
			}
		});
	}
	
	public void okAction() {
		if(type == (WITH_TEXTFIELD | WITH_OK | WITH_CANCEL)) {
			if(!textfield.getText().equals("")) {
				value = Integer.parseInt(textfield.getText());
				status = OK;
				close();
			}
		}
		else {
			status = OK;
			close();
		}
	}
	public void cancelAction() {
		status = CANCEL;
		close();
	}

	public void setScreenMouseEvents() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					mouseX = e.getX();
					mouseY = e.getY();
				}
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					int X = e.getX();
					int Y = e.getY();
					int Screen_X = getX();
					int Screen_Y = getY();
					if(mouseX >= 4 && mouseX <= 255 && mouseY >= 4 && mouseY <= 15) {
						if(Screen_X >= -100 && Screen_X <= 640 && Screen_Y >= 0 && Screen_Y <= 550) {
							int Screen_New_X = X + Screen_X - mouseX;
							int Screen_New_Y = Y + Screen_Y - mouseY;
							if(Screen_New_X >= -100 && Screen_New_X <= 640 && Screen_New_Y >= 0 && Screen_New_Y <= 550) {
								setLocation(Screen_New_X, Screen_New_Y);
							}
						}
					}
				}
			}
		});
	}
	
	public boolean open(int _type, String _noticeString) {
		if(!isOpen) {
			type = _type;
			noticeString = _noticeString;
			textfield.setText("");
			
			if(type == (WITH_TEXTFIELD | WITH_OK | WITH_CANCEL)) {
				add(textfield);
				add(bt_ok);
				add(bt_cancel);
				setBounds(270, 206, 260, 119);
				bt_ok.setLocation(152, 88);
				bt_ok.setIcon(Maplestory.images.Notice_OK);
				bt_cancel.setLocation(195, 88);
				bt_cancel.setIcon(Maplestory.images.Notice_Cancel);
			}
			else if(type == WITH_OK) {
				Music Alert_Music = new Music("DlgNotice.wav", 1);
				Alert_Music.play();
				add(bt_ok);
				setBounds(270, 218, 260, 105);
				bt_ok.setLocation(195, 64);
				bt_ok.setIcon(Maplestory.images.Notice_OK);
			}
			else if(type == (WITH_OK | WITH_CANCEL)) {
				Music Alert_Music = new Music("DlgNotice.wav", 1);
				Alert_Music.play();
				add(bt_ok);
				add(bt_cancel);
				setBounds(270, 218, 260, 105);
				bt_ok.setLocation(152, 64);
				bt_ok.setIcon(Maplestory.images.Notice_OK);
				bt_cancel.setLocation(195, 64);
				bt_cancel.setIcon(Maplestory.images.Notice_Cancel);
			}
			else if(type == DIE_NOTICE) {
				add(bt_ok);
				setBounds(250, 150, 300, 131);
				bt_ok.setLocation(240, 100);
			}
			else if(type == QUICKSLOT_CHANGE) {
				//TODO quickslot
				tempQuickSlot = new UI_Quick_Slot();
				add(tempQuickSlot);
				add(bt_ok);
				add(bt_cancel);
				setBounds(285, 160, 230, 210);
				
				tempQuickSlot.setLocation(40, 90);
				for(int i=0;i<8;i++) {
					tempQuickSlot.quickSlot[i].setQuickSlotKey(Maplestory.ui_keySetting.Slot[Maplestory.ui_quick_slot.quickSlot[i].index]);
				}
				
				bt_ok.setLocation(129, 180);
				bt_ok.setIcon(Maplestory.images.Notice_OK);
				bt_cancel.setLocation(172, 180);
				bt_cancel.setIcon(Maplestory.images.Notice_Cancel);
			}
			
			isOpen = true;
			setVisible(true);
			
			if(type == (WITH_TEXTFIELD | WITH_OK | WITH_CANCEL)) {
				textfield.requestFocus();
			}
			else if(type == QUICKSLOT_CHANGE){
				requestFocus();
			}
			return true;
		}
		else {
			return false;
		}
	}
	public void close() {
		if(isOpen) {
			if(type == (WITH_TEXTFIELD | WITH_OK | WITH_CANCEL)) {
				remove(textfield);
				remove(bt_ok);
				remove(bt_cancel);
			}
			else if(type == WITH_OK) {
				remove(bt_ok);
			}
			else if(type == (WITH_OK | WITH_CANCEL)) {
				remove(bt_ok);
				remove(bt_cancel);
			}
			else if(type == DIE_NOTICE) {
				remove(bt_ok);
			}
			isOpen = false;
			setVisible(false);
			Maplestory.current_stage.grabFocus();
		}
	}
	
	public class OKHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(type == (WITH_TEXTFIELD | WITH_OK | WITH_CANCEL)) {
				if(!textfield.getText().equals("")) {
					value = Integer.parseInt(textfield.getText());
					status = OK;
					close();
				}
			}
			else {
				status = OK;
				close();
			}
		}
		
	}
}

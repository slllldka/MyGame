package maplestory;

import java.awt.AWTException;
import java.awt.DisplayMode;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class KeyBoardConfig {
	private JFrame frame;
	private JPanel panel;
	private DisplayMode displaymode;
	private boolean stable = true;
	
	private boolean Ladder_Checking = false;
	private boolean Ladder_Jump_Checking = false;
	
	protected static Robot robot;
	
	protected String[] actionMapKeyArray = new String[223];
	
	protected FullScreen fullScreen = new FullScreen();
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
	private NullAction nullAction = new NullAction();
	private CtrlPress ctrlPress = new CtrlPress();
	private ShiftPress shiftPress = new ShiftPress();
	private InventoryAction inventoryAction = new InventoryAction();
	private MinimapAction minimapAction = new MinimapAction();
	private PickUpAction pickupAction = new PickUpAction();
	private EscPress escPress = new EscPress();
	private TabPress tabPress = new TabPress();
	private EnterPress enterPress = new EnterPress();
	private NPCAction npcAction = new NPCAction();
	private KeyConfigAction keyconfigAction = new KeyConfigAction();
	protected ItemUse[] itemUse = new ItemUse[223];

	public KeyBoardConfig(JFrame _frame) {
		frame = _frame;
		
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<223;i++) {
			itemUse[i] = new ItemUse();
		}

		setDefault();
	}
	
	public void setDefault() {
		actionMapKeyArray[KeyEvent.VK_ESCAPE] = "EscPress";
		actionMapKeyArray[KeyEvent.VK_F1] = "";
		actionMapKeyArray[KeyEvent.VK_F2] = "";
		actionMapKeyArray[KeyEvent.VK_F3] = "";
		actionMapKeyArray[KeyEvent.VK_F4] = "";
		actionMapKeyArray[KeyEvent.VK_F5] = "";
		actionMapKeyArray[KeyEvent.VK_F6] = "";
		actionMapKeyArray[KeyEvent.VK_F7] = "";
		actionMapKeyArray[KeyEvent.VK_F8] = "";
		actionMapKeyArray[KeyEvent.VK_F9] = "";
		actionMapKeyArray[KeyEvent.VK_F10] = "";
		actionMapKeyArray[KeyEvent.VK_F11] = "";
		actionMapKeyArray[KeyEvent.VK_F12] = "";
		
		actionMapKeyArray[KeyEvent.VK_INSERT] = "";
		actionMapKeyArray[KeyEvent.VK_DELETE] = "";
		actionMapKeyArray[KeyEvent.VK_HOME] = "";
		actionMapKeyArray[KeyEvent.VK_END] = "";
		actionMapKeyArray[KeyEvent.VK_PAGE_UP] = "";
		actionMapKeyArray[KeyEvent.VK_PAGE_DOWN] = "";

		actionMapKeyArray[KeyEvent.VK_BACK_QUOTE] = "";
		actionMapKeyArray[KeyEvent.VK_1] = "";
		actionMapKeyArray[KeyEvent.VK_2] = "";
		actionMapKeyArray[KeyEvent.VK_3] = "";
		actionMapKeyArray[KeyEvent.VK_4] = "";
		actionMapKeyArray[KeyEvent.VK_5] = "";
		actionMapKeyArray[KeyEvent.VK_6] = "";
		actionMapKeyArray[KeyEvent.VK_7] = "";
		actionMapKeyArray[KeyEvent.VK_8] = "";
		actionMapKeyArray[KeyEvent.VK_9] = "";
		actionMapKeyArray[KeyEvent.VK_0] = "";
		actionMapKeyArray[KeyEvent.VK_MINUS] = "";
		actionMapKeyArray[KeyEvent.VK_EQUALS] = "";
		actionMapKeyArray[KeyEvent.VK_BACK_SPACE] = "";

		actionMapKeyArray[KeyEvent.VK_TAB] = "TabPress";
		actionMapKeyArray[KeyEvent.VK_Q] = "";
		actionMapKeyArray[KeyEvent.VK_W] = "";
		actionMapKeyArray[KeyEvent.VK_E] = "";
		actionMapKeyArray[KeyEvent.VK_R] = "";
		actionMapKeyArray[KeyEvent.VK_T] = "";
		actionMapKeyArray[KeyEvent.VK_Y] = "";
		actionMapKeyArray[KeyEvent.VK_U] = "";
		actionMapKeyArray[KeyEvent.VK_I] = "Inventory";
		actionMapKeyArray[KeyEvent.VK_O] = "";
		actionMapKeyArray[KeyEvent.VK_P] = "";
		actionMapKeyArray[KeyEvent.VK_OPEN_BRACKET] = "";
		actionMapKeyArray[KeyEvent.VK_CLOSE_BRACKET] = "";
		actionMapKeyArray[KeyEvent.VK_BACK_SLASH] = "KeyConfigAction";
		
		actionMapKeyArray[KeyEvent.VK_A] = "";
		actionMapKeyArray[KeyEvent.VK_S] = "";
		actionMapKeyArray[KeyEvent.VK_D] = "";
		actionMapKeyArray[KeyEvent.VK_F] = "";
		actionMapKeyArray[KeyEvent.VK_G] = "";
		actionMapKeyArray[KeyEvent.VK_H] = "";
		actionMapKeyArray[KeyEvent.VK_J] = "";
		actionMapKeyArray[KeyEvent.VK_K] = "";
		actionMapKeyArray[KeyEvent.VK_L] = "";
		actionMapKeyArray[KeyEvent.VK_SEMICOLON] = "";
		actionMapKeyArray[KeyEvent.VK_QUOTE] = "";
		actionMapKeyArray[KeyEvent.VK_ENTER] = "EnterPress";

		actionMapKeyArray[KeyEvent.VK_SHIFT] = "ShiftPress";
		actionMapKeyArray[KeyEvent.VK_Z] = "PickUp";
		actionMapKeyArray[KeyEvent.VK_X] = "";
		actionMapKeyArray[KeyEvent.VK_C] = "";
		actionMapKeyArray[KeyEvent.VK_V] = "";
		actionMapKeyArray[KeyEvent.VK_B] = "";
		actionMapKeyArray[KeyEvent.VK_N] = "";
		actionMapKeyArray[KeyEvent.VK_M] = "Minimap";
		actionMapKeyArray[KeyEvent.VK_COMMA] = "";
		actionMapKeyArray[KeyEvent.VK_PERIOD] = "";
		actionMapKeyArray[KeyEvent.VK_SLASH] = "";

		actionMapKeyArray[KeyEvent.VK_CONTROL] = "CtrlPress";
		actionMapKeyArray[KeyEvent.VK_SPACE] = "NPC";
	}

	public void setSpecificKey(int keyCode, String str_press) {
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
	
	public void setSpecificKey(int keyCode, String str_press, String str_release) {
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
	
	public void setKeyBoard() {
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
		Maplestory.current_stage.getActionMap().put("", nullAction);
		Maplestory.current_stage.getActionMap().put("CtrlPress", ctrlPress);
		Maplestory.current_stage.getActionMap().put("ShiftPress", shiftPress);
		Maplestory.current_stage.getActionMap().put("Inventory", inventoryAction);
		Maplestory.current_stage.getActionMap().put("Minimap", minimapAction);
		Maplestory.current_stage.getActionMap().put("PickUp", pickupAction);
		Maplestory.current_stage.getActionMap().put("EscPress", escPress);
		Maplestory.current_stage.getActionMap().put("TabPress", tabPress);
		Maplestory.current_stage.getActionMap().put("EnterPress", enterPress);
		Maplestory.current_stage.getActionMap().put("NPC", npcAction);
		Maplestory.current_stage.getActionMap().put("KeyConfigAction", keyconfigAction);
		for(int i=0;i<223;i++) {
			Maplestory.current_stage.getActionMap().put("ItemUse" + i, itemUse[i]);
		}
		
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, InputEvent.ALT_DOWN_MASK, false), "AltSpacePress");
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, InputEvent.ALT_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK, false), "AltSpacePress");
		setSpecificKey(KeyEvent.VK_LEFT, "LeftPress", "LeftRelease");
		setSpecificKey(KeyEvent.VK_RIGHT, "RightPress", "RightRelease");
		setSpecificKey(KeyEvent.VK_UP, "UpPress", "UpRelease");
		setSpecificKey(KeyEvent.VK_DOWN, "DownPress", "DownRelease");
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ALT, InputEvent.ALT_DOWN_MASK, false), "Jump");
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ALT, 0, true), "AltRelease");

		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false), actionMapKeyArray[KeyEvent.VK_ESCAPE]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0, false), actionMapKeyArray[KeyEvent.VK_F1]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0, false), actionMapKeyArray[KeyEvent.VK_F2]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0, false), actionMapKeyArray[KeyEvent.VK_F3]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0, false), actionMapKeyArray[KeyEvent.VK_F4]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0, false), actionMapKeyArray[KeyEvent.VK_F5]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0, false), actionMapKeyArray[KeyEvent.VK_F6]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0, false), actionMapKeyArray[KeyEvent.VK_F7]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0, false), actionMapKeyArray[KeyEvent.VK_F8]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F9, 0, false), actionMapKeyArray[KeyEvent.VK_F9]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F10, 0, false), actionMapKeyArray[KeyEvent.VK_F10]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0, false), actionMapKeyArray[KeyEvent.VK_F11]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0, false), actionMapKeyArray[KeyEvent.VK_F12]);
		
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0, false), actionMapKeyArray[KeyEvent.VK_INSERT]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false), actionMapKeyArray[KeyEvent.VK_DELETE]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_HOME, 0, false), actionMapKeyArray[KeyEvent.VK_HOME]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_END, 0, false), actionMapKeyArray[KeyEvent.VK_END]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_UP, 0, false), actionMapKeyArray[KeyEvent.VK_PAGE_UP]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN, 0, false), actionMapKeyArray[KeyEvent.VK_PAGE_DOWN]);
		
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_QUOTE, 0, false), actionMapKeyArray[KeyEvent.VK_BACK_QUOTE]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0, false), actionMapKeyArray[KeyEvent.VK_1]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0, false), actionMapKeyArray[KeyEvent.VK_2]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_3, 0, false), actionMapKeyArray[KeyEvent.VK_3]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_4, 0, false), actionMapKeyArray[KeyEvent.VK_4]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_5, 0, false), actionMapKeyArray[KeyEvent.VK_5]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_6, 0, false), actionMapKeyArray[KeyEvent.VK_6]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_7, 0, false), actionMapKeyArray[KeyEvent.VK_7]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_8, 0, false), actionMapKeyArray[KeyEvent.VK_8]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_9, 0, false), actionMapKeyArray[KeyEvent.VK_9]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_0, 0, false), actionMapKeyArray[KeyEvent.VK_0]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, 0, false), actionMapKeyArray[KeyEvent.VK_MINUS]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, 0, false), actionMapKeyArray[KeyEvent.VK_EQUALS]);
		
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0, false), actionMapKeyArray[KeyEvent.VK_TAB]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0, false), actionMapKeyArray[KeyEvent.VK_Q]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), actionMapKeyArray[KeyEvent.VK_W]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0, false), actionMapKeyArray[KeyEvent.VK_E]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0, false), actionMapKeyArray[KeyEvent.VK_R]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_T, 0, false), actionMapKeyArray[KeyEvent.VK_T]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, 0, false), actionMapKeyArray[KeyEvent.VK_Y]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_U, 0, false), actionMapKeyArray[KeyEvent.VK_U]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_I, 0, false), actionMapKeyArray[KeyEvent.VK_I]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_O, 0, false), actionMapKeyArray[KeyEvent.VK_O]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0, false), actionMapKeyArray[KeyEvent.VK_P]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_OPEN_BRACKET, 0, false), actionMapKeyArray[KeyEvent.VK_OPEN_BRACKET]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_CLOSE_BRACKET, 0, false), actionMapKeyArray[KeyEvent.VK_CLOSE_BRACKET]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SLASH, 0, false), actionMapKeyArray[KeyEvent.VK_BACK_SLASH]);
		
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_CAPS_LOCK, 0, false), actionMapKeyArray[KeyEvent.VK_CAPS_LOCK]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), actionMapKeyArray[KeyEvent.VK_A]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), actionMapKeyArray[KeyEvent.VK_S]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), actionMapKeyArray[KeyEvent.VK_D]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F, 0, false), actionMapKeyArray[KeyEvent.VK_F]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_G, 0, false), actionMapKeyArray[KeyEvent.VK_G]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_H, 0, false), actionMapKeyArray[KeyEvent.VK_H]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_J, 0, false), actionMapKeyArray[KeyEvent.VK_J]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_K, 0, false), actionMapKeyArray[KeyEvent.VK_K]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_L, 0, false), actionMapKeyArray[KeyEvent.VK_L]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SEMICOLON, 0, false), actionMapKeyArray[KeyEvent.VK_SEMICOLON]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_QUOTE, 0, false), actionMapKeyArray[KeyEvent.VK_QUOTE]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), actionMapKeyArray[KeyEvent.VK_ENTER]);

		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SHIFT, InputEvent.ALT_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK, false), actionMapKeyArray[KeyEvent.VK_SHIFT]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SHIFT, InputEvent.SHIFT_DOWN_MASK, false), actionMapKeyArray[KeyEvent.VK_SHIFT]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, 0, false), actionMapKeyArray[KeyEvent.VK_Z]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_X, 0, false), actionMapKeyArray[KeyEvent.VK_X]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_C, 0, false), actionMapKeyArray[KeyEvent.VK_C]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_V, 0, false), actionMapKeyArray[KeyEvent.VK_V]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_B, 0, false), actionMapKeyArray[KeyEvent.VK_B]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_N, 0, false), actionMapKeyArray[KeyEvent.VK_N]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_M, 0, false), actionMapKeyArray[KeyEvent.VK_M]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_COMMA, 0, false), actionMapKeyArray[KeyEvent.VK_COMMA]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_PERIOD, 0, false), actionMapKeyArray[KeyEvent.VK_PERIOD]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SLASH, 0, false), actionMapKeyArray[KeyEvent.VK_SLASH]);

		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_CONTROL, InputEvent.ALT_DOWN_MASK | InputEvent.CTRL_DOWN_MASK, false), actionMapKeyArray[KeyEvent.VK_CONTROL]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_CONTROL, InputEvent.CTRL_DOWN_MASK, false), actionMapKeyArray[KeyEvent.VK_CONTROL]);
		Maplestory.current_stage.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), actionMapKeyArray[KeyEvent.VK_SPACE]);
		
		
	}
	
	public void setKeyBoard(JPanel _panel) {
		panel = _panel;
		displaymode = new DisplayMode(panel.getWidth(), panel.getHeight(), 32, 60);
		panel.getActionMap().put("FullScreen", fullScreen);
		panel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.ALT_DOWN_MASK, false), "FullScreen");
	}

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
	
	public class Jump extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			Map.AltKey = true;
			robot.keyPress(KeyEvent.VK_PAUSE);
			
			if(!Ladder_Jump_Checking) {
				Ladder_Jump_Checking = true;
				Runnable runnable = new Runnable() {

					@Override
					public void run() {
						while(true) {
							if(Maplestory.player.alive && Map.Ladder && !Shop.isOpen && !UI_Notice.isOpen) {
								if(Map.AltKey && !Map.DownKey && !Map.Jump && !Map.Attacking && !Map.attacked) {
									if(Map.LeftKey) {
										Maplestory.player.current_Img = Maplestory.player.characterJumpLeftImg;
										Maplestory.player.Character_LeftJump(true);
										try {
											Thread.sleep(Character.Ladder_Jump_Delay);
										} catch (InterruptedException e) {
											e.printStackTrace();
										}
									}
									else if(Map.RightKey) {
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
			
			if(Maplestory.player.alive && !Shop.isOpen && !UI_Notice.isOpen) {
				if (Maplestory.player.IsLandable() && !Map.Attacking && !Map.attacked) {
					if (Map.DownKey) {
						if (!Maplestory.player.cur_foothold.isBottom) {
							if (Maplestory.player.CharDirection == -1) {
								Maplestory.player.current_Img = Maplestory.player.characterJumpLeftImg;
							} else if (Maplestory.player.CharDirection == 1) {
								Maplestory.player.current_Img = Maplestory.player.characterJumpRightImg;
							}
							Maplestory.player.Character_DownJump();
						}
					}
					else if (Map.LeftKey) {
						Maplestory.player.current_Img = Maplestory.player.characterJumpLeftImg;
						Maplestory.player.Character_LeftJump(false);
					}
					else if (Map.RightKey) {
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

	public class LeftPress extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			Map.LeftKey = true;
			if(Maplestory.player.alive && !Shop.isOpen && !UI_Notice.isOpen) {
				if (Map.Attacking == false) {
					Maplestory.player.CharDirection = -1;
					if (Map.Jump == true) {
						Maplestory.player.current_Img = Maplestory.player.characterJumpLeftImg;
					} else {
						if (!Map.Left && !Map.Ladder) {
							Map.Right = false;
							Maplestory.player.current_Img = Maplestory.player.characterLeftImg;
							Maplestory.player.Character_Left();
						}
					}
				}
			}
		}
	}

	public class LeftRelease extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			Map.LeftKey = false;
			/*if (Stage.Attacking == false) {
				if (Stage.RightKey == true && Stage.Right == false && Stage.Jump == false) {
					Maplestory.player.current_Img = Maplestory.player.characterRightImg;
					Maplestory.player.Character_Right();
				}
			}*/
		}

	}

	public class RightPress extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			Map.RightKey = true;
			if(Maplestory.player.alive && !Shop.isOpen && !UI_Notice.isOpen) {
				if (Map.Attacking == false) {
					Maplestory.player.CharDirection = 1;
					if (Map.Jump == true) {
						Maplestory.player.current_Img = Maplestory.player.characterJumpRightImg;
					} else {
						if (!Map.Right && !Map.Ladder) {
							Map.Left = false;
							Maplestory.player.current_Img = Maplestory.player.characterRightImg;
							Maplestory.player.Character_Right();
						}
					}
				}
			}
		}
	}

	public class RightRelease extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			Map.RightKey = false;
			/*if (Stage.Attacking == false) {
				if (Stage.LeftKey == true && Stage.Left == false && Stage.Jump == false) {
					Maplestory.player.current_Img = Maplestory.player.characterLeftImg;
					Maplestory.player.Character_Left();
				}
			}*/
		}
	}

	public class UpPress extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			Map.UpKey = true;
			if (!Ladder_Checking) {
				Ladder_Checking = true;
				Runnable runnable = new Runnable() {

					@Override
					public void run() {
						int LadderIdx;
						while(true) {
							if(Maplestory.player.alive && !Shop.isOpen && !UI_Notice.isOpen
									&& Map.UpKey && !Map.Up && !Map.Down && !Map.Attacking && !Map.attacked && !Map.Ladder_Jump) {
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
			if(Maplestory.player.alive && !Shop.isOpen && !UI_Notice.isOpen) {
				if (!Map.Attacking) {
					for(Portal portal : Maplestory.current_stage.Portal_List) {
						if ((Maplestory.player.CharacterX + 25 >= portal.xstart)
								&& (Maplestory.player.CharacterX + 25 <= portal.xend)
								&& (portal.y >= Maplestory.player.CharacterY - Character.CharacterHeight + 20)
								&& (portal.y <= Maplestory.player.CharacterY - Character.CharacterHeight + 120)) {
							Runnable runnable = new Runnable() {
		
								@Override
								public void run() {
									Portal.isAvailable = false;
									
									Music Portal_Music = new Music("Portal.wav", 1);
									Portal_Music.play();
									
									Map.Left = false;
									Map.Right = false;
									Map.Up = false;
									Map.Down = false;
									Map.Ladder = false;
		
									if (Maplestory.StageNow == 1) {
										Maplestory.map1.close(portal);
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
										Maplestory.map1_1.close(portal);
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
										Maplestory.map2.close(portal);
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
										Maplestory.map3.close(portal);
									} else if (Maplestory.StageNow == 4) {
										Maplestory.map4.close(portal);
									}
									
									try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									Portal.isAvailable = true;
								}
		
							};
							if(Portal.isAvailable) {
								Maplestory.thread_pool.submit(runnable);
							}
						}
					}
				}
			}
		}
	}

	public class UpRelease extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			Map.UpKey = false;
		}

	}

	public class DownPress extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			Map.DownKey = true;
			if(Maplestory.player.alive && !Shop.isOpen && !UI_Notice.isOpen) {
				if (!Map.Up && !Map.Down && !Map.Attacking && !Map.Jump) {
					int LadderIdx;
					if ((LadderIdx = Maplestory.player.IsLadderAvailable(-1)) >= 0) {
						Maplestory.player.Character_LadderDown(LadderIdx);
					} else if (Map.LeftKey == false && Map.RightKey == false && Map.Jump == false
							&& Map.Ladder == false) {
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

	public class DownRelease extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			Map.DownKey = false;
			if(Maplestory.player.alive && !Shop.isOpen && !UI_Notice.isOpen) {
				if (Map.Ladder == false && Map.Attacking == false) {
					if (Map.LeftKey == false && Map.RightKey == false && Map.Jump == false) {
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
	
	public class AltRelease extends AbstractAction{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			Map.AltKey = false;
		}
		
	}

	public class AltSpacePress extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			robot.keyPress(KeyEvent.VK_ALT);
			Maplestory.player.LevelUp();
			//aplestory.current_stage.reopen();
		}
		
	}
	
	public class NullAction extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}	

	}

	public class CtrlPress extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			if(Maplestory.player.alive && !Shop.isOpen && !UI_Notice.isOpen) {
				if (!Map.Attacking && !Map.Ladder) {
					Maplestory.player.Character_Attack(1, 70, Character.CharacterWidth / 2, Character.CharacterHeight / 2,
							Character.CharacterHeight / 2, 1, 100);
				}
			}

		}

	}

	public class ShiftPress extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			if(Maplestory.player.alive && !Shop.isOpen && !UI_Notice.isOpen) {
				if (!Map.Attacking && !Map.Ladder) {
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

	public class InventoryAction extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!UI_Notice.isOpen) {
				if (!Maplestory.player.inventory.getOpen()) {
					Maplestory.player.inventory.open();
				} else {
					Maplestory.player.inventory.close();
				}
			}
		}

	}

	public class MinimapAction extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			if(Maplestory.current_stage.MiniMap_Available && !UI_Notice.isOpen) {
				if (!Maplestory.ui_minimap.isOpen) {
					Maplestory.ui_minimap.open();
				} else {
					Maplestory.ui_minimap.close();
				}
			}
		}
		
	}
	
	public class PickUpAction extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			Runnable runnable = new Runnable() {

				@Override
				public void run() {
					Maplestory.player.Character_PickUp_Item();
				}

			};

			if (Maplestory.player.alive && !Shop.isOpen && !UI_Notice.isOpen
					&& !Map.Attacking && !Map.Ladder && Maplestory.player.pickable) {
				Maplestory.thread_pool.submit(runnable);
			}
		}

	}
	
	public class EscPress extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			if(UI_Notice.isOpen) {
				Maplestory.ui_notice.cancelAction();
			}
			else if (Maplestory.current_stage.getComponent(1) == Maplestory.player.inventory) {
				if (Maplestory.player.inventory.getOpen()) {
					Maplestory.player.inventory.close();
				}
			}
			else if (Maplestory.current_stage.getComponent(1) == Maplestory.generalStore) {
				if (Shop.isOpen) {
					Maplestory.generalStore.close();
				}
			}
			else if (Maplestory.current_stage.getComponent(1) == Maplestory.ui_keySetting) {
				if(Maplestory.ui_keySetting.isOpen) {
					Maplestory.ui_keySetting.close(true);
				}
			}
		}

	}

	public class TabPress extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (Maplestory.current_stage.getComponent(1) == Maplestory.player.inventory.getScreen()) {
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
	
	public class EnterPress extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			if(UI_Notice.isOpen) {
				if(Maplestory.ui_notice.type == UI_Notice.WITH_OK
						|| Maplestory.ui_notice.type == (UI_Notice.WITH_OK | UI_Notice.WITH_CANCEL)) {
					Maplestory.ui_notice.okAction();
				}
			}
		}

	}
	
	public class NPCAction extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!UI_Notice.isOpen) {
				Maplestory.player.Character_TalktoNPC();
			}
		}
		
	}

	public class ItemUse extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private int item_code = -1;
		private String type = "";
		
		public void setValues(int _item_code, String _type) {
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
	
	public class KeyConfigAction extends AbstractAction {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			if(!UI_Notice.isOpen) {
				if (!Maplestory.ui_keySetting.isOpen) {
					Maplestory.ui_keySetting.open();
				} else {
					Maplestory.ui_keySetting.close(true);
				}
			}
		}
		
	}
	
}

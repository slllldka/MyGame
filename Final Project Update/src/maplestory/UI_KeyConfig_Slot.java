package maplestory;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class UI_KeyConfig_Slot extends UI_Quick_Slot_Icon {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected boolean isQuickSlot = false;
	
	protected int keyCode;
	protected String actionMapKey, defaultKey, tempKey;
	protected ImageIcon actionImage, defaultImage, tempImage;
	protected ImageIcon keyImage, quickslotKeyImage;
	
	public UI_KeyConfig_Slot(int _keyCode, String _actionMapKey) {
		super(-1);
		keyCode = _keyCode;
		actionMapKey = _actionMapKey;
		defaultKey = _actionMapKey;
		actionImage = null;
		defaultImage = null;
		keyImage = null;
		quickslotKeyImage = null;
	}

	public UI_KeyConfig_Slot(int _keyCode, String _actionMapKey, ImageIcon _actionImage) {
		super(-1);
		keyCode = _keyCode;
		actionMapKey = _actionMapKey;
		defaultKey = _actionMapKey;
		actionImage = _actionImage;
		defaultImage = _actionImage;
		keyImage = null;
		quickslotKeyImage = null;
	}
	
	public UI_KeyConfig_Slot(int _quickslotindex) {
		super(_quickslotindex);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if(Maplestory.ui_keySetting.move_index != getIndex()) {
			if(isQuickSlot) {
				actionImage = Maplestory.ui_keySetting.Slot[index].actionImage;
				item = Maplestory.ui_keySetting.Slot[index].getItem();
				
				if(getParent() == Maplestory.ui_notice.tempQuickSlot) {
					if(Maplestory.ui_notice.tempQuickSlot.selectedIndex == quickslotindex) {
						g.drawImage(Maplestory.images.KeySetting_QuickSlotConfig_KeyFocused.getImage(), 0, 0, this);
					}
				}
				
				if(actionImage != null) {
					g.drawImage(actionImage.getImage(), 0, 0, this);
				}
				else if(getItem() != null) {
					g.drawImage(getItem().getRawIcon().getImage(), 0, 0, this);
					showNumber(g);
				}
			}
			else {
				if(actionImage != null) {
				g.drawImage(actionImage.getImage(), 0, 0, this);
				drawKey(g);
				}
				else if(getItem() != null) {
					g.drawImage(getItem().getRawIcon().getImage(), 0, 0, this);
					showNumber(g);
					drawKey(g);
				}
			}
		}
		
		if(isQuickSlot) {
			drawKey(g);
		}
	}
	
	public void drawKey(Graphics g) {
		if(keyCode != -1) {
			if(keyCode == KeyEvent.VK_SPACE) {
				if(isQuickSlot) {
					g.drawImage(quickslotKeyImage.getImage(), 0, 2, this);
				}
				else {
					g.drawImage(keyImage.getImage(), 0, 4, this);
				}
			}
			else {
				if(isQuickSlot) {
					g.drawImage(quickslotKeyImage.getImage(), 2, 2, this);
				}
				else {
					g.drawImage(keyImage.getImage(), 4, 4, this);
				}
			}
		}
	}
	
	public void setQuickSlotKey(UI_KeyConfig_Slot ref) {
		isQuickSlot = true;
		
		if(index != -1) {
			Maplestory.ui_keySetting.Slot[index].quickslotindex = -1;
		}
		
		index = ref.index;
		keyCode = ref.keyCode;
		keyImage = ref.keyImage;
		quickslotKeyImage = ref.quickslotKeyImage;
		
		ref.quickslotindex = quickslotindex;
	}
	
	public void setKeyImage() {
		if(keyCode != -1) {
			keyImage = new ImageIcon(getClass().getClassLoader().getResource("KeyConfig.key." + index + ".png"));
			quickslotKeyImage = new ImageIcon(getClass().getClassLoader().getResource("KeyConfig.quickslotConfig.key." + index + ".png"));
		}
	}

}

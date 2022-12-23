package maplestory;

import javax.swing.ImageIcon;

public class Item_Orange_Omok_Piece extends Item{
	protected static int item_code = 11;
	protected static String type = "Etc";
	protected static String name = "버섯 오목알";
	protected static String tooltip = "오목을 두는데 필요한 버섯 모양의 오목알이다.";
	protected static ImageIcon Raw_Icon = Maplestory.images.Orange_Omok_Piece_Raw_Icon;
	protected static ImageIcon Icon = Maplestory.images.Orange_Omok_Piece_Icon;
	protected static ImageIcon Info = Maplestory.images.Orange_Omok_Piece_Info;
	protected static int sellPrice = 1;
	
	public Item_Orange_Omok_Piece() {
		Quantity = 1;
		infiniteQuantity = true;
		angle = 0;
		pickable = false;
	}
	
	public Item_Orange_Omok_Piece(int _Quantity) {
		Quantity = _Quantity;
		angle = 0;
		pickable = false;
	}

	@Override
	public int getItemCode() {
		return item_code;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getTooltip() {
		return tooltip;
	}
	
	@Override
	public String getType() {
		return type;
	}

	@Override
	public ImageIcon getRawIcon() {
		return Raw_Icon;
	}

	@Override
	public ImageIcon getIcon() {
		return Icon;
	}
	
	@Override
	public ImageIcon getInfo() {
		return Info;
	}

	@Override
	public int getSellPrice() {
		// TODO Auto-generated method stub
		return sellPrice;
	}
	
	@Override
	public Item getNew(int Quantity) {
		Item result = new Item_Orange_Omok_Piece(Quantity);
		return result;
	}
}
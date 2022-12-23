package maplestory;

import javax.swing.ImageIcon;

public class Item_Blue_Shell extends Item{
	protected static int item_code = 1;
	protected static String type = "Etc";
	protected static String name = "파란 달팽이의 껍질";
	protected static String tooltip = "파란 달팽이의 껍질을 벗긴 것이다.";
	protected static ImageIcon Raw_Icon = Maplestory.images.Blue_Shell_Raw_Icon;
	protected static ImageIcon Icon = Maplestory.images.Blue_Shell_Icon;
	protected static ImageIcon Info = Maplestory.images.Blue_Shell_Info;
	protected static int sellPrice = 1;	

	public Item_Blue_Shell() {
		Quantity = 1;
		infiniteQuantity = true;
		angle = 0;
		pickable = false;
	}
	
	public Item_Blue_Shell(int _Quantity) {
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
		Item result = new Item_Blue_Shell(Quantity);
		return result;
	}
}
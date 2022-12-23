package maplestory;

import javax.swing.ImageIcon;

public class Item_Blue_God extends Item{
	protected static int item_code = 12;
	protected static String type = "Etc";
	protected static String name = "파란버섯의 갓";
	protected static String tooltip = "버섯의 갓을 자른 것이다.";
	protected static ImageIcon Raw_Icon = Maplestory.images.Blue_God_Raw_Icon;
	protected static ImageIcon Icon = Maplestory.images.Blue_God_Icon;
	protected static ImageIcon Info = Maplestory.images.Blue_God_Info;
	protected static int sellPrice = 1;	
	
	public Item_Blue_God() {
		Quantity = 1;
		infiniteQuantity = true;
		angle = 0;
		pickable = false;
	}
	
	public Item_Blue_God(int _Quantity) {
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
		Item result = new Item_Blue_God(Quantity);
		return result;
	}
}
package maplestory;

import javax.swing.ImageIcon;

public class Item_Slime_Bubble extends Item{
	protected static int item_code = 21;
	protected static String type = "Etc";
	protected static String name = "슬라임의 방울";
	protected static String tooltip = "슬라임의 방울을 떼어온 것이다.";
	protected static ImageIcon Raw_Icon = Maplestory.images.Slime_Bubble_Raw_Icon;
	protected static ImageIcon Icon = Maplestory.images.Slime_Bubble_Icon;
	protected static ImageIcon Info = Maplestory.images.Slime_Bubble_Info;
	protected static int sellPrice = 1;
	
	public Item_Slime_Bubble() {
		Quantity = 1;
		infiniteQuantity = true;
		angle = 0;
		pickable = false;
	}
	
	public Item_Slime_Bubble(int _Quantity) {
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
		Item result = new Item_Slime_Bubble(Quantity);
		return result;
	}
}
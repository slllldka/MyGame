package maplestory;

import javax.swing.ImageIcon;

public class Item_Squishy_Liquid extends Item{
	protected static int item_code = 20;
	protected static String type = "Etc";
	protected static String name = "물컹물컹한 액체";
	protected static String tooltip = "점성이 높아 끈적끈적한 액체이다.";
	protected static ImageIcon Raw_Icon = Maplestory.images.Squishy_Liquid_Raw_Icon;
	protected static ImageIcon Icon = Maplestory.images.Squishy_Liquid_Icon;
	protected static ImageIcon Info = Maplestory.images.Squishy_Liquid_Info;
	protected static int sellPrice = 1;
	
	public Item_Squishy_Liquid() {
		Quantity = 1;
		infiniteQuantity = true;
		angle = 0;
		pickable = false;
	}
	
	public Item_Squishy_Liquid(int _Quantity) {
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
		Item result = new Item_Squishy_Liquid(Quantity);
		return result;
	}
}
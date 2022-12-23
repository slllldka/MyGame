package maplestory;

import javax.swing.ImageIcon;

public class Item_MushMom_spore extends Item{
	protected static int item_code = 13;
	protected static String type = "Etc";
	protected static String name = "머쉬맘의 포자";
	protected static String tooltip = "머쉬맘의 포자이다.";
	protected static ImageIcon Raw_Icon = Maplestory.images.MushMom_Spore_Raw_Icon;
	protected static ImageIcon Icon = Maplestory.images.MushMom_Spore_Icon;
	protected static ImageIcon Info = Maplestory.images.MushMom_Spore_Info;
	protected static int sellPrice = 1;
	
	public Item_MushMom_spore() {
		Quantity = 1;
		infiniteQuantity = true;
		angle = 0;
		pickable = false;
	}
	
	public Item_MushMom_spore(int _Quantity) {
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
		Item result = new Item_MushMom_spore(Quantity);
		return result;
	}
}
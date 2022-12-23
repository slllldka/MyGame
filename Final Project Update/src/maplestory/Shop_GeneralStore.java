package maplestory;

public class Shop_GeneralStore extends Shop {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Shop_GeneralStore() {
		setSellList();
		makeSlots();
	}
	
	@Override
	public void setSellList() {
		sell_list.add(new ItemForSale(new Item_Red_Potion(), 5));
		sell_list.add(new ItemForSale(new Item_Orange_Potion(), 48));
		sell_list.add(new ItemForSale(new Item_White_Potion(), 96));
		sell_list.add(new ItemForSale(new Item_Blue_Potion(), 20));
	}

}
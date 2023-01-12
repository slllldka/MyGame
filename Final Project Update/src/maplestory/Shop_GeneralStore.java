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
		sell_list.add(new ItemForSale(new Item_Mana_Elixir(), 186));
		sell_list.add(new ItemForSale(new Item_Apple(), 3));
		sell_list.add(new ItemForSale(new Item_Egg(), 5));
		sell_list.add(new ItemForSale(new Item_Meat(), 10));
		sell_list.add(new ItemForSale(new Item_Orange(), 10));
		sell_list.add(new ItemForSale(new Item_Lemon(), 93));
		sell_list.add(new ItemForSale(new Item_Chocolate(), 2100));
		sell_list.add(new ItemForSale(new Item_ReturnScroll_Town(), 400));
		sell_list.add(new ItemForSale(new Item_ReturnScroll_Henesys(), 500));
	}

}
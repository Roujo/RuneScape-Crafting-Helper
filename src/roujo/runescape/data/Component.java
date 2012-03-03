package roujo.runescape.data;

public class Component {
	private final ItemName itemName;
	private final long quantity;
	
	protected Component(ItemName itemName, long quantity) {
		this.itemName = itemName;
		this.quantity = quantity;
	}

	public ItemName getItemName() {
		return itemName;
	}

	public long getQuantity() {
		return quantity;
	}
}

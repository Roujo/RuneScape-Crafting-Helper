package roujo.runescape.data;

import java.util.HashMap;
import java.util.Map;

public class Craftable extends Item {
	private Map<Item, Long> materials;

	protected Craftable() {
		this.materials = new HashMap<Item, Long>();
		this.setPrice("Crafting", 0);
	}

	protected void addMaterial(ItemName itemName, long quantity) {
		Item item = Fetcher.getItem(itemName);
		materials.put(item, quantity);
		this.setPrice("Crafting",
				this.getPrice("Crafting") + item.getPrice("Market") * quantity);
	}

	protected void removeMaterial(ItemName itemName) {
		Item item = Fetcher.getItem(itemName);
		long quantity = materials.remove(item);
		this.setPrice("Crafting",
				this.getPrice("Crafting") - item.getPrice("Market") * quantity);
	}
}

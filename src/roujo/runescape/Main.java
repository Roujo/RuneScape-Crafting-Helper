package roujo.runescape;

import java.util.Collection;

import roujo.runescape.data.Craftable;
import roujo.runescape.data.Fetcher;
import roujo.runescape.data.ItemName;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Debug.isEnabled = true;
		load();
		Collection<Craftable> craftables = Fetcher.getInstance().getCraftables().values();
		for(Craftable craftable : craftables) {
			System.out.println(craftable.getName() + " - Profit: " + craftable.getProfit("Crafting"));
		}
	}
	
	public static void load() {
		for(ItemName itemName : ItemName.values()) {
			Fetcher.getInstance().getItem(itemName);
		}
	}
	
	public static void save() {
		
	}

}

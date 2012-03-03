package roujo.runescape.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import roujo.runescape.Debug;

public class Fetcher {
	private static final Fetcher instance = new Fetcher();
	
	public static Fetcher getInstance() {
		return instance;
	}
	
	private Fetcher() {
		items = new TreeMap<Long, Item>();
		craftables = new TreeMap<Long, Craftable>();
	}
	
	private final Map<Long, Item> items;
	private final Map<Long, Craftable> craftables;
	private final String ItemURLBase = "http://services.runescape.com/m=itemdb_rs/api/catalogue/detail.json?item=";

	public Item getItem(ItemName itemName) {
		Item item;
		if (items.containsKey(itemName.getId())) {
			item = items.get(itemName.getId());
		} else {
			if (itemName.isCraftable()) {
				item = new Craftable();
				for(Component c : itemName.getComponents()) {
					((Craftable) item).addMaterial(c.getItemName(), c.getQuantity());
				}
			} else {
				item = new Item();
			}
			try {
				URL itemURL = new URL(ItemURLBase + itemName.getId());
				BufferedReader in = new BufferedReader(new InputStreamReader(
						itemURL.openStream()));
				StringBuilder response = new StringBuilder();
				String inputLine;

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				String JSONString = response.toString();

				// Jagex wraps the Item JSON Object in a nice extra JSON Object.
				// Yay.
				JSONObject itemData = (JSONObject) ((JSONObject) JSONValue
						.parse(JSONString)).get("item");
				item.setIcon((String) itemData.get("icon"));
				item.setLargeIcon((String) itemData.get("icon_large"));
				item.setId((Long) itemData.get("id"));
				item.setType((String) itemData.get("type"));
				// item.setTypeIcon((String) itemData.get("typeIcon"));
				item.setName((String) itemData.get("name"));
				item.setDescription((String) itemData.get("description"));
				Object marketPriceObj = ((JSONObject) itemData.get("current"))
						.get("price");
				Long marketPrice;
				if(marketPriceObj instanceof Long) {
					marketPrice = (Long) marketPriceObj;
				} else {
					marketPrice = Long.parseLong(((String) marketPriceObj).replace(",", "")); 
				}
				item.setPrice("Market", marketPrice);
				item.setMembers(((String) itemData.get("members"))
						.equals("true"));
				items.put(itemName.getId(), item);
				if(item instanceof Craftable) {
					craftables.put(item.getId(), (Craftable) item);
				}
				if (Debug.isEnabled)
					System.out.println("Finished fetching item: "
							+ item.getName());
			} catch (IOException e) {
				item = null;
			}
		}

		return item;
	}
	
	public Map<Long, Item> getItems() {
		return items;
	}
	
	public Map<Long, Craftable> getCraftables() {
		return craftables;
	}
}

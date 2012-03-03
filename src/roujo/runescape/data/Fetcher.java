package roujo.runescape.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Fetcher {
	// TODO: Switch debug off
	private static boolean debug = true;
	private static final Map<Long, Item> Items = new HashMap<Long, Item>();
	private static final String ItemURLBase = "http://services.runescape.com/m=itemdb_rs/api/catalogue/detail.json?item=";

	public static Item getItem(ItemName itemName) {
		Item item;
		if (Items.containsKey(itemName.getId())) {
			item = Items.get(itemName.getId());
		} else {
			if (itemName.isCraftable()) {
				item = new Craftable();
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
				Items.put(itemName.getId(), item);
				if (debug)
					System.out.println("Finished fetching item: "
							+ item.getName());
			} catch (IOException e) {
				item = null;
			}
		}

		return item;
	}
}

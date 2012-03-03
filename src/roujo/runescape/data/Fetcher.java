package roujo.runescape.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TimeZone;
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
	private final String GraphURLBase = "http://services.runescape.com/m=itemdb_rs/api/graph/";

	public Item getItem(ItemName itemName) {
		Item item;
		if (items.containsKey(itemName.getId())) {
			item = items.get(itemName.getId());
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
				String itemJSONString = response.toString();

				// Jagex wraps the Item JSON Object in a nice extra JSON Object.
				// Yay.
				JSONObject itemData = (JSONObject) ((JSONObject) JSONValue
						.parse(itemJSONString)).get("item");
				item.setIcon((String) itemData.get("icon"));
				item.setLargeIcon((String) itemData.get("icon_large"));
				item.setId((Long) itemData.get("id"));
				item.setType((String) itemData.get("type"));
				// The TypeIcon URL that is returned is invalid
				// item.setTypeIcon((String) itemData.get("typeIcon"));
				item.setName((String) itemData.get("name"));
				item.setDescription((String) itemData.get("description"));
				item.setMembers(((String) itemData.get("members"))
						.equals("true"));
				items.put(itemName.getId(), item);

				// Get the price, not from the item data where its represenation
				// is quirky at best,
				// but from the price graph, where it is not only more precise
				// but also coherent.
				URL graphURL = new URL(GraphURLBase + itemName.getId()
						+ ".json");
				in = new BufferedReader(new InputStreamReader(
						graphURL.openStream()));
				response = new StringBuilder();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				String graphJSONString = response.toString();

				JSONObject graphData = (JSONObject) ((JSONObject) JSONValue
						.parse(graphJSONString)).get("daily");
				Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
				Calendar cal2 = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
				Long today = cal2.getTimeInMillis() + TimeZone.getDefault().getOffset(new Date().getTime());
				item.setPrice("Market", (Long) graphData.get(today.toString()));
				
				

				// Add item to craftables if it is a craftable.
				if (item instanceof Craftable) {
					craftables.put(item.getId(), (Craftable) item);
					for (Component c : itemName.getComponents()) {
						((Craftable) item).addMaterial(c.getItemName(),
								c.getQuantity());
					}
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

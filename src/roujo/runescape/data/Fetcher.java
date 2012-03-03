package roujo.runescape.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class Fetcher {
	private static final String ItemURLBase = "http://services.runescape.com/m=itemdb_rs/api/catalogue/detail.json?item=";

	public static Item getItem(int id) {
		Item item;
		try {
			URL itemURL = new URL(ItemURLBase + id);
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
			item = new Item();
			item.setIcon((String) itemData.get("icon"));
			item.setIcon_large((String) itemData.get("icon_large"));
			item.setId((Long) itemData.get("id"));
			item.setType((String) itemData.get("type"));
			//item.setTypeIcon((String) itemData.get("typeIcon"));
			item.setName((String) itemData.get("name"));
			item.setDescription((String) itemData.get("description"));
			item.setPrice((Long) ((JSONObject) itemData.get("current"))
					.get("price"));
			item.setMembers((Boolean) itemData.get("members"));
		} catch (IOException e) {
			item = null;
		}

		return item;
	}
}

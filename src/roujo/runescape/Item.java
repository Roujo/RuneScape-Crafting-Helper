package roujo.runescape;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.imageio.ImageIO;

public class Item {
	private String type, name, description;
	private URL iconURL, icon_largeURL, typeIconURL;
	private Image icon, icon_large, typeIcon;
	private boolean members;
	private int id, price;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Image getIcon() {
		return icon;
	}

	public void setIcon(String iconURL) throws IOException {
		this.iconURL = new URL(iconURL);
		icon = ImageIO.read(this.iconURL);
	}

	public Image getIcon_large() {
		return icon_large;
	}

	public void setIcon_large(String icon_largeURL) throws IOException {
		this.icon_largeURL = new URL(icon_largeURL);
		icon_large = ImageIO.read(this.icon_largeURL);
	}

	public Image getTypeIcon() {
		return typeIcon;
	}

	public void setTypeIcon(String typeIconURL) throws IOException {
		this.typeIconURL = new URL(typeIconURL);
		typeIcon = ImageIO.read(this.typeIconURL);
	}

	public boolean isMembers() {
		return members;
	}

	public void setMembers(boolean members) {
		this.members = members;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	// TODO: Add trend management
}
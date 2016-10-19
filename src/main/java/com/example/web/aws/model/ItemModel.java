package com.example.web.aws.model;

import java.io.Serializable;

public class ItemModel implements Serializable {

	private static final long serialVersionUID = 4429386992827915231L;

	public ItemModel() {
	}

	public ItemModel(Long id, String name, Integer price, Integer remaining, String imageUrl) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.remaining = remaining;
		this.imageUrl = imageUrl;
	}

	private Long id;

	private String name;

	private Integer price;

	private Integer remaining;

	private String imageUrl;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getRemaining() {
		return remaining;
	}

	public void setRemaining(Integer remaining) {
		this.remaining = remaining;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "ItemModel [id=" + id + ", name=" + name + ", price=" + price + ", remaining=" + remaining
				+ ", imageUrl=" + imageUrl + "]";
	}

}

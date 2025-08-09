package com.example.security.model;

public class Item {
    private Integer id;
    private String title;
    private String image;
    private Double price;
    private Integer stock;

    public Item() {
    }

    public Item(Integer id, String title, String image, Double price, Integer stock) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.price = price;
        this.stock = stock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
}

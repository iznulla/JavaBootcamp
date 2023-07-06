package edu.school21.models;

import java.util.Objects;

public class Product {
  private Long ID;
  private String name;
  private double price;

  public Product(Long id, String name, double price) {
    this.ID = id;
    this.name = name;
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || o.getClass() != this.getClass())
      return false;
    Product product = (Product) o;
    return Objects.equals(this.ID, product.ID) &&
        Objects.equals(this.name, product.name) &&
        Objects.equals(this.price, product.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.ID, this.name, this.price);
  }

  @Override
  public String toString() {
    return "ID=" + this.ID +
        "\nname=" + this.name +
        "\nprice=" + this.price;
  }

}

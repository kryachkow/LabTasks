package com.task7.part2.model;

import java.util.Objects;

public class NoteBook implements PagedItem {

  private String name;
  private String material;
  private int pageNumber;
  private int price;

  public NoteBook() {
  }

  public NoteBook(String name, String material, int pageNumber, int price) {
    this.name = name;
    this.material = material;
    this.pageNumber = pageNumber;
    this.price = price;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getMaterial() {
    return this.material;
  }

  @Override
  public void setMaterial(String material) {
    this.material = material;
  }

  @Override
  public int getPageNumber() {
    return this.pageNumber;
  }

  @Override
  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }

  @Override
  public int getPrice() {
    return this.price;
  }

  @Override
  public void setPrice(int price) {
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof NoteBook)) {
      return false;
    }
    NoteBook noteBook = (NoteBook) o;
    return getPageNumber() == noteBook.getPageNumber() && getPrice() == noteBook.getPrice()
        && Objects.equals(getName(), noteBook.getName()) && Objects.equals(getMaterial(),
        noteBook.getMaterial());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getMaterial(), getPageNumber(), getPrice());
  }

  @Override
  public String toString() {
    return "NoteBook{" + "name='" + name + '\'' + ", material='" + material + '\'' + ", pageNumber="
        + pageNumber + ", price=" + price + '}';
  }
}

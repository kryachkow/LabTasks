package com.store.model;

import java.util.Objects;

public class CartPart {

  private Book book;
  private int quantity;

  public CartPart() {
  }

  public CartPart(Book book, int quantity) {
    this.book = book;
    this.quantity = quantity;
  }

  public Book getBook() {
    return book;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof CartPart)) {
      return false;
    }
    CartPart cartPart = (CartPart) o;
    return getQuantity() == cartPart.getQuantity() && Objects.equals(getBook(), cartPart.getBook());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getBook(), getQuantity());
  }

  @Override
  public String toString() {
    return "CartPart{"
        + "book=" + book
        + ", quantity=" + quantity + '}';
  }
}

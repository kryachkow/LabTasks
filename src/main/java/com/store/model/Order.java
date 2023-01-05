package com.store.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Order {

  private LocalDateTime time;
  private List<CartPart> cart;

  public Order(LocalDateTime time, List<CartPart> cart) {
    this.time = time;
    this.cart = cart;
  }

  public LocalDateTime getTime() {
    return time;
  }

  public void setTime(LocalDateTime time) {
    this.time = time;
  }

  public List<CartPart> getCart() {
    return cart;
  }

  public void setCart(List<CartPart> cart) {
    this.cart = cart;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Order)) {
      return false;
    }
    Order order = (Order) o;
    return Objects.equals(getTime(), order.getTime()) && Objects.equals(getCart(), order.getCart());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getTime(), getCart());
  }

  @Override
  public String toString() {
    return "Order{"
        + "time=" + time
        + ", cart=" + cart + '}';
  }
}

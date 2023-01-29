package com.task1.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Electric extends Item {
    private int price;
    private LocalDate dateOfManufacture;
    private Period guaranteePeriod;

    public Electric() {
    }

    public Electric(int weight, String colour, int price, LocalDate dateOfManufacture, Period guaranteePeriod) {
        super(weight, colour);
        this.price = price;
        this.dateOfManufacture = dateOfManufacture;
        this.guaranteePeriod = guaranteePeriod;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getDateOfManufacture() {
        return dateOfManufacture;
    }

    public void setDateOfManufacture(LocalDate dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
    }

    public Period getGuaranteePeriod() {
        return guaranteePeriod;
    }

    public void setGuaranteePeriod(Period guaranteePeriod) {
        this.guaranteePeriod = guaranteePeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Electric)) return false;
        if (!super.equals(o)) return false;
        Electric electric = (Electric) o;
        return getPrice() == electric.getPrice() && getDateOfManufacture().equals(electric.getDateOfManufacture()) && getGuaranteePeriod().equals(electric.getGuaranteePeriod());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPrice(), getDateOfManufacture(), getGuaranteePeriod());
    }

    @Override
    public String toString() {
        return "Electric{" +
                "price=" + price +
                ", dateOfManufacture=" + dateOfManufacture +
                ", guaranteePeriod=" + guaranteePeriod +
                '}';
    }
}

package com.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Blender extends Electric{
    private int volume;
    private int power;
    private int numberOfMods;

    public Blender() {
    }

    public Blender(int weight, String colour, int price, LocalDate dateOfManufacture, Period guaranteePeriod, int volume, int power, int numberOfMods) {
        super(weight, colour, price, dateOfManufacture, guaranteePeriod);
        this.volume = volume;
        this.power = power;
        this.numberOfMods = numberOfMods;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getNumberOfMods() {
        return numberOfMods;
    }

    public void setNumberOfMods(int numberOfMods) {
        this.numberOfMods = numberOfMods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Blender)) return false;
        if (!super.equals(o)) return false;
        Blender blender = (Blender) o;
        return getVolume() == blender.getVolume() && getPower() == blender.getPower() && getNumberOfMods() == blender.getNumberOfMods();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getVolume(), getPower(), getNumberOfMods());
    }

    @Override
    public String toString() {
        return "Blender{" +
                "volume=" + volume +
                ", power=" + power +
                ", numberOfMods=" + numberOfMods +
                '}';
    }
}

package com.task1.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Phone extends Electric {
    private int batteryVolume;
    private int cameraResolution;
    private String processor;
    private int memoryStorage;

    public Phone() {
    }

    public Phone(int weight, String colour, int price, LocalDate dateOfManufacture, Period guaranteePeriod, int batteryVolume, int cameraResolution, String processor, int memoryStorage) {
        super(weight, colour, price, dateOfManufacture, guaranteePeriod);
        this.batteryVolume = batteryVolume;
        this.cameraResolution = cameraResolution;
        this.processor = processor;
        this.memoryStorage = memoryStorage;
    }

    public int getBatteryVolume() {
        return batteryVolume;
    }

    public void setBatteryVolume(int batteryVolume) {
        this.batteryVolume = batteryVolume;
    }

    public int getCameraResolution() {
        return cameraResolution;
    }

    public void setCameraResolution(int cameraResolution) {
        this.cameraResolution = cameraResolution;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public int getMemoryStorage() {
        return memoryStorage;
    }

    public void setMemoryStorage(int memoryStorage) {
        this.memoryStorage = memoryStorage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Phone)) return false;
        if (!super.equals(o)) return false;
        Phone phone = (Phone) o;
        return getBatteryVolume() == phone.getBatteryVolume() && getCameraResolution() == phone.getCameraResolution() && getMemoryStorage() == phone.getMemoryStorage() && getProcessor().equals(phone.getProcessor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getBatteryVolume(), getCameraResolution(), getProcessor(), getMemoryStorage());
    }

    @Override
    public String toString() {
        return "Phone{" +
                "batteryVolume=" + batteryVolume +
                ", cameraResolution=" + cameraResolution +
                ", processor='" + processor + '\'' +
                ", memoryStorage=" + memoryStorage +
                '}';
    }
}
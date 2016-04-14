package com.bootcamp;

public class Car {

  private String registrationNumber;
  private CarType type;
  private boolean handicapped;

  public Car(CarType type, boolean handicapped) {
    this.type = type;
    this.handicapped = handicapped;
  }

  public Car(String registrationNumber, CarType type, boolean handicapped) {
    this.registrationNumber = registrationNumber;
    this.type = type;
    this.handicapped = handicapped;
  }

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public CarType getType() {
    return type;
  }

  public boolean isHandicapped() {
    return handicapped;
  }

  public enum CarType {HATCHBACK, SEDAN;}
}

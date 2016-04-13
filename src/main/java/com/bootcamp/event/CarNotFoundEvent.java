package com.bootcamp.event;

import com.bootcamp.Car;

public class CarNotFoundEvent implements Event {

  private Car car;

  public CarNotFoundEvent(Car car) {
    this.car = car;
  }

  public Car getCar() {
    return car;
  }
}

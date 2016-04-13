package com.bootcamp;

import com.bootcamp.event.CarNotFoundEvent;

public class PoliceDepartment implements Subscriber<CarNotFoundEvent> {

  private APB apb;

  public PoliceDepartment(APB apb) {
    this.apb = apb;
  }

  public void notifyParty(CarNotFoundEvent event) {
    Car car = event.getCar();

    apb.createNewReport("Car", car.getRegistrationNumber());
    apb.submitReport();
  }
}

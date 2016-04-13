package com.bootcamp.subscriber;

import com.bootcamp.APB;
import com.bootcamp.Car;
import com.bootcamp.event.CarNotFoundEvent;
import com.bootcamp.subscriber.CarNotFoundSubscriber;

public class PoliceDepartment implements CarNotFoundSubscriber {

  private APB apb;

  public PoliceDepartment(APB apb) {
    this.apb = apb;
  }

  public void notifyCarNotFound(CarNotFoundEvent event) {
    Car car = event.getCar();

    apb.createNewReport("Car", car.getRegistrationNumber());
    apb.submitReport();
  }
}

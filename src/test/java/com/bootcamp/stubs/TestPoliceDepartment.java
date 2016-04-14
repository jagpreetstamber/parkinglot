package com.bootcamp.stubs;

import com.bootcamp.APB;
import com.bootcamp.event.CarNotFoundEvent;
import com.bootcamp.subscriber.PoliceDepartment;

public class TestPoliceDepartment extends PoliceDepartment {

  private int noOfCarsNotFound;
  private CarNotFoundEvent event;

  public TestPoliceDepartment() {
    super(new APB());
  }

  @Override
  public void notifyCarNotFound(CarNotFoundEvent event) {
    noOfCarsNotFound++;
    this.event = event;
  }

  public int getNoOfCarsNotFound() {
    return noOfCarsNotFound;
  }

  public CarNotFoundEvent getEvent() {
    return event;
  }
}

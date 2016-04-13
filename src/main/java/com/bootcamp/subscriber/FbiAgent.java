package com.bootcamp.subscriber;

import com.bootcamp.event.CarNotFoundEvent;
import com.bootcamp.subscriber.CarNotFoundSubscriber;
import com.bootcamp.subscriber.EightyPercentParkingSubscriber;

public class FbiAgent implements CarNotFoundSubscriber, EightyPercentParkingSubscriber {

  public void notifyParkingEightyPercentFull() {
    System.out.println("Hooray! Parking is 80% populated");
  }

  public void notifyCarNotFound(CarNotFoundEvent e) {
    startPaperWork();
  }

  protected void startPaperWork() {

  }
}

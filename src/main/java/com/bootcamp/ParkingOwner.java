package com.bootcamp;

import com.bootcamp.subscriber.HasSpaceSubscriber;
import com.bootcamp.subscriber.ParkingFullSubscriber;

public class ParkingOwner implements HasSpaceSubscriber, ParkingFullSubscriber {

  public void notifyParkingLotIsFull() {
    System.out.println("Yes. I am earning good amount of money.");
  }

  public void notifyParkingLotHasSpace() {
    System.out.println("No. My earning's dropped.");
  }
}

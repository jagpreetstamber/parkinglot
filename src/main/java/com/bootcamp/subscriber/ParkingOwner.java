package com.bootcamp.subscriber;

public class ParkingOwner implements HasSpaceSubscriber, ParkingFullSubscriber {

  public void notifyParkingLotIsFull() {
    System.out.println("Yes. I am earning good amount of money.");
  }

  public void notifyParkingLotHasSpace() {
    System.out.println("No. My earning's dropped.");
  }
}

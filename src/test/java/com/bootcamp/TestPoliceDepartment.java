package com.bootcamp;

/**
 * Created by Jagpreet on 13/04/16.
 */
public class TestPoliceDepartment implements Subscriber {

  private int noOfCarsNotFound;

  public void notifyParty() {
    noOfCarsNotFound++;
  }

  public int getNoOfCarsNotFound() {
    return noOfCarsNotFound;
  }
}

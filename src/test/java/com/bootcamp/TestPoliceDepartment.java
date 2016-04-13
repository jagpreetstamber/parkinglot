package com.bootcamp;

public class TestPoliceDepartment extends PoliceDepartment {

  private int noOfCarsNotFound;

  public void notifyParty() {
    noOfCarsNotFound++;
  }

  public int getNoOfCarsNotFound() {
    return noOfCarsNotFound;
  }
}

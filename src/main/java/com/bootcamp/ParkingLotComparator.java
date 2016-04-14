package com.bootcamp;

import java.util.Comparator;

public class ParkingLotComparator implements Comparator<ParkingLot> {

  @Override
  public int compare(ParkingLot lot1, ParkingLot lot2) {
    return lot2.getFreeSpace() - lot1.getFreeSpace();
  }
}

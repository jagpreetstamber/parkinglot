package com.bootcamp;

import java.util.List;
import java.util.Optional;

public abstract class ParkingLotAllocation {

  private int lastAllocatedLot;

  public ParkingLotAllocation(int lastAllocatedLot) {
    this.lastAllocatedLot = lastAllocatedLot;
  }

  public abstract Optional<ParkingLot> getAvailableParkingLot(List<ParkingLot> parkingLots);

  public int getLastAllocatedLot(){
    return lastAllocatedLot;
  }

  protected ParkingLot getNextAvailableParkingLot(List<ParkingLot> parkingLots) {

    ParkingLot availableLot = null;
    for (ParkingLot lot : parkingLots) {
      if (lot.isParkingAvailable()) {
        availableLot = lot;
        break;
      }
    }
    return availableLot;
  }
}

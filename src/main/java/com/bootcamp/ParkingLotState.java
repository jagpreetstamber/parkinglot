package com.bootcamp;

import java.util.List;

public class ParkingLotState {

  private List<ParkingLot> parkingLots;
  private int lastAllocatedLotIndex = -1;

  public ParkingLotState(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
  }

  public void setLastAllocatedLot(ParkingLot lastAllocatedLot) {
    lastAllocatedLotIndex = parkingLots.indexOf(lastAllocatedLot);
  }

  public int getLastAllocatedLotIndex() {
    return lastAllocatedLotIndex;
  }

  public List<ParkingLot> getParkingLots() {
    return parkingLots;
  }
}

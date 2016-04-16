package com.bootcamp.allocation;

import com.bootcamp.ParkingLot;
import com.bootcamp.ParkingLotState;

import java.util.List;
import java.util.Optional;

public abstract class ParkingLotAllocation {

  private ParkingLotState state;

  public ParkingLotAllocation(ParkingLotState state) {
    this.state = state;
  }

  public abstract List<ParkingLot> getParkingLots();

  public abstract Optional<ParkingLot> getAvailableParkingLot();

  protected ParkingLot getNextAvailableParkingLot() {

    ParkingLot availableLot = null;
    List<ParkingLot> parkingLots = getParkingLots();
    if (parkingLots != null) {
      for (ParkingLot lot : parkingLots) {
        if (lot.isParkingAvailable()) {
          availableLot = lot;
          break;
        }
      }
    }
    return availableLot;
  }
}

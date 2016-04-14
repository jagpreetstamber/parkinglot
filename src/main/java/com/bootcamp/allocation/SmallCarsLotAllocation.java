package com.bootcamp.allocation;

import com.bootcamp.ParkingLot;
import com.bootcamp.allocation.ParkingLotAllocation;

import java.util.List;
import java.util.Optional;

public class SmallCarsLotAllocation extends ParkingLotAllocation {

  private int lastAllocatedLot;

  public SmallCarsLotAllocation(int lastAllocatedLot) {
    super(lastAllocatedLot);
    this.lastAllocatedLot = lastAllocatedLot;
  }

  @Override
  public Optional<ParkingLot> getAvailableParkingLot(List<ParkingLot> parkingLots) {

    Optional<ParkingLot> availableLotOptional = allocateParkingLotEvenly(parkingLots);

    if (availableLotOptional.equals(Optional.empty())) {
      ParkingLot availableLot = getNextAvailableParkingLot(parkingLots);

      if (availableLot != null) {
        int index = parkingLots.indexOf(availableLot);
        lastAllocatedLot = index;
        availableLotOptional = Optional.of(availableLot);
      }
    }
    return availableLotOptional;
  }

  @Override
  public int getLastAllocatedLot() {
    return lastAllocatedLot;
  }

  private Optional<ParkingLot> allocateParkingLotEvenly(List<ParkingLot> parkingLots) {

    Optional<ParkingLot> availableLot = Optional.empty();
    for (ParkingLot lot : parkingLots) {
      int index = parkingLots.indexOf(lot);
      boolean isNotLotAllocatedEarlier = lastAllocatedLot % parkingLots.size() < index;

      if (lot.isParkingAvailable() && isNotLotAllocatedEarlier) {
        availableLot = Optional.of(lot);
        lastAllocatedLot = index;
        break;
      }
    }
    return availableLot;
  }
}

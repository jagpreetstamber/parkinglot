package com.bootcamp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LargerCarsLotAllocation extends ParkingLotAllocation {

  public LargerCarsLotAllocation(int lastAllocatedLot) {
    super(lastAllocatedLot);
  }

  @Override
  public Optional<ParkingLot> getAvailableParkingLot(List<ParkingLot> parkingLots) {

    Optional<ParkingLot> availableLotOptional = Optional.empty();
    List<ParkingLot> sortedLots = new ArrayList<>();
    sortedLots.addAll(parkingLots);
    sortedLots.sort(new ParkingLotComparator());
    ParkingLot availableLot = getNextAvailableParkingLot(sortedLots);
    if (availableLot != null) {
      availableLotOptional = Optional.of(availableLot);
    }
    return availableLotOptional;
  }
}

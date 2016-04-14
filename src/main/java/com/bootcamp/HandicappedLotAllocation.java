package com.bootcamp;

import java.util.List;
import java.util.Optional;

public class HandicappedLotAllocation extends ParkingLotAllocation {

  public HandicappedLotAllocation(int lastAllocatedLot) {
    super(lastAllocatedLot);
  }

  @Override
  public Optional<ParkingLot> getAvailableParkingLot(List<ParkingLot> parkingLots) {
    Optional<ParkingLot> availableLotOptional = Optional.empty();
    ParkingLot availableLot = getNextAvailableParkingLot(parkingLots);
    if (availableLot != null) {
      availableLotOptional = Optional.of(availableLot);
    }
    return availableLotOptional;
  }
}

package com.bootcamp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingAttendant {

  private List<ParkingLot> parkingLots;
  private int lastAllocatedLot = -1;

  public ParkingAttendant() {
  }

  public ParkingAttendant(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
  }

  public void add(ParkingLot lot) {
    if (parkingLots == null) {
      parkingLots = new ArrayList<>();
    }
    parkingLots.add(lot);
  }

  public Optional<ParkingLot> getAvailableParkingLot(Car.CarType type, boolean handicapped) {
    Optional<ParkingLot> availableLotOptional = Optional.empty();

    ParkingLotAllocation strategy = null;
    if (parkingLots != null) {
      if (handicapped) {
        strategy = new HandicappedLotAllocation(lastAllocatedLot);
      } else if (type.equals(Car.CarType.SEDAN)) {
        strategy = new LargerCarsLotAllocation(lastAllocatedLot);
      } else {
        strategy = new SmallCarsLotAllocation(lastAllocatedLot);
      }

      availableLotOptional = strategy.getAvailableParkingLot(parkingLots);
      lastAllocatedLot = strategy.getLastAllocatedLot();
    }
    return availableLotOptional;
  }
}

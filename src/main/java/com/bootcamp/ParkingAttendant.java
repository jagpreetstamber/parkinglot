package com.bootcamp;

import com.bootcamp.allocation.HandicappedLotAllocation;
import com.bootcamp.allocation.LargerCarsLotAllocation;
import com.bootcamp.allocation.ParkingLotAllocation;
import com.bootcamp.allocation.SmallCarsLotAllocation;

import java.util.Optional;

public class ParkingAttendant {

  private ParkingLotState state;

  public ParkingAttendant(ParkingLotState state) {
    this.state = state;
  }

  public Optional<ParkingLot> getAvailableParkingLot(Car.CarType type, boolean handicapped) {

    ParkingLotAllocation strategy;
    if (handicapped) {
      strategy = new HandicappedLotAllocation(state);
    } else if (type.equals(Car.CarType.SEDAN)) {
      strategy = new LargerCarsLotAllocation(state);
    } else {
      strategy = new SmallCarsLotAllocation(state);
    }
    return strategy.getAvailableParkingLot();
  }
}

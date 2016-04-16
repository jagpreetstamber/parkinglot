package com.bootcamp.allocation;

import com.bootcamp.ParkingLot;
import com.bootcamp.ParkingLotComparator;
import com.bootcamp.ParkingLotState;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LargerCarsLotAllocation extends ParkingLotAllocation {

  private ParkingLotState state;

  public LargerCarsLotAllocation(ParkingLotState state) {
    super(state);
    this.state = state;
  }

  @Override
  public List<ParkingLot> getParkingLots() {
    List<ParkingLot> sortedParkingLots = new ArrayList<>(state.getParkingLots());
    sortedParkingLots.sort(new ParkingLotComparator());
    return sortedParkingLots;
  }

  @Override
  public Optional<ParkingLot> getAvailableParkingLot() {

    Optional<ParkingLot> availableLotOptional = Optional.empty();
    ParkingLot availableLot = getNextAvailableParkingLot();
    if (availableLot != null) {
      availableLotOptional = Optional.of(availableLot);
    }
    return availableLotOptional;
  }
}

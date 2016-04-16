package com.bootcamp.allocation;

import com.bootcamp.ParkingLot;
import com.bootcamp.ParkingLotState;

import java.util.List;
import java.util.Optional;

public class HandicappedLotAllocation extends ParkingLotAllocation {

  private ParkingLotState state;

  public HandicappedLotAllocation(ParkingLotState state) {
    super(state);
    this.state = state;
  }

  @Override
  public List<ParkingLot> getParkingLots() {
    return state.getParkingLots();
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

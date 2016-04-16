package com.bootcamp.allocation;

import com.bootcamp.ParkingLot;
import com.bootcamp.ParkingLotState;

import java.util.List;
import java.util.Optional;

public class SmallCarsLotAllocation extends ParkingLotAllocation {

  private int lastAllocatedLot;
  private ParkingLotState state;

  public SmallCarsLotAllocation(ParkingLotState state) {
    super(state);
    this.state = state;
  }

  @Override
  public List<ParkingLot> getParkingLots() {
    return state.getParkingLots();
  }

  @Override
  public Optional<ParkingLot> getAvailableParkingLot() {

    lastAllocatedLot = state.getLastAllocatedLotIndex();
    Optional<ParkingLot> availableLotOptional = allocateParkingLotEvenly();

    if (availableLotOptional.equals(Optional.empty())) {
      ParkingLot availableLot = getNextAvailableParkingLot();

      if (availableLot != null) {
        state.setLastAllocatedLot(availableLot);
        availableLotOptional = Optional.of(availableLot);
      }
    }
    return availableLotOptional;
  }

  private Optional<ParkingLot> allocateParkingLotEvenly() {

    List<ParkingLot> parkingLots = getParkingLots();
    Optional<ParkingLot> availableLot = Optional.empty();
    for (ParkingLot lot : parkingLots) {
      int index = parkingLots.indexOf(lot);
      boolean isNotLotAllocatedEarlier = lastAllocatedLot % parkingLots.size() < index;

      if (lot.isParkingAvailable() && isNotLotAllocatedEarlier) {
        availableLot = Optional.of(lot);
        state.setLastAllocatedLot(lot);
        break;
      }
    }
    return availableLot;
  }
}

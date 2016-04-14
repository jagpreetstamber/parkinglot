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

  public Optional<ParkingLot> getAvailableParkingLot() {

    Optional<ParkingLot> availableLot = Optional.empty();
    if (parkingLots != null) {
      availableLot = allocateParkingLotEvenly();

      if (availableLot.equals(Optional.empty())) {
        availableLot = getNextAvailableParkingLot();
      }
    }
    return availableLot;
  }

  private Optional<ParkingLot> allocateParkingLotEvenly() {

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

  private Optional<ParkingLot> getNextAvailableParkingLot() {

    Optional<ParkingLot> availableLot = Optional.empty();
    for (ParkingLot lot : parkingLots) {
      int index = parkingLots.indexOf(lot);
      if (lot.isParkingAvailable()) {
        availableLot = Optional.of(lot);
        lastAllocatedLot = index;
        break;
      }
    }
    return availableLot;
  }
}

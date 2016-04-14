package com.bootcamp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingAttendant {

  private List<ParkingLot> parkingLots;
  private List<ParkingLot> sortedLots;
  private int lastAllocatedLot = -1;

  public ParkingAttendant() {
    sortedLots = new ArrayList<>();
  }

  public ParkingAttendant(List<ParkingLot> parkingLots) {
    this.parkingLots = parkingLots;
    sortedLots = new ArrayList<>();
  }

  public void add(ParkingLot lot) {
    if (parkingLots == null) {
      parkingLots = new ArrayList<>();
    }
    parkingLots.add(lot);
  }

  public Optional<ParkingLot> getAvailableParkingLot(Car.CarType type, boolean handicapped) {
    Optional<ParkingLot> availableLotOptional = Optional.empty();

    if (parkingLots != null) {
      if (handicapped) {
        ParkingLot availableLot = getNextAvailableParkingLot(parkingLots);
        if (availableLot != null) {
          availableLotOptional = Optional.of(availableLot);
        }
      } else if (type.equals(Car.CarType.SEDAN)) {
        availableLotOptional = getAvailableParkingLotForLargeCars();
      } else {
        availableLotOptional = getAvailableParkingLotForSmallCars();
      }
    }
    return availableLotOptional;
  }

  private Optional<ParkingLot> getAvailableParkingLotForSmallCars() {

    Optional<ParkingLot> availableLotOptional = allocateParkingLotEvenly();
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

  private Optional<ParkingLot> getAvailableParkingLotForLargeCars() {

    Optional<ParkingLot> availableLotOptional = Optional.empty();
    sortedLots.clear();
    sortedLots.addAll(parkingLots);
    sortedLots.sort(new ParkingLotComparator());
    ParkingLot availableLot = getNextAvailableParkingLot(sortedLots);
    if (availableLot != null) {
      availableLotOptional = Optional.of(availableLot);
    }
    return availableLotOptional;
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

  private ParkingLot getNextAvailableParkingLot(List<ParkingLot> parkingLots) {

    ParkingLot availableLot = null;
    for (ParkingLot lot : parkingLots) {
      if (lot.isParkingAvailable()) {
        availableLot = lot;
        break;
      }
    }
    return availableLot;
  }
}

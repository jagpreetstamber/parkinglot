package com.bootcamp;

import com.bootcamp.exception.ParkingFullException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingAttendant {

  private List<ParkingLot> parkingLots;

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

  public Optional<ParkingLot> directToFreeParkingLot() {

    Optional<ParkingLot> availableLot = Optional.empty();
    if (parkingLots != null) {
      for (ParkingLot lot : parkingLots) {
        try {
          lot.isParkingSlotAvailable();
          availableLot = Optional.of(lot);
          break;
        } catch (ParkingFullException e) {
        }
      }
    }
    return availableLot;
  }
}

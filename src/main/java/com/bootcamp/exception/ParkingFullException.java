package com.bootcamp.exception;

/**
 * Created by Jagpreet on 13/04/16.
 */
public class ParkingFullException extends ParkingLotException {

  public ParkingFullException() {
    super("Parking lot is full");
  }
}

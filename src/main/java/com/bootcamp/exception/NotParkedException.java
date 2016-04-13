package com.bootcamp.exception;

/**
 * Created by Jagpreet on 13/04/16.
 */
public class NotParkedException extends ParkingLotException {

  public NotParkedException() {
    super("Car not Parked");
  }
}

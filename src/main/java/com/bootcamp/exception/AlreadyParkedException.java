package com.bootcamp.exception;

/**
 * Created by Jagpreet on 13/04/16.
 */
public class AlreadyParkedException extends ParkingLotException {

  public AlreadyParkedException() {
    super("Car Already Parked");
  }
}

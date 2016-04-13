package com.bootcamp.exception;

/**
 * Created by Jagpreet on 13/04/16.
 */
public class ParkingLotException extends Exception {

  private String message;

  public ParkingLotException(String message) {
    super(message);
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}

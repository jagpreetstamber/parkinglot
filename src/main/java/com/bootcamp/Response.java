package com.bootcamp;

/**
 * Created by Jagpreet on 13/04/16.
 */
public class Response {

  private boolean success;
  private String message;
  private int parkingSlotNumber;

  public boolean isSuccess() {
    return success;
  }

  public String getMessage() {
    return message;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public int getParkingSlotNumber() {
    return parkingSlotNumber;
  }

  public void setParkingSlotNumber(int parkingSlotNumber) {
    this.parkingSlotNumber = parkingSlotNumber;
  }
}

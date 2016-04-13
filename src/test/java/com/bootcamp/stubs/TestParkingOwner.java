package com.bootcamp.stubs;

import com.bootcamp.ParkingOwner;

public class TestParkingOwner extends ParkingOwner {

  private int fullNotificationCount;
  private int hasSpaceNotificationCount;

  @Override
  public void notifyParkingLotIsFull() {
    fullNotificationCount++;
  }

  @Override
  public void notifyParkingLotHasSpace() {
    hasSpaceNotificationCount++;
  }

  public int getFullNotificationCount() {
    return fullNotificationCount;
  }

  public int getHasSpaceNotificationCount() {
    return hasSpaceNotificationCount;
  }
}

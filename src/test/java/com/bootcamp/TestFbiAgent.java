package com.bootcamp;

/**
 * Created by Jagpreet on 13/04/16.
 */
public class TestFbiAgent implements Subscriber {

  private int notificationCount;

  public void notifyParty() {
    notificationCount++;
  }

  public int getNotificationCount() {
    return notificationCount;
  }
}

package com.bootcamp;

public class APB {

  private String itemMissing;
  private String itemId;

  public APB(String itemMissing, String itemId) {
    this.itemMissing = itemMissing;
    this.itemId = itemId;
  }

  public String getItemId() {
    return itemId;
  }
}

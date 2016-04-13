package com.bootcamp.stubs;

import com.bootcamp.APB;

public class APBStub extends APB {

  private int noOfReportsSubmitted;

  @Override
  public void submitReport() {
    noOfReportsSubmitted++;
  }

  public int getNoOfReportsSubmitted() {
    return noOfReportsSubmitted;
  }
}

package com.bootcamp;

import com.bootcamp.event.CarNotFoundEvent;
import com.bootcamp.event.Event;

import java.util.ArrayList;
import java.util.List;

public class PoliceDepartment implements Subscriber {

  private List<APB> reports;

  public PoliceDepartment() {
    this.reports = new ArrayList<APB>();
  }

  public void notifyParty(Event e) {
    CarNotFoundEvent event = (CarNotFoundEvent) e;

    Car car = event.getCar();

    reports.add(new APB("Car", car.getRegistrationNumber()));
  }

  public List<APB> getSubmittedReports() {
    return reports;
  }
}

package com.bootcamp;

import com.bootcamp.event.CarNotFoundEvent;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PoliceDepartmentTest {

  @Test
  public void testReportHasBeenFiled() throws Exception {
    int expectedReportCount = 1;
    PoliceDepartment department = new PoliceDepartment();
    Car missingCar = new Car("NY 12 AB 1234");
    CarNotFoundEvent event = new CarNotFoundEvent(missingCar);
    String expectedRegistrationNumber = missingCar.getRegistrationNumber();

    department.notifyParty(event);
    List<APB> reports = department.getSubmittedReports();
    String actualRegistrationNumber = reports.get(0).getItemId();

    assertEquals(expectedReportCount, reports.size());
    assertEquals(expectedRegistrationNumber, actualRegistrationNumber);
  }
}
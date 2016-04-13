package com.bootcamp;

import com.bootcamp.event.CarNotFoundEvent;
import com.bootcamp.stubs.APBStub;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PoliceDepartmentTest {

  @Test
  public void testReportHasBeenFiled() throws Exception {
    int expectedReportCount = 1;
    APBStub apbStub = new APBStub();
    PoliceDepartment department = new PoliceDepartment(apbStub);
    Car missingCar = new Car("NY 12 AB 1234");
    CarNotFoundEvent event = new CarNotFoundEvent(missingCar);

    department.notifyParty(event);

    assertEquals(expectedReportCount, apbStub.getNoOfReportsSubmitted());
  }
}
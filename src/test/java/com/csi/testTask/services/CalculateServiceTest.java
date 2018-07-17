package com.csi.testTask.services;

import static org.junit.Assert.assertEquals;

import com.csi.testTask.domain.Price;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("deprecation")
public class CalculateServiceTest {

  private List<Price> old;

  @Before
  public void setUp() {
    old = new ArrayList<Price>();
    old.add(
        new Price(1L, "122856", 1, 1, new Date(2013, 1, 1, 0, 0, 0), new Date(2013, 1, 31, 23, 59, 59), 11000));
    old.add(
        new Price(1L, "122856", 2, 1, new Date(2013, 1, 10, 0, 0, 0), new Date(2013, 1, 20, 23, 59, 59), 99000));
    old.add(
        new Price(1L, "6654", 1, 2, new Date(2013, 1, 1, 0, 0, 0), new Date(2013, 1, 31, 0, 0, 0), 5000));
  }

  //given test
  @Test
  public void calculateTest() {
    List<Price> newP = new ArrayList<Price>();
    newP.add(
        new Price(1L, "122856", 1, 1, new Date(2013, 1, 20, 0, 0, 0), new Date(2013, 2, 20, 23, 59, 59), 11000));
    newP.add(
        new Price(1L, "122856", 2, 1, new Date(2013, 1, 15, 0, 0, 0), new Date(2013, 1, 25, 23, 59, 59), 92000));
    newP.add(
        new Price(1L, "6654", 1, 2, new Date(2013, 1, 12, 0, 0, 0), new Date(2013, 1, 13, 0, 0, 0), 4000));

    List<Price> result = CalculateService.calculate(old, newP);

    List<Price> expected = new ArrayList<Price>();
    expected.add(
        new Price(1L, "122856", 1, 1, new Date(2013, 1, 1, 0, 0, 0), new Date(2013, 2, 20, 23, 59, 59), 11000));
    expected.add(
        new Price(1L, "122856", 2, 1, new Date(2013, 1, 10, 0, 0, 0), new Date(2013, 1, 15, 0, 0, 0), 99000));
    expected.add(
        new Price(1L, "122856", 2, 1, new Date(2013, 1, 15, 0, 0, 0), new Date(2013, 1, 25, 23, 59, 59), 92000));
    expected.add(
        new Price(1L, "6654", 1, 2, new Date(2013, 1, 1, 0, 0, 0), new Date(2013, 1, 12, 0, 0, 0), 5000));
    expected.add(
        new Price(1L, "6654", 1, 2, new Date(2013, 1, 12, 0, 0, 0), new Date(2013, 1, 13, 0, 0, 0), 4000));
    expected.add(
        new Price(1L, "6654", 1, 2, new Date(2013, 1, 13, 0, 0, 0), new Date(2013, 1, 31, 0, 0, 0), 5000));

    Collections.sort(result);
    Collections.sort(expected);
    assertEquals(expected, result);
  }

  //extension of entry with same value
  @Test
  public void calculateTest2() {
    List<Price> newP = new ArrayList<Price>();
    newP.add(
        new Price(1L, "122856", 1, 1, new Date(2012, 1, 20, 0, 0, 0), new Date(2013, 1, 1, 0, 0, 0), 11000));
    newP.add(
        new Price(1L, "122856", 1, 1, new Date(2013, 1, 31, 23, 59, 59), new Date(2014, 1, 25, 23, 59, 59), 11000));
    newP.add(
        new Price(1L, "6654", 1, 2, new Date(2012, 1, 12, 0, 0, 0), new Date(2014, 1, 13, 0, 0, 0), 4000));

    List<Price> result = CalculateService.calculate(old, newP);

    List<Price> expected = new ArrayList<Price>();
    expected.add(
        new Price(1L, "122856", 1, 1, new Date(2012, 1, 20, 0, 0, 0), new Date(2014, 1, 25, 23, 59, 59), 11000));
    expected.add(
        new Price(1L, "122856", 2, 1, new Date(2013, 1, 10, 0, 0, 0), new Date(2013, 1, 20, 23, 59, 59), 99000));
    expected.add(
        new Price(1L, "6654", 1, 2, new Date(2012, 1, 12, 0, 0, 0), new Date(2014, 1, 13, 0, 0, 0), 4000));

    Collections.sort(result);
    Collections.sort(expected);
    assertEquals(expected, result);
  }

  //double adding
  @Test
  public void calculateTest3() {
    List<Price> newP = new ArrayList<Price>();
    newP.add(
        new Price(1L, "122856", 1, 1, new Date(2012, 1, 20, 0, 0, 0), new Date(2013, 1, 1, 0, 0, 0), 11000));
    newP.add(
        new Price(1L, "122856", 1, 1, new Date(2013, 1, 1, 0, 0, 0), new Date(2014, 1, 25, 23, 59, 59), 11000));
    newP.add(
        new Price(1L, "6654", 1, 2, new Date(2012, 1, 12, 0, 0, 0), new Date(2014, 1, 13, 0, 0, 0), 4000));
    newP.add(
        new Price(1L, "6654", 1, 2, new Date(2012, 1, 12, 0, 0, 0), new Date(2014, 1, 13, 0, 0, 0), 4000));

    List<Price> result = CalculateService.calculate(old, newP);

    List<Price> expected = new ArrayList<Price>();
    expected.add(
        new Price(1L, "122856", 1, 1, new Date(2012, 1, 20, 0, 0, 0), new Date(2014, 1, 25, 23, 59, 59), 11000));
    expected.add(
        new Price(1L, "122856", 2, 1, new Date(2013, 1, 10, 0, 0, 0), new Date(2013, 1, 20, 23, 59, 59), 99000));
    expected.add(
        new Price(1L, "6654", 1, 2, new Date(2012, 1, 12, 0, 0, 0), new Date(2014, 1, 13, 0, 0, 0), 4000));

    Collections.sort(result);
    Collections.sort(expected);
    assertEquals(expected, result);
  }

  //1 new overwrite all old
  @Test
  public void calculateTest4() {
    List<Price> newP = new ArrayList<Price>();
    newP.add(
        new Price(1L, "122856", 1, 1, new Date(2012, 1, 20, 0, 0, 0), new Date(2013, 1, 1, 0, 0, 0), 11000));
    newP.add(
        new Price(1L, "122856", 1, 1, new Date(2013, 1, 1, 0, 0, 0), new Date(2014, 1, 25, 23, 59, 59), 11000));
    newP.add(
        new Price(1L, "6654", 1, 2, new Date(2012, 1, 12, 0, 0, 0), new Date(2014, 1, 13, 0, 0, 0), 4000));
    newP.add(
        new Price(1L, "122856", 1, 1, new Date(2011, 1, 20, 0, 0, 0), new Date(2015, 1, 1, 0, 0, 0), 15000));

    List<Price> result = CalculateService.calculate(old, newP);

    List<Price> expected = new ArrayList<Price>();
    expected.add(
        new Price(1L, "122856", 1, 1, new Date(2011, 1, 20, 0, 0, 0), new Date(2015, 1, 1, 0, 0, 0), 15000));
    expected.add(
        new Price(1L, "122856", 2, 1, new Date(2013, 1, 10, 0, 0, 0), new Date(2013, 1, 20, 23, 59, 59), 99000));
    expected.add(
        new Price(1L, "6654", 1, 2, new Date(2012, 1, 12, 0, 0, 0), new Date(2014, 1, 13, 0, 0, 0), 4000));

    Collections.sort(result);
    Collections.sort(expected);
    assertEquals(expected, result);
  }

  //for 69 line coverage
  @Test
  public void calculateTest5() {
    List<Price> newP = new ArrayList<Price>();
    newP.add(
        new Price(1L, "122856", 1, 1, new Date(2012, 1, 20, 0, 0, 0), new Date(2013, 1, 5, 0, 0, 0), 1000));
    newP.add(
        new Price(1L, "122856", 1, 1, new Date(2013, 1, 31, 23, 59, 59), new Date(2014, 1, 25, 23, 59, 59), 11000));
    newP.add(
        new Price(1L, "6654", 1, 2, new Date(2012, 1, 12, 0, 0, 0), new Date(2014, 1, 13, 0, 0, 0), 4000));

    List<Price> result = CalculateService.calculate(old, newP);

    List<Price> expected = new ArrayList<Price>();
    expected.add(
        new Price(1L, "122856", 1, 1, new Date(2012, 1, 20, 0, 0, 0), new Date(2013, 1, 5, 0, 0, 0), 1000));
    expected.add(
        new Price(1L, "122856", 1, 1, new Date(2013, 1, 5, 0, 0, 0), new Date(2014, 1, 25, 23, 59, 59), 11000));
    expected.add(
        new Price(1L, "122856", 2, 1, new Date(2013, 1, 10, 0, 0, 0), new Date(2013, 1, 20, 23, 59, 59), 99000));
    expected.add(
        new Price(1L, "6654", 1, 2, new Date(2012, 1, 12, 0, 0, 0), new Date(2014, 1, 13, 0, 0, 0), 4000));

    Collections.sort(result);
    Collections.sort(expected);
    assertEquals(expected, result);
  }

  @Test(expected = RuntimeException.class)
  public void wrongDataTest() {
    old.get(0).setBegin(new Date(2018, 1, 20));
    old.get(0).setEnd(new Date(2000, 11, 11));
    List<Price> newP = new ArrayList<Price>();
    newP.add(
        new Price(1L, "122856", 1, 1, new Date(2012, 1, 20, 0, 0, 0), new Date(2013, 1, 5, 0, 0, 0), 1000));
    CalculateService.calculate(old, newP);

  }

  @Test(expected = RuntimeException.class)
  public void wrongDataTest2() {
    List<Price> newP = new ArrayList<Price>();
    newP.add(
        new Price(1L, "122856", 1, 1, new Date(2012, 1, 20, 0, 0, 0), new Date(2010, 1, 5, 0, 0, 0), 1000));
    CalculateService.calculate(old, newP);

  }


}

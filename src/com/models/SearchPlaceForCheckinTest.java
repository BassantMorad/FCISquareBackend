package com.models;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchPlaceForCheckinTest {

  @Test
  public void getCheckins() {
	  Assert.assertEquals(false,SearchPlaceForCheckin.getCheckins());
  //  throw new RuntimeException("Test not implemented");
  }

  @Test
  public void undoCheckin() {
	  Assert.assertEquals(true,SearchPlaceForCheckin.undoCheckin());
    //throw new RuntimeException("Test not implemented");
  }
}

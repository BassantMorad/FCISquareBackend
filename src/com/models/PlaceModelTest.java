package com.models;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaceModelTest {

String placeName="Ahmed Zewail St.",desc="";
PlaceModel place=new PlaceModel("");
Double lat=33.33,lon=31.21;
  @Test
  public void addNewPlace() {
	  Assert.assertEquals(true, place.addNewPlace(placeName, lat, lon, desc,""));
    //throw new RuntimeException("Test not implemented");
  }
}

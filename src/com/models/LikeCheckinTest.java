package com.models;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LikeCheckinTest {

	Like l=new LikeCheckin(55);
	
  @Test
  public void getNumberOfLikes() {
	  Assert.assertEquals(1,l.getNumberOfLikes());
    //throw new RuntimeException("Test not implemented");
  }
}

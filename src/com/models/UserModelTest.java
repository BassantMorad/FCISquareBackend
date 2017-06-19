package com.models;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.models.UserModel;

public class UserModelTest {

 @Test
  public void addNewfollow() {
	  Assert.assertEquals(false,UserModel.addNewfollow("ahmed","mhmdsamir@gmail.com"));
    //throw new RuntimeException("Test not implemented");
  }

 /* @Test
  public void unfollow() {
	  Assert.assertEquals(true,UserModel.unfollow("ahmed","mhmdsamir@gmail.com"));
    //throw new RuntimeException("Test not implemented");
  }*/
}

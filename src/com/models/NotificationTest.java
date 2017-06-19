package com.models;

import org.testng.Assert;
import org.testng.annotations.Test;

public class NotificationTest {

  @Test
  public void addNotification() {
	  Assert.assertEquals(false,Notification.addNotification());
   // throw new RuntimeException("Test not implemented");
  }
}

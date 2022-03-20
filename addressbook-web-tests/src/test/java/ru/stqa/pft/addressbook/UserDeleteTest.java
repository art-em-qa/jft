package ru.stqa.pft.addressbook;

import org.testng.annotations.*;
import org.openqa.selenium.*;


public class UserDeleteTest extends TestBase{

  @Test
  public void testUserDelete() throws Exception {
    selectUser();
    deleteSelectedUsers();
    confirmDeleteUsers();
    returnHomePage();
  }
}

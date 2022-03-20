package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;


public class UserDeleteTest extends TestBase{

  @Test
  public void testUserDelete() throws Exception {
    app.getNavigationHelper().selectRandomCheckBox();
    app.getContactHelper().deleteSelectedUsers();
    app.getNavigationHelper().acceptAllert();
    app.getNavigationHelper().returnHomePage();
  }
}

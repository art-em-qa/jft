package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;


public class UserDeleteTest extends TestBase{

  @Test
  public void testUserDelete() throws Exception {
    app.selectUser();
    app.deleteSelectedUsers();
    app.confirmDeleteUsers();
    app.returnHomePage();
  }
}

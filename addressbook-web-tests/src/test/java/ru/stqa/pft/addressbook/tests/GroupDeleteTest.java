package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;


public class GroupDeleteTest extends TestBase{

  @Test
  public void testGroupDelete() throws Exception {
    app.openGroupPage();
    app.selectGroup();
    app.deleteSelectedGroup();
    app.returnGroupPage();
  }
}

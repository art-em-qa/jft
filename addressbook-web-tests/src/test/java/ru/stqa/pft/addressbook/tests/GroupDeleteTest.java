package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;


public class GroupDeleteTest extends TestBase{

  @Test
  public void testGroupDelete() throws Exception {
    app.getNavigationHelper().openGroupPage();
    app.getNavigationHelper().selectRandomCheckBox();
    app.getGroupHelper().deleteSelectedGroup();
    app.getGroupHelper().returnGroupPage();
  }
}

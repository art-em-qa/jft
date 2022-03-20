package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;


public class GroupDeleteTest extends TestBase{

  @Test
  public void testGroupDelete() throws Exception {
    openGroupPage();
    selectGroup();
    deleteSelectedGroup();
    returnGroupPage();
  }
}

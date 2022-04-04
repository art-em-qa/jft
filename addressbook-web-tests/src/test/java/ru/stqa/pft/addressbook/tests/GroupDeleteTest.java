package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;


public class GroupDeleteTest extends TestBase{

  @Test
  public void testGroupDelete() throws Exception {
    app.getNavigationHelper().openGroupPage();
    if(! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("peopleTest", "humanTest", "testGroup"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupLis();
    app.getNavigationHelper().selectCheckBox(before.size() -1);
    app.getGroupHelper().deleteSelectedGroup();
    app.getGroupHelper().returnGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupLis();
    Assert.assertEquals(after.size(), before.size() -1);
  }
}

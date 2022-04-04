package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;


public class GroupCreationTest extends TestBase{


    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().openGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupLis();
        app.getGroupHelper().createGroup(new GroupData("peopleTest", "humanTest", "testGroup"));
        List<GroupData> after = app.getGroupHelper().getGroupLis();
        Assert.assertEquals(after.size(), before.size() +1);
    }

}

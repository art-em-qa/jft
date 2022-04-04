package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;


public class GroupCreationTest extends TestBase{


    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().openGroupPage();
        int before = app.getGroupHelper().getGroupCount();
        app.getGroupHelper().createGroup(new GroupData("peopleTest", "humanTest", "testGroup"));
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before +1);
    }

}

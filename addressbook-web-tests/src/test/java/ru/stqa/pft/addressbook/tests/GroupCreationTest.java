package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;


public class GroupCreationTest extends TestBase{


    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().openGroupPage();
        app.getGroupHelper().initCreateNewGroup();
        app.getGroupHelper().fillGropForm(new GroupData("peopleTest", "humanTest", "testGroup"));
        app.getGroupHelper().submitCreateNewGroup();
        app.getGroupHelper().returnGroupPage();
    }

}

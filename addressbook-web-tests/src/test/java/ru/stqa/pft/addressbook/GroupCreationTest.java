package ru.stqa.pft.addressbook;

import org.testng.annotations.*;


public class GroupCreationTest extends TestBase{


    @Test
    public void testGroupCreation() throws Exception {
        openGroupPage();
        initCreateNewGroup();
        fillGropForm(new GroupData("peopleTest", "humanTest", "testGroup"));
        submitCreateNewGroup();
        returnGroupPage();
    }

}

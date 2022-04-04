package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupModificationsTests extends TestBase{

    @Test
    public void testsGroupModifications() {
        app.getNavigationHelper().openGroupPage();
        if(! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("peopleTest", "humanTest", "testGroup"));
        }
        List<GroupData> before = app.getGroupHelper().getGroupLis();
        app.getNavigationHelper().selectCheckBox(0);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGropForm(new GroupData("Test1", "Test2", "Test3"));
        app.getGroupHelper().submitGroupModifications();
        app.getGroupHelper().returnGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupLis();
        Assert.assertEquals(after.size(), before.size());
    }
}

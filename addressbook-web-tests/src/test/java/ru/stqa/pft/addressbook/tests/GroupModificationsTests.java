package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationsTests extends TestBase{

    @Test
    public void testsGroupModifications() {
        app.getNavigationHelper().openGroupPage();
        app.getNavigationHelper().selectRandomCheckBox();
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGropForm(new GroupData("Test1", "Test2", "Test3"));
        app.getGroupHelper().submitGroupModifications();
        app.getGroupHelper().returnGroupPage();
    }
}

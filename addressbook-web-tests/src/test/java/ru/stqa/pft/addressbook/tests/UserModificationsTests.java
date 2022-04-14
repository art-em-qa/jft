package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UserModificationsTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if(app.contact().all().size() == 0){
            app.contact().createContactAndGroupIfGroupNotExist(new UserData().withName("Antonio").withLastname("Fagundes").
                    withAddress("Portugal, St.Barbara").withWorkphone("+0123456789").withEmail("a.fagundes@stbarbara.com").
                    withGroup("actor"));
        }
    }

    @Test
    public void testUserModifications() {
        Contacts before = app.contact().all();
        UserData modifiedContact = before.iterator().next();
        UserData modContact = new UserData().withId(modifiedContact.getId()).withName("Antonio-Maria").withLastname("Fagundes").
                withAddress("Portugal, St.Barbara").withWorkphone("+0123456789").withEmail("a.fagundes@stbarbara.com");
        app.contact().modify(modContact);
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(modContact)));
    }

}
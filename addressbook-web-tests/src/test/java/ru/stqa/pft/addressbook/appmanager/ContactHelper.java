package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitCreateNewUser() {
        click(By.name("submit"));
    }

    public void fillFormNewContact(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        attach(By.name("photo"), contactData.getPhoto());
        type(By.name("work"), contactData.getWorkphone());
        type(By.name("home"), contactData.getHomephone());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("phone2"), contactData.getHomephone2());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public boolean isGroupCreatedByName(String groupName) {
        try{
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(groupName);
            return true;
        } catch (NoSuchElementException e){
            return false;
        }
    }

    public void initCreateNewUser() {
        click(By.linkText("add new"));
    }

    public void deleteSelectedUsers() {
        click(By.xpath("//input[@value='Delete']"));
    }


    public void initContactModificationsById(int id) {
        click(By.cssSelector("a[href='edit.php?id="+ id + "']"));
    }

    public void submitUserModifications() {
        click(By.name("update"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public void create(ContactData contact) {
        initCreateNewUser();
        fillFormNewContact(contact, true);
        submitCreateNewUser();
        contactsCache = null;
        stopCreateAndOpenHomePage();
    }

    public void selectGroupList(GroupData group) {
        new Select(wd.findElement(By.name("group"))).selectByValue(String.valueOf(group.getId()));;
    }

    public void selectContactCheckboxById(int id) {
        wd.findElement(By.cssSelector("input[id='" +id + "']")).click();
    }

    public void delete(ContactData contact) {
        selectContactCheckboxById(contact.getId());
        deleteSelectedUsers();
        acceptAllert();
        contactsCache = null;
        openHomePage();
    }

    private void stopCreateAndOpenHomePage() {
        click(By.linkText("home page"));
    }

    public boolean isGroupCreatedByIndex(int index) {
        try {
            new Select (wd.findElement(By.name("new_group"))).selectByIndex(index);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void modificateContact(ContactData contact) {
        initContactModificationsById(contact.getId());
        fillFormNewContact(contact, false);
        submitUserModifications();
        contactsCache = null;
    }

    public void modify(ContactData modContact) {
        modificateContact(modContact);
        openHomePage();
    }

    public void acceptAllert() {
        wd.switchTo().alert().accept();
    }

    public void openHomePage() {
        if(isElementPresent(By.id("maintable"))){
            return;
        }
        click(By.linkText("home"));
    }

    public void addInGroup(ContactData contact, GroupData group) {
        selectContactCheckboxById(contact.getId());
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getName());
        click(By.xpath("(//input[@name='add'])"));
    }

    public Groups findGroupForAdding(ContactData contact, Groups groups) {
        Groups groupsInContact = contact.getGroups();
        groups.removeAll(groupsInContact);
        return groups;
    }

    private Contacts contactsCache = null;

    public Contacts all() {
        if(contactsCache != null){
            return new Contacts(contactsCache);
        }
        contactsCache = new Contacts();
        List<WebElement> listRows = wd.findElements(By.name("entry"));
        for (WebElement element : listRows) {
            List<WebElement> tag = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("id"));
            String lastname = tag.get(1).getText();
            String firstname = tag.get(2).getText();
            String address = tag.get(3).getText();
            String allEmail = tag.get(4).getText();
            String allPhones = tag.get(5).getText();
            contactsCache.add(new ContactData().withId(id).withName(firstname).withLastname(lastname).
                    withAllPhones(allPhones).withAddress(address).withAllEmails(allEmail));
        }
        return new Contacts(contactsCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationsById(contact.getId());
        int id = contact.getId();
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String workphone = wd.findElement(By.name("work")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String homephone2 = wd.findElement(By.name("phone2")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(id).withName(firstname).withLastname(lastname).withAddress(address).withEmail(email).
                withtEmail2(email2).withEmail3(email3).withHomephone(home).withWorkphone(workphone).withMobile(mobile).
                withHomephone2(homephone2);

    }

    public void contactRemoveFromGroup(ContactData contactRemove) {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(contactRemove.getGroups().iterator().next().getName());
        selectContactCheckboxById(contactRemove.getId());
        removeToGroup();
    }

    private void removeToGroup() {
        click(By.name("remove"));
    }
}

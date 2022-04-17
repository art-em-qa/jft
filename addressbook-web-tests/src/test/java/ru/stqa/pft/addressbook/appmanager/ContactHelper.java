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

import java.util.List;

import static org.testng.Assert.assertTrue;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitCreateNewUser() {
        click(By.name("submit"));
    }

    public void fillFormNewUser(ContactData userData, boolean creation) {
        type(By.name("firstname"), userData.getFirstname());
        type(By.name("lastname"), userData.getLastname());
        type(By.name("address"), userData.getAddress());
        attach(By.name("photo"), userData.getPhoto());
        type(By.name("work"), userData.getWorkphone());
        type(By.name("home"), userData.getHomephone());
        type(By.name("mobile"), userData.getMobile());
        type(By.name("email"), userData.getEmail());
        type(By.name("email2"), userData.getEmail2());
        type(By.name("email3"), userData.getEmail3());
        if (creation) {
            isGroupCreatedByName(userData.getGroup());
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

    public void createContactAndGroupIfGroupNotExist(ContactData contact) {
        initCreateNewUser();
        if(! isGroupCreatedByName(contact.getGroup())){
            new GroupHelper(wd).create(new GroupData().withName(contact.getGroup()));
            initCreateNewUser();
        }
        fillFormNewUser(contact, true);
        submitCreateNewUser();
        contactsCache = null;
        stopCreateAndOpenHomePage();
    }

    public void createContact(ContactData contact) {
        initCreateNewUser();
        fillFormNewUser(contact, true);
        submitCreateNewUser();
        contactsCache = null;
        stopCreateAndOpenHomePage();
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
        fillFormNewUser(contact, false);
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
        wd.navigate().back();
        return new ContactData().withId(id).withName(firstname).withLastname(lastname).withAddress(address).withEmail(email).
                withtEmail2(email2).withEmail3(email3).withHomephone(home).withWorkphone(workphone).withMobile(mobile);

    }

}

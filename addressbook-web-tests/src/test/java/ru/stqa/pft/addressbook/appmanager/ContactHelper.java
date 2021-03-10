package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getPhone());
    type(By.name("email"), contactData.getEmail());
    attach(By.name("photo"), contactData.getPhoto());
    /*if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }*/
  }

  public void gotoAddNewContactPage() {
    click(By.linkText("add new"));
  }


  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }



  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void acceptAlertDeletionContact() throws InterruptedException {
    /*wd.switchTo().alert().accept();*/
    Thread.sleep(1000);
  }

  public void initContactModification(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  }

  public void initContactModificationById(int id) {
    //wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }

  public void submitContactModification() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }

  public void create(ContactData contact, boolean creation) {
    gotoAddNewContactPage();
    fillContactForm(contact, true);
    submitContactCreation();
    contactCache = null;
  }

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
  }

  public void delete(int index) throws InterruptedException {
    initContactModification(index);
    deleteSelectedContact();
    acceptAlertDeletionContact();
  }

  public void delete(ContactData contact) throws InterruptedException {
    initContactModificationById(contact.getId());
    deleteSelectedContact();
    contactCache = null;
    acceptAlertDeletionContact();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String lastname = element.findElement(By.xpath(".//td[2]")).getText();
      String firstname = element.findElement(By.xpath(".//td[3]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return contacts;
  }

  private Contacts contactCache = null;


  public Contacts all() {
    if(contactCache != null){
      return new Contacts(contactCache);
    }

    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String lastname = element.findElement(By.xpath(".//td[2]")).getText();
      String firstname = element.findElement(By.xpath(".//td[3]")).getText();
      String allPhones = element.findElement(By.xpath(".//td[6]")).getText();
      String address = element.findElement(By.xpath(".//td[4]")).getText();
      String allEmails = element.findElement(By.xpath(".//td[5]")).getText();

      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
      .withAllPhone(allPhones).withAddress(address).withAllEmails(allEmails));
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
String home = wd.findElement(By.name("home")).getAttribute("value");
String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
String work = wd.findElement(By.name("work")).getAttribute("value");
String address = wd.findElement(By.name("address")).getText();
String email = wd.findElement(By.name("email")).getAttribute("value");
String email2 = wd.findElement(By.name("email2")).getAttribute("value");
String email3 = wd.findElement(By.name("email3")).getAttribute("value");

wd.navigate().back();
return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).
        withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withAddress(address)
        .withEmail(email).withEmail2(email2).withEmail3(email3);

  }
}

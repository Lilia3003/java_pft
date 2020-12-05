package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.concurrent.TimeUnit;

public class DeleteContactTests extends TestBase {
  @Test
  public void testDeleteContact() throws InterruptedException {
    if (!app.getContactHelper().isThereAContact()) {

      app.getContactHelper().createContact(new ContactData("Name", "LastName", "address", "22-22-22", "test@mail.ru", "1"), true);
    }
    app.getNavigationHelper().returnToHomePage();
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().selectContact(before - 1);
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().acceptAlertDeletionContact();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before - 1);
  }

}

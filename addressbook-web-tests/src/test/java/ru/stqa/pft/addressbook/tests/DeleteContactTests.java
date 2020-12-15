package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class DeleteContactTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Name", "LastName", "address", "22-22-22", "test@mail.ru", "1"), true);
    }
  }

  @Test
  public void testDeleteContact() throws InterruptedException {
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();

    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().acceptAlertDeletionContact();

    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }

}

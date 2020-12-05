package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ModificationContactTests extends TestBase {

  @Test
  public void testContactModification() {
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Name", "LastName", "address", "22-22-22", "test@mail.ru", "1"), true);
    }
    app.getNavigationHelper().returnToHomePage();
    int before = app.getContactHelper().getContactCount();

    app.getContactHelper().initContactModification(before - 1);
    app.getContactHelper().fillContactForm(new ContactData("modfirstname", "modlastname", "modadress", "223322", "modemail", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();

    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);
  }
}

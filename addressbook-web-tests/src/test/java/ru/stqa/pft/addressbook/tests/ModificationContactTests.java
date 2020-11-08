package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ModificationContactTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("modfirstname", "modlastname", "modadress", "223322", "modemail"));
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();

  }
}

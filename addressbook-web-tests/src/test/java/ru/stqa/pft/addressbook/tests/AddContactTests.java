package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class AddContactTests extends TestBase {


  @Test
  public void testAddContact() throws Exception {

    app.getContactHelper().gotoAddNewContactPage();
    app.getContactHelper().fillContactForm(new ContactData("Name", "LastName", "address", "22-22-22", "test@mail.ru", "1"), true);
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().returnToHomePage();
  }

}

package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class AddContactTests extends TestBase {


  @Test
  public void testAddContact() throws Exception {

    gotoAddNewContactPage();
    fillContactForm(new ContactData("Name", "LastName", "address", "22-22-22", "test@mail.ru"));
    submitContactCreation();
    returnToHomePage();
  }

}

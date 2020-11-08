package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class DeleteContactTests extends TestBase {
  @Test
  public void testDeleteContact() {
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().acceptAlertDeletionContact();
  }

}

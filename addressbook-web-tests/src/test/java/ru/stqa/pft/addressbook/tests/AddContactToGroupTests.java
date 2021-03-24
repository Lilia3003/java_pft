package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactToGroupTests extends TestBase {

  @Test
  public void testAddContactToGroup() throws InterruptedException {
    app.goTo().HomePage();
    Contacts before = app.db().contacts();
    ContactData addedContact = before.iterator().next();

    app.contact().delete(addedContact);

    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(addedContact)));

    verifyContactListInUI();
  }
}

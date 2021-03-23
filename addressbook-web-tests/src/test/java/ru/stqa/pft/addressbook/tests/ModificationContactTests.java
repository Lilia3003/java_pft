package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ModificationContactTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if(app.db().contacts().size() == 0){
      app.contact().create(new ContactData().withFirstname("Name").withLastname("LastName").withAddress("address").withEmail("test@mail.ru")
              .withEmail2("Email2").withEmail3("Email3")
              .withHomePhone("home").withWorkPhone("work").withMobilePhone("mobile")
              .withGroup("1"), true);
    }
  }

  @Test
  public void testContactModification() throws InterruptedException {

    app.goTo().HomePage();
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("modfirstname").withLastname("modlastname").withAddress("modadress")
            .withEmail("modtest@mail.ru").withEmail2("modEmail2").withEmail3("modEmail3")
            .withHomePhone("modhome").withWorkPhone("modwork").withMobilePhone("modmobile").withGroup(null);
    app.contact().modify(contact);
    app.goTo().HomePage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));

    verifyContactListInUI();
  }


}

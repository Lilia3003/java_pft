package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ModificationContactTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Name").withLastname("LastName").withAddress("address").withPhone("22-22-22").withEmail("test@mail.ru").withGroup("1"), true);
    }
  }

  @Test
  public void testContactModification() throws InterruptedException {

    app.goTo().HomePage();
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData().
            withId(before.get(index).getId()).withFirstname("modfirstname").withLastname("modlastname").withAddress("modadress").withPhone("223322").withEmail("modemail").withGroup(null);
    app.contact().modify(index, contact);
    app.goTo().HomePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());
    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}

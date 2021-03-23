package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@Entity
@Table(name="addressbook")

@XStreamAlias("contact")

public class ContactData {
  @XStreamOmitField
  @Id
  @Column(name="id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name="firstname")
  private String firstname;

  @Expose
  @Column(name="lastname")
  private String lastname;

  @Expose
  @Column(name="address")
  @Type(type = "text")
  private String address;

  @Transient
  private String phone;

  @Expose
  @Column(name="email")
  @Type(type = "text")
  private String email;

  @Expose
  @Column(name="email2")
  @Type(type = "text")
  private String email2;

  @Expose
  @Column(name="email3")
  @Type(type = "text")
  private String email3;

  @Transient
  private String allEmails;

  @Expose
  @Column(name="home")
  @Type(type = "text")
  private String home;

  @Expose
  @Type(type = "text")
  @Column(name="mobile")
  private String mobile;

  @Expose
  @Type(type = "text")
  @Column(name="work")
  private String work;

  @Transient
  private String allPhones;

  @Transient
  private String group;

  @Transient
  @Column(name="photo")
  @Type(type = "text")
  private String photo;

  public int getId() {
    return id;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;

  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }


  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withHomePhone(String home) {
    this.home = home;
    return this;
  }

  public ContactData withMobilePhone(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public ContactData withWorkPhone(String work) {
    this.work = work;
    return this;
  }

  public ContactData withAllPhone(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }


  public String getFirstname() { return firstname; }

  public String getLastname() { return lastname; }

  public String getAddress() { return address; }

  public String getPhone() { return phone; }

  public String getEmail() { return email; }

  public String getEmail2() { return email2; }

  public String getEmail3() { return email3; }

  public String getAllEmails() { return allEmails; }

  public String getGroup() { return group; }

  public String getHome() { return home; }

  public String getMobile() { return mobile; }

  public String getWork() { return work; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstname, that.firstname) &&
            Objects.equals(lastname, that.lastname) &&
            Objects.equals(address, that.address) &&
            Objects.equals(email, that.email) &&
            Objects.equals(email2, that.email2) &&
            Objects.equals(email3, that.email3) &&
            Objects.equals(home, that.home) &&
            Objects.equals(mobile, that.mobile) &&
            Objects.equals(work, that.work);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname, address, email, email2, email3, home, mobile, work);
  }

  public String getAllPhones() { return allPhones; }

  public File getPhoto() { return new File(photo); }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", address='" + address + '\'' +
            ", email='" + email + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
            ", home='" + home + '\'' +
            ", mobile='" + mobile + '\'' +
            ", work='" + work + '\'' +
            '}';
  }

}


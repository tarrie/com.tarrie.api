package io.tarrie;

import io.tarrie.controller.Controller;
import io.tarrie.model.Location;
import io.tarrie.model.condensed.UserCondensed;
import io.tarrie.model.consumes.CreateGroup;
import io.tarrie.model.consumes.CreateUser;

import javax.mail.internet.AddressException;

public class Test {
  public static final String userName1 = "Jake Stricht";
  public static final String userEmail1= "jake@gmail.com";
  public static final String unformattedUserId1 = "northwestern_6960";
  public static final String formattedUserId1 = "USR#northwestern_6960";

  public static final String userName2 = "Becky B";
  public static final String userEmail2= "beck2020@u.northwestern.com";
  public static final String unformattedUserId2 = "beckb_triDelt";
  public static final String formattedUserId2 = "USR#beckb_triDelt";

  public static final String groupId1 = "boogoParty33333";
  public static final String formattedGroupId1 = "GRP#boogoParty33333";

  public static void main(String[] args) throws AddressException {
    //createDummyUsers();
    //createDummyGroup();
    //createDummyFollowers();
    createDummyContacts();

  }

  static void createDummyUsers() throws AddressException {
    CreateUser newUser1 = new CreateUser();
    newUser1.setId(unformattedUserId1);
    newUser1.setName(userName1);
    newUser1.setEmailAddr(userEmail1);

    CreateUser newUser2 = new CreateUser();
    newUser2.setId(unformattedUserId2);
    newUser2.setName(userName2);
    newUser2.setEmailAddr(userEmail2);

    Controller.createUser(newUser1);
    Controller.createUser(newUser2);
  }

  static void createDummyGroup(){
    UserCondensed owner = new UserCondensed();
    owner.setId(formattedUserId1);
    owner.setName(userName1);

    Location loc = new Location();
    loc.city="Evanston";
    loc.state="IL";
    loc.locName="Northwestern University";

    CreateGroup group = new CreateGroup();
    group.setGroupId(groupId1);
    group.setOwner(owner);
    group.setName("Boogo Party");
    group.setBio("Dude's who like to party");
    group.setLocation(loc);

    Controller.createGroup(group);
  }

  static void createDummyFollowers(){
    // group1 is following user2
    Controller.followEntity(formattedGroupId1,formattedUserId2);

    // user2 is following group1
    Controller.followEntity(formattedUserId2,formattedGroupId1);

    // user2 is following user1
    Controller.followEntity(formattedUserId2,formattedUserId1);

  }

  static void createDummyContacts(){
    // group1 is following user2
    Controller.addContact(formattedGroupId1,formattedUserId2);

    // user2 is following group1
    Controller.addContact(formattedUserId2,formattedGroupId1);

    // user2 is following user1
    Controller.addContact(formattedUserId2,formattedUserId1);

  }
}

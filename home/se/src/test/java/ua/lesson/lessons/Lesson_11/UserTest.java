package ua.lesson.lessons.Lesson_11;

import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void arrayContaints(){
        List<User> usersList=new ArrayList<User>();
        List<UserEquals> usersEqualsList=new ArrayList<UserEquals>();
        List<UserHash> usersHashList=new ArrayList<UserHash>();
        List<UserEqualsHash> usersEqualsHashList=new ArrayList<UserEqualsHash>();

        User u1=new User("John", "1", 20);
        User u2=new User("John", "1", 20);
        usersList.add(u1);
        usersList.add(u2);
        //hash and equals not overrided
        assertEquals(true, usersList.contains(u1));
        assertEquals(true, usersList.contains(u2));
        assertEquals(false, usersList.contains(new User("John", "1", 20)));
        assertEquals(2,usersList.size());

        UserEquals ue1=new UserEquals("John", "1", 20);
        UserEquals ue2=new UserEquals("John", "1", 20);
        usersEqualsList.add(ue1);
        usersEqualsList.add(ue2);
        //hash not overrided and equals  overrided
        assertEquals(true, usersEqualsList.contains(ue1));
        assertEquals(true, usersEqualsList.contains(ue2));
        assertEquals(true, usersEqualsList.contains(new UserEquals("John", "1", 20)));
        assertEquals(2,usersEqualsList.size());

        UserHash uh1=new UserHash("John", "1", 20);
        UserHash uh2=new UserHash("John", "1", 20);
        usersHashList.add(uh1);
        usersHashList.add(uh2);
        //hash overrided and equals not overrided
        assertEquals(true, usersHashList.contains(uh1));
        assertEquals(true, usersHashList.contains(uh2));
        assertEquals(false, usersHashList.contains(new UserHash("John", "1", 20)));
        assertEquals(2,usersHashList.size());


        UserEqualsHash ueh1=new UserEqualsHash("John", "1", 20);
        UserEqualsHash ueh2=new UserEqualsHash("John", "1", 20);
        usersEqualsHashList.add(ueh1);
        usersEqualsHashList.add(ueh2);
        //hash and equals overrided
        assertEquals(true, usersEqualsHashList.contains(ueh1));
        assertEquals(true, usersEqualsHashList.contains(ueh2));
        assertEquals(true, usersEqualsHashList.contains(new UserEqualsHash("John", "1", 20)));
        assertEquals(2,usersEqualsHashList.size());
    }

    @Test
    public void setAdd(){
        Set<User> usersSet=new HashSet<User>();
        Set<UserEquals> usersEqualsSet=new HashSet<UserEquals>();
        Set<UserHash> usersHashSet=new HashSet<UserHash>();
        Set<UserEqualsHash> usersEqualsHashSet=new HashSet<UserEqualsHash>();

        User u1=new User("John","1", 20);
        User u2=new User("John","1", 20);
        usersSet.add(u1);
        usersSet.add(u2);
        //Size will be 2 because addresses not equals
        assertEquals(2, usersSet.size());
        assertEquals(false, u1.equals(u2));


        UserEquals ue1=new UserEquals("James","1",20);
        UserEquals ue2=new UserEquals("James","1",20);
        usersEqualsSet.add(ue1);
        usersEqualsSet.add(ue2);
        //hash not override, adresses not equals
        assertEquals(2,usersEqualsSet.size());
        assertEquals(true, ue1.equals(ue2));

        UserHash uh1=new UserHash("James","1",20);
        UserHash uh2=new UserHash("James","1",20);
        usersHashSet.add(uh1);
        usersHashSet.add(uh2);
        //hash override , equals not override
        assertEquals(2,usersHashSet.size());
        assertEquals(true , uh1.hashCode()==uh2.hashCode());
        assertEquals(false, uh1.equals(uh2));

        UserEqualsHash ueh1=new UserEqualsHash("J","1", 20);
        UserEqualsHash ueh2=new UserEqualsHash("J","1", 20);
        usersEqualsHashSet.add(ueh1);
        usersEqualsHashSet.add(ueh2);
        //Hash and equals overrides
        assertEquals(1,usersEqualsHashSet.size());
        assertEquals(true , ueh1.hashCode()==ueh2.hashCode());
        assertEquals(true, ueh1.equals(ueh2));



    }

    @Test
    public void mapPut(){
        Map<UserEqualsHash, Integer> uehMap= new HashMap<UserEqualsHash, Integer>();
        UserEqualsHash ueh1=new UserEqualsHash("John", "1", 20);
        UserEqualsHash ueh2=new UserEqualsHash("John", "1", 20);
        uehMap.put(ueh1,1);
        uehMap.put(ueh2,2);
        assertEquals(1,uehMap.size());
        assertEquals(new Integer(2) ,uehMap.get(ueh1));


        Map<UserHash, Integer> uhMap= new HashMap<UserHash, Integer>();
        UserHash uh1=new UserHash("John", "1", 20);
        UserHash uh2=new UserHash("John", "1", 20);
        uhMap.put(uh1,1);
        uhMap.put(uh2,2);
        assertEquals(2,uhMap.size());
        assertEquals(new Integer(1) ,uhMap.get(uh1));

        Map<UserEquals, Integer> ueMap= new HashMap<UserEquals, Integer>();
        UserEquals ue1=new UserEquals("John", "1", 20);
        UserEquals ue2=new UserEquals("John", "1", 20);
        ueMap.put(ue2,2);
        ueMap.put(ue1,1);
        assertEquals(2,ueMap.size());
        assertEquals(new Integer(1) ,ueMap.get(ue1));

    }

}
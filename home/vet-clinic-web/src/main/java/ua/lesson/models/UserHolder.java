package ua.lesson.models;

import java.util.ArrayList;

/**
 * userclass with singletone
 */
public class UserHolder {
    public static ArrayList<User> users=new ArrayList<User>();
    private static int id=0;
    public static int generateId(){
        return id++;
    }

    public static void delete(int index){
        for(int i=0;i<users.size();i++){
            if(users.get(i).getId()== index){
                users.remove(i);
                break;
            }
        }
    }

    public static void edit(int index, String newName,String newEmail){
        for(int i=0;i<users.size();i++){
            if(users.get(i).getId()== index){
                users.get(i).setName(newName);
                users.get(i).setEmail(newEmail);
                break;
            }
        }
    }

    private UserHolder(){}


}

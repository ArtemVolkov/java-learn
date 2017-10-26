package ua.lesson.lessons.Lesson_11;

/**
 * Class for testing equals, hashCode, toString
 */
public class User {
    private int age;
    private String id;
    private String name;


    public User(String name, String id, int age){
        this.name=name;
        this.age=age;
        this.id=id;
    }

    public int getAge() {
        return age;
    }
    public String getId(){
        return id;
    }


    public String getName() {
        return name;
    }

}

/**
 * User with overrides Equals,HashCode
 */
class UserEqualsHash {
    private int age;
    private String id;
    private String name;


    public UserEqualsHash(String name, String id, int age){
        this.name=name;
        this.age=age;
        this.id=id;
    }

    public int getAge() {
        return age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object arg){
        if(this==arg) return true;
        if(!(arg instanceof UserEqualsHash)) return false;

        UserEqualsHash user=(UserEqualsHash)arg;
        boolean isEqual=false;
        if(this.getAge()==user.getAge() &&
                this.getName().equals(user.getName())){
            isEqual= true;
        }
        return isEqual;

    }

    @Override
    public int hashCode(){
        int result=id!=null? id.hashCode():0;
        //31 magic number
        result=31* result + (name!=null? name.hashCode():0);
        return result;
    }
}

class UserEquals {
    private int age;
    private String id;
    private String name;


    public UserEquals(String name, String id, int age){
        this.name=name;
        this.age=age;
        this.id=id;
    }

    public int getAge() {
        return age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object arg){
        if(this==arg) return true;
        if(!(arg instanceof UserEquals)) return false;

        UserEquals user=(UserEquals)arg;
        boolean isEqual=false;
        if(this.getAge()==user.getAge() &&
                this.getName().equals(user.getName())){
            isEqual= true;
        }
        return isEqual;

    }
}

class UserHash {
    private int age;
    private String id;
    private String name;


    public UserHash(String name, String id, int age){
        this.name=name;
        this.age=age;
        this.id=id;
    }

    public int getAge() {
        return age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode(){
        int result=id!=null? id.hashCode():0;
        //31 magic number
        result=31* result + (name!=null? name.hashCode():0);
        return result;
    }



}
package pssh.com.mule;

public class Employee {

    public Employee(Integer id, Integer age, String gender, String fName, String lName){
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.firstName = fName;
        this.lastName = lName;
    }

    private Integer id;
    private Integer age;
    private String gender;
    private String firstName;
    private String lastName;

    public Integer getId() {
        return id;
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    //Please generate Getter and Setters

    @Override
    public String toString() {
        return this.gender.toString()+" - "+this.age.toString()+" - "+this.firstName.toString()+" "+this.lastName.toString(); //To change body of generated methods, choose Tools | Templates.
    }
}

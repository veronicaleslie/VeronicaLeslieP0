package com.revature.bankapp.models;


public class Users {
    // Encapsulated these variables/attributes to the class or instance thereof
    // Another pillar of OOP Encapsulation
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String username;


//    public Trainer(){}

    // public is allowing any instance of class to leverage this command
    // This is a constructor because it's using the class name
    // This requires all atttributes defined above to be passed
    // This assigns each argument to their respective parameter variable being fname, lname, email, etc
    // We assign this instance of the objects the passed argumented.
    // So now, "this" is refering to the instance and we are setting this.fname to equal the passed arugment that was assign fname

    // Overloading constructors another subset of polymorphism
    public Users(String first_name, String last_name, String email, String password, String username) {
        super(); // just always there, by default of EVERY CLASS is Object
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public Users(String password){
        this.password = password;
    }

    // public is allowing any instance of class to leverage this command
    // This is a constructor because it's using the class name
    // this constructor requires no Arguments to be passed.
    public Users() {

    }


    // Getters & Setters
    public String getFirst_name() {
        return first_name;
    }

    // public is letting every instance of this class use the .setFname anywhere.
    // void - this means it will return nothing to me
    // setFname() - to reassign fname attribute
    // method takes in paramerters of String that will be assigned fname
    //this - is the keyword to indicate it's refering to the particular instance of that class
    // this.fname is refering to the attribute in that class
    // we are setting this objects fname attribute to equal the fname that has been provided as an argument
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    // public is letting every instance of this class use the .getLName anywhere.
    // returning a String value when called
    // getLname() is a method to retrieve this instances lname attribute
    // no arguments required
    // return a string which happens to be our attribute lname
    public String getLast_name() {
        return last_name;
    }

    // trainer.lname = "Jester" is bad, because you could reassign on accident if it were say and int and you did
    // trainer.age = 10;
    // This allows us to explicitly state we are setting the lname variablem, or reassigning it
    // Trainer trainer = new Trainer();
    // trainer.setLname("Jester")
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username= username;
    }

    public String toFileString() {
        // StringBuilder, there is also a StringBuffer (it's thread-safe)
        // Is another class for Strings that allows them to be mutated
        StringBuilder mutableString = new StringBuilder();
        mutableString
                .append(first_name).append(",")
                .append(last_name).append(",")
                .append(email).append(",")
                .append(password).append(",")
                .append(username);

        // Without changing the mutableString class from StringBuilder we wont' have an appropriate return type
        return mutableString.toString(); // We need the toString to return it to it's appropriate type
    }

    @Override // What this is?? Annotation - basically metadata
    public String toString() {
        return "User{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                '}';
    }


}
package com.revature.pokedex.models;

public class Trainer {

    private String fname;
    private String lname;
    private String email;
    private String password;
    private String dob;

//    public Trainer(){}

    public Trainer(String fname, String lname, String email, String password, String dob) {
        super(); // just always there, by default of EVERY CLASS is Object
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.dob = dob;
    }

    public Trainer() {

    }

    // Getters & Setters
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    // trainer.lname = "Jester" is bad, because you could reassign on accident if it were say and int and you did
    // trainer.age = 10;
    // This allows us to explicitly state we are setting the lname variablem, or reassigning it
    // Trainer trainer = new Trainer();
    // trainer.setLname("Jester")
    public void setLname(String lname) {
        this.lname = lname;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String toFileString() {
        // StringBuilder, there is also a StringBuffer (it's thread-safe)
        // Is another class for Strings that allows them to be mutated
        StringBuilder mutableString = new StringBuilder();
        mutableString
                .append(fname).append(",")
                .append(lname).append(",")
                .append(email).append(",")
                .append(password).append(",")
                .append(dob);

        // Without changing the mutableString class from StringBuilder we wont' have an appropriate return type
        return mutableString.toString(); // We need the toString to return it to it's appropriate type
    }

    @Override // What this is?? Annotation - basically metadata
    public String toString() {
        return "Trainer{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }


}

package ex3;

import java.time.LocalDate;

public class Employee {
    String name;
    LocalDate birthday;
    public Employee(String name, LocalDate birthday) {
        this.name = name;
        this.birthday = birthday;
    }
    public Employee(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public LocalDate getBirthday() {
        return birthday;
    }
    public String toString() {
        return "Employee{name='" + name + "', birthday=" + birthday + "}";
    }
}


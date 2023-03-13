package org.example;

public class Student {

    int id;
    String lastName;
    String firstName;
    int age;
    int studentGroup;
    String gender;
    String idCity;

    public Student() {}

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public int getStudentGroup() { return studentGroup; }
    public void setStudentGroup(int studentGroup) { this.studentGroup = studentGroup; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getIdCity() { return idCity; }
    public void setIdCity(String idCity) { this.idCity = idCity; }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" +
                "id: " + id +
                ", lastName: '" + lastName + '\'' +
                ", firstName: '" + firstName + '\'' +
                ", age: " + age +
                ", studentGroup: '" + studentGroup +
                ", gender: '" + gender + '\'' +
                ", idCity: '" + idCity + '\'' +
                '}';
    }
}

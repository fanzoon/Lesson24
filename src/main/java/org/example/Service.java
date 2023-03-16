package org.example;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Service {
    public static final String URL = "jdbc:mysql://localhost:3306/lesson24";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "admin";

    public List<Student> getAllStudents() {
        List<Student> studentList = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT " +
                    "id_student ," +
                    "last_name, " +
                    "first_name, " +
                    "age, " +
                    "student_group, " +
                    "gender, " +
                    "city_name " +
                    "FROM students2 JOIN cities ON id_city = id;");
            studentList = new ArrayList<>();
            Student student = new Student();
            System.out.println("Список студентов:");
            while (resultSet.next()) {
                student.setId(resultSet.getInt("id_student"));
                student.setLastName(resultSet.getString("last_name"));
                student.setFirstName(resultSet.getString("first_name"));
                student.setAge(resultSet.getInt("age"));
                student.setStudentGroup(resultSet.getInt("student_group"));
                student.setGender(resultSet.getString("gender"));
                student.setIdCity(resultSet.getString("city_name"));
                studentList.add(student);
                System.out.println(student);
            }
        } catch (SQLException e) {
            System.out.println("Exception!!!");
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println("resultSet не закрыт");
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("statement не закрыт");
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("connection не закрыт");
                }
            }
        }
        return studentList;
    }

    public static void getAllCities() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            preparedStatement = connection.prepareStatement("select * from cities;");
            resultSet = preparedStatement.executeQuery();
            System.out.println("Список городов:");
            while (resultSet.next()) {
                System.out.print("id: " + resultSet.getInt("id") + "; ");
                System.out.println("cityName: " + resultSet.getString("city_name"));
            }
        } catch (SQLException e) {
            System.out.println("Exception!!!");
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println("resultSet не закрыт");
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.out.println("statement не закрыт");
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("connection не закрыт");
                }
            }
        }
    }

    public void addStudent() {
        Scanner scanner = new Scanner(System.in);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Service resources = new Service();
        System.out.println("Введите данные о студенте, которого хотите добавить в таблицу 'students2':");
        System.out.println("Введите фамилию студента:");
        String lastName = scanner.nextLine();
        System.out.println("Введите имя студента:");
        String firstName = scanner.nextLine();
        System.out.println("Введите возраст студента:");
        int age = scanner.nextInt();
        System.out.println("Введите группу студента:");
        int studentGroup = scanner.nextInt();
        System.out.println("Введите пол студента:");
        String gender = scanner.next();
        System.out.println("Введите id города, где проживает студент:");
        int idCity = scanner.nextInt();
        String query = "INSERT INTO students2 (last_name, first_name, age, student_group, gender, id_city) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, firstName);
            preparedStatement.setInt(3, age);
            preparedStatement.setInt(4, studentGroup);
            preparedStatement.setString(5, gender);
            preparedStatement.setInt(6, idCity);
            preparedStatement.executeUpdate();
            System.out.println("Студент  " + lastName + " "+ firstName + " добавлен в таблицу 'students2'");
            resources.getAllStudents();
        } catch (SQLException e) {
            System.out.println("Exception!!!");
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.out.println("statement не закрыт");
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("connection не закрыт");
                }
            }
        }
    }

    public void addCity() {
        Scanner scanner = new Scanner(System.in);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        System.out.println("Введите название города, который хотите добавить в таблицу 'cities':");
        String cityName = scanner.nextLine();
        String query = "insert into cities (city_name) values (?)";
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, cityName);
            preparedStatement.executeUpdate();
            System.out.println("Город " + cityName + " добавлен в таблицу 'cities'");
            Service.getAllCities();
        } catch (SQLException e) {
            System.out.println("Exception!!!");
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.out.println("statement не закрыт");
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("connection не закрыт");
                }
            }
        }
    }

    public void deleteStudent() {
        Scanner scanner = new Scanner(System.in);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Service resources = new Service();
        System.out.println("Введите id студента, которого хотите удалить из таблицы 'students2':");
        int id = scanner.nextInt();
        String query = "DELETE FROM students2 where id_student = ?";
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Студете с id_student: " + id + " удален из таблицы 'students2'");
            resources.getAllStudents();
        } catch (SQLException e) {
            System.out.println("Exception!!!");
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.out.println("statement не закрыт");
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("connection не закрыт");
                }
            }
        }
    }

    public void deleteCity() {
        Scanner scanner = new Scanner(System.in);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        System.out.println("Введите id города, который хотите удалить из таблицы 'cities':");
        int id = scanner.nextInt();
        String query = "DELETE FROM cities where id = ?";
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Город с id: " + id + " удален из таблицу 'cities'");
            Service.getAllCities();
        } catch (SQLException e) {
            System.out.println("Exception!!!");
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.out.println("statement не закрыт");
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("connection не закрыт");
                }
            }
        }
    }
}

//Exercise 21: Custom Exception

class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}
class AgeValidator {
    static void validateAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age must be 18 or above.");
        }
        System.out.println("Age is valid.");
    }

    public static void main(String[] args) {
        try {
            validateAge(16);
        } catch (InvalidAgeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

//Exercise 22: File Writing

import java.io.*;
import java.util.Scanner;

public class FileWriteExample {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text to write to file: ");
        String text = sc.nextLine();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            writer.write(text);
        }
        System.out.println("Data written to output.txt successfully.");
        sc.close();
    }
}

//Exercise 23: File Reading

import java.io.*;

public class FileReadExample {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("output.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}


//Exercise 24: ArrayList Example

import java.util.ArrayList;
import java.util.Scanner;

public class StudentList {
    public static void main(String[] args) {
        ArrayList<String> students = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter student names (type 'end' to stop):");
        String name;
        while (!(name = sc.nextLine()).equalsIgnoreCase("end")) {
            students.add(name);
        }
        System.out.println("Student Names:");
        students.forEach(System.out::println);
        sc.close();
    }
}


//Exercise 25: HashMap Example

import java.util.*;

public class StudentMap {
    public static void main(String[] args) {
        Map<Integer, String> students = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter student ID and name (type -1 to stop):");
        while (true) {
            System.out.print("ID: ");
            int id = sc.nextInt();
            sc.nextLine(); // consume newline
            if (id == -1) break;
            System.out.print("Name: ");
            String name = sc.nextLine();
            students.put(id, name);
        }
        System.out.print("Enter ID to lookup: ");
        int lookupId = sc.nextInt();
        String student = students.get(lookupId);
        System.out.println("Student: " + (student != null ? student : "Not Found"));
        sc.close();
    }
}

//Exercise 26: Thread Creation

class MyThread extends Thread {
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getName() + " is running.");
    }

    public static void main(String[] args) {
        Thread t1 = new MyThread();
        Thread t2 = new MyThread();
        t1.setName("Thread-1");
        t2.setName("Thread-2");
        t1.start();
        t2.start();
    }
}


//Exercise 27: Lambda Expressions

import java.util.*;

public class LambdaSort {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(Arrays.asList("John", "Alice", "Bob", "David"));
        names.sort(String::compareToIgnoreCase);
        names.forEach(name -> System.out.println(name));
    }
}

//Exercise 28: Stream API

import java.util.*;
import java.util.stream.*;

public class StreamFilter {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(10, 15, 20, 25, 30);
        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        evenNumbers.forEach(n -> System.out.println(n));
    }
}


//Exercise 29: Records

record Person(String name, int age) {}

import java.util.*;

public class RecordExample {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Alice", 22),
                new Person("Bob", 30),
                new Person("Eve", 18)
        );
        people.stream()
                .filter(person -> person.age() >= 21)
                .forEach(person -> System.out.println(person));
    }
}

//Exercise 30: Pattern Matching for switch (Java 21)

public class PatternSwitch {
    public static void checkType(Object obj) {
        switch (obj) {
            case String str -> System.out.println("This is a String: " + str);
            case Integer num -> System.out.println("This is an Integer: " + num);
            case Double decimal -> System.out.println("This is a Double: " + decimal);
            default -> System.out.println("Unknown data type");
        }
    }

    public static void main(String[] args) {
        checkType("Hello");
        checkType(123);
        checkType(3.14);
        checkType(true);
    }
}

//Exercise 31: Basic JDBC Connection

import java.sql.*;

public class JDBCConnect {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:students.db";
        String query = "SELECT * FROM students";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " - " + rs.getString("name"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}

//Exercise 32: Insert and Update Operations in JDBC

import java.sql.*;

public class StudentDAO implements AutoCloseable {
    private Connection conn;

    public StudentDAO() throws SQLException {
        conn = DriverManager.getConnection("jdbc:sqlite:students.db");
    }

    public void insertStudent(int id, String name) throws SQLException {
        String query = "INSERT INTO students (id, name) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.executeUpdate();
        }
    }

    public void updateStudent(int id, String name) throws SQLException {
        String query = "UPDATE students SET name = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    @Override
    public void close() throws SQLException {
        conn.close();
    }
}


// Exercise 33: Transaction Handling in JDBC

import java.sql.*;

public class BankTransfer {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:bank.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            conn.setAutoCommit(false);
            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("UPDATE accounts SET balance = balance - 100 WHERE id = 1");
                stmt.executeUpdate("UPDATE accounts SET balance = balance + 100 WHERE id = 2");
                conn.commit();
                System.out.println("Transaction successful");
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Transaction failed: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}


//Exercise 34: Create and Use Java Modules
com.utils module-info.java:

module utils {
    exports com.utils.math;
    exports com.utils.string;
}


com.greetings module-info.java:

module greetings {
    requires utils;
}



//Exercise 35: TCP Client-Server Chat
Server:

import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(1234);
             Socket socket = server.accept();
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            out.println("Hello client!");
            System.out.println("Client: " + in.readLine());
        }
    }
}


Client:

import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 1234);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            System.out.println("Server: " + in.readLine());
            out.println("Hello server!");
        }
    }
}


//Exercise 36: HTTP Client API (Java 11+)

import java.net.http.*;
import java.net.URI;
import java.io.IOException;

public class HttpExample {
    public static void main(String[] args) throws IOException, InterruptedException {
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.github.com"))
                .header("Accept", "application/json")
                .build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("HTTP Status: " + response.statusCode());
        System.out.println("Response Body: " + response.body());
    }
}

//Exercise 37: Using javap to Inspect Bytecode
Demo.java

public class Demo {
    public static int calculateSquare(int number) {
        return number * number;
    }

    public static void main(String[] args) {
        int result = calculateSquare(5);
        System.out.println(result);
    }
}


Compilation and Inspection Commands:

bash
javac Demo.java
javap -c Demo


Sample Output (Bytecode):

Compiled from "Demo.java"
public class Demo {
  public Demo();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static int calculateSquare(int);
    Code:
       0: iload_0
       1: iload_0
       2: imul
       3: ireturn

  public static void main(java.lang.String[]);
    Code:
       0: iconst_5
       1: invokestatic  #2                  // Method calculateSquare:(I)I
       4: istore_1
       5: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
       8: iload_1
       9: invokevirtual #4                  // Method java/io/PrintStream.println:(I)V
      12: return
}


//Exercise 38: Decompile a Class File
Hello.java

public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}


Compilation:

bash
javac Hello.java


Decompilation using CFR:

bash
java -jar cfr.jar Hello.class


The decompiled code will be:

public class Hello {
    public static void main(String[] var0) {
        System.out.println("Hello World");
    }
}
// Exercise 39: Reflection in Java

import java.lang.reflect.*;

public class ReflectionDemo {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> clazz = String.class;
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
    }
}

//Exercise 40: Virtual Threads (Java 21)
public class VirtualThreads {
    public static void main(String[] args) {
        for (int i = 0; i < 100_000; i++) {
            Thread.startVirtualThread(VirtualThreads::printMessage);
        }
    }

    private static void printMessage() {
        System.out.println("Running virtual thread");
    }
}


//Exercise 41: ExecutorService and Callable
import java.util.concurrent.*;
import java.util.*;

public class CallableExample {
    public static void main(String[] args) {
        try (ExecutorService executor = Executors.newFixedThreadPool(3)) {
            List<Callable<String>> tasks = List.of(
                () -> "Task 1",
                () -> "Task 2",
                () -> "Task 3"
            );
            executor.invokeAll(tasks).forEach(future -> {
                try {
                    System.out.println(future.get());
                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
            });
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

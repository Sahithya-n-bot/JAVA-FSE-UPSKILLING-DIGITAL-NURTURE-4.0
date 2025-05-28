// Exercise 1: Hello World
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}

// Exercise 2: Simple Calculator

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Simple Calculator");
        System.out.println("-----------------");
        System.out.print("Enter first number: ");
        double a = sc.nextDouble();
        System.out.print("Enter second number: ");
        double b = sc.nextDouble();
        System.out.print("Choose operation (+ - * /): ");
        char op = sc.next().charAt(0);
        double result = switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> b != 0 ? a / b : Double.NaN;
            default -> {
                System.out.println("Invalid operator");
                return;
            }
        };
        System.out.println("Result: " + a + " " + op + " " + b + " = " + result);
        sc.close();
    }
}


// Exercise 3: Even or Odd Checker

import java.util.Scanner;

public class EvenOdd {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int num = sc.nextInt();
        String result = (num % 2 == 0) ? "Even" : "Odd";
        System.out.println("The number is: " + result);
        sc.close();
    }
}


// Exercise 4: Leap Year Checker

import java.util.Scanner;

public class LeapYear {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a year: ");
        int year = sc.nextInt();
        String result = ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) ? "Leap Year" : "Not a Leap Year";
        System.out.println(result);
        sc.close();
    }
}


// Exercise 5: Multiplication Table

import java.util.Scanner;

public class MultiplicationTable {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = sc.nextInt();
        System.out.println("Multiplication Table of " + num + ":");
        for (int i = 1; i <= 10; i++) {
            System.out.printf("%d x %d = %d\n", num, i, num * i);
        }
        sc.close();
    }
}


// Exercise 6: Data Type Demonstration

public class DataTypes {
    public static void main(String[] args) {
        int i = 10;
        float f = 3.14f;
        double d = 3.14159;
        char c = 'A';
        boolean b = true;

        System.out.println("Primitive Data Types:");
        System.out.println("---------------------");
        System.out.println("int: " + i);
        System.out.println("float: " + f);
        System.out.println("double: " + d);
        System.out.println("char: " + c);
        System.out.println("boolean: " + b);
    }
}


// Exercise 7: Type Casting

public class Typecasting {
    public static void main(String[] args) {
        double d = 9.99;
        int i = (int) d;
        System.out.println("Double to int: " + d + " -> " + i);

        int j = 7;
        double dj = (double) j; 
        System.out.println("Int to double: " + j + " -> " + dj);
    }
}


// Exercise 8: Operator Precedence

public class OperatorPrecedence {
    public static void main(String[] args) {
        int result = 10 + 5 * 2;
        System.out.println("Expression: 10 + 5 * 2");
        System.out.println("Result: " + result);
    }
}


// Exercise 9: Grade Calculator

import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter marks (0-100): ");
        int marks = sc.nextInt();
        char grade = switch (marks / 10) {
            case 10, 9 -> 'A';
            case 8 -> 'B';
            case 7 -> 'C';
            case 6 -> 'D';
            default -> 'F';
        };
        System.out.println("Grade: " + grade);
        sc.close();
    }
}


// Exercise 10: Number Guessing Game

import java.util.*;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int target = rand.nextInt(100) + 1;
        int guess;
        do {
            System.out.print("Guess the number (1-100): ");
            guess = sc.nextInt();
            System.out.println(guess < target ? "Too low" : guess > target ? "Too high" : "Correct!");
        } while (guess != target);
        sc.close();
    }
}

}

// Exercise 11: Factorial Calculator

import java.util.Scanner;

public class Factorial {
    public static int factorial(int n) {
        return n == 0 ? 1 : n * factorial(n - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = sc.nextInt();
        System.out.println(num + "! = " + factorial(num));
        sc.close();
    }
}


// Exercise 12: Method Overloading

public class MethodOverload {
    static int add(int a, int b) {
        return a + b;
    }

    static double add(double a, double b) {
        return a + b;
    }

    static int add(int a, int b, int c) {
        return a + b + c;
    }

    public static void main(String[] args) {
        System.out.println("2 + 3 = " + add(2, 3));
        System.out.println("2.5 + 3.5 = " + add(2.5, 3.5));
        System.out.println("1 + 2 + 3 = " + add(1, 2, 3));
    }
}


// Exercise 13: Recursive Fibonacci

import java.util.Scanner;

public class FibonacciRecursive {
    static int fibonacci(int n) {
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n for Fibonacci sequence: ");
        int n = sc.nextInt();
        System.out.println("Fibonacci number at position " + n + ": " + fibonacci(n));
        sc.close();
    }
}


// Exercise 14: Array Sum and Average

import java.util.Scanner;

public class ArraySumAverage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            System.out.print("Enter element " + (i + 1) + ": ");
            arr[i] = sc.nextInt();
            sum += arr[i];
        }
        double avg = (double) sum / n;
        System.out.println("Sum of elements: " + sum);
        System.out.println("Average of elements: " + avg);
        sc.close();
    }
}


// Exercise 15: String Reversal

import java.util.Scanner;

public class ReverseString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string to reverse: ");
        String input = sc.nextLine();
        StringBuilder sb = new StringBuilder(input);
        System.out.println("Original: " + input);
        System.out.println("Reversed: " + sb.reverse());
        sc.close();
    }
}

// Exercise 16: Palindrome Checker

import java.util.Scanner;

public class PalindromeString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string to check: ");
        String str = sc.nextLine().replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String rev = new StringBuilder(str).reverse().toString();
        System.out.println("String: " + str);
        System.out.println("Reversed: " + rev);
        System.out.println(str.equals(rev) ? "Result: Palindrome" : "Result: Not a Palindrome");
        sc.close();
    }
}


// Exercise 17: Class and Object Creation

class Car {
    String make, model;
    int year;

    Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    void displayDetails() {
        System.out.println("Car Details:");
        System.out.println("Make: " + make);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
    }

    public static void main(String[] args) {
        Car car1 = new Car("Toyota", "Corolla", 2022);
        System.out.print("Car Information: ");
        car1.displayDetails();
    }
}

// Exercise 18: Inheritance Example

class Animal {
    void makeSound() {
        System.out.println("Some sound");
    }
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Bark");
    }

    public static void main(String[] args) {
        Animal a = new Animal();
        System.out.print("Animal sound: ");
        a.makeSound();
        Dog d = new Dog();
        System.out.print("Dog sound: ");
        d.makeSound();
    }
}

// Exercise 19: Interface Implementation

interface Playable {
    void play();
}

class Guitar implements Playable {
    public void play() {
        System.out.println("Strumming the guitar");
    }
}

class Piano implements Playable {
    public void play() {
        System.out.println("Playing the piano");
    }

    public static void main(String[] args) {
        Playable guitar = new Guitar();
        Playable piano = new Piano();
        System.out.println("Music Time!");
        guitar.play();
        piano.play();
        System.out.println("Music session ended.");
    }
}


// Exercise 20: Try-Catch Example

import java.util.Scanner;

public class TryCatchExample {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Division Program");
        try {
            System.out.print("Enter numerator: ");
            int a = sc.nextInt();
            System.out.print("Enter denominator: ");
            int b = sc.nextInt();
            System.out.println("Result of division: " + (a / b));
        } catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero.");
        }
        System.out.println("Program execution completed.");
        sc.close();
    }
}

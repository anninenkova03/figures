# Figure Generator: A Design Patterns Project

This document provides a detailed overview of the "Figures" Java project, created as part of a software design patterns course. It details its structure, features, and the key patterns that form its architectural foundation.

---

## 1. Project Overview

The Figure Generator is a command-line application that creates and manages a collection of geometric figures, such as circles, rectangles, and triangles. The application is designed to be highly extensible, allowing for new figure types and creation methods to be added with minimal changes to the existing code.

### Core Features
* **Create figures** from various sources:
    * Randomly generated dimensions.
    * User input via the standard input (STDIN).
    * Reading from a text file.
* **Manage a list** of created figures through an interactive menu:
    * List all figures.
    * Delete a specific figure.
    * Duplicate an existing figure.
    * Save the current list of figures to a file.

---

## 2. Project Structure

The project is organized into logical packages to separate concerns:

* `figures`: Contains the `Figure` interface and its concrete implementations (`Circle`, `Rectangle`, `Triangle`). This package defines the "products" of the application.
* `factories`: Contains all classes related to the creation of `Figure` objects. This is where the creational design patterns are implemented.
* `Main.java`: The entry point of the application, responsible for handling user interaction and orchestrating the creation and management of figures.
* `test/java`: Contains a comprehensive suite of JUnit tests for all classes, ensuring correctness and robustness.



---

## 3. Design Patterns in Use

The project's architecture heavily relies on several fundamental design patterns to achieve its goals of flexibility, decoupling, and maintainability.

### Abstract Factory

The **Abstract Factory** pattern encapsulates the logic for creating other factories. It provides a static method that returns an instance of one of several possible factories based on input parameters, hiding the instantiation logic from the client.

* **Implementation**: The `AbstractFigureFactory` class acts as an Abstract Factory. Its static method `getFactory(String type)` takes a string (`"random"`, `"stdin"`, or `"file"`) and returns the appropriate concrete `FigureFactory` implementation (`RandomFigureFactory` or `StreamFigureFactory`).

    ```java
    // In factories/AbstractFigureFactory.java
    public class AbstractFigureFactory {
        public static FigureFactory getFactory(String type) {
            switch (type.trim().toLowerCase()) {
                case "random":
                    return new RandomFigureFactory();
                case "stdin":
                    return new StreamFigureFactory(System.in);
                case "file":
                String path = promptForFilePath();
                try {
                    InputStream fileStream = new FileInputStream(path);
                    return new StreamFigureFactory(fileStream);
                } catch (Exception e) {
                    throw new RuntimeException("Error opening file: " + path, e);
                }
                default:
                    throw new IllegalArgumentException("Unknown input type: " + type);
            }
        }
    }
    ```

* **Purpose**: This pattern **decouples the client** (`Main` class) from the concrete factory implementations. The `Main` class doesn't need to know *how* to construct a `RandomFigureFactory` or a `StreamFigureFactory`; it only needs to ask the `AbstractFigureFactory` for a generic factory. This makes it easy to add new figure creation methods in the future without modifying the client code.

### Factory Method

The broader structure is an application of the **Factory Method** pattern. This pattern defines an interface for creating an object but lets subclasses decide which class to instantiate.

* **Implementation**:
    1.  The `FigureFactory` interface declares the factory method `create()`.
    2.  Concrete factory classes (`RandomFigureFactory`, `StreamFigureFactory`) implement the `create()` method, each providing a different way to construct a `Figure` object.

    ```java
    // The "Creator" interface
    public interface FigureFactory {
        Figure create();
    }

    // A "Concrete Creator"
    public class RandomFigureFactory implements FigureFactory {
        @Override
        public Figure create() {
            // Logic to create a random figure...
        }
    }
    ```

* **Purpose**: This pattern allows the `Main` class to work with the abstract `FigureFactory` interface, delegating the responsibility of *how* a figure is created to the concrete subclasses. This aligns with the **Open/Closed Principle**, as the system is open to extension (new factories can be added) but closed for modification (the client code doesn't change).

### Prototype

The **Prototype** pattern is a creational pattern used to create new objects by copying an existing object, known as the prototype. This is useful when the cost of creating an object from scratch is more expensive than cloning it.

* **Implementation**:
    1.  The `Figure` interface extends Java's built-in `Cloneable` interface and declares a `clone()` method.
    2.  Each concrete class (`Circle`, `Rectangle`, `Triangle`) provides a public implementation of the `clone()` method.

    ```java
    // In figures/Figure.java
    public interface Figure extends Cloneable {
        Figure clone();
        // ... other methods
    }

    // In figures/Rectangle.java
    public class Rectangle implements Figure {
        @Override
        public Rectangle clone() {
            try {
                return (Rectangle) super.clone();
            } catch (CloneNotSupportedException e) {
                // This should never happen since we implement Cloneable
                throw new AssertionError("Cloning not supported", e);
            }
        }
    }
    ```
  The "Duplicate a figure" feature in the `Main` class directly uses this pattern:

    ```java
    // In Main.java
    Figure copy = figures.get(index).clone();
    figures.add(copy);
    ```
* **Purpose**: This pattern allows the application to easily create an exact copy of any figure object without being coupled to its concrete class. The client simply calls `clone()` on a `Figure` object, and the object itself handles the duplication.

---

## 4. Reflection for Dynamic Instantiation

A notable implementation detail is the use of **Java Reflection** in `RandomFigureFactory` and `StringToFigure`. Reflection allows the program to inspect and manipulate classes at runtime.

* In `RandomFigureFactory`, the `org.reflections` library dynamically finds all classes that implement the `Figure` interface. This means you can add a new figure class (e.g., `Pentagon.java`) to the `figures` package, and the `RandomFigureFactory` will **automatically discover and use it** without any code changes.
* In `StringToFigure`, `Class.forName()` and `ConstructorUtils.invokeConstructor()` are used to instantiate a figure class based on a string name (e.g., "triangle"). This allows the factory to parse any valid figure type from a string, making the system highly extensible.

## 5. References

[Abstract Factory Pattern – Wikipedia](https://en.wikipedia.org/wiki/Abstract_factory_pattern)  
[Factory (object-oriented programming) – Wikipedia](https://en.wikipedia.org/wiki/Factory_(object-oriented_programming))  
[Prototype Pattern – Wikipedia](https://en.wikipedia.org/wiki/Prototype_pattern)  
[Scanner (Java Platform SE 7) – Oracle Docs](https://docs.oracle.com/javase/7/docs/api/java/util/Scanner.html)  
[Java Scanner – Baeldung](https://www.baeldung.com/java-scanner)  
[Java User Input – W3Schools](https://www.w3schools.com/java/java_user_input.asp)  
[BufferedReader vs. Console vs. Scanner in Java – Baeldung](https://www.baeldung.com/bufferedreader-vs-console-vs-scanner-in-java)  
[ConstructorUtils (Apache Commons Lang 3.12.0 API)](https://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/reflect/ConstructorUtils.html)
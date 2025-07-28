import factories.AbstractFigureFactory;
import factories.FigureFactory;
import figures.Figure;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose input method (Random / STDIN / File): ");
        String inputMethod = scanner.nextLine();

        FigureFactory factory;
        try {
            factory = AbstractFigureFactory.getFactory(inputMethod);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return;
        }

        System.out.print("Enter number of figures to read: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        if (n < 0) {
            System.err.println("Number of figures must be nonnegative.");
            return;
        }

        List<Figure> figures = new ArrayList<Figure>();

        for (int i = 0; i < n; i++) {
            try {
                Figure figure = factory.create();
                if (figure != null) {
                    figures.add(figure);
                } else {
                    System.out.println("No more figures in source");
                    break;
                }
            } catch (Exception e) {
                System.out.println("Failed to create figure: " + e.getMessage());
                i--;
            }
        }

        while (true) {
            System.out.println("Menu");
            System.out.println("1. List all figures");
            System.out.println("2. Delete a figure");
            System.out.println("3. Duplicate a figure");
            System.out.println("4. Save figures to a file");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    if (figures.isEmpty()) {
                        System.out.println("Nothing to list");
                    } else {
                        listFigures(figures, System.out);
                    }
                    break;
                case "2":
                    deleteFigure(figures, scanner);
                    break;
                case "3":
                    duplicateFigure(figures, scanner);
                    break;
                case "4":
                    System.out.print("Enter output file name: ");
                    String path = scanner.nextLine();
                    try {
                        listFigures(figures, new PrintStream(new FileOutputStream(path)));
                        System.out.println("Successfully saved figures to file: " + path);
                    } catch (Exception _) {
                        System.out.println("Error opening file: " + path);
                    }
                    break;
                case "0":
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void listFigures(List<Figure> figures, PrintStream out) {
        for (int i = 0; i < figures.size(); i++) {
            out.println((i + 1) + ". " + figures.get(i));
        }
    }

    private static void deleteFigure(List<Figure> figures, Scanner scanner) {
        if (figures.isEmpty()) {
            System.out.println("Nothing to delete");
            return;
        }

        listFigures(figures, System.out);
        System.out.print("Enter index of figure to delete: ");
        try {
            int index = scanner.nextInt() - 1;
            scanner.nextLine();
            figures.remove(index);
            System.out.println("The figure has been deleted successfully");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index");
        }
    }

    private static void duplicateFigure(List<Figure> figures, Scanner scanner) {
        if (figures.isEmpty()) {
            System.out.println("Nothing to duplicate");
        }

        listFigures(figures, System.out);
        System.out.print("Enter index of figure to duplicate: ");
        try {
            int index = scanner.nextInt() - 1;
            scanner.nextLine();
            Figure copy = figures.get(index).clone();
            figures.add(copy);
            System.out.println("The figure has been duplicated successfully");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index");
        }
    }
}

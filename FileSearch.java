package ETSN20_lab4;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileSearch {
    public static void main(String[] args) {
        // Check if at least two arguments are provided (pattern and file path)
        if (args.length < 2) {
            System.out.println("Usage: search [-i] <pattern> <file>");
            return;
        }

        boolean ignoreCase = false;
        String pattern;
        String filePath;

        // Handle optional -i flag
        if (args[0].equals("-i")) {
            ignoreCase = true;
            if (args.length < 3) {
                System.out.println("Usage: search [-i] <pattern> <file>");
                return;
            }
            pattern = args[1];
            filePath = args[2];
        } else {
            pattern = args[0];
            filePath = args[1];
        }

        // Read the file and search for the pattern
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            searchInFile(lines, pattern, ignoreCase);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void searchInFile(List<String> lines, String pattern, boolean ignoreCase) {
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            boolean match = ignoreCase ? line.toLowerCase().contains(pattern.toLowerCase()) : line.contains(pattern);

            if (match) {
                System.out.printf("Line %d: %s%n", i + 1, line);
            }
        }
    }
}


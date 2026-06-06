import java.util.Scanner;

public class TemperatureConverter {

    public static double convertTemperature(double temperature, String unit) {
        if (unit.equalsIgnoreCase("C")) {
            return (temperature * 9.0 / 5.0) + 32.0;
        } else {
            return (temperature - 32.0) * 5.0 / 9.0;
        }
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        String input;
        String unit;
        double temp;
        double converted;
        boolean validNumber;
        boolean validUnit;
        boolean running = true;

        while (running) {
            System.out.print("Enter a temperature (or 'stop' to quit): ");
            input = scnr.next();

            if (input.equalsIgnoreCase("stop")) {
                running = false;
            } else {
                validNumber = true;
                int startIndex = 0;

                if (input.charAt(0) == '-') {
                    if (input.length() == 1) {
                        validNumber = false;
                    } else {
                        startIndex = 1;
                    }
                }

                int dotCount = 0;
                int i = startIndex;
                while (i < input.length() && validNumber) {
                    char c = input.charAt(i);
                    if (c == '.') {
                        dotCount++;
                        if (dotCount > 1) {
                            validNumber = false;
                        }
                    } else if (c < '0' || c > '9') {
                        validNumber = false;
                    }
                    i++;
                }

                if (!validNumber) {
                    System.out.println("Error: Invalid temperature input. Please enter a numeric value.");
                } else {
                    temp = Double.parseDouble(input);
                    validUnit = false;

                    while (!validUnit) {
                        System.out.print("Enter unit (C or F): ");
                        unit = scnr.next();

                        if (!unit.equalsIgnoreCase("C") && !unit.equalsIgnoreCase("F")) {
                            System.out.println("Error: Unrecognized unit '" + unit + "'. Please enter C or F.");
                        } else {
                            validUnit = true;
                            converted = convertTemperature(temp, unit);
                            if (unit.equalsIgnoreCase("C")) {
                                System.out.printf("%.2f\u00b0C is equal to %.2f\u00b0F%n", temp, converted);
                            } else {
                                System.out.printf("%.2f\u00b0F is equal to %.2f\u00b0C%n", temp, converted);
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Goodbye!");
        scnr.close();
    }
}
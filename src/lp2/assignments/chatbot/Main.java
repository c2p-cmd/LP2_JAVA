package lp2.assignments.chatbot;

import java.util.Scanner;

import static lp2.assignments.chatbot.ChatBot.findMatch;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print(">");
            final String input = scanner.nextLine();

            // to quit
            if (input.equalsIgnoreCase("q") || input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("bye")) {
                break;
            } else if (findMatch(input).isEmpty()) {
                System.out.println("I'm not sure if I understand what you are talking about.");
            } else {
                System.out.println(findMatch(input));
            }
        }

        scanner.close();
    }
}

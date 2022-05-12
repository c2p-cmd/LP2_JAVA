package lp2.assignments.encryption.transposition.cipher;

import java.util.Scanner;

public class TranspositionCipherMain {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Enter key:");
        final String key = scanner.nextLine();
        final TranspositionCipher runner = new TranspositionCipher(key);

        System.out.println("Enter message:");
        final String input = scanner.nextLine();
        scanner.close();

        final String cipherText = runner.doEncryption(input);
        final String message = runner.doDecryption(cipherText);

        System.out.println("Input = " + input + "\nCipher = " + cipherText + "\nOutput = " + message);
    }
}

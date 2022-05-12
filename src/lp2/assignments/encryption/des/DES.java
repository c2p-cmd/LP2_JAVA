package lp2.assignments.encryption.des;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

public class DES {
    private static final byte[] iv = {11, 22, 33, 44, 99, 88, 77, 66};
    private static Cipher encryptCipher;
    private static Cipher decryptCipher;

    public static void main(String[] args) {
        final String clearTextFile = "/Users/captain2mac/Documents/Code/IdeaProjects/src/lp2/assignments/encryption/des/source.txt";
        final String cipherTextFile = "/Users/captain2mac/Documents/Code/IdeaProjects/src/lp2/assignments/encryption/des/cipher.txt";
        final String clearTextNewFile = "/Users/captain2mac/Documents/Code/IdeaProjects/src/lp2/assignments/encryption/des/output.txt";

        try {
            // create SecretKey using KeyGenerator
            final SecretKey key = KeyGenerator.getInstance("DES").generateKey();
            final AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);

            // get Cipher instance and initiate in encrypt mode
            encryptCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            encryptCipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);

            // get Cipher instance and initiate in decrypt mode
            decryptCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            decryptCipher.init(Cipher.DECRYPT_MODE, key, paramSpec);

            // method to encrypt clear text file to encrypted file
            final Path path = Paths.get(cipherTextFile);
            encrypt(Files.newInputStream(Paths.get(clearTextFile)), Files.newOutputStream(path));

            // method to decrypt encrypted file to clear text file
            decrypt(Files.newInputStream(path), Files.newOutputStream(Paths.get(clearTextNewFile)));
            System.out.println("DONE");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                | InvalidAlgorithmParameterException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void encrypt(InputStream is, OutputStream os) throws IOException {
        // create CipherOutputStream to encrypt the data using encryptCipher
        os = new CipherOutputStream(os, encryptCipher);
        writeData(is, os);
    }

    private static void decrypt(InputStream is, OutputStream os) throws IOException {
        // create CipherOutputStream to decrypt the data using decryptCipher
        is = new CipherInputStream(is, decryptCipher);
        writeData(is, os);
    }

    // utility method to read data from input stream and write to output stream
    private static void writeData(InputStream is, OutputStream os) throws IOException {
        final byte[] buf = new byte[1024];
        int numRead;
        // read and write operation
        while ((numRead = is.read(buf)) >= 0) {
            os.write(buf, 0, numRead);
        }
        os.close();
        is.close();
    }

}


package lp2.assignments.encryption.transposition.cipher;

import java.util.*;

public class TranspositionCipher {
    // Member declaration for ciphering
    private final String selectedKey;
    private final List<Character> sortedKey = new LinkedList<>();
    private final List<Integer> sortedKeyPos;

    public TranspositionCipher(final String customKey) {
        selectedKey = customKey;
        sortedKeyPos = new ArrayList<>(selectedKey.length());
        for (char ch : selectedKey.toCharArray()) {
            sortedKey.add(ch);
        }
    }

    private void doProcessKey() {
        Collections.sort(sortedKey);
        for (int i = 0; i < selectedKey.length(); i++) {
            for (int j = 0; j < selectedKey.length(); j++) {
                if (selectedKey.charAt(i) == sortedKey.get(j)) {
                    sortedKeyPos.add(i, j);
                }
            }
        }
    }

    public String doEncryption(final String plainText) {
        doProcessKey();

        final int rows = plainText.length() / selectedKey.length();
        final int extraBit = plainText.length() % selectedKey.length();
        final int extraRow = (extraBit == 0) ? 0: 1;
        int colTemp = -1;
        final int totalLength = (rows + extraRow) * selectedKey.length();
        final char[][] matrix = new char[(rows + extraRow)][selectedKey.length()];

        int row = 0;
        for (int i = 0; i < totalLength; i++) {
            colTemp++;
            if (i < plainText.length()) {
                if (colTemp == selectedKey.length()) {
                    row++;
                    colTemp = 0;
                }
                if (plainText.charAt(i) == ' ')
                    matrix[row][colTemp] = '#';
                else
                    matrix[row][colTemp] = plainText.charAt(i);
            } else {
                matrix[row][colTemp] = '-';
            }
        }

        int k;

        final StringBuilder cipherBuilder = new StringBuilder();
        for (int i = 0; i < selectedKey.length(); i++) {
            for (k = 0; k < selectedKey.length(); k++) {
                if (i == sortedKeyPos.get(k))
                    break;
            }
            for (int j = 0; j <= row; j++) {
                cipherBuilder.append(matrix[j][k]);
            }
        }

        return cipherBuilder.toString();
    }

    public String doDecryption(final String cipherText) {
        doProcessKey();

        final int row = cipherText.length() / selectedKey.length();
        final char[][] matrix = new char[row][selectedKey.length()];
        int tempCtr = -1;

        int k;
        for (int i = 0; i < selectedKey.length(); i++) {
            for (k = 0; k < selectedKey.length(); k++) {
                if (i == sortedKeyPos.get(k))
                    break;
            }
            for (int j = 0; j < row; j++) {
                if (++tempCtr < cipherText.length()) {
                    matrix[j][k] = cipherText.charAt(tempCtr);
                }
            }
        }

        final StringBuilder message = new StringBuilder();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < selectedKey.length(); j++) {
                if (matrix[i][j] == '#') {
                    message.append(" ");
                    continue;
                } if (matrix[i][j] != '-') {
                    message.append(matrix[i][j]);
                }
            }
        }

        return message.toString();
    }
}

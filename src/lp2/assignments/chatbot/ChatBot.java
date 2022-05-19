package lp2.assignments.chatbot;

import java.util.Random;

public class ChatBot {
    final static Random random = new Random();

    static class Record {
        private final String input;
        private final String[] responses;

        public Record(String input, String[] responses) {
            this.input = input;
            this.responses = responses;
        }

        public String getInput() {
            return input;
        }

        public String[] getResponses() {
            return responses;
        }
    }

    private static final Record[] KnowledgeBase = {
            new Record("WHAT IS YOUR NAME", new String[] {"MY NAME IS CHATTY.", "YOU CAN CALL ME CHATTY"}),
            new Record("HI", new String[]{"HI THERE!",
                    "HOW ARE YOU?",
                    "HI!"}),
            new Record("HOW ARE YOU", new String[]{"I'M DOING FINE!",
                    "I'M DOING WELL AND YOU?",
                    "WHY DO YOU WANT TO KNOW HOW AM I DOING?"}),
            new Record("HOW ARE YOU", new String[]{"I'M DOING FINE!",
                            "I'M DOING WELL AND YOU?",
                            "WHY DO YOU WANT TO KNOW HOW AM I DOING?"}),
            new Record("WHO ARE YOU", new String[]{"I'M AN A.I PROGRAM.",
                    "I THINK THAT YOU KNOW WHO I'M.",
                    "WHY ARE YOU ASKING?"}),
            new Record("ARE YOU INTELLIGENT", new String[]{"YES,OFCORSE.",
                    "WHAT DO YOU THINK?",
                    "ACTUALLY,I'M VERY INTELLIGENT!"}),
            new Record("ARE YOU REAL", new String[]{"DOES THAT QUESTION REALLY MATERS TO YOU?", "WHAT DO YOU MEAN BY THAT?",
                    "I'M AS REAL AS I CAN BE."})
    };

    private static int getRandom(final int size) {
        return random.nextInt(size);
    }

    public static String findMatch(final String input) {
        for (Record record : KnowledgeBase) {
            if (record.getInput().equalsIgnoreCase(input)) {
                final String[] responses = record.getResponses();
                final int responsesSize = responses.length;
                return responses[getRandom(responsesSize)];
            }
        }
        return "";
    }
}

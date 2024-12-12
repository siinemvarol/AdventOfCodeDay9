import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Path fileName
                = Path.of("C:\\Users\\VarolS\\IdeaProjects\\AdventOfCodeDay9\\day9.txt");

        String str;
        try {
            str = Files.readString(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("str is... " + str);

        ArrayList<String> fileBlocks = new ArrayList<>();

        int idCounter = 0;
        for (int i = 0; i < str.length(); i++) {
            int currentNumber = str.charAt(i) - '0';
//            System.out.println("current number is... " + currentNumber);

            if (!(currentNumber == 0)) {
                for (int j = 1; j <= currentNumber; j++) {

                    if (i % 2 == 0) {
                        fileBlocks.add(String.valueOf(idCounter));
                    } else {
                        fileBlocks.add(".");
                    }
                }
                if ((i % 2 == 0) && (i != str.length() - 1)) {
                    idCounter++;
                }
            }
        }
        System.out.println("fileBlocks is... : " + fileBlocks);
        System.out.println("id counter is... " + idCounter);

//        System.out.println("first index of . is... : " + findIndexOfFirstDot(fileBlocks));
//        System.out.println("index of last number is... : " + findIndexOfLastNumber(fileBlocks));

        while (findIndexOfFirstDot(fileBlocks) < findIndexOfLastNumber(fileBlocks)) {
            for (int i = fileBlocks.size() - 1; i >= 0; i--) {
                int currentAscii = fileBlocks.get(i).charAt(0);
                int currentNumber;
                StringBuilder movingNumber = new StringBuilder();
                if (currentAscii >= 48 && currentAscii <= 57) {

                    for (int j = 0; j < fileBlocks.get(i).length(); j++) {
                        movingNumber.append(fileBlocks.get(i).charAt(j));
                    }

                    currentNumber = currentAscii - '0';
                    fileBlocks.set(i, ".");
//                    fileBlocks.set(findIndexOfFirstDot(fileBlocks), String.valueOf(currentNumber));
                    fileBlocks.set(findIndexOfFirstDot(fileBlocks), movingNumber.toString());
                }
            }
        }

        System.out.println("last list ... : " + fileBlocks);

        long checksum = 0;

//        for (int i = 0; i < fileBlocks.size(); i++) {
//            int currentAscii = fileBlocks.get(i).charAt(0);
//            if (currentAscii >= 48 && currentAscii <= 57) {
//                checksum += (i * Integer.parseInt(fileBlocks.get(i)));
//            }
//        }

        for (int i = 0; i < fileBlocks.size(); i++) {

            if (!fileBlocks.get(i).contains(".")) {
                checksum += ((long) i * Integer.parseInt(fileBlocks.get(i)));
            }
        }

        System.out.println("Checksum is : " + checksum);
    }

    public static int findIndexOfLastNumber(ArrayList<String> fileBlocks) {
        int indexOfLastNumber = 0;

        for (int i = 0; i < fileBlocks.size(); i++) {
            int currentAscii = fileBlocks.get(i).charAt(0);
            if (currentAscii >= 48 && currentAscii <= 57) {
                indexOfLastNumber = i;
            }
        }
        return indexOfLastNumber;
    }

    public static int findIndexOfFirstDot(ArrayList<String> fileBlocks) {
        return fileBlocks.indexOf(".");
    }
}
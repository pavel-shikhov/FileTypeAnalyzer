import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        if (isPDF(args[0], args[1])){
            System.out.println(args[2]);
        } else {
            System.out.println("Unknown file type");
        }
    }

    static boolean isPDF(String fileName, String pattern){
        File f = new File(fileName);
        byte[] patternSignature = pattern.getBytes();
        if (f.isFile()){
            try (
                InputStream inputStream = new BufferedInputStream(new FileInputStream(f))
            ) {
                int i = 0;
                int currentSymbol;
                while ((currentSymbol = inputStream.read()) != -1){
                    if (currentSymbol == patternSignature[i]){
                        i++;
                    } else {
                        i = 0;
                    }
                    if (i == patternSignature.length){
                        return true;
                    }
                }
                return false;
            } catch (IOException e) {
                System.out.println("Error!");
            }
        }
        return false;
    }
}
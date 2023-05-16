import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Document {

    public static void loadDocument(String name, Scanner scan) {

        boolean run = true;
        Pattern pattern = Pattern.compile("link=([\\w]+)");
        Matcher matcher;

        while (run && scan.hasNextLine()) {
            String line = scan.nextLine().toLowerCase();

            if (line.contains("eod")) {
                run = false;
                continue;
            }
            matcher = pattern.matcher(line);

            while (matcher.find()) {
                if (correctLink(matcher.group(1))) {
                    System.out.println(matcher.group(1));
                }
            }
        }

    }

    public static boolean correctLink(String link) {

        boolean correct = false;
        Pattern pattern = Pattern.compile("^[A-Za-z][\\w]*");
        Matcher matcher = pattern.matcher(link);

        if (matcher.find()) {
            correct = true;
        }

        return correct;
    }

}

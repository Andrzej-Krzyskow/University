import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Document {
    public String name;
    public TwoWayCycledOrderedListWithSentinel<Link> links;

    public Document(String name, Scanner scan) {
        this.name = name.toLowerCase();
        links = new TwoWayCycledOrderedListWithSentinel<Link>();
        load(scan);
    }

    public void load(Scanner scan) {
        boolean run = true;
        Pattern pattern = Pattern.compile("link=([\\w]+[(]\\d+[)]|\\w+\\s|\\w+$)");
        Matcher matcher;

        while (run && scan.hasNextLine()) {
            String line = scan.nextLine().toLowerCase();

            if (line.contains("eod")) {
                run = false;
                continue;
            }
            matcher = pattern.matcher(line);

            while (matcher.find()) {
                links.add(createLink(matcher.group(1).trim()));
            }
        }
    }

    public static boolean isCorrectId(String id) {
        boolean correct = false;
        Pattern pattern = Pattern.compile("^[A-Za-z][\\w]*");
        Matcher matcher = pattern.matcher(id);

        if (matcher.find()) {
            correct = true;
        }

        return correct;
    }

    private static Link createLink(String link) {
        Pattern pattern = Pattern.compile("(\\w+)([(](\\d+)[)]|$)");
        Matcher matcher = pattern.matcher(link);

        if (matcher.find()) {
            if (matcher.group(3) == null) {
                return new Link(matcher.group(1));
            } else {
                return new Link(matcher.group(1), Integer.parseInt(matcher.group(3)));
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder retStr = new StringBuilder("Document: " + name + " ");

        int rowCounter = 0;

        for (Link link : links) {

            if (rowCounter % 10 == 0) {
                retStr.setLength(retStr.length() - 1);
                retStr.append("\n");
                rowCounter = 0;
            }

            retStr.append(link.toString()).append(" ");

            rowCounter++;
        }

        if (retStr.charAt(retStr.length() - 1) == ' ') {
            retStr.setLength(retStr.length() - 1);
        }


        return retStr.toString();
    }

    public String toStringReverse() {
        StringBuilder retStr = new StringBuilder("Document: " + name + " ");
        int rowCounter = 0;

        ListIterator<Link> iter = links.listIterator();
        while (iter.hasNext())
            iter.next();

        while (iter.hasPrevious()) {

            if (rowCounter % 10 == 0) {
                retStr.setLength(retStr.length() - 1);
                retStr.append("\n");
                rowCounter = 0;
            }

            retStr.append(iter.previous().toString()).append(" ");

            rowCounter++;

        }

        if (retStr.charAt(retStr.length() - 1) == ' ') {
            retStr.setLength(retStr.length() - 1);
        }

        return retStr.toString();
    }
}


import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Document {
    public String name;
    public TwoWayUnorderedListWithHeadAndTail<Link> link;

    public Document(String name, Scanner scan) {
        this.name = name;
        link = new TwoWayUnorderedListWithHeadAndTail<Link>();
        load(scan);
    }

    public void load(Scanner scan) {

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
                    link.add(new Link(matcher.group(1)));
                }
            }
        }

    }

    private static boolean correctLink(String link) {
        boolean correct = false;
        Pattern pattern = Pattern.compile("^[A-Za-z][\\w]*");
        Matcher matcher = pattern.matcher(link);

        if (matcher.find()) {
            correct = true;
        }

        return correct;
    }

    @Override
    public String toString() {

        return "Document: " + name + link.toString();
    }

    public String toStringReverse() {
        String retStr = "Document: " + name;
        return retStr + link.toStringReverse();
    }

}

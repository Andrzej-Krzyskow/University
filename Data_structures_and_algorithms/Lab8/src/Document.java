import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Document implements IWithName {
    private static final int MOD_VALUE = 100000000;
    private static final int[] HASH_CYCLE = {7, 11, 13, 17, 19};
    public String name;
    public BST<Link> links;

    public Document(String name) {
        this.name = name.toLowerCase();
        links = new BST<>();
    }

    public Document(String name, Scanner scan) {
        this.name = name.toLowerCase();
        links = new BST<>();
        load(scan);
    }

    public void load(Scanner scan) {
        boolean run = true;
        Pattern pattern = Pattern.compile("link=(\\w+[(]\\d+[)]|\\w+\\s|\\w+$)");
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
        Pattern pattern = Pattern.compile("^[A-Za-z]\\w*");
        Matcher matcher = pattern.matcher(id);

        if (matcher.find()) {
            correct = true;
        }

        return correct;
    }

    static Link createLink(String link) {
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
        String retStr = "Document: " + name + "\n";
        retStr += links.toStringInOrder();
        return retStr;
    }

    public String toStringPreOrder() {
        String retStr = "Document: " + name + "\n";
        retStr += links.toStringPreOrder();
        return retStr;
    }

    public String toStringPostOrder() {
        String retStr = "Document: " + name + "\n";
        retStr += links.toStringPostOrder();
        return retStr;
    }

    @Override
    public int hashCode() {
        int hashCode = 0;

        if (name.length() == 0) {
            return 0;
        }

        hashCode += name.charAt(0);
        for (int i = 1; i < name.length(); i++) {
            hashCode = (hashCode * HASH_CYCLE[(i - 1) % 5] + name.charAt(i)) % MOD_VALUE;
        }


        return hashCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(name, document.name);
    }


    @Override
    public String getName() {
        return name;
    }
}
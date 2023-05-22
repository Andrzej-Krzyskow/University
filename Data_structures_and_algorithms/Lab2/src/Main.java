import java.util.*;


public class Main {

    static Scanner scan; // for input stream

    public static void main(String[] args) {
/*

        OneWayLinkedList<Integer> oneWayLinkedList = new OneWayLinkedList<>();

        System.out.println("size: " + oneWayLinkedList.size());

        oneWayLinkedList.add(1);
        oneWayLinkedList.add(2);
        oneWayLinkedList.add(3);


        Iterator<Integer> iterator = oneWayLinkedList.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());

        }

        oneWayLinkedList.clear();
        System.out.println("cleared");

        while (iterator.hasNext()) {
            System.out.print(iterator.next()+" ");

        }

        oneWayLinkedList.add(10);
        oneWayLinkedList.add(20);
        oneWayLinkedList.add(30);

*/

/*        System.out.println(oneWayLinkedList.indexOf(1));
        System.out.println(oneWayLinkedList.indexOf(2));
        System.out.println(oneWayLinkedList.indexOf(3));
        System.out.println(oneWayLinkedList.indexOf(4));

        System.out.println(oneWayLinkedList.contains(1));
        System.out.println(oneWayLinkedList.contains(2));
        System.out.println(oneWayLinkedList.contains(3));
        System.out.println(oneWayLinkedList.contains(4));*/

        /*

        Iterator<Integer> iterator2 = oneWayLinkedList.iterator();

        while (iterator2.hasNext()) {
            System.out.print(iterator2.next()+" ");
        }
        System.out.println();

        oneWayLinkedList.add(2,54);

        Iterator<Integer> iterator1 = oneWayLinkedList.iterator();

        while (iterator1.hasNext()) {
            System.out.print(iterator1.next()+" ");
        }

        System.out.println();
        System.out.println(oneWayLinkedList.get(0));


        System.out.println(oneWayLinkedList.set(3, 66));
        Iterator<Integer> iterator3 = oneWayLinkedList.iterator();

        while (iterator3.hasNext()) {
            System.out.print(iterator3.next()+" ");
        }
        System.out.println();

        System.out.println("remove " + oneWayLinkedList.remove((Integer) 66));
        Iterator<Integer> iterator4 = oneWayLinkedList.iterator();

        while (iterator4.hasNext()) {
            System.out.print(iterator4.next()+" ");
        }
*/


        System.out.println("START");
        scan=new Scanner(System.in);
        Document[] doc=null;
        int currentDocNo=0;
        int maxNo=-1;
        boolean halt=false;
        while(!halt) {
            String line=scan.nextLine();
            // empty line and comment line - read next line
            if(line.length()==0 || line.charAt(0)=='#')
                continue;
            // copy line to output (it is easier to find a place of a mistake)
            System.out.println("!"+line);
            String word[]=line.split(" ");
            // go n - start with array of the length n
            if(word[0].equalsIgnoreCase("go") && word.length==2) {
                maxNo=Integer.parseInt(word[1]);
                doc = new Document[maxNo];
                continue;
            }
            //ch - change index
            if(word[0].equalsIgnoreCase("ch") && word.length==2) {
                currentDocNo=Integer.parseInt(word[1]);
                continue;
            }

            // ld documentName
            if(word[0].equalsIgnoreCase("ld") && word.length==2) {
                doc[currentDocNo]=new Document(word[1],scan);
                continue;
            }
            // ha
            if(word[0].equalsIgnoreCase("ha") && word.length==1) {
                halt=true;
                continue;
            }
            // clear
            if(word[0].equalsIgnoreCase("clear") && word.length==1) {
                doc[currentDocNo].links.clear();
                continue;
            }
            // show
            if(word[0].equalsIgnoreCase("show") && word.length==1) {
                System.out.println(doc[currentDocNo].toString());
                continue;
            }
            // size
            if(word[0].equalsIgnoreCase("size") && word.length==1) {
                System.out.println(doc[currentDocNo].links.size());
                continue;
            }
            // add str
            if(word[0].equalsIgnoreCase("add") && word.length==2) {
                System.out.println(doc[currentDocNo].links.add(new Link(word[1])));
                continue;
            }
            // addi index str
            if(word[0].equalsIgnoreCase("addi") && word.length==3) {
                int index=Integer.parseInt(word[1]);
                try {
                    doc[currentDocNo].links.add(index, new Link(word[2]));
                }
                catch (NoSuchElementException e) {
                    System.out.println("error");
                }
                continue;
            }
            // get index
            if(word[0].equalsIgnoreCase("get") && word.length==2) {
                int index=Integer.parseInt(word[1]);
                try {
                    Link l=doc[currentDocNo].links.get(index);
                    System.out.println(l.ref);
                }
                catch(NoSuchElementException e) {
                    System.out.println("error");
                }
                continue;
            }
            // set index str
            if(word[0].equalsIgnoreCase("set") && word.length==3) {
                int index=Integer.parseInt(word[1]);
                try {
                    Link l=doc[currentDocNo].links.set(index,new Link(word[2]));
                    System.out.println(l.ref);
                }
                catch(NoSuchElementException e) {
                    System.out.println("error");
                }

                continue;
            }
            // index str
            if(word[0].equalsIgnoreCase("index") && word.length==2) {
                int index=doc[currentDocNo].links.indexOf(new Link(word[1]));
                System.out.println(index);
                continue;
            }
            // remi index
            if(word[0].equalsIgnoreCase("remi") && word.length==2) {
                int index=Integer.parseInt(word[1]);
                try {
                    Link l=doc[currentDocNo].links.remove(index);
                    System.out.println(l.ref);
                }
                catch(NoSuchElementException e) {
                    System.out.println("error");
                }
                continue;
            }
            // rem str
            if(word[0].equalsIgnoreCase("rem") && word.length==2) {
                System.out.println(doc[currentDocNo].links.remove(new Link(word[1])));
                continue;
            }
            //remeven
            if(word[0].equalsIgnoreCase("remeven") && word.length==1) {
                doc[currentDocNo].links.removeeven();
                continue;
            }

            System.out.println("Wrong command");
        }
        System.out.println("END OF EXECUTION");
        scan.close();

    }

}

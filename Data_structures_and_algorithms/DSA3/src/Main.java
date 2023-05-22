public class Main {

    public static void main(String[] args) {

        //TASK 1
        System.out.println("\nTASK 1");

        VTS_Stack<Integer> stack = new VTS_Stack<>();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println(stack.down());
        System.out.println(stack.down());
        System.out.println(stack.down());

        System.out.println(stack.peek());

        //TASK 2
        System.out.println("\nTASK 2");
        stack.push(666);
        System.out.println(stack);
        stack.reverse();
        System.out.println(stack);


        //TASK 3
        System.out.println("\nTASK 3");

        EfficientQueue <Integer> efficientQueue = new EfficientQueue<>();
        efficientQueue.enqueue(5);
        efficientQueue.enqueue(4);
        System.out.println(efficientQueue.dequeue());
        efficientQueue.enqueue(66);
        System.out.println(efficientQueue.dequeue());
        System.out.println(efficientQueue.dequeue());

        //TASK 4 A
        System.out.println("\nTASK 4 A");
        SinkingStackInefficient<Integer> sinkingStackInefficient = new SinkingStackInefficient<>(5);
        sinkingStackInefficient.push(1);
        sinkingStackInefficient.push(2);
        sinkingStackInefficient.push(3);
        sinkingStackInefficient.push(4);
        sinkingStackInefficient.push(5);
        System.out.println(sinkingStackInefficient);
        sinkingStackInefficient.push(666);
        sinkingStackInefficient.push(666);
        sinkingStackInefficient.push(666);
        System.out.println(sinkingStackInefficient);

        //TASK 4 B
        System.out.println("\nTASK 4 B");

        SinkingStackEfficient<Integer> sinkingStackEfficient = new SinkingStackEfficient<>(4);
        sinkingStackEfficient.push(1);
        sinkingStackEfficient.push(2);
        sinkingStackEfficient.push(3);
        sinkingStackEfficient.push(4);
        System.out.println(sinkingStackEfficient.pop());
        System.out.println(sinkingStackEfficient.pop());
        System.out.println(sinkingStackEfficient.pop());
        sinkingStackEfficient.push(666);
        System.out.println(sinkingStackEfficient.pop());
        sinkingStackEfficient.push(747);
        sinkingStackEfficient.push(888);
        sinkingStackEfficient.push(999);
        sinkingStackEfficient.push(10000);
        System.out.println(sinkingStackEfficient.pop());
        System.out.println(sinkingStackEfficient.pop());
        System.out.println(sinkingStackEfficient.pop());
        System.out.println(sinkingStackEfficient.pop());
        sinkingStackEfficient.push(747);
        sinkingStackEfficient.push(888);
        sinkingStackEfficient.push(999);
        sinkingStackEfficient.push(10000);
        sinkingStackEfficient.push(5);
        sinkingStackEfficient.push(4);
        sinkingStackEfficient.push(3);
        System.out.println();
        System.out.println(sinkingStackEfficient.pop());
        System.out.println(sinkingStackEfficient.pop());
        System.out.println(sinkingStackEfficient.pop());
        System.out.println(sinkingStackEfficient.pop());
        sinkingStackEfficient.push(9797);
        sinkingStackEfficient.push(9797);
        sinkingStackEfficient.push(9797);
        sinkingStackEfficient.push(9797);
        sinkingStackEfficient.push(9797);

        System.out.println(sinkingStackEfficient);


        //TASK 5
        System.out.println("\nTASK 5");

        CommittingSuicide committingSuicide = new CommittingSuicide(11, 3);

        System.out.println(committingSuicide.killPeople());


        //TASK 6
        System.out.println("\nTASK 6");

        String equation = "(2+4)*(3.7-9/3)";
        String RpnEquation = MyAnalyzer.toRPNString(MyAnalyzer.analize(equation));

        System.out.println(RpnEquation);
        System.out.println(RpnCalculator.toInfixNotation(RpnEquation));


    }
}

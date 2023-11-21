import java.util.*;
class Stack {
    private static final int max_size = 1000;
    private int top;
    private String[] arr;
    public Stack() {
        arr = new String[max_size];
        top = -1;
    }
    public boolean isEmpty() {
        return top == -1;
    }
    public boolean isFull() {
        return top == max_size - 1;
    }
    public void push(String item) {
        if (isFull()) {
            System.out.println("overflow" + item);
        } else {
            arr[++top] = item;
        }
    }
    public String pop() {
        if (isEmpty()) {
            System.out.println("underflow");
            return null;
        } else {
            String item = arr[top--];
            return item;
        }
    }
    public String peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        } else {
            return arr[top];
        }
    }
}
class Node {
    String data;
    Node next;
    public Node(String d) {
        data = d;
        next = null;
    }
}
class Queue {
    String[] arr;
    int front;
    int rear;
    int capacity = 100;
    public Queue() {
        arr = new String[capacity];
        front = rear = -1;
}
    public void enqueue(String data) {
        if (isFull()) {
            System.out.println("Queue is full");
        } else {
            if (isEmpty()) {
                front = rear = 0;
            } else {
                rear = (rear + 1) % capacity;
            }
            arr[rear] = data;
        }
    }
    public void dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
        } else {
            if (front == rear) {
                front = rear = -1;
            } else {
                front = (front + 1) % capacity;
            }
        }
    }
    public boolean isEmpty() {
        return front == -1;
    }
    public boolean isFull() {
        return (rear + 1) % capacity == front;
    }
    public String peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        } else {
            return arr[front];
        }
    }
}
class Gamerules{
    public static void play(Scanner sc, Random rd) {
        Stack rmh = new Stack();
        Queue mh = new Queue();
        int[] scores = new int[2];
        while (true) {
            displayMenu();
            String um = getuserMove(sc);
            String cm = getRandomMove(rd);
            System.out.println("\n\t\t\t\t Your move: " + um);
            System.out.println("\t\t\t\t Computer's move: " + cm);
            String result = Winner(um, cm);
            System.out.println("\t\t\t\t Result: " + result);
            updateScores(result, scores);
            rmh.push("User: " + um + ", Computer: " + cm + " - " + result);
            mh.enqueue("User: " + um + ", Computer: " + cm + " - " + result);
            System.out.println("\n\t\t\t\t Scores - You: " + scores[0] + ", Computer: " + scores[1]);
            System.out.println("\n\t\t\t\t Move History (in reverse order):");
            printreversemoveHistory(rmh);
            System.out.println("\n\t\t\t\t Move History (in order played):");
            printmoveHistory(mh);
            System.out.print("\n\t\t\t\t Do you want to play again? (yes/no): ");
            String playAgain = sc.next().toLowerCase();
            if (!playAgain.equals("yes")) {
                break;
            }
            System.out.println("\t\t\t\t------------------------------------------\n");
        }
    }
    private static void displayMenu() {
        System.out.println("\t\t\t\t------------------------------------------");
        System.out.println("\t\t\t\t Welcome to Rock, Paper and Scissors Game");
        System.out.println("\t\t\t\t------------------------------------------");
        System.out.println("\t\t\t\t Note: ");
        System.out.println("\t\t\t\t\t r : Rock\n\t\t\t\t\t p : Paper\n\t\t\t\t\t s : scissor\n\n");
        System.out.println("\t\t\t\t Available moves: r, p, s");
    }
    private static String getuserMove(Scanner sc) {
        String um = null;
        while (um == null) {
            System.out.print("\t\t\t\t Enter your move: ");
            String Input = sc.next().toLowerCase();
            if (isValidMove(Input)) {
                um = Input;
            } else {
                System.out.println("\t\t\t\t Invalid move. Please try again.");
            }
        }
        return um;
    }
    private static String getRandomMove(Random rd) {
        String[] moves = {"r", "p", "s"};
        return moves[rd.nextInt(moves.length)];
    }
    private static boolean isValidMove(String move) {
        String[] Moves = {"r", "p", "s"};
        for (String validMove : Moves) {
            if (move.equals(validMove)) {
                return true;
            }
        }
        return false;
    }
    private static String Winner(String um, String cm) {
        if (um.equals(cm)) {
            return "Woah! That's a tie";
        } else if ((um.equals("r") && cm.equals("s")) ||
                (um.equals("p") && cm.equals("r")) ||
                (um.equals("s") && cm.equals("p"))) {
            return "You won! Hurray";
        } else {
            return "You lose! BadLuck";
        }
    }
    private static void updateScores(String result, int[] scores) {
        if (result.equals("You won! Hurray")) {
            scores[0]++;
        } else if (result.equals("You lose! BadLuck")) {
            scores[1]++;
        }
    }
    public static void printreversemoveHistory(Stack history) {
        if (history.isEmpty()) {
            System.out.println("\t\t\t\t No moves recorded.");
        } else {
            Stack temp = new Stack();
            while (!history.isEmpty()) {
                String move = history.pop();
                temp.push(move);
                System.out.println("\t\t\t\t "+move);
            }
            while (!temp.isEmpty()) {
                history.push(temp.pop());
            }
        }
    }
    public static void printmoveHistory(Queue history) {
    if (history.isEmpty()) {
        System.out.println("\t\t\t\t No moves record");
    } else {
        int current = history.front;
        do {
            System.out.println("\t\t\t\t "+history.arr[current]);
            current = (current + 1) % history.capacity;
            } while (current != (history.rear + 1) % history.capacity);
        }
    }
}
public class Project {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rd = new Random();
        Gamerules.play(sc, rd);
        sc.close();
    }
}
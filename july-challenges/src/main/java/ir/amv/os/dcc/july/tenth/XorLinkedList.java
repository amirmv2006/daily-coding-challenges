package ir.amv.os.dcc.july.tenth;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amir
 */
public class XorLinkedList {

    private Node head;
    private Node tail;

    public void add(String val) {
        Node node = newNode();
        node.value = val;
        if (tail == null) {
            head = node;
            tail = head;
        } else {
            node.both = getPointer(tail);
            tail.both = tail.both ^ getPointer(node);
            tail = node;
        }
    }

    public String get(int i) {
        int counter = getPointer(head);
        int previous = 0;
        for (int j = 0; j < i; j++) {
            int tmp = previous;
            previous = counter;
            counter = tmp ^ dereferencePointer(counter).both;
        }
        return dereferencePointer(counter).value;
    }

    @Data
    private static class Node {
        private String value;
        private int both;
    }

    public static void main(String[] args) {
        XorLinkedList linkedList = new XorLinkedList();
        for (int i = 0; i < 10; i++) {
            linkedList.add(String.valueOf(i));
        }
        for (int i = 0; i < 10; i++) {
            String val = linkedList.get(i);
            System.out.println("val = " + val + ", i = " + i);
            System.out.println("correct = " + val.equals(String.valueOf(i)));
        }
    }


    private static List<Node> addressPool = new ArrayList<>();
    static {
        addressPool.add(null);
    }
    private static int getPointer(Node node) {
        return addressPool.indexOf(node);
    }
    private static Node dereferencePointer(int pointer) {
        return addressPool.get(pointer);
    }
    private static Node newNode() {
        Node node =  new Node();
        addressPool.add(node);
        return node;
    }

}

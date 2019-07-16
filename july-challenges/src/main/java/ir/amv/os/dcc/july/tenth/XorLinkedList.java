package ir.amv.os.dcc.july.tenth;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Good morning! Here's your coding interview problem for today.
 *
 * This problem was asked by Google.
 *
 * An XOR linked list is a more memory efficient doubly linked list. Instead of each node holding next and prev fields, it holds a field named both, which is an XOR of the next node and the previous node. Implement an XOR linked list; it has an add(element) which adds the element to the end, and a get(index) which returns the node at index.
 *
 * If using a language that has no pointers (such as Python), you can assume you have access to get_pointer and dereference_pointer functions that converts between nodes and memory addresses.
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

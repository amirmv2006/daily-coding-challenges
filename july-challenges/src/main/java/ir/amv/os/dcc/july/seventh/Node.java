package ir.amv.os.dcc.july.seventh;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Good morning! Here's your coding interview problem for today.
 *
 * This problem was asked by Google.
 *
 * Given the root to a binary tree, implement serialize(root), which serializes the tree into a string, and deserialize(s), which deserializes the string back into the tree.
 *
 * For example, given the following Node class
 *
 * class Node:
 *     def __init__(self, val, left=None, right=None):
 *         self.val = val
 *         self.left = left
 *         self.right = right
 * The following test should pass:
 *
 * node = Node('root', Node('left', Node('left.left')), Node('right'))
 * assert deserialize(serialize(node)).left.left.val == 'left.left'
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"left", "right"})
public class Node {

    private String name;
    private Node left;
    private Node right;

    public Node(String name) {
        this.name = name;
    }

    private static String serialize(Node node) {
        StringBuilder sb = new StringBuilder();
        sb.append(node.name.replaceAll("([()\\[\\]])", "|$1"));
        if (node.left != null) {
            sb.append("(");
            sb.append(serialize(node.left));
            sb.append(")");
        }
        if (node.right != null) {
            sb.append("[");
            sb.append(serialize(node.right));
            sb.append("]");
        }
        return sb.toString();
    }

    private static Node deserialize(String nodeStr) {
        if (nodeStr == null || nodeStr.isEmpty()) {
            return null;
        }
        Node result = new Node();
        recDes(result, nodeStr, 0, Objects::isNull);
        return result;
    }

    private static int recDes(Node result, String str, int index, Predicate<Character> endPredicate) {
        StringBuilder sb = new StringBuilder();
        int i = index;
        for (; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '|' && i < str.length() -1 && "()[]".contains(String.valueOf(str.charAt(i + 1)))) { // unescape
                i++;
                sb.append(str.charAt(i));
            } else if (charAt == '(') {
                result.left = new Node();
                i = recDes(result.left, str, i + 1, c -> c == ')');
            } else if (charAt == '[') {
                result.right = new Node();
                i = recDes(result.right, str, i + 1, c -> c == ']');
            } else if (endPredicate.test(charAt)) {
                break;
            } else {
                sb.append(charAt);
            }
        }
        result.name = sb.toString();
        return i;
    }

    public static void main(String[] args) {
        Node amir = new Node("Amir()");
        amir.left = new Node("Left)");
        amir.left.left = new Node("Left.Left[");
        amir.right = new Node("Right]");
        String serialize = serialize(amir);
        System.out.println("serialize(amir) = " + serialize);
        Node deserialize = deserialize(serialize);
        System.out.println("deserialize = " + deserialize);
    }
}

package ir.amv.os.dcc.july.fifteenth;

import ir.amv.os.dcc.july.Utils;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Good morning! Here's your coding interview problem for today.
 *
 * This problem was asked by Twitter.
 *
 * Implement an autocomplete system. That is, given a query string s and a set of all possible query strings, return all strings in the set that have s as a prefix.
 *
 * For example, given the query string de and the set of strings [dog, deer, deal], return [deer, deal].
 *
 * Hint: Try preprocessing the dictionary into a more efficient data structure to speed up queries.
 * @author Amir
 */
public class AutoCompleter {

    public String[] query(String query, String[] data) {
        CharTreeNode root = new CharTreeNode(' ');
        for (String datum : data) {
            CharTreeNode counter = root;
            for (int i = 0; i < datum.length(); i++) {
                char c = datum.charAt(i);
                counter = addIfMissing(counter, c).get();
            }
        }
        CharTreeNode counter = root;
        for (int i = 0; counter != null && i < query.length(); i++) {
            char c = query.charAt(i);
            counter = getNextChar(counter, c).orElse(null);
        }
        if (counter == null) {
            return new String[0];
        }
        List<String> result = new ArrayList<>();
        addToResult(query.length() > 0 ? query.substring(0, query.length() - 1) : query, counter, result);
        return result.toArray(new String[0]);
    }

    private void addToResult(final String query, final CharTreeNode counter, final List<String> result) {
        String subQ = query + counter.aChar;
        if (counter.nextChars.isEmpty()) {
            result.add(subQ);
        } else {
            counter.nextChars.forEach(charTreeNode -> addToResult(subQ, charTreeNode, result));
        }
    }

    private Optional<CharTreeNode> addIfMissing(final CharTreeNode node, final char c) {
        Optional<CharTreeNode> nextChar = getNextChar(node, c);
        if (!nextChar.isPresent()) {
            CharTreeNode e = new CharTreeNode(c);
            node.getNextChars().add(e);
            return Optional.of(e);
        }
        return nextChar;
    }

    private Optional<CharTreeNode> getNextChar(final CharTreeNode node, final char c) {
        return node.getNextChars().stream().filter(charTreeNode -> charTreeNode.aChar == c).findFirst();
    }

    @RequiredArgsConstructor
    @Data
    private static class CharTreeNode {
        private final char aChar;
        private Set<CharTreeNode> nextChars = new HashSet<>();
    }

    public static void main(String[] args) {
        String query = "amir";
        String[] data = {"amir", "amv"};
        String[] out = new AutoCompleter().query(query, data);
        Utils.printInputAndOutput(out, query, data);
    }
}

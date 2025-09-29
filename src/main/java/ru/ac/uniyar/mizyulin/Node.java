package ru.ac.uniyar.mizyulin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Node {
    private String name;
    private int id;
    private static int nextId = 1; // начнём с 1
    private List<Node> children = new ArrayList<>();

    public Node(String name) {
        this.name = name;
        this.id = nextId++;
    }

    public Node() {
        this("unnamed");
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public List<Node> getChildren() {
        return new ArrayList<>(children);
    }

    public void addChild(Node child) {
        children.add(child);
    }

    // Поиск ТОЛЬКО среди потомков
    public Optional<Node> getChildByName(String name) {
        for (Node child : children) {
            if (child.getName().equals(name)) {
                return Optional.of(child);
            }

            Optional<Node> foundInSubtree = child.getChildByName(name);
            if (foundInSubtree.isPresent()) {
                return foundInSubtree;
            }
        }
        return Optional.empty();
    }

    public void removeChildByName(String name) {
        children.removeIf(child -> child.getName().equals(name));
    }

    public void removeChildByID(int id) {
        children.removeIf(child -> child.getId() == id);
    }

    public void removeAllChildren() {
        children.clear();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toStringTree() {
        StringBuilder sb = new StringBuilder();
        toStringTree(sb, 0);
        return sb.toString();
    }

    private void toStringTree(StringBuilder sb, int depth) {
        String indent = "  ".repeat(depth);
        sb.append(indent).append(name).append("\n");
        for (Node child : children) {
            child.toStringTree(sb, depth + 1);
        }
    }
    public void printTree() {
        System.out.print(toStringTree());
    }
}
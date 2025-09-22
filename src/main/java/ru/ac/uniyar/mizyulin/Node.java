package ru.ac.uniyar.mizyulin;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Node {
    private String name = "";
    private int id;
    private static int cnt = 1;
    private List<Node> children = new ArrayList<>();

    public Node(String name){
        this.name = name;
        cnt ++;
    }

    public Node(){
        cnt ++;
        this.id = cnt;
    }

    public String getName(){
        return this.name;
    }
    public int getID(){
        return this.id;
    }

    public List<Node> getChildren(){
        return this.children;
    }

    public void addChild(Node child){
        this.children.add(child);
    }

    public Optional<Node> getChildByName(String name) {
        if (this.getName().equals(name)) {
            return Optional.of(this);
        }

        for (Node child : this.children) {
            Optional<Node> result = child.getChildByName(name); // ← рекурсивный вызов!
            if (result.isPresent()) {
                return result;
            }
        }

        return Optional.empty();
    }


    public void removeChildByName(String removedChildName){
        this.children.removeIf(child-> child.getName().equals(removedChildName));
    }

    public void removeChildByID(int removedChildID){
        this.children.removeIf(child-> child.getID() == removedChildID);
    }

    public void removeAllChildren(){
        this.children.clear();
    }

    public void setName(String nodeName){
        this.name = nodeName;
    }


}

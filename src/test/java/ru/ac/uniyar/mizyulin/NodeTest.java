package ru.ac.uniyar.mizyulin;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NodeTest {

    Node root;

    @BeforeEach
    void setUp() {
        root = new Node("root");
    }

    @Test
    void createNode(){
        assertNotNull(this.root);
        assertEquals("root", this.root.getName());
    }

    @Test
    void addChild(){
        int cntChildren = root.getChildren().size();
        root.addChild(new Node("child1"));
        assertEquals((cntChildren + 1), root.getChildren().size());
    }

    @Test
    void findChildByName(){
        Node child = new Node("child2");
        root.addChild(child);

        child.addChild(new Node("child3"));

        Optional<Node> found = root.getChildByName("child3");
        assertTrue(found.isPresent());
        assertEquals("child3", found.get().getName());
    }

    @Test
    void findNotExistingChildByName(){
        Optional<Node> found = root.getChildByName("childNotExist");
        assertFalse(found.isPresent());
    }

    @Test
    void removeChildByName() {
        root.addChild(new Node("removedChild"));
        root.removeChildByName("removedChild");
        Optional<Node> found = root.getChildByName("removedChild");
        assertTrue(found.isEmpty());
    }

    @Test
    void removeChildByID(){
        root.addChild(new Node());
        root.removeChildByID(1);
        Optional<Node> found = root.getChildByName("removedChild");
        assertTrue(found.isEmpty());
    }

    @Test
    void removeAllChildren() {
        root.addChild(new Node("child3"));
        root.addChild(new Node("child4"));
        root.removeAllChildren();
        List<Node> list = new ArrayList<>();
        assertEquals(list, root.getChildren());
    }

    @Test
    void changeName(){
        Node child = new Node("child1");
        root.addChild(child);

        assertEquals("child1", child.getName());

        child.setName("newName");
        assertEquals("newName", child.getName());
    }
}

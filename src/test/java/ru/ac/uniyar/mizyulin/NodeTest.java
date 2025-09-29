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
    void removeChildByID() {
        Node child = new Node("toBeRemoved");
        root.addChild(child);
        int id = child.getId();

        root.removeChildByID(id);

        Optional<Node> found = root.getChildByName("toBeRemoved");
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

    @Test
    void testToStringTree() {
        Node root = new Node("root");
        Node child1 = new Node("child1");
        Node child2 = new Node("child2");
        Node grandchild = new Node("grandchild");

        root.addChild(child1);
        root.addChild(child2);
        child1.addChild(grandchild);

        String expected =
                "root\n" +
                        "  child1\n" +
                        "    grandchild\n" +
                        "  child2\n";

        assertEquals(expected, root.toStringTree());
    }
}

import org.junit.jupiter.api.Test;
import java.lang.reflect.Array;
import static org.junit.jupiter.api.Assertions.*;


public class ExamPrepTest {
    @Test
    void addAtIndex_first_adds() {
        SinglyLinkedList<String> sll = new SinglyLinkedList();

        sll.addToBack("Mary");
        sll.addToBack("Andrew");
        sll.addToBack("Stephanie");
        sll.addToBack("Ivan");
        sll.addToBack("Miguel");
        sll.addToBack("Adrianna");

        mystery(sll.getHead());
    }

    void mystery(SinglyLinkedListNode<String> cur){
        if(cur == null){
            System.out.println("CS 1332 is cool!");
            return;
        }
        if(cur.getNext() != null && cur.getData().length() > 5){
            mystery(cur.getNext().getNext());
            System.out.println(cur.getData().length());
        } else if(cur.getData().length() % 2 == 0){
            System.out.println(cur.getData());
            mystery(cur.getNext());
        }
    }
}

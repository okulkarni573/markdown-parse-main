
/**
 * Name: Omkaar Kulkarni
 * ID: A17092332
 * Email: okulkarni@ucsd.edu
 * Sources used: Put "None" if you did not have any external help
 * Some example of sources used would be Tutors, Zybooks, and Lecture Slides
 * Tutor: Jett
 * https://docs.oracle.com/javase/8/docs/api/java/util/ListIterator.html
 * 2-4 sentence file description here
 */

import static org.junit.Assert.*;
import org.junit.*;
import java.util.NoSuchElementException;
/**
 * TODO: Add your class header
 * 
 * IMPORTANT: Do not change the method headers and points are awarded
 * only if your test cases cover cases that the public tester file
 * does not take into account.
 */
public class MyLinkedListCustomTester {
    private MyLinkedList listEmpty, list2;
    private MyLinkedList.MyListIterator listEmptyIter, list2Iter;
    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test.
     */
    @Before
    public void setUp() throws Exception {
        listEmpty = new MyLinkedList();
        listEmptyIter = listEmpty.new MyListIterator();

        list2 = new MyLinkedList<>();
        list2.add(0);
        list2.add(1);
        list2.add(2);
        list2Iter = list2.new MyListIterator();
    }

    /**
     * TODO: test the hasNext method when list is empty
     */
    @Test
    public void testHasNext() {
        assertFalse("Doensn't have Next when there's not next node", listEmptyIter.hasNext());
    }

    /**
     * TODO: test the next method when there is no element going forward
     */
    @Test
    public void testNext() {
        //manually doing list2Iter.next().next().next()
        list2Iter.left = list2.head.getNext().getNext().getNext();
        list2Iter.right = list2Iter.left.getNext();
        list2Iter.idx = 3;
        list2Iter.forward = true;
        list2Iter.canRemoveOrSet = true;
        try {
            list2Iter.next(); // Should throw NoSuchElementException
            fail();
        } catch (NoSuchElementException e) {
            // should pass if not fail
        }
    }

    /**
     * TODO: test the hasPrevious method when the list is empty
     */
    @Test
    public void testHasPrevious() {
        assertFalse("Doensn't have Next when there's not next node", listEmptyIter.hasPrevious());
    }

    /**
     * TODO: test the previous method with 2 calls
     */
    @Test
    public void testPrevious() {
        //manually doing list2Iter.next().next().next()
        list2Iter.left = list2.head.getNext().getNext().getNext();
        list2Iter.right = list2Iter.left.getNext();
        list2Iter.idx = 3;
        list2Iter.forward = true;
        list2Iter.canRemoveOrSet = true;
        assertEquals("Returns correct element", 2,
                list2Iter.previous());
        assertEquals("Index of iterator after 1 previous()", 2, list2Iter.idx);
        assertTrue("Can remove node", list2Iter.canRemoveOrSet);
        assertFalse("Direction is backward", list2Iter.forward);
        assertEquals("Returns correct element", 1,
                list2Iter.previous());
        assertEquals("Index of iterator after 2 previous()", 1, list2Iter.idx);
        assertTrue("Can remove node", list2Iter.canRemoveOrSet);
        assertFalse("Direction is backward", list2Iter.forward);
    }

    /**
     * TODO: test the nextIndex method, 2 calls
     */
    @Test
    public void testNextIndex() {
        assertEquals("Index after 0 next", 0,
        list2Iter.nextIndex());
        //manually doing list2Iter.next()
        list2Iter.left = list2.head.getNext();
        list2Iter.right = list2Iter.left.getNext();
        list2Iter.idx = 1;
        list2Iter.forward = true;
        list2Iter.canRemoveOrSet = true;
        assertEquals("Index after 1 next", 1,
        list2Iter.nextIndex());
    }

    /**
     * TODO: test the previousIndex method at end of the list
     */
    @Test
    public void testPreviousIndex() {
        //manually doing list2Iter.next().next().next()
        list2Iter.left = list2.head.getNext().getNext().getNext();
        list2Iter.right = list2Iter.left.getNext();
        list2Iter.idx = 3;
        list2Iter.forward = true;
        list2Iter.canRemoveOrSet = true;
        assertEquals("Index after 3next", 2,
        list2Iter.previousIndex());
    }

    /**
     * TODO: test the set method when canRemoveorSet is false
     */
    @Test
    public void testSet() {
        //manually setting RemoveOrSet
        list2Iter.canRemoveOrSet = false;
        try {
            list2Iter.set(3); // Should throw IllegalStatetException
            fail();
        } catch (IllegalStateException e) {
            // should pass if not fail
        }
    }

    /**
     * TODO: test the remove method when list is empty
     */
    @Test
    public void testRemoveTestOne() {
        try {
            listEmptyIter.remove(); // Should throw IllegalStateException
            fail();
        } catch (IllegalStateException e) {
            // should pass if not fail
        }
    }

    /**
     * TODO: test the remove method when canRemoveorSet is false in a non-empty list
     */
    @Test
    public void testRemoveTestTwo() {
        //manually setting RemoveOrSet
        list2Iter.canRemoveOrSet = false;
        try {
            list2Iter.remove();; // Should throw IllegalStateException
            fail();
        } catch (IllegalStateException e) {
            // should pass if not fail
        }
    }

    /**
     * TODO: test the add method at the end of a list
     */
    @Test
    public void testAdd() {
        //manually doing list2Iter.next().next().next()
        list2Iter.left = list2.head.getNext().getNext().getNext();
        list2Iter.right = list2Iter.left.getNext();
        list2Iter.idx = 3;
        list2Iter.forward = true;
        list2Iter.canRemoveOrSet = true;
        list2Iter.add(2);
        assertEquals("Valid change left", 2,
                list2Iter.left.getElement());
        assertEquals("No change right", list2.head.getElement(), 
                list2Iter.right.getElement());
        assertEquals("Index change after adding", 4, list2Iter.idx);
        assertFalse("No longer able to remove or set after adding",
                list2Iter.canRemoveOrSet);
    }

}
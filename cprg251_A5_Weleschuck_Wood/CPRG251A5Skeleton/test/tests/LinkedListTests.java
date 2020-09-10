package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import sait.sll.manager.SLL;
import sait.sll.utility.*;

/**
 * @author Nick Hamnett
 * @author Maryam Moussavi
 * @author Zennon Weleschuck
 * @author Joel Wood
 * @version 2020-07-23
 *
 */
class LinkedListTests {
	/**
	 * Contains the linked list that is manipulated in each test.
	 */
	private LinkedListADT linkedList;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		// Create your concrete linked list class and assign to to linkedList.
		this.linkedList = new SLL();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		this.linkedList.clear();
	}

	/**
	 * Test the linked list is empty.
	 */
	@Test
	void testIsEmpty() {
		assertTrue(this.linkedList.isEmpty());
		assertEquals(0, this.linkedList.size());
	}

	/**
	 * Test the linked list is empty. (test to fail)
	 */
	@Test
	void testIsEmptyNot() {
		this.linkedList.append("a");
		this.linkedList.append("b");
		this.linkedList.append("c");
		this.linkedList.append("d");
		assertTrue(!(this.linkedList.isEmpty()));
	}

	/**
	 * Tests appending elements to the linked list.
	 */
	@Test
	void testAppendNode() {
		this.linkedList.append("a");
		this.linkedList.append("b");
		this.linkedList.append("c");
		this.linkedList.append("d");

		/**
		 * Linked list should now be:
		 * 
		 * a -> b -> c -> d
		 */

		// Test the linked list is not empty.
		assertFalse(this.linkedList.isEmpty());

		// Test the size is 4
		assertEquals(4, this.linkedList.size());

		// Test the first node value is a
		assertEquals("a", this.linkedList.retrieve(0));

		// Test the second node value is b
		assertEquals("b", this.linkedList.retrieve(1));

		// Test the third node value is c
		assertEquals("c", this.linkedList.retrieve(2));

		// Test the fourth node value is d
		assertEquals("d", this.linkedList.retrieve(3));
	}

	/**
	 * Tests prepending nodes to linked list.
	 */
	@Test
	void testPrependNodes() {
		this.linkedList.prepend("a");
		this.linkedList.prepend("b");
		this.linkedList.prepend("c");
		this.linkedList.prepend("d");

		/**
		 * Linked list should now be:
		 * 
		 * d -> c -> b -> a
		 */

		// Test the linked list is not empty.
		assertFalse(this.linkedList.isEmpty());

		// Test the size is 4
		assertEquals(4, this.linkedList.size());

		// Test the first node value is a
		assertEquals("d", this.linkedList.retrieve(0));

		// Test the second node value is b
		assertEquals("c", this.linkedList.retrieve(1));

		// Test the third node value is c
		assertEquals("b", this.linkedList.retrieve(2));

		// Test the fourth node value is d
		assertEquals("a", this.linkedList.retrieve(3));
	}

	/**
	 * Tests inserting node at valid index.
	 */
	@Test
	void testInsertNode() {
		this.linkedList.append("a");
		this.linkedList.append("b");
		this.linkedList.append("c");
		this.linkedList.append("d");

		this.linkedList.insert("e", 2);

		/**
		 * Linked list should now be:
		 * 
		 * a -> b -> e -> c -> d
		 */

		// Test the linked list is not empty.
		assertFalse(this.linkedList.isEmpty());

		// Test the size is 4
		assertEquals(5, this.linkedList.size());

		// Test the first node value is a
		assertEquals("a", this.linkedList.retrieve(0));

		// Test the second node value is b
		assertEquals("b", this.linkedList.retrieve(1));

		// Test the third node value is e
		assertEquals("e", this.linkedList.retrieve(2));

		// Test the third node value is c
		assertEquals("c", this.linkedList.retrieve(3));

		// Test the fourth node value is d
		assertEquals("d", this.linkedList.retrieve(4));
	}

	/**
	 * Tests inserting node at into invalid index.(test to fail)
	 */
	@Test
	void testInsertNodeEmpty() {

		try {
			this.linkedList.insert("e", 2);
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}

	}

	/**
	 * Tests replacing existing nodes data in empty list. (test to fail)
	 */
	@Test
	void testReplaceNodeEmpty() {

		try {
			this.linkedList.replace("e", 2);
		} catch (IndexOutOfBoundsException e) {
			assertTrue(true);
		}

	}

	/**
	 * Tests replacing existing nodes data.
	 */
	@Test
	void testReplaceNode() {
		this.linkedList.append("a");
		this.linkedList.append("b");
		this.linkedList.append("c");
		this.linkedList.append("d");

		this.linkedList.replace("e", 2);

		/**
		 * Linked list should now be:
		 * 
		 * a -> b -> e -> d
		 */

		// Test the linked list is not empty.
		assertFalse(this.linkedList.isEmpty());

		// Test the size is 4
		assertEquals(4, this.linkedList.size());

		// Test the first node value is a
		assertEquals("a", this.linkedList.retrieve(0));

		// Test the second node value is b
		assertEquals("b", this.linkedList.retrieve(1));

		// Test the third node value is e
		assertEquals("e", this.linkedList.retrieve(2));

		// Test the fourth node value is d
		assertEquals("d", this.linkedList.retrieve(3));
	}

	/**
	 * Tests deleting node from linked list.
	 */
	@Test
	void testDeleteNode() {
		this.linkedList.append("a");
		this.linkedList.append("b");
		this.linkedList.append("c");
		this.linkedList.append("d");

		this.linkedList.delete(2);

		/**
		 * Linked list should now be:
		 * 
		 * a -> b -> d
		 */

		// Test the linked list is not empty.
		assertFalse(this.linkedList.isEmpty());

		// Test the size is 4
		assertEquals(3, this.linkedList.size());

		// Test the first node value is a
		assertEquals("a", this.linkedList.retrieve(0));

		// Test the second node value is b
		assertEquals("b", this.linkedList.retrieve(1));

		// Test the fourth node value is d
		assertEquals("d", this.linkedList.retrieve(2));
	}

	/**
	 * Tests deleting node from empty linked list.(test to fail)
	 */
	@Test
	void testDeleteNodeEmpty() {

		try {
			this.linkedList.delete(2);
		} catch (NullPointerException e) {
			assertTrue(true);
		}
	}

	/**
	 * Tests finding and retrieving node in linked list.
	 */
	@Test
	void testFindNode() {
		this.linkedList.append("a");
		this.linkedList.append("b");
		this.linkedList.append("c");
		this.linkedList.append("d");

		/**
		 * Linked list should now be:
		 * 
		 * a -> b -> c -> d
		 */

		boolean contains = this.linkedList.contains("b");
		assertTrue(contains);

		int index = this.linkedList.indexOf("b");
		assertEquals(1, index);

		String value = (String) this.linkedList.retrieve(1);
		assertEquals("b", value);
	}

	
	/**
	 * Tests if the linked list is cleared properly
	 */
	@Test
	void testClear() {
		this.linkedList.append("a");
		this.linkedList.append("b");
		this.linkedList.append("c");
		this.linkedList.append("d");

		this.linkedList.clear();

		assertTrue(this.linkedList.isEmpty());

	}

	/**
	 * Tests if the linked list is cleared properly if initially empty
	 */
	@Test
	void testClearEmpty() {

		this.linkedList.clear();

		assertTrue(this.linkedList.isEmpty());

	}

	/**
	 * Tests if the size is correct
	 */
	@Test
	void testSize() {
		this.linkedList.append("a");
		this.linkedList.append("b");
		this.linkedList.append("c");
		this.linkedList.append("d");

		int size = 4;

		assertEquals(4, this.linkedList.size());
	}

	/**
	 * Tests if the size is correct when empty
	 */
	@Test
	void testSizeZero() {

		int size = 0;

		assertEquals(0, this.linkedList.size());
	}

	/**
	 * Tests if the size is correct when many items are appended
	 */
	@Test
	void testSizeLarge() {
		for (int i = 0; i < 100; i++)
			this.linkedList.append("X");
		int size = 100;

		assertEquals(100, this.linkedList.size());
	}

}

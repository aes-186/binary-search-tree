package cs1501_p1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;

public class Anzu2Test {

	private BST<Integer> setup10() {
		int[] puts = {50,40,60,20,45,55,54};

		BST<Integer> t = new BST<Integer>();

		for (int i:puts) {
			t.put(i);
		}

		for (int i: puts) {
			assertTrue(t.contains(i), "Cannot check, put/contains not working");
		}

		return t; 
	}

	@Test
	public void bal10() {
		BST<Integer> t = setup10();

		assertEquals(false, t.isBalanced(), "Tree with 50,40,20,45,60,55,54 inserted should NOT be balanced");
	}

	private BST<Integer> setup8() {
		int[] puts = {40,30,35,32};

		BST<Integer> t = new BST<Integer>();

		for (int i:puts) {
			t.put(i);
		}

		for (int i: puts) {
			assertTrue(t.contains(i), "Cannot check, put/contains not working");
		}

		return t; 
	}

	private BST<Integer> setup9() {
		int[] puts = {5,2,4,10,8,12};

		BST<Integer> t = new BST<Integer>();

		for (int i:puts) {
			t.put(i);
		}

		for (int i: puts) {
			assertTrue(t.contains(i), "Cannot check, put/contains not working");
		}

		return t; 
	}

	private BST<Integer> setup6() {
		int[] puts = {40,30,35,32,33};

		BST<Integer> t = new BST<Integer>();

		for (int i:puts) {
			t.put(i);
		}

		for (int i: puts) {
			assertTrue(t.contains(i), "Cannot check, put/contains not working");
		}

		return t; 
	}

	private BST<Integer> setup7() {
		int[] puts = {40,30,35};

		BST<Integer> t = new BST<Integer>();

		for (int i:puts) {
			t.put(i);
		}

		for (int i: puts) {
			assertTrue(t.contains(i), "Cannot check, put/contains not working");
		}

		return t; 
	}

	
	private BST<Integer> setup5() {
		int[] puts = {50,60,55};

		BST<Integer> t = new BST<Integer>();

		for (int i:puts) {
			t.put(i);
		}

		for (int i: puts) {
			assertTrue(t.contains(i), "Cannot check, put/contains not working");
		}

		return t; 
	}



	private BST<Integer> setup4() {
		int[] puts = {50,40,30,20,60};

		BST<Integer> t = new BST<Integer>();

		for (int i:puts) {
			t.put(i);
		}

		for (int i: puts) {
			assertTrue(t.contains(i), "Cannot check, put/contains not working");
		}

		return t; 
	}

    private BST<Integer> setup() {

		int[] puts = {10, 5, 2, 37, 45};

		BST<Integer> t = new BST<Integer>();

		for (int i: puts) {
			t.put(i);
		}

		for (int i: puts) {
			assertTrue(t.contains(i), "Cannot check, put/contains not working");
		}

		return t;
	}

	private BST<Integer> setup2() {

		int[] puts = {10,8};

		BST<Integer> t = new BST<Integer>();

		for (int i: puts) {
			t.put(i);
		}

		for (int i: puts) {
			assertTrue(t.contains(i), "Cannot check, put/contains not working");
		}

		return t;
	}

	private BST<Integer> setup3() {

		int[] puts = {50,40,60,30,45,70,20,46,65};

		BST<Integer> t = new BST<Integer>();

		for (int i: puts) {
			t.put(i);
		}

		for (int i: puts) {
			assertTrue(t.contains(i), "Cannot check, put/contains not working");
		}

		return t;
	}

	@Test
	public void height3() {
		BST<Integer> t = setup3();

		assertEquals(4, t.height(), "Tree should be height 4");
	}

	@Test
	public void height4() {
		BST<Integer> t = setup4();

		assertEquals(4, t.height(), "Tree should be height 4");
	}

	@Test
	public void height2() {
		BST<Integer> t = setup2();

		assertEquals(2, t.height(), "Tree should be height 2");
	}

    @Test
	public void height() {
		BST<Integer> t = setup();

		assertEquals(3, t.height(), "Tree should be height 3");
	}

    @Test
	public void bal() {
		BST<Integer> t = setup();

		assertEquals(true, t.isBalanced(), "Tree should be balanced");
	}

	@Test
	public void bal3() {
		BST<Integer> t = setup3();

		assertEquals(false, t.isBalanced(), "Tree should NOT balanced");
	}



	@Test
	public void bal2() {
		BST<Integer> t = setup2();

		assertEquals(true, t.isBalanced(), "Tree should be balanced");
	}

	@Test
	public void bal4() {
		BST<Integer> t = setup4();

		assertEquals(false, t.isBalanced(), "Tree should be un-balanced");
	}

	@Test
	public void serial2() {
		BST<Integer> t = setup2();
		
		assertEquals("R(10),L(8),X(NULL)", t.serialize(), "Should produce \"R(10),L(8),X(NULL)\"");
	}

    @Test
	public void serial() {
		BST<Integer> t = setup();
		
		assertEquals("R(10),I(5),L(2),X(NULL),I(37),X(NULL),L(45)", t.serialize(), "Should produce \"R(10),I(5),L(2),X(NULL),I(37),X(NULL),L(45)\"");
	}

	@Test
	public void serial3() {
		BST<Integer> t = setup3();

		// serialize tree 3 

		// "R(50),I(40),I(30),L(20),X(NULL),I(45),X(NULL),L(46),I(60),X(NULL),I(70),L(65),X(NULL)"
		
		assertEquals("R(50),I(40),I(30),L(20),X(NULL),I(45),X(NULL),L(46),I(60),X(NULL),I(70),L(65),X(NULL)", 
					t.serialize(), 
					"Should produce \"R(50),I(40),I(30),L(20),X(NULL),I(45),X(NULL),L(46),I(60),X(NULL),I(70),L(65),X(NULL)\"");
	}

	@Test
	public void serial5() {
		BST<Integer> t = setup5();
		
		assertEquals("R(50),X(NULL),I(60),L(55),X(NULL)", t.serialize(), "Should produce \"R(50),X(NULL),I(60),L(55),X(NULL)\"");
	}

	// test reverse() using inOrderTraversal() 
	@Test
	public void revTest() {
		BST<Integer> t = setup();
		
		BST<Integer> rev = (BST<Integer>) t.reverse(); 

		assertEquals("45:37:10:5:2", rev.inOrderTraversal(), "Should produce \"45:37:10:5:2\"");	
	}

	@Test
	public void revTest2() {
		BST<Integer> t = setup2();
		
		BST<Integer> rev = (BST<Integer>) t.reverse(); 

		assertEquals("10:8", rev.inOrderTraversal(), "Should produce \"10:8\"");	
	}

	@Test
	public void rev2() {
		BST<Integer> t = setup2();

		BST<Integer> r = (BST<Integer>) t.reverse();
		assertEquals("R(10),X(NULL),L(8)", r.serialize(), "Should produce \"R(10),X(NULL),L(8)\"");
	}

	@Test
	public void rev3() {
		BST<Integer> t = setup3();

		BST<Integer> r = (BST<Integer>) t.reverse();
		assertEquals("R(50),I(60),I(70),X(NULL),L(65),X(NULL),I(40),I(45),L(46),X(NULL),I(30),X(NULL),L(20)", r.serialize(), "Should produce \"R(50),I(60),I(70),X(NULL),L(65),X(NULL),I(40),I(45),L(46),X(NULL),I(30),X(NULL),L(20)\"");
	}



	@Test
	public void serial6() {
		BST<Integer> t = setup6();
		
		assertEquals("R(40),I(30),X(NULL),I(35),I(32),X(NULL),L(33),X(NULL),X(NULL)", t.serialize(), "Should produce \"R(40),I(30),X(NULL),I(35),I(32),X(NULL),L(33),X(NULL),X(NULL)\"");
	}
	
	@Test
	public void serial7() {
		BST<Integer> t = setup7();
		
		assertEquals("R(40),I(30),X(NULL),L(35),X(NULL)", t.serialize(), "Should produce \"R(40),I(30),X(NULL),L(35),X(NULL)\"");
	}

	@Test
	public void serial8() {
		BST<Integer> t = setup8();
		
		assertEquals("R(40),I(30),X(NULL),I(35),L(32),X(NULL),X(NULL)", t.serialize(), "Should produce \"R(40),I(30),X(NULL),I(35),L(32),X(NULL),X(NULL)\"");
	}

	@Test
	public void del9() {
		BST<Integer> t = setup9();

		t.delete(5);
		assertTrue(!t.contains(5), "Could not remove 5");
	}

	@Test
	public void pc9() {
		BST<Integer> t = setup9();

		assertTrue(t.contains(5), "Could not find 5");
	}

	@Test
	public void iot9() {
		BST<Integer> t = setup9();

		assertEquals("2:4:5:8:10:12", t.inOrderTraversal(), "Should produce \"2:4:5:8:10:12\"");
	}

	@Test
	public void iot9andDel() {
		BST<Integer> t = setup9();

		t.delete(50);

		assertEquals("2:4:5:8:10:12", t.inOrderTraversal(), "Should produce \"2:4:5:8:10:12\"");
	}



	

	

	


    
}

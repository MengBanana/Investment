/**
 * Investment_test.java
 *
 *  @since July 28, 2018
 *  @author Xinmeng Zhang
 */

package edu.northeastern.cs_5006;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.FixMethodOrder;  
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runners.MethodSorters;

import edu.northeastern.cs_5006.Investment.Bond;

import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Test functions for Investment class
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class Investment_test {
	
	/**
	 * Test Bond class.
	 */
	@Test
	public void test_0010_Invenstment() {
		final float err = 0.0001f;
		
		// bond A, 100 shares available, cost 1 per shar, yield is 2%
		Bond A = new Bond("A", 100, 1, (float)0.02);		
		assertEquals("A", A.name);
		assertEquals(100.0f, A.shares, 0.0f);
		assertEquals(1.0f, A.cost, 0.0f);
		assertEquals(0.02f, A.yield, 0.0f);
		assertEquals(100.0f, A.totalCost(), err);
		assertEquals(2.0f, A.totalProfit(), err);

		// bond B, 100 shares available, cost 2 per shar, yield is 4%
		Bond B = new Bond("B", 100, 2, (float)0.04);		
		assertEquals("B", B.name);
		assertEquals(100.0f, B.shares, 0.0f);
		assertEquals(2.0f, B.cost, 0.0f);
		assertEquals(0.04f, B.yield, 0.0f);
		assertEquals(200.0f, B.totalCost(), err);
		assertEquals(8.0f, B.totalProfit(), err);
	}
	

	/**
	 * Test invest function in Investment class.
	 */
	@Test
	public void test_0020_Invenstment() {
		final float err = 0.0001f;
		
		// test if used all of total, and profit is max as expected, and portforlio is right
		// bond A, 100 shares available, cost 1 per shar, yield is 2%
		Bond A = new Bond("A", 100, 1, (float)0.02);
		// bond B, 100 shares available, cost 2 per shar, yield is 4%
		Bond B = new Bond("B", 100, 2, (float)0.04);
		// bond C, 100 shares available, cost 4 per shar, yield is 6%
		Bond C = new Bond("C", 100, 4, (float)0.06);
		// bond D, 100 shares available, cost 5 per shar, yield is 8%
		Bond D = new Bond("D", 100, 5, (float)0.08);
		List<Bond> opportunities1 = new ArrayList<>();
		opportunities1.add(A);
		opportunities1.add(B);
		opportunities1.add(C);
		opportunities1.add(D);
		float total1 = 1000;
		float totalCost1 = 0;
		float totalProfit1 = 0;
		Collection<Bond> bonds = Investment.invest(total1, opportunities1);
		for (Bond b:bonds) {
			totalCost1 += b.totalCost();
			totalProfit1 += b.totalProfit();
		}
		assertEquals(1000.0f, totalCost1, err);
		assertEquals(68.0f, totalProfit1, err);
		Iterator<Bond> itr = bonds.iterator();
		
		//Portforlio should be:
		//Name: D, Shares: 100.00, Cost per share: 5.00, Cost: 500.00, Yeild: 0.08, Profit: 40.00
		//Name: C, Shares: 100.00, Cost per share: 4.00, Cost: 400.00, Yeild: 0.06, Profit: 24.00
		//Name: B, Shares: 50.00, Cost per share: 2.00, Cost: 100.00, Yeild: 0.04, Profit: 4.00
		//size is 3
		assertEquals(3, bonds.size());
		
		Bond b1 = itr.next();
		assertEquals("D", b1.name);
		assertEquals(100.0f, b1.shares, 0.0f);
		assertEquals(5.0f, b1.cost, 0.0f);
		assertEquals(0.08f, b1.yield, 0.0f);
		assertEquals(500.0f, b1.totalCost(), err);
		assertEquals(40.0f, b1.totalProfit(), err);
		
		Bond b2 = itr.next();
		assertEquals("C", b2.name);
		assertEquals(100.0f, b2.shares, 0.0f);
		assertEquals(4.0f, b2.cost, 0.0f);
		assertEquals(0.06f, b2.yield, 0.0f);
		assertEquals(400.0f, b2.totalCost(), err);
		assertEquals(24.0f, b2.totalProfit(), err);
		
		Bond b3 = itr.next();
		assertEquals("B", b3.name);
		assertEquals(50.0f, b3.shares, 0.0f);
		assertEquals(2.0f, b3.cost, 0.0f);
		assertEquals(0.04f, b3.yield, 0.0f);
		assertEquals(100.0f, b3.totalCost(), err);
		assertEquals(4.0f, b3.totalProfit(), err);
		
		
		// test if used all of total, and profit is max as expected, and portforlio is right
		Bond E = new Bond("E", 100, 1, (float)0.02);
		// bond F, 100 shares available, cost 2 per shar, yield is 4%
		Bond F = new Bond("F", 100, 2, (float)0.04);
		// bond G, 100 shares available, cost 10 per shar, yield is 6%
		Bond G = new Bond("G", 100, 9, (float)0.06);
		List<Bond> opportunities2 = new ArrayList<>();
		opportunities2.add(E);
		opportunities2.add(F);
		opportunities2.add(G);
		float total2 = 1000;
		float totalCost2 = 0;
		float totalProfit2 = 0;
		Collection<Bond> bonds2 = Investment.invest(total2, opportunities2);
		for (Bond b:bonds2) {
			totalCost2 += b.totalCost();
			totalProfit2 += b.totalProfit();
		}
		assertEquals(1000.0f, totalCost2, err);
		assertEquals(58.0f, totalProfit2, err);
		Iterator<Bond> itr2 = bonds2.iterator();
		
		//Portforlio should be:
		//Name: G, Shares: 100.00, Cost per share: 9.00, Cost: 900.00, Yeild: 0.06, Profit: 54.00
		//Name: F, Shares: 50.00, Cost per share: 2.00, Cost: 100.00, Yeild: 0.04, Profit: 4.00
		//size is 2
		assertEquals(2, bonds2.size());
		
		Bond b5 = itr2.next();
		assertEquals("G", b5.name);
		assertEquals(100.0f, b5.shares, 0.0f);
		assertEquals(9.0f, b5.cost, 0.0f);
		assertEquals(0.06f, b5.yield, 0.0f);
		assertEquals(900.0f, b5.totalCost(), err);
		assertEquals(54.0f, b5.totalProfit(), err);
		
		Bond b6 = itr2.next();
		assertEquals("F", b6.name);
		assertEquals(50.0f, b6.shares, 0.0f);
		assertEquals(2.0f, b6.cost, 0.0f);
		assertEquals(0.04f, b6.yield, 0.0f);
		assertEquals(100.0f, b6.totalCost(), err);
		assertEquals(4.0f, b6.totalProfit(), err);
		
	}
	
	/**
	 * Get a string representation of the Failure object.
	 * @param failure the failure
	 * @return string representation of the failure
	 */
	static String getFailureString(Failure failure) {
		String fs = failure.toString();
		for (StackTraceElement ste : failure.getException().getStackTrace()) {
			if (!ste.getClassName().startsWith("org.junit")) {
				fs += " line " +  ste.getLineNumber();
				break;
			}
		}
		return fs;
	}
	
	/**
	 * Run the tests in this class.
	 * 
	 * @param args the program arguments
	 */
	public static void main(String[] args) {
	    Result result = JUnitCore.runClasses(Investment_test.class);
	    System.out.println("[Unit Test Results]");
	    System.out.println();

	    if (result.getFailureCount() > 0) {
	    	System.out.println("Test failure details:");
		    for (Failure failure : result.getFailures()) {
		    	System.out.println(getFailureString(failure));
		    }
		    System.out.println();
	    }
	    
	    int passCount = result.getRunCount()-result.getFailureCount()-result.getIgnoreCount(); 
	    System.out.println("Test summary:");
	    System.out.println("* Total tests = " + result.getRunCount());
	    System.out.println("* Passed tests: " + passCount);
	    System.out.println("* Failed tests = " + result.getFailureCount());
	    System.out.println("* Inactive tests = " + result.getIgnoreCount());

	}
}

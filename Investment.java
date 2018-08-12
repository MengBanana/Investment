/**
 * Investment.java
 *
 *  @since July 28, 2018
 *  @author Xinmeng Zhang
 */

package edu.northeastern.cs_5006;
import java.util.ArrayList;
import java.util.Collection;
//import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * This Investment class works as a bond investor with a total amount of money and want to fully invest it
 * in a portfolio that provides the greatest return on your investment.
 *
 */
public class Investment {
	
	/**
	 * The Bond class represents each bond in investment opportunites we can choose from. 
	 * Bond has a string field name, float filed shares and cost for cost per share, 
	 * and yield for the yield per share
	 *
	 */
	public static class Bond {
		
		/** The name of the bond*/
		public final String name;
		
		/** The number of shares available*/
		public final float shares;
		
		/** The cost per share*/
		public final float cost;
		
		/** The yield per share*/
		public final float yield;
		
		/**
		 * Constructor for Bond
		 * 
		 * @param name the name of the bond
		 * @param shares the number of shares available
		 * @param cost the cost per share
		 * @param yeild the yield per share e.g. 0.06 for a 6% yield
		 */
		public Bond (String name, float shares, float cost, float yeild) {
			this.name = name;
			this.shares = shares;
			this.cost = cost;
			this.yield = yeild;
		}
		
		/**
		 * Total cost of the bonds offering
		 * 
		 * @return the total cost of the bonds offering
		 */
		public float totalCost () {
			return shares*cost;
		}
		
		/**
		 * Total profit of the bonds offering
		 * 
		 * @return the total profit of the bonds offering
		 */
		public float totalProfit () {
			return shares*cost*yield;
		}

	}
	
	/*
	// override comparator, no more needed if use lambda expression
	public static class MyComparator implements Comparator<Bond> { 
	    @Override
	    public int compare(Bond a, Bond b){
	    //redefine compare
	        return b1.yield<b2.yield ? 1: b1.yield>b2.yield? -1:0;
	    }
	}
	*/
	
	/**
	 * invest function that takes a float value total for the total amount to be invested
	 * and a collection of Bonds representing the investment opportunites. The function 
	 * uses a greedy algorithm to compute the optimal investment and returns a portfolio of
	 * Bonds representing the actual investments made
	 * 
	 * @param total the total amount to be invested
	 * @param oppotunities the investment oppotunites
	 * @return a portfoli of Bonds representing the actual invenstments made
	 */
	public static Collection<Bond> invest(float total, Collection<Bond> oppotunities) {
		//Lambda Expression works
		//yield is a float, but compare function returns integr, so multiply by 100 and cast to int works
		//shouldn't use b2.yield - b1.yield
		PriorityQueue<Bond> maxHeap = new PriorityQueue<>((b1, b2) -> (b1.yield<b2.yield ? 1: b1.yield>b2.yield? -1:0));
		//PriorityQueue<Bond> maxHeap = new PriorityQueue<>(100, new MyComparator());
		//Enhanced for loop to iterate bonds
		for (Bond b: oppotunities) {
			maxHeap.offer(b); //offer to heap, and will be sorted from highest yield to lowest yield
		}
		//result list is a collection
		List<Bond> res = new ArrayList<>();
		//while heap is not empty, keep looking for the next bond to invest
		while (!maxHeap.isEmpty()) {
			if (total - maxHeap.peek().totalCost() <= 0) { 
				//if we can't fully invest in the top bond in heap, break loop and will calculate the fraction  to invest
				break;
			}
			//if we can fully inest in the bond, add it to result, and update remain total
			Bond temp = maxHeap.poll();
			res.add(temp);
			total -= temp.totalCost();
		}
		//if heap is empty, may still have some remaining money in total, but no more bonds to invest
		if (maxHeap.isEmpty()) {
			return res;
		}
		//else we still have remaining total, need to calculate the fraction to invest
		Bond temp = maxHeap.poll();
		float s = total / temp.cost; // share we could buy
		Bond b = new Bond(temp.name, s, temp.cost, temp.yield); 
		// because fields are final we can't change, new a bond with the buyable shares
		res.add(b);
		return res;
	}
	
	/**
	 * printBond function prints the details of the bond
	 * @param b the bond
	 */
	public static void printBond(Bond b) {
		System.out.printf("Name: %s, Shares: %.2f, Cost per share: %.2f, Cost: %.2f, Yeild: %.2f, Profit: %.2f\n", 
				b.name, b.shares, b.cost, b.totalCost(), b.yield, b.totalProfit());
	}
	
	/**
	 * The main function to test the invest() function
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// bond A, 100 shares available, cost 1 per shar, yield is 2%
		Bond A = new Bond("A", 100, 1, (float)0.02);
		// bond B, 100 shares available, cost 2 per shar, yield is 4%
		Bond B = new Bond("B", 100, 2, (float)0.04);
		// bond C, 100 shares available, cost 4 per shar, yield is 6%
		Bond C = new Bond("C", 100, 4, (float)0.06);
		// bond D, 100 shares available, cost 5 per shar, yield is 8%
		Bond D = new Bond("D", 100, 5, (float)0.08);
		List<Bond> opportunities = new ArrayList<>();
		opportunities.add(A);
		opportunities.add(B);
		opportunities.add(C);
		opportunities.add(D);
		float total = 1000;
		System.out.printf("Total to invesnt: %.2f\n", total);
		float totalCost = 0;
		float totalProfit = 0;
		for (Bond b:invest(total, opportunities)) {
			totalCost += b.totalCost();
			totalProfit += b.totalProfit();
			printBond(b);
		}
		System.out.printf("Total cost: %.2f, Total profit: %.2f", totalCost, totalProfit);		
	}
}


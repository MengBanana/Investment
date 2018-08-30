# Investment

This Investment program works as a bond investor with a total amount of money and want to fully invest it
in a portfolio that provides the greatest return on your investment. 

Class Bond has a string field name for the name of the bond, and float fields shares for the number of shares available, cost for the 
cost per share, and yield for the yield per share (e.g. 0.06 for a 6% yield). All of the fields are public and constant. It also has 
public float convenience functions totalCost() for the total cost of the bond offering (shares * cost), and totalProfit() for the total 
profit of the bond offering (shares * cost * yield).

With the implementation of greedy algorithms, override the compare function using lambda expression. Store all the bonds in a priorityqueue/heap in descending yeild order. Invest as much as possible from the top of the heap.


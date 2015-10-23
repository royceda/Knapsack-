/*********************************************
 * OPL 12.6.0.0 Model
 * Author: Charlie Brown
 * Creation Date: 22 oct. 2015 at 20:36:12
 *********************************************/

int n = ...;
int capacity = ...;

int profits[1..n] = ...;
int weights[1..n] = ...;

dvar int+ x[1..n] in  0..1;
dvar float+ z;

maximize
  sum(i in 1..n) x[i]*profits[i];
  
  subject to{
  	sum(i in 1..n) weights[i]*x[i] <= capacity;
  }
  
 
 //z = sum(i in 1..n) x[i]*profits[i];
  
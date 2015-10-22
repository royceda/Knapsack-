/*********************************************
 * OPL 12.6.0.0 Data
 * Author: Charlie Brown
 * Creation Date: 20 oct. 2015 at 13:40:46
 *********************************************/

int NbItems = ...;
int NbResources = ...;
range Items = 1..NbItems;
range Resources = 1..NbResources;
int Capacity[Resources] = ...;
int Value[Items] = ...;
int Use[Resources][Items] = ...;
int MaxValue = max(r in Resources) Capacity[r];


dvar int Take[Items] in 0..MaxValue;

maximize
  sum(i in Items) Value[i] * Take[i];

subject to {
  forall( r in Resources )
    ct:
      sum( i in Items )
        Use[r][i] * Take[i] <= Capacity[r];
}

#2D Knapsack: dynamic program
def Z(a, b, r, l, w, p):
    if r > 0:
        return max(Z(a, b, r-1, l, w, p), (Z(a-l[r], b-w[r], r-1, l, w, p) + p[r]));
    elif r == 0:
        if a-l[1] >= 0 and b-w[1] >= 0:
            return p[r];
        else:
            return 0;
                
  
  
r = 4;
p = [1, 2, 2, 3, 5];
w = [2, 9, 4, 6, 8];
l = [1, 9, 2, 4, 8];
a = 30;
b = 20;

res = Z(a, b, r, l, w , p);
print res;

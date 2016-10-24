import numpy as np

n = 7
W = 50

p = [70, 20, 39, 35, 7, 5, 9]
w = [31, 10, 20, 18, 4, 3, 6]


# n : number of items
# p : prices
# w : weights
# W : weight

def dynamic_knapsack(n, p, w, W ):
    z = np.zeros((W+1, n))
    x = np.zeros(n)
    liste = []

    #init
    for l in range(0, W+1):
        if l >= w[0]:
            z[l][0] = p[0]
            if 0 not in liste:
                liste.append(0)
        else:
            if 0 in liste:
                liste.remove(0)

    #forward
    for j in range(0, W+1):
        for i in range(1, n):
            if j-w[i] >= 0:
                z[j][i] = max( z[j][i-1], p[i] + z[j-w[i]][i-1])
                if z[j][i] == p[i] + z[j-w[i]][i-1]:
                    if i not in liste:
                        liste.append(i)
                else:
                    if i in liste:
                        liste.remove(i)
            else:
                z[j][i] = z[j][i-1]
                if i in liste:
                    liste.remove(i)



    #print track
    for i in range(0, W+1):
        stri = ""
        for j in range(0, n):
            stri += str(z[i][j])+" "
        print stri

    return liste

liste = dynamic_knapsack(n, p, w, W)
# Printing
print "\n"
print str(liste)

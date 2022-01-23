# GaleShapley
Implementing the Gale-Shapley algorithm for stable matching with an O(n2) running time. In this version, people will be adopting pets. Each person will adopt exactly one pet and each pet will be adopted by exactly one person. Both people and pets have preference lists that you will read from a file. The file will be formatted as follows:

Line 1: Number of people/pets (n)
Lines 2 to n+1: Names of people
Lines n+2 to 2n+1: Preference lists of people using indices, not names (n preferences per line)
Lines 2n+2 to 3n+1: Names of pets
Lines 3n+2 to 4n+1: Preference lists of pets using indices, not name (n preferences per line)


.txt file:

Owners name with their preferences:
5
Archie
Betty
Clark
Deborah
Earl
2 1 4 5 3
4 2 1 3 5
2 5 3 4 1
1 4 3 2 5
2 4 1 5 3


Pet names with their prefrences:
Buster
Mittens
Princess
Fluffy
Felix
5 1 2 4 3
3 2 4 1 5
2 3 4 5 1
1 5 4 3 2
4 2 5 3 1

Desired output:
Archie / Buster
Betty / Princess
Clark / Mittens
Deborah / Felix
Earl / Fluffy

''' 
Given a collection of distinct integers, return all possible 
permutations.

Example:

Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

Solution:
Using Heap's algorithm, a single swap for each generated permutation is
achieved with O(n!) operations for n! permutations. The algorithm is
similar to the backtracking solution, but does not require that elements
are swapped back after each recursive call. See Steinhaus-Johnson-
Trotter algorithm for an additional permutation specific solution.
'''

def heaps_perm(n: int, base: list, returnList: list) -> None:
    if n == 0: 
        returnList.append(base.copy())
        return

    for i in range(0, n): 
        heaps_perm(n -1, base, returnList)
        if n % 2 == 0: 
            base[0], base[n] = base[n], base[0]
        else:

            base[i], base[n] = base[n], base[i]

    heaps_perm(n - 1, base, returnList)

if __name__ == "__main__": 
    base = [4, 3, 2, 1]

    permsList = list()
    heaps_perm(len(base) - 1, base, permsList)

    for perm in permsList:
        print(perm)


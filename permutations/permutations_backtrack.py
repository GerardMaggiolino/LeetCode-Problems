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
Generates n! permutations in O(n!) time, with two swaps per permutation
generated. Fix the last element as the i'th element for all i elements
in the list, and recursively call on this fixed i'th element. Requires 
swapping i'th element twice per recursive call. 
'''

def backtrack_perm(n: int, base: list, returnList: list) -> None:
    if n == 0: 
        returnList.append(base.copy())
        return

    for i in range(n):
        base[n], base[i] = base[i], base[n]
        backtrack_perm(n-1, base, returnList)
        base[i], base[n] = base[n], base[i]

    backtrack_perm(n-1, base, returnList) 

if __name__ == "__main__": 
    base = [4, 3, 2, 1]

    permsList = list()
    backtrack_perm(len(base) - 1, base, permsList)

    for perm in permsList:
        print(perm)


'''
Frequency mapped in dict for each element of passed list, O(n) worst
case time, space (with all unique elements). 
Bucket sort based on frequency of elements - no inner sort of elements 
required, where bucket sort may have degraded to efficiency of the 
inner algorithm. Thus, O(n) time, space. 
Returned list populated by traversal of buckets until k returned 
elements. O(n) time, space. 

Thus, algorithm runs in linear time and space, with n as number of 
elements of passed nums list. 
'''

def topKFrequent(nums: list, k: int) -> list:

    total_freqs = dict()
    outer_bucket = [None] * (len(nums) + 1)
    rlist = list()

    for number in nums: 
        total_freqs[number] = total_freqs.setdefault(number, 0) + 1

    for key in total_freqs.keys(): 
        if not outer_bucket[total_freqs[key]]:
            outer_bucket[total_freqs[key]] = [] 
        outer_bucket[total_freqs[key]].append(key)

    for inner in reversed(outer_bucket): 
        if inner: 
            for val in inner: 
                if len(rlist) < k: 
                    rlist.append(val)
                else: 
                    return rlist
        
    return rlist

if __name__ == "__main__": 
    print(topKFrequent([1, 1, 1, 2, 2, 3, 3, 4], 1))
    print(topKFrequent([2, 4, 8, 8, 16, 16, 16, 32, 32, 32, 32], 3))

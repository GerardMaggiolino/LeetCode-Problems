//
// Find the kth largest element in an unsorted array. Note that it is the 
// kth largest element in the sorted order, not the kth distinct element.
//
// Example 1:
// Input: [3,2,1,5,6,4] and k = 2
// Output: 5
//
// Example 2:
// Input: [3,2,3,1,2,4,5,5,6] and k = 4
// Output: 4
//
// Note: 
// You may assume k is always valid, 1 ≤ k ≤ array's length.
// 
// Solution: 
// Using randomized Quickselect Algorithm, with Hoare partition scheme. Average
// time complexity of O(n), worst case O(n^2). Constant space usage, no 
// additional memory allocated from initial array. Comparable worst case to
// Quicksort solution, but much better average case. Deterministic select
// available with median of medians, but larger overhead for average case.
// Degrades to O(n^2) if randomly picked value is always max while 
// searching for min, or always min while searching for max. 
// 
// K-greatest is (n - k) index in sorted array. Partition arr into greatest
// and lowest. Based on index of partition, recurse into only greatest
// or lowest side. Median of three can be used rather than random for slightly
// more deterministic time (higher probability of better partitions), see 
// KLargestQuicksort solution. 
//
// Reference: https://www.cs.cmu.edu/~avrim/451f13/lectures/lect0829.pdf

import java.util.Random; 

public class KLargestQuickselect { 

    private static final Random rand = new Random();

    public int findKthLargest(int [] nums, int k) {  
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }  

    private int quickSelect(int [] nums, int lBound, int rBound, int k) {  

        if (lBound == rBound) { 
            return nums[lBound]; 
        }

        int pivotInd = partition(nums, lBound, rBound);   

        if (pivotInd < k) { 
            return quickSelect(nums, pivotInd + 1, rBound, k);  
        }
        else { 
            return quickSelect(nums, lBound, pivotInd, k); 
        }


    }  

    private int partition (int [] nums, int lBound, int rBound) {  

        int pivotVal = nums[rand.nextInt(rBound - lBound + 1) + lBound];
        int tmp; 
        
        lBound -= 1; 
        rBound += 1; 

        // Hoare partion method
        while (true) {  

            do {  
                lBound += 1; 
            } while (nums[lBound] < pivotVal);

            do {  
                rBound -= 1; 
            } while (nums[rBound] > pivotVal); 

            if (lBound < rBound) {
                tmp = nums[lBound]; 
                nums[lBound] = nums[rBound]; 
                nums[rBound] = tmp; 
            }
            else {
                return rBound;
            }

        }

    }

    public static void main (String [] args) { 
        KLargestQuickselect obj = new KLargestQuickselect(); 

        int [] test1 = {1, 5, 3, 10, 85, -5, 8, 3};
        int [] test2 = {30, 31, -1, -5, -8, 2, 3, 5, 6}; 
        int [] test3 = {7, 6, 5, 4, 3, 2, 1};

        // Expected 10
        System.out.println(obj.findKthLargest(test1, 2));
        // Expected -1
        System.out.println(obj.findKthLargest(test2, 7));
        // Expected 6
        System.out.println(obj.findKthLargest(test3, 2)); 
    }



}

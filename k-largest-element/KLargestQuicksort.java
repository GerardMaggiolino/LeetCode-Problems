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
// Quicksort algorithm followed by access at k. 
//
// Hoare partition scheme used to sort in expected O(nlogn) time with median
// of 3 (lBound, mid, rBound). Possible to degrade to O(n^2) time, if three
// picked values are consistently min / max of array (unlikely). Worst case
// O(nlogn) is possible with deterministic linear time using median of medians 
// algorithm, but overhead incurred is large. Constant space usage. 

public class KLargestQuicksort { 

    public int findKthLargest (int [] nums, int k) { 
        quickSort(nums, 0, nums.length - 1); 
        return nums[nums.length - k]; 
    }

    private void quickSort (int [] nums, int lBound, int rBound) { 

        if (lBound >= rBound) { 
            return; 
        }

        int pivot = partition(nums, lBound, rBound); 

        quickSort(nums, lBound, pivot); 
        quickSort(nums, pivot + 1, rBound); 

    }

    private int partition (int [] nums, int lBound, int rBound) { 

        int tmp = (rBound - lBound) / 2 + lBound; 
        int pivotVal = nums[tmp];

        // Swap median to middle and save in pivotVal
        if (nums[lBound] > nums[rBound]) { 
            if (nums[rBound] >= nums[tmp]) {
                pivotVal = nums[rBound];
                nums[rBound] = nums[tmp]; 
                nums[tmp] = pivotVal; 
            }
            else if (nums[lBound] < nums[tmp]) {
                pivotVal = nums[lBound]; 
                nums[lBound] = nums[tmp]; 
                nums[tmp] = pivotVal; 
            }
        }
        else if (nums[lBound] > nums[tmp]) { 
            pivotVal = nums[lBound]; 
            nums[lBound] = nums[tmp]; 
            nums[tmp] = pivotVal; 
        }
        else if (nums[rBound] < nums[tmp]){ 
            pivotVal = nums[rBound];
            nums[rBound] = nums[tmp]; 
            nums[tmp] = pivotVal; 
        }

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
        KLargestQuicksort obj = new KLargestQuicksort(); 

        int [] test1 = {1, 5, 3, 10, 85, -5, 8, 3};
        int [] test2 = {30, 31, -1, -5, -8, 2, 3, 5, 6}; 

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

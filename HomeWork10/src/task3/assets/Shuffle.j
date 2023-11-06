import java.util.Arrays;

public class Solution {
    public int[] shuffle(int[] nums, int n) {
        int[] shuffled = new int[n*2];
        for(int i=0; i<n; i++){
            shuffled[i*2] = nums[i];
            shuffled[i*2 + 1] = nums[n + i];
        }
        return shuffled;
    }
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,4,3,2,1};
        int[] shuffled = new Solution().shuffle(arr, arr.length / 2);
        System.out.println(Arrays.toString(shuffled));
    }
}
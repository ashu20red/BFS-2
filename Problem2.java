/** Rotting Oranges-BFS Approach
In a given grid, each cell can have one of three values:

the value 0 representing an empty cell;
the value 1 representing a fresh orange;
the value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.

Example 1:
Input: [[2,1,1],[1,1,0],[0,1,1]]
Output: 4

Example 2:
Input: [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.

Example 3:
Input: [[0,2]]
Output: 0
Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.
 

Note:

1 <= grid.length <= 10
1 <= grid[0].length <= 10
grid[i][j] is only 0, 1, or 2. */

// Time Complexity : O(mn) as it traverses each node exactly once in the matrix m*n
// Space Complexity : O(n) as it uses the queue and push the elements at each level atleast once
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
//we will be handling with BFS Approach
//we will initialise our queue and we keep iterating all the elements(m*n) level by level traversal
//we will keep a count for 2 variables: Fresh oranges and Minutes elapsed
//at the end, we have to return minutes elapsed
//there are 3 cases to look for rotting(it starts with 4 directionally-right, left, up and bottom) to take place
//here rotten oranges-2, fresh orange-1, empty cell-0
//case1) if after the rotting operation, no fresh oranges are left, return the minutes 
//case2) if atleast one fresh orange is still left, return -1(impossible)
//case3) if there are no fresh oranges only at the start in the grid[][], then return 0

//NOTE(explain to interviewer): we will be maintaining a dirs array to k

class Solution {
    public int orangesRotting(int[][] grid) {
        //initialise queue to keep indices in the type
        Queue<int []> q = new LinkedList<>();
        //boundary conditions
        int m = grid.length;
        int n = grid[0].length;
        //edge case 1-if the grid doesn't exist, return 0
        if(m == 0) return 0;
        int fresh = 0;
        //iterating the whole grid
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                //if the cell has rotten orange(2), add to the queue with array indices i,j
                if(grid[i][j] == 2) q.add(new int[] {i,j});
                //if the cell has fresh oranges, increment fresh variable
                if(grid[i][j] == 1) fresh++;
            }
        }
        //edge case 2-if there are no fresh oranges in the grid, return 0
        if(fresh == 0) return 0;
        //initialise dirs array- to traverse in all 4 directions
        //{0,1}-right, {1,0}-bottom, {-1,0}-up, {0,-1}-left
        int[][] dirs = {{0,1}, {1,0}, {-1,0},{0,-1}};
        int minutes = 0;
        while(!q.isEmpty()){
            //initialise the size of queue
            int size = q.size();
            for(int k = 0; k < size; k++){
                //process rotten orange cell by removing from the queue
                int[] rotten = q.poll();
                //process all neighours for rotten orange
                for(int [] dir: dirs){
                    //for example- ifrotten is at[1,1](i,j), then add dirs array
                    int i = dir[0] + rotten[0];
                    int j = dir[1] + rotten[1];
                    if(i >= 0 && i < m && j >= 0 && j < n && grid[i][j] == 1){
                        //1. adding the neighbours and push to the queue
                        q.add(new int[] {i,j});
                        //2. change the fresh orange to rotten(2)
                        grid[i][j] = 2;
                        //3. decrement fresh orange count to 1
                        fresh--;
                    }
                }
            }
            //increment minutes elapsed after every level(or depth)
            minutes++;
        }
        //if total count of fresh oranges at the end is not 0, return -1(impossible)
        if(fresh != 0) return -1;
        //if the minutes elapsed at the end is greater than 0, then return (minutes-1), else return 0
        return minutes > 0 ? minutes-1 : 0;
    }
}
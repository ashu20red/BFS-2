/** Cousins in binary tree-DFS Approach
In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.

Two nodes of a binary tree are cousins if they have the same depth, but have different parents.

We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the tree.

Return true if and only if the nodes corresponding to the values x and y are cousins.

 

Example 1:


Input: root = [1,2,3,4], x = 4, y = 3
Output: false
Example 2:


Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
Output: true
Example 3:



Input: root = [1,2,3,null,4], x = 2, y = 3
Output: false
 

Note:

The number of nodes in the tree will be between 2 and 100.
Each node has a unique integer value from 1 to 100. */

// Time Complexity : O(n) as it traverses each node exactly once
// Space Complexity : O(h) as it uses the recursive stack and put all elements at a time of height/depth h
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
//we can handle this problem with BFS and DFS both
//DFS approach- we are going to maintain 4 variables here for each x and y nodes at depth(max)
//variables: parent(x), parent(y), depth(x), depth(y)
//then we check or compare each x and y nodes for these variables

class Solution {
    TreeNode x_parent = null;
    TreeNode y_parent = null;
    int x_depth = -1;
    int y_depth = -1;
    public boolean isCousins(TreeNode root, int x, int y) {
        helper(root,x, y, 0, null);
        //checking if both depths are equal and both parents are not equal
        return x_depth == y_depth && x_parent != y_parent;

    }
    private void helper(TreeNode root, int x, int y, int depth, TreeNode parent){
        if(root == null) return;
        //base case
        if(root.val == x){
            x_depth = depth;
            x_parent = parent;
        }
        if(root.val == y){
            y_depth = depth;
            y_parent = parent;
        }
        helper(root.left, x, y, depth+1, root);
        helper(root.right, x, y, depth+1, root);
    }
}
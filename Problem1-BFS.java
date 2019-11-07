/** Cousins in binary tree-BFS Approach
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
// Space Complexity : O(n) as it uses the queue and push the elements at each level atleast once
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
//we can handle this problem with BFS and DFS both
//BFS approach(explain this to interviewer)- check at each level, if the both nodes exist at the same level and they don't have the same parents
//logic: case1) checking if left and right child are equal to x and y(if node.left == x and node.right == y, then return false)
//maintaining two booleans x_found, y_found and keep size as well
//traverse each nodes at each level and check for case1) logic and keep pushing elements into the queue
//at the end, check if x_found and y_found are true, then check out

class Solution {
    public boolean isCousins(TreeNode root, int x, int y) {
        //edge case-if graph doesn't exist
        if(root == null) return false;
        //initialising queue and push elements level by level
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            //maintaining boolean x and y found variables
            boolean x_found = false;
            boolean y_found = false;
            //taking out each node one by one
            for(int i = 0; i < size; i++){
                TreeNode curr = q.poll();
                //checking if curr value are equal to x and y
                if(curr.val == x) x_found = true;
                if(curr.val == y) y_found = true;
                if(curr.left != null && curr.right != null){
                    //if both the children exist for a node and if the values are equal then return false
                    if(curr.left.val == x && curr.right.val == y) return false;
                    if(curr.left.val == y && curr.right.val == x) return false;
                }
                //else, keep adding current node to the queue
                if(curr.left != null) q.add(curr.left);
                if(curr.right != null) q.add(curr.right);
            }
            //if both the values x and y are found then return true
            if(x_found && y_found) return true;
        }
        //if either of the values are not found, then return false
        return false;
    }
}

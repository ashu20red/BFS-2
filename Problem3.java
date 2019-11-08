/** Employee Importance-BFS Approach
You are given a data structure of employee information, which includes the employee's unique id, his importance value and his direct subordinates' id.

For example, employee 1 is the leader of employee 2, and employee 2 is the leader of employee 3. They have importance value 15, 10 and 5, respectively. Then employee 1 has a data structure like [1, 15, [2]], and employee 2 has [2, 10, [3]], and employee 3 has [3, 5, []]. Note that although employee 3 is also a subordinate of employee 1, the relationship is not direct.

Now given the employee information of a company, and an employee id, you need to return the total importance value of this employee and all his subordinates.

Example 1:

Input: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
Output: 11
Explanation:
Employee 1 has importance value 5, and he has two direct subordinates: employee 2 and employee 3. They both have importance value 3. So the total importance value of employee 1 is 5 + 3 + 3 = 11.
 

Note:

One employee has at most one direct leader and may have several subordinates.
The maximum number of employees won't exceed 2000. */

// Time Complexity : O(n) (as it traverses each node exactly once in queue(O(n)) and O(1) for lookup in hashmap)
// Space Complexity : O(n) asymptotically as it uses the queue(O(n)) and push the elements at each level atleast once and hashmap(O(n))
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
//we will be handling this problem with BFS using queue
//BFS Approach- 1. we initialise queue(of type integer) and store each employee id 2. we will maintain hashmap(as its better in time complexity for O(1) lookup) storing employee object
//we will follow level order traversal and we process first eid from queue and retrieve eid from hashmap
//after mapping, we retrieve importance at every eid and increment the result(0)
//then keep traversing subordinates list in the queue for next level and repeat the above process
//at the end, output the final result(in terms of importance)

/*
// Employee info
class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
};
*/

//Employee is an object -[1,5,[2,3]], where 1-Employee ID, 5-Employee importance, [2,3] are subordinates for Employee 1
class Solution {
    public int getImportance(List<Employee> employees, int id) {
        //edge case- if employee object doesn't exist
        if(employees == null || employees.size() == 0) return 0;
        //initialising hashmap to store employee object and its corresponding IDs
        HashMap<Integer, Employee> map = new HashMap<>();
        //initialising queue of type integer-more efficient(than Employee type) as we have Ids inside the queue
        Queue<Integer> q = new LinkedList<>();
        for(Employee e : employees){
            //add employee ids inside the hashmap
            map.put(e.id, e);
        }
        //adding employee ids to the queue
        q.add(id);
        int result = 0;
        while(!q.isEmpty()){
            //retrieve eid from queue
            int eid = q.poll();
            //search the same eid in the hashmap and assign it to e
            Employee e = map.get(eid);
            //increment the result by calling employee object
            result += e.importance;
            //traverse the subordinate list 
            for(int subId: e.subordinates){
                q.add(subId);
            }
        }
        return result;
    }
}
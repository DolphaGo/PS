SELECT IFNULL((SELECT DISTINCT Salary 
               FROM Employee 
               order by Salary desc
               limit 1,1),null) 
               AS SecondHighestSalary

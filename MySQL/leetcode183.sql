# Write your MySQL query statement below
SELECT c.Name Customers
FROM Customers c LEFT JOIN Orders o
ON c.Id = o.CustomerId
WHERE o.Id is null;

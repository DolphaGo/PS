SELECT Email
FROM yogiyo.Person
GROUP BY Email
HAVING COUNT(Email)>1

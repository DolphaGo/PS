SELECT FirstName, LastName, City, State
from yogiyo.Person
left join Address
on yogiyo.Person.PersonId=Address.PersonId;

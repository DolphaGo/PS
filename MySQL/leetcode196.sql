delete p
from person p, person q
where p.id>q.id and p.email = q.email

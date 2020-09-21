insert into scheduling (created_at, updated_at, date_time, status) values (now(), now(), now(),'PENDING');
insert into message (created_at, updated_at, content, recipient, sender, status, subject, type, scheduling_id) values (now(), now(), 'I am testing my email schedule', 'fulano@recipient.com.br', 'fulano@sender.com.br', 'PENDING', 'Email Test', 'EMAIL', (select max(id) from scheduling));

insert into scheduling (created_at, updated_at, date_time, status) values (now(), now(), now(),'PENDING');
insert into message (created_at, updated_at, content, recipient, sender, status, subject, type, scheduling_id) values (now(), now(), 'I am testing my other email schedule', 'fulano2@recipient.com.br', 'fulano2@sender.com.br', 'PENDING', 'Email Test 2', 'EMAIL', (select max(id) from scheduling));

insert into scheduling (created_at, updated_at, date_time, status) values (now(), now(), now(),'SENT');
insert into message (created_at, updated_at, content, recipient, sender, status, subject, type, scheduling_id) values (now(), now(), 'I am testing my other 3 email schedule', 'fulano3@recipient.com.br', 'fulano3@sender.com.br', 'SENT', 'Email Test 3', 'EMAIL', (select max(id) from scheduling));

insert into scheduling (created_at, updated_at, date_time, status) values (now(), now(), now(),'DELETED');
insert into message (created_at, updated_at, content, recipient, sender, status, subject, type, scheduling_id) values (now(), now(), 'I am testing my other 4 email schedule', 'fulano4@recipient.com.br', 'fulano4@sender.com.br', 'PENDING', 'Email Test 4', 'EMAIL', (select max(id) from scheduling));
insert into scheduling (created_at, updated_at, date_time, status) values (now(), now(), now(),'PENDING');
insert into message (created_at, updated_at, content, recipient, sender, status, subject, type, scheduling_id) values (now(), now(), 'I am testing my email schedule', 'fulano@recipient.com.br', 'fulano@sender.com.br', 'PENDING', 'Email Test', 'EMAIL', (select max(id) from scheduling));

insert into scheduling (created_at, updated_at, date_time, status) values (now(), now(), now(),'PENDING');
insert into message (created_at, updated_at, content, recipient, sender, status, subject, type, scheduling_id) values (now(), now(), 'I am testing my other email schedule', 'fulano2@recipient.com.br', 'fulano2@sender.com.br', 'PENDING', 'Email Test 2', 'EMAIL', (select max(id) from scheduling));
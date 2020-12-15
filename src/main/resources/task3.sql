select * from client;

select * from client where name = 'Stark industries';

insert into client(name, property_type, address, phone_number, contact_person) values
('Stark industries', 'Stark tower', 'NYC', '555-666', 'Jarvis'),
('Evil corp', 'E-tower', 'NYC', '777-123', 'Lotus'),
('Some company', 'state property', 'some address', 'some number', 'some person'),
('OOO consult', 'private property', 'Moscow', '8 999 123 9898', 'Putin');

delete from client where name = 'Stark industries';

select * from credit_type;

select * from credit_type where name = 'consumer';

insert into credit_type(name, conditions, interest_rate, repayment_period_month) values
('consumer', 'have a job', 22, 12*4),
('mortgage', 'get married', 12, 12*10);

delete from credit_type;

delete from credit_type where name = 'consumer';

select * from credit;

insert into credit(credit_type_name, client_name, total_debt, current_debt, open_date) values
('mortgage', 'OOO consult', 100500.44, 50004.123, current_date);

update credit set current_debt = 0, close_date = current_date where credit_type_name = 'consumer' and client_name = 'OOO consult';

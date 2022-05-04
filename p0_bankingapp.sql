-- Banking application P0

create schema Banking_app;


create table user_account(
	"first_name" varchar(20) not null,
	"last_name" varchar(20) not null,
	"username" varchar(25) not null unique,
	"password" varchar(25) not null,
	email varchar(50) not null
);

drop table user_account;

insert into user_account values
('Dorine', 'Pauletto', 'dpauletto3', 'GY8BUb', 'dpauletto3@hubpages.com'),
 ('Arleen', 'Shoesmith', 'ashoesmith4', '5H9aYbvL', 'ashoesmith4@vk.com'),
('Ozzy', 'McFaul', 'omcfaul5', '0VlD4gNpKu', 'omcfaul5@upenn.edu'),
('Janessa', 'Rivenzon', 'jrivenzon6', 'd6E17K5', 'jrivenzon6@weebly.com'),
('Clementina', 'Mannering', 'cmannering7', '38oWu9rNPSq', 'cmannering7@spotify.com');


create table banking_accounts (
id serial primary key,
username varchar(25) not null unique,
account_type varchar(30) not null,
account_balance int not null

);

drop table banking_accounts;

insert into banking_accounts values 
(default, 'dpauletto3', 'checking', 'savings', 1000.00, 500.00),
(default, 'ashoesmith4', 'checking', null, 1000.00, null),
(default, 'omcfaul5', 'checking', 'other_checking', 12030.00, 5500.00),
(default, 'jrivenzon6', 'checking', 'savings', 702.00, 50.00),
(default, 'cmannering7', 'checking', 'savings', 1000.00, null);


alter table banking_accounts 
add constraint fk_username
foreign key (username) references user_account(username);

---create table history (
--"transaction_date" varchar(10),
"
);
--withdrawl1 and deposit1 refers to a withdrawl or deposit from the user's primary account(account_type)
--similar with wd2/dep2
--thinking of making a while/for loop for what kind of withdrwl or deposit user makes



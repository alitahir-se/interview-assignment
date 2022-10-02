create table accounts (
     id bigint not null,
     account_number varchar(255),
     available_balance numeric(19,2),
     current_balance numeric(19,2),
     primary key (id)
);
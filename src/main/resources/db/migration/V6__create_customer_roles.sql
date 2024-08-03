create table customer_roles
(
    customer_id int          not null,
    role        varchar(100) not null,
    foreign key (customer_id) references customer (id)
)
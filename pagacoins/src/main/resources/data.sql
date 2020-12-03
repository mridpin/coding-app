insert into user(id)
values(1);
insert into user(id)
values(2);
insert into wallet(id,balance,user_id)
values(1,100,1);
insert into wallet(id,balance,user_id)
values(2,1000,1);
insert into wallet(id,balance,user_id)
values(3,10,2);
insert into user_wallets(user_id,wallets_id)
values(1,1);
insert into user_wallets(user_id,wallets_id)
values(1,2);
insert into user_wallets(user_id,wallets_id)
values(2,3);
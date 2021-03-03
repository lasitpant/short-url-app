insert into keystore (key_url, active_state) values ('tvhrg1d',false);
insert into keystore (key_url, active_state) values ('pxyazhy',false);
insert into keystore (key_url, active_state) values ('acva12a',true);
insert into keystore (key_url, active_state) values ('dxya9xa',true);
insert into keystore (key_url, active_state) values ('wwa13dw',false);
insert into url (id, created_on, expires_on, long_url ) values (1,'2021-03-02 15:31:56.089000',null,'google.com');
insert into url (id, created_on, expires_on, long_url ) values (2,'2021-03-01 12:31:56.089000',null,'amazon.co.uk');
alter table  url AUTO_INCREMENT=3;
insert into urlmapping (short_url, long_url) values ('acva12a','google.com');
insert into urlmapping (short_url, long_url) values ('dxya9xa','amazon.co.uk');
insert into statistic(id, browser, created_at,device_type,operating_system,short_url) values ( 1, 'Chrome 8', null, 'Computer','Windows','acva12a');
insert into statistic(id, browser, created_at,device_type,operating_system,short_url) values ( 2, 'Internet Explorer', null, 'Computer','MacOs','acva12a');
insert into statistic(id, browser, created_at,device_type,operating_system,short_url) values ( 3, 'Mozilla Firefox', null, 'Computer','Ubuntu','acva12a');
insert into statistic(id, browser, created_at,device_type,operating_system,short_url) values ( 4, 'Chrome 8', null, 'Computer','Windows','acva12a');
alter table  statistic AUTO_INCREMENT=5;
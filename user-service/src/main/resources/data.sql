-- 插入 user_priority 表
INSERT INTO user_priority(priority_id, priority_name) VALUES (0, 'NORMAL_USER');
INSERT INTO user_priority(priority_id, priority_name) VALUES (1, 'VIP_USER');

-- 插入 user_status 表
INSERT INTO user_status(status_id, status_name) VALUES (0, 'OFFLINE');
INSERT INTO user_status(status_id, status_name) VALUES (1, 'ONLINE');

-- 插入 user_base 表
INSERT INTO user_base(uid, phone_number, uname, password)
VALUES ('f3831f8c-c338-4ebe-a82a-e2fc1d1ff78a', '19818965587', 'QIXQI', '1214');
INSERT INTO user_base(uid, phone_number, uname, password)
VALUES ('38777179-7094-4200-9d61-edb101c6ea84', '18742024106', '郑翔', '0422');
INSERT INTO user_base(uid, phone_number, uname, password)
VALUES ('08dbe05-606e-4dad-9d33-90ef10e334f9', '18239461609', '刚有手机的我', '0422');

-- 插入 user_ext 表
INSERT INTO user_ext(uid) VALUES ('f3831f8c-c338-4ebe-a82a-e2fc1d1ff78a');
INSERT INTO user_ext(uid) VALUES ('38777179-7094-4200-9d61-edb101c6ea84');
INSERT INTO user_ext(uid) VALUES ('08dbe05-606e-4dad-9d33-90ef10e334f9');

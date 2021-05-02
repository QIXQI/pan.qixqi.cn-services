DROP TABLE IF EXISTS user_ext;
DROP TABLE IF EXISTS user_base;
DROP TABLE IF EXISTS user_status;
DROP TABLE IF EXISTS user_priority;

-- 用户权限表
CREATE TABLE user_priority(
    priority_id INT(3) PRIMARY KEY NOT NULL,
    priority_name VARCHAR(255) NOT NULL UNIQUE
) ENGINE=InnoDB default charset=utf8;

-- 用户状态表
CREATE TABLE user_status(
    status_id INT(3) PRIMARY KEY NOT NULL,
    status_name VARCHAR(255) NOT NULL UNIQUE
) ENGINE=InnoDB default charset=utf8;

-- 用户表
-- 将访问频繁的定长字段放到基本表
CREATE TABLE user_base(
    uid CHAR(36) PRIMARY KEY NOT NULL,              -- 36 位uuid
    phone_number CHAR(15) NOT NULL UNIQUE,
    uname CHAR(100) NOT NULL UNIQUE,
    password CHAR(72) NOT NULL,
    email CHAR(100) UNIQUE,                      -- mysql 唯一约束允许多个空值
    priority_id INT(3) DEFAULT 0,
    status_id INT(3) DEFAULT 0,
    sex CHAR(1) DEFAULT 'u',                        -- m 男/ f 女/ u 未知
    disk_capacity INT(11) DEFAULT 100,              -- 磁盘容量，默认100G
    free_disk_capacity DECIMAL(13, 2) DEFAULT 100,         -- 剩余磁盘空间
    birthday DATE,
    join_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 注册时间
    FOREIGN KEY(priority_id) REFERENCES user_priority(priority_id)
        ON DELETE SET NULL                          -- 权限外键删除时，设为空
        ON UPDATE CASCADE,                          -- 权限外键更改时，伴随更改
    FOREIGN KEY(status_id) REFERENCES user_status(status_id)
        ON DELETE SET NULL                         -- 状态外键删除时，设为空
        ON UPDATE CASCADE,                          -- 状态外键更改时，伴随更改
    INDEX index_phone_number(phone_number)          -- 为手机号创建索引
) ENGINE=InnoDB default charset=utf8;

-- 垂直分片，将不定长、访问较少的字段放到用户扩展表
CREATE TABLE user_ext(
    uid CHAR(36) PRIMARY KEY NOT NULL,
    avatar VARCHAR(255) DEFAULT 'default.png',      -- 默认头像
    FOREIGN KEY(uid) REFERENCES user_base(uid)
        ON DELETE CASCADE                           -- 外键删除时，伴随删除
        ON UPDATE CASCADE,                          -- 外键更改时，伴随更改
    INDEX index_uid(uid)                            -- 为uid创建索引
) ENGINE=InnoDB default charset=utf8;







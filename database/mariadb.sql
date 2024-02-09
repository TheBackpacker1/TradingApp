CREATE DATABASE TradingDashboard;
USE TradingDashboard;

CREATE TABLE `coin`  (
  `coin_id` int NOT NULL AUTO_INCREMENT,
  `symbol` varchar(10) NULL,
  PRIMARY KEY (`coin_id`)
);

CREATE TABLE `coin_market_data`  (
  `coin_id` int NOT NULL,
  `sync_date` datetime NOT NULL,
  `ath` decimal(33, 33) NULL,
  `ath_mc` decimal(33, 33) NULL,
  `circulating_supply` decimal(33, 33) NULL,
  `total_supply` decimal(33, 33) NULL,
  `max_supply` decimal(33, 33) NULL,
  PRIMARY KEY (`coin_id`, `sync_date`)
);

CREATE TABLE `exchange`  (
  `exchange_id` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `api_base_url` varchar(255) NULL,
  `status` set('Enabled','Disabled') NULL DEFAULT '',
  PRIMARY KEY (`exchange_id`)
);

CREATE TABLE `exchange_account`  (
  `exchange_account_id` int NOT NULL AUTO_INCREMENT,
  `api_key` varchar(255) NOT NULL,
  `api_secret` varchar(255) NOT NULL,
  `exchange_id` int NOT NULL,
  `user_id` int NOT NULL,
  `status` set('Enabled','Disabled') NULL DEFAULT '',
  PRIMARY KEY (`exchange_account_id`)
);

CREATE TABLE `order`  (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `pair_id` int NULL,
  `exchange_account_id` int NULL,
  `side` set('BUY','SELL') NULL DEFAULT '',
  `type` set('LIMIT','MARKET','TAKE_PROFIT') NULL DEFAULT '',
  PRIMARY KEY (`order_id`)
);

CREATE TABLE `order_type`  (
  `order_type_id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(50) NULL,
  PRIMARY KEY (`order_type_id`)
);

CREATE TABLE `pair`  (
  `pair_id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(20) NOT NULL,
  `coin_id` int NULL,
  PRIMARY KEY (`pair_id`)
);

CREATE TABLE `permission`  (
  `permission_id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(100) NULL,
  `http_method` varchar(10) NULL,
  `api_path` varchar(200) NULL,
  `status` set('Enabled','Disabled') NULL DEFAULT '',
  PRIMARY KEY (`permission_id`)
);

CREATE TABLE `role`  (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(100) NULL,
  `status` set('Enabled','Disabled') NULL DEFAULT '',
  PRIMARY KEY (`role_id`)
);

CREATE TABLE `role_permission`  (
  `role_id` int NOT NULL,
  `permission_id` int NOT NULL,
  PRIMARY KEY (`role_id`, `permission_id`)
);

CREATE TABLE `user`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NULL,
  `password` varchar(100) NULL,
  `complete_name` varchar(100) NULL,
  `status` set('Active','Suspended','Verification') NULL DEFAULT '',
  PRIMARY KEY (`user_id`)
);

CREATE TABLE `user_coin`  (
  `user_id` int NOT NULL,
  `coin_id` int NOT NULL,
  `display` tinyint(1) NULL DEFAULT 1,
  PRIMARY KEY (`user_id`, `coin_id`)
);

CREATE TABLE `user_role`  (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`)
);


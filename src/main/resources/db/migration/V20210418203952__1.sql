CREATE TABLE IF NOT EXISTS `user`(
    `id`        INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主鍵',
    `username`  VARCHAR(64) NOT NULL COMMENT '用戶名稱',
    `password`  VARCHAR(128) NOT NULL COMMENT '密碼',
    PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT;
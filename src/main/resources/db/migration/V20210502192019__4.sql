ALTER TABLE user ADD COLUMN email VARCHAR(128) AFTER password;
ALTER TABLE user ADD COLUMN phone VARCHAR(32) AFTER email;
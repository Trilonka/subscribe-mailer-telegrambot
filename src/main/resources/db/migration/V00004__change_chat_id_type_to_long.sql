ALTER TABLE group_x_user DROP FOREIGN KEY group_x_user_ibfk_1;
ALTER TABLE tg_user MODIFY chat_id INT;
ALTER TABLE group_x_user MODIFY user_id INT;
ALTER TABLE group_x_user ADD FOREIGN KEY (user_id) REFERENCES tg_user(chat_id);
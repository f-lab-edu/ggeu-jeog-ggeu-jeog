-- -----------------------------------------------------
-- Data `mydb`.`user`
-- -----------------------------------------------------
INSERT INTO user(id, email, verified, nickname, role) values (1, 'test@test1.com', 1, 'tester1', 'ADMIN');
INSERT INTO user(id, email, verified, nickname, role) values (2, 'test@test2.com', 0, 'tester2', 'DEFAULT');
INSERT INTO user(id, email, verified, nickname, role) values (3, 'test@test3.com', 0, 'tester3', 'DEFAULT');
INSERT INTO user(id, email, verified, nickname, role) values (4, 'test@test4.com', 1, 'tester4', 'ADMIN');
INSERT INTO user(id, email, verified, nickname, role) values (5, 'test@test5.com', 1, 'tester5', 'DEFAULT');

-- -----------------------------------------------------
-- Data `mydb`.`board`
-- -----------------------------------------------------
INSERT INTO board(id, board_title, user_id) values (1, 'test-title-1', 1);
INSERT INTO board(id, board_title, user_id) values (2, 'test-title-2', 1);
INSERT INTO board(id, board_title, user_id) values (3, 'test-title-3', 5);
-- -----------------------------------------------------
-- Data `mydb`.`paper`
-- -----------------------------------------------------
INSERT INTO paper(id, owner_name, user_id, board_id) values (1, 'test-owner-1', 1, 1);
INSERT INTO paper(id, owner_name, user_id, board_id) values (2, 'test-owner-1', 1, 1);
INSERT INTO paper(id, owner_name, user_id, board_id) values (3, 'test-owner-5', 5, 3);

-- -----------------------------------------------------
-- Data `mydb`.`comment`
-- -----------------------------------------------------
INSERT INTO comment(id, user_id, paper_id) values (1, 1, 1);
INSERT INTO comment(id, user_id, paper_id) values (2, 5, 3);
-- -----------------------------------------------------
-- Data `mydb`.`notification`
-- -----------------------------------------------------
INSERT INTO notification(id, title, content, type, type_id, status, user_id) values (1, 'test-noti-1', 'test-noti-content-1', 'PAPER', '1','ALIVE', 1);
INSERT INTO notification(id, title, content, type, type_id, status, user_id) values (3, 'test-noti-3', 'test-noti-content-3', 'COMMENT', '1', 'DELETED', 1);
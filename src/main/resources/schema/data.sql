-- -----------------------------------------------------
-- Data `mydb`.`user`
-- -----------------------------------------------------
INSERT INTO user(id, email, password, verified, nickname, role, access_token, refresh_token) values (1, 'tester1@tester.com', '1Q2w3e4r!@', 1, 'tester1', 'DEFAULT', 'test', 'test');
INSERT INTO user(id, email, password, verified, nickname, role) values (2, 'tester2@tester.org', '1Q2w3e4r!@#$', 1, 'tester2', 'DEFAULT');
INSERT INTO user(id, email, verified, nickname, role) values (3, 'test@test3.com', 0, 'tester3', 'DEFAULT');
INSERT INTO user(id, email, verified, nickname, role) values (4, 'test@test4.com', 1, 'tester4', 'ADMIN');
INSERT INTO user(id, email, verified, nickname, role) values (5, 'test@test5.com', 1, 'tester5', 'DEFAULT');

-- -----------------------------------------------------
-- Data `mydb`.`board`
-- -----------------------------------------------------
INSERT INTO board(id, board_title, theme, is_opened, user_id) values (1, 'Title1', 'THEME1', 1, 1);
INSERT INTO board(id, board_title, theme, is_opened, user_id) values (2, 'Title2', 'THEME2', 0, 1);
INSERT INTO board(id, board_title, theme, is_opened, user_id) values (3, 'Title3', 'THEME1', 0, 5);
INSERT INTO board(id, board_title, theme, is_opened, user_id) values (4, 'Title4', 'THEME2', 1, 1);
-- -----------------------------------------------------
-- Data `mydb`.`paper`
-- -----------------------------------------------------
INSERT INTO paper(id, owner_name, user_id, board_id) values (1, 'test-owner-1', 1, 1);
INSERT INTO paper(id, owner_name, user_id, board_id) values (2, 'test-owner-1', 1, 1);
INSERT INTO paper(id, owner_name, user_id, board_id) values (3, 'test-owner-5', 5, 3);
INSERT INTO paper(id, owner_name, user_id, board_id) values (4, 'test-owner-1', 1, 4);
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
-- -----------------------------------------------------
-- Data `mydb`.`outbox`
-- -----------------------------------------------------
INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');
INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');
INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');
INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');
INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');
INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');
INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');
INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');
INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');
INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');
INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');
INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');
INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');
INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');
INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');
INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');
INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');
INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');
INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');
INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO outbox(aggregate_type, aggregate_id, type, payload)
VALUES ('notification', 1, 'INSERT','{"message":{"title":"tester2 님, tester1께서 댓글을 남겼습니다.","content":"Test-title-1","type":"COMMENT","typeId":1},"user":{"id":2,"email":"helloworld@world.com","password":"Tester1234!","verified":true,"role":"DEFAULT","nickname":"tester2","accessToken":null,"refreshToken":null}}');

INSERT INTO users (login, password, rooms, chats)
VALUES ('Ayfon', 'cornhub11', ARRAY[1, 2, 4], ARRAY[1, 2, 3, 4]);
INSERT INTO users (login, password, rooms, chats)
VALUES ('Aypod', 'cornhub12', ARRAY[5], ARRAY[5]);
INSERT INTO users (login, password, rooms, chats)
VALUES ('Aypad', 'cornhub13', ARRAY[3], ARRAY[3, 2]);
INSERT INTO users (login, password, chats)
VALUES ('Ayrat', 'cornhub14', ARRAY[2]);
INSERT INTO users (login, password, chats)
VALUES ('Aygul', 'cornhub15', ARRAY[3]);
INSERT INTO users (login, password, chats)
VALUES ('Aylin', 'cornhub16', ARRAY[4]);
-- INSERT INTO USERS VALUES(1, 'Ayfon', 'cornhub11');
-- INSERT INTO USERS VALUES(2, 'Aypod', 'cornhub12');
-- INSERT INTO USERS VALUES(3, 'Aypad', 'cornhub13');
-- INSERT INTO USERS VALUES(4, 'Ayrat', 'cornhub14');
-- INSERT INTO USERS VALUES(5, 'Aygul', 'cornhub15');
-- INSERT INTO USERS VALUES(6, 'Aylin', 'cornhub16');




INSERT INTO chatroom VALUES(1, 'school11', 1);
INSERT INTO chatroom VALUES(2, 'school21', 1);
INSERT INTO chatroom VALUES(3, 'school31', 3);
INSERT INTO chatroom VALUES(4, 'school41', 1);
INSERT INTO chatroom VALUES(5, 'school51', 2);

INSERT INTO messages VALUES(1, 1, 3, 'Hy my name is Ayfon', CURRENT_TIMESTAMP);
INSERT INTO messages VALUES(2, 5, 3, 'OH my name is Aygul', CURRENT_TIMESTAMP);
INSERT INTO messages VALUES(3, 3, 2, 'Hy my name is Aypad', CURRENT_TIMESTAMP);
INSERT INTO messages VALUES(4, 4, 2, 'OH my name is Ayrat', CURRENT_TIMESTAMP);
INSERT INTO messages VALUES(5, 2, 4, 'Hy my name is Aypod', CURRENT_TIMESTAMP);
INSERT INTO messages VALUES(6, 6, 4, 'OH my name is Aylin', CURRENT_TIMESTAMP);
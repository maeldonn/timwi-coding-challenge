INSERT INTO ALBUM (ID, ARTISTS, DURATION, FAVORITE, IMAGE, RELEASE_DATE, TITLE) VALUES ('1To7kv722A8SpZF789MZy7', 'Nirvana', 14, 1, 'https://i.scdn.co/image/ab67616d0000b27336c5417732e53e23cb219246', '1994-11-1', 'MTV Unplugged In New York');
INSERT INTO ALBUM (ID, ARTISTS, DURATION, FAVORITE, IMAGE, RELEASE_DATE, TITLE) VALUES ('6olE6TJLqED3rqDCT0FyPh', 'Nirvana', 13, 0, 'https://i.scdn.co/image/ab67616d0000b273fbc71c99f9c1296c56dd51b6', '1991-09-26', 'Nevermind (Remastered)');
INSERT INTO ALBUM (ID, ARTISTS, DURATION, FAVORITE, IMAGE, RELEASE_DATE, TITLE) VALUES ('68YP0pEgwhnfRqQAzu71gP', 'Orelsan', 25, 0, 'https://i.scdn.co/image/ab67616d0000b2732724364cd86bb791926b6cc8', '2022-10-28', 'Civilisation Edition Ultime');
INSERT INTO ALBUM (ID, ARTISTS, DURATION, FAVORITE, IMAGE, RELEASE_DATE, TITLE) VALUES ('5l8WyvJ50fdSghK2ySNq1y', 'Luv Resval', 19, 1, 'https://i.scdn.co/image/ab67616d0000b273338f06061282157400b1f76e', '2021-06-04', 'Etoile Noire');
INSERT INTO ALBUM (ID, ARTISTS, DURATION, FAVORITE, IMAGE, RELEASE_DATE, TITLE) VALUES ('2GMizsQeKeilPBjUjrfqhI', 'Lofi Fruits Music, Chill Fruits Music', 20, 0, 'https://i.scdn.co/image/ab67616d0000b273312865962c376e4fc3d8e1ac', '2022-06-03', 'Midnight Rain');

INSERT INTO TAG (ID, NAME) VALUES (1000, 'Rock');
INSERT INTO TAG (ID, NAME) VALUES (1001, 'Chill');

INSERT INTO ALBUM_TAGS (ALBUM_ID, TAG_ID) VALUES ('1To7kv722A8SpZF789MZy7', 1000);
INSERT INTO ALBUM_TAGS (ALBUM_ID, TAG_ID) VALUES ('6olE6TJLqED3rqDCT0FyPh', 1000);
INSERT INTO ALBUM_TAGS (ALBUM_ID, TAG_ID) VALUES ('2GMizsQeKeilPBjUjrfqhI', 1001);
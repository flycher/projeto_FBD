CREATE SEQUENCE userid;

CREATE TABLE usuario (
	userID int default nextval('userid'),
	username varchar(70) unique,
	email varchar(70) unique,
	passwrd varchar(60),
	CONSTRAINT usuario_pk PRYMARY KEY (userID)
);

CREATE SEQUENCE postid;

CREATE TABLE post (
	postID int DEFAULT nextval('post'),
	userID int,
	message varchar(280),
	horario timestamp,
	CONSTRAINT post_pk PRIMARY KEY (postID),
	CONSTRAINT post_fk FOREIGN KEY (userID) REFERENCES usuario (userID)
);

CREATE TABLE following (
	follower_UID int,
	following_UID int,
	CONSTRAINT following_pk PRIMARY KEY (follower_UID, following_UID),
	CONSTRAINT following_fk1 FOREIGN KEY (follower_UID) REFERENCES usuario (userID),
	CONSTRAINT following_fk2 FOREIGN KEY (following_UID) REFERENCES usuario (userID)
);

CREATE TABLE sharing (
	postID int,
	userID int,
	CONSTRAINT sharing_pk PRIMARY KEY (postID, userID),
	CONSTRAINT sharing_fk1 FOREIGN KEY (userID) REFERENCES usuario (userID),
	CONSTRAINT sharing_fk2 FOREIGN KEY (postID) REFERENCES post (postID)
);

CREATE TABLE likes (
	postID int,
	userID int,
	CONSTRAINT likes_pk PRIMARY KEY (postID, userID),
	CONSTRAINT likes_fk1 FOREIGN KEY (userID) REFERENCES usuario (userID),
	CONSTRAINT likes_fk2 FOREIGN KEY (postID) REFERENCES post (postID)
);

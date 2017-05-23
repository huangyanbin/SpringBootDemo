
#用户
CREATE  TABLE User(
  id INT PRIMARY KEY AUTO_INCREMENT,
  userName VARCHAR(12) NOT NULL,
  nickName VARCHAR(50),
  password VARCHAR(15),
  icon VARCHAR(200),
  position VARCHAR(100),
  company  VARCHAR(100),
  intro VARCHAR(200),
  address VARCHAR(100),
  createTime DATE,
  secretKey VARCHAR(100)
);

#关注表
CREATE TABLE FOLLOW(
  id INT PRIMARY KEY AUTO_INCREMENT,
  followingUid INT, ##关注者
  followedUid INT, ##被关注者
  followTime DATE,
  FOREIGN KEY (followingUid) REFERENCES User(id),
  FOREIGN KEY (followedUid) REFERENCES User(id)
);

#文章分类
CREATE TABLE ArticleType(
  id INT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(100),
  createTime DATE
);

#文章
create TABLE ARTICLE(

  id INT PRIMARY KEY AUTO_INCREMENT,
  uid INT,
  title VARCHAR(200),
  url VARCHAR(200),
  icon VARCHAR(200),
  content VARCHAR(200),
  createTime Date,
  typeID INT,
  FOREIGN KEY (uid) REFERENCES User(id),
  FOREIGN KEY (typeID) REFERENCES ArticleType(id)
);
#收藏集
CREATE TABLE FavType(
  id INT PRIMARY KEY AUTO_INCREMENT,
  uid INT,
  type VARCHAR(100),
  createTime DATE,
  FOREIGN KEY (uid) REFERENCES User(id)
);

#喜欢
CREATE TABLE FAV(
  id INT PRIMARY KEY AUTO_INCREMENT,
  uid INT,
  articleID INT,
  typeID INT,
  favTime DATE,
  FOREIGN KEY (uid) REFERENCES User(id),
  FOREIGN KEY (articleID) REFERENCES ARTICLE(id),
  FOREIGN KEY (typeID) REFERENCES FavType(id)
);

#分享
CREATE TABLE SHARE(
  id INT PRIMARY KEY AUTO_INCREMENT,
  uid INT,
  articleID INT,
  shareTime DATE,
  FOREIGN KEY (uid) REFERENCES User(id),
  FOREIGN KEY (articleID) REFERENCES ARTICLE(id)
);


#评论
CREATE TABLE  COMMENT(

  id INT PRIMARY KEY AUTO_INCREMENT,
  uid INT,
  articleID int,
  content TEXT,
  commitTime DATE,
  FOREIGN KEY (uid) REFERENCES User(id),
  FOREIGN KEY (articleID) REFERENCES ARTICLE(id)
);


DROP TABLE IF EXISTS COMMENT;
DROP TABLE IF EXISTS SHARE;
DROP TABLE IF EXISTS FAV;
DROP TABLE IF EXISTS FavType;
DROP TABLE IF EXISTS ARTICLE;
DROP TABLE IF EXISTS ArticleType;
DROP TABLE IF EXISTS  FOLLOW;
DROP TABLE IF EXISTS User;
alter table user add COLUMN secretKey VARCHAR(100);
select count(*) from tab_follow where followingUid=1 and followedUid=2;
alter table tab_follow rename FOLLOW;

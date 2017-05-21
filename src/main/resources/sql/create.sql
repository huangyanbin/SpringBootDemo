Create TABLE  IF NOT EXISTS Article (
  id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  author VARCHAR(12),
  title VARCHAR(100),
  url  VARCHAR(100),
  content TEXT,
   favCount INT
);
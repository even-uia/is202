/**
 * Author:  evenal
 * Created: Nov 12, 2018
 *
 * Note: A mysql blob has a size limit of 64kb, if this is
 * a problem use mediumblob(16Mb) or longblob(4Gb) instead
 */
CREATE TABLE file (
  fileid int(11) NOT NULL AUTO_INCREMENT ,
  filename varchar(512) DEFAULT NULL,
  filesize int(11) DEFAULT NULL,
  filetype varchar(45) DEFAULT NULL,
  filecontent blob,
  PRIMARY KEY (fileid)
)
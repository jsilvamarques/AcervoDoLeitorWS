CREATE TABLE resource_like(
	uid_resource VARCHAR(50) NOT NULL,
  uid_profile VARCHAR(50) NOT NULL,
  resource_type VARCHAR(50) NOT NULL,
  is_like BIGINT(20) DEFAULT 0,
  PRIMARY KEY (uid_resource, uid_profile),
  FOREIGN KEY (uid_profile) REFERENCES profile(uid)  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO resource_like (uid_resource, uid_profile, resource_type, is_like) VALUES (2,'bgzh0OS5IegSvK3wmoUASk5xhL92', 'REVIEW', 1);
INSERT INTO resource_like (uid_resource, uid_profile, resource_type, is_like) VALUES (5,'bgzh0OS5IegSvK3wmoUASk5xhL92', 'REVIEW', 0);

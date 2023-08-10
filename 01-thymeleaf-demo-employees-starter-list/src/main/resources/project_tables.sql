USE `employee_directory`;
DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1

CREATE TABLE `users` (
`username` varchar(50) NOT NULL,
`password` varchar(50) NOT NULL,
`enabled` tinyint NOT NULL,
PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `users`
VALUES('john','{noop}123',1),
('mary','{noop}123',1),
('susan','{noop}123',1);

CREATE TABLE `authorities` (
`username` varchar(50) NOT NULL,
`authority` varchar(50) NOT NULL,
UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
CONSTRAINT `authorities_ibfk_` FOREIGN KEY (`username`)
REFERENCES `users`(`username`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `authorities`
VALUES ('john','ROLE_EMPLOYEE'),
('mary','ROLE_EMPLOYEE'),
('susan','ROLE_EMPLOYEE'),
('mary','ROLE_MANAGER'),
('susan','ROLE_MANAGER'),
('susan','ROLE_ADMIN');
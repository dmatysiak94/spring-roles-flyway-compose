CREATE TABLE `users` (
`user_id` INT NOT NULL AUTO_INCREMENT,
`username` VARCHAR(45) NOT NULL,
`password` VARCHAR(64) NOT NULL,
PRIMARY KEY (`user_id`));

CREATE TABLE `roles` (
`role_id` INT NOT NULL AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL,
PRIMARY KEY (`role_id`));

CREATE TABLE `users_role` (
`user_id` INT NULL,
`role_id` INT NULL,
INDEX `user_fk_idx` (`user_id` ASC) VISIBLE,
CONSTRAINT `user_fk`
FOREIGN KEY (`user_id`)
REFERENCES users (`user_id`)
ON DELETE NO ACTION
ON UPDATE NO ACTION,
CONSTRAINT `role_fk`
FOREIGN KEY (`role_id`)
REFERENCES roles (`role_id`)
ON DELETE NO ACTION
ON UPDATE NO ACTION);

CREATE TABLE `available_roles` (
`role_id` int NOT NULL AUTO_INCREMENT,
`role` varchar(100) NOT NULL UNIQUE,
PRIMARY KEY (`role_id`)
);

INSERT INTO `available_roles`
(`role_id`,`role`)
VALUES
(1,'ROLE_ADMIN');

INSERT INTO `roles`
(`role_id`,`name`)
VALUES
(1, 'ROLE_ADMIN');

INSERT INTO `users`
(`user_id`, `username`, `password`)
VALUES
(1, 'admin', '$2a$10$65rrypG69AMyg90uXvNpEuk6jRXY8qDZMbGpNKJ0TS2JdwqufEZuu');

INSERT INTO `users_role` (`user_id`, `role_id`) VALUES (1, 1);

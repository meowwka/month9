use `handmade`;

CREATE TABLE `users`
(
    `id`   INT auto_increment NOT NULL,
    `email` varchar(128)       NOT NULL,
    `name` varchar (50) NOT NULL ,
     `login` varchar(15)       NOT NULL,
    `password` varchar(15)       NOT NULL,
    PRIMARY KEY (`id`)
);

use `handmade`;

CREATE TABLE `places` (
    `id` INT auto_increment NOT NULL,
    `name` varchar(128) NOT NULL,
    `address` varchar(128) NOT NULL,
    `image` varchar(128) NOT NULL, PRIMARY KEY (`id`)
);

create table `products` (
    `id` INT auto_increment NOT NULL,
    `name` varchar(128) NOT NULL,
    `image` varchar(128) NOT NULL,
    `price` float not null,
    `place_id` int not null, PRIMARY KEY (`id`),
    CONSTRAINT `fk_food_place` FOREIGN KEY (`place_id`) REFERENCES `places` (`id`)
);

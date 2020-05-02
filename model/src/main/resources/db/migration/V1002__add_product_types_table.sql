use `handmade`;

CREATE TABLE `product_types` (
    `id` INT auto_increment NOT NULL,
    `name` varchar(128) NOT NULL,
    `icon` varchar(128) NOT NULL,
    PRIMARY KEY (`id`)
);

ALTER TABLE `products`
ADD COLUMN `product_type_id` INT NOT NULL after `place_id`, add CONSTRAINT `fk_product 	product_types`
FOREIGN KEY (`product_type_id`) REFERENCES `product_types` (`id`);

INSERT INTO`product_types` (`name`, `icon`) VALUES ('Bangle', 'bangle.png'), ('Brooch', 'brooch.png'),
                                                 ('Chain', 'chain.png'), ('Earring', 'earring.png');

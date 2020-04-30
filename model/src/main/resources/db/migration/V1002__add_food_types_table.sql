use `edufood`;

CREATE TABLE `food_types` (
    `id` INT auto_increment NOT NULL,
    `name` varchar(128) NOT NULL,
    `icon` varchar(128) NOT NULL,
    PRIMARY KEY (`id`)
);

ALTER TABLE `foods`
ADD COLUMN `food_type_id` INT NOT NULL after `place_id`, add CONSTRAINT `fk_food 	food_types`
FOREIGN KEY (`food_type_id`) REFERENCES `food_types` (`id`);

INSERT INTO`food_types` (`name`, `icon`) VALUES ('Coffee', 'hot_beverage.png'), ('Steak', 'steaks.png'),
                                                 ('Burger', 'burgers.png'), ('Bakery', 'bakery.png'),
                                                 ('Dessert', 'dessert.png'), ('Sushi', 'sushi.png'),
                                                 ('Sea food', 'sea_food.png'), ('Pasta', 'pasta.png'),
                                                 ('Cocktail', 'cocktail.png'), ('Pizza', 'pizza.png'),
                                                 ('Fast food', 'fast_food.png'), ('Salad', 'salad.png');

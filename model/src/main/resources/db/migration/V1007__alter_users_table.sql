use `handmade`;

ALTER TABLE `users`
ADD COLUMN `cart_id` INT default NULL after `role`;
alter table `users` add CONSTRAINT `fk_cart`
FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`);

alter table `products`
add column `cart_id` int default  null after `product_type_id`;
alter table `products` add constraint `fk_products`
foreign key (`cart_id`) references  `cart` (`id`);

create table cart_products
(
    `id`        int auto_increment not null,
    carts_id    int not null,
    products_id int not null,
        primary key (`id`),
    constraint carts_fk
        foreign key (carts_id) references cart (id),
    constraint Products_fk
        foreign key (products_id) references products (id)
);


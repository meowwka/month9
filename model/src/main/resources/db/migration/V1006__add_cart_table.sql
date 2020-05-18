use `handmade`;
create table `cart`(
    `id`            int auto_increment not null,
    `session`       varchar(128)       not null,
    `user_id`       int default null,
    `product_id` int                default null
,
    primary key (`id`),
    constraint `fk_user` foreign key (`user_id`) references `users` (`id`),
    constraint `products` foreign key (`product_id`) references `products` (`id`)
)


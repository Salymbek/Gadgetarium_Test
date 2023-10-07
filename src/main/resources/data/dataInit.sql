-- INSERT INTO users(is_subscription, role, id, address, email, first_name, image, last_name, password, phone_number)
-- VALUES (false, 'USER', 1, 'Asia 7', 'davran@gmail.com', 'Davran', 'img', 'Joldoshbaev',
--         '$2a$12$PAAkQMYdB59.GYcCbBsQJOcF3RH9sof0CaHXIWkSp9wso8KOkF9ge', '0995665528'), --davran2005
--        (true, 'USER', 2, 'Красный речка', 'salymbek@gmail.com', 'Salymbek', 'img', ' Khadzhakeldyev',
--         '$2a$12$obECYf/JcKcxdFVc4w57we64ZSiU9.RfZ/uV/l3D/.oa72NerEj7y',
--         '0700020206'),                                                                 --salymbek2006
--        (false, 'USER', 3, 'Ибраимова 3', 'gulira@gmail.com', 'Gulira', 'img', ' Abdukerim kyzy',
--         '$2a$12$KMfRpt80J70ZllO4C4thouR/NV.1QIVrEvY74VqMCS/yTZeMQgQJW',
--         '0700020206'),                                                                 --gulira2002
--        (true, 'USER', 4, 'Арча бешик ', 'sanjarabdymomunov@gmail.com', 'Sanzhar', 'img',
--         '  Abdymomunov',
--         '$2a$12$pBVBShiP3FroQBWzcZxedun38JGBzpStTDLaenL1BnGhWYANXzH.G',
--         '0700020206'),                                                                 --sanzhar2003
--        (false, 'USER', 5, 'Аламидин-1', 'erjan@gmail.com', 'Erjan', 'img', '  Taalaibekov',
--         '$2a$12$XhWrZB5uMZcfsw8n5kZ58emB5Z6nkF3Ix94D5QaYB4ikkfyimUO66', '0700020206'); --erjan2004


INSERT INTO categories (id, title)
VALUES (1, 'Phone'),
       (2, 'Laptop'),
       (3, 'Smart Watch'),
       (4, 'Tablet');

INSERT INTO sub_categories (category_id, id, title)
VALUES (1, 1, 'Android'),
       (1, 2, 'iOS'),
       (1, 3, 'Camera Phones'),
       (1, 4, 'Gaming Phones'),
       (2, 5, 'Ultrabooks'),
       (2, 6, 'Business Laptops'),
       (2, 7, 'Touchscreen Laptops'),
       (2, 8, 'Chromebooks'),
       (2, 9, 'Gaming Laptops'),
       (3, 10, 'Fitness Trackers'),
       (3, 11, 'Sports Watches'),
       (3, 12, 'Children Smart Watches'),
       (4, 13, 'Entertainment Tablets'),
       (4, 14, 'Professional Tablets'),
       (4, 15, 'Educational Tablets');



INSERT INTO brands (id, image, name)
VALUES (1, 'https://gadgetariumb9.s3.eu-central-1.amazonaws.com/1692870335571_APPLE_Image.png', 'Apple'),
       (2, 'https://gadgetariumb9.s3.eu-central-1.amazonaws.com/1692870356819_SAMSUNG_Image.png', 'Samsung'),
       (3, 'https://gadgetariumb9.s3.eu-central-1.amazonaws.com/1692870114335_HUAWEI_Image.png', 'Huawei'),
       (4, 'https://gadgetariumb9.s3.eu-central-1.amazonaws.com/1692870166420_XIAOMI_Image.png', 'Xiaomi'),
       (5, 'https://gadgetariumb9.s3.eu-central-1.amazonaws.com/1692870073712_HONOR_Image.png', 'Honor');

-- INSERT INTO products(guarantee, brand_id, created_at, data_of_issue, id, sub_category_id, description, name, pdf,
--                      video_link, category_id)
-- VALUES (5, 1, '2023-07-14T12:59:00+00:00', '2023-08-14T00:00:00+00:00', 1, 2, 'Поддежка быстрой зарядки Беспроводная зарядка (поддерживаются зарядные устройства стандарта Qi) Apple Pay U1 чип для пространственной осведомленности Экспресс-карты с резервным питанием Видеозвонки FaceTime Аудиозвонки FaceTime Совместимость со слуховыми аппаратами M3, T4 Apple Card Поддержка аксессуаров MagSafe 16‑ядерная система Neural Engine 2 производительных ядра и 4 ядра эффективности Поддержка HDR в стандартах Dolby Vision, HDR10 и HLG Пространственное аудио Режимы «Изоляция голоса» и «Широкий спектр» для микрофона Общий экран Режим «Портрет» в видеовызовах FaceTime 5‑ядерный графический процессор Share experiences like movies',
--         'Iphone 14', 'img', 'https://www.youtube.com/watch?v=v94jRN2FhGo&pp=ygUQaXBob25lIDE0IHJldmlldw%3D%3D', 1),
--        (5, 2, '2023-07-29T12:59:00+00:00', '2023-08-24T00:00:00+00:00', 2, 1, 'Встречайте Galaxy S22 Ultra с мощью Note. Его тонкий алюминиевый корпус впечатляет своей совершенной формой. А изысканные очертания основных камер как будто утопают в его гладкой поверхности.', 'Samsung Galaxy S22 Ultra',
--         'img', 'https://www.youtube.com/watch?v=CuRte1HnGcM&pp=ygUhc2Ftc3VuZyBnYWxheHkgdWx0cmEgczIyIHVuYm94aW5n', 1),
--        (5, 1, '2023-07-27T12:59:00+00:00', '2023-08-30T00:00:00+00:00', 3, 2, 'Примечания: 1,07 миллиарда цветов относятся к 10-битной глубине цвета (8-битная аппаратная спецификация, 2-битная-расширение программного алгоритма), количество цветов, которые могут отображаться, составляет 1024 × 1024 × 1024, около 1,07 миллиарда', 'Apple iPad 10', 'img',
--         'https://www.youtube.com/watch?v=ZiYwWhdiBpc&pp=ygUVSFVBV0VJIE1hdGVCb29rIFggUHJv', 4),
--        (5, 1, '2023-07-26T12:59:00+00:00', '2023-09-30T00:00:00+00:00', 4, 11, 'The beast', 'Apple Watch Ultra',
--         'img', 'https://www.youtube.com/watch?v=bo2A3GOte9o&pp=ygURQXBwbGUgV2F0Y2ggVWx0cmE%3D', 3),
--        (12, 1, '2023-07-27T12:59:00+00:00', '2023-09-30T00:00:00+00:00', 5, 6, 'The beast', 'MacBook Air M2', 'img',
--         'https://www.youtube.com/watch?v=feh4RLTRk2Q&pp=ygUUTWFjQm9vayBBaXIgTTIgMjU2R0I%3D', 2);
--
--
--
-- INSERT INTO banners(id)
-- VALUES (1),
--        (2),
--        (3),
--        (4),
--        (5);
--
-- INSERT INTO banner_images (banner_id, images)
-- VALUES (1,'https://gadgetariumb9.s3.eu-central-1.amazonaws.com/1692870908817_wallpaperflare.com_wallpaper.jpg'),
--        (2,'https://gadgetariumb9.s3.eu-central-1.amazonaws.com/1692871052182_wallpaperflare.com_wallpaper%20%281%29.jpg'),
--        (3,'https://gadgetariumb9.s3.eu-central-1.amazonaws.com/1692871204414_wallpaperflare.com_wallpaper%20%282%29.jpg'),
--        (4,'https://gadgetariumb9.s3.eu-central-1.amazonaws.com/1692871301118_wallpaperflare.com_wallpaper%20%283%29.jpg'),
--        (5,'https://gadgetariumb9.s3.eu-central-1.amazonaws.com/1692871435371_wallpaperflare.com_wallpaper%20%284%29.jpg');
--
-- INSERT INTO baskets (id, user_id)
-- VALUES (1, 1),
--        (2, 2),
--        (3, 3);
--
-- INSERT INTO sub_products (article_number, price, quantity, ram, rom, id, product_id, additional_features, code_color,
--                           screen_resolution, rating)
--                 --Iphone
-- VALUES (322323, 78000, 10, 8, 256, 1, 1, 'asc', '#000000', '1170x2532', 4),
--        (123454, 74000, 15, 16, 512, 2, 1, 'rte', '#002673', '1170x2532', 0),
--        (543210, 85000, 16, 12, 256, 3, 1, 'mnbv', '#A5F57A', '1170x2532', 0),
--                 --Samsung
--        (987654, 92000, 21, 8, 512, 4, 2, 'ytrew', '#000000', '1440 x 3088', 2),
--        (678901, 67000, 31, 16, 512, 5, 2, 'lkjh', '#FFFFFF', '1440 x 3088', 0),
--        (987665, 79000, 17, 0, 512, 6, 2, 'bvcb', '#5C8944', '1440 x 3088', 0),
--                 --Tablet
--        (654321, 120000, 35, 8, 128, 7, 3, 'vcvb', '#0071FF', '2360x1640', 3),
--        (123987, 95000, 24, 8, 128, 8, 3, 'qazx', '#FFFFFF', '2360x1640', 0),
--        (789456, 110000, 29, 8, 128, 9, 3, 'wsxc', '#FF00C5', '2360x1640', 0),
--                 --Smart watch
--        (456123, 83000, 19, 0, 32, 10, 4, 'edcv', '#CDCD66', '502x410', 0),
--        (234567, 60000, 34, 0, 32, 11, 4, 'bcvn', '#000000', '502x410', 0),
--        (789012, 80000, 14, 0, 32, 12, 4, 'tyio', '#FFFFFF', '502x410', 0),
--                 --Laptop
--        (345678, 116900, 32, 8, 256, 13, 5, 'qwerty', '#686868', '2560x1664', 0),
--        (901234, 116900, 28, 8, 256, 14, 5, 'zxcvb', '#002673','2560x1664', 0),
--        (567890, 116900, 29, 8, 256, 15, 5, 'asdfg', '#A5F57A', '2560x1664', 0);
--
-- INSERT INTO baskets_sub_products (baskets_id, sub_products_id)
-- VALUES
--     (1, 1),
--     (1, 2),
--     (1, 3),
--     (2, 4),
--     (2, 5),
--     (2, 6),
--     (3, 7),
--     (3, 8),
--     (3, 9);
--
--
-- INSERT INTO discounts (id, sale, start_date, finish_date, sub_product_id)
-- VALUES (1, 10, '2023-07-14T12:59:00+00:00', '2023-11-14T00:00:00+00:00', 1),
--        (2, 10, '2023-07-14T12:59:00+00:00', '2023-11-24T00:00:00+00:00', 4),
--        (3, 10, '2023-07-14T12:59:00+00:00', '2023-11-30T00:00:00+00:00', 7);
--
--
--
-- -- INSERT INTO mailings(finish_date, id, start_date, description, image, title)
-- -- VALUES ('2023-10-14T00:00:00+00:00', 1, '2023-07-14T12:59:00+00:00', 'The beast', 'img', 'mailings'),
-- --        ('2023-10-24T00:00:00+00:00', 2, '2023-07-14T12:59:00+00:00', 'The Beast', 'img', 'mel'),
-- --        ('2023-10-30T00:00:00+00:00', 3, '2023-07-14T12:59:00+00:00', 'The Beast', 'img', 'asd'),
-- --        ('2023-10-30T00:00:00+00:00', 4, '2023-07-14T12:59:00+00:00', 'The Beast', 'img', 'lkj'),
-- --        ('2023-10-30T00:00:00+00:00', 5, '2023-07-14T12:59:00+00:00', 'The Beast', 'img', 'wer');
--
--
-- INSERT INTO orders(order_number, quantity, total_discount, total_price, date_of_order, id, user_id,
--                    status,
--                    type_delivery, type_payment)
-- VALUES (1, 3, 10, 237000, '2023-10-01T12:59:00+00:00', 1, 1, 'PENDING', 'PICKUP', 'CASH'),
--        (2, 3, 10, 233000, '2023-10-01T12:59:00+00:00', 2, 2,    'DELIVERED', 'DELIVERY', 'CASH'),
--        (3, 3, 10, 325000, '2023-10-01T12:59:00+00:00', 3, 3, 'IN_PROCESSING', 'PICKUP', 'CASH');
--
-- INSERT INTO orders_sub_products (orders_id, sub_products_id)
-- VALUES (1, 1),
--        (1, 2),
--        (1, 3),
--        (2, 4),
--        (2, 5),
--        (2, 6),
--        (3, 7),
--        (3, 8),
--        (3, 9);
--
--
--
-- INSERT INTO laptops (screen_size, video_memory, id, processor, purpose, sub_product_id)
-- VALUES (13.6, 8, 1, 'INTEL_CORE_I3', 'FOR_WORK', 13),
--        (13.6, 8, 2, 'INTEL_CORE_I7', 'GAMING', 14),
--        (13.6, 8, 3, 'INTEL_PENTIUM', 'OFFICE', 15);
--
-- INSERT INTO phones(screen_size, sim, id, sub_product_id, battery_capacity, diagonal_screen)
-- VALUES (6.06 , 1, 1, 1, '3,279 mAh', '6.06'),
--        (6.06, 1, 2, 2, '3,279 mAh', '6.06'),
--        (6.06, 1, 3, 3, '3,279 mAh', '6.06'),
--
--
--        (6.8, 2, 4, 4, '4855mAh', '6.1'),
--        (6.8, 2, 5, 5, '4855mAh', '6.1'),
--        (6.8, 2, 6, 6, '4855mAh', '6.1'),
--
--         (10.9, 1, 7, 7, '7606 mAh', '10.9'),
--         (10.9, 2, 8, 8, '7606 mAh', '10.9'),
--         (10.9, 1, 9, 9, '7606 mAh', '10.9');
--
--
--
-- INSERT INTO smart_watches (display_discount, waterproof, id, sub_product_id, an_interface, gender, hull_shape,
--                            housing_material, material_bracelet)
-- VALUES (2.0, FALSE, 1, 10, 'BLUETOOTH', 'FEMALE', 'SQUARE', 'ACRYLIC', 'RUBBER'),
--        (1.22, TRUE, 2, 11, 'WIFI', 'MALE', 'ROUND', 'ALUMINIUM', 'RUBBER'),
--        (1.4, TRUE, 3, 12, 'WIFI', 'MALE', 'OVAL', 'CERAMIC', 'NYLON');
--
--
-- INSERT INTO reviews (grade, date_creat_ad, id, sub_product_id, user_id, comment, reply_to_comment, is_viewed)
-- VALUES (4, '2023-07-14T12:59:00+00:00', 1, 1, 1, 'comm', NULL, false),
--        (2, '2023-07-14T12:59:00+00:00', 2, 4, 2, 'comm', 'ok', true),
--        (3, '2023-07-14T12:59:00+00:00', 3, 7, 3, 'comm', 'ok', true);
--
--
--
-- INSERT INTO sub_product_images (sub_product_id, images)
--                     -- Iphone Black
-- VALUES (1,
--         'https://gadgetariumb9.s3.eu-central-1.amazonaws.com/1696247045092_wwen_iphone14_q422_midnight_pdp_image_position-1a-1200x1200.webp'),
--        (1,
--         'https://gadgetariumb9.s3.eu-central-1.amazonaws.com/1696247078724_wwen_iphone14_q422_midnight_pdp_image_position-1b-1200x1200.webp'),
--        (1, 'https://gadgetariumb9.s3.eu-central-1.amazonaws.com/1696247124198_wwen_iphone14_q422_midnight_pdp_image_position-2-1200x1200.webp'),
--        (1, 'https://gadgetariumb9.s3.eu-central-1.amazonaws.com/1696247175025_wwen_iphone14_q422_midnight_pdp_image_position-3-1200x1200.webp'),
--
--
--                     -- Iphone Blue
--        (2, 'https://gadgetariumb9.s3.eu-central-1.amazonaws.com/1696247238412_wwen_iphone14_q422_blue_pdp_image_position-1a-1200x1200.webp'),
--        (2,
--         'https://gadgetariumb9.s3.eu-central-1.amazonaws.com/1696247280711_wwen_iphone14_q422_blue_pdp_image_position-1b-1200x1200.webp'),
--        (2,
--         'https://gadgetariumb9.s3.eu-central-1.amazonaws.com/1696247323336_wwen_iphone14_q422_blue_pdp_image_position-3-1200x1200.webp'),
--        (2, 'https://gadgetariumb9.s3.eu-central-1.amazonaws.com/1696247415733_wwen_iphone14_q422_blue_pdp_image_position-1b-1200x1200.webp'),
--
--
--                     -- Iphone Yellow
--        (3, 'https://asiastore.kg/image/cachewebp/catalog/iphone/iphone14/iphone14plus/yellow/rukz_iphone14plus_q223_yellow_pdp_image_position-1a-1200x1200.webp'),
--        (3,
--         'https://asiastore.kg/image/cachewebp/catalog/iphone/iphone14/iphone14plus/yellow/rukz_iphone14plus_q223_yellow_pdp_image_position-1b-1200x1200.webp'),
--        (3, 'https://asiastore.kg/image/cachewebp/catalog/iphone/iphone14/iphone14plus/yellow/rukz_iphone14plus_q223_yellow_pdp_image_position-2-1200x1200.webp'),
--        (3, 'https://asiastore.kg/image/cachewebp/catalog/iphone/iphone14/iphone14plus/yellow/rukz_iphone14plus_q223_yellow_pdp_image_position-3-1200x1200.webp'),
--
--                     -- Samsung Black
--        (4, 'https://m.media-amazon.com/images/I/7103aguWL5L._AC_SX466_.jpg'),
--        (4, 'https://m.media-amazon.com/images/I/61kJAJOM5HL._AC_SX466_.jpg'),
--        (4, 'https://m.media-amazon.com/images/I/61YZN2yCW8L._AC_SX466_.jpg'),
--                     -- Samsung White
--        (5, 'https://m.media-amazon.com/images/I/817aOXLoNpL._AC_SX466_.jpg'),
--        (5, 'https://m.media-amazon.com/images/I/71e7LCSRF4L._AC_SX466_.jpg'),
--        (5, 'https://m.media-amazon.com/images/I/71p1SFyK6RL._AC_SX466_.jpg'),
--                     -- Samsung Green
--        (6, 'https://m.media-amazon.com/images/I/61nhOdlKpDL._AC_SX466_.jpg'),
--        (6, 'https://m.media-amazon.com/images/I/71D6ulAmm+L._AC_SX466_.jpg'),
--        (6, 'https://m.media-amazon.com/images/I/719QO01ZzvL._AC_SX466_.jpg'),
--
--                     -- Ipad blue
--        (7, 'https://asiastore.kg/image/cachewebp/catalog/ipad/ipad10th/blue/apple-ipad-10-9-wi-fi-64gb-blue-10th-gen-2022-plansetdators-6-635004507580c-1200x1200.webp'),
--        (7, 'https://asiastore.kg/image/cachewebp/catalog/ipad/ipad10th/blue/apple-ipad-10-9-wi-fi-64gb-blue-10th-gen-2022-plansetdators-5-63500450a3be6-1200x1200.webp'),
--        (7, 'https://asiastore.kg/image/cachewebp/catalog/ipad/ipad10th/blue/apple-ipad-10-9-wi-fi-64gb-blue-10th-gen-2022-plansetdators-4-63500450cf9b2-1200x1200.webp'),
--
--                     -- ipad white
--        (8, 'https://asiastore.kg/image/cachewebp/catalog/ipad/ipad10th/silver/apple-ipad-10-9-wi-fi-64gb-silver-10th-gen-2022-plansetdators-6-63500327256dc-1200x1200.webp'),
--        (8, 'https://asiastore.kg/image/cachewebp/catalog/ipad/ipad10th/silver/apple-ipad-10-9-wi-fi-64gb-silver-10th-gen-2022-plansetdators-5-6350032750547-1200x1200.webp'),
--        (8, 'https://asiastore.kg/image/cachewebp/catalog/ipad/ipad10th/silver/apple-ipad-10-9-wi-fi-64gb-silver-10th-gen-2022-plansetdators-4-635003277a6fd-1200x1200.webp'),
--
--
--        (9, 'https://asiastore.kg/image/cachewebp/catalog/ipad/ipad10th/pink/apple-ipad-10-9-wi-fi-64gb-pink-10th-gen-2022-plansetdators-6-63500634bc846-1200x1200.webp'),
--        (9, 'https://asiastore.kg/image/cachewebp/catalog/ipad/ipad10th/pink/apple-ipad-10-9-wi-fi-64gb-pink-10th-gen-2022-plansetdators-5-63500634f16c0-1200x1200.webp'),
--        (9, 'https://asiastore.kg/image/cachewebp/catalog/ipad/ipad10th/pink/apple-ipad-10-9-wi-fi-64gb-pink-10th-gen-2022-plansetdators-4-6350063529a19-1200x1200.webp'),
--
--                         -- Smart watch yellow
--        (10, 'https://www.kivano.kg/images/product/111401/full/1667074654_03290800.jpg'),
--        (10, 'https://www.kivano.kg/images/product/111401/166707465325497_full.jpg'),
--        (10, 'https://www.kivano.kg/images/product/111401/166707465392149_full.jpg'),
--
--                         -- black
--        (11, 'https://www.kivano.kg/images/product/111402/full/1667074645_69907900.jpg'),
--        (11, 'https://www.kivano.kg/images/product/111402/166707464570112_full.jpg'),
--        (11, 'https://www.kivano.kg/images/product/111402/166707464458579_full.jpg'),
--
--                         -- white
--        (12, 'https://www.kivano.kg/images/product/111403/full/1667074639_44532700.jpg'),
--        (12, 'https://www.kivano.kg/images/product/111403/166707463836687_full.jpg'),
--        (12, 'https://www.kivano.kg/images/product/111403/166707463852040_full.jpg'),
--
--                        -- Laptop gray
--        (13, 'https://ostore.kg/upload/iblock/680/6808d63292ee03b50285a53dfab6e508.jpg'),
--        (13, 'https://ostore.kg/upload/iblock/c62/c6288438343240ffb5ea32c2cdd82459.jpg'),
--
--                        -- blue
--        (14, 'https://ostore.kg/upload/iblock/18e/18e20731185ecf292dad0cc1b86ddddc.jpg'),
--        (14, 'https://ostore.kg/upload/iblock/174/1741c396a26619e5cfb25979444e042e.jpg'),
--        (14, 'https://ostore.kg/upload/iblock/6b8/6b8b9e0704ad7c3c9c178abf967dc226.jpg'),
--
--                       -- yellow
--        (15, 'https://ostore.kg/upload/iblock/301/3013186173084c9f4aea8feb44c9e31a.jpg'),
--        (15, 'https://ostore.kg/upload/iblock/f48/f48c6cb1470e857a06d1693c81d6d3ef.jpg'),
--        (15, 'https://ostore.kg/upload/iblock/e8c/e8c15bbdacebd6c6a32e3669d98761d9.jpg');
--
--
--
-- INSERT INTO user_comparison (comparison, user_id)
-- VALUES (1, 1),
--        (4, 1),
--        (7, 1);
-- --
-- INSERT INTO user_favorite (favorite, user_id)
-- VALUES (1, 2),
--        (4, 2),
--        (7, 2);
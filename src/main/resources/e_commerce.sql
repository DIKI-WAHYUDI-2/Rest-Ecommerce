SELECT products.* FROM products;
SELECT products.* FROM products WHERE name = 'Slim Fit Polo shirt';

DESC products;
SHOW TABLES ;

select * from categories;

# ALTER TABLE products DROP COLUMN image;

SELECT products.*, product_images.*
FROM products
JOIN product_images ON products.id = product_images.product_image;


DELIMITER //
CREATE PROCEDURE GetProductWithImageByName(IN productName VARCHAR(255))
BEGIN
    SELECT p.id AS products_id, p.name, p.brand, p.price, p.description, p.category_id, pi.id AS id, pi.image
    FROM products p
             JOIN product_images pi ON p.id = pi.product_image
    WHERE p.name = productName;
END //
DELIMITER ;



DELIMITER //

CREATE PROCEDURE GetProductsByCategoryName(IN categoryName VARCHAR(255))
BEGIN
    SELECT p.id AS product_id,p.category_id, p.name AS product_name, p.brand AS product_brand, p.price AS product_price, p.description AS product_description,
           c.id AS category_id, c.name AS category_name
    FROM products p
             JOIN categories c ON p.category_id = c.id
    WHERE c.name = categoryName;
END //

DELIMITER ;

SELECT * FROM product_images;

SELECT p.*,c.*
FROM products p
JOIN categories c ON p.category_id = c.id
WHERE c.name = 'Shirt';



-- # DESC categories;
DROP PROCEDURE GetProductsByCategoryName;
# CALL GetAllProductByCategory('Shirt');

DESC product_images;

DESC categories;
DESC products;
DESC sub_categories;

SELECT * FROM sub_categories;
SELECT * FROM categories;
SELECT * FROM products;

DELETE FROM sub_categories;

SELECT c.*, cs.*
FROM categories c JOIN sub_categories cs
ON c.id = cs.category_id
WHERE c.name = 'Women';

UPDATE sub_categories
SET category_id = 3
WHERE id = 15;

SELECT p.name,p.brand,p.product_image
FROM products p
         JOIN categories c ON p.category_id = c.id
         JOIN sub_categories sc ON p.sub_category_id = sc.id
WHERE c.name = 'Men' AND  sc.name = 'Shirt';

DESC cart_item;

SELECT p.id,p.price,ci.id,ci.quantity
(p.price * ci.quantity) AS total_per_product
FROM products p JOIN cart_item ci ON p.cart_item_id = ci.id;

DELETE FROM cart_item WHERE id = 9;

SELECT * FROM checkout;

SELECT p.id,p.price,ci.id,ci.quantity,c.id,c.total
FROM products p
    JOIN cart_item ci  ON ci.id = p.cart_item_id
    JOIN checkout c ON  c.id = p.checkout_id;

INSERT INTO checkout (total)
SELECT
    SUM(p.price * ci.quantity) AS total
FROM
    products p
        JOIN
    cart_item ci
    ON
        p.cart_item_id = ci.id;


desc checkout;

SELECT
    p.id AS product_id,
    p.price,
    ci.id AS cart_item_id,
    ci.quantity,
    c.id AS checkout_id,
    c.total
FROM
    products p
        JOIN
    cart_item ci ON p.cart_item_id = ci.id
        JOIN
    checkout c ON ci.checkout_id = c.id;

SELECT SUM(p.price * ci.quantity) AS total,
       p.id AS product_id,
       ci.id AS cart_item_id
FROM
    products p
JOIN cart_item ci ON p.cart_item_id = ci.id
GROUP BY p.id, ci.id;

SELECT * FROM products;

desc products;

show tables;

ALTER TABLE products DROP foreign key product_id;
desc cart;

select *
from cart;


ALTER TABLE products
    DROP COLUMN checkout_id,
    DROP COLUMN product_id;

SHOW CREATE TABLE products;

ALTER TABLE session
    DROP FOREIGN KEY FKj3ffkfbfbdoh9uon462ow7lhl;

ALTER TABLE session
    DROP COLUMN user_id;

select * from cart_item;
desc cart_item;

desc product_images;

desc session;

select u.*,s.* from users u JOIN session s ON u.email = s.email;

select * from products ;

ALTER TABLE products MODIFY COLUMN description VARCHAR(500);




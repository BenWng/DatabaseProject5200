insert into Users (email, name, password, shippingAddress, seller, shopName, admin) values ('user1@gmail.com', 'user1', 'user2', '123 test st', 1, 'Macy\'s', 0);
insert into Users (email, name, password, shippingAddress, seller, shopName, admin) values ('user2@gmail.com', 'user2', 'user2', '345 test st', 0, 'Sears\'s', 0);
insert into Users (email, name, password, shippingAddress, seller, shopName, admin) values ('user3@gmail.com', 'user3', 'user3', '567 test st', 0, 'JCPenney\'s', 1);
insert into CategoryType (name) value ('Food');
insert into CategoryType (name) value ('Clothes');
insert into CategoryType (name) value ('Electronics');
insert into ShopCategories (sellerId, category) values (1, 'Food');
insert into ShopCategories (sellerId, category) values (1, 'Clothes');
insert into ShopCategories (sellerId, category) values (1, 'Electronics');
insert into ProductsSelling (name, price, shortDescription, longDescription, sellerId, category, quantity, pictureURL) values ('Goodie 1', 10.99, 'Goodie 1 short description', 'Goodie 1 complete description', 1, 'Food', 1, 'path');
insert into ProductsSelling (name, price, shortDescription, longDescription, sellerId, category, quantity, pictureURL) values ('Goodie 2', 7.99, 'Goodie 2 short description', 'Goodie 2 complete description', 1, 'Clothes', 1, 'path');
insert into ProductsSelling (name, price, shortDescription, longDescription, sellerId, category, quantity, pictureURL) values ('Sandwich', 18, 'Sandwich short description', 'Sandwich complete description', 1, 'Food', 1, 'path');
insert into ProductsSelling (name, price, shortDescription, longDescription, sellerId, category, quantity, pictureURL) values ('Hamburger', 18, 'Hamburger short description', 'Hamburger complete description', 1, 'Food', 1, 'path');
insert into ProductsSelling (name, price, shortDescription, longDescription, sellerId, category, quantity, pictureURL) values ('Suit', 18, 'Suit short description', 'Suit complete description', 1, 'Clothes', 1, 'path');
insert into ProductsSelling (name, price, shortDescription, longDescription, sellerId, category, quantity, pictureURL) values ('Dress', 18, 'Dress short description', 'Dress complete description', 1, 'Clothes', 1, 'path');
insert into ProductsSelling (name, price, shortDescription, longDescription, sellerId, category, quantity, pictureURL) values ('iPhone', 200, 'The next big thing is here', 'Mobile Phone\'s revolution. 4 speakers, 5.5 inch, ID Touch', 1, 'Electronics', 1, 'path');
insert into ProductsSold (name, price, shortDescription, longDescription, sellerId, purchaserId, category, shipped, received, pictureURL) values ('iPhone6s', 300, 'The next bigger thing is here', 'Mobile Phone\'s revolution. 8 speakers, 5.5 inch, Improved ID Touch', 1, 2, 'Electronics', 1, 0, 'path');
insert into Wishlist (userId, productId) values (1,1);
insert into Wishlist (userId, productId) values (2,3);
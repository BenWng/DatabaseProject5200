create table Users(
  id int primary key auto_increment,
  email varchar(48),
  name varchar(48) not null,
  password varchar(200) not null,
  shippingAddress varchar(100),
  seller boolean,
  shopName varchar(200),
  admin boolean
);

create table CategoryType(
  name varchar(48) primary key
);

create table ProductsSold(
  id int primary key auto_increment,
  name varchar(48) not null,
  price boolean not null,
  shortDescription varchar(1000) not null,
  longDescription varchar(1000) not null,
  sellerId int not null,
  foreign key (sellerId) references Users(id)
  on update cascade
  on delete no action,
  purchaserId int not null,
  foreign key (purchaserId) references Users(id)
  on update cascade
  on delete no action,
  category varchar(48),
  foreign key (category) references CategoryType(name)
  on update cascade
  on delete no action,
  shipped boolean,
  received boolean,
  pictureURL varchar(255)
);

create table ProductsSelling(
  id int primary key auto_increment,
  name varchar(48) not null,
  price boolean not null,
  shortDescription varchar(1000) not null,
  longDescription varchar(1000) not null,
  sellerId int not null,
  foreign key (sellerId) references Users(id)
  on update cascade
  on delete no action,
  category varchar(48),
  foreign key (category) references CategoryType(name)
  on update cascade
  on delete no action,
  quantity int,
  pictureURL varchar(255)
);

create table FollowedUsers(
  followerId int,
  followedId int,
  primary key (followerId, followedId)
  );
  
create table PicturesSold(
  productId int primary key,
  picture longblob,
  foreign key (productId) references ProductsSold(id)
  on update cascade
  on delete cascade
  );
    
create table PicturesSelling(
  productId int primary key,
  picture longblob,
  foreign key (productID) references ProductsSelling(id)
  on update cascade
  on delete cascade
  );
  
create table Wishlist(
  userId int not null,
  productId int not null,
  foreign key (userId) references Users(id)
  on update cascade
  on delete cascade,
  foreign key (productId) references ProductsSelling(id)
  on update cascade
  on delete cascade,
  primary key(userId, productId)
  );

create table ShopCategories(
  sellerId int,
  category varchar(200),
  foreign key(sellerId) references Users(id)
  on update cascade
  on delete cascade,
  primary key(sellerId, category)
  );

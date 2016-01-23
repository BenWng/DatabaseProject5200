create table Users(
	id int primary key auto_increment,
  Email varchar(48) not null,
  Name varchar(48) not null,
  ShippingAddress varchar(100),
  SellerFlag boolean,
  AdminFlag boolean
);

create table CategoryType(
	name varchar(48) primary key
);

create table ProductSold(
	id int primary key auto_increment,
  Name varchar(48) not null,
  Price boolean not null,
  Description varchar(1000) not null,
  SellerId int not null,
  foreign key (SellerId) references Users(id)
  on update cascade
  on delete no action,
  PurchaserId int not null,
  foreign key (PurchaserId) references Users(id)
  on update cascade
  on delete no action,
  Category varchar(48),
  foreign key (Category) references CategoryType(name)
  on update cascade
  on delete no action,
  ShippedFlag boolean not null,
  ReceivedFlag boolean not null
);


create table ProductSelling(
	id int primary key auto_increment,
  Name varchar(48) not null,
  Price boolean not null,
  Description varchar(1000) not null,
  SellerId int not null,
  foreign key (SellerId) references Users(id)
  on update cascade
  on delete no action,
  PurchaserId int not null,
  foreign key (PurchaserId) references Users(id)
  on update cascade
  on delete no action,
  Category varchar(48),
  foreign key (Category) references CategoryType(name)
  on update cascade
  on delete no action,
 	Quantity int not null
);




create table FollowedUsers(
  FollowerID int,
  FollowedID int,
  primary key (FollowerID, FollowedID)
  );
  
create table PicturesSold(
  ProductID int primary key,
  Picture longblob,
  foreign key (ProductID) references ProductSold(id)
  on update cascade
  on delete cascade
  );
    
    
create table PicturesSelling(
  ProductID int primary key,
  Picture longblob,
  foreign key (ProductID) references ProductSelling(id)
  on update cascade
  on delete cascade
  );
  
  
create table Wishlist(
  UserID int not null,
  ProductID int not null,
  foreign key (UserID) references Users(id)
  on update cascade
  on delete cascade,
  foreign key (ProductID) references ProductSelling(id)
  on update cascade
  on delete cascade,
  primary key(UserID, ProductID)
  );
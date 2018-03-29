create table os_user(
	id varchar2(32) primary key not null,
	username varchar2(20) not null,
	password varchar2(32) not null,
	gender char(2) not null,
	birthday date not null,
	address varchar2(100) not null,
	telephone varchar2(32) not null,
	regist_time date not null,
	isSeller number(1) not null
);

create table os_category(
	id number primary key not null,
	name varchar2(20) not null
);

create table os_item(
	id number primary key not null,
	name varchar2(40) not null,
	price number not null,
	ogl_price number not null,
	sales_vol number default 0,
	repository number not null,
	publish_time date not null,
	img varchar2(255) not null,
	likes number default 0,
	category_id number not null,
	user_id varchar2(32) not null,
	constraint fk_item_category_id foreign key (category_id) references os_category(id),
	constraint fk_item_user_id foreign key (user_id) references os_user(id)
);

create table os_favorite(
	id number primary key not null,
	user_id varchar2(32) not null,
	constraint fk_favorite_user_id foreign key (user_id) references os_user(id)
);

create table os_favoriteItem(
	id number primary key not null,
	item_id number not null,
	fvt_id number not null,
	constraint fk_favoriteItem_favorite_id foreign key (fvt_id) references os_favorite(id),
	constraint fk_favoriteItem_item_id foreign key (item_id) references os_item(id)
);

create table os_order(
	id varchar2(32) primary key not null,
	seller_id varchar2(32) not null,
	ctm_id varchar2(32) not null,
	status number not null,
	constraint fk_order_seller_id foreign key (seller_id) references os_user(id),
	constraint fk_order_ctm_id foreign key (ctm_id) references os_user(id)
);

create table os_orderItem(
	id number primary key not null,
	item_id number not null,
	quantity number not null,
	order_id varchar2(32) not null,
	constraint fk_orderItem_order_id foreign key (order_id) references os_order(id)
);

create table os_review(
	id number primary key not null,
	item_id number not null,
	user_id varchar2(32) not null,
	order_id varchar2(32) not null,
	feedbacks number default 0,
	content varchar(1000) default '好评！',
	constraint fk_review_item_id foreign key (item_id) references os_item(id),
	constraint fk_review_user_id foreign key (user_id) references os_user(id),
	constraint fk_review_order_id foreign key (order_id) references os_order(id)
);

create table os_description(
	id number primary key not null,
	item_id number not null,
	content varchar2(2000) default '商品简介',
	img varchar2(400) not null,
	constraint fk_desc_item_id foreign key (item_id) references os_item(id)
);

create table os_cart(
	id number primary key not null,
	user_id varchar2(32) not null,
	constraint fk_cart_user_id foreign key (user_id) references os_user(id)
);

create table os_cartItem(
	id number primary key not null,
	quantity number default 0,
	item_id number not null,
	cart_id number not null,
	constraint fk_cartItem_item_id foreign key (item_id) references os_item(id),
	constraint fk_cartItem_cart_id foreign key (cart_id) references os_cart(id)
);




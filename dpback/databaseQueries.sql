CREATE TABLE category{
	id IDENTITY,
	name varchar (50),
	desceription varchar(250),
	image_url varchar (50),
	is_active boolean,
	
	CONSTRAINT pk_category_id PRIMARY KEY (id)

};
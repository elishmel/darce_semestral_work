INSERT INTO tags (tag) VALUES ('Odění') ON CONFLICT DO NOTHING;
INSERT INTO tags (tag) VALUES ('Dům') ON CONFLICT DO NOTHING;
INSERT INTO tags (tag) VALUES ('Zahrada') ON CONFLICT DO NOTHING;
INSERT INTO tags (tag) VALUES ('Jídlo') ON CONFLICT DO NOTHING;
INSERT INTO tags (tag) VALUES ('Zdravotní') ON CONFLICT DO NOTHING;
INSERT INTO tags (tag) VALUES ('Koníčky') ON CONFLICT DO NOTHING;

INSERT INTO images (image_id, url) VALUES (0,'http://localhost:8080/pictures/Basic_human_drawing.png') ON CONFLICT DO NOTHING;
INSERT into images (image_id, url) VALUES (1,'https://images.pexels.com/photos/100582/pexels-photo-100582.jpeg') on conflict do nothing ;
INSERT into images (image_id, url) VALUES (2,'https://images.pexels.com/photos/276517/pexels-photo-276517.jpeg') on conflict do nothing ;
INSERT into images (image_id, url) VALUES (3,'https://images.pexels.com/photos/1294731/pexels-photo-1294731.jpeg') on conflict do nothing ;
INSERT into images (image_id, url) VALUES (4,'https://images.pexels.com/photos/1277939/pexels-photo-1277939.jpeg') on conflict do nothing ;

/* Zdrojem obrazku jsou pravoplatni vlastnici skrze portal pexels.com*/

INSERT INTO clients (client_id, date_created, date_last_logon, real_name, username, profile_picture_image_id) VALUES
                                                                                                                  (0,now(),now(),'Lumir Polevka (Test)','Lumir',0);

INSERT INTO clients (client_id, date_created, date_last_logon, real_name, username, profile_picture_image_id) VALUES
                                                                                                                  (1,now(),now(),'Esmeralda Kvetakova (Test)','Esmeralda',null);

INSERT INTO items (item_id, active, description, name, offer, author_client_id, receiver_client_id) VALUES
                                                                                                        (0,true,'Kolo pro kazdou prilezitost!','Jizdni kolo',true,0,null);
INSERT INTO items (item_id, active, description, name, offer, author_client_id, receiver_client_id) VALUES
                                                                                                        (1,true,'Batoh na dlouhe streky!','Turisticky batoh',true,0,1);
INSERT INTO items (item_id, active, description, name, offer, author_client_id, receiver_client_id) VALUES
                                                                                                        (2,true,'Vetsi kovova panev.','Metalicka panev',false,1,null);

insert into items_images (items_item_id, images_image_id) VALUES (0,1);
insert into items_images (items_item_id, images_image_id) VALUES (0,2);

insert into items_images (items_item_id, images_image_id) VALUES (1,3);

insert into items_images (items_item_id, images_image_id) VALUES (2,4);

insert into items_tags (items_item_id, tags_tag) VALUES (0,'Koníčky');
insert into items_tags (items_item_id, tags_tag) VALUES (1,'Zdravotní');
insert into items_tags (items_item_id, tags_tag) VALUES (2,'Dům');




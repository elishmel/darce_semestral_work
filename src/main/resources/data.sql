INSERT INTO tags (tag) VALUES ('Odění') ON CONFLICT DO NOTHING;
INSERT INTO tags (tag) VALUES ('Dům') ON CONFLICT DO NOTHING;
INSERT INTO tags (tag) VALUES ('Zahrada') ON CONFLICT DO NOTHING;
INSERT INTO tags (tag) VALUES ('Jídlo') ON CONFLICT DO NOTHING;
INSERT INTO tags (tag) VALUES ('Zdravotní') ON CONFLICT DO NOTHING;
INSERT INTO tags (tag) VALUES ('Koníčky') ON CONFLICT DO NOTHING;

INSERT INTO images (image_id, url) VALUES (0,'http://localhost:8080/images/Basic_human_drawing.png') ON CONFLICT DO NOTHING;

INSERT INTO clients (client_id, date_created, date_last_logon, real_name, username, profile_picture_image_id) VALUES
                                                                                                                  (0,now(),now(),'Testovací uživatel','Lumír',0);
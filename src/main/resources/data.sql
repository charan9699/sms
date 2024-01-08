INSERT INTO `user` (username, passcode) VALUES 
('charan1', '$2a$10$kgD6yBwntSM2w7lBNGafVeXkWno8.rjb4WydxYvTgB9yV/5Tk8Y26'),
('charan2', '$2a$10$kgD6yBwntSM2w7lBNGafVeXkWno8.rjb4WydxYvTgB9yV/5Tk8Y26'),
('charan3', '$2a$10$kgD6yBwntSM2w7lBNGafVeXkWno8.rjb4WydxYvTgB9yV/5Tk8Y26');
INSERT INTO `message` (text, sender_id, receiver_id, status) VALUES
('Hello Charan', 1, 2, 'RECEIVED'),
('Hello Charan', 1, 3, 'RECEIVED'),
('Hello Charan', 2, 3, 'RECEIVED');
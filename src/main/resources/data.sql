DELETE FROM `user_role`;
DELETE FROM `role`;
DELETE FROM `user`;

/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`role_id`, `created_by`, `created_on`, `modified_by`, `modified_on`, `role`) VALUES
	(1, 'Fri Mar 16 16:23:42 IST 2018', 'Fri Mar 16 16:23:42 IST 2018', 'SYSTEM', 'SYSTEM', 'ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`user_id`, `email`, `name`, `password`, `role`, `status`) VALUES
	('U_101', 'camiloskidata@gmail.com', 'Camilo Skidata', '$2a$10$8KLzc/5Up2hB0AKesdEWgeTzgT71TUOsXbDSY2AqzGxXI47OHzORy', 'DEV', b'1');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
	('U_101', 1);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;

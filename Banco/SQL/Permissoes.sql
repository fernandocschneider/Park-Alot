CREATE ROLE admin_role;

CREATE USER 'admin_user'@'localhost' IDENTIFIED BY 'admin123';

GRANT admin_role TO 'admin_user'@'localhost';

GRANT ALL PRIVILEGES ON ParkAlot.* TO 'admin_user'@'localhost';

SHOW GRANTS FOR 'admin_user'@'localhost';

DROP USER 'admin_user'@'localhost';

DROP ROLE admin_role;
CREATE DATABASE myloginboarddb;

USE myloginboarddb;

CREATE USER 'admindb'@'localhost' IDENTIFIED BY 'Ser@vic@es13$';

GRANT ALL PRIVILEGES ON myloginboarddb.* TO 'admindb'@'localhost';

FLUSH PRIVILEGES;


CREATE TABLE sec_companies (
  id_company INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  status VARCHAR(1) NOT NULL,
  entry_date datetime,
  entry_user VARCHAR(15),
  modify_date datetime,
  modify_user VARCHAR(15),
  PRIMARY KEY (id_company)
) ENGINE=InnoDB;

CREATE TABLE sec_users (
  id_user INT(11) NOT NULL AUTO_INCREMENT,
  username VARCHAR(15) NOT NULL,
  password VARCHAR(100) NOT NULL,
  id_company INT(11) NOT NULL,
  status VARCHAR(1) NOT NULL,
  entry_date datetime,
  entry_user VARCHAR(15),
  modify_date datetime,
  modify_user VARCHAR(15),
  PRIMARY KEY (id_user),
  FOREIGN KEY (id_company) REFERENCES sec_companies (id_company) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE sec_roles (
  id_rol INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  status VARCHAR(1) NOT NULL,
  entry_date datetime,
  entry_user VARCHAR(15),
  modify_date datetime,
  modify_user VARCHAR(15),
  PRIMARY KEY (id_rol)
) ENGINE=InnoDB;

CREATE TABLE sec_roles_x_users (
  id_rol INT(11) NOT NULL,
  id_user INT(11) NOT NULL,
  status VARCHAR(1) NOT NULL,
  entry_date datetime,
  entry_user VARCHAR(15),
  modify_date datetime,
  modify_user VARCHAR(15),
  PRIMARY KEY (id_rol, id_user),
  FOREIGN KEY (id_rol) REFERENCES sec_roles (id_rol) ON DELETE CASCADE,
  FOREIGN KEY (id_user) REFERENCES sec_users (id_user) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE sec_menu_options (
  id_menu_option INT(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  url VARCHAR(255) NOT NULL,
  icon VARCHAR(50),
  order_level SMALLINT,
  is_final_option VARCHAR(1),
  id_menu_option_parent INT(11),
  status VARCHAR(1) NOT NULL,
  entry_date datetime,
  entry_user VARCHAR(15),
  modify_date datetime,
  modify_user VARCHAR(15),
  PRIMARY KEY (id_menu_option),
  FOREIGN KEY (id_menu_option_parent) REFERENCES sec_menu_options (id_menu_option) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE sec_menu_options_x_roles (
  id_rol INT(11) NOT NULL,
  id_menu_option INT(11) NOT NULL,
  status VARCHAR(1) NOT NULL,
  entry_date datetime,
  entry_user VARCHAR(15),
  modify_date datetime,
  modify_user VARCHAR(15),
  PRIMARY KEY (id_rol, id_menu_option),
  FOREIGN KEY (id_rol) REFERENCES sec_roles (id_rol) ON DELETE CASCADE,
  FOREIGN KEY (id_menu_option) REFERENCES sec_menu_options (id_menu_option) ON DELETE CASCADE
) ENGINE=InnoDB;




INSERT INTO `myloginboarddb`.`sec_companies`
(`name`,
`status`,
`entry_date`,
`entry_user`)
VALUES
('Company Name',
'A',
now(),
'TSANGINEZ');



INSERT INTO `myloginboarddb`.`sec_users`
(`username`,
`password`,
`id_company`,
`status`,
`entry_date`,
`entry_user`)
VALUES
('TSANGINEZ',
'$2a$10$DWylzhyzpXtBUjrRmyQthu5KiaM3ZJZ/B6T5bLKYfM.YfF/XmbW1.',
1,
'A',
NOW(),
'TSANGINEZ');


CREATE TABLE User (
  id                  BIGINT NOT NULL AUTO_INCREMENT,
  modificationCounter INTEGER NOT NULL,
  name                VARCHAR(255) NOT NULL,
  password            VARCHAR(255) NOT NULL,
  PRIMARY KEY (ID),
  CONSTRAINT UC_User_name UNIQUE(name)
);

CREATE TABLE Role (
  id                  BIGINT NOT NULL AUTO_INCREMENT,
  modificationCounter INTEGER NOT NULL,
  name                VARCHAR(255) NOT NULL,
  PRIMARY KEY (ID),
  CONSTRAINT UC_Role_name UNIQUE(name)
);

CREATE TABLE Right (
  id                  BIGINT NOT NULL AUTO_INCREMENT,
  modificationCounter INTEGER NOT NULL,
  name                VARCHAR(255) NOT NULL,
  PRIMARY KEY (ID),
  CONSTRAINT UC_Right_name UNIQUE(name)
);

CREATE TABLE RoleRight (
  roleId             BIGINT NOT NULL REFERENCES Role(id),
  rightId             BIGINT NOT NULL REFERENCES Right(id)
);

CREATE TABLE UserRole (
  userId             BIGINT NOT NULL REFERENCES User(id),
  roleId             BIGINT NOT NULL REFERENCES Role(id)
);

INSERT INTO
  Right
VALUES
  (1, 0, 'order-service.AddItem'),
  (2, 0, 'order-service.AddOrder')
;

INSERT INTO
  Role
VALUES
  (1, 0, 'Waiter'),
  (2, 0, 'Cook')
;

INSERT INTO
  RoleRight
VALUES
  (1, 2),
  (2, 1),
;

INSERT INTO
  User
VALUES
  (1, 0, 'waiter', 'waiter'),
  (2, 0, 'cook', 'cook'),
  (3, 0, 'admin', 'admin'),
;

INSERT INTO
  UserRole
VALUES
  (1, 1),
  (2, 2),
  (3, 1),
  (3, 2)
;
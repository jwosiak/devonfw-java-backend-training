CREATE TABLE Item (
  id                  BIGINT NOT NULL AUTO_INCREMENT,
  modificationCounter INTEGER NOT NULL,
  description         VARCHAR(255),
  name                VARCHAR(255) NOT NULL,
  price               DOUBLE,
  PRIMARY KEY (ID),
  CONSTRAINT UC_Item_name UNIQUE(name)
);

CREATE TABLE Customer (
  id                  BIGINT NOT NULL AUTO_INCREMENT,
  modificationCounter INTEGER NOT NULL,
  firstname           VARCHAR(255) NOT NULL,
  lastname            VARCHAR(255) NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE OrderSummary (
  id                  BIGINT NOT NULL AUTO_INCREMENT,
  modificationCounter INTEGER NOT NULL,
  price               DOUBLE,
  creationDate        DATE NOT NULL,
  ownerId             BIGINT NOT NULL REFERENCES Customer(id),
  status              VARCHAR(255) NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE OrderPosition (
  orderId             BIGINT NOT NULL REFERENCES OrderSummary(id),
  itemId              BIGINT NOT NULL REFERENCES Item(id)
);
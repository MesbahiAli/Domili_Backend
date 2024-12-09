
CREATE TABLE residence_exercice (
  exercice_id 			character varying(36) NOT NULL,
  residence_id 			character varying(36) NOT NULL,
  exercice_title 		character varying(36) NOT NULL,
  start_date 			DATE,
  end_date 				DATE,
  previous_exercice_id 	character varying(36) ,
  constraint pk_residence_exercice PRIMARY KEY (exercice_id)
  
);
--------------------------------------------------
CREATE TABLE residence (
  residence_id 		character varying(36) NOT NULL,
  residence_name 	character varying(50) NOT NULL,
  residence_adresse character varying(1024) DEFAULT '',
  latitude 			numeric(10, 6) default 0.0,
  longitude 		numeric(10, 6) default 0.0,
  city_name 		character varying(30) DEFAULT '',
  nbr_lots 			integer NOT NULL,
  total_tentieme 	integer default 0,
  total_milliem 	integer default 0,
  exercice_id 		character varying(36) NOT NULL,
  constraint pk_residence PRIMARY KEY (residence_id),
  constraint fk_residence_exercice FOREIGN KEY (exercice_id) REFERENCES residence_exercice (exercice_id)
);
--------------------------------------------------
CREATE TABLE residence_group (
  group_id 		character varying(36) NOT NULL,
  group_title 	character varying(50) NOT NULL,
  constraint pk_residence_group PRIMARY KEY (group_id)
);

RESIDENCE
-----------

Compte pour les lots
4500 00001 => LOT N° 1
4500 00002
4500 00003

--------------------------------------------------
CREATE TABLE Residence_Account (
  account_id VARCHAR(36) PRIMARY KEY,
  account_code VARCHAR(24),
  account_title VARCHAR(50),
  residence_id VARCHAR(36),
  FOREIGN KEY (residence_id) REFERENCES Residence (residence_id)
);
--------------------------------------------------
CREATE TABLE Residence_Lot (
  lot_id VARCHAR(36) PRIMARY KEY,
  residence_id VARCHAR(36),
  account_id VARCHAR(36),
  group_id VARCHAR(36),
  nbr_tentieme INT,
  nbr_milliem INT,
  lot_type VARCHAR(20),
  FOREIGN KEY (residence_id) REFERENCES Residence (residence_id),
  FOREIGN KEY (account_id) REFERENCES Residence_Account (account_id),
  FOREIGN KEY (group_id) REFERENCES RESIDENCE_GROUP (group_id)
);
--------------------------------------------------
CREATE TABLE Appel_Fonds (
  appel_id VARCHAR(36) PRIMARY KEY,
  appel_title VARCHAR(50),
  residence_id VARCHAR(36),
  exercice_id VARCHAR(36),
  appel_fonds_status VARCHAR(255),
  FOREIGN KEY (residence_id) REFERENCES Residence (residence_id),
  FOREIGN KEY (exercice_id) REFERENCES Residence_Exercice (exercice_id)
);
--------------------------------------------------
CREATE TABLE Residence_Budget (
  budget_id VARCHAR(36) PRIMARY KEY,
  budget_code VARCHAR(24),
  budget_title VARCHAR(24),
  residence_id VARCHAR(36),
  budget_type VARCHAR(15),
  FOREIGN KEY (residence_id) REFERENCES Residence (residence_id)
);
--------------------------------------------------
CREATE TABLE Appel_Fonds_Budget (
  appel_budget_id VARCHAR(36) PRIMARY KEY,
  appel_id VARCHAR(36),
  residence_id VARCHAR(36),
  exercice_id VARCHAR(36),
  budget_id VARCHAR(36),
  calcul_type VARCHAR(20),
  total_amount DECIMAL(10, 2),
  FOREIGN KEY (appel_id) REFERENCES Appel_Fonds (appel_id),
  FOREIGN KEY (residence_id) REFERENCES Residence (residence_id),
  FOREIGN KEY (exercice_id) REFERENCES Residence_Exercice (exercice_id),
  FOREIGN KEY (budget_id) REFERENCES Residence_Budget (budget_id)
);
---------------------------------
CREATE TABLE Appel_Fonds_Lots (
  appel_lot_id VARCHAR(36) PRIMARY KEY,
  appel_id VARCHAR(36),
  residence_id VARCHAR(36),
  exercice_id VARCHAR(36),
  budget_id VARCHAR(36),
  lot_id VARCHAR(36),
  account_id VARCHAR(36),
  appel_proportion DECIMAL(10, 2),
  budget_amount DECIMAL(10, 2),
  FOREIGN KEY (appel_id) REFERENCES Appel_Fonds (appel_id),
  FOREIGN KEY (residence_id) REFERENCES Residence (residence_id),
  FOREIGN KEY (exercice_id) REFERENCES Residence_Exercice (exercice_id),
  FOREIGN KEY (budget_id) REFERENCES Residence_Budget (budget_id),
  FOREIGN KEY (lot_id) REFERENCES Residence_Lot (lot_id),
  FOREIGN KEY (account_id) REFERENCES Residence_Account (account_id)
);



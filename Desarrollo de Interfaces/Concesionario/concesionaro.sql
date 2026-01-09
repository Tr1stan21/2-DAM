DROP DATABASE IF EXISTS car_dealership;

CREATE DATABASE IF NOT EXISTS car_dealership
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE car_dealership;

SET FOREIGN_KEY_CHECKS = 0;

-- =========================
-- 1) Organización
-- =========================
DROP TABLE IF EXISTS repair_material;
DROP TABLE IF EXISTS mechanic_specialty;
DROP TABLE IF EXISTS repair;
DROP TABLE IF EXISTS repair_status;
DROP TABLE IF EXISTS sale;
DROP TABLE IF EXISTS offer;
DROP TABLE IF EXISTS offer_status;
DROP TABLE IF EXISTS payment_method;
DROP TABLE IF EXISTS client_vehicle_interest;
DROP TABLE IF EXISTS client;
DROP TABLE IF EXISTS vehicle_image;
DROP TABLE IF EXISTS vehicle;
DROP TABLE IF EXISTS vehicle_category;
DROP TABLE IF EXISTS material;
DROP TABLE IF EXISTS material_type;

DROP TABLE IF EXISTS manager;
DROP TABLE IF EXISTS head_mechanic;
DROP TABLE IF EXISTS mechanic;
DROP TABLE IF EXISTS sales_employee;
DROP TABLE IF EXISTS worker;

DROP TABLE IF EXISTS dealership;

SET FOREIGN_KEY_CHECKS = 1;


CREATE TABLE dealership (
  id_dealership INT NOT NULL AUTO_INCREMENT,
  name          VARCHAR(120) NOT NULL,
  address       VARCHAR(200) NOT NULL,
  city          VARCHAR(80)  NOT NULL,
  province      VARCHAR(80)  NOT NULL,
  country       VARCHAR(80)  NOT NULL,
  PRIMARY KEY (id_dealership)
);

-- =========================
-- 2) Usuarios (base + subtipos)
-- =========================
CREATE TABLE worker (
  id_worker     INT NOT NULL AUTO_INCREMENT,
  id_dealership INT NOT NULL,
  email         VARCHAR(255) NOT NULL,
  password_hash VARCHAR(255) NOT NULL,
  full_name     VARCHAR(160) NOT NULL,
  phone_number  VARCHAR(30),
  entry_date    DATE NOT NULL DEFAULT (CURRENT_DATE),
  active        TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (id_worker),
  UNIQUE KEY ux_worker_email (email),
  KEY ix_worker_dealership (id_dealership),
  CONSTRAINT fk_worker_dealership
    FOREIGN KEY (id_dealership) REFERENCES dealership(id_dealership)
    ON DELETE RESTRICT ON UPDATE CASCADE
);

-- Subtipos (PK = FK a worker)
CREATE TABLE sales_employee (
  id_worker INT NOT NULL,
  PRIMARY KEY (id_worker),
  CONSTRAINT fk_sales_worker
    FOREIGN KEY (id_worker) REFERENCES worker(id_worker)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE mechanic (
  id_worker INT NOT NULL,
  PRIMARY KEY (id_worker),
  CONSTRAINT fk_mechanic_worker
    FOREIGN KEY (id_worker) REFERENCES worker(id_worker)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE head_mechanic (
  id_worker INT NOT NULL,
  PRIMARY KEY (id_worker),
  CONSTRAINT fk_head_mechanic_worker
	FOREIGN KEY (id_worker) REFERENCES mechanic(id_worker)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE manager (
  id_worker INT NOT NULL,
  PRIMARY KEY (id_worker),
  CONSTRAINT fk_manager_worker
    FOREIGN KEY (id_worker) REFERENCES worker(id_worker)
    ON DELETE CASCADE ON UPDATE CASCADE
);

-- =========================
-- 3) Vehículos
-- =========================
CREATE TABLE vehicle_category (
  id_category INT AUTO_INCREMENT NOT NULL,
  name        VARCHAR(30) NOT NULL,
  PRIMARY KEY (id_category),
  UNIQUE KEY ux_vehicle_category_name (name)
);

CREATE TABLE vehicle (
  id_vehicle     INT NOT NULL AUTO_INCREMENT,
  id_dealership  INT NOT NULL,
  id_category    INT NOT NULL,
  vin            VARCHAR(25),
  license_plate  VARCHAR(20),
  brand          VARCHAR(80) NOT NULL,
  model          VARCHAR(80) NOT NULL,
  year           SMALLINT NOT NULL,
  km             INT NOT NULL,
  fuel           VARCHAR(30) NOT NULL,
  base_price     DECIMAL(12,2) NOT NULL,
  arrival_date   DATE NOT NULL,
  status         ENUM('IN_STOCK','SOLD','IN_REPAIR') NOT NULL DEFAULT 'IN_STOCK',
  PRIMARY KEY (id_vehicle),
  UNIQUE KEY ux_vehicle_vin (vin),
  UNIQUE KEY ux_vehicle_plate (license_plate),
  KEY ix_vehicle_dealership_arrival (id_dealership, arrival_date),
  KEY ix_vehicle_category (id_category),
  CONSTRAINT fk_vehicle_dealership
    FOREIGN KEY (id_dealership) REFERENCES dealership(id_dealership)
    ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT fk_vehicle_category
    FOREIGN KEY (id_category) REFERENCES vehicle_category(id_category)
    ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT chk_vehicle_year CHECK (year BETWEEN 1900 AND 2200),
  CONSTRAINT chk_vehicle_km CHECK (km >= 0),
  CONSTRAINT chk_vehicle_price CHECK (base_price >= 0)
);

CREATE TABLE vehicle_image (
  id_image   INT NOT NULL AUTO_INCREMENT,
  id_vehicle INT NOT NULL,
  url        VARCHAR(500) NOT NULL,
  PRIMARY KEY (id_image),
  KEY ix_vehicle_image_vehicle (id_vehicle),
  CONSTRAINT fk_vehicle_image_vehicle
    FOREIGN KEY (id_vehicle) REFERENCES vehicle(id_vehicle)
    ON DELETE CASCADE ON UPDATE CASCADE
);

-- =========================
-- 4) Clientes + interés
-- =========================
CREATE TABLE client (
  id_client  INT NOT NULL AUTO_INCREMENT,
  dni_nif    VARCHAR(20) NOT NULL,
  full_name  VARCHAR(160) NOT NULL,
  email      VARCHAR(255),
  phone      VARCHAR(30),
  birthdate  DATE,
  address    VARCHAR(220),
  PRIMARY KEY (id_client),
  UNIQUE KEY ux_client_dni (dni_nif)
);

CREATE TABLE client_vehicle_interest (
  id_interest   INT NOT NULL AUTO_INCREMENT,
  id_client     INT NOT NULL,
  id_vehicle    INT NOT NULL,
  interest_date DATE NOT NULL DEFAULT (CURRENT_DATE),
  approx_budget DECIMAL(12,2) NULL,
  notes         TEXT NULL,
  PRIMARY KEY (id_interest),
  UNIQUE KEY ux_interest_client_vehicle (id_client, id_vehicle),
  KEY ix_interest_vehicle (id_vehicle),
  CONSTRAINT fk_interest_client
    FOREIGN KEY (id_client) REFERENCES client(id_client)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_interest_vehicle
    FOREIGN KEY (id_vehicle) REFERENCES vehicle(id_vehicle)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT chk_interest_budget CHECK (approx_budget IS NULL OR approx_budget >= 0)
);

-- =========================
-- 5) Ofertas / ventas
-- =========================
CREATE TABLE payment_method (
  id_payment_method SMALLINT NOT NULL,
  name              VARCHAR(40) NOT NULL,
  PRIMARY KEY (id_payment_method),
  UNIQUE KEY ux_payment_method_name (name)
);

CREATE TABLE offer_status (
  id_offer_status SMALLINT NOT NULL,
  name            VARCHAR(40) NOT NULL,
  PRIMARY KEY (id_offer_status),
  UNIQUE KEY ux_offer_status_name (name)
);

CREATE TABLE offer (
  id_offer            INT NOT NULL AUTO_INCREMENT,
  id_client           INT NOT NULL,
  id_vehicle          INT NOT NULL,
  id_sales_employee   INT NOT NULL,          -- FK a SALES_EMPLOYEE (subtipo)
  id_payment_method   SMALLINT NOT NULL,
  id_offer_status     SMALLINT NOT NULL,
  offer_date          DATE NOT NULL DEFAULT (CURRENT_DATE),
  validity_date       DATE NOT NULL,
  base_price_snapshot DECIMAL(12,2) NOT NULL,
  discount_amount     DECIMAL(12,2) NOT NULL DEFAULT 0,
  final_price         DECIMAL(12,2) NOT NULL,
  details             TEXT NULL,
  PRIMARY KEY (id_offer),
  KEY ix_offer_client_date (id_client, offer_date),
  KEY ix_offer_vehicle_date (id_vehicle, offer_date),
  KEY ix_offer_sales (id_sales_employee),
  CONSTRAINT fk_offer_client
    FOREIGN KEY (id_client) REFERENCES client(id_client)
    ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT fk_offer_vehicle
    FOREIGN KEY (id_vehicle) REFERENCES vehicle(id_vehicle)
    ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT fk_offer_sales_employee
    FOREIGN KEY (id_sales_employee) REFERENCES sales_employee(id_worker)
    ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT fk_offer_payment_method
    FOREIGN KEY (id_payment_method) REFERENCES payment_method(id_payment_method)
    ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT fk_offer_status
    FOREIGN KEY (id_offer_status) REFERENCES offer_status(id_offer_status)
    ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT chk_offer_prices CHECK (
    base_price_snapshot >= 0 AND discount_amount >= 0 AND final_price >= 0),
  CONSTRAINT chk_offer_discount CHECK (
	discount_amount <= base_price_snapshot),
  CONSTRAINT chk_offer_validity CHECK (
	validity_date >= offer_date)
);

CREATE TABLE sale (
  id_sale              INT NOT NULL AUTO_INCREMENT,
  id_offer             INT NOT NULL,
  sale_ts              DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  final_price_snapshot DECIMAL(12,2) NOT NULL,
  PRIMARY KEY (id_sale),
  UNIQUE KEY ux_sale_offer (id_offer),
  CONSTRAINT fk_sale_offer
    FOREIGN KEY (id_offer) REFERENCES offer(id_offer)
    ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT chk_sale_price CHECK (final_price_snapshot >= 0)
);

-- =========================
-- 6) Taller (reparaciones + especialidades + materiales)
-- =========================
CREATE TABLE repair_status (
  id_repair_status SMALLINT NOT NULL,
  name             VARCHAR(40) NOT NULL,
  PRIMARY KEY (id_repair_status),
  UNIQUE KEY ux_repair_status_name (name)
);

CREATE TABLE repair (
  id_repair                INT NOT NULL AUTO_INCREMENT,
  id_dealership            INT NOT NULL,
  id_vehicle               INT NOT NULL,
  id_client                INT NULL,
  created_by_head_mechanic INT NOT NULL,  -- FK a HEAD_MECHANIC (subtipo)
  assigned_mechanic        INT NULL,      -- FK a MECHANIC (subtipo)
  id_repair_status         SMALLINT NOT NULL,
  creation_ts              DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  start_ts                 DATETIME NULL,
  end_ts                   DATETIME NULL,
  estimated_time_hours     DECIMAL(6,2) NOT NULL,
  estimated_cost           DECIMAL(12,2) NOT NULL,
  final_cost               DECIMAL(12,2) NULL,
  work_type                VARCHAR(120) NOT NULL,
  notes                    TEXT NULL,
  PRIMARY KEY (id_repair),
  KEY ix_repair_vehicle_ts (id_vehicle, creation_ts),
  KEY ix_repair_assigned_status (assigned_mechanic, id_repair_status, creation_ts),
  CONSTRAINT fk_repair_dealership
	FOREIGN KEY (id_dealership) REFERENCES dealership(id_dealership)
	ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT fk_repair_vehicle
    FOREIGN KEY (id_vehicle) REFERENCES vehicle(id_vehicle)
    ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT fk_repair_client
    FOREIGN KEY (id_client) REFERENCES client(id_client)
    ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT fk_repair_created_by
    FOREIGN KEY (created_by_head_mechanic) REFERENCES head_mechanic(id_worker)
    ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT fk_repair_assigned_mechanic
    FOREIGN KEY (assigned_mechanic) REFERENCES mechanic(id_worker)
    ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT fk_repair_status
    FOREIGN KEY (id_repair_status) REFERENCES repair_status(id_repair_status)
    ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT chk_repair_numbers CHECK (
    estimated_time_hours >= 0 AND estimated_cost >= 0
    AND (final_cost IS NULL OR final_cost >= 0)
  )
);

-- Especialidades: solo MECHANIC puede tenerlas (ya garantizado por FK al subtipo)
CREATE TABLE mechanic_specialty (
  id_mechanic INT NOT NULL,
  id_category INT NOT NULL,
  PRIMARY KEY (id_mechanic, id_category),
  KEY ix_specialty_category (id_category),
  CONSTRAINT fk_specialty_mechanic
    FOREIGN KEY (id_mechanic) REFERENCES mechanic(id_worker)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_specialty_category
    FOREIGN KEY (id_category) REFERENCES vehicle_category(id_category)
    ON DELETE RESTRICT ON UPDATE CASCADE
);

-- Materiales (3FN): tipo en tabla catálogo + material
CREATE TABLE material_type (
  id_material_type SMALLINT NOT NULL,
  name             VARCHAR(40) NOT NULL,
  PRIMARY KEY (id_material_type),
  UNIQUE KEY ux_material_type_name (name)
);

CREATE TABLE material (
  id_material      INT NOT NULL AUTO_INCREMENT,
  id_material_type SMALLINT NOT NULL,
  name             VARCHAR(160) NOT NULL,
  price_ref        DECIMAL(12,2) NOT NULL,
  active           TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (id_material),
  KEY ix_material_type (id_material_type),
  CONSTRAINT fk_material_type
    FOREIGN KEY (id_material_type) REFERENCES material_type(id_material_type)
    ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT chk_material_price CHECK (price_ref >= 0)
);

-- Lista de materiales por reparación
CREATE TABLE repair_material (
  id_repair          INT NOT NULL,
  id_material        INT NOT NULL,
  quantity           INT NOT NULL,
  unit_price_applied DECIMAL(12,2) NOT NULL,
  PRIMARY KEY (id_repair, id_material),
  KEY ix_rm_material (id_material),
  CONSTRAINT fk_rm_repair
    FOREIGN KEY (id_repair) REFERENCES repair(id_repair)
    ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT fk_rm_material
    FOREIGN KEY (id_material) REFERENCES material(id_material)
    ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT chk_rm_qty CHECK (quantity > 0),
  CONSTRAINT chk_rm_price CHECK (unit_price_applied >= 0)
);


-- TRIGGERS
-- ============================================================
-- DROP old triggers (if exist)
-- ============================================================
DROP TRIGGER IF EXISTS trg_headMechanic_onePerDealership;
DROP TRIGGER IF EXISTS trg_repair_beforeInsert_validate;
DROP TRIGGER IF EXISTS trg_repair_beforeUpdate_validate;
DROP TRIGGER IF EXISTS trg_sale_beforeInsert_validateAndClose;

DELIMITER $$

-- ============================================================
-- 1) Only one head_mechanic per dealership
-- Table: head_mechanic (BEFORE INSERT)
-- ============================================================
CREATE TRIGGER trg_headMechanic_onePerDealership
BEFORE INSERT ON head_mechanic
FOR EACH ROW
trg: BEGIN
  DECLARE v_dealership INT;
  DECLARE v_active TINYINT;

  SELECT w.id_dealership, w.active
    INTO v_dealership, v_active
  FROM worker w
  WHERE w.id_worker = NEW.id_worker;

  IF v_dealership IS NULL THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Worker not found for head_mechanic';
  END IF;

  IF v_active IS NULL OR v_active = 0 THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Worker is not active';
  END IF;

  IF EXISTS (
    SELECT 1
    FROM head_mechanic hm
    JOIN worker w2 ON w2.id_worker = hm.id_worker
    WHERE w2.id_dealership = v_dealership
  ) THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Head mechanic already exists for this dealership';
  END IF;
END$$


-- ============================================================
-- Helpers (inline logic) used in repair triggers:
-- - dealership must match vehicle.dealership
-- - assigned mechanic (if any) must have specialty for vehicle.category
-- - timestamps for status
-- - vehicle.status update for repair lifecycle
-- ============================================================

-- ============================================================
-- 2) REPAIR BEFORE INSERT: validate + timestamps/states
-- Table: repair (BEFORE INSERT)
-- ============================================================
CREATE TRIGGER trg_repair_beforeInsert_validate
BEFORE INSERT ON repair
FOR EACH ROW
trg: BEGIN
  DECLARE v_vehicle_dealership INT;
  DECLARE v_category INT;
  DECLARE v_status_name VARCHAR(40);
  DECLARE v_vehicle_status VARCHAR(20);

  -- 2.1 dealership consistency
  SELECT v.id_dealership, v.id_category, v.status
    INTO v_vehicle_dealership, v_category, v_vehicle_status
  FROM vehicle v
  WHERE v.id_vehicle = NEW.id_vehicle;

  IF v_vehicle_dealership IS NULL THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Vehicle not found for repair';
  END IF;

  IF NEW.id_dealership <> v_vehicle_dealership THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Repair dealership does not match vehicle dealership';
  END IF;

  -- 2.2 status name
  SELECT rs.name INTO v_status_name
  FROM repair_status rs
  WHERE rs.id_repair_status = NEW.id_repair_status;

  IF v_status_name IS NULL THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Repair status not found';
  END IF;

  -- 2.3 assigned mechanic specialty validation (if assigned)
  IF NEW.assigned_mechanic IS NOT NULL THEN
    IF NOT EXISTS (
      SELECT 1
      FROM mechanic_specialty ms
      WHERE ms.id_mechanic = NEW.assigned_mechanic
        AND ms.id_category = v_category
    ) THEN
      SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Assigned mechanic has no specialty for vehicle category';
    END IF;
  END IF;

  -- 2.4 timestamps normalization
  -- Ensure creation_ts exists (default exists, but keep safe)
  IF NEW.creation_ts IS NULL THEN
    SET NEW.creation_ts = NOW();
  END IF;

  IF v_status_name = 'IN_PROGRESS' AND NEW.start_ts IS NULL THEN
    SET NEW.start_ts = NOW();
  END IF;

  IF (v_status_name = 'FINISHED' OR v_status_name = 'CANCELLED') THEN
    IF NEW.start_ts IS NULL THEN
      SET NEW.start_ts = NOW();
    END IF;
    IF NEW.end_ts IS NULL THEN
      SET NEW.end_ts = NOW();
    END IF;
  END IF;

  -- 2.5 vehicle status update for open repairs (avoid touching SOLD)
  IF v_vehicle_status <> 'SOLD' THEN
    IF v_status_name IN ('PENDING','ASSIGNED','IN_PROGRESS') THEN
      UPDATE vehicle SET status = 'IN_REPAIR'
      WHERE id_vehicle = NEW.id_vehicle;
    END IF;

    IF v_status_name IN ('FINISHED','CANCELLED') THEN
      -- If it was in repair, return to stock
      UPDATE vehicle
      SET status = CASE WHEN status = 'IN_REPAIR' THEN 'IN_STOCK' ELSE status END
      WHERE id_vehicle = NEW.id_vehicle;
    END IF;
  END IF;

END$$


-- ============================================================
-- 3) REPAIR BEFORE UPDATE: validate transitions + timestamps/states + specialty
-- Table: repair (BEFORE UPDATE)
-- ============================================================
CREATE TRIGGER trg_repair_beforeUpdate_validate
BEFORE UPDATE ON repair
FOR EACH ROW
trg: BEGIN
  DECLARE v_old_status_name VARCHAR(40);
  DECLARE v_new_status_name VARCHAR(40);
  DECLARE v_vehicle_dealership INT;
  DECLARE v_category INT;
  DECLARE v_vehicle_status VARCHAR(20);

  -- 3.1 if vehicle changes OR dealership changes: keep dealership consistency
  SELECT v.id_dealership, v.id_category, v.status
    INTO v_vehicle_dealership, v_category, v_vehicle_status
  FROM vehicle v
  WHERE v.id_vehicle = NEW.id_vehicle;

  IF v_vehicle_dealership IS NULL THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Vehicle not found for repair';
  END IF;

  IF NEW.id_dealership <> v_vehicle_dealership THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Repair dealership does not match vehicle dealership';
  END IF;

  -- 3.2 resolve status names
  SELECT rs.name INTO v_old_status_name
  FROM repair_status rs
  WHERE rs.id_repair_status = OLD.id_repair_status;

  SELECT rs.name INTO v_new_status_name
  FROM repair_status rs
  WHERE rs.id_repair_status = NEW.id_repair_status;

  IF v_new_status_name IS NULL THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Repair status not found';
  END IF;

  -- 3.3 validate allowed transitions (only if status changed)
  IF OLD.id_repair_status <> NEW.id_repair_status THEN
    IF NOT (
      (v_old_status_name = 'PENDING'     AND v_new_status_name IN ('ASSIGNED','CANCELLED')) OR
      (v_old_status_name = 'ASSIGNED'    AND v_new_status_name IN ('IN_PROGRESS','CANCELLED')) OR
      (v_old_status_name = 'IN_PROGRESS' AND v_new_status_name IN ('FINISHED','CANCELLED')) OR
      (v_old_status_name = 'FINISHED'    AND v_new_status_name = 'FINISHED') OR
      (v_old_status_name = 'CANCELLED'   AND v_new_status_name = 'CANCELLED')
    ) THEN
      SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Invalid repair status transition';
    END IF;
  END IF;

  -- 3.4 assigned mechanic specialty validation (if assigned AND changed or vehicle changed)
  IF NEW.assigned_mechanic IS NOT NULL THEN
    IF (NOT (OLD.assigned_mechanic <=> NEW.assigned_mechanic)) OR (OLD.id_vehicle <> NEW.id_vehicle) THEN
      IF NOT EXISTS (
        SELECT 1
        FROM mechanic_specialty ms
        WHERE ms.id_mechanic = NEW.assigned_mechanic
          AND ms.id_category = v_category
      ) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Assigned mechanic has no specialty for vehicle category';
      END IF;
    END IF;
  END IF;

  -- 3.5 timestamps normalization
  IF NEW.creation_ts IS NULL THEN
    SET NEW.creation_ts = OLD.creation_ts;
  END IF;

  IF v_new_status_name = 'IN_PROGRESS' AND NEW.start_ts IS NULL THEN
    SET NEW.start_ts = NOW();
  END IF;

  IF (v_new_status_name = 'FINISHED' OR v_new_status_name = 'CANCELLED') THEN
    IF NEW.start_ts IS NULL THEN
      SET NEW.start_ts = COALESCE(OLD.start_ts, NOW());
    END IF;
    IF NEW.end_ts IS NULL THEN
      SET NEW.end_ts = NOW();
    END IF;
  END IF;

  -- 3.6 vehicle status update for repair lifecycle (avoid touching SOLD)
  IF v_vehicle_status <> 'SOLD' THEN
    IF v_new_status_name IN ('PENDING','ASSIGNED','IN_PROGRESS') THEN
      UPDATE vehicle SET status = 'IN_REPAIR'
      WHERE id_vehicle = NEW.id_vehicle;
    END IF;

    IF v_new_status_name IN ('FINISHED','CANCELLED') THEN
      UPDATE vehicle
      SET status = CASE WHEN status = 'IN_REPAIR' THEN 'IN_STOCK' ELSE status END
      WHERE id_vehicle = NEW.id_vehicle;
    END IF;
  END IF;

END$$


-- ============================================================
-- 4) SALE BEFORE INSERT: validate offer ACTIVE + not expired + close states
-- Table: sale (BEFORE INSERT)
-- ============================================================
CREATE TRIGGER trg_sale_beforeInsert_validateAndClose
BEFORE INSERT ON sale
FOR EACH ROW
trg: BEGIN
  DECLARE v_validity DATE;
  DECLARE v_offer_status_name VARCHAR(40);
  DECLARE v_accept_status_id SMALLINT;
  DECLARE v_vehicle_id INT;
  DECLARE v_offer_final DECIMAL(12,2);
  DECLARE v_vehicle_status VARCHAR(20);

  -- Load offer data + status name
  SELECT o.validity_date, os.name, o.id_vehicle, o.final_price
    INTO v_validity, v_offer_status_name, v_vehicle_id, v_offer_final
  FROM offer o
  JOIN offer_status os ON os.id_offer_status = o.id_offer_status
  WHERE o.id_offer = NEW.id_offer;

  IF v_validity IS NULL THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Offer not found';
  END IF;

  IF v_offer_status_name <> 'ACTIVE' THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Offer is not ACTIVE';
  END IF;

  IF v_validity < CURRENT_DATE THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Offer is expired';
  END IF;

  -- Prevent selling an already SOLD vehicle
  SELECT v.status INTO v_vehicle_status
  FROM vehicle v
  WHERE v.id_vehicle = v_vehicle_id;

  IF v_vehicle_status = 'SOLD' THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Vehicle already SOLD';
  END IF;

  -- If snapshot not provided, take it from offer.final_price
  IF NEW.final_price_snapshot IS NULL THEN
    SET NEW.final_price_snapshot = v_offer_final;
  END IF;

  -- Close offer -> ACCEPTED
  SELECT id_offer_status INTO v_accept_status_id
  FROM offer_status
  WHERE name = 'ACCEPTED';

  IF v_accept_status_id IS NULL THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Offer status ACCEPTED not found';
  END IF;

  UPDATE offer
  SET id_offer_status = v_accept_status_id
  WHERE id_offer = NEW.id_offer;

  -- Mark vehicle as SOLD
  UPDATE vehicle
  SET status = 'SOLD'
  WHERE id_vehicle = v_vehicle_id;

END$$

DELIMITER ;
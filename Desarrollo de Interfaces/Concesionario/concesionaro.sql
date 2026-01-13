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
    ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE mechanic (
  id_worker INT NOT NULL,
  PRIMARY KEY (id_worker),
  CONSTRAINT fk_mechanic_worker
    FOREIGN KEY (id_worker) REFERENCES worker(id_worker)
    ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE head_mechanic (
  id_worker INT NOT NULL,
  PRIMARY KEY (id_worker),
  CONSTRAINT fk_head_mechanic_worker
	FOREIGN KEY (id_worker) REFERENCES mechanic(id_worker)
    ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE manager (
  id_worker INT NOT NULL,
  PRIMARY KEY (id_worker),
  CONSTRAINT fk_manager_worker
    FOREIGN KEY (id_worker) REFERENCES worker(id_worker)
    ON DELETE RESTRICT ON UPDATE CASCADE
);

-- =========================
-- 3) Vehículos
-- =========================
CREATE TABLE vehicle_category (
  id_category SMALLINT NOT NULL,
  name        VARCHAR(30) NOT NULL,
  PRIMARY KEY (id_category),
  UNIQUE KEY ux_vehicle_category_name (name)
);
INSERT INTO vehicle_category (id_category, name) VALUES
(1, 'CAR'),(2, 'MOTORCYCLE'),(3, 'MOPED');

CREATE TABLE vehicle_status (
  id_vehicle_status SMALLINT NOT NULL,
  name              VARCHAR(30) NOT NULL,
  PRIMARY KEY (id_vehicle_status),
  UNIQUE KEY ux_vehicle_status_name (name)
);
INSERT INTO vehicle_status (id_vehicle_status, name) VALUES
(1, 'IN_STOCK'), (2, 'SOLD'), (3, 'IN_REPAIR');

CREATE TABLE vehicle (
  id_vehicle     INT NOT NULL AUTO_INCREMENT,
  id_dealership  INT NOT NULL,
  id_category    INT NOT NULL,
  id_vehicle_status SMALLINT NOT NULL DEFAULT 1,
  vin            VARCHAR(25),
  license_plate  VARCHAR(20),
  brand          VARCHAR(80) NOT NULL,
  model          VARCHAR(80) NOT NULL,
  year           SMALLINT NOT NULL,
  km             INT NOT NULL,
  fuel           VARCHAR(30) NOT NULL,
  base_price     DECIMAL(12,2) NOT NULL,
  arrival_date   DATE NOT NULL,
  PRIMARY KEY (id_vehicle),
  UNIQUE KEY ux_vehicle_vin (vin),
  UNIQUE KEY ux_vehicle_plate (license_plate),
  KEY ix_vehicle_dealership_arrival (id_dealership, arrival_date),
  KEY ix_vehicle_category (id_category),
  KEY ix_vehicle_status (id_vehicle_status),
  CONSTRAINT fk_vehicle_dealership
    FOREIGN KEY (id_dealership) REFERENCES dealership(id_dealership)
    ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT fk_vehicle_category
    FOREIGN KEY (id_category) REFERENCES vehicle_category(id_category)
    ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT fk_vehicle_status
    FOREIGN KEY (id_vehicle_status) REFERENCES vehicle_status(id_vehicle_status)
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
  phone_number      VARCHAR(30),
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
  id_payment_method SMALLINT ,
  name              VARCHAR(40) NOT NULL,
  PRIMARY KEY (id_payment_method),
  UNIQUE KEY ux_payment_method_name (name)
);
INSERT INTO payment_method (id_payment_method, name) VALUES
(1, 'CASH'),(2, 'CARD'),(3, 'TRANSFER'),(4, 'FINANCING');


CREATE TABLE offer_status (
  id_offer_status SMALLINT NOT NULL,
  name            VARCHAR(40) NOT NULL,
  PRIMARY KEY (id_offer_status),
  UNIQUE KEY ux_offer_status_name (name)
);
INSERT INTO offer_status (id_offer_status, name) VALUES
(1,'ACTIVE'),(2,'ACCEPTED'),(3,'REJECTED');


CREATE TABLE offer (
  id_offer            INT NOT NULL AUTO_INCREMENT,
  id_client           INT NOT NULL,
  id_vehicle          INT NOT NULL,
  id_sales_employee   INT NOT NULL,
  id_payment_method   SMALLINT NOT NULL,
  id_offer_status     SMALLINT NOT NULL,
  offer_date          DATE NOT NULL DEFAULT (CURRENT_DATE),
  validity_date       DATE NOT NULL,
  base_price_snapshot DECIMAL(12,2) NOT NULL,
  discount_amount     DECIMAL(12,2) NOT NULL DEFAULT 0,
  final_price         DECIMAL(12,2) NOT NULL,
  details             TEXT NULL,
  PRIMARY KEY (id_offer),
  KEY ix_offer_sales (id_sales_employee),
  KEY ix_offer_vehicle_status (id_vehicle, id_offer_status),
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
  id_vehicle           INT NOT NULL,
  sale_ts              DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  final_price_snapshot DECIMAL(12,2) NOT NULL,
  PRIMARY KEY (id_sale),
  UNIQUE KEY ux_sale_offer (id_offer),
  UNIQUE KEY ux_sale_vehicle (id_vehicle),
  CONSTRAINT fk_sale_offer
    FOREIGN KEY (id_offer) REFERENCES offer(id_offer)
    ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT fk_sale_vehicle
    FOREIGN KEY (id_vehicle) REFERENCES vehicle(id_vehicle)
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
INSERT INTO repair_status (id_repair_status, name) VALUES
(1,'PENDING'),(2,'ASSIGNED'),(3,'IN_PROGRESS'),(4,'FINISHED'),(5,'CANCELLED');

CREATE TABLE repair (
  id_repair                INT NOT NULL AUTO_INCREMENT,
  id_dealership            INT NOT NULL,
  id_vehicle               INT NOT NULL,
  id_client                INT NULL,
  created_by_head_mechanic INT NOT NULL,
  assigned_mechanic        INT NULL,
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
  ),
  CONSTRAINT chk_repair_ts CHECK (
	(start_ts IS NULL OR start_ts >= creation_ts) AND
	(end_ts IS NULL OR start_ts IS NULL OR end_ts >= start_ts))
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


-- PROCEDURES
-- ============================================================
-- DROP old procedures (if exist)
-- ============================================================
DROP PROCEDURE IF EXISTS validate_vehicle_status;
DROP PROCEDURE IF EXISTS validate_offer_status;
DROP PROCEDURE IF EXISTS validate_mechanic_specialty;
DROP PROCEDURE IF EXISTS validate_open_repairs;
DROP PROCEDURE IF EXISTS validate_repair_status_transition;
DROP PROCEDURE IF EXISTS get_vehicle_info;
DROP PROCEDURE IF EXISTS validate_repair_common;
DROP PROCEDURE IF EXISTS update_vehicle_status_for_repair;
DROP PROCEDURE IF EXISTS validate_offer_common;

DELIMITER $$

-- ============================================================
-- 1. Obtener información del vehículo (una sola consulta)
-- ============================================================
CREATE PROCEDURE get_vehicle_info(
    IN p_vehicle_id INT,
    OUT p_dealership_id INT,
    OUT p_category_id INT,
    OUT p_status_id SMALLINT,
    OUT p_status_name VARCHAR(30)
)
BEGIN
    SELECT v.id_dealership, v.id_category, v.id_vehicle_status, vs.name
    INTO p_dealership_id, p_category_id, p_status_id, p_status_name
    FROM vehicle v
    JOIN vehicle_status vs ON vs.id_vehicle_status = v.id_vehicle_status
    WHERE v.id_vehicle = p_vehicle_id;
    
    IF p_dealership_id IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Vehicle not found';
    END IF;
END$$

-- ============================================================
-- 2. Validar estado de vehículo (simplificado)
-- ============================================================
CREATE PROCEDURE validate_vehicle_status(
    IN p_vehicle_status VARCHAR(30), 
    IN p_required_status VARCHAR(30)
)
BEGIN
    DECLARE v_msg TEXT;

    IF p_vehicle_status <> p_required_status THEN
        SET v_msg = CONCAT('Vehicle must be ',p_required_status,', currently: ',p_vehicle_status);

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = v_msg;
    END IF;
END$$


-- ============================================================
-- 3. Validar especialidad del mecánico
-- ============================================================
CREATE PROCEDURE validate_mechanic_specialty(
    IN p_mechanic_id INT, 
    IN p_vehicle_category INT
)
BEGIN
    IF NOT EXISTS (
        SELECT 1 
        FROM mechanic_specialty
        WHERE id_mechanic = p_mechanic_id 
        AND id_category = p_vehicle_category
    ) THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Mechanic does not have required specialty for this vehicle category';
    END IF;
END$$

-- ============================================================
-- 4. Verificar reparaciones abiertas
-- ============================================================
CREATE PROCEDURE validate_open_repairs(
    IN p_vehicle_id INT
)
BEGIN
    IF EXISTS (
        SELECT 1
        FROM repair r
        JOIN repair_status rs ON rs.id_repair_status = r.id_repair_status
        WHERE r.id_vehicle = p_vehicle_id 
        AND rs.name IN ('PENDING', 'ASSIGNED', 'IN_PROGRESS')
    ) THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Vehicle has open repairs';
    END IF;
END$$

-- ============================================================
-- 5. Validar transición de estado de reparación
-- ============================================================
CREATE PROCEDURE validate_repair_status_transition(
    IN p_old_status VARCHAR(40), 
    IN p_new_status VARCHAR(40)
)
BEGIN
    DECLARE v_msg TEXT;
    -- Validar transiciones permitidas
    IF NOT (
        (p_old_status = 'PENDING' AND p_new_status IN ('ASSIGNED','CANCELLED')) OR
        (p_old_status = 'ASSIGNED' AND p_new_status IN ('IN_PROGRESS','CANCELLED')) OR
        (p_old_status = 'IN_PROGRESS' AND p_new_status IN ('FINISHED','CANCELLED')) OR
        (p_old_status = 'FINISHED' AND p_new_status = 'FINISHED') OR
        (p_old_status = 'CANCELLED' AND p_new_status = 'CANCELLED')
    ) THEN
        SET v_msg = CONCAT('Invalid status transition: ', p_old_status, ' -> ', p_new_status);
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = v_msg;
    END IF;
END$$

-- ============================================================
-- 6. Actualizar estado del vehículo según reparación
-- ============================================================
CREATE PROCEDURE update_vehicle_status_for_repair(
    IN p_vehicle_id INT,
    IN p_repair_status_name VARCHAR(40),
    IN p_vehicle_current_status_name VARCHAR(30)
)
BEGIN
    DECLARE v_in_repair_id SMALLINT;
    DECLARE v_in_stock_id SMALLINT;
    
    -- Obtener IDs de estados
    SELECT id_vehicle_status INTO v_in_repair_id
    FROM vehicle_status WHERE name = 'IN_REPAIR';
    
    SELECT id_vehicle_status INTO v_in_stock_id
    FROM vehicle_status WHERE name = 'IN_STOCK';
    
    -- Solo actualizar si no está vendido
    IF p_vehicle_current_status_name <> 'SOLD' THEN
        IF p_repair_status_name IN ('PENDING', 'ASSIGNED', 'IN_PROGRESS') THEN
            UPDATE vehicle 
            SET id_vehicle_status = v_in_repair_id 
            WHERE id_vehicle = p_vehicle_id;
        ELSEIF p_repair_status_name IN ('FINISHED', 'CANCELLED') THEN
            UPDATE vehicle 
            SET id_vehicle_status = v_in_stock_id 
            WHERE id_vehicle = p_vehicle_id;
        END IF;
    END IF;
END$$

-- ============================================================
-- 7. Validaciones comunes para reparaciones (INSERT y UPDATE)
-- ============================================================
CREATE PROCEDURE validate_repair_common(
    IN p_vehicle_id INT,
    IN p_assigned_mechanic INT,
    IN p_repair_status_id SMALLINT,
    IN p_is_new_repair BOOLEAN,
    OUT p_vehicle_dealership INT,
    OUT p_vehicle_category INT,
    OUT p_vehicle_status VARCHAR(30),
    OUT p_status_name VARCHAR(40)
)
BEGIN
    DECLARE v_vehicle_status_id SMALLINT;
    DECLARE v_vehicle_status_name VARCHAR(30);
    
    -- Obtener info del vehículo
    CALL get_vehicle_info(
        p_vehicle_id, 
        p_vehicle_dealership, 
        p_vehicle_category, 
        v_vehicle_status_id,
        v_vehicle_status_name
    );
    
    -- Asignar al OUT parameter
    SET p_vehicle_status = v_vehicle_status_name;
    
    -- Obtener nombre del estado de reparación
    SELECT name INTO p_status_name
    FROM repair_status
    WHERE id_repair_status = p_repair_status_id;
    
    IF p_status_name IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Invalid repair status';
    END IF;
    
    -- Si es una nueva reparación, validar que el vehículo esté IN_STOCK
    IF p_is_new_repair THEN
        CALL validate_vehicle_status(v_vehicle_status_name, 'IN_STOCK');
    END IF;
    
    -- Validar especialidad del mecánico si está asignado
    IF p_assigned_mechanic IS NOT NULL THEN
        CALL validate_mechanic_specialty(p_assigned_mechanic, p_vehicle_category);
    END IF;
    
    -- Validar que no haya reparaciones abiertas si se está creando una nueva
    IF p_is_new_repair AND p_status_name IN ('PENDING', 'ASSIGNED', 'IN_PROGRESS') THEN
        CALL validate_open_repairs(p_vehicle_id);
    END IF;
END$$
-- ============================================================
-- 8. Validar estado de oferta (consolidado)
-- ============================================================
CREATE PROCEDURE validate_offer_status(
    IN p_offer_id INT,
    OUT p_vehicle_id INT,
    OUT p_offer_status_name VARCHAR(40)
)
BEGIN
    DECLARE v_validity DATE;
	DECLARE v_msg TEXT;

    -- Obtener datos de la oferta
    SELECT o.validity_date, os.name, o.id_vehicle
    INTO v_validity, p_offer_status_name, p_vehicle_id
    FROM offer o
    JOIN offer_status os ON os.id_offer_status = o.id_offer_status
    WHERE o.id_offer = p_offer_id
    FOR UPDATE;
    
    IF p_vehicle_id IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Offer not found';
    END IF;
    
    IF v_validity < CURRENT_DATE THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Offer has expired';
    END IF;
    
    IF p_offer_status_name <> 'ACTIVE' THEN
        SET v_msg = CONCAT('Offer is not ACTIVE, current status: ', p_offer_status_name);
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = v_msg;
    END IF;
END$$

-- ============================================================
-- 9. Validaciones comunes para ofertas (INSERT y UPDATE)
-- ============================================================
CREATE PROCEDURE validate_offer_common(
    IN p_vehicle_id INT,
    IN p_offer_status_id SMALLINT
)
BEGIN
    DECLARE v_status_name VARCHAR(40);
    DECLARE v_vehicle_status_name VARCHAR(30);
    
    -- Obtener nombre del estado de oferta
    SELECT name INTO v_status_name
    FROM offer_status
    WHERE id_offer_status = p_offer_status_id;
    
    -- Solo validar si la oferta está o va a estar ACTIVE
    IF v_status_name = 'ACTIVE' THEN
        -- Obtener estado del vehículo
        SELECT vs.name INTO v_vehicle_status_name
        FROM vehicle v
        JOIN vehicle_status vs ON vs.id_vehicle_status = v.id_vehicle_status
        WHERE v.id_vehicle = p_vehicle_id;
        
        IF v_vehicle_status_name IS NULL THEN
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Vehicle not found';
        END IF;
        
        -- Validar que el vehículo esté IN_STOCK
        CALL validate_vehicle_status(v_vehicle_status_name, 'IN_STOCK');
        
        -- Validar que no haya reparaciones abiertas
        CALL validate_open_repairs(p_vehicle_id);
    END IF;
END$$

DELIMITER ;

-- ============================================================
-- TRIGGERS OPTIMIZADOS
-- ============================================================

DROP TRIGGER IF EXISTS trg_headMechanic_onePerDealership;
DROP TRIGGER IF EXISTS trg_repair_beforeInsert_validate;
DROP TRIGGER IF EXISTS trg_repair_afterInsert_updateVehicle;
DROP TRIGGER IF EXISTS trg_repair_beforeUpdate_validate;
DROP TRIGGER IF EXISTS trg_repair_afterUpdate_updateVehicle;

DELIMITER $$

-- ============================================================
-- 1. Solo un head_mechanic activo por concesionario
-- ============================================================
CREATE TRIGGER trg_headMechanic_onePerDealership
BEFORE INSERT ON head_mechanic
FOR EACH ROW
BEGIN
    DECLARE v_dealership INT;
    DECLARE v_active TINYINT;
    
    -- Obtener dealership y estado activo del worker
    SELECT w.id_dealership, w.active
    INTO v_dealership, v_active
    FROM worker w
    WHERE w.id_worker = NEW.id_worker;
    
    IF v_dealership IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Worker not found';
    END IF;
    
    IF v_active = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Worker is not active';
    END IF;
    
    -- Verificar si ya existe un head_mechanic activo en ese dealership
    IF EXISTS (
        SELECT 1
        FROM head_mechanic hm
        JOIN worker w ON w.id_worker = hm.id_worker
        WHERE w.id_dealership = v_dealership
        AND w.active = 1
    ) THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Active head mechanic already exists for this dealership';
    END IF;
END$$

-- ============================================================
-- 2. REPAIR BEFORE INSERT (optimizado)
-- ============================================================
CREATE TRIGGER trg_repair_beforeInsert_validate
BEFORE INSERT ON repair
FOR EACH ROW
BEGIN
    DECLARE v_vehicle_dealership INT;
    DECLARE v_vehicle_category INT;
    DECLARE v_vehicle_status VARCHAR(30);
    DECLARE v_status_name VARCHAR(40);
    
    -- Validaciones comunes consolidadas
    CALL validate_repair_common(
        NEW.id_vehicle,
        NEW.assigned_mechanic,
        NEW.id_repair_status,
        TRUE,
        v_vehicle_dealership,
        v_vehicle_category,
        v_vehicle_status,
        v_status_name
    );
    
    -- Validar coherencia de dealership
    IF NEW.id_dealership <> v_vehicle_dealership THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Repair dealership must match vehicle dealership';
    END IF;
    
    -- Establecer timestamp de creación si no existe
    IF NEW.creation_ts IS NULL THEN
        SET NEW.creation_ts = NOW();
    END IF;
    
END$$

CREATE TRIGGER trg_repair_afterInsert_updateVehicle
AFTER INSERT ON repair
FOR EACH ROW
BEGIN
    DECLARE v_status_name VARCHAR(40);
    DECLARE v_vehicle_status_id SMALLINT;
    
    SELECT name INTO v_status_name
    FROM repair_status WHERE id_repair_status = NEW.id_repair_status;
    
    SELECT id_vehicle_status INTO v_vehicle_status_id
    FROM vehicle WHERE id_vehicle = NEW.id_vehicle;
    
    -- Actualizar solo si no está vendido
    IF v_vehicle_status_id != (SELECT id_vehicle_status FROM vehicle_status WHERE name = 'SOLD') THEN
        IF v_status_name IN ('PENDING', 'ASSIGNED', 'IN_PROGRESS') THEN
            UPDATE vehicle SET id_vehicle_status = (
                SELECT id_vehicle_status FROM vehicle_status WHERE name = 'IN_REPAIR'
            ) WHERE id_vehicle = NEW.id_vehicle;
        END IF;
    END IF;
END$$

-- ============================================================
-- 3. REPAIR BEFORE UPDATE (optimizado)
-- ============================================================
CREATE TRIGGER trg_repair_beforeUpdate_validate
BEFORE UPDATE ON repair
FOR EACH ROW
BEGIN
    DECLARE v_vehicle_dealership INT;
    DECLARE v_vehicle_category INT;
    DECLARE v_vehicle_status VARCHAR(30);
    DECLARE v_old_status_name VARCHAR(40);
    DECLARE v_new_status_name VARCHAR(40);
    
    -- Obtener nombres de estados
    SELECT rs_old.name, rs_new.name
    INTO v_old_status_name, v_new_status_name
    FROM repair_status rs_old, repair_status rs_new
    WHERE rs_old.id_repair_status = OLD.id_repair_status
    AND rs_new.id_repair_status = NEW.id_repair_status;
    
    -- Validar transición de estado
    CALL validate_repair_status_transition(v_old_status_name, v_new_status_name);
    
    -- Validaciones comunes consolidadas
    CALL validate_repair_common(
        NEW.id_vehicle,
        NEW.assigned_mechanic,
        NEW.id_repair_status,
        FALSE, -- no es nueva reparación
        v_vehicle_dealership,
        v_vehicle_category,
        v_vehicle_status,
        v_new_status_name
    );
    
    -- Preservar timestamp de creación
    IF NEW.creation_ts IS NULL THEN
        SET NEW.creation_ts = OLD.creation_ts;
    END IF;
    
    -- Establecer timestamps automáticos según transiciones
    IF v_new_status_name = 'IN_PROGRESS' AND NEW.start_ts IS NULL THEN
        SET NEW.start_ts = NOW();
    END IF;
    
    IF v_new_status_name IN ('FINISHED', 'CANCELLED') THEN
        IF NEW.start_ts IS NULL THEN
            SET NEW.start_ts = COALESCE(OLD.start_ts, NOW());
        END IF;
        IF NEW.end_ts IS NULL THEN
            SET NEW.end_ts = NOW();
        END IF;
    END IF;
END$$

CREATE TRIGGER trg_repair_afterUpdate_updateVehicle
AFTER UPDATE ON repair
FOR EACH ROW
BEGIN
    DECLARE v_status_name VARCHAR(40);
    DECLARE v_vehicle_status_id SMALLINT;
    DECLARE v_in_repair_id SMALLINT;
    DECLARE v_in_stock_id SMALLINT;
    DECLARE v_sold_id SMALLINT;
    
    -- Obtener nombre del nuevo estado
    SELECT name INTO v_status_name
    FROM repair_status WHERE id_repair_status = NEW.id_repair_status;
    
    -- Obtener estado actual del vehículo
    SELECT id_vehicle_status INTO v_vehicle_status_id
    FROM vehicle WHERE id_vehicle = NEW.id_vehicle;
    
    -- Obtener IDs de estados
    SELECT id_vehicle_status INTO v_in_repair_id
    FROM vehicle_status WHERE name = 'IN_REPAIR';
    
    SELECT id_vehicle_status INTO v_in_stock_id
    FROM vehicle_status WHERE name = 'IN_STOCK';
    
    SELECT id_vehicle_status INTO v_sold_id
    FROM vehicle_status WHERE name = 'SOLD';
    
    -- Actualizar solo si no está vendido
    IF v_vehicle_status_id != v_sold_id THEN
        IF v_status_name IN ('PENDING', 'ASSIGNED', 'IN_PROGRESS') THEN
            UPDATE vehicle 
            SET id_vehicle_status = v_in_repair_id
            WHERE id_vehicle = NEW.id_vehicle;
        ELSEIF v_status_name IN ('FINISHED', 'CANCELLED') THEN
            UPDATE vehicle 
            SET id_vehicle_status = v_in_stock_id
            WHERE id_vehicle = NEW.id_vehicle;
        END IF;
    END IF;
END$$

DELIMITER ;
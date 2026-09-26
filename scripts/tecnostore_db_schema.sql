DROP DATABASE IF EXISTS tecnostore_db;
CREATE DATABASE tecnostore_db;

USE tecnostore_db;

CREATE TABLE celulares (
    id                INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    marca             VARCHAR(50)  NOT NULL,
    modelo            VARCHAR(50)  NOT NULL,
    sistema_operativo ENUM('ANDROID', 'IOS', 'HARMONYOS') NOT NULL,
    gama              ENUM('ALTA', 'MEDIA', 'BAJA') NOT NULL,
    precio            DECIMAL(10, 2) NOT NULL CHECK (precio > 0),
    stock             INT NOT NULL DEFAULT 0 CHECK (stock >= 0)
);

-- Permite marcas distintas con el mismo nombre de modelo
ALTER TABLE celulares
    ADD UNIQUE celulares_marca_modelo_unique (marca, modelo);


CREATE TABLE clientes (
    id              INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nombre          VARCHAR(100) NOT NULL,
    identificacion  VARCHAR(20)  NOT NULL,
    correo          VARCHAR(100) NULL,
    telefono        VARCHAR(20)  NULL
);

ALTER TABLE clientes ADD UNIQUE clientes_identificacion_unique (identificacion);
-- Sin UNIQUE en correo ni telefono: varios clientes pueden compartir uno (ej. familiares sin correo propio)


CREATE TABLE ventas (
    id          INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_cliente  INT UNSIGNED NOT NULL,
    fecha       DATE         NOT NULL,
    total       DECIMAL(10, 2) NOT NULL
);

ALTER TABLE ventas
    ADD CONSTRAINT ventas_id_cliente_foreign
    FOREIGN KEY (id_cliente) REFERENCES clientes (id);


CREATE TABLE detalle_ventas (
    id         INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_venta   INT UNSIGNED NOT NULL,
    id_celular INT UNSIGNED NOT NULL,
    cantidad   INT NOT NULL CHECK (cantidad > 0),
    subtotal   DECIMAL(10, 2) NOT NULL
);

ALTER TABLE detalle_ventas
    ADD CONSTRAINT detalle_ventas_id_venta_foreign
    FOREIGN KEY (id_venta)   REFERENCES ventas    (id) ON DELETE CASCADE;

ALTER TABLE detalle_ventas
    ADD CONSTRAINT detalle_ventas_id_celular_foreign
    FOREIGN KEY (id_celular) REFERENCES celulares (id);


-- Requerida por ReporteDAO.obtenerClientesConCreditoPendiente()
CREATE TABLE creditos (
    id               INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_cliente       INT UNSIGNED NOT NULL,
    id_venta         INT UNSIGNED NOT NULL,
    saldo_pendiente  DECIMAL(10, 2) NOT NULL CHECK (saldo_pendiente >= 0)
);

ALTER TABLE creditos
    ADD CONSTRAINT creditos_id_cliente_foreign
    FOREIGN KEY (id_cliente) REFERENCES clientes (id);

ALTER TABLE creditos
    ADD CONSTRAINT creditos_id_venta_foreign
    FOREIGN KEY (id_venta) REFERENCES ventas (id);

ALTER TABLE creditos
    ADD CONSTRAINT creditos_id_venta_unique
    UNIQUE (id_venta);
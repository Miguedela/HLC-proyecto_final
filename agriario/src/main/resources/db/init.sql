-- Crear la base de datos si no existe
CREATE DATABASE IF NOT EXISTS agriario_db;

USE agriario_db;

-- Crear la tabla tractores
CREATE TABLE IF NOT EXISTS tractores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description TEXT NOT NULL,
    image VARCHAR(255) NOT NULL,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL
);

-- Insertar datos en tractores
INSERT INTO
    tractores (
        id,
        description,
        image,
        name,
        price,
        stock
    )
VALUES (
        2,
        'Descripción del tractor 1',
        'jhondeere1.jpeg',
        'Jhon Deere 1',
        15000,
        3
    ),
    (
        3,
        'Descripción del Jhon Deere 2',
        'jhondeere2.jpeg',
        'Jhon Deere 2',
        40000,
        15
    ),
    (
        4,
        'Descripción del tractor 3',
        'jhonddere3.jpeg',
        'Jhon Deere 3',
        34599,
        12
    )
ON DUPLICATE KEY UPDATE
    description = VALUES(description),
    image = VALUES(image),
    name = VALUES(name),
    price = VALUES(price),
    stock = VALUES(stock);

-- Crear la tabla usuario
CREATE TABLE IF NOT EXISTS usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    perfil ENUM('ADMIN', 'USER') NOT NULL
);

-- Insertar datos en usuario
INSERT INTO
    usuario (
        id,
        nombre,
        contrasena,
        perfil
    )
VALUES (
        1,
        'admin',
        '$2a$10$pAExvwHJY/NnuF.GAaHvVOBs3gHdDBVqkv1xEuE54pSs.y0IsLOSG',
        'ADMIN'
    ),
    (
        2,
        'user',
        '$2a$10$hz.2cRSKyjaIvn76NDYzu.vSbuYAiUOk4h/HK95wn0JaYc1Cl3dQ6',
        'USER'
    )
ON DUPLICATE KEY UPDATE
    nombre = VALUES(nombre),
    contrasena = VALUES(contrasena),
    perfil = VALUES(perfil);
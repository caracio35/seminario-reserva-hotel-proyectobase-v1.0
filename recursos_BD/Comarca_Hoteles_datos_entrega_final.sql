-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-11-2024 a las 21:08:35
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `comarca hoteles`
--

--
-- Volcado de datos para la tabla `calificacion`
--

INSERT INTO `calificacion` (`id`, `reserva_id`, `puntaje`, `descripcion`) VALUES
(1, 1, 5, 'Excelente servicio y comodidad.'),
(2, 2, 4, 'Muy buena experiencia, aunque podría mejorar.'),
(3, 3, 3, 'Agradable, pero con algunos inconvenientes.'),
(4, 4, 5, 'Todo perfecto, superó mis expectativas.'),
(5, 5, 2, 'El servicio podría ser mucho mejor.'),
(6, 6, 4, 'Buena atención y limpieza.'),
(7, 7, 5, 'Excelente, volvería a reservar aquí.'),
(8, 8, 3, 'Satisfactorio, pero con áreas para mejorar.'),
(9, 9, 4, 're fea '),
(10, 9, 1, 'dfdd'),
(11, 8, 1, 'A'),
(12, 1, 1, 'A'),
(13, 1, 1, 'a'),
(14, 1, 4, 'asddas'),
(15, 10, 1, 'asdasdasd'),
(16, 3, 1, 'nose'),
(17, 11, 5, 'asdasdsadasdsad'),
(18, 9, 5, 're linda'),
(19, 9, 2, '4'),
(20, 9, 5, 're linda'),
(21, 9, 5, 're linda'),
(22, 9, 5, 're linda'),
(23, 9, 1, 're linda'),
(24, 9, 1, ''),
(25, 9, 1, ''),
(26, 9, 1, ''),
(27, 9, 5, 'erwf'),
(28, 9, 3, 'a'),
(29, 9, 1, 're linda'),
(30, 9, 1, 're linda'),
(31, 9, 1, 're linda'),
(32, 9, 1, 'ad'),
(33, 9, 1, '4'),
(34, 9, 1, 'a');

--
-- Volcado de datos para la tabla `caracteristicaespecial`
--

INSERT INTO `caracteristicaespecial` (`nombre`, `descripcion`, `precio`) VALUES
('Aire Acondicionado', 'Aire acondicionado en toda la habitación', 70.00),
('Balcon', 'Grande', 100.00),
('Coneccion ethernet cableada ', 'Conexión a internet de alta velocidad', 50.00),
('Jacuzzi', 'Grande', 100.00),
('Pileta', 'Pileta Grande', 100.00),
('Pileta chica', 'pileta chica', 50.00);

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`id`, `reserva_id`, `fecha`, `codigo`, `monto`, `descripcion`) VALUES
(1, 1, '2024-11-01', 1001, 500.00, 'Factura por estadía de 4 noches'),
(2, 2, '2024-11-03', 1002, 300.00, 'Factura por estadía de 5 noches'),
(3, 3, '2024-11-05', 1003, 750.00, 'Factura por estadía de 5 noches con desayuno incluido'),
(4, 4, '2024-11-07', 1004, 900.00, 'Factura por estadía de 6 noches con acceso al spa'),
(5, 5, '2024-11-10', 1005, 200.00, 'Factura por estadía de 3 noches sin servicios adicionales'),
(6, 6, '2024-11-12', 1006, 450.00, 'Factura por estadía de 5 noches con merienda incluida'),
(7, 7, '2024-11-15', 1007, 600.00, 'Factura por estadía de 5 noches con piscina'),
(8, 8, '2024-11-18', 1008, 1000.00, 'Factura por estadía de 7 noches con desayuno y spa'),
(9, 9, '2024-11-20', 1009, 850.00, 'Factura por estadía de 6 noches con gimnasio'),
(10, 10, '2024-11-22', 1010, 400.00, 'Factura por estadía de 4 noches con servicio a la habitación');

--
-- Volcado de datos para la tabla `habitacion`
--

INSERT INTO `habitacion` (`cantidadDeCamas`, `descripcion`, `precio`, `habilitado`, `fechaHastaCuandoEstaDesactivado`, `numHabitaciones`) VALUES
(1, 'dfafd', 122.00, 1, NULL, 1),
(2, 'Habitación renovada', 150.00, 1, NULL, 3),
(4, 'Suite de Lujo', 300.00, 0, '2024-11-08', 4),
(1, 'Habitacion Sencilla', 80.00, 1, NULL, 5),
(2, 'Habitacion Doble', 120.00, 1, NULL, 6),
(2, 'Habitacion Doble Deluxe', 150.00, 1, NULL, 7),
(3, 'Habitacion Triple', 180.00, 1, NULL, 8),
(2, 'Habitacion con vista al mar', 220.00, 1, NULL, 9),
(3, 'Habitacion familiar con balcón', 200.00, 1, NULL, 10),
(1, 'Habitacion Individual Premium', 90.00, 1, NULL, 11),
(4, 'Penthouse con piscina privada', 500.00, 1, NULL, 12),
(7, 'prueva ', 200.00, 1, NULL, 15),
(1, 'ads', 1222.00, 1, NULL, 22),
(3, 'prueva', 1220.00, 1, NULL, 23),
(11, 'dq', 11111.00, 1, NULL, 90),
(2, 'lala', 120.00, 1, NULL, 111),
(2, 'lw', 12.00, 1, NULL, 122),
(1, 'wdwq', 12.00, 1, NULL, 124),
(1, '21312', 1.00, 1, NULL, 1112),
(2, 'sgsd', 123.00, 1, NULL, 13123),
(1, 'adsa', 12.00, 1, NULL, 121212);

--
-- Volcado de datos para la tabla `habitacion_caracteristicaespecial`
--

INSERT INTO `habitacion_caracteristicaespecial` (`numHabitacion`, `nombreCaracteristicaEspecial`) VALUES
(3, 'Aire Acondicionado'),
(3, 'Balcon'),
(3, 'Coneccion ethernet cableada '),
(3, 'Pileta'),
(3, 'Pileta chica'),
(4, 'Balcon'),
(4, 'Jacuzzi'),
(4, 'Pileta'),
(5, 'Aire Acondicionado'),
(5, 'Coneccion ethernet cableada '),
(6, 'Aire Acondicionado'),
(6, 'Pileta chica'),
(7, 'Aire Acondicionado'),
(7, 'Balcon'),
(7, 'Pileta'),
(8, 'Balcon'),
(8, 'Coneccion ethernet cableada'),
(8, 'Pileta chica'),
(15, 'Balcon'),
(15, 'Coneccion ethernet cableada '),
(15, 'Jacuzzi'),
(23, 'Jacuzzi'),
(23, 'Pileta'),
(90, 'Aire Acondicionado'),
(90, 'Balcon'),
(90, 'Jacuzzi'),
(90, 'Pileta'),
(111, 'Balcon'),
(111, 'Pileta'),
(1112, 'Aire Acondicionado'),
(1112, 'Balcon'),
(1112, 'Coneccion ethernet cableada '),
(1112, 'Jacuzzi'),
(1112, 'Pileta'),
(13123, 'Aire Acondicionado'),
(13123, 'Balcon'),
(13123, 'Jacuzzi'),
(13123, 'Pileta'),
(13123, 'Pileta chica'),
(121212, 'Pileta chica');

--
-- Volcado de datos para la tabla `reserva`
--

INSERT INTO `reserva` (`id`, `usuario_id`, `fechaDeInicio`, `fechaDeSalida`, `cantidadDePersonas`, `checkIn`, `checkOut`, `fechaDeReserva`, `saldoFavor`, `pagoMinimo`) VALUES
(1, 1, '2024-11-01', '2024-11-05', 2, '2024-11-01', '2024-11-05', '2024-10-25', 100.00, 1),
(2, 2, '2024-11-03', '2024-11-08', 1, '2024-11-03', '2024-11-08', '2024-10-26', 50.00, 0),
(3, 3, '2024-11-05', '2024-11-10', 3, NULL, NULL, '2024-10-27', 0.00, 1),
(4, 4, '2024-11-07', '2024-11-12', 4, '2024-11-07', '2024-11-12', '2024-10-28', 25.00, 0),
(5, 5, '2024-11-10', '2024-11-15', 2, NULL, NULL, '2024-10-29', 0.00, 0),
(6, 6, '2024-11-12', '2024-11-17', 1, '2024-11-12', '2024-11-17', '2024-10-30', 75.00, 1),
(7, 7, '2024-11-15', '2024-11-20', 3, '2024-11-15', '2024-11-20', '2024-10-31', 150.00, 1),
(8, 8, '2024-11-18', '2024-11-22', 2, NULL, NULL, '2024-11-01', 0.00, 0),
(9, 1, '2024-11-20', '2024-11-25', 4, '2024-11-20', '2024-11-25', '2024-11-02', 50.00, 0),
(10, 2, '2024-11-22', '2024-11-27', 1, NULL, NULL, '2024-11-03', 0.00, 1),
(11, 3, '2042-11-01', '2020-11-05', 8, NULL, NULL, '2024-10-31', 0.00, 1),
(12, 3, '2042-11-01', '2020-11-05', 8, NULL, NULL, '2024-10-31', 0.00, 1),
(13, 3, '2042-11-01', '2020-11-05', 8, NULL, NULL, '2024-10-31', 0.00, 1),
(14, 2, '2024-11-13', '2024-11-15', 2, NULL, NULL, '2024-11-12', 0.00, 1),
(15, 1, '2024-11-15', '2024-11-17', 1, NULL, NULL, '2024-11-13', 0.00, 0);

--
-- Volcado de datos para la tabla `reserva_habitacion`
--

INSERT INTO `reserva_habitacion` (`reserva_id`, `numHabitacion`) VALUES
(1, 3),
(2, 3),
(3, 4),
(3, 5),
(4, 6),
(5, 7),
(6, 8),
(7, 9),
(8, 10),
(9, 4),
(10, 3),
(14, 5),
(14, 6),
(14, 11),
(14, 122),
(14, 1112),
(15, 7);

--
-- Volcado de datos para la tabla `reserva_servicio`
--

INSERT INTO `reserva_servicio` (`reserva_id`, `servicio_id`) VALUES
(1, 1),
(1, 2),
(2, 3),
(3, 4),
(4, 5),
(5, 1),
(6, 2),
(7, 3),
(8, 4),
(9, 5),
(10, 1),
(11, 1),
(11, 2),
(12, 1),
(12, 2),
(13, 1),
(13, 2),
(14, 1),
(15, 1);

--
-- Volcado de datos para la tabla `servicio`
--

INSERT INTO `servicio` (`id`, `nombre`, `precio`, `descripcion`) VALUES
(1, 'Desayuno', 150.00, 'Desayuno continental con café, jugo y tostadas'),
(2, 'Merienda', 120.00, 'Merienda con té o café, acompañada de medialunas'),
(3, 'Spa', 500.00, 'Acceso completo al spa durante 1 hora'),
(4, 'Gimnasio', 200.00, 'Acceso al gimnasio durante todo el día'),
(5, 'Piscina', 300.00, 'Acceso a la piscina climatizada durante el día');

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombre`, `apellido`, `email`, `usuario`, `contrasena`, `telefono`, `dni`) VALUES
(1, 'Juan', 'Pérez', 'juan.perez@gmail.com', 'juanp', 'claveSegura123', '1112345678', '12345678'),
(2, 'María', 'Gómez', 'maria.gomez@gmail.com', 'mariag', 'claveSegura456', '1123456789', '23456789'),
(3, 'Carlos', 'López', 'carlos.lopez@gmail.com', 'carlosl', 'claveSegura789', '1134567890', '34567890'),
(4, 'Ana', 'Martínez', 'ana.martinez@gmail.com', 'anam', 'claveSegura101', '1145678901', '45678901'),
(5, 'Luis', 'Fernández', 'luis.fernandez@gmail.com', 'luisf', 'claveSegura102', '1156789012', '56789012'),
(6, 'Elena', 'Sánchez', 'elena.sanchez@gmail.com', 'elenas', 'claveSegura103', '1167890123', '67890123'),
(7, 'Pablo', 'Ramírez', 'pablo.ramirez@gmail.com', 'pablor', 'claveSegura104', '1178901234', '78901234'),
(8, 'Admin', 'Principal', 'admin.principal@gmail.com', 'admin', 'claveAdminSegura', '1189012345', '00000001');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

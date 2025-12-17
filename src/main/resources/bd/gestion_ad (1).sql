-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-12-2025 a las 01:45:56
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
-- Base de datos: `gestion_ad`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `abonos`
--

CREATE TABLE `abonos` (
  `id` int(11) NOT NULL,
  `id_detalle_pagos` int(11) NOT NULL,
  `fecha_abono` date NOT NULL,
  `monto_abonado` double NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  `metodo_pago` varchar(15) NOT NULL,
  `num_trans` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `abonos`
--

INSERT INTO `abonos` (`id`, `id_detalle_pagos`, `fecha_abono`, `monto_abonado`, `descripcion`, `metodo_pago`, `num_trans`) VALUES
(4, 13, '2025-12-04', 20, 'aaa', 'Efectivo', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `anos_escolares`
--

CREATE TABLE `anos_escolares` (
  `id` int(11) NOT NULL,
  `periodo_inicio` date NOT NULL,
  `periodo_fin` date NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `anos_escolares`
--

INSERT INTO `anos_escolares` (`id`, `periodo_inicio`, `periodo_fin`, `estado`) VALUES
(1, '2025-09-11', '2026-07-24', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalles_pagos`
--

CREATE TABLE `detalles_pagos` (
  `id` int(11) NOT NULL,
  `id_pago_recibo` int(11) NOT NULL,
  `id_tipo_pago` int(11) NOT NULL,
  `metodo_pago` varchar(15) NOT NULL,
  `num_trans` varchar(30) NOT NULL,
  `id_ano_escolar` int(11) NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  `mes_correspondiente` int(1) DEFAULT NULL,
  `monto_total` double NOT NULL,
  `monto_pagado` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalles_pagos`
--

INSERT INTO `detalles_pagos` (`id`, `id_pago_recibo`, `id_tipo_pago`, `metodo_pago`, `num_trans`, `id_ano_escolar`, `descripcion`, `mes_correspondiente`, `monto_total`, `monto_pagado`) VALUES
(12, 20, 2, 'Tarjeta', '', 1, 'aaa', NULL, 15, 15),
(13, 20, 1, 'Efectivo', '', 1, 'aaa', 1, 45, 20),
(14, 25, 2, 'Tarjeta', '', 1, 'asdasds', NULL, 15, 15),
(16, 29, 2, 'Tarjeta', '', 1, 'jghj', NULL, 15, 15),
(17, 29, 3, 'Tarjeta', '', 1, 'hhg', NULL, 25, 25);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estudiantes`
--

CREATE TABLE `estudiantes` (
  `id` int(11) NOT NULL,
  `cedula_rep` varchar(15) NOT NULL,
  `nombre_1` varchar(30) NOT NULL,
  `nombre_2` varchar(30) NOT NULL,
  `apellido_1` varchar(30) NOT NULL,
  `apellido_2` varchar(30) NOT NULL,
  `fecha_N` date NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `grado` varchar(10) NOT NULL,
  `nivel_academico` tinyint(1) NOT NULL,
  `estado` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `estudiantes`
--

INSERT INTO `estudiantes` (`id`, `cedula_rep`, `nombre_1`, `nombre_2`, `apellido_1`, `apellido_2`, `fecha_N`, `direccion`, `grado`, `nivel_academico`, `estado`) VALUES
(1, '11298695', 'Victoria', 'Sofía', 'Davalillo', 'González', '2010-07-18', 'Av. Perijá, Villa Luna', '8vo', 1, 1),
(2, '12345678', 'asdasdasd', 'asdasdasd', 'asdasd', 'asdad', '2020-06-21', 'asdasdsadadssadasd', '1°', 1, 1),
(4, '13245678', 'sdsadas', 'gfdsf', 'dsfsdf', 'sdfsdf', '2008-06-08', 'dsffsdfsfsdfsdf', '1er año', 0, 1),
(5, '12435678', 'asdasdasdsad', 'sadasdasdsad', 'asdasd', 'asdasdasdasd', '2000-11-14', 'sadasdasdadsadasdasdas', '6°', 1, 1),
(6, '12345678', 'Juan', 'rrrrrrrrrr', 'rrrrrrrrrr', 'rrrrrrrrrr', '2012-07-15', 'rrrrrrrrrrrrrrrrrrrrrrrrrrrrrr', '4°', 1, 1),
(7, '12345678', 'ssssssss', 'ssssssss', 'ssssssss', 'ssssssss', '2009-10-27', 'ssssssssssssssssssssssss', '3er año', 0, 1),
(8, '11298695', 'dddddddd', 'dddddddd', 'dddddddd', 'dddddddd', '2018-06-21', 'dddddddddddddddd', '2do año', 0, 1),
(9, '11298695', 'sadasdsa', 'sadasd', 'asdasd', 'asdasd', '2000-02-12', 'asdasdasdasdasdasd', '3°', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagos_recibos`
--

CREATE TABLE `pagos_recibos` (
  `id` int(11) NOT NULL,
  `id_estudiante` int(11) NOT NULL,
  `monto_total` double NOT NULL,
  `monto_pagado` double NOT NULL,
  `estado` tinyint(1) NOT NULL,
  `fecha_pago` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pagos_recibos`
--

INSERT INTO `pagos_recibos` (`id`, `id_estudiante`, `monto_total`, `monto_pagado`, `estado`, `fecha_pago`) VALUES
(20, 1, 60, 35, 1, '2025-12-04'),
(25, 1, 15, 15, 0, '2025-12-05'),
(29, 1, 40, 40, 0, '2025-12-05');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `representantes`
--

CREATE TABLE `representantes` (
  `cedula` varchar(15) NOT NULL,
  `nombre_1` varchar(30) NOT NULL,
  `nombre_2` varchar(30) NOT NULL,
  `apellido_1` varchar(30) NOT NULL,
  `apellido_2` varchar(30) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `fecha_N` date NOT NULL,
  `direccion` varchar(150) NOT NULL,
  `estado` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `representantes`
--

INSERT INTO `representantes` (`cedula`, `nombre_1`, `nombre_2`, `apellido_1`, `apellido_2`, `telefono`, `fecha_N`, `direccion`, `estado`) VALUES
('11298695', 'Nilfa', 'Josefina', 'González', 'González', '04149646521', '1984-12-09', 'Av. Perijá, Villa Luna', 1),
('12345678', 'Federico', 'asdadasd', 'asdasdasd', 'asdasdsad', '04141234567', '2020-06-21', 'asdsadadsadaadasd', 1),
('12435678', 'asdasd', 'sadasd', 'asdasdsa', 'sadasd', '04121234567', '2000-11-14', 'sadasdasdasdasd', 1),
('13245678', 'asdasdasd', 'asdasds', 'sadas', 'asdasdasd', '04142134567', '2008-06-08', 'sdasdfdfeqrqweqeqwe', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_pagos`
--

CREATE TABLE `tipos_pagos` (
  `id` int(11) NOT NULL,
  `categoria` enum('MENSUALIDAD','INSCRIPCIÓN','CURSO','CUOTA EXTRA') NOT NULL,
  `costo` double NOT NULL,
  `estado` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipos_pagos`
--

INSERT INTO `tipos_pagos` (`id`, `categoria`, `costo`, `estado`) VALUES
(1, 'MENSUALIDAD', 45, 1),
(2, 'INSCRIPCIÓN', 15, 1),
(3, 'CURSO', 25, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `trabajadores`
--

CREATE TABLE `trabajadores` (
  `id` int(11) NOT NULL,
  `cedula` varchar(15) NOT NULL,
  `nombre_1` varchar(30) NOT NULL,
  `nombre_2` varchar(30) NOT NULL,
  `apellido_1` varchar(30) NOT NULL,
  `apellido_2` varchar(30) NOT NULL,
  `telefono` varchar(25) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `fecha_N` date NOT NULL,
  `direccion` varchar(150) NOT NULL,
  `estado` tinyint(1) NOT NULL DEFAULT 1,
  `contrasena` varchar(100) NOT NULL,
  `rol` int(1) NOT NULL DEFAULT 0 COMMENT '0 Desempleado 1 Cajero 2 Profesor 3 Estudios'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `trabajadores`
--

INSERT INTO `trabajadores` (`id`, `cedula`, `nombre_1`, `nombre_2`, `apellido_1`, `apellido_2`, `telefono`, `correo`, `fecha_N`, `direccion`, `estado`, `contrasena`, `rol`) VALUES
(1, '29977347', 'Douglas', 'Ezequiel', 'Ojeda', 'González', '04146136693', 'douglasojeda43@gmail.com', '2003-05-08', 'Via Perijá, Villa Luna T23, Frente al Cementerio La Chinita', 1, '$2a$10$rjBlZC3gCrQWC7SpzTza5e0lgqfUv1uuux37CLeW9vvX4b9p6/aH.', 1),
(2, '12345678', 'Pablo', 'Andrés', 'Ferrero', 'De La Cruz', '04121234567', 'pablocontrol@gmail.com', '2006-10-13', 'El bajo, calle 45, diagonal al Pipo, Casa 24-F', 1, '$2a$10$k3S0UGwAmMYNfAl/HT5ote3Lzi1WKpFpFKQINMqBt3Jen46kRE3WK', 3),
(3, '54312345', 'Rosa', 'Andreína', 'Perez', 'Hernandez', '04124231556', 'rosaprofesor@gmail.com', '1986-02-12', 'Abajo de tu cama', 1, '$2a$10$OUDv4AlkMhzJQXpIGgTL0.OCcF3rExFjW7aPTJ09bO6scSWTcEvJO', 2),
(4, '5678431', 'asdasdadasda', 'asdasdadasda', 'asdasdadasda', 'asdasdadasda', '04146754321', 'prueba@gmail.com', '2000-07-24', 'asdasdadasdaasdasdadasdaasdasdadasda', 1, '$2a$10$XHK5F2dGu4NkaA6pg7czK.TcyXCa3pNpJbGQNNDrKKsAEI6L6VgkW', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `abonos`
--
ALTER TABLE `abonos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_detalle_pagos` (`id_detalle_pagos`);

--
-- Indices de la tabla `anos_escolares`
--
ALTER TABLE `anos_escolares`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `detalles_pagos`
--
ALTER TABLE `detalles_pagos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_pago_recibo` (`id_pago_recibo`),
  ADD KEY `id_tipo_pago` (`id_tipo_pago`),
  ADD KEY `id_ano_escolar` (`id_ano_escolar`);

--
-- Indices de la tabla `estudiantes`
--
ALTER TABLE `estudiantes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `cedula_rep` (`cedula_rep`);

--
-- Indices de la tabla `pagos_recibos`
--
ALTER TABLE `pagos_recibos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_estudiante` (`id_estudiante`);

--
-- Indices de la tabla `representantes`
--
ALTER TABLE `representantes`
  ADD PRIMARY KEY (`cedula`);

--
-- Indices de la tabla `tipos_pagos`
--
ALTER TABLE `tipos_pagos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `trabajadores`
--
ALTER TABLE `trabajadores`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `cedula` (`cedula`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `abonos`
--
ALTER TABLE `abonos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `anos_escolares`
--
ALTER TABLE `anos_escolares`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `detalles_pagos`
--
ALTER TABLE `detalles_pagos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `estudiantes`
--
ALTER TABLE `estudiantes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `pagos_recibos`
--
ALTER TABLE `pagos_recibos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT de la tabla `tipos_pagos`
--
ALTER TABLE `tipos_pagos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `trabajadores`
--
ALTER TABLE `trabajadores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `abonos`
--
ALTER TABLE `abonos`
  ADD CONSTRAINT `abonos_ibfk_1` FOREIGN KEY (`id_detalle_pagos`) REFERENCES `detalles_pagos` (`id`);

--
-- Filtros para la tabla `detalles_pagos`
--
ALTER TABLE `detalles_pagos`
  ADD CONSTRAINT `detalles_pagos_ibfk_1` FOREIGN KEY (`id_pago_recibo`) REFERENCES `pagos_recibos` (`id`),
  ADD CONSTRAINT `detalles_pagos_ibfk_2` FOREIGN KEY (`id_tipo_pago`) REFERENCES `tipos_pagos` (`id`),
  ADD CONSTRAINT `detalles_pagos_ibfk_3` FOREIGN KEY (`id_ano_escolar`) REFERENCES `anos_escolares` (`id`);

--
-- Filtros para la tabla `estudiantes`
--
ALTER TABLE `estudiantes`
  ADD CONSTRAINT `estudiantes_ibfk_1` FOREIGN KEY (`cedula_rep`) REFERENCES `representantes` (`cedula`);

--
-- Filtros para la tabla `pagos_recibos`
--
ALTER TABLE `pagos_recibos`
  ADD CONSTRAINT `pagos_recibos_ibfk_1` FOREIGN KEY (`id_estudiante`) REFERENCES `estudiantes` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

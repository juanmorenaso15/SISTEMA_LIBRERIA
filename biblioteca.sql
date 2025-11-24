-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-11-2025 a las 04:48:59
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `biblioteca`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `autores`
--

CREATE TABLE `autores` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `nacionalidad` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `autores`
--

INSERT INTO `autores` (`id`, `nombre`, `nacionalidad`) VALUES
(7, 'gabriel garcia marquez', 'colombia'),
(8, 'edgar alan poe', 'colombia');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libros`
--

CREATE TABLE `libros` (
  `isbn` varchar(13) NOT NULL,
  `titulo` varchar(200) NOT NULL,
  `autor_id` int(11) DEFAULT NULL,
  `año_publicacion` int(11) DEFAULT NULL,
  `cantidad_total` int(11) NOT NULL,
  `cantidad_disponible` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `libros`
--

INSERT INTO `libros` (`isbn`, `titulo`, `autor_id`, `año_publicacion`, `cantidad_total`, `cantidad_disponible`) VALUES
('1', '100 anos de soledad', 7, 1967, 10, 0),
('2', 'Los crimenes de la calle Morgue', 8, 1990, 20, 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prestamos`
--

CREATE TABLE `prestamos` (
  `id` int(11) NOT NULL,
  `libro_isbn` varchar(13) DEFAULT NULL,
  `socio_id` int(11) DEFAULT NULL,
  `fecha_prestamo` date NOT NULL,
  `fecha_devolucion_prevista` date NOT NULL,
  `fecha_devolucion_real` date DEFAULT NULL,
  `estado` enum('PRESTADO','DEVUELTO') DEFAULT 'PRESTADO'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `prestamos`
--

INSERT INTO `prestamos` (`id`, `libro_isbn`, `socio_id`, `fecha_prestamo`, `fecha_devolucion_prevista`, `fecha_devolucion_real`, `estado`) VALUES
(1, '2', 2, '2025-11-21', '2025-12-05', '2025-11-21', 'DEVUELTO'),
(2, '2', 3, '2025-11-21', '2025-12-05', '2025-11-21', 'DEVUELTO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `socios`
--

CREATE TABLE `socios` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `dni` varchar(10) NOT NULL,
  `telefono` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `socios`
--

INSERT INTO `socios` (`id`, `nombre`, `apellido`, `dni`, `telefono`) VALUES
(2, 'manuel', 'moreno', '456', '456'),
(3, 'juan', 'moreno', '123', '123');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `autores`
--
ALTER TABLE `autores`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `libros`
--
ALTER TABLE `libros`
  ADD PRIMARY KEY (`isbn`),
  ADD KEY `autor_id` (`autor_id`);

--
-- Indices de la tabla `prestamos`
--
ALTER TABLE `prestamos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `libro_isbn` (`libro_isbn`),
  ADD KEY `socio_id` (`socio_id`);

--
-- Indices de la tabla `socios`
--
ALTER TABLE `socios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `dni` (`dni`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `autores`
--
ALTER TABLE `autores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `prestamos`
--
ALTER TABLE `prestamos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `socios`
--
ALTER TABLE `socios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `libros`
--
ALTER TABLE `libros`
  ADD CONSTRAINT `libros_ibfk_1` FOREIGN KEY (`autor_id`) REFERENCES `autores` (`id`);

--
-- Filtros para la tabla `prestamos`
--
ALTER TABLE `prestamos`
  ADD CONSTRAINT `prestamos_ibfk_1` FOREIGN KEY (`libro_isbn`) REFERENCES `libros` (`isbn`),
  ADD CONSTRAINT `prestamos_ibfk_2` FOREIGN KEY (`socio_id`) REFERENCES `socios` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

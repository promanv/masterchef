-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 22-09-2024 a las 18:39:02
-- Versión del servidor: 8.3.0
-- Versión de PHP: 8.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `masterchef`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `receta`
--

DROP TABLE IF EXISTS `receta`;
CREATE TABLE IF NOT EXISTS `receta` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` text NOT NULL,
  `descripcion` text NOT NULL,
  `ingredientes` text NOT NULL,
  `instrucciones` text NOT NULL,
  `tiempo_preparacion` int NOT NULL,
  `dificultad` text NOT NULL,
  `participante` text NOT NULL,
  `votos` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `receta`
--

INSERT INTO `receta` (`id`, `nombre`, `descripcion`, `ingredientes`, `instrucciones`, `tiempo_preparacion`, `dificultad`, `participante`, `votos`) VALUES
(1, 'Porotos con Riendas', 'Ricos porotos a la chilena con spaghetti', 'porotos,cebolla,pimenton,comino,aji color,spaghetti,zapallo camote', 'hacer el pino, cocer los porotos, agregar spaghetti, juntar, revolver, condimentar y listo!', 50, 'media', 'Pablo', 0),
(2, 'Mechada Italiana chilensis', 'Rico sandwich de carne mechada', 'pan amasado grande,tomate,palta,carne mechada,mayonesa,pimienta,sal', 'preparar la carne mechada en olla a presion condimentar a gusto, picar tomates en cuadritos, moler la palta!', 30, 'facil', 'Dennise', 0),
(3, 'Lomo pobre-pobre', 'Arroz blanco con huevos fritos y bistec', 'arroz,huevos,bistec', 'preparar arroz graneado, freir los huevos, freir el bistec, servir al arroz junto al bistec, poner el huevo frito sobre el arroz.', 30, 'facil', 'Javi', 0),
(4, 'Fideos Florencia', 'Spaghetti con salsa bolognesa', 'Spaghetti,tomate,salsa,sal,cebolla,carne molida', 'cocer los fideos por 10 minutos, preparar un pino con la carne molida, cebolla, salsa de tomates, juntar todos y servir con queso rallado', 30, 'facil', 'Florencia', -2),
(5, 'Porotos con Riendas', 'Ricos porotos a la chilena con spaghetti', 'porotos,cebolla,pimenton,comino,aji color,spaghetti,zapallo camote', 'hacer el pino, cocer los porotos, poner spaghetti, juntar, revolver, condimentar y listo!', 50, 'media', 'Pablo', 0),
(6, 'Porotos con Riendas', 'Ricos porotos a la chilena con spaghetti', 'porotos,cebolla,pimenton,comino,aji color,spaghetti,zapallo camote', 'hacer el pino, cocer los porotos, poner spaghetti, juntar, revolver, condimentar y listo!', 50, 'media', 'Pablo', 0);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

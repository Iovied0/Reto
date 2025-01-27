-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3307
-- Tiempo de generación: 27-01-2025 a las 20:02:27
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
-- Base de datos: `reto2_g2_dam1`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actividad`
--

CREATE TABLE `actividad` (
  `nombre` varchar(255) NOT NULL,
  `descripcion` text NOT NULL,
  `fecha` date NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `id_viaje` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `actividad`
--

INSERT INTO `actividad` (`nombre`, `descripcion`, `fecha`, `precio`, `id_viaje`) VALUES
('Caminata por los Fiordos', 'Explora la naturaleza nórdica', '2025-05-12', 120.00, 5),
('Excursión en Río', 'Explora las playas y montañas', '2025-02-12', 150.00, 2),
('prueba', 'null', '2025-01-12', 0.00, 1),
('Tour en barco por el Mediterráneo', 'Descubre las maravillas marítimas', '2025-06-10', 200.00, 4),
('Tour por París', 'Visita guiada por los principales monumentos', '2025-03-05', 100.00, 1),
('Visita a la Ciudad Prohibida', 'Un recorrido histórico en Pekín', '2025-04-08', 50.00, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `aerolineas`
--

CREATE TABLE `aerolineas` (
  `codigo` varchar(10) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `pais` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `aerolineas`
--

INSERT INTO `aerolineas` (`codigo`, `nombre`, `pais`) VALUES
('2K', 'AVIANCA-Ecuador dba AVIANCA', 'EC'),
('3P', 'World 2 Fly PT, S.A.', 'PT'),
('6B', 'TUIfly Nordic AB', 'CN'),
('A.C.', 'Air France', 'FR'),
('A0', 'BA Euroflyer Limited dba Briti', 'GB'),
('AA', 'American Airlines', 'USA'),
('AM', 'Aerovias de Mexico SA de CV db', 'MX'),
('AR', 'Aerolineas Argentinas S.A.', 'AR'),
('AV', 'Aerovias del Continente Americ', 'CO'),
('AY', 'Finnair', 'FI'),
('AZ', 'Alitalia', 'IT'),
('BA', 'British Airways PLC', 'GB'),
('CL', 'Lufthansa CityLine GmbH', 'DE'),
('DE', 'Condor Flugdienst GmbH', 'DE'),
('DL', 'Delta Air Lines Inc', 'USA'),
('DS', 'Easyjet CH S.A', 'CH'),
('GL', 'Air GRL', 'GRL'),
('JJ', 'Tam Linhas Aereas SA dba Latam', 'BR'),
('KL', 'KLM', 'NL'),
('KN', 'CN United Airlines', 'CN'),
('LH', 'Lufthansa', 'DE'),
('LX', 'SWISS Internation Air Lines Lt', 'CH'),
('M3', 'BSA - Aerolinhas Brasileiras S', 'BR'),
('MS', 'Egyptair', 'EG'),
('MT', 'MT Air Travel Ltd dba MT MedAi', 'MT'),
('N0', 'Norse Atlantic Airways AS', 'NO'),
('OU', 'HR Airlines d.d.', 'HR'),
('PC', 'Pegasus Airlines', 'TR'),
('QR', 'QA Airways Group Q.C.S.C dba Q', 'QA'),
('RJ', 'Alia - The Royal JOn Airlines ', 'JO'),
('RK', 'RYNAIR', 'GB'),
('S4', 'SATA Internacional - Azores Ai', 'PT'),
('SN', 'Brussels Airlines', 'BE'),
('SP', 'SATA (Air Acores)', 'PT'),
('TK', 'Turkish Airlines Inc', 'TR'),
('TP', 'TAP PT', 'PT'),
('TS', 'Air Transat', 'CA'),
('U2', 'EASYJET UK LIMITED', 'GB'),
('UA', 'United Airlines Inc', 'USA'),
('UX', 'Air Europa Lineas Aereas, S.A.', 'ES'),
('VOY', 'Aerolínea Vueling SA', 'ES'),
('VS', 'Virgin Atlantic Airways Ltd', 'GB'),
('WA', 'KLM Cityhopper', 'NL'),
('WFL', 'World2Fly', 'ES'),
('WK', 'Edelweiss Air AG', 'CH'),
('X3', 'TUIfly Gmbh', 'DE'),
('X7', 'Challenge Airlines (BE) S.A.', 'BE'),
('YW', 'Air Nostrum, Lineas aereas del', 'ES');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `aeropuerto`
--

CREATE TABLE `aeropuerto` (
  `codigo` char(3) NOT NULL,
  `id_ciudad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `aeropuerto`
--

INSERT INTO `aeropuerto` (`codigo`, `id_ciudad`) VALUES
('ACA', 1),
('ACE', 2),
('AGP', 3),
('ALC', 4),
('AMM', 5),
('AMS', 6),
('ATH', 7),
('BCN', 8),
('BER', 9),
('BIO', 10),
('BJZ', 11),
('BKK', 12),
('BOG', 13),
('BOS', 14),
('BRU', 15),
('BSB', 16),
('BUE', 17),
('CAI', 18),
('CAS', 19),
('CCS', 20),
('CDG', 21),
('CPH', 22),
('DTT', 23),
('DUB', 24),
('DUS', 25),
('EAS', 26),
('FRA', 27),
('FUE', 28),
('GMZ', 29),
('GRO', 30),
('GRX', 31),
('GVA', 32),
('HAM', 33),
('HEL', 34),
('HOU', 35),
('IBZ', 36),
('IST', 37),
('JFK', 38),
('KIN', 39),
('LAX', 40),
('LBG', 41),
('LCG', 42),
('LGH', 43),
('LHR', 44),
('LIM', 45),
('LIS', 46),
('LPA', 47),
('LYS', 48),
('MAD', 49),
('MAH', 50),
('MEL', 51),
('MEX', 52),
('MIA', 53),
('MIL', 54),
('MJV', 55),
('MOW', 56),
('MRS', 57),
('MUC', 58),
('NBO', 59),
('ODB', 60),
('ORY', 61),
('OSL', 62),
('OVD', 63),
('PHL', 64),
('PMI', 65),
('PNA', 66),
('PRG', 67),
('RAK', 68),
('REU', 69),
('RIO', 70),
('SAO', 71),
('SCQ', 72),
('SDQ', 73),
('SDR', 74),
('SEA', 75),
('SFO', 76),
('SLM', 77),
('SPC', 78),
('STN', 79),
('STO', 80),
('STR', 81),
('SYD', 82),
('TFN', 83),
('TFS', 84),
('TUN', 85),
('VDE', 86),
('VGO', 87),
('VIE', 88),
('VIT', 89),
('VLC', 90),
('WAS', 91),
('WAW', 92),
('XRY', 93),
('YMQ', 94),
('YOW', 95),
('YTO', 96),
('YVR', 97),
('ZAZ', 98),
('ZRH', 99);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `agencia`
--

CREATE TABLE `agencia` (
  `id` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `logo` varchar(50) DEFAULT NULL,
  `color` char(7) DEFAULT NULL,
  `numero_empleados` int(2) NOT NULL,
  `limite_empleados` char(2) NOT NULL,
  `tipo_agencia` char(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `agencia`
--

INSERT INTO `agencia` (`id`, `nombre`, `logo`, `color`, `numero_empleados`, `limite_empleados`, `tipo_agencia`) VALUES
(1, 'Viajes Globales', 'logo1.png', '#FF5733', 3, 'L1', 'A1'),
(2, 'Rutas del Mundo', 'logo2.png', '#33FF57', 8, 'L2', 'A2'),
(3, 'Descubre y Explora', 'logo3.png', '#5733FF', 13, 'L3', 'A3'),
(4, 'Turismo Local', 'logo4.png', '#FFFF33', 5, 'L1', 'A2'),
(5, 'Aventuras Extensas', 'logo5.png', '#33FFFF', 4, 'L2', 'A1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `agentzia`
--

CREATE TABLE `agentzia` (
  `ID` int(11) NOT NULL,
  `Izena` varchar(100) NOT NULL,
  `Erabiltzailea` varchar(50) NOT NULL,
  `Pasahitza` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `agentzia`
--

INSERT INTO `agentzia` (`ID`, `Izena`, `Erabiltzailea`, `Pasahitza`) VALUES
(1, 'Agencia Elorrieta', 'elorrieta', '12345'),
(2, 'Agencia Bilbao', 'bilbao', 'contraseña');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alojamiento`
--

CREATE TABLE `alojamiento` (
  `id` int(11) NOT NULL,
  `nombre_hotel` varchar(50) NOT NULL,
  `fecha_entrada` date NOT NULL,
  `fecha_salida` date NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `id_viaje` int(11) NOT NULL,
  `id_ciudad` int(11) NOT NULL,
  `tipo_dormitorio` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `alojamiento`
--

INSERT INTO `alojamiento` (`id`, `nombre_hotel`, `fecha_entrada`, `fecha_salida`, `precio`, `id_viaje`, `id_ciudad`, `tipo_dormitorio`) VALUES
(1, 'Hotel Louvre', '2025-03-01', '2025-03-15', 1500.00, 1, 21, 'DB'),
(2, 'Resort Copacabana', '2025-02-10', '2025-02-20', 1200.00, 2, 16, 'DB'),
(3, 'Hotel Beijing', '2025-04-05', '2025-04-20', 900.00, 3, 13, 'SIN'),
(4, 'Crucero Mediterráneo', '2025-06-01', '2025-06-15', 2000.00, 4, 7, 'DUI'),
(5, 'Lodge Escandinavo', '2025-05-10', '2025-05-20', 1300.00, 5, 62, 'TPL');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudad`
--

CREATE TABLE `ciudad` (
  `id` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `codPais` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ciudad`
--

INSERT INTO `ciudad` (`id`, `nombre`, `codPais`) VALUES
(1, 'MÉXICO (ACAPULCO)', 'MX'),
(2, 'Lanzarote', 'ES'),
(3, 'MALAGA', 'ES'),
(4, 'Alicante', 'ES'),
(5, 'JO (Ammán)', 'JO'),
(6, 'HOLANDA Amsterdam', 'NL'),
(7, 'GRECIA (Atenas)', 'GR'),
(8, 'Barcelona', 'ES'),
(9, 'ALEMANIA (Berlín)', 'DE'),
(10, 'Bilbao', 'ES'),
(11, 'Badajoz', 'ES'),
(12, 'TAILANDIA Bagkok', 'TH'),
(13, 'COLOMBIA Bogotá', 'CO'),
(14, 'Boston', 'US'),
(15, 'BELGICA (Bruselas)', 'BE'),
(16, 'BRASIL (Brasilia)', 'BR'),
(17, 'Buenos Aires', 'AR'),
(18, 'EG El Cairo', 'EG'),
(19, 'MARRUECOS (Casablanca)', 'MA'),
(20, 'VENEZUELA (CARACAS)', 'VE'),
(21, 'FRANCIA, París (aeropuerto Cha', 'FR'),
(22, 'DINAMARCA', 'DK'),
(23, 'DETROIT', 'US'),
(24, 'IRLANDA (DUBLIN)', 'IE'),
(25, 'ALEMANIA (Dusseldorf)', 'DE'),
(26, 'SAN SEBASTIAN', 'ES'),
(27, 'ALEMANIA (Frankfurt)', 'DE'),
(28, 'FUERTEVENTURA', 'ES'),
(29, 'LA GOMERA', 'ES'),
(30, 'Gerona', 'ES'),
(31, 'Granada', 'ES'),
(32, 'SUIZA (Ginebra)', 'CH'),
(33, 'ALEMANIA (Hamburgo)', 'DE'),
(34, 'FINLANDIA (Helsinki)', 'FI'),
(35, 'Houston', 'US'),
(36, 'Ibiza', 'ES'),
(37, 'TR (Estambul)', 'TR'),
(38, 'Nueva York', 'US'),
(39, 'JAMAICA (Kingston)', 'JM'),
(40, 'LOS ANGELES', 'US'),
(41, 'FRANCIA, Le Bourget', 'FR'),
(42, 'La Coruña', 'ES'),
(43, 'LONDRES (Gatwick)', 'GB'),
(44, 'LONDRES Heathrow', 'GB'),
(45, 'PERU (Lima)', 'PE'),
(46, 'PT (Lisboa)', 'PT'),
(47, 'GRAN CANARIA', 'ES'),
(48, 'FRANCIA (Lyon)', 'FR'),
(49, 'Madrid', 'ES'),
(50, 'MAHON', 'ES'),
(51, 'AUSTRALIA Melbourne', 'AU'),
(52, 'México D.F.', 'MX'),
(53, 'Miami', 'US'),
(54, 'ITALIA (Milán)', 'IT'),
(55, 'Murcia', 'ES'),
(56, 'RUSIA (Moscú)', 'RU'),
(57, 'FRANCIA (Marsella)', 'FR'),
(58, 'ALEMANIA (Munich)', 'DE'),
(59, 'KENIA (Nairobi)', 'KE'),
(60, 'Córdoba', 'ES'),
(61, 'FRANCIA (Orly)', 'FR'),
(62, 'NORUEGA (Oslo)', 'NO'),
(63, 'Asturias', 'ES'),
(64, 'Philadelphia', 'US'),
(65, 'PALMA DE MALLORCA', 'ES'),
(66, 'Pamplona', 'ES'),
(67, 'REPUBLICA CHECA (Praga)', 'CZ'),
(68, 'MARRUECOS (Marrakech)', 'MA'),
(69, 'Reus', 'ES'),
(70, 'BRASIL (Rio de Janeiro)', 'BR'),
(71, 'BRASIL (Sao Paulo)', 'BR'),
(72, 'Santiago de Compostela', 'ES'),
(73, 'REPUBLICA DOMINICANA (Santo Do', 'DO'),
(74, 'Santander', 'ES'),
(75, 'Seattle', 'US'),
(76, 'San Francisco', 'US'),
(77, 'Salamanca', 'ES'),
(78, 'Santa Cruz de la Palma', 'ES'),
(79, 'LONDRES (Stanted)', 'GB'),
(80, 'SUECIA (Estocolmo)', 'SE'),
(81, 'ALEMANIA (Stuttgart)', 'DE'),
(82, 'AUSTRALIA (Sídney)', 'AU'),
(83, 'Tenerife Norte', 'ES'),
(84, 'Tenerife Sur', 'ES'),
(85, 'Túnez', 'TN'),
(86, 'Hierro', 'ES'),
(87, 'Vigo', 'ES'),
(88, 'AUSTRIA (Viena)', 'AT'),
(89, 'Vitoria', 'ES'),
(90, 'Valencia', 'ES'),
(91, 'Washington', 'US'),
(92, 'POLONIA (Varsovia)', 'PL'),
(93, 'JEREZ DE LA FRONTERA', 'ES'),
(94, 'Montreal, Québec', 'CA'),
(95, 'CA Ottawa, Ontario', 'CA'),
(96, 'CA Toronto, Ontario', 'CA'),
(97, 'CA Vancouver', 'CA'),
(98, 'Zaragoza', 'ES'),
(99, 'SUIZA (Zurich)', 'CH');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `numeroempleados`
--

CREATE TABLE `numeroempleados` (
  `codigo` char(2) NOT NULL,
  `maxEmpleados` int(11) NOT NULL,
  `minEmpleados` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `numeroempleados`
--

INSERT INTO `numeroempleados` (`codigo`, `maxEmpleados`, `minEmpleados`) VALUES
('L1', 5, 1),
('L2', 10, 1),
('L3', 20, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pais`
--

CREATE TABLE `pais` (
  `codigo` varchar(5) NOT NULL,
  `nombre` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pais`
--

INSERT INTO `pais` (`codigo`, `nombre`) VALUES
('AR', 'ARGENTINA'),
('AT', 'AUSTRIA'),
('AU', 'AUSTRALIA'),
('BE', 'BÉLGICA'),
('BR', 'BRASIL'),
('CA', 'CANADA'),
('CH', 'SUIZA'),
('CN', 'CHINA'),
('CO', 'COLOMBIA'),
('CU', 'CUBA'),
('CY', 'CHIPRE'),
('CZ', 'REPUBLICA CHECA'),
('DE', 'ALEMANIA'),
('DK', 'DINAMARCA'),
('DO', 'REPUBLICA DOMINICANA'),
('EE', 'ESTONIA'),
('EG', 'EGIPTO'),
('ES', 'ESPAÑA'),
('FI', 'FINLANDIA'),
('FR', 'FRANCIA'),
('GB', 'REINO UNIDO'),
('GR', 'GRECIA'),
('GT', 'GUATEMALA'),
('HK', 'HONG-KONG'),
('HR', 'CROACIA'),
('HU', 'HUNGRIA'),
('ID', 'INDONESIA'),
('IE', 'IRLANDA'),
('IL', 'ISRAEL'),
('IN', 'INDIA'),
('IS', 'ISLANDIA'),
('IT', 'ITALIA'),
('JM', 'JAMAICA'),
('JO', 'JORDAN'),
('JP', 'JAPÓN'),
('KE', 'KENIA'),
('LU', 'LUXEMBURGO'),
('MA', 'MARRUECOS'),
('MC', 'MÓNACO'),
('MT', 'MALTA'),
('MV', 'MALDIVAS'),
('MX', 'MEXICO'),
('NL', 'PAISES BAJOS'),
('NO', 'NORUEGA'),
('PA', 'PANAMÁ'),
('PE', 'PERÚ'),
('PL', 'POLONIA'),
('PR', 'PUERTO RICO'),
('PT', 'PORTUGAL'),
('QA', 'QATAR'),
('RO', 'RUMANIA'),
('RU', 'RUSIA'),
('SC', 'SEYCHELLES'),
('SE', 'SUECIA'),
('SG', 'SINGAPUR'),
('TH', 'TAILANDIA'),
('TN', 'TÚNEZ'),
('TR', 'TURQUIA'),
('TZ', 'TANZANIA (INCLUYE ZANZIBAR)'),
('US', 'ESTADOS UNIDOS'),
('VE', 'VENEZUELA'),
('VN', 'VIETNAM'),
('ZA', 'SUDÁFRICA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipodormitorio`
--

CREATE TABLE `tipodormitorio` (
  `codigo` varchar(5) NOT NULL,
  `descripcion` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipodormitorio`
--

INSERT INTO `tipodormitorio` (`codigo`, `descripcion`) VALUES
('DB', 'Doble'),
('DUI', 'Uso doble e individual'),
('SIN', 'Individual'),
('TPL', 'Triple');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tiposagencia`
--

CREATE TABLE `tiposagencia` (
  `codigo` char(2) NOT NULL,
  `descripcion` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tiposagencia`
--

INSERT INTO `tiposagencia` (`codigo`, `descripcion`) VALUES
('A1', 'Mayorista'),
('A2', 'Minorista'),
('A3', 'Mayorista-minorista');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipoviaje`
--

CREATE TABLE `tipoviaje` (
  `codigo` char(2) NOT NULL,
  `descripcion` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipoviaje`
--

INSERT INTO `tipoviaje` (`codigo`, `descripcion`) VALUES
('B1', 'Los novios'),
('B2', 'Senior'),
('B3', 'Grupos'),
('B4', 'Grandes viajes (destinos exóticos + vuelo + alojamiento)'),
('B5', 'Escapada'),
('B6', 'Familias (con niños pequeños)');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `viaje`
--

CREATE TABLE `viaje` (
  `id` int(11) NOT NULL,
  `nombreViaje` varchar(30) NOT NULL,
  `descViaje` text DEFAULT NULL,
  `inicioViaje` date NOT NULL,
  `finViaje` date NOT NULL,
  `numeroDias` int(11) GENERATED ALWAYS AS (to_days(`finViaje`) - to_days(`inicioViaje`)) STORED,
  `servNoIncluidos` text DEFAULT NULL,
  `id_agencia` int(11) NOT NULL,
  `tipo_viaje` char(2) NOT NULL,
  `pais_destino` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `viaje`
--

INSERT INTO `viaje` (`id`, `nombreViaje`, `descViaje`, `inicioViaje`, `finViaje`, `servNoIncluidos`, `id_agencia`, `tipo_viaje`, `pais_destino`) VALUES
(1, 'Europa Clásica', 'Un recorrido por las principales ciudades europeas', '2025-03-01', '2025-03-15', 'Comidas no especificadas', 1, 'B4', 'FR'),
(2, 'Escapada Tropical', 'Descubre las playas de Brasil', '2025-02-10', '2025-02-20', 'Traslados', 2, 'B5', 'BR'),
(3, 'Aventura en Asia', 'Un viaje por los rincones de Asia', '2025-04-05', '2025-04-20', 'Seguro de viaje', 3, 'B4', 'CN'),
(4, 'Ruta Mediterránea', 'Explora las maravillas del Mediterráneo', '2025-06-01', '2025-06-15', 'Propinas', 4, 'B3', 'ES'),
(5, 'Naturaleza Escandinava', 'Conexión con la naturaleza en los países nórdicos', '2025-05-10', '2025-05-20', 'Entradas a museos', 5, 'B6', 'SE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vuelo`
--

CREATE TABLE `vuelo` (
  `codigo_vuelo` varchar(50) NOT NULL,
  `fecha_ida` date NOT NULL,
  `fecha_vuelta` date DEFAULT NULL,
  `precio_total` decimal(10,2) NOT NULL,
  `hora_salida` time NOT NULL,
  `duracion` time NOT NULL,
  `id_viaje` int(11) NOT NULL,
  `aeropuerto_origen` char(3) NOT NULL,
  `aeropuerto_destino` char(3) DEFAULT NULL,
  `aerolinea` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `vuelo`
--

INSERT INTO `vuelo` (`codigo_vuelo`, `fecha_ida`, `fecha_vuelta`, `precio_total`, `hora_salida`, `duracion`, `id_viaje`, `aeropuerto_origen`, `aeropuerto_destino`, `aerolinea`) VALUES
('BR5678', '2025-02-10', '2025-02-20', 700.00, '14:00:00', '04:00:00', 2, 'RIO', 'MAD', 'JJ'),
('FR1234', '2025-03-01', '2025-03-15', 600.00, '08:00:00', '02:30:00', 1, 'MAD', 'CDG', 'UX');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `actividad`
--
ALTER TABLE `actividad`
  ADD PRIMARY KEY (`nombre`),
  ADD KEY `id_evento` (`id_viaje`);

--
-- Indices de la tabla `aerolineas`
--
ALTER TABLE `aerolineas`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `aeropuerto`
--
ALTER TABLE `aeropuerto`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `id_ciudad` (`id_ciudad`);

--
-- Indices de la tabla `agencia`
--
ALTER TABLE `agencia`
  ADD PRIMARY KEY (`id`),
  ADD KEY `num_empleados` (`limite_empleados`),
  ADD KEY `tipo_agencia` (`tipo_agencia`);

--
-- Indices de la tabla `agentzia`
--
ALTER TABLE `agentzia`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Erabiltzailea` (`Erabiltzailea`);

--
-- Indices de la tabla `alojamiento`
--
ALTER TABLE `alojamiento`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_evento` (`id_viaje`),
  ADD KEY `id_ciudad` (`id_ciudad`),
  ADD KEY `tipo_dormitorio` (`tipo_dormitorio`);

--
-- Indices de la tabla `ciudad`
--
ALTER TABLE `ciudad`
  ADD PRIMARY KEY (`id`),
  ADD KEY `codPais` (`codPais`);

--
-- Indices de la tabla `numeroempleados`
--
ALTER TABLE `numeroempleados`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `pais`
--
ALTER TABLE `pais`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `tipodormitorio`
--
ALTER TABLE `tipodormitorio`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `tiposagencia`
--
ALTER TABLE `tiposagencia`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `tipoviaje`
--
ALTER TABLE `tipoviaje`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `viaje`
--
ALTER TABLE `viaje`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_agencia` (`id_agencia`),
  ADD KEY `tipo_viaje` (`tipo_viaje`),
  ADD KEY `pais_destino` (`pais_destino`);

--
-- Indices de la tabla `vuelo`
--
ALTER TABLE `vuelo`
  ADD PRIMARY KEY (`codigo_vuelo`),
  ADD KEY `id_evento` (`id_viaje`),
  ADD KEY `aeropuerto_origen` (`aeropuerto_origen`),
  ADD KEY `aeropuerto_destino` (`aeropuerto_destino`),
  ADD KEY `aerolinea` (`aerolinea`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `agencia`
--
ALTER TABLE `agencia`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `agentzia`
--
ALTER TABLE `agentzia`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `alojamiento`
--
ALTER TABLE `alojamiento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `ciudad`
--
ALTER TABLE `ciudad`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=100;

--
-- AUTO_INCREMENT de la tabla `viaje`
--
ALTER TABLE `viaje`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `actividad`
--
ALTER TABLE `actividad`
  ADD CONSTRAINT `actividad_ibfk_1` FOREIGN KEY (`id_viaje`) REFERENCES `viaje` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `aeropuerto`
--
ALTER TABLE `aeropuerto`
  ADD CONSTRAINT `aeropuerto_ibfk_1` FOREIGN KEY (`id_ciudad`) REFERENCES `ciudad` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `agencia`
--
ALTER TABLE `agencia`
  ADD CONSTRAINT `agencia_ibfk_1` FOREIGN KEY (`limite_empleados`) REFERENCES `numeroempleados` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `agencia_ibfk_2` FOREIGN KEY (`tipo_agencia`) REFERENCES `tiposagencia` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `alojamiento`
--
ALTER TABLE `alojamiento`
  ADD CONSTRAINT `alojamiento_ibfk_1` FOREIGN KEY (`id_viaje`) REFERENCES `viaje` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `alojamiento_ibfk_2` FOREIGN KEY (`id_ciudad`) REFERENCES `ciudad` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `alojamiento_ibfk_3` FOREIGN KEY (`tipo_dormitorio`) REFERENCES `tipodormitorio` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `ciudad`
--
ALTER TABLE `ciudad`
  ADD CONSTRAINT `ciudad_ibfk_1` FOREIGN KEY (`codPais`) REFERENCES `pais` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `viaje`
--
ALTER TABLE `viaje`
  ADD CONSTRAINT `viaje_ibfk_1` FOREIGN KEY (`id_agencia`) REFERENCES `agencia` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `viaje_ibfk_2` FOREIGN KEY (`tipo_viaje`) REFERENCES `tipoviaje` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `viaje_ibfk_3` FOREIGN KEY (`pais_destino`) REFERENCES `pais` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `vuelo`
--
ALTER TABLE `vuelo`
  ADD CONSTRAINT `vuelo_ibfk_1` FOREIGN KEY (`id_viaje`) REFERENCES `viaje` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `vuelo_ibfk_2` FOREIGN KEY (`aeropuerto_origen`) REFERENCES `aeropuerto` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `vuelo_ibfk_3` FOREIGN KEY (`aeropuerto_destino`) REFERENCES `aeropuerto` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `vuelo_ibfk_4` FOREIGN KEY (`aerolinea`) REFERENCES `aerolineas` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 4.1.4
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 27, 2016 alle 17:06
-- Versione del server: 5.6.15-log
-- PHP Version: 5.5.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `viaggi`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `acquisti`
--

CREATE TABLE IF NOT EXISTS `acquisti` (
  `codacquisti` varchar(10) NOT NULL,
  `codcliente` varchar(10) NOT NULL,
  `codpacchetto` varchar(10) NOT NULL,
  `dataacquisti` date NOT NULL,
  `partenza` date NOT NULL,
  PRIMARY KEY (`codacquisti`),
  KEY `codcliente` (`codcliente`),
  KEY `codpacchetto` (`codpacchetto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `acquisti`
--

INSERT INTO `acquisti` (`codacquisti`, `codcliente`, `codpacchetto`, `dataacquisti`, `partenza`) VALUES
('a1', 'c1', 'p1', '2013-10-08', '2013-12-20'),
('a10', 'c8', 'P33', '2012-11-12', '2012-12-02'),
('a11', 'c8', 'P33', '2012-11-12', '2012-12-02'),
('a12', 'c8', 'P26', '2013-11-12', '2013-12-12'),
('a13', 'c8', 'P26', '2013-11-22', '2013-12-12'),
('a2', 'c3', 'p3', '2013-10-08', '2013-12-28'),
('a3', 'c1', 'p10', '2013-04-08', '2013-06-20'),
('a4', 'c3', 'p1', '2013-02-08', '2013-04-28'),
('a8', 'c8', 'P12', '2012-07-12', '2012-08-02'),
('a9', 'c8', 'P32', '2012-07-12', '2012-08-02');

-- --------------------------------------------------------

--
-- Struttura della tabella `clienti`
--

CREATE TABLE IF NOT EXISTS `clienti` (
  `codcliente` varchar(10) NOT NULL,
  `cognome` text NOT NULL,
  `nome` text NOT NULL,
  `indirizzo` text NOT NULL,
  `citta` text NOT NULL,
  `telefono` text NOT NULL,
  PRIMARY KEY (`codcliente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `clienti`
--

INSERT INTO `clienti` (`codcliente`, `cognome`, `nome`, `indirizzo`, `citta`, `telefono`) VALUES
('c1', 'Polselli', 'Massimo', 'via Gonici 2', 'Milano', '02 43752666'),
('c10', 'Rosi', 'Giovanna', 'via Verdi 18', 'Verona', '044 562726'),
('c11', 'Sabbatini', 'Giorgia', 'via Bartoli 21', 'Milano', '02 12312366'),
('c12', 'Rosini', 'Rosa', 'via Malva 18', 'Verona', '044 562726'),
('c13', 'Rottini', 'Mario', 'via Bartolini 13', 'Milano', '02 12312366'),
('c14', 'Rossi', 'Paolo', 'via Rossini 18', 'Verona', '044 562726'),
('c2', 'Musca', 'Giuliana', 'via Padova 88', 'Venezia', '041 23117726'),
('c3', 'Bettini', 'Renatoo', 'via Pici 2', 'Milano', '02 43712366'),
('c4', 'Morni', 'Monica', 'via Padova 88', 'Venezia', '041 23117726'),
('c5', 'Cucchi', 'Paolo', 'via Sfici 12', 'Milano', '02 2312366'),
('c6', 'Mornitti', 'Maura', 'via Caoli 88', 'Venezia', '041 23117726'),
('c7', 'Gemignani', 'Feferico', 'via Tolci 212', 'Milano', '02 12312366'),
('c8', 'Ulivieri', 'Giovanni', 'via Cini 18', 'Verona', '044 562726'),
('c9', 'Sabbatini', 'Gigi', 'via Batti 21', 'Milano', '02 12312366');

-- --------------------------------------------------------

--
-- Struttura della tabella `nazioni`
--

CREATE TABLE IF NOT EXISTS `nazioni` (
  `codnazioni` varchar(8) NOT NULL,
  `denom` text NOT NULL,
  `moneta` text NOT NULL,
  `clima` text NOT NULL,
  `costovisto` decimal(4,2) NOT NULL,
  `rilasciovisto` text NOT NULL,
  PRIMARY KEY (`codnazioni`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `nazioni`
--

INSERT INTO `nazioni` (`codnazioni`, `denom`, `moneta`, `clima`, `costovisto`, `rilasciovisto`) VALUES
('n1', 'Madagascar', 'franco malgascio', 'mite', '15.00', 'entrata'),
('n10', 'Spagna', 'euro', 'vario', '0.00', ' '),
('n11', 'Islanda', 'euro', 'freddo', '0.00', ' '),
('n2', 'Mauritius', 'franco', 'mite', '15.00', 'entrata'),
('n3', 'Sudafrica', 'rand', 'mite', '15.00', 'entrata'),
('n4', 'Namibia', 'franco', 'mite', '20.00', 'consolato'),
('n5', 'Ladakh', 'rupia', 'rigido', '25.00', 'consolato'),
('n6', 'India', 'rupia', 'vario', '15.00', 'consolato'),
('n7', 'Cina', 'remimbi', 'vario', '30.00', 'consolato'),
('n9', 'Grecia', 'euro', 'mite', '0.00', ' ');

-- --------------------------------------------------------

--
-- Struttura della tabella `pacchetti`
--

CREATE TABLE IF NOT EXISTS `pacchetti` (
  `codpacchetto` varchar(10) NOT NULL,
  `descrizione` text NOT NULL,
  `modalita` text NOT NULL,
  `prezzo` decimal(8,2) NOT NULL,
  `codnazioni` varchar(8) NOT NULL,
  `codorg` varchar(8) NOT NULL,
  `vaccinazioni` text NOT NULL,
  `giorni` int(11) NOT NULL,
  PRIMARY KEY (`codpacchetto`),
  KEY `codnazioni` (`codnazioni`),
  KEY `codorg` (`codorg`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `pacchetti`
--

INSERT INTO `pacchetti` (`codpacchetto`, `descrizione`, `modalita`, `prezzo`, `codnazioni`, `codorg`, `vaccinazioni`, `giorni`) VALUES
('p1', 'Madagascar', 'trasporti prenotati', '2900.00', 'n1', 'to1', 'consigliate', 21),
('p10', 'Mauritius smile', 'tutto compreso', '3760.00', 'n2', 'to6', 'consigliate', 15),
('p11', 'Mauritius a vela ', 'crociera', '2670.00', 'n2', 'to3', 'consigliate', 19),
('p12', 'Sudafrica breve', 'trasporti prenotati', '2700.00', 'n3', 'to3', 'nessuna', 19),
('p13', 'Sudafrica soft', 'tutto compreso', '3300.00', 'n3', 'to5', 'nessuna', 20),
('p14', 'Sudafrica Kruger', 'trasporti prenotati', '2700.00', 'n3', 'to1', 'nessuna', 21),
('P15', 'Sudafrica la via dei vini', 'tutto compreso', '3700.00', 'n3', 'to4', 'nessuna', 16),
('p16', 'Sudafrica music', 'tutto compreso', '2950.00', 'n3', 'to3', 'nessuna', 19),
('P17', 'Sudafrica tutto natura', 'tutto compreso', '3700.00', 'n3', 'to5', 'nessuna', 20),
('p18', 'Garden route', 'tutto compreso', '3250.00', 'n3', 'to4', 'nessuna', 16),
('P19', 'tutto Namibia', 'tutto compreso', '3750.00', 'n4', 'to5', 'nessuna', 20),
('p2', 'Madagascar natura', 'trasporti prenotati', '2240.00', 'n1', 'to1', 'consigliate', 21),
('p20', 'Namibia natura', 'trasporti prenotati', '2250.00', 'n4', 'to1', 'nessuna', 21),
('P21', 'Himba Namibia', 'tutto compreso', '3750.00', 'n4', 'to5', 'nessuna', 20),
('P23', 'Namib Etosha', 'safari', '3750.00', 'n4', 'to5', 'nessuna', 20),
('p24', 'Ladakh Festival', 'trekking', '1665.00', 'n5', 'to1', 'nessuna', 21),
('p25', 'Ladakh Imachal', 'trasporti prenotati', '2265.00', 'n5', 'to3', 'nessuna', 19),
('P26', 'Spiti Kinnaur', 'trasporti prenotati', '1750.00', 'n5', 'to5', 'nessuna', 20),
('p27', 'Piccolo Tibet soft', 'trasporti prenotati', '2565.00', 'n5', 'to2', 'nessuna', 18),
('P28', 'Ladakh Zanskar', 'trekking', '1790.00', 'n5', 'to1', 'nessuna', 21),
('p29', 'Zanskar breve', 'trekking', '3200.00', 'n5', 'to4', 'nessuna', 14),
('p3', 'Tutto Madagascar ', 'tutto compreso', '2980.00', 'n1', 'to2', 'consigliate', 18),
('p30', 'Tutto monasteri', 'tutto compreso', '2900.00', 'n5', 'to4', 'nessuna', 15),
('p31', 'Rajastan dei maraja', 'tutto compreso', '2900.00', 'n6', 'to1', 'consigliate', 8),
('p32', 'India in libertà', 'trasporti locali', '1900.00', 'n6', 'to3', 'consigliate', 22),
('p33', 'Delhi Agra Jaipur', 'tutto compreso', '2900.00', 'n6', 'to2', 'consigliate', 13),
('p34', 'India centrale', 'trasporti prenotati', '1940.00', 'n6', 'to5', 'consigliate', 22),
('p35', 'Andamane sub', 'tutto compreso', '3400.00', 'n6', 'to6', 'consigliate', 16),
('p36', 'Lungo il Gange', 'trasporti prenotati', '2140.00', 'n6', 'to2', 'consigliate', 16),
('P38', 'tra i geyser', 'tutto compreso', '3200.00', 'n11', 'to6', 'nessuna', 16),
('P39', 'Fuerteventura express', 'tutto compreso', '2300.00', 'n10', 'to6', 'nessuna', 8),
('p4', 'Mad sorpresa', 'trasporti locali', '2800.00', 'n1', 'to3', 'consigliate', 19),
('P40', 'Kruger park', 'servizi prenotati', '2100.00', 'n3', 'to3', 'nessuna', 12),
('P41', 'palme e mare', 'tutto compreso', '2200.00', 'n2', 'to3', 'nessuna', 16),
('p5', 'Baobab e pachipodium ', 'tutto compreso', '3600.00', 'n1', 'to5', 'consigliate', 20),
('p6', 'Madagascar reef', 'tutto compreso', '3760.00', 'n1', 'to6', 'consigliate', 15),
('p7', 'Nosy Be Express ', 'tutto compreso', '2160.00', 'n1', 'to1', 'consigliate', 21),
('p8', 'Tulear reef Express', 'tutto compreso', '3760.00', 'n1', 'to6', 'consigliate', 15),
('p9', 'Madagascar S.te Marie ', 'trasporti locali', '2160.00', 'n1', 'to1', 'consigliate', 21);

-- --------------------------------------------------------

--
-- Struttura della tabella `touroperator`
--

CREATE TABLE IF NOT EXISTS `touroperator` (
  `codorg` varchar(8) NOT NULL,
  `nome` text NOT NULL,
  `indirizzo` text NOT NULL,
  `citta` text NOT NULL,
  `telefono` text NOT NULL,
  PRIMARY KEY (`codorg`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `touroperator`
--

INSERT INTO `touroperator` (`codorg`, `nome`, `indirizzo`, `citta`, `telefono`) VALUES
('to1', 'Viaggi nel Mondo', 'Largo Grigioni 7', 'Roma', '06 77652387'),
('to2', 'Viaggi Elefante', 'via Pandora 12', 'Milano', '01 22354329'),
('to3', 'Argonauti', 'via Sirio 3212', 'Torino', '011 42946777'),
('to4', 'Franco Rosso', 'via Torino 112', 'Milano', '02 78452347'),
('to5', 'Viaggi della Conchiglia', 'via Nautilus 44', 'Milano', '02 3411329'),
('to6', 'Club Mediterranée', 'via Pamplona 15', 'Milano', '02 88263537');

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `acquisti`
--
ALTER TABLE `acquisti`
  ADD CONSTRAINT `acquisti_ibfk_1` FOREIGN KEY (`codcliente`) REFERENCES `clienti` (`codcliente`),
  ADD CONSTRAINT `acquisti_ibfk_2` FOREIGN KEY (`codpacchetto`) REFERENCES `pacchetti` (`codpacchetto`);

--
-- Limiti per la tabella `pacchetti`
--
ALTER TABLE `pacchetti`
  ADD CONSTRAINT `pacchetti_ibfk_1` FOREIGN KEY (`codorg`) REFERENCES `touroperator` (`codorg`),
  ADD CONSTRAINT `pacchetti_ibfk_2` FOREIGN KEY (`codnazioni`) REFERENCES `nazioni` (`codnazioni`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

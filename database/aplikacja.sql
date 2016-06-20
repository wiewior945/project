-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Czas generowania: 20 Cze 2016, 14:44
-- Wersja serwera: 10.1.10-MariaDB
-- Wersja PHP: 5.6.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `aplikacja`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `groups`
--

CREATE TABLE `groups` (
  `id` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `adminID` int(11) NOT NULL,
  `isPublic` tinyint(1) NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `groups`
--

INSERT INTO `groups` (`id`, `name`, `adminID`, `isPublic`, `password`) VALUES
(8, 'Prywatne notatki', 9, 0, ''),
(9, 'tyu', 9, 1, ''),
(10, 'rfc', 9, 0, 'rfc'),
(11, 'qaz', 9, 0, 'qaz');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `notegroup`
--

CREATE TABLE `notegroup` (
  `noteId` int(11) NOT NULL,
  `groupId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `notegroup`
--

INSERT INTO `notegroup` (`noteId`, `groupId`) VALUES
(9, 8),
(11, 8),
(12, 8),
(13, 8),
(14, 8),
(11, 9),
(9, 11);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `notes`
--

CREATE TABLE `notes` (
  `id` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `name` varchar(10000) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `note` varchar(10000) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `notes`
--

INSERT INTO `notes` (`id`, `userId`, `name`, `note`) VALUES
(9, 9, 'jakas', '<b>string</b> <font color="red">+++</font>'),
(11, 9, 'nowa nazwa', '<p dir="ltr"><b><i><u>edytowana</u></i></b> notatka <b>ttt</b> ghb <i><u>tgfhbc</u></i> jgg</p>\n'),
(12, 9, 'dluga dluga naaaaaazwa', '<p dir="ltr">ddd</p>\n'),
(13, 9, 'czwarta', '<p dir="ltr"><b>fgvc</b></p>\n'),
(14, 9, 'pionta', '<p dir="ltr"><b><u>pisionta</u></b></p>\n');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `Username` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `Password` varchar(50) CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `privateGroup` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `user`
--

INSERT INTO `user` (`id`, `Username`, `Password`, `privateGroup`) VALUES
(9, 'q', 'q', 8);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `usergroup`
--

CREATE TABLE `usergroup` (
  `userID` int(11) NOT NULL,
  `groupID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `usergroup`
--

INSERT INTO `usergroup` (`userID`, `groupID`) VALUES
(9, 8),
(9, 9),
(9, 10),
(9, 11);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `notes`
--
ALTER TABLE `notes`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `usergroup`
--
ALTER TABLE `usergroup`
  ADD KEY `groupID` (`groupID`),
  ADD KEY `userID` (`userID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `groups`
--
ALTER TABLE `groups`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT dla tabeli `notes`
--
ALTER TABLE `notes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT dla tabeli `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `usergroup`
--
ALTER TABLE `usergroup`
  ADD CONSTRAINT `groupsCascade` FOREIGN KEY (`groupID`) REFERENCES `groups` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `userCascade` FOREIGN KEY (`userID`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

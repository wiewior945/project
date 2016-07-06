-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Czas generowania: 06 Lip 2016, 20:10
-- Wersja serwera: 10.1.10-MariaDB
-- Wersja PHP: 7.0.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `notesdb`
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
(8, 'Prywatne notatki 7', 9, 1, ''),
(9, 'Testowanie', 1, 1, ''),
(10, 'Ziomeczki z klasy', 1, 0, 'gall'),
(11, 'PrivateGroup_Greg', 4, 0, ''),
(12, 'PrivateGroup_Greg', 5, 0, ''),
(13, 'PrivateGroup_Greg', 6, 0, ''),
(14, 'PrivateGroup_Tomasz', 7, 0, ''),
(15, 'PrivateGroup_Kao', 8, 0, '');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `migrations`
--

CREATE TABLE `migrations` (
  `migration` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `migrations`
--

INSERT INTO `migrations` (`migration`, `batch`) VALUES
('2014_10_12_000000_create_users_table', 1),
('2014_10_12_100000_create_password_resets_table', 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `notegroup`
--

CREATE TABLE `notegroup` (
  `recordID` int(11) NOT NULL,
  `noteId` int(11) NOT NULL,
  `groupId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `notegroup`
--

INSERT INTO `notegroup` (`recordID`, `noteId`, `groupId`) VALUES
(1, 9, 8),
(2, 11, 8),
(3, 12, 8),
(4, 13, 8),
(5, 14, 8),
(6, 11, 9),
(7, 9, 11),
(8, 6, 9),
(9, 6, 8),
(10, 1, 8),
(11, 0, 8);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `notes`
--

CREATE TABLE `notes` (
  `id` int(11) NOT NULL,
  `nazwa` varchar(255) CHARACTER SET ucs2 COLLATE ucs2_polish_ci NOT NULL,
  `userID` int(11) NOT NULL,
  `Contents` text CHARACTER SET utf8 COLLATE utf8_polish_ci NOT NULL,
  `Created` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Zrzut danych tabeli `notes`
--

INSERT INTO `notes` (`id`, `nazwa`, `userID`, `Contents`, `Created`) VALUES
(6, 'Zażółć Gęślą jaźń', 1, '<p>Testowanie <strong>znak&oacute;w</strong> polskich. Edytowałem!</p>', '13.06.2016 19:25:02'),
(7, 'Przykład', 1, '<p>Piszę właśnie przykładową nota<strong>tkę.</strong></p>', '06.07.2016 06:21:53'),
(8, 'Przykład', 1, '<p>Piszę właśnie przykładową nota<strong>tkę.</strong></p>', '06.07.2016 06:23:08'),
(9, 'Przykład', 1, '<p>Piszę właśnie przykładową nota<strong>tkę.</strong></p>', '06.07.2016 06:26:20');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `password_resets`
--

CREATE TABLE `password_resets` (
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `token` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
(1, 8),
(1, 9),
(1, 8),
(1, 8),
(1, 10),
(1, 10),
(3, 0),
(4, 11),
(5, 12),
(6, 13),
(7, 14),
(8, 15);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `mobile_pass` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `remember_token` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL,
  `privateGroup` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Zrzut danych tabeli `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `password`, `mobile_pass`, `remember_token`, `created_at`, `updated_at`, `privateGroup`) VALUES
(1, 'Grzegorz Leks', 'grzegorz.leks@gmail.com', '$2y$10$fd68JORsdOm.BUueg14jrOoyyWEnCdHj5drHFp/W43YRH8g5IQjQi', '', '4xdXPin9f3qFDCsHny0QBrDgGIWV5Lafh5r2ybCwOVI0lkpgP4dkN7Kz8AVM', '2016-05-23 08:25:49', '2016-07-05 18:47:48', 8),
(2, 'Grzegorz', 'gleks@pwc.com', 'test123', '', NULL, '2016-07-03 16:50:20', '2016-07-03 16:50:20', 10),
(3, '', 'Wiewior to cipa', 'gallardo', '', NULL, NULL, NULL, 0),
(4, 'Greg', 'greg@greg.com', 'test123', '', NULL, NULL, NULL, 11),
(5, 'Greg', 'test@test.com', 'test123', '', NULL, '2016-07-05 18:28:11', '2016-07-05 18:28:11', 12),
(6, 'Greg', 'test@test.co', 'test123', '', 'H2gTeI0Xj6xjehX9NxYivZVvzKcQWPz1gtrbtLqgWHWpm01VOP16wU8uLYtW', '2016-07-05 18:28:54', '2016-07-05 18:32:20', 13),
(7, 'Tomasz', 'gitara@struny.pl', '$2y$10$bfYfZLIlB6AvVYUIeZ4/vOOuKiNNXCpnooagBeaJ5ABAv2jGrMZlq', '', 'dnyIOQEweDMtGVUQ6EtGVOCV5Y9JAjTUNBqyBCC5Nk6rnqe0kq2Pz8HfgEtU', '2016-07-05 18:48:19', '2016-07-05 18:48:22', 14),
(8, 'Kao', 'Kao@stempel.pl', '$2y$10$8gwz7F1tx4VGzavyUOhHW.AY2eDpIe17pIzQUZANP/ddAqfdiUDUK', 'gallardo', 'qw9DagubDsvTub8kir026kMlM5XELdDh6er7QyPGUeU1iWIBFSSLgKAUPuUC', '2016-07-05 18:49:04', '2016-07-05 18:50:52', 15);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `notegroup`
--
ALTER TABLE `notegroup`
  ADD PRIMARY KEY (`recordID`);

--
-- Indexes for table `notes`
--
ALTER TABLE `notes`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `password_resets`
--
ALTER TABLE `password_resets`
  ADD KEY `password_resets_email_index` (`email`),
  ADD KEY `password_resets_token_index` (`token`);

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
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_email_unique` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `groups`
--
ALTER TABLE `groups`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT dla tabeli `notegroup`
--
ALTER TABLE `notegroup`
  MODIFY `recordID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT dla tabeli `notes`
--
ALTER TABLE `notes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT dla tabeli `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT dla tabeli `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Database: `HealTech`
--
CREATE DATABASE IF NOT EXISTS `HealTech` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `HealTech`;

-- --------------------------------------------------------

--
-- Table structure for table `doctor_users`
--

CREATE TABLE `doctor_users` (
  `doctor_id` int(11) NOT NULL,
  `frist_name` varchar(50) NOT NULL,
  `second_name` varchar(50) NOT NULL,
  `email` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `medical_id` varchar(6) NOT NULL,
  `hospital_clinic_id` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `doctor_users`
--

INSERT INTO `doctor_users` (`doctor_id`, `frist_name`, `second_name`, `email`, `password`, `medical_id`, `hospital_clinic_id`) VALUES
(1, 'Mohamed', 'Ahmed', 'mohamed123@gmail.com', '12345678', '323170', 'nh7418'),
(8, 'hany', 'ahmed', 'hany123@gmail.com', '12345888', '323160', 'dfh7417'),
(9, 'mahmoud', 'rada', 'mhm123@gmail.com', '4569871', '323152', 'meh7416'),
(10, 'Ahmed', 'Hossam', 'Ahmed14@gmail.com', 'ahmed14', '323171', 'dfh7417 '),
(11, 'Ahmed', 'Hossam', 'jana14@gmail.com', 'ahmed14', '323175', 'zh7419 '),
(12, 'hla', 'essam', 'hla@gmail.com', '2589637', '123654', 'meh7416 '),
(13, 'hllaa', 'essam', 'hla42000@gmail.com', 'hla12345', '159369', 'nh7418 '),
(14, 'hla', 'essam', 'h300@gmail.com', 'hla12345', '963258', 'zh7419 '),
(15, 'karem', 'adel', 'karem@gmail.com', 'k123456', '369852', 'nh7418 '),
(16, 'Karim', 'Ahmed', 'karim@gmail.com', 'k654321', '159874', 'nh7418 ');

-- --------------------------------------------------------

--
-- Table structure for table `hospital_clinicals`
--

CREATE TABLE `hospital_clinicals` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `code` varchar(14) NOT NULL,
  `phone` varchar(13) NOT NULL,
  `city` varchar(50) NOT NULL,
  `street` varchar(250) NOT NULL,
  `country` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hospital_clinicals`
--

INSERT INTO `hospital_clinicals` (`id`, `name`, `code`, `phone`, `city`, `street`, `country`) VALUES
(1, 'Zamalek hospital', 'zh7419', '+20 233470131', 'Agouza, Giza Governorate', '\r\n3675669, El-Kasasin, Madinet Al Eelam', 'Egypt'),
(2, 'Nile Hospital	', 'nh7418', '+20 235680181', 'Giza Governorate ', '5 El Gamaa st,beside National Bank of Egypt', 'Egypt'),
(3, '\r\nDar Al Fouad Hospital	', 'dfh7417', '+20 238247248', 'Giza Governorate ', 'XXW8+XW8, 26th of July Corridor, المنطقة السياحية،، First 6th of October', 'Egypt'),
(4, 'Middle East Hospital', 'meh7416', '+20 34299505', 'Alexandria Governorate', '25 Mohammed Fawzi Moaz, Smouha, Ezbet Saad, Sidi Gabir, Qesm', 'Egypt'),
(5, 'Victoria Hospital', 'vh7415', '+20 35776770', 'Alexandria Governorate', '\r\nFleb Glad, Al Qasaei Qebli, Qesm AR Ramel', 'Egypt');

-- --------------------------------------------------------

--
-- Table structure for table `patient_appoin`
--

CREATE TABLE `patient_appoin` (
  `id` int(11) NOT NULL,
  `frist_name` varchar(250) NOT NULL,
  `second_name` varchar(250) NOT NULL,
  `work_days_date` date NOT NULL,
  `work_hours_time` time NOT NULL,
  `doctor_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `patient_phone_table`
--

CREATE TABLE `patient_phone_table` (
  `id` int(11) NOT NULL,
  `patient_phone` varchar(11) NOT NULL,
  `relative_phone` varchar(11) NOT NULL,
  `patient_id` varchar(14) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `patient_time`
--

CREATE TABLE `patient_time` (
  `id` int(11) NOT NULL,
  `patient_id` varchar(14) NOT NULL,
  `doctor_id` int(11) NOT NULL,
  `work_days_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `patient_time`
--

INSERT INTO `patient_time` (`id`, `patient_id`, `doctor_id`, `work_days_id`) VALUES
(11, '30104221030845', 1, 163),
(13, '02369874521478', 16, 193),
(14, '12398745636985', 16, 192);

-- --------------------------------------------------------

--
-- Table structure for table `patient_users`
--

CREATE TABLE `patient_users` (
  `national_id` varchar(14) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `second_name` varchar(50) NOT NULL,
  `email` varchar(250) NOT NULL,
  `first_emergancy_number` varchar(20) NOT NULL,
  `second_emergancy_number` varchar(20) DEFAULT NULL,
  `password` varchar(250) NOT NULL,
  `gender` varchar(50) NOT NULL,
  `birthdate` varchar(10) NOT NULL,
  `weight` varchar(10) NOT NULL,
  `height` varchar(10) NOT NULL,
  `blood_type` varchar(5) NOT NULL,
  `blood_pressure` varchar(1) NOT NULL,
  `pregnant` varchar(1) NOT NULL,
  `liver` varchar(1) NOT NULL,
  `diabetes` varchar(1) NOT NULL,
  `doctor_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `patient_users`
--

INSERT INTO `patient_users` (`national_id`, `first_name`, `second_name`, `email`, `first_emergancy_number`, `second_emergancy_number`, `password`, `gender`, `birthdate`, `weight`, `height`, `blood_type`, `blood_pressure`, `pregnant`, `liver`, `diabetes`, `doctor_id`) VALUES
('02369874521478', 'farah ', 'ahmed', 'farah@gmail.com', '01116932581', '01085693214', 'f12345678', 'famle', '2000-09-05', '58', '165', 'A+', 'N', 'N', 'N', 'Y', 16),
('02385697418596', 'omar', 'mohamed', 'omar@gmail.com', '01119956324', '01096258314', 'o12345678', 'male', '1996-02-18', '90', '180', 'O+', 'Y', 'N', 'N', 'N', 16),
('09874563211478', 'hla', 'essam2', 'h9000@gmail.com', '01023698547', '01008523697', 'k123456', 'female', '2000-08-06', '66', '180', 'A+', 'N', 'N', 'N', 'N', 1),
('12398745636985', 'samy', 'adel', 'samy@gmail.com', '01023958741', '01096325874', '87654321', 'male ', '2002-06-09', '55', '165', 'A+', 'N', 'N', 'N', 'N', 16),
('14785236996325', 'jana', 'ahmed', 'jana20@gmail.com', '01125369741', '01023698547', '1234569', 'female', '2035-02-03', '55', '185', 'A+', 'N', 'N', 'N', 'N', 1),
('20303140102388', 'Bassandh', 'Hesham', 'email@gmail.com', '01152321090', '', '12345678', 'Female', '3/14/2002', '55', '165', 'A+', '0', '0', '0', '0', 1),
('30104221012377', 'rada', 'karm', 'hany23@gmail.com', '01236547596', '01006547898', '5896', 'male', '1989-05-03', '75.5', '170', '', '0', '1', '0', '1', 1),
('30104221030845', 'hla', 'essam', 'hla123@gmail.com', '01236547896', '01236547898', '123654', 'female', '2002-04-03', '75.5', '190', '1', '0', '1', '0', '1', 1),
('30104221030877', 'karem', 'adel', 'karem@gmail.com', '01115563895', '01115544895', '258963', 'male', '1989-05-03', '96', '180', 'A+', '0', '1', '0', '1', 8);

-- --------------------------------------------------------

--
-- Table structure for table `patient_vitals`
--

CREATE TABLE `patient_vitals` (
  `I_id` int(11) NOT NULL,
  `temperture` varchar(10) NOT NULL,
  `oxygen` varchar(10) NOT NULL,
  `heart_rate` varchar(10) NOT NULL,
  `blood_pressure` varchar(10) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `ID` varchar(14) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `patient_vitals`
--

INSERT INTO `patient_vitals` (`I_id`, `temperture`, `oxygen`, `heart_rate`, `blood_pressure`, `date`, `time`, `ID`) VALUES
(1, '35.5', '95', '120', '85.5', '2023-06-06', '05:00:00', '30104221012377'),
(2, '36.2', '90', '118', '85.3', '2023-06-05', '09:00:00', '30104221030845'),
(3, '33', '68', '90', '55.6', '2023-06-04', '10:00:00', '30104221012377'),
(4, '39.6', '80', '80', '85.5', '2023-06-04', '08:00:00', '30104221030845'),
(5, '33.2', '95', '105', '99', '2023-06-03', '03:00:00', '30104221012377'),
(6, '40', '80', '108', '200', '2023-04-12', '09:00:00', '30104221030877'),
(7, '35.5', '95', '90', '85.5', '2023-06-25', '08:00:00', '02369874521478'),
(8, '36.2', '96', '98', '97', '2023-06-25', '08:04:00', '02369874521478'),
(11, '36.5', '95', '97', '85.5', '2023-06-25', '08:05:00', '02369874521478'),
(12, '37.5', '97', '95', '88.5', '2023-06-25', '08:50:00', '02385697418596'),
(13, '34.2', '94', '98', '88.3', '2023-06-25', '09:00:00', '02385697418596'),
(14, '36.1', '96', '94', '88', '2023-06-25', '09:10:00', '02385697418596'),
(15, '39.5', '95', '105', '100', '2023-06-25', '09:30:00', '02385697418596'),
(16, '36.5', '95', '90', '85', '2023-06-25', '07:00:00', '12398745636985'),
(17, '36.8', '94', '92', '85.9', '2023-06-25', '07:20:00', '12398745636985'),
(18, '37.1', '93', '96', '94', '2023-06-25', '07:25:00', '12398745636985'),
(19, '39.8', '90', '106', '85.9', '2023-06-25', '07:50:00', '12398745636985');

-- --------------------------------------------------------

--
-- Table structure for table `symptoms`
--

CREATE TABLE `symptoms` (
  `id` int(11) NOT NULL,
  `stomach_ulcer` tinyint(1) NOT NULL,
  `heart_burn` tinyint(1) NOT NULL,
  `vomiting` tinyint(1) NOT NULL,
  `nausea` tinyint(1) NOT NULL,
  `diarrhea` tinyint(1) NOT NULL,
  `incontinence` tinyint(1) NOT NULL,
  `bloating` tinyint(1) NOT NULL,
  `constipation` tinyint(1) NOT NULL,
  `bleeding` tinyint(1) NOT NULL,
  `cough` tinyint(1) NOT NULL,
  `sneezing` tinyint(1) NOT NULL,
  `stuffy_nose` tinyint(1) NOT NULL,
  `sore_throat` tinyint(1) NOT NULL,
  `breathlessness` tinyint(1) NOT NULL,
  `headache` tinyint(1) NOT NULL,
  `dizziness` tinyint(1) NOT NULL,
  `body_ahes` tinyint(1) NOT NULL,
  `skin_irritation` tinyint(1) NOT NULL,
  `date` date NOT NULL,
  `patient_id` varchar(14) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `symptoms`
--

INSERT INTO `symptoms` (`id`, `stomach_ulcer`, `heart_burn`, `vomiting`, `nausea`, `diarrhea`, `incontinence`, `bloating`, `constipation`, `bleeding`, `cough`, `sneezing`, `stuffy_nose`, `sore_throat`, `breathlessness`, `headache`, `dizziness`, `body_ahes`, `skin_irritation`, `date`, `patient_id`) VALUES
(1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, '2023-06-23', '02369874521478'),
(2, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, '2023-06-24', '02369874521478'),
(4, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, '2023-06-25', '02369874521478'),
(5, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, '2023-06-23', '02385697418596'),
(6, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, '2023-06-24', '02385697418596'),
(7, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, '2023-06-25', '02385697418596'),
(8, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, '2023-06-23', '12398745636985'),
(9, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, '2023-06-24', '12398745636985'),
(10, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, '2023-06-25', '12398745636985'),
(11, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '2023-07-19', '02369874521478'),
(12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, '2024-02-01', '02369874521478'),
(13, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, '2024-02-02', '02369874521478'),
(14, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, '2024-02-03', '12398745636985'),
(15, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, '2024-02-04', '02369874521478');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `doctor_users`
--
ALTER TABLE `doctor_users`
  ADD PRIMARY KEY (`doctor_id`),
  ADD UNIQUE KEY `medical-id` (`medical_id`),
  ADD KEY `medical-id_2` (`medical_id`),
  ADD KEY `doctor-id` (`doctor_id`),
  ADD KEY `doctor-hospital-id_index` (`hospital_clinic_id`);

--
-- Indexes for table `hospital_clinicals`
--
ALTER TABLE `hospital_clinicals`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code` (`code`),
  ADD KEY `code_2` (`code`);

--
-- Indexes for table `patient_appoin`
--
ALTER TABLE `patient_appoin`
  ADD PRIMARY KEY (`id`),
  ADD KEY `frist-name` (`frist_name`),
  ADD KEY `second-name` (`second_name`),
  ADD KEY `work-days-date` (`work_days_date`),
  ADD KEY `work_hours_time` (`work_hours_time`),
  ADD KEY `doctor_id` (`doctor_id`);

--
-- Indexes for table `patient_phone_table`
--
ALTER TABLE `patient_phone_table`
  ADD PRIMARY KEY (`id`),
  ADD KEY `patient-phone-table_ibfk_1` (`patient_id`);

--
-- Indexes for table `patient_time`
--
ALTER TABLE `patient_time`
  ADD PRIMARY KEY (`id`),
  ADD KEY `patient-time_ibfk_1` (`patient_id`),
  ADD KEY `doctor_id` (`doctor_id`),
  ADD KEY `work_days_id` (`work_days_id`);

--
-- Indexes for table `patient_users`
--
ALTER TABLE `patient_users`
  ADD PRIMARY KEY (`national_id`),
  ADD UNIQUE KEY `national-id` (`national_id`),
  ADD KEY `doctor-id` (`doctor_id`),
  ADD KEY `doctor-id_2` (`doctor_id`),
  ADD KEY `first_name` (`first_name`),
  ADD KEY `first_name_2` (`first_name`),
  ADD KEY `second_name` (`second_name`);

--
-- Indexes for table `patient_vitals`
--
ALTER TABLE `patient_vitals`
  ADD PRIMARY KEY (`I_id`),
  ADD KEY `patient-history_ibfk_1` (`ID`);

--
-- Indexes for table `symptoms`
--
ALTER TABLE `symptoms`
  ADD PRIMARY KEY (`id`),
  ADD KEY `symptoms_ibfk_1` (`patient_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `doctor_users`
--
ALTER TABLE `doctor_users`
  MODIFY `doctor_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `hospital_clinicals`
--
ALTER TABLE `hospital_clinicals`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `patient_appoin`
--
ALTER TABLE `patient_appoin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `patient_phone_table`
--
ALTER TABLE `patient_phone_table`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `patient_time`
--
ALTER TABLE `patient_time`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `patient_vitals`
--
ALTER TABLE `patient_vitals`
  MODIFY `I_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `symptoms`
--
ALTER TABLE `symptoms`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `doctor_users`
--
ALTER TABLE `doctor_users`
  ADD CONSTRAINT `doctor-hospital-id_index` FOREIGN KEY (`hospital_clinic_id`) REFERENCES `hospital_clinicals` (`code`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `patient_appoin`
--
ALTER TABLE `patient_appoin`
  ADD CONSTRAINT `patient_appoin_ibfk_1` FOREIGN KEY (`doctor_id`) REFERENCES `doctor_users` (`doctor_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `patient_phone_table`
--
ALTER TABLE `patient_phone_table`
  ADD CONSTRAINT `patient_phone_table_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patient_users` (`national_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `patient_time`
--
ALTER TABLE `patient_time`
  ADD CONSTRAINT `patient_time_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patient_users` (`national_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `patient_users`
--
ALTER TABLE `patient_users`
  ADD CONSTRAINT `patient_users_ibfk_1` FOREIGN KEY (`doctor_id`) REFERENCES `doctor_users` (`doctor_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `patient_vitals`
--
ALTER TABLE `patient_vitals`
  ADD CONSTRAINT `patient_vitals_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `patient_users` (`national_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `symptoms`
--
ALTER TABLE `symptoms`
  ADD CONSTRAINT `symptoms_ibfk_1` FOREIGN KEY (`patient_id`) REFERENCES `patient_users` (`national_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

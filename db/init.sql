/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE DATABASE IF NOT EXISTS `aurelia_db` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `aurelia_db`;

CREATE TABLE IF NOT EXISTS `admin` (
  `barcode` varchar(50) NOT NULL,
  `passkey` varchar(45) NOT NULL,
  `user_details_id` int NOT NULL,
  PRIMARY KEY (`barcode`),
  KEY `fk_admin_user_details_idx` (`user_details_id`),
  CONSTRAINT `fk_admin_user_details` FOREIGN KEY (`user_details_id`) REFERENCES `user_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

INSERT INTO `admin` (`barcode`, `passkey`, `user_details_id`) VALUES
	('15464213797_AD', 'user3@example.com_0700000003', 3);

CREATE TABLE IF NOT EXISTS `channeling` (
  `id` int NOT NULL AUTO_INCREMENT,
  `patient_id` int NOT NULL,
  `doctor_barcode` varchar(50) NOT NULL,
  `doctor_has_schedule_id` int NOT NULL,
  `time` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_channeling_patient1_idx` (`patient_id`),
  KEY `fk_channeling_doctor1_idx` (`doctor_barcode`),
  KEY `fk_channeling_doctor_has_schedule1_idx` (`doctor_has_schedule_id`),
  CONSTRAINT `fk_channeling_doctor1` FOREIGN KEY (`doctor_barcode`) REFERENCES `doctor` (`barcode`),
  CONSTRAINT `fk_channeling_doctor_has_schedule1` FOREIGN KEY (`doctor_has_schedule_id`) REFERENCES `doctor_has_schedule` (`id`),
  CONSTRAINT `fk_channeling_patient1` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

INSERT INTO `channeling` (`id`, `patient_id`, `doctor_barcode`, `doctor_has_schedule_id`, `time`) VALUES
	(1, 1, '4564313487_DR', 1, '10.00 am'),
	(2, 2, '4564313487_DR', 1, '11.00 am');

CREATE TABLE IF NOT EXISTS `doctor` (
  `barcode` varchar(50) NOT NULL,
  `user_details_id` int NOT NULL,
  `doctor_type_id` int NOT NULL,
  PRIMARY KEY (`barcode`),
  KEY `fk_doctor_user_details1_idx` (`user_details_id`),
  KEY `fk_doctor_doctor_type1_idx` (`doctor_type_id`),
  CONSTRAINT `fk_doctor_doctor_type1` FOREIGN KEY (`doctor_type_id`) REFERENCES `doctor_type` (`id`),
  CONSTRAINT `fk_doctor_user_details1` FOREIGN KEY (`user_details_id`) REFERENCES `user_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

INSERT INTO `doctor` (`barcode`, `user_details_id`, `doctor_type_id`) VALUES
	('1724732344903_DR', 6, 2),
	('1724742386578_DR', 7, 4),
	('4564313487_DR', 2, 1);

CREATE TABLE IF NOT EXISTS `doctor_has_schedule` (
  `id` int NOT NULL AUTO_INCREMENT,
  `doctor_barcode` varchar(50) NOT NULL,
  `schedule_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_doctor_has_schedule_schedule1_idx` (`schedule_id`),
  KEY `fk_doctor_has_schedule_doctor1_idx` (`doctor_barcode`),
  CONSTRAINT `fk_doctor_has_schedule_doctor1` FOREIGN KEY (`doctor_barcode`) REFERENCES `doctor` (`barcode`),
  CONSTRAINT `fk_doctor_has_schedule_schedule1` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

INSERT INTO `doctor_has_schedule` (`id`, `doctor_barcode`, `schedule_id`) VALUES
	(1, '4564313487_DR', 1);

CREATE TABLE IF NOT EXISTS `doctor_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

INSERT INTO `doctor_type` (`id`, `type`) VALUES
	(1, 'Cardeologist'),
	(2, 'Neurologist'),
	(3, 'Psychiatrist'),
	(4, 'Oncologist'),
	(5, 'Anesthesiologist');

CREATE TABLE IF NOT EXISTS `drug_cat` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;

INSERT INTO `drug_cat` (`id`, `category`) VALUES
	(1, 'Analgesics'),
	(2, 'Anesthetics'),
	(3, 'Anti-addiction agents'),
	(4, 'Antibacterials'),
	(5, 'Anticonvulsants'),
	(6, 'Antidementia agents');

CREATE TABLE IF NOT EXISTS `grn` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `supplier_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_grn_supplier1_idx` (`supplier_id`),
  CONSTRAINT `fk_grn_supplier1` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;

INSERT INTO `grn` (`id`, `date`, `supplier_id`) VALUES
	(1, '2024-08-31 15:00:24', 1),
	(14, '2024-09-01 09:18:04', 1),
	(20, '2024-09-01 09:54:56', 1);

CREATE TABLE IF NOT EXISTS `grn_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `brand` varchar(45) NOT NULL,
  `drug_cat_id` int NOT NULL,
  `grn_id` int NOT NULL,
  `qty` int NOT NULL,
  `unit_price` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_grn_item_drug_cat1_idx` (`drug_cat_id`),
  KEY `fk_grn_item_grn1_idx` (`grn_id`),
  CONSTRAINT `fk_grn_item_drug_cat1` FOREIGN KEY (`drug_cat_id`) REFERENCES `drug_cat` (`id`),
  CONSTRAINT `fk_grn_item_grn1` FOREIGN KEY (`grn_id`) REFERENCES `grn` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

INSERT INTO `grn_item` (`id`, `brand`, `drug_cat_id`, `grn_id`, `qty`, `unit_price`) VALUES
	(1, 'Sample Brand A', 3, 1, 30, 100),
	(2, 'Sample Brand B', 1, 1, 12, 500),
	(3, 'Sample Brand C', 2, 1, 100, 200),
	(4, 'Sample Brand D', 1, 14, 12, 1000),
	(5, 'Sample Brand E', 1, 20, 10, 200);

CREATE TABLE IF NOT EXISTS `invoice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `total` double NOT NULL,
  `pharmacist_barcode` varchar(50) NOT NULL,
  `patient_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_invoice_pharmacist1_idx` (`pharmacist_barcode`),
  KEY `fk_invoice_patient1_idx` (`patient_id`),
  CONSTRAINT `fk_invoice_patient1` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`),
  CONSTRAINT `fk_invoice_pharmacist1` FOREIGN KEY (`pharmacist_barcode`) REFERENCES `pharmacist` (`barcode`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb3;

INSERT INTO `invoice` (`id`, `date`, `total`, `pharmacist_barcode`, `patient_id`) VALUES
	(3, '2024-09-01 09:56:47', 4300, '1724908499521_PH', 1),
	(4, '2024-08-31 10:06:21', 10000, '1724908499521_PH', 1),
	(5, '2024-08-31 10:47:34', 1000, '1724908499521_PH', 1),
	(11, '2024-08-31 10:42:12', 7000, '1724908499521_PH', 1),
	(17, '2024-08-31 10:44:30', 4000, '1724908499521_PH', 1),
	(54, '2024-08-31 10:38:32', 8000, '1724908499521_PH', 1),
	(57, '2024-08-31 10:35:34', 3000, '1724908499521_PH', 1),
	(60, '2024-08-31 10:32:10', 5000, '1724908499521_PH', 2);

CREATE TABLE IF NOT EXISTS `invoice_items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `stock_id` int NOT NULL,
  `qty` int NOT NULL,
  `invoice_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_invoice_items_stock1_idx` (`stock_id`),
  KEY `fk_invoice_items_invoice1_idx` (`invoice_id`),
  CONSTRAINT `fk_invoice_items_invoice1` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`),
  CONSTRAINT `fk_invoice_items_stock1` FOREIGN KEY (`stock_id`) REFERENCES `stock` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb3;

INSERT INTO `invoice_items` (`id`, `stock_id`, `qty`, `invoice_id`) VALUES
	(5, 2, 2, 4),
	(6, 1, 6, 4),
	(7, 1, 2, 60),
	(8, 3, 1, 60),
	(9, 3, 1, 57),
	(10, 2, 1, 54),
	(11, 3, 2, 54),
	(12, 2, 2, 11),
	(13, 3, 1, 11),
	(14, 2, 1, 17),
	(15, 1, 2, 17),
	(16, 1, 1, 5),
	(17, 2, 2, 3),
	(18, 4, 1, 3);

CREATE TABLE IF NOT EXISTS `it_management` (
  `barcode` varchar(50) NOT NULL,
  `user_details_id` int NOT NULL,
  PRIMARY KEY (`barcode`),
  KEY `fk_it_management_user_details1_idx` (`user_details_id`),
  CONSTRAINT `fk_it_management_user_details1` FOREIGN KEY (`user_details_id`) REFERENCES `user_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

INSERT INTO `it_management` (`barcode`, `user_details_id`) VALUES
	('5654351316_IM', 5);

CREATE TABLE IF NOT EXISTS `nurses` (
  `barcode` varchar(50) NOT NULL,
  `fname` varchar(25) NOT NULL,
  `lname` varchar(25) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mobile` varchar(10) NOT NULL,
  `registered_date` datetime NOT NULL,
  `address` text NOT NULL,
  `status_id` int NOT NULL,
  PRIMARY KEY (`barcode`),
  KEY `fk_nurses_status1_idx` (`status_id`),
  CONSTRAINT `fk_nurses_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

INSERT INTO `nurses` (`barcode`, `fname`, `lname`, `email`, `mobile`, `registered_date`, `address`, `status_id`) VALUES
	('1724924422983_NU', 'Sample', 'Nurse1', 'nurse1@example.com', '0700000201', '2024-08-29 15:10:22', 'Sample address, Sample City', 1),
	('1724924500675_NU', 'Sample', 'Nurse2', 'nurse2@example.com', '0700000202', '2024-08-29 15:11:40', 'Sample address, Sample City', 1);

CREATE TABLE IF NOT EXISTS `operation` (
  `barcode` varchar(50) NOT NULL,
  `doctor_barcode` varchar(50) NOT NULL,
  `patient_id` int NOT NULL,
  `date` datetime NOT NULL,
  `price` double NOT NULL,
  `payment_status_id` int NOT NULL,
  PRIMARY KEY (`barcode`),
  KEY `fk_operation_doctor1_idx` (`doctor_barcode`),
  KEY `fk_operation_patient1_idx` (`patient_id`),
  KEY `fk_operation_payment_status1_idx` (`payment_status_id`),
  CONSTRAINT `fk_operation_doctor1` FOREIGN KEY (`doctor_barcode`) REFERENCES `doctor` (`barcode`),
  CONSTRAINT `fk_operation_patient1` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`),
  CONSTRAINT `fk_operation_payment_status1` FOREIGN KEY (`payment_status_id`) REFERENCES `payment_status` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

INSERT INTO `operation` (`barcode`, `doctor_barcode`, `patient_id`, `date`, `price`, `payment_status_id`) VALUES
	('1724669262375_OP', '4564313487_DR', 1, '2024-08-26 16:18:46', 50000, 3),
	('1724751182585_OP', '1724732344903_DR', 2, '2024-08-27 15:03:21', 45000, 3);

CREATE TABLE IF NOT EXISTS `op_invoice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `operation_barcode` varchar(50) NOT NULL,
  `reception_barcode` varchar(50) NOT NULL,
  `paid_price` double NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_op_invoice_operation1_idx` (`operation_barcode`),
  KEY `fk_op_invoice_reception1_idx` (`reception_barcode`),
  CONSTRAINT `fk_op_invoice_operation1` FOREIGN KEY (`operation_barcode`) REFERENCES `operation` (`barcode`),
  CONSTRAINT `fk_op_invoice_reception1` FOREIGN KEY (`reception_barcode`) REFERENCES `reception` (`barcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


CREATE TABLE IF NOT EXISTS `patient` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fname` varchar(25) NOT NULL,
  `lname` varchar(25) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mobile` varchar(10) NOT NULL,
  `address` text NOT NULL,
  `patient_type_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_patient_patient_type1_idx` (`patient_type_id`),
  CONSTRAINT `fk_patient_patient_type1` FOREIGN KEY (`patient_type_id`) REFERENCES `patient_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

INSERT INTO `patient` (`id`, `fname`, `lname`, `email`, `mobile`, `address`, `patient_type_id`) VALUES
	(1, 'Sample', 'Patient1', 'patient1@example.com', '0700000101', 'Sample address, Sample City', 1),
	(2, 'Sample', 'Patient2', 'patient2@example.com', '0700000102', 'Sample address, Sample City', 1);

CREATE TABLE IF NOT EXISTS `patient_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

INSERT INTO `patient_type` (`id`, `type`) VALUES
	(1, 'Channeling'),
	(2, 'Emergency');

CREATE TABLE IF NOT EXISTS `payment_status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

INSERT INTO `payment_status` (`id`, `type`) VALUES
	(1, 'Paid'),
	(2, 'Pending'),
	(3, 'Unpaid');

CREATE TABLE IF NOT EXISTS `pharmacist` (
  `barcode` varchar(50) NOT NULL,
  `user_details_id` int NOT NULL,
  PRIMARY KEY (`barcode`),
  KEY `fk_pharmacist_user_details1_idx` (`user_details_id`),
  CONSTRAINT `fk_pharmacist_user_details1` FOREIGN KEY (`user_details_id`) REFERENCES `user_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

INSERT INTO `pharmacist` (`barcode`, `user_details_id`) VALUES
	('1724908499521_PH', 9),
	('1724908923322_PH', 10);

CREATE TABLE IF NOT EXISTS `reception` (
  `barcode` varchar(50) NOT NULL,
  `user_details_id` int NOT NULL,
  PRIMARY KEY (`barcode`),
  KEY `fk_reception_user_details1_idx` (`user_details_id`),
  CONSTRAINT `fk_reception_user_details1` FOREIGN KEY (`user_details_id`) REFERENCES `user_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

INSERT INTO `reception` (`barcode`, `user_details_id`) VALUES
	('12354568789_RE', 1),
	('1724903607295_RE', 8);

CREATE TABLE IF NOT EXISTS `schedule` (
  `id` int NOT NULL AUTO_INCREMENT,
  `start_time` varchar(25) NOT NULL,
  `end_time` varchar(25) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

INSERT INTO `schedule` (`id`, `start_time`, `end_time`, `date`) VALUES
	(1, '8.00 am', '5.00 pm', '2024-08-23');

CREATE TABLE IF NOT EXISTS `status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

INSERT INTO `status` (`id`, `status`) VALUES
	(1, 'ACTIVE'),
	(2, 'DEACTIVE'),
	(3, 'RESIGN');

CREATE TABLE IF NOT EXISTS `stock` (
  `id` int NOT NULL AUTO_INCREMENT,
  `selling_price` double NOT NULL,
  `grn_item_id` int NOT NULL,
  `qty` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_stock_grn_item1_idx` (`grn_item_id`),
  CONSTRAINT `fk_stock_grn_item1` FOREIGN KEY (`grn_item_id`) REFERENCES `grn_item` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

INSERT INTO `stock` (`id`, `selling_price`, `grn_item_id`, `qty`) VALUES
	(1, 1000, 3, 9),
	(2, 2000, 1, 0),
	(3, 3000, 1, 2),
	(4, 300, 4, 9),
	(5, 1000, 5, 10);

CREATE TABLE IF NOT EXISTS `supplier` (
  `id` int NOT NULL AUTO_INCREMENT,
  `company` varchar(45) NOT NULL,
  `hotline` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

INSERT INTO `supplier` (`id`, `company`, `hotline`) VALUES
	(1, 'Merck', '(845) 354-9912'),
	(2, 'RiseShine', '(248) 434-5508'),
	(3, 'Goalcraft', '(719) 266-2837');

CREATE TABLE IF NOT EXISTS `theater` (
  `barcode` varchar(50) NOT NULL,
  `user_details_id` int NOT NULL,
  PRIMARY KEY (`barcode`),
  KEY `fk_theater_user_details1_idx` (`user_details_id`),
  CONSTRAINT `fk_theater_user_details1` FOREIGN KEY (`user_details_id`) REFERENCES `user_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

INSERT INTO `theater` (`barcode`, `user_details_id`) VALUES
	('3790485999_TH', 4),
	('1724920834621_TH', 11);

CREATE TABLE IF NOT EXISTS `user_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fname` varchar(25) NOT NULL,
  `lname` varchar(25) NOT NULL,
  `mobile` varchar(10) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(25) NOT NULL,
  `registered_date` datetime NOT NULL,
  `address` text NOT NULL,
  `status_id` int NOT NULL,
  `user_type_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_details_status1_idx` (`status_id`),
  KEY `fk_user_details_user_type1_idx` (`user_type_id`),
  CONSTRAINT `fk_user_details_status1` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`),
  CONSTRAINT `fk_user_details_user_type1` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;

INSERT INTO `user_details` (`id`, `fname`, `lname`, `mobile`, `email`, `password`, `registered_date`, `address`, `status_id`, `user_type_id`) VALUES
	(1, 'Sample', 'User1', '0700000001', 'user1@example.com', 'password', '2024-08-23 23:27:28', 'Sample address, Sample City', 1, 3),
	(2, 'Sample', 'User2', '0700000002', 'user2@example.com', 'password', '2024-08-23 23:31:02', 'Sample address, Sample City', 1, 2),
	(3, 'Sample', 'User3', '0700000003', 'user3@example.com', 'password', '2024-08-23 23:33:32', 'Sample address, Sample City', 1, 1),
	(4, 'Sample', 'User4', '0700000004', 'user4@example.com', 'password', '2024-08-26 16:15:44', 'Sample address, Sample City', 1, 5),
	(5, 'Sample', 'User5', '0700000005', 'user5@example.com', 'password', '2024-08-26 18:00:40', 'Sample address, Sample City', 1, 4),
	(6, 'Sample', 'User6', '0700000006', 'user6@example.com', 'password', '2024-08-27 09:49:04', 'Sample address, Sample City', 2, 2),
	(7, 'Sample', 'User7', '0700000007', 'user7@example.com', 'password', '2024-08-27 12:36:26', 'Sample address, Sample City', 2, 2),
	(8, 'Sample', 'User8', '0700000008', 'user8@example.com', 'password', '2024-08-29 09:23:27', 'Sample address, Sample City', 1, 3),
	(9, 'Sample', 'User9', '0700000009', 'user9@example.com', 'password', '2024-08-29 10:44:59', 'Sample address, Sample City', 1, 6),
	(10, 'Sample', 'User10', '0700000010', 'user10@example.com', 'password', '2024-08-29 10:52:03', 'Sample address, Sample City', 1, 6),
	(11, 'Sample', 'User11', '0700000011', 'user11@example.com', 'password', '2024-08-29 14:10:34', 'Sample address, Sample City', 1, 5);

CREATE TABLE IF NOT EXISTS `user_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;

INSERT INTO `user_type` (`id`, `type`) VALUES
	(1, 'Admin'),
	(2, 'Doctor'),
	(3, 'Reception'),
	(4, 'IT Management'),
	(5, 'Theater'),
	(6, 'Pharmacist');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
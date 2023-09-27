USE skillbox;
SET NAMES utf8mb4;

DROP TABLE IF EXISTS `LinkedPurchaseList`;
SET character_set_client = utf8mb4;

CREATE TABLE `LinkedPurchaseList` (
  `student_id` int unsigned NOT NULL,
  `course_id` int unsigned NOT NULL,
  `student_name` varchar(500) DEFAULT NULL,
  `course_name` varchar(500) DEFAULT NULL,
  `price` int DEFAULT NULL,
  `subscription_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`student_id`,`course_id`),
  KEY `lpl_course_constr` (`course_id`),
  CONSTRAINT `lpl_course_constr` FOREIGN KEY (`course_id`) REFERENCES `Courses` (`id`),
  CONSTRAINT `lpl_student_constr` FOREIGN KEY (`student_id`) REFERENCES `Students` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

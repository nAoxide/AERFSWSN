-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Aug 24, 2020 at 12:17 PM
-- Server version: 8.0.17
-- PHP Version: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `exams`
--

-- --------------------------------------------------------

--
-- Table structure for table `attempt`
--

CREATE TABLE `attempt` (
  `attempt_id` int(11) NOT NULL,
  `student_id` varchar(50) NOT NULL,
  `course_id` varchar(50) NOT NULL,
  `exam_id` varchar(50) NOT NULL,
  `question_id` int(11) NOT NULL,
  `true_answer` int(11) NOT NULL,
  `student_answer` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `course_id` varchar(50) NOT NULL,
  `course_name` varchar(50) NOT NULL,
  `section_id` varchar(50) NOT NULL,
  `teacher_id` varchar(50) NOT NULL,
  `semester_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`course_id`, `course_name`, `section_id`, `teacher_id`, `semester_id`) VALUES
('COMP01', 'ICDL', 'IT', '123456', 2),
('COMP02', 'PROGRAMMING LANGUAGE 1', 'IT', '12345', 2),
('MATH01', 'Calculus A', 'SCI', '123456', 2);

-- --------------------------------------------------------

--
-- Table structure for table `exam`
--

CREATE TABLE `exam` (
  `exam_id` varchar(50) NOT NULL,
  `course_id` varchar(50) NOT NULL,
  `exam_date` date DEFAULT NULL,
  `exam_time` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `exam`
--

INSERT INTO `exam` (`exam_id`, `course_id`, `exam_date`, `exam_time`) VALUES
('COMP01_1', 'COMP01', NULL, NULL),
('COMP02_1', 'COMP02', NULL, NULL),
('MATH01_1', 'MATH01', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `user_id` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`user_id`, `password`) VALUES
('100', '100'),
('200', '200');

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE `question` (
  `question_id` int(11) NOT NULL,
  `question_text` varchar(500) NOT NULL,
  `option1` varchar(200) NOT NULL,
  `option2` varchar(200) NOT NULL,
  `option3` varchar(200) NOT NULL,
  `true_answer` int(11) NOT NULL,
  `exam_id` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`question_id`, `question_text`, `option1`, `option2`, `option3`, `true_answer`, `exam_id`) VALUES
(1, 'ما هو الاسم العام للأجهزة المتصلة بوحدة المعالجة المركزية و يتحكم بها المعالج', 'لوحة المفاتيح', 'الشاشة', 'الطرفيات', 3, 'COMP01_1'),
(2, 'أي العوامل التالية لها أكبر تأثير في تحسين أداء الكمبيوتر الذي يعمل ببطء عند تشغيل بعض التطبيقات', 'زيادة حجم الشاشة', 'إضافة قرص مضغوط', 'إضافة المزيد من الذاكرة RAM', 3, 'COMP01_1'),
(3, 'تقاس سرعة وحدة المعالجة المركزية بِ', 'بت في الثانية', 'ميجاهرتز', 'كيلوبايت', 2, 'COMP01_1'),
(4, 'ما نوع ذاكرة التخزين المستخدمة عند الحاجة إلى تخزين بيانات بشكل دائم', 'ذاكرة الوصول العشوائي', 'القرص الصلب', 'ذاكرة القراءة فقط', 2, 'COMP01_1'),
(5, 'كم بت يوجد في البايت الواحد', '2', '4', '8', 3, 'COMP01_1'),
(6, 'أي من وسائط التخزين التالية يمتلك أكبر سعة تخزينية', 'القرص الصلب', 'القرص المرن', 'القرص المضغوط', 1, 'COMP01_1'),
(7, 'أي مما يلي يستدعي تهيئة القرص', 'لحماية البيانات في القرص', 'البحث عن الملفات في القرص', 'تحضير القرص لتخزين الملفات', 3, 'COMP01_1'),
(8, 'أي المهام التالية لا يستطيع الحاسوب أداءها', 'تنسيق الملفات', 'تأليف المقالات الصحفية', 'تحرير الصور', 2, 'COMP01_1'),
(9, 'أي مما يلي يعمل تلقائيًا بعد توقفك عن العمل على الحاسوب لمدة تحددها بنفسك', 'لوحة المفاتيح', 'شاشة التوقف', 'الفأرة', 2, 'COMP01_1'),
(10, 'تُستخدم كلمة المرور لِ', 'تسهيل الوصول لمعلومات الحاسوب', 'حماية الحاسوب من المستخدمين غير المصرح لهم', 'تسهيل اتصال الحاسوب بالشبكة', 2, 'COMP01_1');

-- --------------------------------------------------------

--
-- Table structure for table `register`
--

CREATE TABLE `register` (
  `student_id` varchar(50) NOT NULL,
  `semester_id` int(11) NOT NULL,
  `course_id` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `register`
--

INSERT INTO `register` (`student_id`, `semester_id`, `course_id`) VALUES
('100', 2, 'COMP01'),
('100', 2, 'COMP02'),
('200', 2, 'MATH01');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `student_id` varchar(50) NOT NULL,
  `student_name` varchar(50) NOT NULL,
  `department` varchar(50) NOT NULL,
  `college` varchar(50) NOT NULL,
  `health_status` varchar(50) NOT NULL,
  `level` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`student_id`, `student_name`, `department`, `college`, `health_status`, `level`) VALUES
('100', 'Sami', 'IT', 'IT', 'Visually impaired', 2),
('200', 'Ahmad', 'IT', 'IT', 'Visually impaired', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `attempt`
--
ALTER TABLE `attempt`
  ADD PRIMARY KEY (`attempt_id`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`course_id`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD UNIQUE KEY `user_id` (`user_id`);

--
-- Indexes for table `question`
--
ALTER TABLE `question`
  ADD PRIMARY KEY (`question_id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`student_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `attempt`
--
ALTER TABLE `attempt`
  MODIFY `attempt_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT for table `question`
--
ALTER TABLE `question`
  MODIFY `question_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

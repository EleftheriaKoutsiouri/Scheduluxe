CREATE TABLE `activitytypes` (
  `TypeID` int(11) NOT NULL AUTO_INCREMENT,
  `TypeName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`TypeID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `budgettype` (
  `BudgetID` int(11) NOT NULL AUTO_INCREMENT,
  `BudgetName` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`BudgetID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `destinations` (
  `DestinationID` int(11) NOT NULL AUTO_INCREMENT,
  `DestinationName` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DestinationPhotoPath` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DestinationDetails` text COLLATE utf8_unicode_ci,
  PRIMARY KEY (`DestinationID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;





CREATE TABLE `activities` (
  `ActivityID` int(11) NOT NULL AUTO_INCREMENT,
  `ActivityName` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DestinationID` int(11) DEFAULT NULL,
  `Details` text COLLATE utf8_unicode_ci,
  `TypeID` int(11) DEFAULT NULL,
  `BudgetID` int(11) DEFAULT NULL,
  `StartTime` time DEFAULT NULL,
  `EndTime` time DEFAULT NULL,
  PRIMARY KEY (`ActivityID`),
  KEY `DestinationID` (`DestinationID`),
  KEY `TypeID` (`TypeID`),
  KEY `BudgetID` (`BudgetID`),
  CONSTRAINT `activities_ibfk_1` FOREIGN KEY (`DestinationID`) REFERENCES `destinations` (`DestinationID`),
  CONSTRAINT `activities_ibfk_2` FOREIGN KEY (`TypeID`) REFERENCES `activitytypes` (`TypeID`),
  CONSTRAINT `activities_ibfk_3` FOREIGN KEY (`BudgetID`) REFERENCES `budgettype` (`BudgetID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;




CREATE TABLE `travelers` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `Password` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `Email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `FirstName` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LastName` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `OriginCountry` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `UserName` (`UserName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE `schedules` (
  `scheduleId` int(11) NOT NULL,
  `activityId` int(11) NOT NULL,
  `day` int(11) NOT NULL,
  `timeSlot` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`scheduleId`,`day`,`timeSlot`),
  KEY `activityId` (`activityId`),
  CONSTRAINT `schedules_ibfk_1` FOREIGN KEY (`activityId`) REFERENCES `activities` (`ActivityID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;



CREATE TABLE `schedulesByTraveler` (
  `UserID` int(11) NOT NULL,
  `scheduleId` int(11) NOT NULL,
  `savedDate` DATE NOT NULL,
  `comment` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `rating` tinyint(1) DEFAULT NULL CHECK (`rating` IS NULL OR `rating` BETWEEN 1 AND 5),
  PRIMARY KEY (`UserID`, `scheduleId`),
  CONSTRAINT `user_schedules_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `travelers` (`UserID`) ON DELETE CASCADE,
  CONSTRAINT `user_schedules_ibfk_2` FOREIGN KEY (`scheduleId`) REFERENCES `schedules` (`scheduleId`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
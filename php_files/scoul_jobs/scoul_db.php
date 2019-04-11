<?php

include 'db_connect.php';


$query = 'CREATE DATABASE IF NOT EXISTS scoul_db';

if($db->query($query) == TRUE){
	echo 'Database created Successfully';

//create tables.

include 'config.php';

	mysqli_select_db($db, DB_NAME) or die(mysqli_error($db));


	$query = 'CREATE TABLE IF NOT EXISTS users(
	             id     INTEGER UNSIGNED   NOT NULL AUTO_INCREMENT,
	             firstname           VARCHAR(55)       NOT NULL,
				 lastname            VARCHAR(55)       NOT NULL,
				 dob                 VARCHAR(55)       NOT NULL,
				 nationality         VARCHAR(55)       NOT NULL,
				 email               VARCHAR(55)       NOT NULL,
				 phone               VARCHAR(55)       NOT NULL,
				 education           VARCHAR(55)       NOT NULL,
				 address             VARCHAR(55)       NOT NULL,
				 user_password       VARCHAR(255)      NOT NULL,
				 access_level        TINYINT NOT NULL DEFAULT 0,
	             created_at          TIMESTAMP,
	             updated_at          TIMESTAMP,	 
	             PRIMARY KEY(id))
	             ENGINE=MyISAM';

	 if($db->query($query) == TRUE){
	 	echo 'Created Users table successfully.';
	 }else{
	 	echo 'Server Error while Creating Users Table:

	 	 '. $db->error;
	 }



	$query = 'CREATE TABLE IF NOT EXISTS jobs(
	             job_id     INTEGER UNSIGNED   NOT NULL AUTO_INCREMENT,
	             organisation_name           VARCHAR(255)       NOT NULL,
	             job_title          VARCHAR(255)       NOT NULL,
	             job_category    VARCHAR(255)       NOT NULL,
				 job_description    MEDIUMTEXT       NOT NULL,
				 job_qualifications    MEDIUMTEXT       NOT NULL,
				 job_requirements    MEDIUMTEXT       NOT NULL,
	             created_at          TIMESTAMP,
	             updated_at          TIMESTAMP,	 
	             PRIMARY KEY(job_id))
	             ENGINE=MyISAM';

	 if($db->query($query) == TRUE){
	 	echo 'Created Job Table successfully.';
	 }else{
	 	echo 'Server Error while Creating Job Table:

	 	 '. $db->error;
	 }

	 }else{
		 echo 'DATABASE NOT CREATED';
	 } 
?>
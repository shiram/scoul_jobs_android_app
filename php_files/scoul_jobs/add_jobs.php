<?php
error_reporting(E_ERROR);

$org_name = $_REQUEST['org_name'];
$title = $_REQUEST['title'];
$category = $_REQUEST['category'];
$description = $_REQUEST['description'];
$qualifications = $_REQUEST['qualifications'];
$requirements = $_REQUEST['requirements'];

/*
$user_id = (int)"1";
$item_name = "Perfume";
$item_price = "20000";
$item_desc = "Uploading images to our server is a very frequently used thing. In most of the apps, we need user avatar, i.e. user profile image. So here is Android Upload Image to Server Tutorial.";
$item_cat = "Cosmetics";
$country = "Uganda";
$city = "Kampala";
$address = "Wandegeya";
$lat = "-2.11";
$lng = "6.99";
$item_image = "jjj.png";
$item_image_en = "hello";
*/

include 'db_connect.php';
include 'config.php';
mysqli_select_db($db, DB_NAME) or die(mysqli_error($db));


$json = array();

$query = 'INSERT INTO jobs(organisation_name, job_title, job_category, job_description, job_qualifications, job_requirements) 
                      VALUES ("'.$org_name.'", "'.$title.'","'.$category.'", "'.$description.'", "'.$qualifications.'", "'.$requirements.'" )';
					  


if($db->query($query) == TRUE){
	 	$json['success'] = 'Job Posted Succefully.';

	 }else{
	 	$json['success'] =  'Internet Connection error.';
	 	echo mysqli_error($db);
	 }


print(json_encode($json));

?>
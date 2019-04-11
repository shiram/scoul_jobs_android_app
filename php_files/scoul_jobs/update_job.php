<?php
access_open_academy_open_academy,open_academy.open_academy,model_open_academy_open_academy,,1,0,0,0
error_reporting(E_ERROR);

$job_id = (int)$_REQUEST['job_id'];
$org_name = $_REQUEST['org_name'];
$title = $_REQUEST['title'];
$category = $_REQUEST['category'];
$description = $_REQUEST['description'];
$qualifications = $_REQUEST['qualifications'];
$requirements = $_REQUEST['requirements'];

include 'db_connect.php';
include 'config.php';
mysqli_select_db($db, DB_NAME) or die(mysqli_error($db));

$json = array();

				  
$query = 'UPDATE jobs SET
          organisation_name = "'.$org_name.'",
		  job_title = "'.$title.'",
		  job_category = "'.$category.'",
		  job_description = "'.$description.'",
		  job_qualifications = "'.$qualifications.'",
		  job_requirements = "'.$requirements.'"
		  WHERE
		  job_id = "'.$job_id.'"';

if($db->query($query) == TRUE){
	 	$json['success'] = 'Job Updated.';

	 }else{
	 	$json['success'] =  'Connection Error Updating Item.';
	 }


print(json_encode($json));
	 //upload image to folder on server.

?>
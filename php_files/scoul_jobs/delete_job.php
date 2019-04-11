
<?php
error_reporting(E_ERROR);

$job_id = (int)$_REQUEST['job_id'];



include 'db_connect.php';
include 'config.php';

mysqli_select_db($db, DB_NAME) or die(mysqli_error($db));

$id = (int)$hostel_id;

$query = 'DELETE FROM jobs WHERE job_id = "'.$job_id.'"';

$result = mysqli_query($db, $query);

if($result){
	
$query = 'SELECT *  FROM  jobs  ORDER BY job_id DESC';




$result = mysqli_query($db, $query);

if($result){
	while($row=mysqli_fetch_array($result)){
		$data[] = $row;
	
	}
}
else {
    $data['success'] = 'Error';
}


	
}else{
	$data['success'] = 'Could not delete';
}
print(json_encode($data));





?>

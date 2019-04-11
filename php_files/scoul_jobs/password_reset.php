
<?php
error_reporting(E_ERROR);


$user_email = $_REQUEST['user_email'];
$password = $_REQUEST['user_password'];
//$user_id = '2';

include 'db_connect.php';
include 'config.php';
mysqli_select_db($db, DB_NAME) or die(mysqli_error($db));

$pass = md5($password);

$id = (int)$user_id;
$query = 'UPDATE users SET
			user_password = "'.$pass.'"
            WHERE
            user_email = "'.$user_email.'"';
			
			
if($db->query($query) == TRUE){
	 	$json['success'] = 'Password updated sucessfully.';

	 }else{
	 	$json['success'] =  'Password not updated, try again.';
	 }


print(json_encode($json));

?>

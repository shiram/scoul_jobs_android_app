<?php
error_reporting(E_ERROR);
$firstname = $_REQUEST['firstname'];
$lastname = $_REQUEST['lastname'];
$dob = $_REQUEST['dob'];
$nationality = $_REQUEST['nationality'];
$email = $_REQUEST['email'];
$phone = $_REQUEST['phone'];
$education = $_REQUEST['education'];
$address = $_REQUEST['address'];
$password = md5($_REQUEST['password']);

include "db_connect.php";
include "config.php";

mysqli_select_db($db, DB_NAME) or die(mysqli_error($db));
$json = array();


$query = 'INSERT INTO users(firstname, lastname, dob, nationality, email, phone, education, address, user_password)
 VAlUES("'.$firstname.'", "'.$lastname.'", "'.$dob.'", "'.$nationality.'", "'.$email.'", "'.$phone.'", "'.$education.'", "'.$address.'", "'.$password.'" )';

if($db->query($query) == TRUE){


        $json['success'] = 'Welcome to Scoul JOBS, Please Login to  use System.';
        $user_id = $db->insert_id;
        $json['user_id'] = $user_id;

     }else{
        $json['success'] =  'Registration failed, try again.';
        $json['user_id'] = 0;
        echo mysqli_error($db);
     }

     print(json_encode($json));

?>
<?php
error_reporting(E_ERROR);

$user_email = $_REQUEST["email"];
$user_password = md5($_REQUEST["password"]);

include "db_connect.php";
include "config.php";

mysqli_select_db($db, DB_NAME) or die(mysqli_error($db));



        $json = array();
        
        $query = 'SELECT * FROM  users 
        WHERE email = "'.$user_email.'" AND user_password = "'.$user_password.'"';
        $result = mysqli_query($db, $query);
        $row = mysqli_fetch_array($result, MYSQLI_ASSOC);

        $count = mysqli_num_rows($result);

        if($count > 0){
                    
                $json['success'] = 'Welcome '.$row['firstname'].' '.$row['lastname'];
                $json['user_id'] = $row['id'];
                $json['email'] = $row['email'];
                $json['dob'] = $row['dob'];
                $json['firstname'] = $row['firstname'];
                $json['lastname'] = $row['lastname'];
                $json['access_level'] = $row['access_level'];
                $json['nationality'] = $row['nationality'];
                $json['phone'] = $row['phone'];
                $json['education'] = $row['education'];
                $json['address'] = $row['address'];
            
            }else{

                $json['success'] = 'Check Internet Connecton Please.';
                $json['user_id'] = 0;
                $json['email'] = "";
                $json['dob'] = "";
                $json['firstname'] = "";
                $json['lastname'] = "";
                $json['access_level'] = -1;
                $json['nationality'] = "";
                $json['phone'] = "";
                $json['education'] = "";
                $json['address'] = "";
            }
  
    print(json_encode($json));


?>
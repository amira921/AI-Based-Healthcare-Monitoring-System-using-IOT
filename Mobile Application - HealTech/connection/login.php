<?php
require "dbConnect.php";

$email=$_POST["email"];
$password=$_POST["password"];


/*$email="mohamed123@gmail.com";
$password="12345678";*/


$isValidEmail = filter_var($email, FILTER_VALIDATE_EMAIL);

if($conn){

$sqlChecklogin_D="SELECT * FROM `doctor_users`  WHERE `email` = '$email' AND `password` = '$password'";
$loginQuery_D=mysqli_query($conn,$sqlChecklogin_D);

$sqlChecklogin_P="SELECT * FROM `patient_users`  WHERE `email` = '$email' AND`password` = '$password'";
$loginQuery_P=mysqli_query($conn,$sqlChecklogin_P);

$result = array();

if(mysqli_num_rows($loginQuery_P) > 0){
    $row = mysqli_fetch_array($loginQuery_P);
    $id = $row['national_id'];
    echo "P-" . $id;
} else if(mysqli_num_rows($loginQuery_D) > 0){
    $row = mysqli_fetch_array($loginQuery_D);
    $id = $row['doctor_id'];
    echo "D-" . $id;
} else {
    echo "Wrong password or Email";
}
}else{
	echo "Connection Error";
}


?> 



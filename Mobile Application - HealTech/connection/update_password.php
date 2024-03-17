<?php
require "dbConnect.php";

$password=$_POST["password"];
$M_ID=$_POST["medical_id"];
$N_ID=$_POST["national_id"];

/*$password="12345678";
$M_ID='323170';
$N_ID='323170';*/


if($conn){
$sqlCheckemail_D="SELECT * FROM `doctor_users`  WHERE `medical_id` LIKE '$M_ID'";
$emailQuery_D=mysqli_query($conn,$sqlCheckemail_D);

$sqlCheckemail_P="SELECT * FROM `patient_users`  WHERE `national_id` LIKE '$N_ID'";
$emailQuery_P=mysqli_query($conn,$sqlCheckemail_P);

if(mysqli_num_rows($emailQuery_D) > 0){
$sql = "UPDATE `doctor_users` SET `password` = '$password' WHERE `medical_id` = '$M_ID'";
if(mysqli_query($conn,$sql)){
echo 'Updated';
}
}
else if(mysqli_num_rows($emailQuery_P) > 0){
$sql = "UPDATE `patient_users` SET `password` = '$password' WHERE `national_id` = '$N_ID'";
if(mysqli_query($conn,$sql)){
echo 'Updated';
}}
else{
echo 'No Such ID';
}}
else{
    echo 'Could Not Update Password Try Again';
}
?>
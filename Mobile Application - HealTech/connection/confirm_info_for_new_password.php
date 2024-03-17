<?php
require "dbConnect.php";

$hospital_clinic_id =$_POST["hospital_clinic_id"];
$doctor_id =$_POST["doctor_id"];
$email=$_POST["email"]; 


/*$hospital_clinic_id ='zh7419';
$doctor_id ='1';
$email='hla@gmail.com'; */

if($conn){

$sqlCheckhospital_clinic_id_mail_D ="SELECT * FROM `doctor_users`  WHERE `hospital_clinic_id` LIKE '$hospital_clinic_id' AND `email` LIKE '$email'";
$DoctorQuery =mysqli_query($conn,$sqlCheckhospital_clinic_id_mail_D);

$sqlCheckdoctor_id_mail_P ="SELECT * FROM `doctor_users`  WHERE `doctor_id` LIKE '$doctor_id' AND `email` LIKE '$email'";
$PatientQuery =mysqli_query($conn,$sqlCheckdoctor_id_mail_P);


if(mysqli_num_rows($DoctorQuery) < 0 && mysqli_num_rows($PatientQuery) > 0){
echo "Those Credentials does not exist, check your Mail and Hospital/clinic ID and Try again";
}
else if(mysqli_num_rows($PatientQuery) < 0 && mysqli_num_rows($DoctorQuery) > 0){
echo "Those Credentials does not exist, check your Mail and Doctor ID and Try again";
}
else{
echo "Confirmed";
}
}
else{
echo "Connection Error";
}
?>
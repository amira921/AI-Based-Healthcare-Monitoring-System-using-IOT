<?php
require "dbConnect.php";

$medical_id =$_POST["medical_id"];
$first_name=$_POST["first_name"];
$second_name=$_POST["second_name"];
$email=$_POST["email"]; 
$password=$_POST["password"];
$hospital_clinic_id =$_POST["hospital_clinic_id"];

/*$medical_id="323171";
$first_name="Ahmed";
$second_name="Hossam";
$email="Ahmed14@gmail.com"; 
$password="ahmed14";
$hospital_clinic_id="3";*/

$isValidEmail = filter_var($email, FILTER_VALIDATE_EMAIL);

if($conn){
$sqlCheckmedical_id="SELECT * FROM `doctor_users`  WHERE `medical_id` LIKE '$medical_id'";
$medical_idQuery=mysqli_query($conn,$sqlCheckmedical_id);

$sqlCheckhospital_clinic_id ="SELECT * FROM `hospital_clinicals`  WHERE `code` LIKE '$hospital_clinic_id'";
$hospital_clinic_idQuery =mysqli_query($conn,$sqlCheckhospital_clinic_id);

$sqlCheckemail="SELECT * FROM `doctor_users`  WHERE `email` LIKE '$email'";
$emailQuery=mysqli_query($conn,$sqlCheckemail);


// check validity of medical id (regex)

if(! preg_match('/^[0-9]{6}+$/', $medical_id)){
    echo "Enter a valid Medical ID"; 
}  
else if(strlen($password) > 40 || strlen($password) < 6){
echo "Password must be less than 40 or more than 6 characters"; 
}
else if($isValidEmail === false){
echo "This Email is not valid";
}
else if(mysqli_num_rows($emailQuery) > 0){
echo "Email already Used";
}
else if(mysqli_num_rows($medical_idQuery) > 0){
echo "Medical id already Used";
}
else if(mysqli_num_rows($hospital_clinic_idQuery) === 0){
echo "Hospital or Clinic does not exist";
}
else{
$sql_register="INSERT INTO `doctor_users`  (`frist_name`,`second_name`,`email`,`password`,`medical_id`,`hospital_clinic_id`) VALUES('$first_name','$second_name','$email','$password','$medical_id ','$hospital_clinic_id ')";

if(mysqli_query($conn,$sql_register)){
echo "successfully Registered";
}
else{
echo "failed to Register";
}
}}
?>
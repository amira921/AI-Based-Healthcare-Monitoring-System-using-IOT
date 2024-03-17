<?php
require "dbConnect.php";

$national_id=$_POST["national_id"];
$first_name=$_POST["first_name"];
$second_name=$_POST["second_name"];
$email=$_POST["email"];
$first_emergancy_number=$_POST["first_emergancy_number"];
$second_emergancy_number=$_POST["second_emergancy_number"]; 
$password=$_POST["password"];
$gender=$_POST["gender"];
$birthdate=$_POST["birthdate"];
$weight=$_POST["weight"];
$height=$_POST["height"];
$blood_type=$_POST["blood_type"];
$blood_pressure=$_POST["blood_pressure"];
$pregnant=$_POST["pregnant"];
$liver=$_POST["liver"];
$diabetes=$_POST["diabetes"];
$doctor_id=$_POST["doctor_id"];

/*$national_id ="20303140102388";
$first_name="Bassandh";
$second_name="Hesham";
$email="email@gmail.com";
$first_emergancy_number="01152321090";
$second_emergancy_number=Null;
$password="password3";
$gender="Female";
$birthdate="3/14/2002";
$weight="55";
$height="165";
$blood_type="A+";
$blood_pressure="0";
$pregnant="0";
$liver="0";
$diabetes="0";
$doctor_id="1";*/

$isValidEmail = filter_var($email, FILTER_VALIDATE_EMAIL);

if($conn){
$sqlChecknational_id="SELECT * FROM `patient_users`  WHERE `national_id` LIKE '$national_id'";
$national_idQuery=mysqli_query($conn,$sqlChecknational_id);

$sqlCheckdoctor_id="SELECT * FROM `doctor_users`  WHERE `doctor_id` LIKE '$doctor_id'";
$doctor_idQuery=mysqli_query($conn,$sqlCheckdoctor_id);

// to check the validity of the mobile number (regex)
if(! preg_match('/^[0-9]{11}+$/', $first_emergancy_number)){
    echo "Enter a correct Phone Number"; 
}  
else if(! preg_match('/^[0-9]{14}+$/', $national_id)){
    echo "Enter a valid National ID"; 
}  
else if(strlen($password) > 40 || strlen($password) < 6){
echo "Password must be less than 40 or more than 6 characters"; 
}
else if($isValidEmail === false){
echo "This Email is not valid";
}
else if(mysqli_num_rows($national_idQuery) > 0){
echo "National id already Used";
}
else if(mysqli_num_rows($doctor_idQuery) === 0){
echo "Doctor does not exist";
}
else{
$sql_register="INSERT INTO `patient_users`  (`national_id`,`first_name`,`second_name`,`email`,`first_emergancy_number`,`second_emergancy_number`,`password`,`gender`,`birthdate`,`weight`,`height`,`blood_type`,`blood_pressure`,`pregnant`,`liver`,`diabetes`,`doctor_id`) VALUES('$national_id','$first_name','$second_name','$email','$first_emergancy_number','$second_emergancy_number','$password','$gender','$birthdate','$weight','$height','$blood_type','$blood_pressure','$pregnant','$liver','$diabetes','$doctor_id')";

if(mysqli_query($conn,$sql_register)){
echo "successfully Registered";
}
else{
echo "failed to Register";
}
}}
?>
<?php
require "conn.php";

$patient_id=$_POST["patient_id"];
$stomach_ulcer=$_POST["stomach_ulcer"];
$heart_burn=$_POST["heart_burn"];
$vomiting=$_POST["vomiting"];
$nausea=$_POST["nausea"];
$diarrhea=$_POST["diarrhea"]; 
$incontinence=$_POST["incontinence"];
$bloating=$_POST["bloating"];
$constipation=$_POST["constipation"];
$bleeding=$_POST["bleeding"];
$cough=$_POST["cough"];
$sneezing=$_POST["sneezing"];
$stuffy_nose=$_POST["stuffy_nose"];
$sore_throat=$_POST["sore_throat"];
$breathlessness=$_POST["breathlessness"];
$headache=$_POST["headache"];
$dizziness=$_POST["dizziness"];
$body_ahes=$_POST["body_ahes"];
$skin_irritation=$_POST["skin_irritation"];
$date=$_POST["date"];


/*$patient_id ="20303140102388";
$stomach_ulcer="1";
$heart_burn="1";
$vomiting="1";
$nausea="1";
$diarrhea="1"; 
$incontinence="1";
$bloating="1";
$constipation="1";
$bleeding="1";
$cough="1";
$sneezing="1";
$stuffy_nose="1";
$sore_throat="1";
$breathlessness="1";
$headache="1";
$dizziness="1";
$body_ahes="1";
$skin_irritation="1";
$date="2002-10-12";*/


if($conn){
$sqlChecknational_id="SELECT * FROM `patient_users`  WHERE `national_id` LIKE '$patient_id'";
$national_idQuery=mysqli_query($conn,$sqlChecknational_id);

$sqlCheckDate="SELECT * FROM `symptoms`  WHERE `date` LIKE '$date'";
$dateQuery=mysqli_query($conn,$sqlCheckDate);

if(mysqli_num_rows($national_idQuery) < 0){
echo "National id Not Right";
}else if(mysqli_num_rows($dateQuery) > 0){
	$sql_symptoms_update="UPDATE `symptoms` SET `patient_id` = '$patient_id',`stomach_ulcer` = '$stomach_ulcer',`heart_burn` = '$heart_burn',`vomiting` = '$vomiting',`nausea` = '$nausea',`diarrhea` = '$diarrhea',`incontinence` = '$incontinence',`bloating` = '$bloating',`constipation` = '$constipation',`bleeding` = '$bleeding',`cough` = '$cough',`sneezing` = '$sneezing',`stuffy_nose` = '$stuffy_nose',`sore_throat` = '$sore_throat',`breathlessness` = '$breathlessness',`headache` = '$headache',`dizziness` = '$dizziness',`body_ahes` = '$body_ahes',`skin_irritation` = '$skin_irritation' WHERE `date` = '$date'";

    if(mysqli_query($conn,$sql_symptoms_update)){
      echo "successfully Updated";
    }
    else{
     echo "failed to Update";
    }
}
else{
$sql_symptoms_add="INSERT INTO `symptoms`(`patient_id`,`stomach_ulcer`,`heart_burn`,`vomiting`,`nausea`,`diarrhea`,`incontinence`,`bloating`,`constipation`,`bleeding`,`cough`,`sneezing`,`stuffy_nose`,`sore_throat`,`breathlessness`,`headache`,`dizziness`,`body_ahes`,`skin_irritation`,`date`) VALUES('$patient_id','$stomach_ulcer','$heart_burn','$vomiting','$nausea','$diarrhea','$incontinence','$bloating','$constipation','$bleeding','$cough','$sneezing','$stuffy_nose','$sore_throat','$breathlessness','$headache','$dizziness','$body_ahes','$skin_irritation','$date')";

if(mysqli_query($conn,$sql_symptoms_add)){
echo "successfully Added";
}
else{
echo "failed to Add";
}
}}
?>
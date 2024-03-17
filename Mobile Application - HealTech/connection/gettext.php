<?php 
  $id =$_GET['id'];
  // $id='8';
	
	require_once('dbConnect.php');
	
	$sql = "SELECT DISTINCT patient_users.first_name, patient_users.second_name
    FROM patient_users 
        INNER JOIN patient_vitals on patient_users.national_id =patient_vitals.ID AND patient_users.doctor_id=$id
        WHERE  patient_vitals.temperture > 39.3 OR patient_vitals.oxygen <91 OR patient_vitals.heart_rate>100 
        OR patient_vitals.heart_rate<60 OR patient_vitals.blood_pressure>100";
    
     
	$r = mysqli_query($conn,$sql);
	
	$result = array();
	

		//looping through all the records fetched
	while($row = mysqli_fetch_array($r)){
		
		//Pushing name and id in the blank array created 
		array_push($result,array(
			"patient_name"=>$row['first_name'],
			"patient_sname"=>$row['second_name'],
			
		));
	}
	echo json_encode(array('result'=>$result));
	
	mysqli_close($conn);
	
	

	
	
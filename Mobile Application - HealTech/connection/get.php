<?php 
  $id =$_GET['id'];
 // $id ='1';
	
 require_once('dbConnect.php');
	
	$sql = "SELECT national_id,first_name,second_name FROM patient_users WHERE doctor_id=$id";
	$r = mysqli_query($conn,$sql);
	
	$result = array();
	

		//looping through all the records fetched
	while($row = mysqli_fetch_array($r)){
		
		//Pushing name and id in the blank array created 
		array_push($result,array("patient_id"=>$row['national_id'],
			"patient_name"=>$row['first_name'],
			"patient_sname"=>$row['second_name'],
			
		));
	}
	echo json_encode(array('result'=>$result));
	
	mysqli_close($conn);
	
	
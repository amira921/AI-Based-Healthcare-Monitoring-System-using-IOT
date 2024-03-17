<?php 
 $id =$_GET['id'];
 //$id ='1';
	
	require_once('dbConnect.php');
	
	/*$sql = "SELECT work_days.days, work_days.date, work_hours.time , work_days.doctor_id
	FROM work_hours
	JOIN work_days ON work_days.id = work_hours.work_days_id
	WHERE work_days.doctor_id = $id
	AND work_hours.doctor_id = $id";*/
	$sql = "SELECT work_days.days, work_days.date, work_days.time , work_days.doctor_id ,patient_users.first_name ,patient_users.second_name 
	FROM patient_time 
	JOIN work_days ON work_days.id = patient_time.work_days_id 
	JOIN patient_users ON patient_users.national_id=patient_time.patient_id 
	WHERE work_days.doctor_id =  $id AND patient_users.doctor_id =  $id";

	$r = mysqli_query($conn,$sql);
	$result = array();
	

		//looping through all the records fetched
	while($row = mysqli_fetch_array($r)){
		
		//Pushing name and id in the blank array created 
		array_push($result,array(
			"work_day"=>$row['days'],
			"date"=>$row['date'],
			"work_hour"=>$row['time'],
			"patient_name"=>$row['first_name'],
			"patient_sname"=>$row['second_name'],
			
		));
	}
	echo json_encode(array('result'=>$result));
	
	mysqli_close($conn);
	

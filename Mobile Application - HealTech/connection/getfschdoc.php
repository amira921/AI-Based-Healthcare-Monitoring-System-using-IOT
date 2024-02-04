<?php 
 $id =$_GET['id'];
 //$id ='1';
	
	require_once('dbConnect.php');
	
	$sql = "SELECT work_days.days, work_days.date, work_days.time,work_days.id
	FROM work_days
	WHERE work_days.doctor_id = $id";
	$r = mysqli_query($conn,$sql);
	$result = array();
	

		//looping through all the records fetched
	while($row = mysqli_fetch_array($r)){
		
		//Pushing name and id in the blank array created 
		array_push($result,array(
			"work_day"=>$row['days'],
			"date"=>$row['date'],
			"work_hour"=>$row['time'],
			"work_day_id"=>$row['id'],
			
			
		));
	}
	echo json_encode(array('result'=>$result));
	
	mysqli_close($conn);
	

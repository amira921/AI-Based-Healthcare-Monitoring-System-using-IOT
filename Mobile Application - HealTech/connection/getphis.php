<?php 
require "conn.php";

$id = $_GET['id'];
// $id = 20303140102388;
	
	$sql = "SELECT temperture,oxygen,heart_rate,blood_pressure,time,date FROM patient_vitals WHERE ID=$id";
	$r = mysqli_query($conn,$sql);
	
	$result = array();
	

		//looping through all the records fetched
	while($row = mysqli_fetch_array($r)){
		
		//Pushing name and id in the blank array created 
		array_push($result,array(
			"ptemp"=>$row['temperture'],
			"poxgen"=>$row['oxygen'],
			"pheart_pulse"=>$row['heart_rate'],
			"ppressure"=>$row['blood_pressure'],
			"ptime"=>$row['time'],
			"pdate"=>$row['date'],
		));
	}
	echo json_encode(array('result'=>$result));
	
	mysqli_close($conn);
	
	
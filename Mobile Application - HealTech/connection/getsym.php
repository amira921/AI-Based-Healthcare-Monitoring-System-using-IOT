<?php 
  $id = $_GET['id'];
	
	require_once('dbConnect.php');
	
	$sql = "SELECT * FROM symptoms WHERE patient_id=$id";

	$r = mysqli_query($conn,$sql);
	
	$result = array();
	

		//looping through all the records fetched
	while($row = mysqli_fetch_array($r)){
		
		//Pushing name and id in the blank array created 
		array_push($result,array(
			
	
			"sstomach_ulcer"=>$row['stomach_ulcer'],
			"sheart_burn"=>$row['heart_burn'],
			"svomiting"=>$row['vomiting'],
			"snausea"=>$row['nausea'],
			"sdiarrhea"=>$row['diarrhea'],
			"sincontinence"=>$row['incontinence'],
			"sbloating"=>$row['bloating'],
			"sconstipation"=>$row['constipation'],
			"sbleeding"=>$row['bleeding'],
			"scough"=>$row['cough'],
			"ssneezing"=>$row['sneezing'],
			"sstuffy_nose"=>$row['stuffy_nose'],
			"ssore_throat"=>$row['sore_throat'],
			"sbreathlessness"=>$row['breathlessness'],
			"sheadache"=>$row['headache'],
			"sdizziness"=>$row['dizziness'],
			"sbody_ahes"=>$row['body_ahes'],
			"sskin_irritation"=>$row['skin_irritation'],
			"sydate"=>$row['date'],
		
			
		));
	}
	echo json_encode(array('result'=>$result));
	
	mysqli_close($conn);
	
	
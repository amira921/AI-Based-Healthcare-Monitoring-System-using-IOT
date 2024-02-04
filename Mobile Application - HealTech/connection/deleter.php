<?php
	//$id ='103';
	$id =$_GET['id'];
	

	require_once('dbConnect.php');
	
	$sql = "DELETE  FROM work_days 
	WHERE work_days.id ='$id'";

	
if(mysqli_query($conn,$sql)){
	echo 'Category Deleted Successfully';
}else{
	echo 'Could Not Delete Category Try Again';
}

mysqli_close($conn);
?>

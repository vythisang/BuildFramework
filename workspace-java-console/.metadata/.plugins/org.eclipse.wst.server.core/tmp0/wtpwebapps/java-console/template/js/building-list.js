function assignmentBuilding(buildingId){
				openModelAssignmentBuilding();
				$("#buildingId").val(buildingId);
				console.log($('#buildingId').val());
			}

			function openModelAssignmentBuilding(){
				$('#assignmentBuildingModal').modal();
			}
			$("#btnAssignBuilding").click(function (e) { 
				e.preventDefault();
				var data = {};
				data['buildingId'] = $('#buildingId').val();
				//$('#staffList').find('tbody input[type = checkbox]');
				var staffs =  $('#staffList').find('tbody input[type = checkbox]:checked').map(function () {
					return $(this).val();	
				}).get();
				data['staffs'] = staffs;
				assignStaff(data);
			});
			function assignStaff(data){
				$.ajax({
					type: "POST",
					url: "http://localhost:8080/api-user-assignment",
					data: JSON.stringify(data),
					dataType: "json",
					contentType:"application/json",
					success: function (response) {
						console.log('success'); 
					},
					error: function (response) {
						console.log('fail');
						console.log(response);
					}
				});
			}
			$('#btnDeleteBuilding').click(function (e) { 
				e.preventDefault();
				var data = {};
				var buildingIds =  $('#buildingList').find('tbody input[type = checkbox]:checked').map(function () {
					return $(this).val();	
				}).get();
				data['buildingIds'] = buildingIds;
				deleteBuilding(data);
			
			});
			function deleteBuilding(data){
				$.ajax({
					type: "DELETE",
					url: "http://localhost:8080/api-building",
					data: JSON.stringify(data),
					dataType: "json",
					contentType:"application/json",
					success: function (response) {
						console.log('success'); 
					},
					error: function (response) {
						console.log('fail');
						console.log(response);
					}
				});
			}
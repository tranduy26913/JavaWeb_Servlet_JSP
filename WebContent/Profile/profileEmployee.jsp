<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Thông tin người dùng</title>
<link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,300;0,400;0,500;1,100&display=swap&subset=vietnamese" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="<c:url value='/assets/css/dashboard.css' />">

</head>

<body>

	<fmt:formatDate value="${user.birthday}" var="formatedBirthday" type="date" pattern="yyyy-MM-dd" />

	
	<div class="main-container">
		<div class="pd-ltr-20">
			<div class="container rounded bg-white mb-5">
				<div class="row">
					<div class="col-md-5 border-right">
						<div class="d-flex flex-column align-items-center text-center p-3 py-5">
							<!-- <img class="rounded-circle mt-5" width="150px"
                                src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg"> -->
							<img class="rounded-circle mt-5" width="150px" height="150px" src="${user.profile_url }" id="profile-image">
							<!--<img class="rounded-circle mt-5" width="150px"
                                src="${user.profile_url }"> -->
							<span class="font-weight-bold"><c:out value='${user.fullName}' /></span>
							<form action="upload" method="post" id="upload-form" enctype="multipart/form-data" class="form-group w-100">
								<input type="file" id="avatar" name="avatar" accept="image/png, image/jpeg"> 
								<!-- <input type="submit" value="upload"> -->
								<button class="btn btn-primary" type="button" id="upload" >Upload</button>
							</form>
						</div>
					</div>

					<div class="col-md-7 border-right">
						<form action="profile" method="POST">
							<div class="p-3 py-5">
								<div class="d-flex justify-content-between align-items-center mb-3">
									<h4 class="text-right">Cài đặt Profile</h4>
								</div>
								<div class="row mt-2">
									<div class="col-md-12 mt-3">
										<label class="labels">Họ và tên</label> <input type="text" class="form-control" placeholder="Tên công ty" name="username" value="${user.fullName}" readonly="readonly">
									</div>
									<div class="col-md-12 mt-3">
										<label class="labels">Số điện thoại</label> <input type="text" class="form-control" placeholder="Số điện thoại" name="phone" value="${user.phone}" readonly="readonly">
									</div>
									<div class="col-md-12 mt-3">
										<label class="labels">Địa chỉ</label> <input type="text" class="form-control" placeholder="Địa chỉ" name="address" value="${user.address}" readonly="readonly">
									</div>
									<div class="col-md-12 mt-3">
										<label class="labels">Email</label> <input type="text" class="form-control" placeholder="Email" name="email" value="${user.email}" readonly="readonly">
									</div>
									<div class="col-md-12 mt-3">
										<label class="labels">Ngày sinh</label> 
										<input type="date" class="form-control" placeholder="" name="birthday" value="${formatedBirthday}" readonly="readonly">
									</div>
									<div class="col-md-12 mt-3">
										<label class="labels">Giới tính</label>
										<div class="row">
											<div class="col-md-6">
												<input type="radio" name="gender" id="male" value="male" readonly="readonly" checked> <label for="male">Nam</label>
											</div>
											<div class="col-md-6">
												<input type="radio" name="gender" id="female" value="female" readonly="readonly"> <label for="female">Nữ</label>
											</div>
										</div>
									</div>
								</div>

								<div class="mt-5 text-center d-flex">
									<div>
										<input type="hidden" name="action" value="update">
										<button class="btn btn-primary profile-button" type="submit" onclick="EnableReadonly()">Save Profile</button>
										<button class="btn btn-primary profile-button" type="button" onclick="RemoveReadonly()">Edit Profile</button>
									</div>
								</div>
							</div>
						</form>
					</div>
					
				</div>
			</div>
		</div>
	</div>



	<script>
		const inputPropertise = document.getElementsByClassName("form-control");
		const avatar = document.getElementById("avatar");
		const profileImg = document.getElementById("profile-image");

		avatar.addEventListener("change", function() {
			const file = this.files[0];

			if (file) {
				const reader = new FileReader();

				reader.addEventListener("load", function() {
					profileImg.setAttribute("src", this.result);
				});
				reader.readAsDataURL(file);
			}
		});

		function RemoveReadonly() {
			var i;
			for (i = 0; i < inputPropertise.length; i++) {
				inputPropertise[i].removeAttribute("readonly");
			}
		}

		function EnableReadonly() {
			var i;
			for (i = 0; i < inputPropertise.length; i++) {
				inputPropertise[i].setAttribute("readonly", "readonly");
			}
		}
		
		$(document).ready(function() {
			$('#upload').click(function() {
				var form = $("#upload-form")[0];
	            var data = new FormData(form);
					$.ajax({
						type: "POST",
						url: "upload",
						processData : false,
		                contentType : false,
						data: data,
						enctype: 'multipart/form-data',
						success: function(msg) {
							alert("Đổi Avatar thành công");
						},
						error: function(jqXHR, textStatus, errorThrown) {
							
						}
					});
			});
		});
	</script>
</body>

</html>
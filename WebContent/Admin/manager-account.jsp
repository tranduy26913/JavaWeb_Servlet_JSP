<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" type="image/png"
	href="<c:url value='/assets/img/favicon.ico'/>">
<title>Thông tin tài khoản</title>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,300;0,400;0,500;1,100&display=swap&subset=vietnamese"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/all.min.css">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/assets/css/main.css'/>">
</head>

<body>
	<div class="main-container">
		<div class="pd-ltr-20">

			<div class="card-box mb-30">
				<div class="pd-20">
					<h4 class="text-blue h4">TÀI KHOẢN</h4>
				</div>
				<div class="pb-20">
					<table class="data-table table stripe hover nowrap">
						<thead>
							<tr>
								<th class="table-plus datatable-nosort">STT</th>
								<th class="table-plus datatable-nosort">Tài khoản</th>
								<th class="table-plus datatable-nosort">Email</th>
								<th class="table-plus datatable-nosort">Loại tài khoản</th>
								<th class="table-plus datatable-nosort">Trạng thái</th>
								<th class="datatable-nosort">Hành động</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${accounts}" var="item" varStatus="loop">
								<tr>
									<td class="table-plus">${loop.index}</td>
									<td>${item.getUsername()}</td>
									<td>${item.getEmail()}</td>
									<td>${item.getTypeUser()}</td>
									<td>${item.getStatus()}</td>
									<td>
										<div class="dropdown">
											<a
												class="btn btn-link font-24 p-0 line-height-1 no-arrow dropdown-toggle"
												href="#" role="button" data-toggle="dropdown"> <i
												class="dw dw-more"></i>
											</a>
											<div
												class="dropdown-menu dropdown-menu-right dropdown-menu-icon-list">
												<a class="dropdown-item" href="<c:url value='/viewprofile?user=${item.getUsername()}'/>"><i class="dw dw-eye"></i>
													View</a> <a class="dropdown-item"
													href="<c:url value='/adminactive?user=${item.getUsername()}'/>"><i
													class="dw dw-edit2"></i> Active</a> <a class="dropdown-item"
													href="<c:url value='/admindelete?user=${item.getUsername()}'/>"><i class="dw dw-delete-3"></i> Delete</a>
											</div>
										</div>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
<script	src="<c:url value='/assets/script/jquery.dataTables.min.js' />"></script>
<script src="<c:url value='/assets/script/dataTables.bootstrap4.min.js' />"></script>
<script src="<c:url value='/assets/script/dataTables.responsive.min.js' />"></script>
<script src="<c:url value='/assets/script/responsive.bootstrap4.min.js'/>"></script>
<script src="<c:url value='/assets/script/datatable-setting.js'/>"></script>
</body>



</html>
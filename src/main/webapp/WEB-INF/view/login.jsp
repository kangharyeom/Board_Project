<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<meta charset="UTF-8">
<style>

loginContainer{
	font-weight: bold;
	margin-top: 30px;
	display: flex;
	flex-direction: column;
	align-items: center;
	width: 400px;
}

RegisterButtons{
	margin-top: 30px;
	display: flex;
	flex-direction: column;
	gap: 10px;
	width: 260px;
}

#loginButtonBottom{
	width: 260px;
}

.IdAndPassword{
	width: 300px;
}

IdContainer{
	margin-top: 30px;
}
PasswordContainer{
	margin-top: 30px;
}

#exampleModalLabel{
	font-weight: bold;
}

</style>
</head>
	<body>
		<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
		<header>
			<%@ include file="/WEB-INF/view/header/header.jsp" %>
		</header>
				<IdContainer class="form-floating">
					<input type="text" class="form-control"  id="idInput" placeholder="Id">
					<label for="floatingPassword">아이디</label>
				  </IdContainer>
				  <PasswordContainer class="form-floating">
					<input type="password" class="form-control" id="passInput" placeholder="Password">
					<label for="floatingPassword">비밀번호</label>
				  </PasswordContainer>
			<RegisterButtons>
				<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
				회원가입
			</button>
			
			<!-- Modal -->
			<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
					<h1 class="modal-title fs-5" id="exampleModalLabel">sign up</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<%@ include file="/WEB-INF/view/join.jsp" %>
					</div>
					<div class="modal-footer">
					<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary"  id="join_btn">등록</button>
					</div>
				</div>
				</div>
			</div>
				<loginButton height="50">
					<button class="btn btn-primary" onclick="location.href='/login'" id="loginButtonBottom">로그인</button>
				</loginButton>
			</RegisterButtons>
		</loginContainer>
	</body>
</html>
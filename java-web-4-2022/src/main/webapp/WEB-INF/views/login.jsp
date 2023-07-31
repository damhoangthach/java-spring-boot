<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div class="main-container">
	<div class="main-content">

		<div class="row">
			<div class="col-sm-10 col-sm-offset-1">
				<div class="login-container">
					<div class="center">
						<h1>
							<i class="ace-icon fa fa-leaf green"></i>
							<span class="red">Ace Application</span>
							<%--	<span class="white" id="id-text2">Application</span>--%>
						</h1>
						<h4 class="blue" id="id-company-text">&copy; Company Name</h4>
					</div>
					<c:if test="${param.incorrectAccount != null}">
						<div class="alert alert-danger">
							Tên hoặc mật khẩu không đúng
						</div>
					</c:if>
					<c:if test="${param.accessDenied != null}">
						<div class="alert alert-danger">
							Bạn không được phép
						</div>
					</c:if>
					<c:if test="${param.sessionTimeout != null}">
						<div class="alert alert-danger">
							Thời gian chờ của phiên
						</div>
					</c:if>



					<%--		<form action="j_spring_security_check" id="formLogin" method="post">
                                <div class="form-group">
                                    <input type="text" class="form-control" id="userName" name="j_username" placeholder="Tên đăng nhập">
                                </div>

                                <div class="form-group">
                                    <input type="password" class="form-control" id="password" name="j_password" placeholder="Mật khẩu">
                                </div>
                                <button type="submit" class="btn btn-primary" >Đăng nhập</button>
                            </form>

            --%>



					<div class="space-6"></div>

					<div class="position-relative">
						<div id="login-box" class="login-box visible widget-box no-border">
							<div class="widget-body">
								<div class="widget-main">
									<h4 class="header blue lighter bigger">
										<i class="ace-icon fa fa-coffee green"></i>
										Please Enter Your Information
									</h4>

									<div class="space-6"></div>

									<form action="j_spring_security_check" id="formLogin" method="post">
										<fieldset>
											<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control"  name="j_username" placeholder="Username" />
															<i class="ace-icon fa fa-user"></i>
														</span>
											</label>

											<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control"  name="j_password" placeholder="Password" />
															<i class="ace-icon fa fa-lock"></i>
														</span>
											</label>

											<div class="space"></div>

											<div class="clearfix">
												<label class="inline">
													<input type="checkbox" class="ace" />
													<span class="lbl"> Remember Me</span>
												</label>

												<button type="submit" class="width-35 pull-right btn btn-sm btn-primary">
													<i class="ace-icon fa fa-key"></i>
													<span class="bigger-110">Login</span>
												</button>
											</div>

											<div class="space-4"></div>
										</fieldset>
									</form>

									<div class="social-or-login center">
										<span class="bigger-110">Or Login Using</span>
									</div>

									<div class="space-6"></div>

									<div class="social-login center">
										<a class="btn btn-primary">
											<i class="ace-icon fa fa-facebook"></i>
										</a>

										<a class="btn btn-info">
											<i class="ace-icon fa fa-twitter"></i>
										</a>

										<a class="btn btn-danger">
											<i class="ace-icon fa fa-google-plus"></i>
										</a>
									</div>
								</div><!-- /.widget-main -->

								<div class="toolbar clearfix">
									<div>
										<a href="#" data-target="#forgot-box" class="forgot-password-link">
											<i class="ace-icon fa fa-arrow-left"></i>
											I forgot my password
										</a>
									</div>

									<div>
										<a href="#" data-target="#signup-box" class="user-signup-link">
											I want to register
											<i class="ace-icon fa fa-arrow-right"></i>
										</a>
									</div>

								</div>
							</div><!-- /.widget-body -->
						</div><!-- /.login-box -->

					</div><!-- /.signup-box -->
				</div><!-- /.position-relative -->


			</div>
		</div><!-- /.col -->
	</div><!-- /.main-content -->
</div><!-- /.main-container -->
</body>
</html>
<%@page pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="styles/global_color.css" />
         <style type="">
    		.ok {
    			color: green;
    		}
    		.error {
    			color: red;
    		}
    	</style>
    	<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
        <script language="javascript" type="text/javascript">
	        function check_pwd() {
	    		var $pwd = $("#pwd").val();
	    		var $div = $("#d1");
	    		var reg = /^\w{3,10}$/;
	    		if ($pwd!="" && reg.test($pwd)) {
	    			$div.toggleClass("ok");
	    			return true;
	    		} else {
	    			$div.toggleClass("error");
	    			return false;
	    		}
	        }
	        function check_tf() {
	    		var $pwd = $("#pwd").val();
	    		var $confirmPwd = $("#confirmPwd").val();
	    		var $div = $("#d2");
	    		if($pwd != $confirmPwd) {
	    			$div.toggleClass("error");
	    			return false;
	    		} else {
	    			$div.toggleClass("ok");
	    			return true;
	    		}
	    	}
            //保存成功的提示消息
            function showResult() {
                showResultDiv(true);
                window.setTimeout("showResultDiv(false);", 3000);
            }
            function showResultDiv(flag) {
                var divResult = document.getElementById("save_result_info");
                if (flag)
                    divResult.style.display = "block";
                else
                    divResult.style.display = "none";
            }
        </script>
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <img src="images/logo.png" alt="logo" class="left"/>
            <c:import url="../logo.jsp"></c:import>           
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">
            <ul id="menu">
               <c:import url="../menu.jsp"></c:import>
            </ul>
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">            
            <div id="save_result_info" class="save_success">保存成功！</div>
            <form action="addAdmin.do" method="post" class="main_form" onsubmit="return check_pwd() + check_tf() == 2;">
                    <div class="text_info clearfix"><span>姓名：</span></div>
                    <div class="input_info">
                        <input type="text" name="name"/>
                        <span class="required">*</span>
                        <div class="validate_msg_medium error_msg">7长度以内的汉字、字母、数字的组合</div>
                    </div>
                    <div class="text_info clearfix"><span>管理员账号：</span></div>
                    <div class="input_info">
                        <input type="text" name="adminCode" />
                        <span class="required">*</span>
                        <div class="validate_msg_medium error_msg">10长度以内的字母、数字和下划线的组合</div>
                    </div>
                    <div class="text_info clearfix"><span>密码：</span></div>
                    <div class="input_info">
                        <input type="password" name="password" id="pwd"/>
                        <span class="required">*</span>
                        <div class="validate_msg_medium error_msg" id="d1">16长度以内的字母、数字和下划线的组合</div>
                    </div>
                    <div class="text_info clearfix"><span>重复密码：</span></div>
                    <div class="input_info">
                        <input type="password" id="confirmPwd" />
                        <span class="required">*</span>
                        <div class="validate_msg_medium error_msg" id="d2">两次密码必须相同</div>
                    </div>      
                    <div class="text_info clearfix"><span>电话：</span></div>
                    <div class="input_info">
                        <input type="text" class="width200" name="telephone"/>
                        <span class="required">*</span>
                        <div class="validate_msg_medium error_msg">正确的电话号码格式：手机或固话</div>
                    </div>
                    <div class="text_info clearfix"><span>Email：</span></div>
                    <div class="input_info">
                        <input type="text" class="width200" name="email"/>
                        <span class="required">*</span>
                        <div class="validate_msg_medium error_msg">20长度以内，正确的 email 格式</div>
                    </div>
                    <div class="text_info clearfix"><span>角色：</span></div>
                    <div class="input_info_high">
                        <div class="input_info_scroll">
                            <ul>
                                <li><input type="checkbox" value="超级管理员" name="role"/>超级管理员</li>
	                            <li><input type="checkbox" value="角色管理员" name="role"/>角色管理员</li>
	                            <li><input type="checkbox" value="资费管理员" name="role"/>资费管理员</li>
	                            <li><input type="checkbox" value="账务账号员" name="role"/>账务账号管理员</li>
	                            <li><input type="checkbox" value="业务账号员" name="role"/>业务账号管理员</li>
	                            <li><input type="checkbox" value="账单管理员" name="role"/>账单管理员</li>
	                            <li><input type="checkbox" value="报表管理员" name="role"/>报表管理员</li>
                            </ul>
                        </div>
                        <span class="required">*</span>
                        <div class="validate_msg_tiny error_msg">至少选择一个</div>
                    </div>
                    <div class="button_info clearfix">
                        <input type="submit" value="保存" class="btn_save" onclick="showResult();" />
                        <input type="button" value="取消" class="btn_save" onclick="history.back();" />
                    </div>
                </form>  
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <span>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</span>
            <br />
            <span>版权所有(C)加拿大达内IT培训集团公司 </span>
        </div>
    </body>
</html>

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
	    	function check_oldPwd() {
	    		var $oldPwd = $("#oldPwd").val();
	    		var $div = $("#d1");
	    		var reg = /^\w{0,30}$/;
	    		if ($oldPwd!="" && reg.test($oldPwd)) {
	    			$div.toggleClass("ok");
	    			return true;
	    		} else {
	    			$div.toggleClass("error");
	    			return false;
	    		}
	    	}
	    	function check_newPwd() {
	    		var $newPwd = $("#newPwd").val();
	    		var $div = $("#d2");
	    		var reg = /^\w{3,10}$/;
	    		if ($newPwd!="" && reg.test($newPwd)) {
	    			$div.toggleClass("ok");
	    			return true;
	    		} else {
	    			$div.toggleClass("error");
	    			return false;
	    		}
	    	}
	    	function check_tf() {
	    		var $newPwd = $("#newPwd").val();
	    		var $againNewPwd = $("#againNewPwd").val();
	    		var $div = $("#d3");
	    		if($newPwd != $againNewPwd) {
	    			$div.toggleClass("error");
	    			return false;
	    		} else {
	    			$div.toggleClass("ok");
	    			return true;
	    		}
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
        <div id="main">      
            <!--保存操作后的提示信息：成功或者失败-->      
            <div id="save_result_info" class="save_success">保存成功！</div><!--保存失败，旧密码错误！-->
            <form action="updatePwd.do?adminCode=${adminCode }" method="post" class="main_form" onsubmit="return check_oldPwd() + check_newPwd() + check_tf() == 3;">
                <div class="text_info clearfix"><span>旧密码：</span></div>
                <div class="input_info">
                    <input type="password" name="oldPwd" id="oldPwd" class="width200" onblur="check_oldPwd();"/><span class="required">*</span>
                    <div id="d1" class="validate_msg_medium">30长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>新密码：</span></div>
                <div class="input_info">
                    <input type="password" name="newPwd" id="newPwd" class="width200" onblur="check_newPwd();" /><span class="required">*</span>
                    <div id="d2" class="validate_msg_medium">30长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>重复新密码：</span></div>
                <div class="input_info">
                    <input type="password" id="againNewPwd" class="width200" onblur="check_tf();" /><span class="required">*</span>
                    <div id="d3" class="validate_msg_medium">两次新密码必须相同</div>
                </div>
                <div class="button_info clearfix">
                    <input type="submit" value="保存" class="btn_save" onclick="showResult();" />
                    <input type="button" value="取消" class="btn_save" onclick="history.back();"/>
                </div>
            </form>  
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
            <p>版权所有(C)加拿大达内IT培训集团公司 </p>
        </div>
    </body>
</html>

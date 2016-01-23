<!DOCTYPE html>
<html >
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<head>
   <title>Bootstrap 实例 - 标签式的导航菜单</title>
   <link rel="stylesheet" type="text/css" href="/modelgarden/css/bootstrap.min.css" >
   <link rel="stylesheet" type="text/css" href="/modelgarden/css/register.css">
   <script src="/modelgarden/js/jquery-2.1.4.min.js"></script>
   <script src="/modelgarden/js/bootstrap.min.js"></script>
   <script src="/modelgarden/js/MD5.js"></script>
   <script src="/modelgarden/js/reg.js"></script>
</head>
<body>
    <article class="regCon">
       <div class="lineOne">
          <h1>注册模特网</h1>
       </div>
       <section class="Userreg">
          <p class="name1">
             已有账户？
             <a onclick="platBt('login')" href="javascript:void(0)">登录</a>
          </p>
          <form name="reg_form" id="reg_form" action="#" method="post" role="form">
             <ul>
                <li>
                   <section class="ulLi">
                      <span>手机号</span>
                      <input type="text" name="telphone" class="phoneNumber" id="phoneNumber" placeholder="请输入您的手机号" maxlength="11">
                   </section>
                </li>
                <li>
                  <section class="ulLi">
                      <span>验证码</span>
         
                      <input type="text" name="phoneVerificationCode" class=" numberYz" 
                         placeholder="请输入验证码" disabled="disabled" maxlength="6">
                  
                      <input type="button" class=" getYz yzmBt daojishi zhuceVali " value="获取验证码" clicktime="1">
                  </section>
                </li>
                <li>
                   <section class="ulLi">
                      <span id="password">密码</span>
                      <input type="password" name="password" class="LogoPass"  placeholder="输入密码，6位至16位" maxlength="16" onpaste="return false" ondragenter="return false" oncontextmenu="return false">
                      <!-- <h6 class="msgError">请输入密码</h6> -->
                   </section>
                </li>
                <li>
                    <section class="ulLi">
                         <span id="password">确认密码</span>
                            <input type="password" name="confrimPwd" class="ConFrimPass"  placeholder="确认密码" maxlength="16" onpaste="return false" ondragenter="return false" oncontextmenu="return false">
                           <!--  <h6 class="msgErrorConfirm">确认密码不得为空</h6> -->
                     </section>
                </li>
                <li>
                   <input type="button" value="提交注册" class="subReg" onclick="subReg()">
                </li>
             </ul>
          </form>
       </section>
       <input type="button" value="MD5加密测试" class="jm" onclick="">
    </article>
    <script type="text/javascript">
      /*$(function(){
         $(".jm").click(function(){
           alert(MD5("1234567")) ;
         });
         
      });*/
   
    </script>
</body>
</html>
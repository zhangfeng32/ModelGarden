// JavaScript Document
/**************注册验证Begin****************/
$(function(){
		
		$(".phoneNumber").focus(function(){
			$(this).css({"border-color":"#7A9ECE"});
			$(this).parent(".ulLi").siblings("h6.msgError").remove();
		});
		$(".phoneNumber").blur(function(){
			if($(this).val()==""||$(this).val()==null){
				if(!$(this).parent(".ulLi").siblings().hasClass("msgError")){
					$(this).css({"border-color":"#F3696E"});
					$(this).parent(".ulLi").after("<h6 class='msgError'>手机号不得为空</h6>");
				}
			}
			/*else
            {
                if(!valiMobilePhone($(".phoneNumber"))){
                    $(".phoneNumber").css({"border-color":"#F3696E"});
                    $(this).parent(".ulLi").after("<h6 class='msgError'>手机号错误</h6>");
                  }
            }*/
				
		});
        $(".phoneNumber").keyup(function(event){
            event.preventDefault();
            var thisVal = $(this).val();
            if(isNaN(thisVal)){
                $(this).onfocus;  
                $(this).val("");   
            }
            /*if(valiMobilePhone($(this))){
                $(".daojishi").css({
                    background: '#EC008C',
                    color:'#fff'               
                });
            }*/
        });
         $(".numberYz").keyup(function(event){
            event.preventDefault();
            var thisVal = $(this).val();
            if(isNaN(thisVal)){
                $(this).onfocus;  
                $(this).val("");   
            } 
        });
         $(".numberYz").focus(function(){
            $(this).css({"border-color":"#7A9ECE"});
            $(this).parent(".ulLi").siblings("h6.msgError").remove();
        });
		$(".LogoPass").focus(function(){
			$(this).css({"border-color":"#7A9ECE"});
			$(this).parent(".ulLi").siblings("h6.msgError").remove();
		});
		$(".LogoPass").blur(function(){
			if($(this).val()==""||$(this).val()==null){
				if(!$(this).parent(".ulLi").siblings().hasClass("msgError")){
					$(this).css({"border-color":"#F3696E"});
					$(this).parent(".ulLi").after("<h6 class='msgError'>请输入密码</h6>");
					
				}
			}
			else if($(this).val().length<6){
					$(this).css({"border-color":"#F3696E"});
					$(this).parent(".ulLi").after("<h6 class='msgError'>密码个数不得少于6位</h6>");
					
				}
			else{
				$(this).css({"border-color":"#E1E1E1"});
				
				}
		});
		$(".LogoPass").keyup(function(){
		/*	this.value=this.value.replace(/\s/g,'');*/
			$(".ConFrimPass").val("");
			$(".ConFrimPass").css({"border-color":"#E1E1E1"});
			$(".ConFrimPass").siblings("h6.msgError").remove();
			
			});
		
		$(".ConFrimPass").focus(function(){
			$(this).css({"border-color":"#7A9ECE"});
			$(this).parent(".ulLi").siblings("h6.msgError").remove();
		});
		$(".ConFrimPass").blur(function(){
			if($(this).val()==""||$(this).val()==null){
				if(!$(this).parent(".ulLi").siblings().hasClass("msgError")){
					$(this).css({"border-color":"#F3696E"});
					$(this).parent(".ulLi").after("<h6 class='msgError'>确认密码不得为空</h6>");
					
				}
			}
			else if($(this).val()!=$(".LogoPass").val()){
					$(this).css({"border-color":"#F3696E"});
					$(this).parent(".ulLi").after("<h6 class='msgError'>确认密码与密码不一致</h6>");
					
				}
			else{
					$(this).css({"border-color":"#E1E1E1"});
					
				}
		});
		
    $("#subReg").click(function(){
        subReg();
    });

	})


	
	function subReg(){
        var flag = "yes";
		var phoneNumber,numberYz,LogoPass,ConFrimPass;
		phoneNumber = $(".phoneNumber").val();
        numberYz = $(".numberYz").val();
		LogoPass = $(".LogoPass").val();
		ConFrimPass = $(".ConFrimPass").val();

		if(phoneNumber==null||phoneNumber==""){//.parent(".ulLi").after("<h6 class='msgError'>
			if(!$(".phoneNumber").parent(".ulLi").siblings().hasClass("msgError")){
				$(".phoneNumber").css({"border-color":"#F3696E"});
				$(".phoneNumber").parent(".ulLi").after("<h6 class='msgError'>手机号不得为空</h6>");
				
				}
            flag = "no";
		}
        if(numberYz==null||numberYz=="")
        {   
            if(!$(".numberYz").parent(".ulLi").siblings().hasClass("msgError")){
                $(".numberYz").css({"border-color":"#F3696E"});
                $(".numberYz").parent(".ulLi").after("<h6 class='msgError'>验证码不得为空</h6>");
          }
            flag='no';
            
        }
        else if(numberYz.length!=6){
            if(!$(".numberYz").parent(".ulLi").siblings().hasClass("msgError")){
                $(".numberYz").css({"border-color":"#F3696E"});
                $(".numberYz").parent(".ulLi").after("<h6 class='msgError'>验证码个数错误</h6>");
            }
            flag='no';
        }  
		if(LogoPass==null||LogoPass==""){
			if(!$(".LogoPass").parent(".ulLi").siblings().hasClass("msgError")){
				$(".LogoPass").css({"border-color":"#F3696E"});
				$(".LogoPass").parent(".ulLi").after("<h6 class='msgError'>密码不得为空</h6>");
				
				}
            flag = "no";
		}
        else
        {
            if(LogoPass.length<6)
            {
                if(!$(".LogoPass").parent(".ulLi").siblings().hasClass("msgError"))
                {
                    
                    $(".LogoPass").css({"border-color":"#F3696E"});
                    $(".LogoPass").parent(".ulLi").after("<h6 class='msgError'>密码个数不得少于6位</h6>");
                }
                
                flag = "no";
            }

        }
		if(ConFrimPass==null||ConFrimPass==""){
			if(!$(".ConFrimPass").parent(".ulLi").siblings().hasClass("msgError")){
				$(".ConFrimPass").css({"border-color":"#F3696E"});
				$(".ConFrimPass").parent(".ulLi").after("<h6 class='msgError'>确认密码不得为空</h6>");
				
				}
            flag = "no";
		}
        if(ConFrimPass!=LogoPass){
            if(!$(".ConFrimPass").parent(".ulLi").siblings().hasClass("msgError")){
                $(".ConFrimPass").css({"border-color":"#F3696E"});
                $(".ConFrimPass").parent(".ulLi").after("<h6 class='msgError'>确认密码与密码不一致</h6>");
                
            }
            flag = "no";
        }
        if(flag=="yes")
        {
            //alert("注册的前台验证已经通过");return false;
            var LogoPassMD5 = MD5(LogoPass);
            var ConFrimPassMD5 = MD5(ConFrimPass);
            $(".LogoPass").val(LogoPassMD5);
            $(".ConFrimPass").val(ConFrimPassMD5);
            $.ajax({
                type: "POST",
                //url: Routing.generate("PageBundle_user_registerTwo"),
                url:"/ModelGarden/register",
                data:$('#reg_form').serialize(),// 表单序列化
                success: function(result)
                {
                    if(result.code == 200)
                    {
                        /*var memberId = result.memberId;
                        var vendorId = result.vendorId;
                        var nickname = result.nickname;
                        var email = result.email;
                        var avatar = result.avatar;
                        var vendorStatus = result.vendorStatus;*/
                        alert("retrunData Sucess!!");
                        location.href = "../html/success.html";
                        //location.href = Routing.generate("PageBundle_user_regSuccJump",{"memberId":memberId,"vendorId":vendorId,"nickname":nickname,"email":email,"avatar":avatar,"vendorStatus":vendorStatus })
                    }
                    else
                    {
                        alert(result.errorMsg);
                        return false;
                    }
                }
            });
        }

	}

//注册_获取短信验证码
$(function  ()
{
    $(".zhuceVali").click (function() {
        /*if(!valiMobilePhone($("#findpass_phone"))){
         return false;
         }*/
        var validCode=true,phoneNumber;
        phoneNumber = $('#phoneNumber').val();
        if(phoneNumber==null || phoneNumber=="")
        {
            alert("手机号不能为空");
            return false;
        }

        var clicktime =  $(".zhuceVali").attr("clicktime");
        $(".zhuceVali").attr("clicktime","2");
        if(clicktime==2){
            return false;
        }
        $.ajax({
            type: "POST",
            //url: Routing.generate("m_10_MobileBundle_MUser_theOnlyPhone"),
            url:"/ModelGarden/getVerifyCode",
            data: { telphone:phoneNumber  },
            success: function(result)
            {
                if(result.code == 300)
                {
                    $(".errorReg").show().text(result.errorMsg);
                    if(!$(".phoneNumber").parent(".ulLi").siblings().hasClass("msgError"))
                    {
                        $(".phoneNumber").css({"border-color":"#F3696E"});
                        $(".phoneNumber").parent(".ulLi").after("<h6 class='msgError'>"+result.errorMsg+"</h6>");
                    }
                }
                else
                {
                    //打开注册页里禁用的input
                    $(".numberYz").removeAttr("disabled");
                    //向注册页面里隐藏域赋值
                    $("#activateId").val(result.activateId);

                    var times=60;
                    var code=$(".zhuceVali");
                    if (validCode)
                    {
                        validCode=false;
                        code.addClass("msgs1");
                        $("#phoneNumber").attr('readonly','readonly');
                        var t=setInterval(function  ()
                        {
                            times--;
                            code.val(times+"s后重新获取");

                            if (times==0) {
                                clearInterval(t);
                                code.val("获取验证码");
                                validCode=true;
                                code.removeClass("msgs1");

                                $("#phoneNumber").removeAttr("readonly");

                                $(".zhuceVali").attr("clicktime","1");
                            }
                        },1000)
                    }
                }
            }
        });
    });

});

    function numError_fun()
    {
        var numError = $('#regForm .msgError').length;
        if(numError)
        {
            $('#subReg').css("background-color", "#ec008c");
            $('#subReg').val('注  册');
        }
    }
		
		$(document).keyup(function(event){
		  if(event.keyCode ==13){
              $('#subReg').css("background-color", "#ccc");
              $('#subReg').val('注册中...');
              subReg();
              setTimeout("numError_fun()", 1000);
		  }
		});

/**************注册验证End****************/
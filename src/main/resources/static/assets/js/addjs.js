//选项卡长度 arryLen-10
var arryLen=20;
var   comArry=getArry(arryLen);
function getArry(length){
    var arry1=[];
    for(var i=10;i<length;i++){
        arry1.push(i);
    }
    return arry1;
}
function  getSingleNum(){
    if(comArry && comArry.length>0){
        return comArry.pop();
    }else{
        return -1;
    }
}

//更换主题
function changeTheme(obj,fun){
	fun?fun(obj):null;
	if(obj.find("iframe").length>0){
	for(var i=0;i<obj.find("iframe").length;i++){
	changeTheme(obj.find("iframe").eq(i).contents().find('body'),fun)
	}
	}
}
function JuiTheme(className){
	changeTheme($("body"),function(obj){
		obj.removeClass().addClass(className);
		$.cookie('theme',className)
	})
}
if($.cookie('theme')){
	$("body").removeClass().addClass($.cookie('theme'));
}else{
	var theme = $('body').attr("class");
	$.cookie('theme',theme)
}
//导航横向滚动
function navscroll(){
	var w = $(window).width(),
		 w1 = $('#navbar .jui-navbar').width(),
		 w2 = w-$('#navbar .navbar-header').width()-330,
		 w3 = 0,
		 liIndex = 0,
		 menuBox = $('#navbar .jui-navbar'),
		 menu = menuBox.find('ul'),
		 wArray = [],
		 limit;
	menuBox.find('ul').find('li').each(function(){
		w3 += $(this).outerWidth();
	}) 
	menuBox.append('<div class="navbarleft"></div><div class="navbarright"></div>');
	var scollBtnStop = function(){
		var w4 = 0; 
		limit =-(w3-w2);
		wArray = [];
		menuBox.find('ul').find('li').each(function(){
			if(w4 > limit){
				wArray.push(w4);
				w4 -= $(this).outerWidth();
			}else if(w4 <= limit){
				wArray.push(w4);
				return false;
			}	
		})
		liIndex = 0;
		menuBox.width(w2);
		menu.css({
			"width":w3+2+'px',
			"position":'absolute',
			"top":0,
			"left":0
		})	
	}
	if(w3>w2){
		scollBtnStop();
		menuBox.find(".navbarleft").show();
		menuBox.find(".navbarright").show();	
	}else{
		menuBox.find(".navbarleft").hide();
		menuBox.find(".navbarright").hide();
	}
	menuBox.find(".navbarleft").click(function(){
		console.log(liIndex)
		liIndex++;
		menu.stop(true,true);
		liIndex >= wArray.length-1?liIndex=wArray.length-1:null;
		menu.animate({left:wArray[liIndex]},300);
		
		
	})
	menuBox.find(".navbarright").click(function(){
        console.log(liIndex)
		liIndex--;
		menu.stop(true,true);
		liIndex <= 0?liIndex=0:null;
		menu.animate({left:wArray[liIndex]},300);
		
		
	})
	$(window).on("resize",function(){
		w = $(window).width();
		w1 = $('#navbar .jui-navbar').width();
		w2 = w-$('#navbar .navbar-header').width()-330;
		w3 = 0;
		menuBox.find('ul').find('li').each(function(){
		w3 += $(this).outerWidth();
		}) 
		if(w3>w2){
			scollBtnStop();	
			menuBox.find(".navbarleft").show();
			menuBox.find(".navbarright").show();	
		}else{
			scollBtnStop();
			menuBox.find(".navbarleft").hide();
			menuBox.find(".navbarright").hide();
		}
	})
}
//iframe大列表切换

$(document).on("click",".iframes-nav li",function(){
	var index = $(this).parent().find('li').index(this);
    var main = $(this).parents('.iframes-nav-box').siblings('.iframes-content').find('.iframes-main');
	$(this).addClass("active").siblings().removeClass("active");
	main.hide();
	main.eq(index).fadeIn();
})
$(document).on("click",".iframes-nav li .fa-times-circle",function(){
    comArry.push (this.lang.split("_")[1]);
	var parentDom = $(this).parent().parent();
	var index = parentDom.parent().find('li').index(parentDom);
	var main = $(this).parents('.iframes-nav-box').siblings('.iframes-content').find('.iframes-main');
	$(this).parent().parent().remove();
	main.eq(index).remove();
	if($(".iframes-nav").find("li.active").length <1){
        $(".iframes-nav").find("li").last().addClass("active");
       var main1=  $('.iframes-content').find('.iframes-main')
        main1.hide();
        main1.last().fadeIn();
	}

	return false;
})

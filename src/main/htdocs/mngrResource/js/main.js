//modal
function openLayer(sGetName){
	var $layer = $("#"+ sGetName);
	$layer.addClass("on");
}
function closeLayer(sGetName){
	$("#"+ sGetName).removeClass("on");
}

$(document).ready(function(){


    //Traffic_management tab Btn
    (function(){
        var monthBtn = document.querySelectorAll('.month');

        for(var i = 0; i < monthBtn.length; i++){
            monthBtn[i].addEventListener('click', function(e){
                for(var j = 0; j < monthBtn.length; j++){
                    monthBtn[j].classList.remove('active');
                    e.target.classList.add('active');
                }
            });
        }
    })();

    //SlideNav
	$(".top_nav").mouseenter(function(){
		$(this).find(".topmenu>li .smenu").stop().slideDown();
		$(".bg_box").stop().slideDown();
	});

	$("header").mouseleave(function(){
		$(this).find(".topmenu>li .smenu").stop().slideUp();
		$(".bg_box").stop().slideUp();
	});

	//MainTabBoard
	$('.tab-menu li a').click(function(){
		$('.tab-menu li a').removeClass('active');
		$(this).addClass('active');
		$('#tab-content > div').hide();
		$( $(this).attr('href')).show();
	   return false;
   });

	//File inputbox
    var fileTarget = $('.filebox .upload-hidden');
	fileTarget.on('change', function(){

		if(window.FileReader){
			var filename = $(this)[0].files[0].name;
		} else {
			var filename = $(this).val().split('/').pop().split('\\').pop();
		}
			$('.filebox .upload-name').val(filename);
		});

	//회원상세 법정대리인동의시
	$(".agent_view").hide();
	if($('#check_per').hasClass('agent') === true) {
			$(".agent_view").show();
			$(".cons_view").hide();
		}else{
			$(".agent_view").hide();
			$(".cons_view").show();
		}

	//sms발송관리 팝업 라디오체크
	$('.sms_bottom').hide();
	$('.reservation').click(function(){
		$('.sms_bottom').show();
	});
	$('.hidden').click(function(){
		$('.sms_bottom').hide();
	});

	//통계 라디오체크
	$('.table2,.table3').hide();
	$('#t_1').click(function(){
		$('.table1').show();
		$('.table2,.table3').hide();
	});
	$('#t_2').click(function(){
		$('.table2').show();
		$('.table1,.table3').hide();
	});
	$('#t_3').click(function(){
		$('.table3').show();
		$('.table1,.table2').hide();
	});
/**
 * 2020.08.04 김명진 수정
 *  하기 내용은 조건이 맞지않아 푸터 쪽으로 이동 처리 함
//메인메뉴의 bg_box높이값 수정
var navHeight = $('.smenu').outerHeight();
$('.bg_box').css('height',navHeight);
 */

//트리메뉴
var treeBtn=$(".treeMenu a");
var treeLi=$(".treeMenu li");
var firstLi=$(".treeMenu>ul>li:first-child");
var lastLi=$(".treeMenu ul li:last-child");
var lastUl=lastLi.children("ul");

firstLi.addClass("first");
lastLi.addClass("last");
lastUl.addClass("last");

for(var i=0; i<treeLi.length; i++){
var thisLi=treeLi.eq(i);
var haveUl=thisLi.children("ul");
var haveUlNum=haveUl.length;
var thisbg=thisLi.attr("class");

	if(haveUlNum=="0"){
		if(thisbg=="first"){
			 thisLi.addClass("none_first");
		}else if(thisbg=="last"){
			thisLi.addClass("none_last");
		}else{
			thisLi.addClass("none");
		}
	}else if(haveUlNum!="0"){
		thisLi.children("a").before("<img src='../../assets/images/btn_plus.gif' alt='하위메뉴 보기' />");
	}
}

var treeBtn=$(".treeMenu li img");

treeBtn.click(function(){
	var thisNum=treeBtn.index(this);
	var thisBtn=treeBtn.eq(thisNum);
	var thisSubUl=thisBtn.parent().children("ul");

	if(thisSubUl.is(":visible")){
		thisBtn.attr("src",thisBtn.attr("src").replace("../../assets/images/minus.gif","../../assets/images/plus.gif"));
		thisSubUl.slideUp("500");
	}else{
		thisBtn.attr("src",thisBtn.attr("src").replace("../../assets/images/plus.gif","../../assets/images/minus.gif"));
		thisSubUl.slideDown("500");
	}


})

});

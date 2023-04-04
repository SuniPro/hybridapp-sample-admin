let row_1 = $('#row_1').eq(0);

$("input[name='add_row[]']").click(function () {
	$('#detail_info > tbody:last').append(row_1.clone(true));
	$('#detail_info > tbody:last > tr:last').attr('id', ('row_' + $(".data-row-cnt").length));
})

$('input[name="del_row[]"]').click(function () {
	let del_obj = $(this).parent().parent().parent();
	if (del_obj.attr('id') !== '1row') {
		del_obj.remove();
	}
})

// 이미지 선택
// let MuilefileUploadClass = $("#MuilefileUploadClass");

$('#multi_pt_btn').click(function () {
	$('#multi_pt').click();
	/*if (noticePopNm.val() === "Y") {
		fnMngrAlt("이미지가 등록 되어있습니다.");
	}
	else {*/
	// MuilefileUploadClass.click();
	// }
})

// let noticePopNm = $("#noticePopNm");
/*noticePop.change(function () {
	const imageName = noticePop.val().split('\\').pop().toLowerCase();
	noticePopNm.append("<div><a href='javascript:;' onclick='noticePopDel(this)'>x</a>&nbsp;" + imageName + "</div>");
	// noticePopNm.val("Y");
})

let noticePopDel = function (o) {
	const agent = navigator.userAgent.toLowerCase();
	if ((navigator.appName === 'Netscape' && navigator.userAgent.search('Trident') !== -1) || (agent.indexOf("msie") !== -1)) { // ie 일때 input[type=file] init.
		noticePop.replaceWith(noticePop.clone(true));
	}
	else { // other browser 일때 input[type=file] init.
		// noticePop.val("");
	}
	$(o).parent().remove();
	// noticePopNm.text("");
	// noticePopNm.val("N");
}*/
$(function () {
	// $('.multi_pt').MultiFile({
	/*$('#multi_pt').MultiFile({
		// your options go here
		max: 5,
		accept: 'gif|jpg|png',
		// list: "$(this).siblings('.multi_pt_list')",
		STRING: { //Multi-lingual support : 메시지 수정 가능
			denied: "$ext 는(은) 업로드 할 수 없는 파일확장자입니다.",
			toomany: "업로드할 수 있는 최대 갯수는 $max개 입니다.",
			// remove: "제거", //추가한 파일 제거 문구, 이미 태그를 사용하면 이미지사용가능
			duplicate: "$file 은 이미 선택된 파일입니다.",
			// selected: '$file 을 선택했습니다.',
			// toomuch: "업로드할 수 있는 최대크기를 초과하였습니다.($size)",
			// toobig: "$file 은 크기가 매우 큽니다. (max $size)"
		}
	});*/
});
``
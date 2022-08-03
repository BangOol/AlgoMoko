/*
    항목				패턴
    아이디				대문자, 숫자 8~10 자
    비밀번호			특수문자를 포함하는 영, 숫자 8~10 자
    비밀번호 확인		비밀번호와 같다.
    이름				한글 2~10 자
    이메일				/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i; ★ 이메일 규칙은 그냥 복붙하세용!
                        /i (case insensitive, 대소문자 구분하지 말라는 것)
                        /g (global, 정규식은 패턴을 찾으면 중지하는데, 중지하지 말고 모두 찾으라는 것)
                            js에서 test() 말고 replace()할 때 필요하다. 
    생일				숫자 4자 - 2자 - 2자
*/

// 1. 패턴 정의
const usernamePattern = /^[0-9A-Z]{8,10}$/;//안쓰는 기능..
const unamePattern = /^[가-힣]{2,10}$/;
// ()는 독립된 조건, ?=는 앞부터 찾으라는 것(전방 탐색)
// .은 임의의 글자가 * 0글자 이상 -> 특수문자가 1 글자 이상
const passwordPattern = /^(?=.*[!@#$%^&*])^[A-Za-z0-9!@#$%^&*]{8,50}$/;
const midPattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
const birthPattern = /^[0-9]{4}-[0-9]{2}-[0-9]{2}$/;

// 2. 오류 메시지 출력 함수 : 아이디부터 생일까지 6개의 input에 대한 공통 오류 메시지 처리
// function printCheckMessage(입력 값, 패턴, 출력할 메시지, 출력할 span)
function check(value, pattern, message, span) {
    // 입력 안 함
    if (value == "") {
        span.text("필수 입력 항목 입니다.").attr('class', 'fail');
        return false;
    }
    // 패턴 체크
    if (pattern.test(value)==false) {
        span.text(message).attr('class','fail');
        console.log(value);
        console.log(pattern.test(value));
        return false;
    }
    return true;
}

// 3-1. 입력한 아이디 확인 함수
//function usernameCheck() {
//    // 입력한 아이디를 대문자로 변경
//    const $username = $("#username").val().toUpperCase();
//    $("#username").val($username);
//    return check($username, usernamePattern, "아이디는 대문자와 숫자 8~10자입니다", $("#username_msg"));
//}

// 3-2. 입력한 이름 확인 함수
function unameCheck() {
    $("#uname_msg").text("");
    return check($("#uname").val(), unamePattern, "이름은 한글 2~10자입니다", $("#uname_msg"));
}

// 3-3. 입력한 비밀번호 확인 함수
function mpwCheck() {
    $("#mpw_msg").text("");
    return check($("#mpw").val(), passwordPattern, "비밀번호는 영숫자와 특수문자 8~10자입니다", $("#pw1_msg"));	
}

// 3-4. 입력한 비밀번호 확인이 비밀번호와 일치하는지 확인 함수 - 패턴이 없으므로 check() 함수를 사용하지 않는다.
function mpw2Check() {
    $("#mpw2_msg").text("");
    const $password2 = $("#mpw2").val();
    if($password2=="") {
        $("#mpw2_msg").text("필수 입력 항목 입니다.").attr("class","fail");
        return false;
    } 
    if($password2!==$("#mpw").val()) {
        $("#mpw2_msg").text("비밀번호가 일치하지 않습니다").attr("class","fail");
        return false;
    }
    return true;
}

// 3-5. 입력한 이메일 확인 함수
function midCheck() {
    $("#mid_msg").text("");
    return check($("#mid").val(), midPattern, "정확한 이메일형식을 입력하세요", $("#mid_msg"))
    
}

// 3-6. 입력한 생일 확인 함수
function birthCheck() {
    $("#birth_msg").text("");
    return check($("#birth").val(), birthPattern, "정확한 생일을 입력하세요", $("#birth_msg"))
}
// 3-7 성별 확인
function sexCheck(){
 	$("#sex_msg").text("");
 	const $sex = $("#sex").val();
 	if($sex=="") {
        $("#sex_msg").text("필수 입력 항목 입니다.").attr("class","fail");
        return false;
    } 
    	return true;
}
// 3-8 키 확인
function heightCheck(){
	$("#height_msg").text("");
	const $height = $("#height").val();
	if($height=="") {
        $("#height_msg").text("필수 입력 항목 입니다.").attr("class","fail");
        return false;
    } 
    	return true;
}
// 3-9 몸무게 확인
function weightCheck(){
	 $("#weight_msg").text("");
	 const $weight = $("#weight").val();
	 if($weight=="") {
        $("#weight_msg").text("필수 입력 항목 입니다.").attr("class","fail");
        return false;
    } 
    	return true;
}
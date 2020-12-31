/**
 * JavaScript의 모듈화
 * 
 * 함수들을 하나의 모듈처럼 묶음으로 구성하는 것을 의미합니다.
 * 화면 내에서 JavaScript 처리를 하다 보면 이벤트 처리와 DOM, Ajax처리 등이
 * 복잡하게 섞여서 유지보수가 힘듭니다. 따라서 JavaScript를 하나의 모듈처럼 구성하여 사용합니다.
 * 
 * reply modules
 */

console.log("Reply Modules...");

var replyService = (function(){
	
	//댓글 추가
	//callback, error는 외부에서 전달받을 함수입니다.
	//함수의 파라미터 개수를 일치시킬 필요가 없기 때문에
	//사용 시 callback이나 error와 같은 파라미터는 상황에 따라 작성합니다.
	function add(reply, callback, error){
		console.log("add reply...");
		
		$.ajax({
			type: "POST",
			url: "/replies/new",
			data: JSON.stringify(reply),
			contentType: "application/json;charset=utf-8",
			success : function(result, status, xhr){
				if(callback){
					console.log(result);
					//외부에서 전달받은 값이 있다면 결과를 해당 함수에 전달하여 사용합니다.
					callback(result);
				}
			},
			error : function(xhr, status, er){
				if(error){
					//외부에서 전달받은 값이 있다면 er 객체를 전달하여 사용합니다.
					error(er);
				}
			}
		});
	}
	
	//댓글 삭제
	function remove(rno, callback, error){
		console.log("remove reply...");
		$.ajax({
			type: "DELETE",
			url: `/replies/${rno}`,
			success : function(result, status, xhr){
				if(callback){
					console.log(result);
					callback(result);
				}
			},
			error : function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		});
	}
	
	//댓글 수정
	function update(reply, callback, error){
		console.log("modify reply...");
		$.ajax({
			type: "PUT",
			url: `/replies/${reply.rno}`,
			data: JSON.stringify(reply),
			contentType: "application/json;charset=utf-8",
			success: function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error: function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		});
	}
	
	//댓글 목록
	function getList(param, callback, error){
		var bno = param.bno;
		
		//var v = a || b	: a에 값이 있으면 a를 할당, 없으면 b를 할당
		var page = param.page || 1;
		
		//$.getJSON은 통신 관련 상세 설정은 지원하지 않으나, json 데이터를 화면에 출력할 때 
		//간결한 소스코드를 활용할 수 있게 해줍니다.
		
		//컨트롤러에 있는 페이지 목록 조회 메소드를 json 타입으로 호출합니다.
		$.getJSON(`/replies/pages/${bno}/${page}.json`, 
				function(data){
					//페이지 목록 조회 컨트롤러 메소드의 리턴타입인 ReplyPageDTO를 data로 전달받습니다.
					if(callback){
						//전달받은 메소드의 매개변수에 각각 댓글 전체 개수, 요청한 페이지의 게시글 List를 전달해줍니다.
						callback(data.replyCnt, data.list);
					}
		}).fail(function(xhr, status, er){
			if(error){
				error(er);
			}
		}); 
	}
	return {add : add, getList : getList, remove : remove, update : update};
})();



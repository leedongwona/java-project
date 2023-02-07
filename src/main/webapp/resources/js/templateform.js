/**
 * 
 */
 
function ufshowform2(result, filename){
	let isImageFile = isImgFile(filename);
	let originalFilename = getOriginalFilename(filename);	
	
	let tagstr = "";
	
	if(isImageFile){
		tagstr = `
		<li class="col-sm-4 mt-3">
    		<div class="card">
      			<div class="card-body">
      				<img src="/board/showimgfile?filename=${filename}" width="100px" height="100px" alt="${filename}"/>
        			<p class="card-text">${originalFilename}</p>
        			<a href="#" class="btn btn-danger del">삭제</a>
      			</div>
    		</div>
  		</li>
	`;
	}else{
		tagstr = `
		<li class="col-sm-4 mt-3">
    		<div class="card">
      			<div class="card-body">
      				<img src="/resources/img/gf.png" width="100px" height="100px" alt="${filename}"/>
        			<p class="card-text">${originalFilename}</p>
        			<a href="#" class="btn btn-danger del">삭제</a>
      			</div>
    		</div>
  		</li>
	`;
	}
	
	
	
	return tagstr;
} 
 
 
function getOriginalFilename(filename){
	
	let isImage = isImgFile(filename);
	let idx = -1;
	
	if(isImage){
		idx = filename.indexOf("_", 14) + 1;
	} else{
		idx = filename.indexOf("_") + 1;
	}
	
	let originalFilename = filename.substring(idx);
	
	return originalFilename;
}

function ufshowform(result, filename){
	let isImageFile = isImgFile(filename);
	let originalFilename = getOriginalFilename(filename);	
	
	let tagstr = "";
	
	if(isImageFile){
		tagstr = `
		<li class="col-sm-4 mt-3">
    		<div class="card">
      			<div class="card-body">
      				<a href="/board/showOrDownload?filename=${filename}" target="_blank">
      					<img src="/board/showimgfile?filename=${filename}" width="100px" height="100px" alt="${filename}"/>
        				<p class="card-text">${originalFilename}</p>
        			</a>
      			</div>
    		</div>
  		</li>
	`;
	}else{
		tagstr = `
		<li class="col-sm-4 mt-3">
    		<div class="card">
      			<div class="card-body">
      				<a href="/board/showOrDownload?filename=${filename}">
      					<img src="/resources/img/gf.png" width="100px" height="100px" alt="${filename}"/>
        				<p class="card-text">${originalFilename}</p>
        			</a>
      			</div>
    		</div>
  		</li>
	`;
	}
	
	
	
	return tagstr;
}
 
 
 
 
 
function isImgFile(filename){
	let idx = filename.lastIndexOf(".") +1;
	let formatName = filename.substring(idx);
	
	let arr = ["png", "gif", "jpg", "jpeg"];
	
	let isImageFile = false;
	
	for(let i=0; i<arr.length; i++){
		if(arr[i] == formatName.toLowerCase()){
			isImageFile = true;
			break;
		}
	}
	
	return isImageFile;
} 


function futest2(result, filename){
	let isImageFile = isImgFile(filename);
	let tagstr = "";
	
	if(isImageFile){
		tagstr = `
		<li class="col-sm-4 mt-3">
    		<div class="card">
      			<div class="card-body">
      				<img src="${result}" width="100px" height="100px" alt="${filename}"/>
        			<p class="card-text">${filename}</p>
       				<a href="#" class="btn btn-danger del">삭제</a>
      			</div>
    		</div>
  		</li>
	`;
	}else{
		tagstr = `
		<li class="col-sm-4 mt-3">
    		<div class="card">
      			<div class="card-body">
      				<img src="/resources/img/gf.png" width="100px" height="100px" alt="${filename}"/>
        			<p class="card-text">${filename}</p>
       				<a href="#" class="btn btn-danger del">삭제</a>
      			</div>
    		</div>
  		</li>
	`;
	}
	
	
	
	return tagstr;
} 

 
function futest11(result, filename){
	let tagstr = `
		<div>
			<img src="${result}" class="fu" height="100px" width="="100px">
			<span class="del" data-filename="${filename}"> X </span>
		</div>
	`;
	
	return tagstr;
} 


function futest(result){
	let tagstr = `
	<img class="fu" width="100px" height="100px"
	src="${result}">
	`;
	
	return tagstr;
	
} 


function getReplyAll2(bno, reply_list){
	
	let tagstrs = '';
	
	$.getJSON("/replies/"+bno+"/all", function(result){
		
		
		
		for(let i=0; i<result.length; i++){
			let qnareplyDto = result[i];
			let tagstr = reply_form(qnareplyDto.rno, 
			qnareplyDto.id, qnareplyDto.reply, 
			qnareplyDto.updatedate)
			
			tagstrs = tagstrs + tagstr;
		}
		
		reply_list.html(tagstrs)
		
	});
}

 
 
function getReplyAll(bno, reply_list){
	
	let tagstrs = '';
	
	$.getJSON("/replies/"+bno+"/all", function(result){
		
		
		
		for(let i=0; i<result.length; i++){
			let replyDto = result[i];
			let tagstr = reply_form(replyDto.rno, 
			replyDto.id, replyDto.reply, 
			replyDto.updatedate)
			
			tagstrs = tagstrs + tagstr;
		}
		
		reply_list.html(tagstrs)
		
	});
}
 
 
 
function reply_form(rno, id, reply, updatedate){
	
	let tagstr = `
	<div class="card row my-4">
				<h6 class="card-header">
					댓글번호 : <span class="reply_rno">${rno}</span>
					<span class="reply_id float-right">작성자: ${id}</span>
					
				</h6>
				<div class="card-body" data-rno="${rno}" data-id="${id}">
					<p class="card-text reply">${reply}</p>
					<a href="#" class="btn btn-warning btn-sm read_btn_replyui_update">수정</a>
					<a href="#" class="btn btn-warning btn-sm read_btn_replyui_delete">삭제</a>
					<span class="float-right">수정일: ${updatedate}</span>
				</div>
			</div>
	`;
		
	return tagstr;
}
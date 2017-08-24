// 글 임시저장, 작성완료 , 제출 관리
function write_temporary(idx,author_save){

    $.ajax({
        type:'POST',
        url:'/temporary.htm',
        data:{'author_save':author_save,'idx':idx},
        success:function(){
            location.href="/index.htm"
        },
        error:function(request,status,error)
        {
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }

    });

}

function commit(idx,author_save){

    $.ajax({
        type:'POST',
        url:'/commit.htm',
        data:{'author_save':author_save,'idx':idx},
        success:function(){
            location.href="/index.htm"
        },
        error:function(request,status,error)
        {
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }

    });

}



// 글 기간이 끝나면 삭제되는 기능
function delete_temporary(){
   var idx =  $("#remove").val();
    $.ajax({
        type:'POST',
        url:'/writing.htm',
        data:({idx:idx}),
        success:function(){
            location.href="/index.htm"
        },
        error:function(request,status,error)
        {
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }

    });

}


function submit(idx,author_save){

    $.ajax({
        type:'POST',
        url:'/submit.htm',
        data:{'author_save':author_save,'idx':idx},
        success:function(){
            location.href="/index.htm"
        },
        error:function(request,status,error)
        {
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }

    });

}

// 첫글 작성
function first_submit(author_save){

    $.ajax({
        type:'POST',
        url:'/firstwriting.htm',
        data:{'author_save':author_save},
        success:function(){
            location.href="/index.htm"
        },
        error:function(request,status,error)
        {
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        }

    });

}
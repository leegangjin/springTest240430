console.log("comment js in");

// cmtBtn 버튼을 클릭하면 bno , writer , content를 비동기로 db에 넣기
document.getElementById('cmtAddBtn').addEventListener('click',()=>{
const cmtWriter = document.getElementById('cmtWriter').innerText; //writer
const cmtText = document.getElementById('cmtText').value; //content

//디브나 스팬 처럼 열고닫고 사이에있는값은 innertext
if(cmtWriter ==null || cmtText==''){
    alert('댓글을 입력해주세요.');
    document.getElementById('cmtText').focus();
    return false;
}else{
    let cmtData ={
        bno : bnoVal,
        writer : cmtWriter,
        content : cmtText
    }
    console.log(cmtData);
    postCommentToServer(cmtData).then(result =>{
        console.log(result);
        if(result ==1){
            alert('댓글입력완료');
            document.getElementById('cmtText').value="";
            //화면에 뿌리기
            spreadCommentList(bnoVal);

        }
    
      

    })
}

});

async function postCommentToServer(cmtData){
try {
    const url ="/comment/post";
    const config ={
        method:"post",
        headers:{
            'Content-type' : 'application/json; charset=utf-8'
        },
        body :JSON.stringify(cmtData)
    };
    const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
} catch (error) {
    console.log("Error")
}

}
async function getCommentListFromServer(bno,page){
    try {
        const resp = await fetch("/comment/"+bno+"/"+page);
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}



function spreadCommentList(bno,page=1){
    getCommentListFromServer(bno,page).then(result =>{
        console.log(result);
        //댓글 뿌리기
        const ul = document.getElementById('cmtListArea');
        // ul.innerHTML ='';
        if(result.cmtList.length > 0){
            if(page==1){
                ul.innerHTML ='';
            }
           for(let cvo of result.cmtList){
            let li = `<li class ="list-group-item" data-cno="${cvo.cno}">`;
            li+= `<div class="mb-3"> no. ${cvo.cno}  `;
            li+= `<div class="fw-bold">${cvo.writer}</div>`;
            li+=`${cvo.content}`;
            li+= `</div>`;
            li+= `<span class="badge rounded-pill text-bg-warning">${cvo.regDate}</span>`;
            //수정 삭제버튼
            li += `<button type="button"  class="btn btn-danger btn-sm mod"  data-bs-toggle="modal" data-bs-target="#myModal">수정</button>`;
            li += `<button type="button" class="btn btn-warning btn-sm del" id="del">삭제</button>`;
            li+= `</li>`;
            ul.innerHTML += li;
           
           
           
           
           
            }

             //더보기 버튼 코드
             let moreBtn = document.getElementById('moreBtn');
             console.log(moreBtn);
             //moreBtn 표시되는 조건
             //pgvo.pageNo =1 / realEndPage =3
             //realEndPage보다 현재 내 페이지가 작으면 표시
             if(result.pgvo.pageNo < result.realEndPage){
                //style="visibility:hidden"=숨김 visibility: visible = 표시
                moreBtn.style.visibility = 'visible'; //버튼 표시
                moreBtn.dataset.page = page +1; //1페이지 늘림

             }else{
                moreBtn.style.visibility ='hidden'; //숨김
             }




        }else{
           let li =`<li class="list-grou-item">Comment List Empty</li>`;
           ul.innerHTML =li;
        }
    })
}
document.addEventListener('click',(e)=>{
    if(e.target.id=='moreBtn'){
        let page = parseInt(e.target.dataset.page);
        spreadCommentList(bnoVal, page);
    }
    //수정시 모달창을 통해 댓글 입력받기
  else  if(e.target.classList.contains('mod')){
    //내가 수정버튼을 누른 댓글의 li
    let li = e.target.closest('li');

    //nextSlbling : 한 부모 안에서 다음 형제를 찾기
    let cmtText =  li.querySelector('.fw-bold').nextSibling;
    console.log(cmtText);
    document.getElementById('cmtTextMod').value= cmtText.nodeValue;






    //수정 => cno dataset으로 달기 cno , content
    document.getElementById('cmtModBtn').setAttribute("data-cno", li.dataset.cno);
    }
    else if(e.target.id=='cmtModBtn'){
        let cmtModData = {
            cno : e.target.dataset.cno,
            content : document.getElementById('cmtTextMod').value
        };
        console.log(cmtModData);

        //비동기로 보내기
        TextCommentToServer(cmtModData).then(result=>{
            console.log(result);
            if(result==1){
                alert("댓글 수정 완료");
                //모달창 닫기
                document.querySelector(".btn-close").click();
            }else{

            alert("수정실패");
            document.querySelector(".btn-close").click();
            }
            //댓글 새로 뿌리기
            spreadCommentList(bnoVal);
        })

        

    

    }
    else if(e.target.id=='del'){
        let li = e.target.closest('li');
        let cno = li.dataset.cno;
        console.log(cno);

        //비동기로 보내기

        DelCommentToServer(cno).then(result=>{
            console.log(result);
            if(result==1){
                alert("댓글 삭제 완료");
           spreadCommentList(bnoVal);
            }
        })

    }








})
async function TextCommentToServer(cmtModData){
    try {
        const url ="/comment/update";
        const config ={
            method:"PUT",
            headers:{
                'Content-type' : 'application/json; charset=utf-8'
            },
            body :JSON.stringify(cmtModData)
        };
        const resp = await fetch(url, config);
            const result = await resp.text();
            return result;
    } catch (error) {
        console.log("Error")
    }
    
    }
async function DelCommentToServer(cno){
    try {
        const url ="/comment/"+cno;
        const config ={
            method:"delete",
            headers:{
                'Content-type' : 'application/json; charset=utf-8'
            },
            body :JSON.stringify(cno)
        };
        const resp = await fetch(url, config);
            const result = await resp.text();
            return result;
    } catch (error) {
        console.log("Error")
    }
    
    }


//수정 시 모달창을 통해 댓글 입력받기 




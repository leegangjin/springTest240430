/**
 * 
 */

console.log("modify js in");
document.addEventListener('click',(e)=>{
    console.log(e.target);
    if(e.target.classList.contains('file-x')){
        let uuid = e.target.dataset.uuid;
        //서버로 전송
        removeFileToServer(uuid).then(result=>{
            if(result=='1'){
alert("파일삭제성공");
         e.target.closest('li').remove();
            }
        })
    }
});

async function removeFileToServer(uuid){
    try {
        const url ="/board/file/"+uuid;
        const config={
            method:"delete"
        }
        const resp = await fetch(url, config);
        const result = resp.text();
        return result;
    } catch (error) {
        console.log("error");
    }
}






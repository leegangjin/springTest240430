/**
 * 
 */

console.log("check js in");

 document.getElementById('usercheck').addEventListener('click',(e)=>{

   const email =document.getElementById('e').value;
  
   console.log(email);
   
   usercheck(email).then(result=>{
    if(result=='1'){
        alert("사용불가한 아이디입니다.");
        email.value=null;
        return;
    }else{
        alert("사용가능 한 아이디입니다.");
        email.value="";
        return;
    } 

   })





 })

 async function usercheck(email){
    try {
        const url ="/user/check/"+email; //컨트롤러로
        const config={
            method:"get"
        }
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log("error");
    }
}
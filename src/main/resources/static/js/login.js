$(document).ready(function(){
    $("form").on("submit",function(event){
        let userId = $("#userId").val();
        let password = $("#password").val();
        if(userId === ""||password === ""){
            event.preventDeFault();
            alert("아뒤또는 비번")
            return
        }


    })
})
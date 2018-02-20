'use strict';

let ip = "http://192.168.15.111:";
let port = "8082";
let url = ip + port;

let motDevine;
let rep;
let sendRep;
let arretMainLoop = false;

$(document).ready(function(){
	
  mainLoop();
 
 // Envoyer le mot proposé du joueur2 au client (joueur1)
  $('.sendRep').click(sendValue);
  
});


//Afficher le canvas au joueur2

function mainLoop() {
   
       // send line to to the server
     //  console.log("ENVOYE CANVAS IMAGE ");
      // socket.emit('draw_line', { line: [ mouse.pos, mouse.pos_prev ] , color:contextToDraw.strokeStyle, width: contextToDraw.lineWidth });
       
       $.ajax({
           type        : 'GET', // define the type of HTTP verb we want to use (POST for our form)
        //   data		: 
           url          : '/partie/image', // the url where we want to POST
           // dataType    : 'text', // what type of data do we expect back from the server
           success:
               function(retour){
                   if(retour != ""){
                   	//console.log("retour       " + retour);
                   	$('.Myimage').attr('src', retour);
                   	arretMainLoop = true;
                   }        
                   
               },
          error: function(err) {
       	   console.log(err)
          }
       });
       
       setTimeout(mainLoop, 2000);
   /* if(arretMainLoop){
    	return;
    } else{
    	setTimeout(mainLoop, 2000);
    }
    */
      
 }


// Envoie le mot deviné par le joueur2 au dessinateur
function sendValue() {
	
	motDevine = document.querySelector('.rep').value;
	
	// Requete pour recuperer er envoyer le mot dessiné
	$.ajax({
		type : 'POST', // define the type of HTTP verb we want to use (POST for our form)
		url : '/partie/sendMotDevine', // the url where we want to POST	
		data : motDevine,
		contentType : "text/plain; charset=UTF-8"
	
	});
	
	// effacer l'image une fois le mot envoyé
	$('.Myimage').attr('src', "");
	

	
}

//afficher et cacher les divs
$("#selecte").on("change", function(elm) {
	let selecte = $("#selecte option:selected").val();

	if (selecte === 'joueur2') {
		window.location = "http://localhost:8082/joueur/formulairefenetreJoueur2";
		
	} else {
		window.location = "http://localhost:8082/joueur/fenetre";
	}

});
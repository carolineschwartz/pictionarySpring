'use strict';

let cs = 0;
let seconde = 0;
let canvas, context, w, h, colorDessin;
let color = "black";
let paint = true;
let clearI;
let imageUrl;
let rep;
let variable = "";
let rand;
let compteur = 0;
let supp;

let ip = "http://192.168.15.111:";
let port = "8082";
let url = ip + port;
let arretMainLoop = false;
let resultCanvasEnvoye = " ";

let partie = new FormData();// get the form data
partie = {
	'score' : $('.score').text(),
	'imageUrl' : imageUrl
};

$(document).ready(
		function() {
			canvas = document.querySelector('#canvas');
			$('.commence').click(myTimeoutFunction);

			$('.envoyer').attr('disabled', 'disabled');

			$('.envoyer').removeAttr('disabled');

			$('.envoyer').click(function() {
				clearInterval(clearI);

				// Recuperation de l'image du canvas
				imageUrl = canvas.toDataURL();

				// Envoyer l'image du joueur1 au joueur 2
				// Mise à jour de la partie (imageUrl et canvasEnvoye = true).
				// Persistance de l' image dans la BDD
				$.ajax({
					type : 'POST', // define the type of HTTP verb we want to
					// use (POST for our form)
					url : '/partie/sendCanvas', // the url where we want to POST
					data : imageUrl, // our data object
					contentType : "text/plain; charset=UTF-8"

				});
				// Envoyer le mot proposé du joueur2 au client (joueur1)

				// Affectation de l'image coté joueur2
				// $('.Myimage').attr('src', imageUrl);
			});

			// Effacer le canvas
			$('.supp').click(function() {
				console.log("deleteCanvas------------------ = ");
				context.clearRect(0, 0, w, h);
			});

			$('.btnMot').click(aleatoire);
			context = canvas.getContext("2d");
			w = canvas.clientWidth;
			h = canvas.clientHeight;
			colorDessin = document.querySelector("#colorDessin");
			colorDessin.addEventListener("input", changeColorDessin);

			let clickX = new Array();
			let clickY = new Array();
			let clickDrag = new Array();
			let clickColor = new Array();

			// Dessiner dans le canvas
			$('#canvas').mousedown(function(e) {
				let mouseX = e.pageX - this.offsetLeft;
				let mouseY = e.pageY - this.offsetTop;

				paint = true;
				addClick(e.pageX - this.offsetLeft, e.pageY - this.offsetTop);
				redraw();
			});

			$('#canvas').mousemove(
					function(e) {
						if (paint) {
							let mouseX = e.pageX - this.offsetLeft;
							let mouseY = e.pageY - this.offsetTop;
							addClick(e.pageX - this.offsetLeft, e.pageY
									- this.offsetTop, true);
							redraw();
						}
					});

			$('#canvas').mouseup(function(e) {
				paint = false;
				clickX = new Array();
				clickY = new Array();
				clickDrag = new Array();
			});

			// quand on sort du canvas
			$('#canvas').mouseleave(function(e) {
				paint = false;
			});

			function addClick(x, y, dragging) {
				clickX.push(x);
				clickY.push(y);
				clickDrag.push(dragging);
			}

			function redraw() {

				for (let i = 0; i < clickX.length; i++) {
					context.beginPath();
					context.lineJoin = "round";
					context.lineWidth = 6;
					if (clickDrag[i] && i) {
						context.moveTo(clickX[i - 1], clickY[i - 1]);
					} else {
						context.moveTo(clickX[i] - 1, clickY[i]);
					}
					context.lineTo(clickX[i], clickY[i]);
					context.closePath();
					context.strokeStyle = color;
					context.stroke();
				}

			}
			mainLoop();
		});

// choix de la couleur du pinceau
function changeColorDessin(event) {
	colorDessin.style.color = event.target.value;
	color = event.target.value; // <-> this.value;
	context.restore();
}

// afficher et cacher les divs
$("#selecte").on("change", function(elm) {
	let selecte = $("#selecte option:selected").val();

	if (selecte === 'joueur2') {
		window.location = "http://localhost:8082/joueur/formulairefenetreJoueur2";
		
	} else {
		window.location = "http://localhost:8082/joueur/fenetre";
	}
	

});

// incrementer le chrono.
function myTimeoutFunction() {
	// let partie = new FormData();// get the form data
	partie = {
		'score' : $('.score').text(),
		'imageUrl' : " "
	};

	// Creation d'une nouvelle partie. Persistance de l' image dans la BDD
	$.ajax({
		type : 'POST', // define the type of HTTP verb we want to use (POST for
		// our form)
		url : '/partie/createPartie', // the url where we want to POST
		data : partie, // our data object
		dataType : 'html', // what type of data do we expect back from the
		// server
		success : function(retour) {

			resultCanvasEnvoye = retour;
		}
	});

	clearI = setInterval(function() {
		if (seconde < 30) {
			seconde++;
		} else {
			return;
		}
		$('.chrono').text(seconde + ' : ' + cs + '0');
	}, 1000);
}

// choisir un mot aleatoire
let tabprenom = new Array('chien', 'maison', 'ballon', 'ordinateur', 'moto',
		'voiture');
function aleatoire() {
	rand = Math.floor(Math.random() * 6);
	variable = document.querySelector('.mot');
	console.log("variable.textContent = " + variable.textContent);
	variable.textContent = tabprenom[rand];
}



function mainLoop() {

	$.ajax({
		type : 'GET', // define the type of HTTP verb we want to use
		// (POST for our form)
		// data :
		url : '/partie/getWord', // the url where we want to POST
		// dataType : 'text', // what type of data do we expect back from the
		// server
		contentType : "text/plain; charset=UTF-8",
		success : function(retour) {
			if (retour != "") {

				$('.ReponseRecu').val(retour);
				
				if (retour == variable.textContent) {
						compteur++;
						$('.resultat').text('GAGNE');
						$('.resultat').css('color', 'green');
						$('.score').text(compteur);
					} else {
						$('.resultat').text('PERDU');
						$('.resultat').css('color', 'red');
					}
				arretMainLoop = true;
				
				}
	
		},
		error : function(err) {
			console.log(err)
		}
	});

	
	 if(arretMainLoop){
	    	return;
	    } else{
	    	setTimeout(mainLoop, 2000);
	    }
    
	
}

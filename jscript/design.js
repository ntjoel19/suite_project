$(document).ready(function(){
/*
* Utilisons $.ajax pour creer une instance de XmlHttpRequest
*/
	//$.ajax();
	$(window).resize(function(){
		var hauteur = ($(window).height());
		var largeur = ($(window).width());
		//alert(hauteur+" , "+largeur);
		$("body").css("width",largeur);
		$("body").css("height",hauteur);
		$("#banniere").css("height",hauteur/5);
		$("#banniere").css("width",largeur);
		$("#corps_index").css("height",hauteur-(hauteur/8)-(hauteur/5)+15-(hauteur/5));
		$("#corps").css("margin-top",30);
		$("#corps").css("height",hauteur-$("#pieds").height()-$("#entete").height()-60);
		$("#corps").css("margin-top",30);
		$("#corps").css("height",hauteur-$("#pieds").height()-$("#entete").height()-60);
		$(".contenu1").css("height",hauteur-$("#entete").height()-$("#pieds").height()-20);
		$("#step1, #step2, #step3, #step4").css("height",hauteur-$("#entete").height()-$("#pieds").height()-$("progress").height()-20);
		$("#suivi_projet").css("height",hauteur-$("#pieds").height()-$("#entete").height()-4);
		$("#suivi_projet div").css("height",$("#suivi_projet").height()-35);
		$("#suivi_projet div").css("width",$("#suivi_projet").width()/2-50);
		$("#tabEtape").css("height",$("#suivi_projet").height()-35);
		$("#tabEtape").css("width",$("#suivi_projet").width()/2-50);
		//$("#suivi_projet div iframe").css("height",$("#suivi_projet div").height()-35-10);
		//$("#suivi_projet div iframe").css("width",$("#suivi_projet div").width()/2-13);
		$(".listProj").css("width",$("#suivi_projet").width()/2-5);
		$("#rowOptions").css("width",$("#suivi_projet").width()/2-30);
		$("#rowOptions").css("height",$("#tbody tr").height());
		$("#pieds_index").css("height",hauteur/8);
		$("#pieds").css("height",hauteur/15);
		
		$("#contenu").css("height",hauteur-(hauteur/8)-(hauteur/5)+15-(hauteur/5));
		//$("#corpsprogr").css("height",$("#contenu").height()-45);
		$("#pieds").css("height",hauteur/10);
		$(".img").css("height",(hauteur+largeur)/60);
		$(".img").css("width",(hauteur+largeur)/60);
		$("#btnDelete").css("width",(hauteur+largeur)/60);
		$("#btnDelete").css("height",(hauteur+largeur)/60);
		$("#btnEdit").css("width",(hauteur+largeur)/60);
		$("#btnEdit").css("height",(hauteur+largeur)/60);
		$("#btnReload").css("width",(hauteur+largeur)/60);
		$("#btnReload").css("height",(hauteur+largeur)/60);
		$("#btnBloquer").css("height",(hauteur+largeur)/60);
		$("#btnBloquer").css("width",(hauteur+largeur)/60);
		$("#btnDebloquer").css("height",(hauteur+largeur)/60);
		$("#btnDebloquer").css("width",(hauteur+largeur)/60);
		$("#btnSave").css("height",(hauteur+largeur)/60);
		$("#btnSave").css("width",(hauteur+largeur)/60);
		$("#btnAddSave").css("height",(hauteur+largeur)/60);
		$("#btnAddSave").css("width",(hauteur+largeur)/60);
		$("#btnPrint").css("height",(hauteur+largeur)/60);
		$("#btnPrint").css("width",(hauteur+largeur)/60);
		
		$(".deconex").css("height",(hauteur+largeur)/70);
		$(".deconex").css("width",(hauteur+largeur)/70);
		
		if(hauteur < 522){
			$("#corps").css("overflow","scroll");
		}
		if(hauteur < 625 || largeur < 918){
			$(".contenu1").css("overflow","scroll");
		}
		if(largeur <= 640 && largeur > 498){
			$('#progr').replaceWith("<a id='progr' title='programmes' href='/suite_project/programme?btn=progr'>Progr</a>");
			$('#pub').replaceWith("<a id='pub' title='prublications' href='/suite_project/publication?btn=pub'>Pub</a>");
		}else if(largeur > 640){
			$('#site').replaceWith("<a id='site' title='Acceuil' href='/suite_project/admin'>Site</a>");
			$('#progr').replaceWith("<a id='progr' title='programmes' href='/suite_project/programme?btn=progr'>Programmes</a>");
			$('#pub').replaceWith("<a id='pub' title='prublications' href='/suite_project/publication?btn=pub'>Publications</a>");
			$('#action').replaceWith("<a id='action' title='Actions' href='/suite_project/action?btn=action'>Actions</a>");
			$('#projet').replaceWith("<a id='projet' title='Projet' href='/suite_project/projet?btn=suiviprojet'>Projets</a>");
		}else if(largeur <= 498){
			$('#site').replaceWith("<a id='site' title='Acceuil' href='/suite_project/admin'>S</a>");
			$('#progr').replaceWith("<a id='progr' title='programmes' href='/suite_project/programme?btn=progr'>P</a>");
			$('#pub').replaceWith("<a id='pub' title='prublications' href='/suite_project/publication?btn=pub'>P</a>");
			$('#action').replaceWith("<a id='action' title='Actions' href='/suite_project/action?btn=action'>A</a>");
			$('#projet').replaceWith("<a id='projet' title='Projet' href='/suite_project/projet?btn=suiviprojet'>P</a>");
		}
	});
	$(window).load(function(){
		var hauteur = ($(window).height());
		var largeur = ($(window).width());
		//alert(hauteur);
		$("body").css("width",largeur);
		$("body").css("height",hauteur);
		$("#banniere").css("height",hauteur/5);
		$("#banniere").css("width",largeur);
		$("#corps_index").css("height",hauteur-(hauteur/8)-(hauteur/5)+15-(hauteur/5));
		$("#corps").css("margin-top",30);
		$("#corps").css("height",hauteur-$("#pieds").height()-$("#entete").height()-60);
		$(".contenu1").css("height",hauteur-$("#entete").height()-$("#pieds").height()-20);
		$("#step1, #step2, #step3, #step4").css("height",hauteur-$("#entete").height()-$("#pieds").height()-$("progress").height()-20);
		$("#suivi_projet").css("height",hauteur-$("#pieds").height()-$("#entete").height()-4);
		$("#suivi_projet div").css("height",$("#suivi_projet").height()-35);
		$("#suivi_projet div").css("width",$("#suivi_projet").width()/2-50);
		$("#tabEtape").css("height",$("#suivi_projet").height()-35);
		$("#tabEtape").css("width",$("#suivi_projet").width()/2-50);
		//$("#suivi_projet div iframe").css("height",$("#suivi_projet div").height()-35-10);
		//$("#suivi_projet div iframe").css("width",$("#suivi_projet div").width()/2-13);
		$(".listProj").css("width",$("#suivi_projet").width()/2-5);
		$("#rowOptions").css("width",$("#suivi_projet").width()/2-30);
		$("#rowOptions").css("height",$("#tbody tr").height());
		$("#pieds_index").css("height",hauteur/8);
		$("#pieds").css("height",hauteur/15);
		
		$("#contenu").css("height",hauteur-(hauteur/8)-(hauteur/5)+15-(hauteur/5));
		//$("#corpsprogr").css("height",$("#contenu").height()-45);
		$("#pieds").css("height",hauteur / 10);
		$(".img").css("height",(hauteur+largeur) / 60);
		$(".img").css("width",(hauteur+largeur) / 60);
		$("#btnDelete").css("width",(hauteur+largeur) / 60);
		$("#btnDelete").css("height",(hauteur+largeur) / 60);
		$("#btnEdit").css("width",(hauteur+largeur)/60);
		$("#btnEdit").css("height",(hauteur+largeur)/60);
		$("#btnReload").css("width",(hauteur+largeur)/60);
		$("#btnReload").css("height",(hauteur+largeur)/60);
		$("#btnBloquer").css("height",(hauteur+largeur)/60);
		$("#btnBloquer").css("width",(hauteur+largeur)/60);
		$("#btnDebloquer").css("height",(hauteur+largeur)/60);
		$("#btnDebloquer").css("width",(hauteur+largeur)/60);
		$("#btnSave").css("height",(hauteur+largeur)/60);
		$("#btnSave").css("width",(hauteur+largeur)/60);
		$("#btnAddSave").css("height",(hauteur+largeur)/60);
		$("#btnAddSave").css("width",(hauteur+largeur)/60);
		$("#btnPrint").css("height",(hauteur+largeur)/60);
		$("#btnPrint").css("width",(hauteur+largeur)/60);
		
		$(".deconex").css("height",(hauteur+largeur) /70);
		$(".deconex").css("width",(hauteur+largeur) /70);
		
		if(hauteur < 522){
			$("#corps").css("overflow","scroll");
		}
		if(hauteur < 625 || largeur < 918){
			$(".contenu1").css("overflow","scroll");
		}
		if(largeur <= 640 && largeur > 498){
			$('#progr').replaceWith("<a id='progr' title='programmes' href='/suite_project/programme?btn=progr'>Progr</a>");
			$('#pub').replaceWith("<a id='pub' title='prublications' href='/suite_project/publication?btn=pub'>Pub</a>");
		}else if(largeur > 640){
			$('#site').replaceWith("<a id='site' title='Acceuil' href='/suite_project/admin'>Site</a>");
			$('#progr').replaceWith("<a id='progr' title='programmes' href='/suite_project/programme?btn=progr'>Programmes</a>");
			$('#pub').replaceWith("<a id='pub' title='prublications' href='/suite_project/publication?btn=pub'>Publications</a>");
			$('#action').replaceWith("<a id='action' title='Actions' href='/suite_project/action?btn=action'>Actions</a>");
			$('#projet').replaceWith("<a id='projet' title='Projet' href='/suite_project/projet?btn=suiviprojet'>Projets</a>");
		}else if(largeur <= 498){
			$('#site').replaceWith("<a id='site' title='Acceuil' href='/suite_project/admin'>S</a>");
			$('#progr').replaceWith("<a id='progr' title='programmes' href='/suite_project/programme?btn=progr'>P</a>");
			$('#pub').replaceWith("<a id='pub' title='prublications' href='/suite_project/publication?btn=pub'>P</a>");
			$('#action').replaceWith("<a id='action' title='Actions' href='/suite_project/action?btn=action'>A</a>");
			$('#projet').replaceWith("<a id='projet' title='Projet' href='/suite_project/projet?btn=suiviprojet'>P</a>");
		}
	});
});
if(window.matchMedia("(min-width: 400px)").matches) {
    // We convert our menu to a collapsible menu
}
function infoContext(btnSurvole,msgContextuel){
	$(btnSurvole).mouseenter(function(){
		$('#c1').hide();
		$(msgContextuel).show();
	});
	$(btnSurvole).mouseleave(function(){
		$('#c1').show();
		$(msgContextuel).hide();
	});
	$(btnSurvole).mousedown(function(){
		$(msgContextuel).hide();
	});
}

function addStep(btn,formulaire,btnValider,btnAnnuler,close){
	$(btn).mousedown(function(e){
		e.preventDefault();
		var coord = $(btn).offset();
		$(formulaire).css("display","block");
		$(formulaire).css("position","absolute");
		$(formulaire).css("top",coord.top+$(btn).height()+$(btn).height()/2);
		$(formulaire).css("left",coord.left-($(formulaire).width())+($(btn).width()));
		$(formulaire).fadeIn();
	});
	$(btnValider).mousedown(function(e){
		e.preventDefault();
		$(formulaire).fadeOut();
	});
	$(btnAnnuler).mousedown(function(e){
		e.preventDefault();
		$(formulaire).fadeOut();
	});
	$(close).mousedown(function(e){
		e.preventDefault();
		$(formulaire).fadeOut();
	});
};

$("#btnDelete").mousedown(function(){
	$('#ConfirmerSupr').dialog({
		modal : true ,
		width : 500 ,
		resizable : true 
	});
});

function caseCochee(boutonClique,nbCase){
	$(boutonClique).mouseclick(function(){
		$('#c1').fadeOut();
		$(msgContextuel).fadeIn();
		var doc;
		var attrib="";
		for(i=0;i<nbCase;i++){
			doc=document.getElementsByName(i);
			if(doc.value="on"){
				
			}
		}
	});
}

function ajaxChargement(donnee){
	$.ajax({
		url : '/suite_project/projet',
		type : 'post',
		dataType : 'html',
		//async: false,
		data : donnee,
		success : function(data, result, jqXHR){
			$(".step").html(data);
		},
		error: function(XMLHttpRequest, textStatus, errorThrown) { 
            alert("Status: " + textStatus); alert("Error: " + errorThrown); 
        } 
	});
}

function confirmPassword(form,password){
	$(form+"input[type='submit']").
	$(form).validate({
	    rules: {
	        password: {
	                        required: true,
	                        minlength: 5
	        },
	        password_confirm: {
	                        required: true,
	                        minlength: 5,
	                        equalTo: password
	        }
	    }

	}); 
}

function afficherPlus(btnClic,partieAafficher){
	$(btnClic).mousedown(function(){
		$(partieAafficher).slideToggle();
	});
}
$(document).ready(function(){
	$("#all").on('click',function(e){
		if(($(this).is(':checked'))===true){
			$("[type='checkbox']").each(function(){
				$(this).attr("checked","checked");
				$(this).removeAttr("disabled");
			});
		}else{
			$("[type='checkbox']").each(function(){
				$(this).removeAttr("checked");
				$(".checkbaxProg").each(function(){
					$(this).attr("disabled","disabled");
				});
				$(".checkbaxAction").each(function(){
					$(this).attr("disabled","disabled");
				});
				$(".checkbaxProj").each(function(){
					$(this).attr("disabled","disabled");
				});
				$(".checkbaxMedia").each(function(){
					$(this).attr("disabled","disabled");
				});
				$(".checkbaxParam").each(function(){
					$(this).attr("disabled","disabled");
				});
				$(".checkbaxUser").each(function(){
					$(this).attr("disabled","disabled");
				});
				$(".checkbaxPub").each(function(){
					$(this).attr("disabled","disabled");
				});
			});
		}
	});
});
function efface_formulaire () {
	$(':input')
	.not(':button, :submit, :reset, :hidden')
	.val('')
	.prop('checked', false)
	.prop('selected', false);
}

function contient(tab,val){
	var i=0;
	for(i=0;i<tab.length;i++){
		if(tab[i] == val) return 1;
	}
	return 0;
}

infoContext("#btn1","#c2");
infoContext("#btn2","#c3");
infoContext("#btn3","#c4");
infoContext("#btn4","#c5");
infoContext("#btn5","#c6");
infoContext("#btn6","#c7");
infoContext("#btn7","#c8");
infoContext("#btn8","#c9");
infoContext("#btn9","#c10");
infoContext("#btn10","#c11");
infoContext("#btn11","#c12");
infoContext("#btn12","#c13");
infoContext("#btn13","#c14");
infoContext("#btn14","#c15");
infoContext("#btn15","#c16");
infoContext("#btn17","#c18");
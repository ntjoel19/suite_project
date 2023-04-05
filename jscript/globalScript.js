


function viewport (ecouteur,masque,printer){
	$(ecouteur).click(function(){
		//static marque = 1;
		$(masque).css('display','none');
		/*if (marque == 1){
			$(masque).css('background','green');
			marque = 0;
		}else if (marque == 0){
			$(masque).css('background','red'); 
			marque=1; 
		}*/
		
		$(printer).fadeIn('slow');
		
	});
}

function smartAnimation(listener,setter,size,speed){
	$(listener).click(function(){
		$(setter).animate({
			height : size
		},speed);
	});
}

var deroule = 0 ;
function appear(ecouteur,printer){
	$(ecouteur).click(function(){
		if (deroule == 0){
			$(printer).fadeIn('slow');
			deroule = 1 ;
		}else if (deroule == 1){
			$(printer).hide();
			deroule = 0;
		}
		
	});
}



function slideItems(ecouteur,printer){
var derouler = 0 ;
	$(ecouteur).click(function(){
		if (derouler == 0){
			$(printer).fadeIn('slow');
			derouler = 1 ;
			$(ecouteur).html('<a href=#><span>hide details</span> </span><span>-</span></a>');
		}else if (derouler == 1){
			$(printer).fadeOut('slow'); 
			derouler = 0;
			$(ecouteur).html('<a href=#><span>show details</span> </span><span>+</span>');
			
		}
	});
	
}

function autoHide(){//Hint: fontion cablee
	var i=0;
	var j=0;
	for(i=0;i<4;i++){
		for(j=0;j<10;j++){
			$(".progr"+i+j).hide();
		}
	}
}

$(document).ready(function(){
	var booleen=0;
	$(".progr").live('click',function(e){
		e.preventDefault();
		var id=$(this).attr('id');
		//alert(id);
		var classe=id.split("#");
		if (booleen == 0){
			$("."+classe[0]).fadeIn('slow');
			booleen = 1 ;
			$(id+" a").replaceWith('<a href=#><span>masquer actions</span> </span><span>-</span></a>');
		}else if(booleen==1){
			$("."+classe[0]).fadeOut('slow');
			booleen = 0 ;
			$(id+" a").html('<a href=#><span>afficher actions</span> </span><span>-</span></a>');
		}
	});
});

$(document).ready(function(){
	var booleen=0;
	$(".progr0,.progr1,.progr2,.progr3").live('click',function(e){
		e.preventDefault();
		var id=$(this).attr('id');
		//alert(id);
		var classe=id.split("#");
		if (booleen == 0){
			$("."+classe[0]).fadeIn('slow');
			booleen = 1 ;
			$(id+" a").replaceWith('<a href=#><span>masquer actions</span> </span><span>-</span></a>');
		}else if(booleen==1){
			$("."+classe[0]).fadeOut('slow');
			booleen = 0 ;
			$(id+" a").html('<a href=#><span>afficher actions</span> </span><span>-</span></a>');
		}
	});
});

function desappear(ecouteur,hidder){
		$(ecouteur).click(function(){
			$(hidder).fadeOut('slow');
			deroule = 0;
		});
}

$(function(){
	viewport("#home","#resultatRecherchesProjet,#TDRProjet,#etatFicheProjet,#man,#barreRecherche,#barreRechercheProjet,#blocPromoteur,#resultatRecherches,#blocTelechargements,#blocPublication1,#blocPublication2,#blocPublication3,#blocPublication4,#blocPublication5,#wowslider-container,#blocAction,#blocProgram,#blocProjet,#blocForum,#optionProgram,#optionAction,#optionProjet,#optionForum","#supplements,#wowslider-container,#pieds");
	viewport("#program","#resultatRecherchesProjet,#TDRProjet,#etatFicheProjet,#man,#barreRecherche,#barreRechercheProjet,#blocPromoteur,#resultatRecherches,#blocTelechargements,#supplements,#blocPublication1,#blocPublication2,#blocPublication3,#blocPublication4,#blocPublication5,#wowslider-container,#blocAction,#blocProgram,#blocProjet,#blocForum,#optionProgram,#optionAction,#optionProjet,#optionForum,#manProgram,#manAction,#manProjet,#manForum","#blocProgram,#optionProgram,#pieds,#man,#manProgram");
	viewport("#action","#resultatRecherchesProjet,#TDRProjet,#etatFicheProjet,#man,#barreRecherche,#barreRechercheProjet,#blocPromoteur,#resultatRecherches,#blocTelechargements,#supplements,#blocPublication1,#blocPublication2,#blocPublication3,#blocPublication4,#blocPublication5,#wowslider-container,#blocAction,#blocProgram,#blocProjet,#blocForum,#optionProgram,#optionAction,#optionProjet,#optionForum,#manProgram,#manAction,#manProjet,#manForum","#blocAction,#optionAction,#pieds,#man,#manAction");
	viewport("#projet","#resultatRecherchesProjet,#TDRProjet,#etatFicheProjet,#man,#barreRecherche,#barreRechercheProjet,#blocPromoteur,#resultatRecherches,#blocTelechargements,#supplements,#blocPublication1,#blocPublication2,#blocPublication3,#blocPublication4,#blocPublication5,#wowslider-container,#blocAction,#blocProgram,#blocProjet,#blocForum,#optionProgram,#optionAction,#optionProjet,#optionForum,#manProgram,#manAction,#manProjet,#manForum","#blocProjet,#optionProjet,#pieds,#man,#manProjet");
	viewport("#forum","#resultatRecherchesProjet,#TDRProjet,#etatFicheProjet,#man,#barreRecherche,#barreRechercheProjet,#blocPromoteur,#resultatRecherches,#blocTelechargements,#supplements,#blocPublication1,#blocPublication2,#blocPublication3,#blocPublication4,#blocPublication5,#wowslider-container,#blocAction,#blocProgram,#blocProjet,#blocForum,#optionProgram,#optionAction,#optionProjet,#optionForum,#manProgram,#manAction,#manProjet,#manForum","#blocForum,#optionForum,#pieds,#man,#manForum");
	//smartAnimation('#program',"#prolonge",'20em',500);
	//smartAnimation('#action',"#prolonge",'20em',500);
	
	viewport("#supplements img:eq(0),#supplements span:eq(0)","#TDRProjet,#man,#blocPromoteur,#resultatRecherches,#resultatRecherchesProjet,#blocTelechargements,#blocPublication1,#blocPublication2,#blocPublication3,#blocPublication4,#blocPublication5,#wowslider-container,#blocAction,#blocProgram,#blocProjet,#blocForum,#optionProgram,#optionAction,#optionProjet,#optionForum","#blocPublication1");
	viewport("#supplements img:eq(1),#supplements span:eq(1)","#TDRProjet,#man,#blocPromoteur,#resultatRecherches,#resultatRecherchesProjet,#blocTelechargements,#blocPublication1,#blocPublication2,#blocPublication3,#blocPublication4,#blocPublication5,#wowslider-container,#blocAction,#blocProgram,#blocProjet,#blocForum,#optionProgram,#optionAction,#optionProjet,#optionForum","#blocPublication2");
	viewport("#supplements img:eq(2),#supplements span:eq(2)","#TDRProjet,#man,#blocPromoteur,#resultatRecherches,#resultatRecherchesProjet,#blocTelechargements,#blocPublication1,#blocPublication2,#blocPublication3,#blocPublication4,#blocPublication5,#wowslider-container,#blocAction,#blocProgram,#blocProjet,#blocForum,#optionProgram,#optionAction,#optionProjet,#optionForum","#blocPublication3");	
	viewport("#supplements img:eq(3),#supplements span:eq(3)","#TDRProjet,#man,#blocPromoteur,#resultatRecherches,#resultatRecherchesProjet,#blocTelechargements,#blocPublication1,#blocPublication2,#blocPublication3,#blocPublication4,#blocPublication5,#wowslider-container,#blocAction,#blocProgram,#blocProjet,#blocForum,#optionProgram,#optionAction,#optionProjet,#optionForum","#blocPublication4");
	viewport("#supplements img:eq(4),#supplements span:eq(4)","#TDRProjet,#man,#blocPromoteur,#resultatRecherches,#resultatRecherchesProjet,#blocTelechargements,#blocPublication1,#blocPublication2,#blocPublication3,#blocPublication4,#blocPublication5,#wowslider-container,#blocAction,#blocProgram,#blocProjet,#blocForum,#optionProgram,#optionAction,#optionProjet,#optionForum","#blocPublication5");
	
	
	appear   ('#optionAction  td:eq(0)','#barreRecherche');
	desappear('#optionAction  td:eq(1)','#barreRecherche');
	desappear('#optionAction  td:eq(2)','#barreRecherche');
	
	appear   ('#optionProjet  td:eq(0)','#barreRechercheProjet');
	desappear('#optionProjet  td:eq(1)','#barreRechercheProjet');
	desappear('#optionProjet  td:eq(2)','#barreRechercheProjet');
	desappear('#optionProjet  td:eq(3)','#barreRechercheProjet');
	desappear('#optionProjet  td:eq(4)','#barreRechercheProjet');
	
	
	
	$('td.buton,#projListener,#mediaListener,#progListener,#actListener').click(function(e){e.preventDefault();});
	viewport(".telecharger","#resultatRecherches,#resultatRecherchesProjet,#TDRProjet,#etatAction,#etatFicheProjet,#man,#blocPromoteur,#pieds,#supplements,#blocPublication1,#resultatRecherchesProjet,#blocPublication2,#blocPublication3,#blocPublication4,#blocPublication5,#wowslider-container,#blocAction,#blocProgram,#blocProjet,#blocForum,#optionProgram,#optionAction,#optionProjet,#optionForum","#blocTelechargements,#pieds");
	viewport("td.buton","#resultatRecherchesProjet,#TDRProjet,#blocPromoteur,#man,#barreRecherche,#barreRechercheProjet,#supplements,#blocPublication1,#blocPublication2,#resultatRecherchesProjet,#blocPublication3,#blocPublication4,#blocPublication5,#wowslider-container,#blocAction,#blocProgram,#blocProjet,#blocForum,#optionProgram,#optionAction,#optionProjet,#optionForum","#resultatRecherches,#pieds");
	viewport("#rech","#resultatRecherchesProjet,#TDRProjet,#blocPromoteur,#man,#barreRecherche,#barreRechercheProjet,#barreRechercheProjet,#supplements,#blocPublication1,#resultatRecherchesProjet,#blocPublication2,#blocPublication3,#blocPublication4,#blocPublication5,#wowslider-container,#blocAction,#blocProgram,#blocProjet,#blocForum,#optionProgram,#optionAction,#optionProjet,#optionForum","#resultatRecherchesProjet,#pieds");
	
	slideItems('#projListener','#fp-dl');
	slideItems('#mediaListener','#m-dl');
	slideItems('#progListener','#pg-dl');
	slideItems('#actListener','#a-dl');
	viewport("#fp-dl td span:eq(0),#fp-dl td span:eq(1)","#TDRProjet,#etatFicheProjet,#barreRechercheProjet,#blocTelechargements,#man,#blocPromoteur,#supplements,#blocPublication1,#blocPublication2,#blocPublication3,#blocPublication4,#blocPublication5,#wowslider-container,#blocAction,#blocProgram,#blocProjet,#blocForum,#optionProgram,#optionAction,#optionForum","#etatFicheProjet,#pieds");
	viewport("#fp-dl td span:eq(2)","#TDRProjet,#etatFicheProjet,#blocTelechargements,#man,#barreRechercheProjet,#blocPromoteur,#supplements,#blocPublication1,#blocPublication2,#blocPublication3,#blocPublication4,#blocPublication5,#wowslider-container,#blocAction,#blocProgram,#blocProjet,#blocForum,#optionProgram,#optionAction,#optionForum","#TDRProjet,#pieds");
	viewport("#a-dl td span","#etatAction,#etatFicheProjet,#blocTelechargements,#man,#blocPromoteur,#supplements,#barreRechercheProjet,#blocPublication1,#blocPublication2,#blocPublication3,#blocPublication4,#blocPublication5,#wowslider-container,#blocAction,#blocProgram,#blocProjet,#blocForum,#optionProgram,#optionAction,#optionForum","#etatAction,#pieds");
	
	autoHide();
});


	

					
				

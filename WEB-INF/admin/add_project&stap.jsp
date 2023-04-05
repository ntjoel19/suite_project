<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css"  href="design/Design_Global.css" />
	<link rel="stylesheet" type="text/css"  href="design/css_programmes.css" />
	<link rel="stylesheet" type="text/css"  href="design/jquery-ui.css" />
	
	<style>
		#next1,#next2,#prev1,#prev2,
		#next3,#next4,#prev3,#prev4,#okUnclicable{
			cursor : pointer;
		}
		#projet{
			border: 1px solid #f6f5f5;
			background: #ffff;
		}
		
		#okUnclicable{
			background : none repeat scroll 0 0 #e5e5e5;
		}
		#next1:hover,#next2:hover,#prev1:hover,#prev2:hover,
		#next3:hover,#next4:hover,#prev3:hover,#prev4:hover{
			-webkit-box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset, 0 0 1px #fff inset;
			-moz-box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset;
			-ms-box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset, 0 0 1px #fff inset;
			-o-box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset, 0 0 1px #fff inset;
			box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset;
			border-radius : 4px;
			border: 1px solid #c0c0c0;
			background: #dbdbdb url(../ressources/main.png) repeat-x scroll 0 0;  
			background: -webkit-gradient(linear,left bottom,left top,color-stop(0, #d4d4d4),color-stop(1, #e6e6e6));
			background: -webkit-linear-gradient(center bottom , #d4d4d4 0%, #e6e6e6 100%) repeat scroll 0 0 #dbdbdb;  
			background: -moz-linear-gradient(center bottom , #d4d4d4 0%, #e6e6e6 100%) repeat scroll 0 0 #dbdbdb;  
		}
		.input{	
			text-align : center;
			padding-top : 1%;
			padding-bottom : 1%;
		}
		.btn{
			border-radius: 5px;
			border : 1px solid #dddddd;	
			width : 40%;
			height : 90%;
			background : #567;
			color : #fff;
			font-family: "Palatino Linotype", "Book Antiqua", Palatino, serif;	
			transition:background 1000ms,color 1000ms,border-color 500ms;	
			box-shadow: 0 0 12px grey;
		}
		#formEtape{
			border : 1px solid #999999;
			z-index : 5;
			padding : 5px;
			background: url(././ressources/footerimage.jpg) repeat;
			border-radius : 5px;
			text-align : center;
			-webkit-box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset, 0 0 1px #fff inset;
			-moz-box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset;
			-ms-box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset, 0 0 1px #fff inset;
			-o-box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset, 0 0 1px #fff inset;
			box-shadow: 0 0 12px grey;
			
			background: #dbdbdb url(../ressources/main.png) repeat-x scroll 0 0;  
			background: -webkit-gradient(linear,left bottom,left top,color-stop(0, #d4d4d4),color-stop(1, #e6e6e6));
			background: -webkit-linear-gradient(center bottom , #d4d4d4 0%, #e6e6e6 100%) repeat scroll 0 0 #dbdbdb;  
			background: -moz-linear-gradient(center bottom , #d4d4d4 0%, #e6e6e6 100%) repeat scroll 0 0 #dbdbdb;  
			border: 1px solid #c0c0c0;
			position : absolute;
			left : -1000em;
		}
		.in{
			margin-top:2px;
			margin-bottom:2px;
			border-radius : 5px;
			width : 95%;
		}
	</style>
<title>Creation de projet</title>
</head>
<body>
	<% String colspan=(String)request.getAttribute("colspan"); %>
	
	<% String intitule=(String)request.getAttribute("intitule");%>
	<% String contexte=(String)request.getAttribute("contexte");%>
	<% String description=(String)request.getAttribute("description");%>
	<% String objectif1=(String)request.getAttribute("objectif1");%>
	<% String objectif2=(String)request.getAttribute("objectif2");%>
	<% String promot1=(String)request.getAttribute("promot1");%>
	<% String promot2=(String)request.getAttribute("promot2");%>
	<% String promot3=(String)request.getAttribute("promot3");%>
	<% String dataList1=(String)request.getAttribute("dataList1");
		String appartenance=(String)request.getAttribute("appartenance");
	   String dataListAction=(String)request.getAttribute("dataListAction");
	   if(dataListAction==null) dataListAction="";
	%>
	<% String ancrage1=(String)request.getAttribute("ancrage1");%>
	<% String ancrage2=(String)request.getAttribute("ancrage2");%>
	<% String effet=(String)request.getAttribute("effet");%>
	<% String impact=(String)request.getAttribute("impact");%>
	<% String justification1=(String)request.getAttribute("justification1");%>
	<% String justification2=(String)request.getAttribute("justification2");%>
	<% String justification3=(String)request.getAttribute("justification3");%>
	<% String cout=(String)request.getAttribute("cout");%>
	<% String population=(String)request.getAttribute("population");%>
	<% String service=(String)request.getAttribute("service");%>
	<% String typologie=(String)request.getAttribute("typologie");%>
	<% String extrants=(String)request.getAttribute("extrants");%>
	<% String nature_finance=(String)request.getAttribute("nature_finance");%>
	<% String maitre_Ovr=(String)request.getAttribute("maitre_Ovr");%>
	<% String maitre_Ovg=(String)request.getAttribute("maitre_Ovg");%>
	<% String partenaire=(String)request.getAttribute("partenaire");%>
	<% String designation=(String)request.getAttribute("designation");%>
	<% String name=(String)request.getAttribute("name");
		//System.out.println("le name est : "+name);
	%>
	<% String porteur=(String)request.getAttribute("porteur");%>
	<section id="entete">
			<jsp:include page="entete_des_pages.jsp"/> 
	</section> 
	
	<section class="contenu1">
		 <form id="formAddProject" method="post" action="/suite_project/projet">
		 	<div style="font-size : 2em;line-height : 200%;text-align : center;" id="step0">
		 		<h2 style="background : none repeat scroll 0 0 #e5e5e5;"><a id='next0' style="display:inline-block;" href=""><img src="././ressources/prevLock.png"></a>&nbsp;&nbsp;Formulaire de création d'un projet &nbsp;&nbsp;<a style="display:inline-block;" id="next1" href=""><img src="././ressources/next.png"></a></h2>
		 		<p>
		 			Plusieurs informations vous seront demandées par la suite.</br>
		 			Le renseignement de ces informations est obligatoire et se fera en plusieurs étapes.</br>
		 			Mais vous n'êtes pas tenus de toutes les renseigner d'un trait!!</br>
		 			Appuyez sur suivant pour commencer.</br>
		 			<input type="hidden" name="designation" value='<% out.print(designation); %>'>
		 			<label style="font-size:14px;"><i>Cocher ne plus voir ce message</i> &nbsp;</label><input class='in' type='checkbox' name='neplusafficher' />.
		 		</p>
		 	</div>
		 	<div height="100%" id="step1" style="display:none;" align="center">
		 		<table border="0" width="95%" style="margin-top: 2%;font-size : 2em;">
		 			<tr>
		 				<th style="border-radius:7px;background : none repeat scroll 0 0 #e5e5e5;"colspan="5"><a style="" id="prev1" href=""><img src="././ressources/prevew.png"></a>&nbsp;&nbsp;Etape 1/4 [Informations generales(1)]&nbsp;&nbsp;<a style="" id="next2" href=""><img src="././ressources/next.png"></a></th>
		 			</tr>
		 			<tr>
		 				<td></td>
		 				<td class="input">
		 					<select class="in" id="programme" name='action' style="border-radius : 3px 3px 3px 3px; border:1px solid #999999;margin-bottom : 5px; width : 97%;">	  
								    <%out.print(dataList1);%>  
							</select></br>
		 				</td>
		 				<td class="input" colspan="2">
		 					<select class="in" id="actions" name='programme' style="border-radius : 3px 3px 3px 3px; border:1px solid #999999;margin-bottom : 5px; width : 97%;">	  
								    <%out.print(dataListAction);%>  
							</select></br>
		 				</td>
		 				<td></td>
		 			</tr>
		 			<tr>
		 				<td></td>
		 				<td class="input" colspan="3">
		 					<input class='in' id="intitule" name='intitule' placeholder="Intitule du projet" value='<%out.print(intitule);%>' type="text"/>
		 				</td>
		 				<td></td>
		 			</tr>
		 			<tr>
		 				<td rowspan="2" id="prev1" width="2em"><<</td>
		 				<td class="input">
		 					<!--  &bull;&nbsp;ligne 1-->
		 					<textarea class='in' id="contexte" name='contexte' placeholder="Contexte du projet" ><%out.print(contexte);%></textarea>
		 				</td>
		 				<td class="input" colspan="2">
		 					<textarea class='in' id="description" name='description' placeholder="Description du projet" cols=""><%out.print(description);%></textarea>
		 				</td>
		 				<td rowspan="2" id="next2" width="2em">>></td>
		 			</tr>
		 			<tr>
		 				<td class="input">
		 					<textarea class='in' id='obj1' name='objectif1' placeholder="Objectif global" cols=""><%out.print(objectif1);%></textarea>
		 				</td>
		 				<td colspan="2" class="input">
		 					<textarea class='in' id='obj2' name='objectif2' placeholder="Objectif specifique" cols=""><%out.print(objectif2);%></textarea>
		 				</td>
		 			</tr>
		 			
		 		</table>
		 	</div>
		 	<div height="100%" id="step2" style="display:none;" align="center">
		 		<table border="0" width="95%" style="margin-top: 2%;font-size : 2em;">
		 			<tr>
		 				<th style="border-radius:7px;background : none repeat scroll 0 0 #e5e5e5;"colspan="5"><a style="" id="prev2" href=""><img src="././ressources/prevew.png"></a>&nbsp;&nbsp;Etape 2/4 [Informations generales(2)]&nbsp;&nbsp;<a style="" id="next3" href=""><img src="././ressources/next.png"></a></th>
		 			</tr>
		 			<tr>
		 				<td>
		 					
		 				</td>
		 				<td class="input">
		 					<textarea class='in' id='promot1' name='promoteur1' placeholder="Ministere" cols=""><%out.print(promot1);%></textarea>
		 				</td>
		 				<td class="input">
		 					<textarea class='in' id='promot2' name='promoteur2' placeholder="Secteur" cols=""><%out.print(promot2);%></textarea>
		 				</td>
		 				<td class="input">
		 					<textarea class='in' id='promot3' name='promoteur3' placeholder="Sous-secteur" cols=""><%out.print(promot3);%></textarea>
		 				</td>
		 			</tr>
		 			<tr>
		 				<td rowspan="2" id="prev2" width="2em"><<</td>
		 				<td class="input">
		 					<textarea class='in' id='ancr1' name='ancrage1' placeholder="Ancrage:Objectif Strategique global" cols=""><%out.print(ancrage1);%></textarea>
		 				</td>
		 				<td class="input" colspan="2">
		 					<textarea class='in' id='ancr2' name='ancrage2' placeholder="Ancrage:Objectif strategique specifique" cols=""><%out.print(ancrage2);%></textarea>
		 				</td>
		 				<td rowspan="2" id="next3" width="2em">>></td>
		 			</tr>
		 			<tr>
		 				<td class="input">
		 					<textarea class='in' id='effet' name='effets' placeholder="Effets attendus" cols=""><%out.print(effet);%></textarea>
		 				</td>
		 				<td colspan="2" class="input">
		 					<textarea class='in' id='impacte' name='impacte' placeholder="Impactes" cols=""><%out.print(impact);%></textarea>
		 				</td>
		 			</tr>
		 			<tr>
		 				<td>
		 					
		 				</td>
		 				<td class="input">
		 					<textarea class='in' id='just1' name='justification1' placeholder="Besoins indentifies a satisfaire" cols=""><%out.print(justification1);%></textarea>
		 				</td>
		 				<td class="input">
		 					<textarea class='in' id='just2' name='justification2' placeholder="Situation actuelle" cols=""><%out.print(justification2);%></textarea>
		 				</td>
		 				<td class="input">
		 					<textarea class='in' id='just3' name='justification3' placeholder="Situation desiree" cols=""><%out.print(justification3);%></textarea>
		 				</td>
		 			</tr>
		 		</table>
		 	</div>
		 	<div id="step3" style="display:none;">
		 		<table align="center" width="95%" style="margin-top: 2%;font-size : 2em;">
		 			<tr>
		 				<th style="border-radius:7px;background : none repeat scroll 0 0 #e5e5e5;" colspan="5"><a style="" id="prev3" href=""><img src="././ressources/prevew.png"></a>&nbsp;&nbsp;Etape 3/4 [Informations generales(3)]&nbsp;&nbsp;<a style="" id="next4" href=""><img src="././ressources/next.png"></a></th>
		 			</tr>
		 			<tr>
		 				<td></td>
		 				<td class="input">
		 					<input class='in' id='cout' name="cout" value='<%out.print(cout);%>' placeholder="Cout du projet" type="number" />
		 				</td>
		 				<td class="input" colspan="2">
		 					<input class='in' id='pop' name='population' value='<%out.print(population);%>' placeholder="Population cible" type="text"/>
		 				</td>
		 				<td></td>
		 			</tr>
		 			<tr>
		 				<td rowspan="2" id="prev3" width="2em"><<</td>
		 				<td class="input">
		 					<textarea class='in' id='service' name='service' placeholder="Service responsable" cols=""><%out.print(service);%></textarea>
		 				</td>
		 				<td class="input" colspan="2">
		 					<table width="96%" height="90%" style="margin-left:2%; border:1px solid #999999; border-radius : 7px;">
		 						<tr align="center" style="font-size:70%;">
		 							<td>Projet Structurant</td>
		 							<td>Projet de reference</td>
		 							<td>Projet institutionnel</td>
		 							<td>Autre</td>
		 						</tr>
		 						<tr>
		 							<td><input class='typo'  type="radio" name="typologie" value="pStruct"/></td>
		 							<td><input class='typo' type="radio" name="typologie" value="pRef"/></td>
		 							<td><input class='typo' type="radio" name="typologie" value="pInstitut"/></td>
		 							<td><input class='typo' type="radio" name="typologie" value="autre"/></td>
		 						</tr>
		 					</table>		
		 				</td>
		 				<td rowspan="2" id="next4" width="2em">>></td>
		 			</tr>
		 			<tr>
		 				<td class="input">
		 					<textarea class='in' id='extrant' name='extrant' placeholder="extrant" cols=""><%out.print(extrants);%></textarea>
		 				</td>
		 				<td colspan="2" class="input">
		 					<textarea class='in' id='finance' name='nature_financement' placeholder="Nature du financement" cols=""><%out.print(nature_finance);%></textarea>
		 				</td>
		 			</tr>
		 			<tr>
		 				<td>
		 					
		 				</td>
		 				<td class="input">
		 					<textarea class='in' id='maitreoe' name='maitredoeuvre' placeholder="Maitre d'oeuvre" cols=""><%out.print(maitre_Ovr);%></textarea>
		 				</td>
		 				<td class="input">
		 					<textarea class='in' id='maitreou' name='maitredouvrage' placeholder="Maitre d'ouvrage" cols=""><%out.print(maitre_Ovg);%></textarea>
		 				</td>
		 				<td class="input">
		 					<textarea class='in' id='partenaire' name='partenaire' placeholder="Partenaire" cols=""<%out.print(partenaire);%>></textarea>
		 				</td>
		 			</tr>
		 		</table>
		 	</div>
		 	<div id="step4" style="text-align : center; display:none;">
		 		<table align="center" width="95%" style="margin-top: 2%;font-size : 2em;line-height:2.2em;" id="step0">
		 			<tr>
		 				<th style="border-radius:7px;background : none repeat scroll 0 0 #e5e5e5;"><a style="" id="prev4" href=""><img src="././ressources/prevew.png"></a>&nbsp;&nbsp;Etape 4/4 [Enregistrer]&nbsp;&nbsp;<a style="" id="next0" href=""><img src="././ressources/nextLock.png"></a></th>
		 			</tr>
		 			<tr>
		 				<td align="center" class="input" colspan="2">
		 					<input style='width:50%;' class='in' id='porteur' name='porteur' value='<%out.print(porteur);%>' placeholder="Noms et prenoms du porteur du projet" type="text"/>
		 				</td>
		 			</tr>
		 			<tr>
		 				<td align="center" class="input" colspan="2">
		 					<input style='width:50%;' class='in' id='appartenance' name='appartenance' value='<%out.print(appartenance);%>' placeholder="Appartenance du projet" type="text"/>
		 				</td>
		 			</tr>
		 			<tr style='line-height:2.2em;'>
		 				<td align="center">
		 					<a id="prev4" href="" align="center"><<</a>
		 					<input type="submit" class="btn" value="<% out.print(name); %>" name='enregistrerProjet' /><!--span align="center" id="okUnclicable" style="display:block;">ok</span-->
		 				</td>
		 			</tr>
		 		</table>
		 	</div>
		 	<!-- progress max="100" value="0" form="formAddProject" style="float:bottom; border:1px solid #dddddd;">25%</progress-->
		 </form>
	</section>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$("#formEtape").submit(function(e){
				e.preventDefault();
				var donnees=$(this).serialize();
				$("#enregistrerEtape").mousedown(function(){
					alert("les champs du formulaire : "+donnees);
					$("#formEtape").fadeOut();
					alert("donnee : "+donnees);
					if($("#intitule").val()=="" ||
							$("#contexte").val()=="" ||
							$("#description").val()=="" ||
							$("#programme").val()==""){
						var message="Impossible de creer une etape sans au prealable \n";
						message += "avoir initialise les champs obligatoires";
						alert(message);
					}else{
						donnees+="&description="+$("#description").val();
						donnees+="&programme="+$("#programme").val();
						$.ajax({
							url : '/suite_project/projet',
							type : 'post',
							dataType : 'html',
							data : donnees,
							success : function(data, result, jqXHR){
								//$(codeHtml).appendTo('#tbody');
								alert("le retour de la servlet: "+data);
								$("#empty1").html(data);
							},
							error: function(XMLHttpRequest, textStatus, errorThrown) { 
					            alert("Status: " + textStatus); alert("Error: " + errorThrown); 
					        } 
						});	
					}
				});
			});
		});
			$("#programme").live('change',function(e){
				e.preventDefault();
				var donnees="";
	
				donnees="programme="+$("#programme option:selected").text();
				//alert(donnees);
				donnees+="&btn=listActions";
				
				$.ajax({
					url : '/suite_project/projet',
					type : 'GET',
					data : donnees,
					dataType : 'html',
					success : function(data, result, jqXHR){
						$("#actions").html(data);	
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) { 
			            alert("Status: " + textStatus); alert("Error: " + errorThrown); 
			        } 
				});
			});
		
		function progression(element){
			$(element).on('blur', function(){
				if($(element).val()!=null){
					  $('progress').val( $('progress').val() + 4);
				}
			});
		}
		progression("#intitule");
		progression("#contexte");
		progression("#description");
		progression("#obj1");
		progression("#obj2");
		progression("#promot1");
		progression("#promot2");
		$(function(){
			$( ".date" ).Datepicker();
		});
		function next(btnClick,prev,suiv){
			$(btnClick).mousedown(function(){
				//if(orientation==1){
					$(prev).slideDown();
					$(prev).css("display","none");
					$(suiv).slideDown();
					$(suiv).css("display","block");
				if(suiv == "#step4" || btnClick == "next4"){
					var coord = $('#btnAddStep').offset();
					$("#formEtape").css("position","absolute");
					$("#formEtape").css("top",coord.top+$('#btnAddStep').height());
					$("#formEtape").css("left",coord.left-($("#formEtape").width()/3));
					$('#formEtape').hide();
					$(window).resize(function(){
						var coord = $('#btnAddStep').offset();
						$("#formEtape").css("position","absolute");
						$("#formEtape").css("top",coord.top + $('#btnAddStep').height());
						$("#formEtape").css("left",coord.left-($("#formEtape").width()/3));
					});
					$(window).resize(function(){
						var coord = $('#btnAddStep').offset();
						$("#formEtape").css("position","absolute");
						$("#formEtape").css("top",coord.top+$('#btnAddStep').height());
						$("#formEtape").css("left",coord.left-($("#formEtape").width()/3));
					});
				}
			});
		}
		
		next("#next1","#step0","#step1");
		next("#next2","#step1","#step2");
		next("#next3","#step2","#step3");
		next("#next4","#step3","#step4");
		
		next("#prev1","#step1","#step0");
		next("#prev2","#step2","#step1");
		next("#prev3","#step3","#step2");
		next("#prev4","#step4","#step3");
		
		function addStep(){
			var etapes="";
			$('#btnAddStep').mousedown(function(){
				var coord = $('#btnAddStep').offset();
				$("#formEtape").css("display","block");
				$("#formEtape").css("position","absolute");
				$("#formEtape").css("top",coord.top+$('#btnAddStep').height());
				$("#formEtape").css("left",coord.left-($("#formEtape").width()/3));
				$("#formEtape").fadeIn();
				//$("#formEtape").css("display","block");
			});
			
			$('#quitter').mousedown(function(){
				$("#formEtape").fadeOut();
			});
		}
		addStep();
		
		$(function(){
			$('#formAddProject').validate({
				rules: {
					"cout" : {
						"required" : true, 
						"minlength": 5,
						"maxlength": 20,
						"regex" : /^[0-9]*$/
					},
					"intitule" : {
						"required" : true, 
						"minlength": 5,
						"maxlength": 20,
						"regex" : /^[0-9]*$/
					}	
				}
				
			});
			
			$(function(){
				$('#formEtape').validate({
					rules: {
						"dateDebut" : {
							"required" : true ,
							"regex" : /^(0?\d|[12]\d|3[01])-(0?\d|1[012])-((?:19|20)\d{2})$/
						},
						"dateFin" : {
							"required" : true ,
							"regex" : /^(0?\d|[12]\d|3[01])-(0?\d|1[012])-((?:19|20)\d{2})$/
						}
					}
					
				});
			});
			
			jQuery.extend(jQuery.validator.messages,{
				required  : "Ce champ est requis !" ,
				minlength : "Entrer au moins 5 characters svp" ,
				maxlength : "Dépassement de capacité"
			});
			
			jQuery.validator.addMethod(
			  "regex",
			   function(value, element, regexp) {
				   if (regexp.constructor != RegExp)
					  regexp = new RegExp(regexp);
				   else if (regexp.global)
					  regexp.lastIndex = 0;
					  return this.optional(element) || regexp.test(value);
			   },"Format incorrect !!!"
			);
		});
		
	</script>
	<script src="jquery/jquery.js"></script>
	<script src="jquery/jquery-ui.js"></script>
	<script src="jquery/jquery-validate.js"></script>
	<script src="jscript/design.js"></script>
	<script src="jscript/juizDropDownMenu-2.0.0.min.js"></script>
	<div id="pieds">
		<jsp:include page="pieds_des_pages.jsp"/>
	</div>
</body>
</html>
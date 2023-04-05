<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css"  href="design/Design_Global.css" />
	<link rel="stylesheet" type="text/css"  href="design/css_programmes.css" />
	<link rel="stylesheet" type="text/css"  href="design/jquery-ui.css" />
	<title>Tache d'un projet</title>
	<style>
		#red{
			background : red;
		}
		#blue{
			background : blue;
		}
		#yellow{
			background : yellow;
		}
		.btn{
			border-radius: 100px;
			background: -webkit-gradient(linear,left bottom,left top,color-stop(0, #d4d4d4),color-stop(1, #e6e6e6));
			background: -webkit-linear-gradient(center bottom , #d4d4d4 0%, #e6e6e6 100%) repeat scroll 0 0 #dbdbdb;  
			background: -moz-linear-gradient(center bottom , #d4d4d4 0%, #e6e6e6 100%) repeat scroll 0 0 #dbdbdb;  
			border: 1px solid #c0c0c0;
			height : 100%;
			color : #000;
			font-family: "Palatino Linotype", "Book Antiqua", Palatino, serif;	
			transition:background 1000ms,color 1000ms,border-color 500ms;
			float : right;
		}
		.btn:hover{
			box-shadow: 0 0 6px grey;
		}
		#tabEtape{
			padding : 5px;
			border-radius : 5px;
			text-align : right;
			
			border: 1px solid #c0c0c0;
			height : 91%;
			font-size : 1.5em;
		}
		#details{
			border-radius : 5px;
			border: 1px solid #c0c0c0;
		}
		#projet{
			border: 1px solid #f6f5f5;
			background: #ffff;
		}
		#project{
			background: #dbdbdb url(../ressources/main.png) repeat-x scroll 0 0;  
			background: -webkit-gradient(linear,left bottom,left top,color-stop(0, #d4d4d4),color-stop(1, #e6e6e6));
			background: -webkit-linear-gradient(center bottom , #d4d4d4 0%, #e6e6e6 100%) repeat scroll 0 0 #dbdbdb;  
			background: -moz-linear-gradient(center bottom , #d4d4d4 0%, #e6e6e6 100%) repeat scroll 0 0 #dbdbdb;  
			border: 1px solid #c0c0c0;
			text-transform: uppercase;
			font-family: "Palatino Linotype", "Book Antiqua", Palatino, serif;	
			
			-webkit-box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset, 0 0 1px #fff inset;
			-moz-box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset;
			-ms-box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset, 0 0 1px #fff inset;
			-o-box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset, 0 0 1px #fff inset;
			box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset; 
		}
		.in{
			border-radius : 5px;
			border : 1px solid #d4d4d4; 
			width : 97%;
			background: -webkit-gradient(linear,left bottom,left top,color-stop(0, #eae8e8),color-stop(1, #ffffff));
			background: -webkit-linear-gradient(center bottom , #eae8e8 0%, #ffffff 100%) repeat scroll 0 0 #dbdbdb;  
			background: -moz-linear-gradient(center bottom , #eae8e8 0%, #ffffff 100%) repeat scroll 0 0 #dbdbdb;  
		}
		.bornes{
			background: -webkit-gradient(linear,left bottom,left top,color-stop(0, #eae8e8),color-stop(1, #ffffff));
			background: -webkit-linear-gradient(center bottom , #eae8e8 0%, #ffffff 100%) repeat scroll 0 0 #dbdbdb;  
			background: -moz-linear-gradient(center bottom , #eae8e8 0%, #ffffff 100%) repeat scroll 0 0 #dbdbdb;  
		}
		.bornes:hover{
			box-shadow: 0 0 6px grey;
			cursor : pointer;
		}
	</style>
</head>
<body>
	<% String dataList1=(String)request.getAttribute("dataList1"); %>
	<% String dataList2=(String)request.getAttribute("dataList2"); %>
	<section id="entete">
			<jsp:include page="entete_des_pages.jsp"/> 
			<table id="menu_gestion">
				<form  id="formEtape">
				<tr>
					<td id="menu_gestiontitre">
						<h2>Renseignement des etapes du projet: &nbsp
							<input type='text' placeholder='rechercher un projet' id="project" name='projet' style="border-radius : 3px 3px 3px 3px; border:0px solid #ffff;width: 20%; display:inline-block;"/>
							<input type='hidden' value='<% out.print(dataList1); %>'/>
							<input id='rechercherProj' type='submit' value='ok' name='rechercherProj'/>
						</h2>
					</td>
				</tr>
			</table>  
	</section> 
	<input type='hidden' id='idTache' name='idTache' />
	<div id="suivi_projet">
		<table class="filtreprogr" align="center" style="overflow:scroll;margin-bottom:2px;">
				<tr>
					<td id='info'>
						Tous les champs sont obligatoires
					</td>
				</tr>
			</table>
		<table id="tabEtape" class='listProj' class="display" style='float:left;overflow:auto;'>
			<tr>
				<td>
					Nom de l'etape:
				</td>
				<td>
					<input class="in" name='nomEtape' id="nomEtape"  type="text" disabled required/>
				</td>
			</tr>
			
			<tr>
				<td>
					description de l'etape:
				</td>
				<td>
					<textarea class="in" name='descriptionEtape' id="descriptionEtape" type="text" disabled required></textarea>
				</td>
			</tr>
			
			<tr>
				<td>
					Date de debut:
				</td>
				<td>
					<input class="in" name='dateDebut' id="dateDebut" type="date" class="date" disabled required/></br>
				</td>
			</tr>
			
			<tr>
				<td>
					Date de fin:
				</td>
				<td>
					<input class="in" name='dateFin' id="dateFin" type="date" class="date" disabled required/>
				</td>
			</tr>
			
			<tr>
				<td>
					Quantite:
				</td>
				<td>
					<input class="in" name='quantite' id="quantite" type="number" disabled required/>
				</td>
			</tr>
			
			<tr>
				<td>
					Cout de l'etape:
				</td>
				<td>
					<input class="in" name='coutEtape' id="coutEtape" type="number"  disabled required/>
				</td>
			</tr>
			
			<tr>
				<td>
					Etapes:
				</td>
				<td>
				 			Demarre<input  type="radio" name="statut" value="demarre"/>
						 	En cours<input  type="radio" name="statut" value="en_cours"/>
							terminee<input  type="radio" name="statut" value="terminee"/>
							Non demarre<input  type="radio" name="statut" value="non_demarree"/>
				</td>
			</tr>
			<tr><td colspan='2'> <input align="center" type="submit" value='valider' name="enregistrerProjet" class="btn" id="enregistrerEtape" /> </td></tr>
		</form>
		</table>
		<div id="details" style='float:right;overflow:auto;'>
			<h2 align='center' >TACHES DU PROJET &nbsp;<a href='#' id='activateForm' style='margin:10px;padding:10px;border-radius:10px;background:white;border:1px solid grey;' title="ajouter une tache">+</a></h2>
				<table class='step' style='float:left;' align='center' border='1'>
					
				</table>
		</div>
		<div id="alert" align='center' style='display:none; border:1px solid black;overflow:auto;'>
				
		</div>
		
	</div>
	<script type="text/javascript">
		//$('#details').accordion();
		var listeProj;
		$("#activateForm").on('click',function(){	
			if($("#project").val() != ""){
				$(".in").removeAttr("disabled");
				if($("#nomEtape").val()!= ""){
					$(".in").val('');
					$("#enregistrerEtape").val('valider');
				}
			}else{
				$("#alert").html("Veuiller selectionner d'abord un projet")
				$("#alert").dialog({
					modal:false,
					title:"Aucun projet selectionné!",
					width : "300",
					height : "150",
					buttons:{
						"OK":function(){
							$(this).dialog("close");
						}
					}
				});
			}
		});
		$(document).ready(function(){		
			$("#enregistrerEtape").live('click',function(e){
				e.preventDefault();
				var operation = $(this).attr("value")
				$(this).attr("value","valider");
				var donnees=$("#formEtape").serialize();
				donnees+="&enregistrerProj="+operation;
				var dateDebut = $('#dateDebut').val();
				var dateFin = $('#dateFin').val();
				if(dateDebut < dateFin){
					$(".in").val('');
					if(operation == "editer la tache")
						$(".step").replaceWith("<table class='step' style='float:left;width:100%;' align='center' border='1'><tr><td align='center'><img src='././ressources/loading-circle.gif'/></td></tr></table>");
					ajaxChargement(donnees);	
				}else{
					$("#alert").html("Attention la date de début doit précéder la date de fin. Veuiller modifier!");
					$("#alert").dialog({
						modal:false,
						title:"Probleme de dates !!!!!",
						width : "300",
						height : "200",
						buttons:{
							"Ok":function(){
								$("#alert").dialog("close");
							}
						}
					})
				}
			});
		});
		$(window).on('load',function(e){
			e.preventDefault();
			listeProj=$("input[type='hidden']").val().split("??");
			//listeProj = ["projet1","projet2","projet3"];
			$(".in").attr("disabled","true");
			$( "#project" ).val("");
			$( "#project" ).autocomplete({
			      source: listeProj
			});
			//donnees+="&enregistrerProj=loadStep";
			//ajaxChargement(donnees);	
		});
		
		$("#rechercherProj").on('click',function(e){
			e.preventDefault();
			$(".in").val('');
			$("#enregistrerEtape").val('valider');
			$(".in").attr("disabled","true");
			var donnees="projet="+$("#project").val();
			donnees+="&enregistrerProj=loadStep";
			$(".step").replaceWith("<table class='step' style='float:left;width:100%;' align='center' border='1'><tr><td align='center'><img src='././ressources/loading-circle.gif'/></td></tr></table>");
			ajaxChargement(donnees);	
		});
		
		$(".btnDelet").live('click',function(e){
			e.preventDefault();
			donnees="tache="+$(this).attr('id');
			donnees+="&enregistrerProj=delStep";
			donnees+="&projet="+$("#project").val();
			$("#alert").html("Vous êtes sur le point de supprimer une tâche!");
			$("#alert").dialog({
				modal:false,
				title:"SUPPRIMER LA TACHE !!!!!",
				width : "300",
				height : "150",
				buttons:{
					"Continuer":function(){
						$("#alert").dialog("close");
						ajaxChargement(donnees);
					},
					"Annuler":function(){
						$("#alert").dialog("close");
					}
				}
			});
		});
		$(".btnEdition").live('click',function(e){
			$("#idTache").val($(this).attr('id'))
			donnees="tache="+$(this).attr('id');
			donnees+="&enregistrerProj=editStep";
			$(".in").removeAttr("disabled");
			$("#info").replaceWith("<td id='info'><img src='././ressources/loading-rect.gif'/></td>")
			$.ajax({
				url : '/suite_project/projet',
				type : 'post',
				dataType : 'html',
				data : donnees,
				success : function(data, result, jqXHR){
					$("#info").replaceWith("<td id='info'>Tous les champs sont obligatoire</td>");
					datas=data.split("??");
					$('#nomEtape').val(datas[0]);
					$('#dateDebut').val(datas[1]);
					$('#dateFin').val(datas[2]);
					$('#coutEtape').val(datas[3]);
					$('#descriptionEtape').val(datas[4]);
					$('#quantite').val(datas[5]);
					if(datas[6] == 0)
						$("input[value='demarre']").attr("checked","checked");
					else if(datas[6] == 1) 
						$("input[value='en_cours']").attr("checked","checked");
					else if(datas[6] == 2) 
						$("input[value='terminee']").attr("checked","checked");
					else if(datas[6] == 1) 
						$("input[value='non_demarre']").attr("checked","checked");
					$('#enregistrerEtape').val('editer la tache');
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) { 
		            alert("Status: " + textStatus); alert("Error: " + errorThrown); 
		        }
			});
		});
		
		$("#draw td:nth-child(even)").live('mouseenter',function(){
			var ligne = $(this).attr('id');
			var coord = $(this).offset();
			$("#option").css("position","absolute");
			$("#option").css("top",coord.top);
			$("#option").css("left",coord.left);
			$("#option").fadeIn();
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
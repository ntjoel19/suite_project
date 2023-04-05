<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css"  href="design/Design_Global.css" />
	<link rel="stylesheet" type="text/css"  href="design/css_programmes.css" />
	<link rel="stylesheet" type="text/css"  href="design/jquery-ui.css" />
	<script src="jquery/jquery.js"></script>
	<script src="jquery/jquery-ui.js"></script>
	<script src="jquery/jquery-validate.js"></script>
	<script src="jquery/jquery-fileUpload.js"></script>
	<script src="jscript/juizDropDownMenu-2.0.0.min.js"></script>
	<script src="jscript/design.js"></script>
	<script src="jscript/fullcalendar.js"></script>
	<script src="jscript/moment.min.js"></script>
	<title>suivi des projets</title>
	<style type="text/css">
		#red{
			background : red;
		}
		#blue{
			background : blue;
		}
		#yellow{
			background : yellow;
		}
		#print{
			text-align: center;
			font-size: 1em;
			background: -webkit-gradient(linear,left bottom,left top,color-stop(0, #d4d4d4),color-stop(1, #e6e6e6));
			background: -webkit-linear-gradient(center bottom , #d4d4d4 0%, #e6e6e6 100%) repeat scroll 0 0 #dbdbdb;  
			background: -moz-linear-gradient(center bottom , #d4d4d4 0%, #e6e6e6 100%) repeat scroll 0 0 #dbdbdb;  
			border-left: 1px solid #c0c0c0;
			border-right: 1px solid #c0c0c0;
			border-bottom: 1px solid #c0c0c0;
			
			-webkit-box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset, 0 0 1px #fff inset;
			-moz-box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset;
			-ms-box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset, 0 0 1px #fff inset;
			-o-box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset, 0 0 1px #fff inset;
			box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset;
			
			border-radius : 0px 0px 5px 5px;
		}
		#projet{
			border: 1px solid #f6f5f5;
			background: #ffff;
		}
		#close{
			border-radius : 0;
			background: #dbdbdb url(../ressources/main.png) repeat-x scroll 0 0;  
			background: -webkit-gradient(linear,left bottom,left top,color-stop(0, #d4d4d4),color-stop(1, #e6e6e6));
			background: -webkit-linear-gradient(center bottom , #d4d4d4 0%, #e6e6e6 100%) repeat scroll 0 0 #dbdbdb;  
			background: -moz-linear-gradient(center bottom , #d4d4d4 0%, #e6e6e6 100%) repeat scroll 0 0 #dbdbdb;  
			
			cursor : pointer;
		}
		#close:hover{
			-webkit-box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset, 0 0 1px #fff inset;
			-moz-box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset;
			-ms-box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset, 0 0 1px #fff inset;
			-o-box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset, 0 0 1px #fff inset;
			box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset;
			border-radius : 4px;
			border: 1px solid #c0c0c0;
		}
		.btn{
				border-radius: 5px;
				border : 1px solid white;	
				width : 100%;
				height : 90%;
				background : #999999;
				font-family: "Palatino Linotype", "Book Antiqua", Palatino, serif;	
				color : #567;
				transition:background 500ms,color 500ms,border-color 500ms;	
				float : left;
			}
		.btn:hover{	
			width : 100%;
			box-shadow: 0 0 5px grey;
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
		embed{
			background : url(././ressources/loading.gif) no-repeat;
			visibility : hidden	
		}
	</style>
</head>
<body>
		<% 
			String edit=(String)request.getAttribute("edit");
			String add=(String)request.getAttribute("add");
			String genererFichPr=(String)request.getAttribute("genererFichPr");
			String etapeEdit=(String)request.getAttribute("etapeEdit");
			String voirEtapes=(String)request.getAttribute("voirEtapes");
			String suppr=(String)request.getAttribute("suppr");
			String budget=(String)request.getAttribute("budget");
			String option=(String)request.getAttribute("option");
			
			String dataList1=(String)request.getAttribute("dataList1");
			String dataList2=(String)request.getAttribute("dataList2");
		%>
	<form id='formTDR'>
		<div  id="print" style="display:none;">
			
		 	<table>
		 	<tr>
			 	<td><a href='#' class='someLink' id='btnFiche' title='generer fiche projet' >Fiche projet</a></td>
				<td><a href='#' class='someLink' id='btnTDR' title='generer fiche TDR' >Fiche TDR</a></td>
				<td><img src="././ressources/close.png" id='close'/></td>
		 	</tr></table>
		</div>
	</form>
	
	<section id="entete">
			<jsp:include page="entete_des_pages.jsp"/>
			<table id="menu_gestion">
				<tr><form id="formTab" method='post' action="/suite_project/projet">
					<td id="menu_gestiontitre"><h2>Suivi des projets</h2></td>
					<td class="menu_gestiontitrebtn"><a title="aller à l'acceuil" href="/suite_project/admin"><img class="img" src="ressources/home.png"/></a></td>
					<td class="menu_gestiontitrebtn"><input title="actualiser" id="btnReload" type="image" src="././ressources/actualiser.png" name="enregistrerProj" value="reload"/></td>
					<% out.print(edit); %>
					<% out.print(add); %>
					<% out.print(suppr); %>
					<% out.print(genererFichPr); %>
					<% out.print(voirEtapes); %>
					<% out.print(etapeEdit); %>
					<% out.print(budget); %>
				</tr>
			</table>  
	</section> 
	
	<div id="suivi_projet">
		<table class="filtreprogr" align="center" style="overflow:scroll;">
				<tr>
					<td>
						<label for="choix_programme">Programmes</label>
						<select id="choix_programme">
							<option value="All" selected>--All--</option>
							<% out.print(option); %>
						</select>
					</td>
					<td>
						<label for="choix_action">Actions</label>
						<select id="choix_action">
							<option value="All" selected>--All--</option>
							<% //out.print(option); %>
						</select>
					</td>
					<td>
						<input list="intitule" type="text" id="choix_intitule"  name='intitule' placeholder="Intitulé projet">
						<input type='hidden' id='hiddenIntitule' value='<% out.print(dataList1); %>'/>
					</td>
					
					<td>
						<input list="porteur" type="text" id="choix_porteur" name='porteur' placeholder="Porteur">
						<input type='hidden' id='hiddenPorteur' value='<% out.print(dataList2); %>'/>
					</td>
					
				</tr>
			</table>
		<table id="corpsprogr" class='listProj' class="display" cellspacing="1">
			<thead>
					<% String nb=(String)request.getAttribute("nb");%>
				    <tr><th colspan="10" id="titreTab">Liste des projets (<% out.print(nb);%>)<img class='loader' src='./ressources/loading-rect.gif'/></tr>
					<tr>
						 <th class="corpsprogrcheckBox">
						 	<input type="checkbox" id='all'/><label for='all'></label>
						 </th>
						 <th>
						 	Intitule projet
						 </th>
						 <th>
						 	Action
						 </th>
						 <th>
						 	Programme
						 </th>
						 <th>
						 	Objectif
						 </th>
						 <th>
						 	Info projet
						 </th>
						 <th>
						 	Realisation
						 </th>
						 <th>
						 	Porteur
						 </th>
						 <th>
						 	Tutelle
						 </th>
						 <th id="lastShild">
						 	Operations
						 </th>
					</tr>
				</thead>
				<tbody id='tbody'>
					<!-- Cette partie du tableau est chargée depuis la BD -->
					<%
			            String tabrow = (String)request.getAttribute("tr");
			            out.println( tabrow );
		            %>
				</tbody>
				</form>
		</table>  
		
		 
			<div id="embed" style='display:none; border:1px solid black;overflow:auto;'>
				<embed height='500px' type='application/pdf' width='60%' src='C:\eclipse-jee-mars-1-win32\eclipse\workspace\suite_project\WebContent\ressources\projet\Nwamek Ntepp9.pdf'/>
			</div>
			<form id='formAddPiece' action='/suite_project/upload' method='post' enctype='multipart/form-data'>
				<div id="dossier_projet" align='center' style='display:none; border:1px solid black;overflow:auto;'>
				
				</div>
			</form>	
			<div id="alert" align='center' style='display:none; border:1px solid black;overflow:auto;'>
				
			</div>
		
	</div>
	
	<div id="pieds">
		<jsp:include page="pieds_des_pages.jsp"/>
	</div>
	<script>
	$(window).on('load',function(e){
		e.preventDefault();
		listeIntitule=$("#hiddenIntitule").val().split("??");
		listePorteur=$("#hiddenPorteur").val().split("??");
		$( "#choix_intitule" ).autocomplete({
		      source: listeIntitule
		});
		$( "#choix_porteur" ).autocomplete({
		      source: listePorteur
		});
		//donnees+="&enregistrerProj=loadStep";
		//ajaxChargement(donnees);	
	});
		var donnees="iuy";
		$(document).ready(function(){
			//$("table" ).accordion();
			$("input[type='checkbox']").live('click',function(){
				var tdr = $(this).attr("path_tdr");
				var code_projet = $(this).attr("code_projet");
				if(($(this).is(':checked'))===true){
					var href = "/suite_project/fichiers/projet/"+code_projet+"/"+tdr;
					$("#btnFiche").attr("href",href);
					$("#btnTDR").attr("href",href);
				}
			});
			$("#btnDelete").on('click',function(e){
				e.preventDefault();
				var donnees=$("#formTab").serialize();
				donnees+="&enregistrerProj=delete";
				$("#alert").html("Attention la suppression d'un projet provoquera la suppresion des taches associées et de toutes lees pieces associées à celui-ci!");
				$("#alert").dialog({
					modal:false,
					title:"ATTENTION !!!!!",
					width : "300",
					height : "200",
					buttons:{
						"Continuer":function(){
							$("#alert").dialog("close");
							$("#tbody").replaceWith("<tbody id='tbody'><tr><td colspan='10' align='center'><img src='././ressources/loading-circle.gif'/></td></tr></tbody");
							$.ajax({
								url : '/suite_project/projet',
								type : 'POST',
								data : donnees,
								dataType : 'html',
								success : function(data, result, jqXHR){
									datas=data.split("??");
									datas[0]="<tbody id='tbody'>"+datas[0]+"</tbody>";
									$("#tbody").replaceWith(datas[0]);
									datas[1]="<th colspan='10' id='titreTab'>Liste des projets ("+datas[1]+")</th>";
									$("#titreTab").replaceWith(datas[1]);	
								},
								error: function(XMLHttpRequest, textStatus, errorThrown) { 
						            //alert("Status: " + textStatus); alert("Error: " + errorThrown); 
						            $("#alert").html("Une erreur s'est produite : "+textStatus);
						            $("#alert").dialog({
										modal:false,
										title:"ERREUR INTERNE !!!!!",
										width : "200",
										height : "150",
										buttons:{
											"Ok":function(){
												$("#alert").dialog("close");
											}
										}
						            });
						        } 
							});
						},
						"Annuler":function(){
							$("#alert").dialog("close");
						}
					}
				});
			});
			
			
		});
		
		$(document).on('ready',function(){
			$("#choix_programme").live('change',function(e){
				e.preventDefault();
				var donnees="";
	
				donnees="programme="+$("#choix_programme option:selected").text();
				donnees+="&intitule="+$("#choix_intitule").val();
				donnees+="&porteur="+$("#choix_porteur").val();
				donnees+="&enregistrerProj=filtrerProgr";
				$("#tbody").replaceWith("<tbody id='tbody'><tr><td colspan='10' align='center'><img src='././ressources/loading-circle.gif'/></td></tr></tbody");
				
				$.ajax({
					url : '/suite_project/projet',
					type : 'POST',
					data : donnees,
					dataType : 'html',
					success : function(data, result, jqXHR){
						var datas=data.split("??");
						$("#choix_action").replaceWith("<select name='choix_action' id='choix_action'>"+datas[0]);	
						datas[1]="<tbody id='tbody'>"+datas[1]+"</tbody>"
						$("#tbody").replaceWith(datas[1]);
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) { 
						 $("#alert").html("Une erreur s'est produite : "+textStatus);
				            $("#alert").dialog({
								modal:false,
								title:"ERREUR INTERNE !!!!!",
								width : "200",
								height : "150",
								buttons:{
									"Ok":function(){
										$("#alert").dialog("close");
									}
								}
				            }); 
			        } 
				});
			});
			
			$("#choix_action").live('change',function(e){
				e.preventDefault();
				var donnees="";
	
				donnees="action="+$("#choix_action option:selected").text();
				donnees+="&programme="+$("#choix_programme option:selected").text();
				donnees+="&intitule="+$("#choix_intitule").val();
				donnees+="&porteur="+$("#choix_porteur").val();
				donnees+="&enregistrerProj=filtrerAction";
				$("#tbody").replaceWith("<tbody id='tbody'><tr><td colspan='10' align='center'><img src='././ressources/loading-circle.gif'/></td></tr></tbody");
				
				$.ajax({
					url : '/suite_project/projet',
					type : 'POST',
					data : donnees,
					dataType : 'html',
					success : function(data, result, jqXHR){
						var datas=data.split("??");
						//$("#choix_action").replaceWith("<select name='choix_action' id='choix_action'>"+datas[0]);	
						datas[0]="<th colspan='10' id='titreTab'>Liste des projets ("+datas[0]+")</th>";
						$("#titreTab").replaceWith(datas[0]);
						datas[1]="<tbody id='tbody'>"+datas[1]+"</tbody>"
						$("#tbody").replaceWith(datas[1]);
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) { 
						 $("#alert").html("Une erreur s'est produite : "+textStatus);
				            $("#alert").dialog({
								modal:false,
								title:"ERREUR INTERNE !!!!!",
								width : "200",
								height : "150",
								buttons:{
									"Ok":function(){
										$("#alert").dialog("close");
									}
								}
				            }); 
			        } 
				});
			});
			
			$("#choix_intitule").live('blur',function(e){
				e.preventDefault();
				var donnees="";
	
				donnees="action="+$("#choix_action option:selected").text();
				donnees+="&programme="+$("#choix_programme option:selected").text();
				donnees+="&intitule="+$("#choix_intitule").val();
				donnees+="&porteur="+$("#choix_porteur").val();
				donnees+="&enregistrerProj=filtrerAction";
				$("#tbody").replaceWith("<tbody id='tbody'><tr><td colspan='10' align='center'><img src='././ressources/loading-circle.gif'/></td></tr></tbody");
				
				$.ajax({
					url : '/suite_project/projet',
					type : 'POST',
					data : donnees,
					dataType : 'html',
					success : function(data, result, jqXHR){
						var datas=data.split("??");
						//$("#choix_action").replaceWith("<select name='choix_action' id='choix_action'>"+datas[0]);	
						datas[0]="<th colspan='10' id='titreTab'>Liste des projets ("+datas[0]+")</th>";
						$("#titreTab").replaceWith(datas[0]);
						datas[1]="<tbody id='tbody'>"+datas[1]+"</tbody>"
						$("#tbody").replaceWith(datas[1]);
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) { 
						 $("#alert").html("Une erreur s'est produite : "+textStatus);
				            $("#alert").dialog({
								modal:false,
								title:"ERREUR INTERNE !!!!!",
								width : "200",
								height : "150",
								buttons:{
									"Ok":function(){
										$("#alert").dialog("close");
									}
								}
				            }); 
			        } 
				});
			});
			
			$("#choix_porteur").live('blur',function(e){
				e.preventDefault();
				var donnees="";
	
				donnees="action="+$("#choix_action option:selected").text();
				donnees+="&programme="+$("#choix_programme option:selected").text();
				donnees+="&intitule="+$("#choix_intitule").val();
				donnees+="&porteur="+$("#choix_porteur").val();
				donnees+="&enregistrerProj=filtrerAction";
				$("#tbody").replaceWith("<tbody id='tbody'><tr><td colspan='10' align='center'><img src='././ressources/loading-circle.gif'/></td></tr></tbody");
				$.ajax({
					url : '/suite_project/projet',
					type : 'POST',
					data : donnees,
					dataType : 'html',
					success : function(data, result, jqXHR){
						var datas=data.split("??");
						//$("#choix_action").replaceWith("<select name='choix_action' id='choix_action'>"+datas[0]);	
						datas[0]="<th colspan='10' id='titreTab'>Liste des projets ("+datas[0]+")</th>";
						$("#titreTab").replaceWith(datas[0]);
						datas[1]="<tbody id='tbody'>"+datas[1]+"</tbody>"
						$("#tbody").replaceWith(datas[1]);
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) { 
						 $("#alert").html("Une erreur s'est produite : "+textStatus);
				            $("#alert").dialog({
								modal:false,
								title:"ERREUR INTERNE !!!!!",
								width : "200",
								height : "150",
								buttons:{
									"Ok":function(){
										$("#alert").dialog("close");
									}
								}
				            }); 
			        } 
				});
			});
		});
		
		$(document).ready(function(donnees){
			$("#btnReload").live('click',function(e){
				e.preventDefault();
				donnees="enregistrerProj=reload";
				$.ajax({
					url : '/suite_project/projet',
					type : 'POST',
					data : donnees,
					dataType : 'html',
					success : function(data, result, jqXHR){
						datas=data.split("??");
						datas[0]="<tbody id='tbody'>"+datas[0]+"</tbody>";
						//alert(datas[0]);
						$("#tbody").replaceWith(datas[0]);
						datas[1]="<th colspan='10' id='titreTab'><h2>Liste des projets ("+datas[1]+")</h2></th>";
						$("#titreTab").replaceWith(datas[1]);
						
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) { 
						 $("#alert").html("Une erreur s'est produite : "+textStatus);
				            $("#alert").dialog({
								modal:false,
								title:"ERREUR INTERNE !!!!!",
								width : "200",
								height : "150",
								buttons:{
									"Ok":function(){
										$("#alert").dialog("close");
									}
								}
				            });
			        } 
				});
			});
			
			$("#tbody tr td a[href='#']").live('click',function(e){
				e.preventDefault();
				var projet = $(this).attr("id");
				//alert($("."+projet).attr("style"));
				if($("."+projet).attr("style")=="display:none;"){
					$(this).html("-");
					$("."+projet).slideDown(500);
					$("."+projet).attr("style", "display:block;");
				}else{
					$(this).html("+");
					$("."+projet).hide("slow");
					$("."+projet).attr("style", "display:none;");
				}
			});
			
			$(".btnDelet").live('click',function(e){
				donnees="projet="+$(this).attr('id');
				donnees+="&enregistrerProj=deleteUnique";
				$("#alert").html("Attention la suppression d'un projet provoquera la suppresion des taches associées et de toutes lees pieces associées à celui-ci!");
				$("#alert").dialog({
					modal:false,
					title:"ATTENTION !!!!!",
					width : "300",
					height : "200",
					buttons:{
						"Continuer":function(){
							$("#alert").dialog("close");
							$.ajax({
								url : '/suite_project/projet',
								type : 'post',
								dataType : 'html',
								data : donnees,
								success : function(data, result, jqXHR){
									datas=data.split("??");
									datas[0]="<tbody id='tbody'>"+datas[0]+"</tbody>";
									//alert(datas[0]);
									$("#tbody").replaceWith(datas[0]);
									datas[1]="<th colspan='10' id='titreTab'><h2>Liste des projets ("+datas[1]+")</h2></th>";
									$("#titreTab").replaceWith(datas[1]);
								},
								error: function(XMLHttpRequest, textStatus, errorThrown) { 
									 $("#alert").html("Une erreur s'est produite : "+textStatus);
							            $("#alert").dialog({
											modal:false,
											title:"ERREUR INTERNE !!!!!",
											width : "200",
											height : "150",
											buttons:{
												"Ok":function(){
													$("#alert").dialog("close");
												}
											}
							            });
						        }
							});
						},
						"Annuler":function(){
							$("#alert").dialog("close");
						}
					}
				});
			});
			
			$(".btnDossier").live('click',function(){
				//e.preventDefault();
				var id=$(this).attr("id");
				donnees="projet="+id;
				donnees+="&enregistrerProj=afficheDossierProjet";
				$.ajax({
					url : '/suite_project/projet',
					type : 'post',
					dataType : 'html',
					data : donnees,
					success : function(data, result, jqXHR){
						$("#dossier_projet").html(data);
						var id = $("input[placeholder='Description de la piece']").attr("value");
						$("#dossier_projet").dialog({
							modal:false,
							title:"Dossier du projet",
							width : "300",
							height : "500"
						});

					},
					error: function(XMLHttpRequest, textStatus, errorThrown) { 
						$("#alert").html("Oups, une erreur s'est produite!! ");
						$("#alert").dialog({
							modal:false,
							title:"ERREUR !!!!!",
							width : "300",
							height : "200",
							buttons:{
								"OK":function(){
									$("#alert").dialog("close");
								}
							}
						});  
			        }
				});	
			});
			$("#addPiece").live('click',function(e){
				e.preventDefault();
				$("#formAddPieceTableRow").slideDown(1000);
				$("#formAddPieceTableRow").css("style","");
			});
			
			$(".supprimerPiece").live('click',function(e){
				var id=$(this).attr("id");
				var ids=id.split("--");
				donnees="piece="+ids[0];
				donnees+="&projet="+ids[1];
				donnees+="&enregistrerProj=supprPieceProjet";
				//alert(donnees);
				if(confirm("Etes-vous sures de vouloir supprimer?")){
					$.ajax({
						url : '/suite_project/projet',
						type : 'post',
						dataType : 'html',
						data : donnees,
						success : function(data, result, jqXHR){
							$("#tablePiecesProj").replaceWith(data);
						},
						error: function(XMLHttpRequest, textStatus, errorThrown) { 
				            alert("Status: " + textStatus); alert("Error: " + errorThrown); 
				        }
					});	
				}
			});
		});
			
		/*
		$("#tbody tr").on({
			mouseenter:function(){
				var ligne = $(this).attr('id');
				var coord = $('#'+ligne).offset();
				//$("#rowOptions").css("position","absolute");
				//$("#rowOptions").css("top",coord.top);
				//$("#rowOptions").css("left",coord.left+20);
				//$("#rowOptions").fadeIn();
				
				$("#rowOptions #btnDelet").on('click',function(e){
					e.preventDefault();
					donnees="enregistrerProj=delete&"+ligne+"=on";
					$.ajax({
						url : '/suite_project/projet',
						type : 'POST',
						data : donnees,
						async: false,
						dataType : 'html',
						success : function(data, result, jqXHR){
							datas=data.split("??");
							datas[0]="<tbody id='tbody'>"+datas[0]+"</tbody>"
							$("#tbody").replaceWith(datas[0]);
							datas[1]="<th colspan='7' id='titreTab'><h2>Liste des projets ("+datas[1]+")</h2></th>";
							$("#titreTab").replaceWith(datas[1]);
							$('#tbody tr').unbind('click');
						},
						error: function(XMLHttpRequest, textStatus, errorThrown) { 
				            alert("Status: " + textStatus); alert("Error: " + errorThrown); 
				        } 
					});
				});
					
				
				
			}
			mouseleave:function(){
				$("#rowOptions").fadeOut();
				$("#rowOptions:animated").stop();
			}
		});*/
		
		addStep('#btnPrint','#print','#tdr','#ao','#close');
    </script>
</body>
</html>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
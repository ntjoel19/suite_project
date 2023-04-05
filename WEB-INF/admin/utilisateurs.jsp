<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="charset=utf-8" />
	<link rel="stylesheet" type="text/css"  href="design/Design_Global.css" />
	<link rel="stylesheet" type="text/css"  href="design/jquery-ui.css" />
	<link rel="stylesheet" type="text/css"  href="design/css_programmes.css" />
	<link rel="stylesheet" type="text/css"  href="design/dataTables.jqueryui.css" />
	<link rel="stylesheet" type="text/css"  href="design/globaldesign.css" />
	<script type="text/javascript" src="./jscript/design.js"></script>
	<title>Gestion des utilisateurs</title>
	<style>
		.hidden{
			display : none;
		}
	</style>
</head>
<body>
		<% 
			String edit=(String)request.getAttribute("edit");
			String add=(String)request.getAttribute("add");
			String del=(String)request.getAttribute("del");
			String bloq=(String)request.getAttribute("bloq");
			String dbloq=(String)request.getAttribute("dbloq");
			String impossibleDediter=(String)request.getAttribute("impossibleDetiter");
			if( impossibleDediter == null) impossibleDediter="";
		%>
		<!-- On inclut l'entete (baniere + menu) -->
		<section id="entete">
			<jsp:include page="entete_des_pages.jsp"/>  
			<table id="menu_gestion">
				<tr><form id="formTab" action="/suite_project/utilisateur" method='post'>
					<td id="menu_gestiontitre"><h2 style='float:left;'>Gestion des utilisateurs</h2></td>
					<td class="menu_gestiontitrebtn"><a title="aller à l'acceuil" href="/suite_project/admin"><img class="img" src="ressources/home.png"/></a></td>
					<td class="menu_gestiontitrebtn"><input title='actualiser la liste' id="btnReload" type="image" src="././ressources/actualiser.png" name="enregistrerProgr" value="reload"/></td>
					<% out.print(add); %>
					<% out.print(edit); %>
					<% out.print(bloq); %>
					<% out.print(dbloq); %>
					<% out.print(del); %>
				</tr>
			</table> 
		</section>
		<div align='center' style='color:red;' color='color'><span ><% out.print(impossibleDediter); %></span></div>
		<section id="contenu">
			<table class="filtreprogr" align="center">
				<tr>
					<td>
						<input list="designation" type="text" id="choix_bieres" placeholder="Nom">
						<datalist id="designation">
							  <option value="Programmes">
							  <option value="Pils">
							  <option value="Kronenbourg">
							  <option value="Grimbergen">
						</datalist>
					</td>
					<td>
						<input list="intitule" type="text" id="choix_bieres" placeholder="role">
						<datalist id="intitule">
							  <option value="Meteor">
							  <option value="Pils">
							  <option value="Kronenbourg">
							  <option value="Grimbergen">
						</datalist>
					</td>
					<td>
						<input list="dates" type="text" id="date_enreg" placeholder="date d'enregistrement">
						<datalist id="date_enreg">
							  <option value="Meteor">
							  <option value="Pils">
							  <option value="Kronenbourg">
							  <option value="Grimbergen">
						</datalist>
					</td>
				</tr>
			</table>
			<table id="corpsprogr" class="display"  cellspacing="1" width="100%">
				<thead>
					<tr><th colspan='8' id="titreTab">Liste des utilisateurs <h4 style='display:inline;' color='red'><% String nb=(String)request.getAttribute("nb");%></th></tr>
					<tr>
						<th class="corpsprogrcheckBox"><input type="checkbox" name='all' id='all'/><label for='all'></label></th>
						<th>ID</th>
						<th>Roles</th>
						<th>Pseudo</th>
						<th>Nom</th>
						<th>E-mail</th>
						<th>Telephone</th>
						<th>Bloque</th>
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
	</section>	
	<div id='alert' style='display:none'>
	
	</div>
	<!-- Inclusion de footer -->
	<div id="pieds">
		<jsp:include page="pieds_des_pages.jsp"/>
	</div>
	<script>
		afficherPlus("#more1","#hd1");
		afficherPlus("#more2","#hd2");
		$(document).ready(function(){
			$("#btnDelete").click(function(e){
				e.preventDefault();
				var donnees=$("#formTab").serialize();
				donnees+="&operation=delete";
				$("#alert").html('Vous êtes sur le point de supprimer un utilisateur de la base de donnée. Etes-vous sûre?')
				$("#alert").dialog({
					modal:false,
					width:250,
					height:150,
					title:'Voulez-vous supprimer?',
					buttons:{
						"Confirmer":function(){
							$("#alert").dialog("close");
							$.ajax({
								url : '/suite_project/utilisateur',
								type : 'POST',
								data : donnees,
								dataType : 'html',
								success : function(data, result, jqXHR){
									//alert(data);
									datas=data.split("??");
									datas[0]="<tbody id='tbody'>"+datas[0]+"</tbody>"
									$("#tbody").replaceWith(datas[0]);
									datas[1]="<th colspan='8' id='titreTab'>Liste des Utilisateurs ("+datas[1]+")</th>";
									$("#titreTab").replaceWith(datas[1]);	
								},
								error: function(XMLHttpRequest, textStatus, errorThrown) { 
						            //alert("Status: " + textStatus); alert("Error: " + errorThrown); 
						            $("#alert").html("Une erreur interne est survenue : " + textStatus);
						            $("#alert").dialog({
										modal:false,
										width:200,
										height:150,
										title:'Erreur interne!',
										buttons:{
											
											"OK":function(){
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
			/*
			$("#btnEdit").click(function(e){
				e.preventDefault();
				donnees=$("#formTab").serialize();
				dataTemp1=donnees.split('=');
				donnees="ligne="+dataTemp1[0];
				donnees+="&operation=editer";
				$.ajax({
					url : '/suite_project/utilisateur',
					type : 'POST',
					data : donnees,
					dataType : 'html',
					success : function(data, result, jqXHR){
						
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) { 
			            alert("Status: " + textStatus); alert("Error: " + errorThrown); 
			        } 
				});
			});*/
			$("#btnBloquer").click(function(e){
				e.preventDefault();
				donnees=$("#formTab").serialize();
				dataTemp1=donnees.split('=');
				donnees="code_pers="+$("#personne"+dataTemp1[0]).text();
				donnees+="&operation=bloquer";
				$("#alert").html("Vous Bloqué l'utilisateur "+$("#personne"+dataTemp1[0]).text()+" !!");
				$.ajax({
					url : '/suite_project/utilisateur',
					type : 'POST',
					data : donnees,
					dataType : 'html',
					success : function(data, result, jqXHR){
						$("#alert").dialog({
							modal:false,
							width:200,
							height:150,
							title:'Utilisateur bloqué!',
							buttons:{
								
								"OK":function(){
									$("#alert").dialog("close");
								}
							}
						});
						$("#bloque"+dataTemp1[0]).html("<img width='20px' height='20px' src='././ressources/bloquer.png'/>");
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) { 
						$("#alert").html("Une erreur interne est survenue : " + textStatus);
			            $("#alert").dialog({
							modal:false,
							width:200,
							height:150,
							title:'Erreur interne!',
							buttons:{
								
								"OK":function(){
									$("#alert").dialog("close");
								}
							}
						});
			        } 
				});
			});
			$(document).ready(function(){
				$("#choix_nom").blur(function(e){
					e.preventDefault();
					var donnees="";
					
						donnees="designation="+$("#choix_designation").val();
						donnees+="&intitule="+$("#choix_intitule").val();
						donnees+="&objectif="+$("#choix_objectif").val();
						donnees+="&annee="+$("#choix_annee").val();
						donnees+="&enregistrerAction=filtrer";
					
						$.ajax({
							url : '/suite_project/action',
							type : 'POST',
							data : donnees,
							dataType : 'html',
							success : function(data, result, jqXHR){
								datas=data.split("??");
								datas[0]="<tbody id='tbody'>"+datas[0]+"</tbody>"
								$("#tbody").replaceWith(datas[0]);
								datas[1]="<th colspan='6' id='titreTab'><h2>Liste des actions ("+datas[1]+")</h2></th>";
								$("#titreTab").replaceWith(datas[1]);
								
							},
							error: function(XMLHttpRequest, textStatus, errorThrown) { 
								$("#alert").html("Une erreur interne est survenue : " + textStatus);
					            $("#alert").dialog({
									modal:false,
									width:200,
									height:150,
									title:'Erreur interne!',
									buttons:{
										
										"OK":function(){
											$("#alert").dialog("close");
										}
									}
								});
					        } 
						});
				});
				$("#choix_role").blur(function(e){
					e.preventDefault();
					var donnees="";

						donnees="designation="+$("#choix_designation").val();
						donnees+="&intitule="+$("#choix_intitule").val();
						donnees+="&objectif="+$("#choix_objectif").val();
						donnees+="&annee="+$("#choix_annee").val();
						donnees+="&enregistrerAction=filtrer";
					//var donnees=$("#formTab").serialize();
					
					$.ajax({
						url : '/suite_project/action',
						type : 'POST',
						data : donnees,
						dataType : 'html',
						success : function(data, result, jqXHR){
							datas=data.split("??");
							datas[0]="<tbody id='tbody'>"+datas[0]+"</tbody>"
							$("#tbody").replaceWith(datas[0]);
							datas[1]="<th colspan='6' id='titreTab'><h2>Liste des actions ("+datas[1]+")</h2></th>";
							$("#titreTab").replaceWith(datas[1]);
							
						},
						error: function(XMLHttpRequest, textStatus, errorThrown) { 
							$("#alert").html("Une erreur interne est survenue : " + textStatus);
				            $("#alert").dialog({
								modal:false,
								width:200,
								height:150,
								title:'Erreur interne!',
								buttons:{
									
									"OK":function(){
										$("#alert").dialog("close");
									}
								}
							}); 
				        } 
					});
				});
				$("#choix_date").blur(function(e){
					e.preventDefault();
					var donnees="";

						donnees="designation="+$("#choix_designation").val();
						donnees+="&intitule="+$("#choix_intitule").val();
						donnees+="&objectif="+$("#choix_objectif").val();
						donnees+="&annee="+$("#choix_annee").val();
						donnees+="&enregistrerAction=filtrer";

					//var donnees=$("#formTab").serialize();
					
					$.ajax({
						url : '/suite_project/action',
						type : 'POST',
						data : donnees,
						dataType : 'html',
						success : function(data, result, jqXHR){
							datas=data.split("??");
							datas[0]="<tbody id='tbody'>"+datas[0]+"</tbody>"
							$("#tbody").replaceWith(datas[0]);
							datas[1]="<th colspan='6' id='titreTab'><h2>Liste des actions ("+datas[1]+")</h2></th>";
							$("#titreTab").replaceWith(datas[1]);
							
						},
						error: function(XMLHttpRequest, textStatus, errorThrown) { 
							$("#alert").html("Une erreur interne est survenue : " + textStatus);
				            $("#alert").dialog({
								modal:false,
								width:200,
								height:150,
								title:'Erreur interne!',
								buttons:{
									
									"OK":function(){
										$("#alert").dialog("close");
									}
								}
							});
				        } 
					});
				});
			});
			$("#btnDebloquer").click(function(e){
				e.preventDefault();
				donnees=$("#formTab").serialize();
				dataTemp1=donnees.split('=');
				donnees="code_pers="+$("#personne"+dataTemp1[0]).text();
				donnees+="&operation=debloquer";
				$("#alert").html("Vous débloqué l'utilisateur "+$("#personne"+dataTemp1[0]).text()+" !!");
				$.ajax({
					url : '/suite_project/utilisateur',
					type : 'POST',
					data : donnees,
					dataType : 'html',
					success : function(data, result, jqXHR){
						$("#alert").dialog({
							modal:false,
							width:200,
							height:150,
							title:'Utilisateur bloqué!',
							buttons:{
								
								"OK":function(){
									$("#alert").dialog("close");
								}
							}
						});
						$("#bloque"+dataTemp1[0]).html("<img width='20px' height='20px' src='././ressources/debloquer.png'/>");
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) { 
						$("#alert").html("Une erreur interne est survenue : " + textStatus);
			            $("#alert").dialog({
							modal:false,
							width:200,
							height:150,
							title:'Erreur interne!',
							buttons:{
								
								"OK":function(){
									$("#alert").dialog("close");
								}
							}
						});
			        } 
				});
			});
			$(document).ready(function(){
				$("#btnReload").click(function(e){
					e.preventDefault();
					var donnees="operation=reload";
					$.ajax({
						url : '/suite_project/utilisateur',
						type : 'POST',
						data : donnees,
						dataType : 'html',
						success : function(data, result, jqXHR){
							datas=data.split("??");
							datas[0]="<tbody id='tbody'>"+datas[0]+"</tbody>"
							$("#tbody").replaceWith(datas[0]);
							datas[1]="<th colspan='8' id='titreTab'>Liste des utilisateurs ("+datas[1]+")</th>";
							$("#titreTab").replaceWith(datas[1]);
							
						},
						error: function(XMLHttpRequest, textStatus, errorThrown) { 
							$("#alert").html("Une erreur interne est survenue : " + textStatus);
				            $("#alert").dialog({
								modal:false,
								width:200,
								height:150,
								title:'Erreur interne!',
								buttons:{
									
									"OK":function(){
										$("#alert").dialog("close");
									}
								}
							});
				        } 
					});
				});
		});
		})
	</script>
    
</body>
</html>
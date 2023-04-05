<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="charset=utf-8" />
	<meta http-equiv="Content-Type" content="charset=utf-8" />
	<link rel="stylesheet" type="text/css"  href="design/Design_Global.css" />
	<link rel="stylesheet" type="text/css"  href="design/jquery-ui.css" />
	<link rel="stylesheet" type="text/css"  href="design/css_programmes.css" />
	<link rel="stylesheet" type="text/css"  href="design/dataTables.jqueryui.css" />
	<link rel="stylesheet" type="text/css"  href="design/globaldesign.css" />
	<script src="jquery/jquery.js"></script>
	<script src="jquery/jquery-ui.js"></script>
	<script src="jquery/jquery-validate.js"></script>
	<script src="jscript/juizDropDownMenu-2.0.0.min.js"></script>
	<script src="jscript/design.js"></script>
	<title>Espace des Publications</title>
	<style>
		#pub{
			border: 1px solid #f6f5f5;
			background: #ffff;
		}
	</style>
</head>
<body>
		<% 
			String code_pers=(String)session.getAttribute("code_pers");
			String nomUser=(String)session.getAttribute("nom");
			String emailUser=(String)session.getAttribute("email");
			String contactUser=(String)session.getAttribute("contacts");
			String code_role_user=(String)session.getAttribute("code_role");
			
			String lAuteur=(String)request.getAttribute("listAuteurs");
			String lTitre=(String)request.getAttribute("listTitres");
			String add=(String)request.getAttribute("add");
			String edit=(String)request.getAttribute("edit");
			String suppr=(String)request.getAttribute("suppr");
			
		%>
		<!-- On inclut l'entete (baniere + menu) -->
		<section id="entete">
			<jsp:include page="entete_des_pages.jsp"/>  
			<table id="menu_gestion">
				<tr><form action="/suite_project/publication" method="get" id='formTab'>
					<td id="menu_gestiontitre"><h2>Gestion des publications</h2></td>
					<td class="menu_gestiontitrebtn"><a href="/suite_project/admin"><img class="img" src="ressources/home.png"/></a></td>
					<% out.print(add); %>
					<% out.print(edit); %>
					<% out.print(suppr); %>
				</tr>
			</table>
		</section>	
		<section id="suivi_projet">
			<table class="filtreprogr" align="center">
				<tr>
					<td>
						<input type="text" id="choix_auteur" placeholder="auteur"/>
						<input type='hidden' id='lAuteur' value='<% out.print(lAuteur);%>'>
					</td>
					<td>
						<input type="text" id="choix_titre" placeholder="titre"/>
						<input type='hidden' id='lTitre' value='<% out.print(lTitre);%>'>
						
					</td>
					<td>
						<input type="text" id="choix_date" placeholder="date..." />
						
					</td>
				</tr>
			</table>
			<table id="corpsprogr" class="display"  cellspacing="1" width="100%">
				<thead>
					<% String nb=(String)request.getAttribute("nb");%>
					<tr><th colspan="5" id="titreTab"><h2>Liste des Publications (<% out.print(nb);%> enregistrements)</h2></th></tr>
					<tr>
						 <th class="corpsprogrcheckBox">
						 	
						 </th>
						 <th>
						 	Auteur
						 </th>
						 <th>
						 	Titre de l'article
						 </th>
						 <th>
						 	Contenu
						 </th>
						 <th id="lastShild">
						 	Date de publication
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
	</section>
	<div id="alert" style="display:none;">
		
	</div>
	<!-- Inclusion de footer -->
	<div id="pieds">
		<jsp:include page="pieds_des_pages.jsp"/>
	</div>
	
	<script type="text/javascript" src="jscript/jquery.js"></script>
    <script type="text/javascript" src="jscript/design.js"></script>
    <script type="text/javascript" src="jscript/jquery-ui.min.js"></script>
    <script>
	    $(window).on('load',function(e){
			e.preventDefault();
			listAuteur=$("#lAuteur").val().split("??");
			listePorteur=$("#lTitre").val().split("??");
			$( "#choix_auteur" ).autocomplete({
			      source: listAuteur
			});
			$( "#choix_titre" ).autocomplete({
			      source: listePorteur
			});	
		});
		$(document).ready(function(){
			
			$("input[type='checkbox']").on('click',function(e){
				var ligne=$(this).attr("name");
				if(($(this).is(':checked'))===true){
					var href = $("#btnEdit").attr("href");
					var listParam =($("#btnEdit").attr("href").split("?")[1]).split("&");
					alert(contient(listParam,"ligne="+ligne));
					if(!contient(listParam,"ligne="+ligne)){
						//$("#btnEdit").attr("href",href+"&ligne=");
					}
				}
			});
			$("#btnDelete").click(function(e){
				e.preventDefault();
				var donnees=$("#formTab").serialize();
				var formulaire=donnees;
				donnees+="&btn=deletePub";
				if(formulaire===""){
					$("#alert").dialog({
						modal:false,
						width:150,
						height:100,
						title:'Valider le formulaire!',
						buttons:{
							
							"OK":function(){
								$("#alert").html("Vous n'avez sélectionné aucun élément!!")
								$("#alert").dialog("close");
							}
						}
					});
				}else{
					$("#alert").dialog({
						modal:false,
						width:100,
						height:100,
						title:'Attention!',
						buttons:{
							
							"OK":function(){
								$("#alert").html("La publication sera définitivement supprimée!!")
								$("#alert").dialog("close");
								$("#tbody").replaceWith("<tbody id='tbody'><tr><td colspan='10' align='center'><img src='././ressources/loading-circle.gif'/></td></tr></tbody>");	
								$.ajax({
									url : '/suite_project/publication',
									type : 'GET',
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
							            alert("Status: " + textStatus); alert("Error: " + errorThrown); 
							        } 
								});
							},
							"Annuler":function(){
								$("#alert").dialog("close");
							}
						}
					});
				}
			});
			$("#choix_auteur, #choix_titre,#choix_date").live('blur',function(e){
				e.preventDefault();
				var donnees="";
	
				donnees="auteur="+$("#choix_auteur").val();
				donnees+="&titre="+$("#choix_titre").val();
				donnees+="&date="+$("#choix_date").val();
				donnees+="&btn=filtrerPub";
				$("#tbody").replaceWith("<tbody id='tbody'><tr><td colspan='10' align='center'><img src='././ressources/loading-circle.gif'/></td></tr></tbody>");
				
				$.ajax({
					url : '/suite_project/publication',
					type : 'GET',
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
			            alert("Status: " + textStatus); alert("Error: " + errorThrown); 
			        } 
				});
			});
		});
	</script>
</body>
</html>
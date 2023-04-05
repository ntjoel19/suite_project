<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Gestion des utilisateurs</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css"  href="design/Design_Global.css" />
	<link rel="stylesheet" type="text/css"  href="design/css_programmes.css" />
	<link rel="stylesheet" type="text/css"  href="design/jquery-ui.css" />
	<script src="jquery/jquery.js"></script>
	<script src="jquery/jquery-ui.js"></script>
	<script src="jquery/jquery-validate.js"></script>
	<script src="jquery/jq-valide-unobstrusive.js"></script>
	<script src="jscript/juizDropDownMenu-2.0.0.min.js"></script>
	<script src="jscript/design.js"></script>
<style type="text/css">
	:-moz-placeholder {
	    color: grey;
	}
	 
	::-webkit-input-placeholder {
	    color: grey;
	}
	*:focus {
		outline: none;
	}
	.label {
	    margin-top: 3px;
	    display:inline-block;
	    float:left;
	    padding:3px;
	    font-size: 13px;
	}
	.form_hint, .required_notification {
		font-size: 11px;
	}
	.in{
		border:1px solid #aaa;
	    box-shadow: 0px 0px 3px #ccc, 0 10px 15px #eee inset;
	    border-radius:2px;
	    -moz-transition: padding .25s;
	    -webkit-transition: padding .25s;
	    -o-transition: padding .25s;
	}
	.in:focus{
		 background: #fff;
	    border:1px solid #555;
	    box-shadow: 0 0 3px #aaa; 
	    padding-right:5px;
	}
	.in:required{
		background: #fff url(images/red_asterisk.png) no-repeat 98% center;
	}
	#suivi_projet table{
		border: 1px solid #c0c0c0;
		-webkit-box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset, 0 0 1px #fff inset;
		-moz-box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset;
		-ms-box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset, 0 0 1px #fff inset;
		-o-box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset, 0 0 1px #fff inset;
		box-shadow: 0 0 1px #fff inset, 0 0 1px #fff inset;
	}
	#editRole{
		height: 92%;
	}
</style> 
</head>
<body>
	<% 
		String type = (String)request.getAttribute("save");
		String type2 = (String)request.getAttribute("saveAdd");
		
		String titre = (String)request.getAttribute("titre")==null?"":(String)request.getAttribute("titre");
		String contenu = (String)request.getAttribute("contenu")==null?"":(String)request.getAttribute("contenu");
		String auteur = (String)request.getAttribute("auteur")==null?"":(String)request.getAttribute("auteur");
		String fichier = (String)request.getAttribute("fichier")==null?"":(String)request.getAttribute("fichier");
		String editer = (String)request.getAttribute("Editer")==null?"save":(String)request.getAttribute("Editer");
		String idPub = (String)request.getAttribute("idPub")==null?"":(String)request.getAttribute("idPub");
		
	%>
		<section id="entete">
			<jsp:include page="entete_des_pages.jsp"/>  
			<table id="menu_gestion">
				<% //out.print(type+" blksdfkj "+type2); %>
				<tr><form id="formSavePersonne" method="post" enctype='multipart/form-data' action="/suite_project/upload">
					<td id="menu_gestiontitre"><h2>Gestion des utilisateurs: ajout</h2></td>
					<td class="menu_gestiontitrebtn"><a href="/suite_project/admin"><img class="img" src="ressources/home.png"/></a></td>
					<td class="menu_gestiontitrebtn" align='center'><input title='enregistrer' id="btnSave" class="btnImage" type="submit" src="././ressources/enregistrer.png" name="operation" value="<% out.print(editer); %>"/></td>
					<td class="menu_gestiontitrebtn" align='center'><input title='enregistrer et ajouter' id="btnAddSave" class="btnImageSAVEADD" type="submit" src="././ressources/enregistrerAdd.png" name="operation" value="<% out.print(type2); %>"/></td>
				</tr>
			</table> 
		</section>	
			<div id="suivi_projet" style='background:#f6f6f6;overflow:auto;'>
					<% //ici je mets un inpu type hidden qui garde l'id de la publication a modifier
						out.print(idPub);
					%>
					<table align='center' width='90%' height='100%' style='overflow:auto;'>
						<tr>
							<td width='50%'>
								<table width='100%' height='90%'>
									<tr><td><label for='articleTitle'>Titre</label></td><td><input class='in' placeholder="Titre de l'article" type='text' name='titre' id='articleTitle' value='<% out.print(titre);%>'/></td></tr>
									<tr><td><label for='auteur'>Auteur</label></td><td><input class='in' placeholder="Auteur de la publication" type='text' name='auteur' id='auteurArticle' value='<%out.print(auteur);%>'></td></tr>
									
									<tr><td><label for='contenuArticle'>Contenu</label></td><td><textarea placeholder="Contenu de l'article de l'article" class='in' rows='6' name='description' id='contenuArticle'><%out.print(contenu);%></textarea></td></tr>
									<tr><td><label for='Illustration'>Illustration</label></td><td><input class='in' type='file' name='fichier' id='articleTitle' accept='image/*,.pdf,.doc,.docx,.xls,.xlsx' value='<%out.print(fichier);%>'/></td></tr>
									
								</table>
							</td>
							<td>
							
							</td>
						</tr>
					</table>	
			</div>
			</form>
	<script>
		$(document).ready(function(){
			
			
			$("#btnAddSave").click(function(e){
				e.preventDefault();
				var donnees=$("#formSavePersonne").serialize();
				alert("attribut name = "+$("#btnAddSave").attr("name"));
				donnees+="&operation=enregistrerAdd";
				$.ajax({
					url : '/suite_project/upload',
					type : 'POST',
					data : donnees,
					dataType : 'html',
					success : function(data, result, jqXHR){
						alert("Utilisateur ajoute avec succes!");
						$("#nom").val("");
						$("#prenom").val("");
						$("#tel").val("");
						$("#email").val("");
						$("#pseudo").val("");
						$("#password").val("");
						$("#password+").val("");
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) { 
			            alert("Status: " + textStatus); alert("Error: " + errorThrown); 
			        } 
				});
			});
		});
		
				
	</script>
	
	<!-- Inclusion de footer -->
	<div id="pieds">
		<jsp:include page="pieds_des_pages.jsp"/>
	</div>
</body>
</html>
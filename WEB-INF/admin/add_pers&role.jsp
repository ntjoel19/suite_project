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
			String nom=(String)request.getAttribute("nom");
			String prenom=(String)request.getAttribute("prenom");
			String contacts=(String)request.getAttribute("contacts");
			String email=(String)request.getAttribute("email");
			String pseudo=(String)request.getAttribute("pseudo");
			String type=(String)request.getAttribute("type");
			String type2=(String)request.getAttribute("type2");
			String id=(String)request.getAttribute("id");
			String passw=(String)request.getAttribute("passw");
			String[] vues=new String[35];
			String cacherPassword="";
			if(type.equals("update")){
				cacherPassword="disabled";
			}
			System.out.println(type+" = "+type2);
			String hide="";
			if(type == "update")
				hide="style='display:none;'";
			for(int i=0;i<35;i++)
				vues[i]=(String)request.getAttribute(""+i);
		%>
	<section id="entete">
			<jsp:include page="entete_des_pages.jsp"/>  
			<table id="menu_gestion">
				<tr><form id="formSavePersonne" method="post" action="/suite_project/AjoutPersonne">
					<td id="menu_gestiontitre"><h2>Gestion des utilisateurs: ajout</h2></td>
					<td class="menu_gestiontitrebtn"><a href="/suite_project/admin"><img class="img" src="ressources/home.png"/></a></td>
					<td class="menu_gestiontitrebtn" align='center'><input title='enregistrer' id="btnSave" class="btnImage" type="submit" src="././ressources/enregistrer.png" name="operation" value="<% out.print(type); %>"/></td>
					<td class="menu_gestiontitrebtn" align='center' <% out.print(hide);%>><input title='enregistrer et ajouter' id="btnAddSave" class="btnImageSAVEADD" type="submit" src="././ressources/enregistrerAdd.png" name="operation" value="<% out.print(type2); %>"/></td>
				</tr>
			</table> 
		</section>	
			<div id="suivi_projet" style='background:#f6f6f6;overflow:auto;'>
				<table width='100%' height='100%'>
					<tr><td width='50%'>
						<table height='92%' width='100%' style='border-radius:10px;background:white;'>
							<tr>
								<td class='label'>noms <span class ="oblige">* </span></td> <td><input class="in" name='nom' id="nom"  type="text" placeholder='Nom' value="<% out.print(nom); %>" required/></td>	
							</tr>
							<tr> 
								<td class='label'>prénoms <span class ="oblige">* </span></td> <td><input class="in" name='prenom' id="prenom" type="text" value="<% out.print(prenom); %>"placeholder='Prenom' required/></td> 
							</tr>
							<tr> 
								<td class='label'>Contact <span class ="oblige">* </span></td> <td><input placeholder='Telephone : (Ex: 695905191)' class="in" name='tel' value="<% out.print(contacts); %>" id="tel" type="tel"  required/></td> 
								
							</tr>
							<tr> 
								<td class='label'>Adresse </td> <td><input placeholder='Email: (ntjoel19@email.fr)' class="in" value="<% out.print(email); %>" name='email' id="email" type="mail" required/></td> 
							</tr>
							
							<tr> 
								<td class='label'>Pseudo</td> <td><input placeholder='Pseudo' class="in" value="<% out.print(pseudo); %>" name='pseudo' id="pseudo" type="text" required/></td> 
							</tr>
							
							<tr> 
								<td class='label'>Mot de passe</td> <td><input 
																			placeholder='Password' 
																			class="in" 
																			name='password' 
																			id="password" 
																			type="password" 
																			value="<% out.print(passw); %>"
																			<% out.print(cacherPassword); %>
																			required/>
																	</td> 
							</tr>
							
							<tr> 
								<td class='label'>Confirmer</td> <td><input 
																		placeholder='Repeter password' 
																		class="in" 
																		name='password+' 
																		id="password+" 
																		type="password" 
																		data-val-equalto="Le mot de passe de confirmation ne correspond pas au mot de passe."
																		data-val-equalto-other="password"
																		value="<% out.print(passw); %>"
																		<% out.print(cacherPassword); %>
																		required/>
																	</td> 
							</tr>
							
							<tr align="center"> 
								<td><u><a id='someLink' href="/suite_project/utilisateur?btn=gereruser"><< retour</a></u></td><td></td>	
							</tr>
						</table>
						</td>
						<td>
						<table id='editRole' border='1' style='float:left;background:#fff;margin:1%;border-radius:10px;padding:5px;'>
							<tr align='center'><th colspan='2'>Taches autorisees</th></tr>
							<tr>
								<td colspan='2'>
									<input class='all' type='checkbox' id='all'/><label for='all'>Tout cocher/decocher</label>
									<input type='hidden' name='id' value="<% out.print(id); %>"/>
								</td>
							</tr>
							<tr>
								<td><input class='checkb' type='checkbox' id='axProg' name='axProg' <% out.print(vues[0]); %>/><label for='axProg'>Programmes</label></td>
								<td>
									<input class='checkbaxProg' type='checkbox' id='axProg1' name='axProg1' <% out.print(vues[1]); %>/><label for='axProg1'>Editer</label>
									<input class='checkbaxProg' type='checkbox' id='axProg2' name='axProg2' <% out.print(vues[2]); %>/><label for='axProg2'>Ajouter</label>
									<input class='checkbaxProg' type='checkbox' id='axProg3' name='axProg3' <% out.print(vues[3]); %>/><label for='axProg3'>Supprimer</label>
								</td>
							</tr>
							<tr>
								<td><input class='checkb' type='checkbox' id='axAction' name='axAction' <% out.print(vues[4]); %>/><label for='axAction'>Actions</label></td>
								<td>
									<input class='checkbaxAction' type='checkbox' id='axAction1' name='axAction1' <% out.print(vues[5]); %>/><label for='axAction1'>Editer</label>
									<input class='checkbaxAction' type='checkbox' id='axAction2' name='axAction2' <% out.print(vues[6]); %>/><label for='axAction2'>Ajouter</label>
									<input class='checkbaxAction' type='checkbox' id='axAction3' name='axAction3' <% out.print(vues[7]); %>/><label for='axAction3'>Supprimer</label>
								</td>
							</tr>
							<tr>
								<td><input class='checkb' type='checkbox' id='axProj' name='axProj' <% out.print(vues[8]); %>/><label for='axProj'>Projets</label></td>
								<td>
									<input class='checkbaxProj' type='checkbox' id='axProj1' name='axProj1' <% out.print(vues[9]); %>/><label for='axProj1'>Editer</label>
									<input class='checkbaxProj' type='checkbox' id='axProj2' name='axProj2' <% out.print(vues[10]); %>/><label for='axProj2'>Ajouter</label>
									<input class='checkbaxProj' type='checkbox' id='axProj3' name='axProj3' <% out.print(vues[11]); %>/><label for='axProj3'>Supprimer</label>
									<input class='checkbaxProj' type='checkbox' id='axProj4' name='axProj4' <% out.print(vues[12]); %>/><label for='axProj4'>Generer TDR</label>
									<input class='checkbaxProj' type='checkbox' id='axProj5' name='axProj5' <% out.print(vues[13]); %>/><label for='axProj5'>Generer Fiche Pro</label>
									<input class='checkbaxProj' type='checkbox' id='axProj6' name='axProj6' <% out.print(vues[14]); %>/><label for='axProj6'>Editer etapes</label>
									<input class='checkbaxProj' type='checkbox' id='axProj7' name='axProj7' <% out.print(vues[15]); %>/><label for='axProj7'>Budgetiser</label>
								</td>
							</tr>
							<tr>
								<td><input class='checkb' type='checkbox' id='axPub' name='axPub' <% out.print(vues[19]); %>/><label for='axPub'>Publications</td>
								<td>
									<input class='checkbaxPub' type='checkbox' id='axPub1' name='axPub1' <% out.print(vues[20]); %>/><label for='axPub1'>Editer</label>
									<input class='checkbaxPub' type='checkbox' id='axPub2' name='axPub2' <% out.print(vues[21]); %>/><label for='axPub2'>Ajouter</label>
									<input class='checkbaxPub' type='checkbox' id='axPub3' name='axPub3' <% out.print(vues[22]); %>/><label for='axPub3'>Supprimer</label>
								</td>
							</tr>
							<tr>
								<td><input class='checkb' type='checkbox' id='axUser' name='axUser' <% out.print(vues[23]); %>/><label for='axUser' >Utilisateurs</label></td>
								<td>
									<input class='checkbaxUser' type='checkbox' id='axUser1' name='axUser1' <% out.print(vues[24]); %>/><label for='axUser1'>Editer</label>
									<input class='checkbaxUser' type='checkbox' id='axUser2' name='axUser2' <% out.print(vues[25]); %>/><label for='axUser2'>Ajouter</label>
									<input class='checkbaxUser' type='checkbox' id='axUser3' name='axUser3' <% out.print(vues[26]); %>/><label for='axUser3'>Bloquer</label>
									<input class='checkbaxUser' type='checkbox' id='axUser4' name='axUser4' <% out.print(vues[27]); %>/><label for='axUser4'>Debloquer</label>
									<input class='checkbaxUser' type='checkbox' id='axUser5' name='axUser5' <% out.print(vues[28]); %>/><label for='axUser5'>Supprimer</label>
								</td>
							</tr>
							<tr>
								<td colspan='2'><input class='checkb' type='checkbox' id='axStat' name='axStat' <% out.print(vues[33]); %>/><label for='axStat' >Statistiques</label></td>
							</tr>
							<tr>
								<td><input class='checkb' type='checkbox' id='axMedia' name='axMedia' <% out.print(vues[16]); %>/><label for='axMedia' >Medias</label></td>
								<td>
									<input class='checkbaxMedia' type='checkbox' id='axMedia1' name='axMedia1' <% out.print(vues[17]); %>/><label for='axMedia1'></label>Uploder
									<input class='checkbaxMedia' type='checkbox' id='axMedia2' name='axMedia2' <% out.print(vues[18]); %>/><label for='axMedia2'></label>Supprimer
								</td>
							</tr>
							<tr>
								<td><input class='checkb' type='checkbox' id='axParam' name='axParam' <% out.print(vues[29]); %>/><label for='axParam' >Parametres</label></td>
								<td>
									<input class='checkbaxParam' type='checkbox' id='axParam1' name='axParam1' <% out.print(vues[30]); %>/><label for='axParam1'>Editer</label>
									<input class='checkbaxParam' type='checkbox' id='axParam2' name='axParam2' <% out.print(vues[31]); %>/><label for='axParam2' >Ajouter</label>
									<input class='checkbaxParam' type='checkbox' id='axParam3' name='axParam3' <% out.print(vues[32]); %>/><label for='axParam3' >Supprimer</label>
								</td>
							</tr>
							<tr>
								<td colspan='2'><input class='checkb' type='checkbox' id='axAide' name='axAide' <% out.print(vues[34]); %>/><label for='axAide'>Aide</label></td>
							</tr>
						</table>
						</td>
						</tr>
						</table>
					</form>
				</div>
				<div id='alert' style='display:none;'>
				</di>
	<script>
		$(document).ready(function(){
			
			function checkPasswords(e) {
				var password1 = document.getElementById('password1');
				var password2 = document.getElementById('password2');
				if (password1.value != password2.value) {
					e.preventDefault();
				    password2.setCustomValidity('Les mots de passe ne sont pas identiques');
				} else {
				    password2.setCustomValidity('');
				}
			}
			
			$(".checkb").on('click',function(e){
				//alert(($(this).is(':checked')));
				identifiant = $(this).attr("id");
				if(($(this).is(':checked'))===true){
					$(".checkb"+identifiant).removeAttr("disabled");
					$(".checkb"+identifiant).attr("checked","checked");
				}else{
					$(".checkb"+identifiant).removeAttr("checked");
					$(".checkb"+identifiant).attr("disabled","disabled");
				}
				
			});
			
			$("#btnAddSave").click(function(e){
				e.preventDefault();
				var donnees=$("#formSavePersonne").serialize();
				alert("attribut name = "+$("#btnAddSave").attr("name"));
				donnees+="&operation=saveAdd";
				alert(donnees);
				$.ajax({
					url : '/suite_project/AjoutPersonne',
					type : 'POST',
					data : donnees,
					dataType : 'html',
					success : function(data, result, jqXHR){
						$("#nom").val("");
						$("#prenom").val("");
						$("#tel").val("");
						$("#email").val("");
						$("#pseudo").val("");
						$("#password").val("");
						$("#password+").val("");
						$("#alert").html(data);
						$("#alert").dialog({
							modal:false,
							width:100,
							height:100,
							title: 'Attention!',
							buttons:{
								"OK":function(){
									$("#alert").dialog("close");
								}
							}
						})
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) { 
			            alert("Status: " + textStatus); alert("Error: " + errorThrown); 
			        } 
				});
			});
		});
		
		$(function(){
			$('#formProgram').validate({
				rules: {
				"codePersonne" : {
					"required" : true ,
					"minlength": 5,
					"maxlength": 60000
				},
				"email": {
					"required" : true ,
					"regex": /([^.@]+)(\.[^.@]+)*@([^.@]+\.)+([^.@]+)$/ ,
					"minlength": 10
				},
					"nom" : {
					"required" : true ,
					"minlength": 5,
					"maxlength": 600
				},
					"contact" : {
					"required" : true ,
					"regex": /^(2376)[57938][0-9]{7}$/}						
				}
			});
			
			jQuery.extend(jQuery.validator.messages,{
				required  : "<span style='color=#d11; font-weight:bold; font-size:0.85em;'>Ce champ est requis !</span>" ,
				minlength : "<span style='color=#d11; font-weight:bold; font-size:0.85em;'> Entrer au moins 5 characters svp</span>" ,
				maxlength : "<span style='color=#d11; font-weight:bold; font-size:0.85em;'> Dépassement de capacité </span>"
			});
				
			jQuery.validator.addMethod(
				  "regex",
				   function(value, element, regexp) {
				   if (regexp.constructor != RegExp)
					  regexp = new RegExp(regexp);
			     	   else if (regexp.global)
							  regexp.lastIndex = 0;
							  return this.optional(element) || regexp.test(value);
					   },"<span style='color:#d11; font-weight:bold; font-size:0.85em;'>Format incorrect !!!</span>"
			);
		});
				
	</script>
	
	<!-- Inclusion de footer -->
	<div id="pieds">
		<jsp:include page="pieds_des_pages.jsp"/>
	</div>
</body>
</html>
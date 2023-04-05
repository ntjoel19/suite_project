<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
    	<link rel="stylesheet" type="text/css"  href="./design/jquery-ui.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Ministere des transports</title>
        <link href="./design/indexDesign.css" type="text/css" rel="stylesheet"/>
        <link href="./design/style_formulaire.css" rel="stylesheet" type="text/css" />
        <script src="./jquery/jquery.js"></script>
		<script src="./jquery/jquery-ui.js"></script>
		<script src="./jquery/jquery-validate.js"></script>
		<script src="./jscript/design.js"></script>
    	<style type="text/css">
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
			.in option{
				background-color: rgba(255, 255, 255, 0.5);
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
			.inAddPers{
				border-radius:5px;
				border:1px solid grey;
				width:100%;
				height:90%;
			}
    	</style>
    </head>	
    <body>
    	<!-- Entete du user -->
        <div id="entete">
        	<div id="banniere">
				<img width="100%" height="100%" src="././ressources/suite_project_barn_adm.png" />
				<!--  div id="organisme">
					<p >MINISTERE DES TRANSPORTS</p>
				</div-->
			</div>
        </div>
		<div id="corps_index">
			<table border="0"  id="bigTab">	
				<%String erreur=(String)request.getAttribute("erreurPassw"); %>
                <form  method="post" action="/suite_project/user">
                	<tr style="height : 1em;"><th colspan="2"><% out.print(erreur); %></th></tr>
            		<tr>
            			<td width="50%" id='mainPub'>
            				<%--@ include file="slide-definition.source.jsp"--%>
            				<!--  img width="100%" src="././ressources/accueilMsg.png"/>-->
            			</td>
            			<td>
            				<table id="smallTab" height="100%" width="100%" >
            					<tr align="center">
			                        <th id="conexionForm">Connexion</th>
			                    </tr>
			                    
			                    <tr>
			                        <td><label>Login</label></br><input class="input" type="text" name="login"/></td>
			                    </tr>
			                    <tr>
			                        <td><label>Mot de passe</label></br><input class="input" type="password" name="password"/></td>
			                    </tr>
			                    <tr>
			                        <td>
			                            <input class="in" type="submit" name="connexion" value="Connexion" id="valider"/>
									</td>
			                    </tr>
			                    <tr>
				                    <td><a id='changePassLink' style='text-decoration:none;' href="">Changer de mots de passe</a>
				                    <a id='creerCompte' style='display:inline-block; float: right; text-decoration:none;'  href="">Je n'ai pas de compte</a></td>
			                    </tr>
			            	</table>
			            	</form>
			            	
			            	<table style='display:none;' id="formPassChange" height="100%" width="100%" >
            					<form id='changePass' >
            						<tr align="center">
				                        <th colspan='2' id="conexionForm">Changer mot de passe</th>
				                    </tr>
				                    
				                    <tr>
				                        <td><label>Login</label></td><td><input placeholder='Login' id='login' class="input" type="text" name="" required/></td>
				                    </tr>
				                    
				                    <tr>
				                        <td><label>Actuel</label></td><td><input placeholder='Mot de passe actuel' class="input" id="password_A" type="password" name="password" required/></td>
				                    </tr>
				                    <tr>
				                        <td><label>Nouveau</label></td><td><input placeholder='Nouveau mot de passe' class="input" id="password1" type="password" name="password" required/></td>
				                    </tr>
				                    <tr>
				                        <td><label>Repeter</label></td><td><input placeholder='Retapper nouveau mot de passe' class="input" id="password2" type="password" name="password" required/></td>
				                    </tr>
				                    <tr>
				                        <td>
				                            <input class="in" type="submit" name="connexion" value="Changer" id="changer"/>
										</td>
										<td>
				                            <input class="in" type="reset" name="connexion" value="Annuler" id="annulerChange"/>
										</td>
				                    </tr>
            					</form>
            				</table>
			            	
			            	<table style='display:none;' id="formCompte" height="100%" width="100%" >
            					<form id='enregistrerPerson'>
            						<tr>
										<td>
											<input class='inAddPers' name='nom' id="nom"  type="text" placeholder='Nom' required/>
										</td>
										<td>
											<input class='inAddPers' name='prenom' id="prenom" type="text" placeholder='Prenom' required/>
										</td>
									</tr>
									
									<tr>
										<td>
											<input class='inAddPers' placeholder='Email: (ntjoel19@email.fr)' name='email' id="email" type="tel" required/>
										</td>
										<td>
											<input class='inAddPers' placeholder='Telephone : (Ex: 695905191)' name='tel' id="tel" type="tel"  required/>
										</td>
									</tr>
									
									<tr>
										<td colspan='2'>
											<input class='inAddPers' placeholder='Pseudo' name='pseudo' id="pseudo" type="text" required/></br>
										</td>
									</tr>
									
									<tr>
										<td>
											<input class='inAddPers' placeholder='Password' name='password' id="password" type="password" required/>
										</td>
										<td>
											<input class='inAddPers' placeholder='Repeter password' name='password+' id="password+" type="password" required/>
										</td>
									</tr>
									
									<tr>
										<td colspan='2'>
											<span id="information" style='color:red;align:center;display:inline-block;'>Apres creation de votre compte, vous ne pourez vous connecter qu'apres attribution d'un
											role par l'administrateur</span>
										</td>
									</tr>
									
									<tr>
										<td> 
											<input  align="center" type="submit" value='Enregistrer' name="Enregistrer" class="btn" id='enregistrer' /> 
										</td>
										<td> 
											<input style="float:left" align="center" type="reset" value='Annuler' name="Annuler" class="btn" id='cancel' /> 
										</td>
									</tr>
            					</form>
			            	</table>
			            </td>
			        </tr>          
            </table> 	
		</div>
		<script type="text/javascript">
				$('#dialog').dialog({
					title : "connexion",
					modal : false ,
					width : 440 ,
					resizable : false 
				});
				activeForm('#creerCompte','#formCompte','#enregistrer','#cancel','#close');
				activeForm2('#changePassLink','#formPassChange','#changer','#annulerChange','#close');
				function activeForm(btn,formulaire,btnValider,btnAnnuler,close){
					$(btn).mousedown(function(){
						$('#smallTab').hide();
						$(formulaire).fadeIn();
					});
					$(btnValider).mousedown(function(e){
						e.preventDefault();
						var donnees=$("#enregistrerPerson").serialize();
						donnees+="&action=enregistrerPers";
						donnees+="&role=vide";
						//alert(donnees);
						$.ajax({
							url : '/suite_project/user',
							type : 'get',
							dataType : 'html',
							data : donnees,
							success : function(data, result, jqXHR){
								//$(codeHtml).appendTo('#tbody');
								//alert("le retour de la servlet: "+data);
								$('#smallTab').fadeIn();
								$('#formCompte').hide();
							},
							error: function(XMLHttpRequest, textStatus, errorThrown) { 
								$("#information").text(textStatus);
					        } 
						});
						$(formulaire).fadeOut();
					});
					$(btnAnnuler).mousedown(function(e){
						e.preventDefault();
						$(formulaire).hide();
						$('#smallTab').fadeIn();
					});
					$(close).mousedown(function(){
						$(formulaire).fadeOut();
					});
				};
				
				function activeForm2(btn,formulaire,btnValider,btnAnnuler,close){
					$(btn).mousedown(function(){
						$('#smallTab').hide();
						$(formulaire).fadeIn();
					});
					$("#changer").on('click',function(e){
						e.preventDefault();
						var donnees="pseudo="+$("#login").val();
						donnees+="&password="+$("#password1").val();
						donnees+="&password_A="+$("#password_A").val();
						donnees+="&action=modifierPass";
						//alert(donnees);
						var pass2 = $("#password2").val();
						if((pass2 == $("#password1").val()) && (pass2 != "")){
							$.ajax({
								url : '/suite_project/user',
								type : 'get',
								dataType : 'html',
								data : donnees,
								success : function(result){
									alert(result);
									$('#smallTab').fadeIn();
									$(formulaire).fadeOut();
									$('#formCompte').hide();
								},
								error: function(XMLHttpRequest, textStatus, errorThrown) { 
						           alert("Login ou mot de passe incorrect!"); 
						        } 
								
							});
							
						}else{
							$("#conexionForm").replaceWith("Erreur de confirmation!!!");
							$("#conexionForm").css("background","red");
							$("#conexionForm").css("color","white");
						}
					});
					
					$(btnAnnuler).mousedown(function(e){
						e.preventDefault();
						$(formulaire).hide();
						$('#smallTab').fadeIn();
					});
					$(close).mousedown(function(){
						$(formulaire).fadeOut();
					});
				};
				
		</script>
		<!-- Pieds de page -->
        <div id="pieds_index">
			<P> MINISTERE DES TRANSPORTS - Tous droits réservés © 2015</br>
	mint@mint.gov.cm  </P>
		</div>
    </body>
</html>
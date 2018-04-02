<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width">

        <link rel="stylesheet" href="css/fontawesome-all.min.css">
        <link rel="stylesheet" href="css/bootstrap.css">
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <script src="js/jquery-latest.min.js" type="text/javascript"></script>
        <script src="js/popper.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/myapp-functions.js" type="text/javascript"></script>

        <title>${compte.getPrenom()} ${compte.getNom()}</title>

        <link rel="stylesheet" type="text/css" href="css/style.css">
    </head>
    <body>
        <!-- debut menu haut de la page ***************************** -->
        <nav class="navbar navbar-expand-md bg-secondary navbar-dark fixed-top">
            <div class="container">
                <div class="navbar-brand">${compte.getPrenom()} ${compte.getNom()}</div>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"> <span class="navbar-toggler-icon"></span> </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="/">Accueil</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Guide</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">À propos</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Contactez-nous</a>
                        </li>
                    </ul>
                    <a class="btn btn-primary" href="ctrl?operation=deconnecter"><span class="fa fa-sign-out-alt"></span>Déconnexion</a>

                </div>
            </div>
        </nav>

        <!-- debut menu gauche (milieu) de la page ***************************** -->  
        <div class="row" id="page">  
            <div class="col-md-2 mx-1 my-1 pb-2" id="menug">
                <div class="card text-center justify-content-md-center">
                    <div class="card-header">
                        <img src="files/${compte.getPhotoDeProfil()}" class="rounded-circle" height="50" width="50" alt="Avatar">
                    </div>
                    <div class="card-footer">
                        <button data-toggle="modal" data-target="#modifModal">
                            Modifier votre photo de profil
                        </button>
                        <button data-toggle="modal" data-target="#supprModal">
                            Supprimer votre compte
                        </button>
                    </div>
                </div>
            </div>


            <!-- debut corps (milieu) de la page ***************************** -->
            <div class="offset-md-2 col-md-8" id="corps">
                <div class="modal fade text-left" id="modifModal">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title">Modifier votre photo de profil</h4>
                                <button class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <form class="modal-header"  action="ctrl" method="post">
                                <input class="btn btn-block btn-primary" type="submit" name="SupprimerPP" value="Supprimer la photo de profil">
                            </form>
                            <form id="PhotoDeProfilForm" class="modal-body row"  action="ctrl" method="post" enctype="multipart/form-data">
                                <label class="btn btn-primary col-7 m-2 p-2 text-center" for="fichier">
                                    <input id="fichier" type="file" required hidden name="fichier" accept="image/*"
                                           onchange="$('#upload-file-info').html('Parcourir');$('#upload-file-info').html(this.files[0].name);">
                                    <span id="upload-file-info">Parcourir</span>
                                </label>
                                <label class="btn btn-primary col-3 m-2 p-2" onclick="ChangerPhotoDeProfil()">Accepter</label>
                            </form>
                        </div>
                    </div>
                </div>
                <form class="modal fade text-left" id="supprModal" action="ctrl" method="get">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Êtes-vous sûr de vouloir supprimer définitivement votre compte</h5>
                                <button class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="modal-body row">
                                <input class="btn btn-primary p-2 m-2 col-5" type="submit" value="oui">
                                <button class="btn btn-primary p-2 m-2 col-5" data-dismiss="modal">non</button>
                            </div>
                            <input type="hidden" name="operation" value="supprimerMonCompte">
                        </div>
                    </div>
                </form>

                <h1>Vos Publications</h1><hr>
            </div>

        </div>
        <script>
            if (!!window.performance && window.performance.navigation.type === 2)
            {
                window.location.reload();
            }
            $.get("ctrl?operation=initialiserVosPublications", function () {
                var d1 = document.createElement('div'), d2 = document.createElement('div');
                $(d1).load("home_1.jsp #publications", function () {
                    $("#corps").append($(d1).children());
                });
                $(d2).load("home.jsp #menud", function () {
                    $("#page").append($(d2).children());
                });
            });
        </script>
    </body>
</html>
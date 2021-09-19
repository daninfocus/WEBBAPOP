<%--
  Created by IntelliJ IDEA.
  User: Dan
  Date: 16/08/2021
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String userEmail = (String) session.getAttribute("loggedInUser");
    if (userEmail == null) {
        response.sendRedirect("/Login");
        return; //the return is important; forces redirect to go now
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <link
            rel="shortcut icon"
            type="img/favicon.ico"
            href="./resources/img/logo512circle.png"
    />
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href="./resources/FontAwesome/css/all.css" rel="stylesheet"/>
    <!--load all styles -->
    <link rel="stylesheet" href="./resources/css/user.css"/>
    <link rel="manifest" href="manifest.json"/>
    <meta name="theme-color" content="#30ab53"/>
    <link rel="apple-touch-icon" href="./resources/img/logo192.png"/>
    <script type="application/javascript">

        function load() {
            let option = "<%=request.getParameter("Option")%>";
            let message = "<%=request.getParameter("Product_ID")%>";


            console.log(option);
            console.log(message);
            if (message == "null") {
                document.querySelector('.my-profile-page').style.display = 'none';
                document.querySelector('.my-product-page').style.display = 'none';
                document.querySelector('.my-messages-page').style.display = 'none';
                document.querySelector('.my-favorites-page').style.display = 'none';
                document.querySelector('.my-reviews-page').style.display = 'none';
                document.querySelector('.my-reviews-page').style.display = 'none';
                if (option.localeCompare("profile") == 0) {
                    console.log("profile");
                    myProfile();
                } else {
                    if (option.localeCompare("products") == 0) {
                        console.log("products");
                        myProducts();
                    } else {
                        if (option.localeCompare("messages") == 0) {
                            console.log("messages");
                            myMessages();
                        } else {
                            if (option.localeCompare("zone") == 0) {
                                myZone();
                            } else {
                                if (option.localeCompare("reviews") == 0) {
                                    myReviews()
                                } else {
                                    myProducts();
                                }
                            }
                        }
                    }
                }
            } else {
                document.querySelector('.my-profile-page').style.display = 'none';
                document.querySelector('.my-product-page').style.display = 'none';
                document.querySelector('.my-messages-page').style.display = 'contents';
                document.querySelector('.my-favorites-page').style.display = 'none';
                document.querySelector('.my-reviews-page').style.display = 'none';

            }
        }

        function myProducts() {
            let products = document.querySelector('.my-product-page').style.display;
            let profile = document.querySelector('.my-profile-page').style.display;

            if (products.localeCompare("none") == 0) {
                document.querySelector('.my-product-page').style.display = 'contents';
                document.querySelector('.products').style.color = 'white';
                document.querySelector('.my-profile-page').style.display = 'none';
                document.querySelector('.profile').style.color = '#818181';
                document.querySelector('.my-messages-page').style.display = 'none';
                document.querySelector('.messages').style.color = '#818181';
                document.querySelector('.zone').style.color = '#818181';
                document.querySelector('.messagesBottomNav').style.color = '#818181';
                document.querySelector('.favouritesBottomNav').style.color = '#818181';
                document.querySelector('.my-reviews-page').style.display = 'none';
                document.querySelector('.reviews').style.color = '#818181';
                document.querySelector('.my-favorites-page').style.display = 'none';
                document.querySelector('.favourites').style.color = '#818181';
            }

        }

        function myProfile() {
            let profile = document.querySelector('.my-profile-page').style.display;
            let products = document.querySelector('.my-product-page').style.display;

            if (profile.localeCompare("none") == 0) {
                document.querySelector('.my-profile-page').style.display = 'contents';
                document.querySelector('.profile').style.color = 'white';
                document.querySelector('.my-product-page').style.display = 'none';
                document.querySelector('.products').style.color = '#818181';
                document.querySelector('.my-messages-page').style.display = 'none';
                document.querySelector('.messages').style.color = '#818181';
                document.querySelector('.zone').style.color = '#818181';
                document.querySelector('.messagesBottomNav').style.color = '#818181';
                document.querySelector('.favouritesBottomNav').style.color = '#818181';
                document.querySelector('.my-reviews-page').style.display = 'none';
                document.querySelector('.reviews').style.color = '#818181';
                document.querySelector('.my-favorites-page').style.display = 'none';
                document.querySelector('.favourites').style.color = '#818181';

            }

        }


        function myMessages() {
            let messages = document.querySelector('.my-messages-page').style.display;


            if (messages.localeCompare("none") == 0) {
                document.querySelector('.my-messages-page').style.display = 'contents';
                document.querySelector('.messages').style.color = 'white';
                document.querySelector('.messagesBottomNav').style.color = 'white';
                document.querySelector('.favouritesBottomNav').style.color = '#818181';
                document.querySelector('.my-product-page').style.display = 'none';
                document.querySelector('.products').style.color = '#818181';
                document.querySelector('.my-profile-page').style.display = 'none';
                document.querySelector('.profile').style.color = '#818181';
                document.querySelector('.zone').style.color = '#818181';
                document.querySelector('.my-reviews-page').style.display = 'none';
                document.querySelector('.reviews').style.color = '#818181';
                document.querySelector('.my-favorites-page').style.display = 'none';
                document.querySelector('.favourites').style.color = '#818181';

            }

        }


        function myZone() {
            let profile = document.querySelector('.my-profile-page').style.display;
            let products = document.querySelector('.my-product-page').style.display;

            if (profile.localeCompare("none") == 0 && products.localeCompare("none") == 0) {
                document.querySelector('.my-product-page').style.display = 'contents';
                document.querySelector('.my-profile-page').style.display = 'contents';
                document.querySelector('.zone').style.color = 'white';
                document.querySelector('.my-messages-page').style.display = 'none';
                document.querySelector('.messages').style.color = '#818181';
                document.querySelector('.messagesBottomNav').style.color = '#818181';
                document.querySelector('.favouritesBottomNav').style.color = '#818181';
                document.querySelector('.my-reviews-page').style.display = 'none';
                document.querySelector('.reviews').style.color = '#818181';
                document.querySelector('.my-favorites-page').style.display = 'none';
                document.querySelector('.favourites').style.color = '#818181';
            }


        }

        function myReviews() {
            let reviews = document.querySelector('.my-reviews-page').style.display;


            if (reviews.localeCompare("none") == 0) {
                document.querySelector('.my-reviews-page').style.display = 'contents';
                document.querySelector('.reviews').style.color = 'white';


                document.querySelector('.my-product-page').style.display = 'none';
                document.querySelector('.products').style.color = '#818181';
                document.querySelector('.my-profile-page').style.display = 'none';
                document.querySelector('.profile').style.color = '#818181';
                document.querySelector('.zone').style.color = '#818181';
                document.querySelector('.my-messages-page').style.display = 'none';
                document.querySelector('.messages').style.color = '#818181';
                document.querySelector('.my-favorites-page').style.display = 'none';
                document.querySelector('.favourites').style.color = '#818181';
                document.querySelector('.favouritesBottomNav').style.color = '#818181';
                document.querySelector('.messagesBottomNav').style.color = '#818181';

            }
        }

        function myFavs() {
            let favs = document.querySelector('.my-favorites-page').style.display;


            if (favs.localeCompare("none") == 0) {
                document.querySelector('.my-favorites-page').style.display = 'contents';
                document.querySelector('.favourites').style.color = 'white';
                document.querySelector('.favouritesBottomNav').style.color = 'white';
                document.querySelector('.my-reviews-page').style.display = 'none';
                document.querySelector('.reviews').style.color = '#818181';
                document.querySelector('.my-product-page').style.display = 'none';
                document.querySelector('.products').style.color = '#818181';
                document.querySelector('.my-profile-page').style.display = 'none';
                document.querySelector('.profile').style.color = '#818181';
                document.querySelector('.zone').style.color = '#818181';
                document.querySelector('.my-messages-page').style.display = 'none';
                document.querySelector('.messages').style.color = '#818181';
                document.querySelector('.messagesBottomNav').style.color = '#818181';


            }
        }
    </script>

    <title>Webbapop</title>
</head>
<body onload="load()">
<div class="header">
    <!-----------------------------------------------------------------------------------------------------------------SearchBAR -->
    <a href="/Home" class="logo"><i class="fab fa-weebly"></i>ebbaPop</a
    ><!--Webbapop logo -->

    <form action="/Search" method="get">
        <div class="wrap">
            <!-----------------------------------------------------------SearchBAR -->
            <div class="search">

                <input type="text" class="searchTerm" name="search" placeholder=" Search"/>
                <button type="submit" class="searchButton">
                    <i class="fa fa-search"></i>
                </button>

            </div>
        </div>
    </form>
    <div class="header-right">
        <!-----------------------------------------Header right options -->

        <%

            if (request.getSession().getAttribute("loggedInUser") == null) {

            } else {
                out.print("<a class=\"option2\" href=\"/Profile\"><i  class=\"far fa-user fa-lg\"></i>&nbsp;&nbsp;" + request.getSession().getAttribute("name") + "</a>");
                out.print("<a class=\"option2\" href=\"/Profile?newProduct=true\"><i class=\"fa fa-plus-square\" ></i><div class=\"addProduct\">&nbsp;&nbsp;Subir un producto</div></a>");

                session.setAttribute("user", request.getAttribute("name"));      //set attribute in session
                session.setAttribute("email", request.getAttribute("email"));
            }
        %>
        <!--<a href="hello-servlet">Hello Servlet</a>-->
    </div>
</div>
<!-------------------------------------------------------------------------------------------------------------------------------SearchBAR -->

<div class="container">

    <% if (request.getParameter("newProduct") != null && request.getParameter("newProduct").equals("true")) {%>

        <jsp:include page="new_product.jsp"/>

    <%} else {
        if (request.getParameter("editProduct") != null){
            String prod_id = request.getParameter("editProduct");%>
            <jsp:include page="edit_product.jsp"/>
        <%}else{%>

    <div class="my-profile-page">
        <jsp:include page="my_profile.jsp"/>
    </div>
    <div class="my-product-page">
        <jsp:include page="my_products.jsp"/>
    </div>
    <div class="my-messages-page">
        <jsp:include page="my_messages.jsp"/>
    </div>
    <div class="my-favorites-page">
        <jsp:include page="saved_products.jsp"/>
    </div>
    <div class="my-reviews-page">
        <jsp:include page="my_reviews.jsp"/>
    </div>
    <% }} %>
</div>
<div class="sidenav">
    <a class="profile" <%
        if (request.getParameter("newProduct") != null) {
            out.print("href=\"/Profile?Option=profile\"");
        }
    %> onClick="myProfile()"><i style="font-size: 30px; padding: 10px" class="fa fa-user"
                                aria-hidden="true"></i>Perfil</a>

    <a class="products"  <%
        if (request.getParameter("newProduct") != null) {
            out.print("href=\"/Profile?Option=products\"");
        }
    %> onClick="myProducts()"><i style="font-size: 30px; padding: 10px" class="fa fa-list"
                                 aria-hidden="true"></i>Productos</a>

    <a class="messages"  <%
        if (request.getParameter("newProduct") != null) {
            out.print("href=\"/Profile?Option=messages\"");
        }
    %> onClick="myMessages()"><i style="font-size: 30px; padding: 10px" class="fa fa-comment" aria-hidden="true"></i>Mensajes</a>


    <a class="favourites"  <%
        if (request.getParameter("newProduct") != null) {
            out.print("href=\"/Profile?Option=favourites\"");
        }
    %> onClick="myFavs()"><i style="font-size: 30px; padding: 10px" class="fa fa-heart" aria-hidden="true"></i>Favoritos</a>


    <a class="reviews"  <%
        if (request.getParameter("newProduct") != null) {
            out.print("href=\"/Profile?Option=reviews\"");
        }
    %> onClick="myReviews()"><i style="font-size: 30px; padding: 10px" class="fa fa-star" aria-hidden="true"></i>Opiniones</a>

</div>

<div class="bottomnav">
    <a class="products" href="/Home"><i class="fa fa-list" aria-hidden="true"></i>
        <p>Inicio</p></a>


    <a class="favouritesBottomNav" <%
        if (request.getParameter("newProduct") != null) {
            out.print("href=\"/Profile?Option=favourites\"");
        }
    %> onClick="myFavs()"><i class="fa fa-heart" aria-hidden="true"></i><p>Favoritos</p></a>


    <a href="/Profile?newProduct=true"><i class="fa fa-plus-square"></i></a>


    <a class="messagesBottomNav"  <%
        if (request.getParameter("newProduct") != null) {
            out.print("href=\"/Profile?Option=messages\"");
        }
    %> onClick="myMessages()"><i class="fa fa-comment" aria-hidden="true"></i><p>Mensajes</p></a>


    <a class="zone"  <%
        if (request.getParameter("newProduct") != null) {
            out.print("href=\"/Profile?Option=zone\"");
        }
    %> onClick="myZone()"><i class="fa fa-user" aria-hidden="true"></i>
        <p>Tu zona</p></a>


</div>
</body>
</html>
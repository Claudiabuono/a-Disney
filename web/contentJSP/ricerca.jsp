
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        #barra {
            width: 130px;
            height: 25px;
            box-sizing: border-box;
            border: 2px solid #ccc;
            border-radius: 4px;
            font-size: 16px;
            background-color: white;
            background-position: 10px 10px;
            background-repeat: no-repeat;
            padding: 12px 20px 12px 40px;
            -webkit-transition: width 0.4s ease-in-out;
            transition: width 0.4s ease-in-out;
        }

        #barra:focus {
            width: 100%;
        }
    </style>
</head>
<body>
<div id="barra" class="form-group">
<form class="navbar-form navbar-left" action="catalogo">
    <input  type="text" name="search" placeholder="Search..">
</form>
</div>

</body>
</html>



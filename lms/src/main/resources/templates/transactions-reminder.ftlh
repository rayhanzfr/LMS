<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Pass Email</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>

    <!-- use the font -->
    <style>
        body {
        	background-color: #eaeaea;
            font-family: 'Roboto', sans-serif;
            font-size: 48px;
        }
        
    </style>
</head>
<body style="margin: 0; padding: 0;">
	<div style="width: 100%; background-color: #eaeaea; ">
	<div border="1px solid black" >
	<table bgcolor="#eaeaea" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;border-collapse:separate">
        <tbody>
            <tr>
                <td height="20" class="m_-4195446629692241502smallspacer" style="border-collapse:separate;table-layout:fixed;font-size:1px;line-height:1px">&nbsp;</td>
            </tr>
        </tbody>
    </table>
    <table align="center" cellpadding="0" cellspacing="0" style="border-collapse: collapse;padding= 40px;"; bgcolor="#FFFFFF">
        <tr>
            
            <td align="center" style="padding: 40px 0 20px 0;">
                <h1 style="color:#FA840F">PT. Lawencon International</p>
                <h2 style="color:#0094FF">Lawencon-Assets Management System</p>
            </td>
        </tr>
        <tr>
            <td  style="padding: 10px 30px 40px 30px;">
                <p>Hello ${to},<br><br>
                	
                	Sorry to inform, Your assets will expire on <b>${expired}</b>.
					Here's detail of the assets<br>
					Items Name: <b>${items}</b><br>
					Assets Name: <b>${assets}</b><br>
					Expired Date: <b>${expired}</b><br>
					
					Thank you for your trust to be a customer of Lawencon-Assets Management System. 
					
					<br><br>
					Lawencon-Assets Management System is a Assets Management System managed by PT. Lawencon International
					<br><br><br>
					Thankyou very much for the attention.
					<br><br>
					Sincerely
				</p>
            </td>
        </tr>
        
    </table>
    <table bgcolor="#eaeaea" border="0" cellspacing="0" cellpadding="0" style="table-layout:fixed;border-collapse:separate">
        <tbody>
            <tr>
                <td height="20" class="m_-4195446629692241502smallspacer" style="border-collapse:separate;table-layout:fixed;font-size:1px;line-height:1px">&nbsp;</td>
            </tr>
        </tbody>
    </table>
	</div>
     </div>

</body>
</html>
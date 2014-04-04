<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main" />
    <title>Generate BarCode branch 1</title>
</head>
<body>

<div class="body">
    <h1>Generate BarCode</h1>
    <g:form action="generateBarCode" method="post" >
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="name">Patient Name:</label>
                    </td>
                    <td valign="top">
                        <input type="text" id="patientName" name="patientName" />
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="feedback">Patient ID:</label>
                    </td>
                    <td valign="top">
                        <input type="text" id="patientId" name="patientId" />
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="buttons">
            <span class="button"><input class="save" type="submit" value="Generate" /></span>
        </div>
    </g:form>
</div>
</body>
</html> 
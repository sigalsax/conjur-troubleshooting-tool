<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
</head>

<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script type="text/javascript">
    function getAjax() {
        var formData = {
            "containerId": $("#containerId").val(),
            "query": $("#query").val()
        }
        $.ajax({
            type: "GET",
            url: "subView",
            contentType: "application/json",
            data: formData,
            dataType: 'json',
            success: function(response) {
                response["logList"].forEach(function(pair) {
                    var cleanJson = pair["origin"] + " " + pair["requestId"] + " "
                        + pair ["threadId"] + " " + pair["message"]
                    var li = document.createElement("li");
                    var text = document.createTextNode(cleanJson);
                    li.appendChild(text);
                    document.getElementById("subViewDiv").appendChild(li);
                 })
            },
            error: function (xhr) {
                console.log(xhr)
                $("#subViewDiv").text(xhr.statusText);
            }
        });
    }
</script>

    <body>
        <form id="authnForm">
            <label for="containerId">Container ID:</label>
            <input "containerId" id="containerId" name="containerId" type="text" />

            <label for="query">Query</label>
            <input "query" id="query" name="query" type="text" />

            <input type="button" value="Submit"  onclick="getAjax();"/>
            <div id="subViewDiv"></div>
        </form>
    </body>
</html>
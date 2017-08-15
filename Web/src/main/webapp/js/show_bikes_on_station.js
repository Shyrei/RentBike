$(document).ready(function () {
    /*$('#bttTest').click(function () {*/
    $('#station').change(function () {
        var station = $('#station').val();
        $.ajax({
            type: 'POST',
            data: {station: station},
            url: '/Ajax',
            success: function (result) {
                var bikeList = $.parseJSON(result);
                var s = '';
                for (var i = 0; i < bikeList.length; i++) {
                    s += '<tr>' + '<td>' + bikeList[i].id + '</td>' + '<td>' + bikeList[i].type + '</td>' + '<td>' + bikeList[i].pricePerHour + '</td>' + '<td>' + bikeList[i].city + '</td>' + '<td>' + bikeList[i].location + '</td>' + '<td>' + '<img src="data:image/jpeg;base64,' + bikeList[i].picture + '"/>' + '</td>' + '</tr>';

                }
                s = '<table class="MyTableMain"><thead><tr><td><fmt:message key="table.number"/></td><td><fmt:message key="table.rent.type"/></td><td><fmt:message key="table.rent.price"/></td><td><fmt:message key="table.rent.city"/></td><td><fmt:message key="table.rent.location"/></td><td><fmt:message key="table.rent.photo"/></td></tr></thead>' + s;
                document.getElementById('result').innerHTML = s;
            }
        });
    });
});

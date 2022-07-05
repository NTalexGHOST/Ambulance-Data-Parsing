google.charts.load('current', { 'packages': ['corechart'] });
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Topping');
    data.addColumn('number', 'Slices');
    data.addRows([[${ values }]]);

    var options = {
        title: 'Ambulance calls frequency',
        curveType: 'function',
        legend: { position: 'bottom' },
        backgroundColor: "#F2FFF2"
    };

    var chart = new google.visualization.LineChart(document.getElementById('chart_div'));

    chart.draw(data, options);
}
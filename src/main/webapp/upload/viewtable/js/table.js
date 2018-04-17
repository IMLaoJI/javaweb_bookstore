/*
 * @Author: LONG
 * @Date:   2017-11-25 08:51:17
 * @Last Modified by:   LONG
 * @Last Modified time: 2017-11-25 08:51:26
 */

$(function () {
    // Create the chart
    Highcharts.chart('container', {
        chart: {
            type: 'column'
        },
        title: {
            text: '2016年，某业务员业绩情况'
        },
        subtitle: {
            text: '默认显示平均数,点击各列进入详细'
        },
        xAxis: {
            type: 'category'
        },
        yAxis: {
            title: {
                text: '金额'
            }
        },
        legend: {
            enabled: false
        },
        plotOptions: {
            series: {
                borderWidth: 0,
                dataLabels: {
                    enabled: true
                }
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
            pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b><br/>'
        },
        series: [{
            name: '各类金额',
            colorByPoint: true,
            data: [{
                name: '达成情况',
                y: average1,
                drilldown: '达成情况'
            }, {
                name: '返利所得',
                y: average2,
                drilldown: '返利所得'
            }, {
                name: '返利金额',
                y: average3,
                drilldown: '返利金额'
            }]
        }],
        drilldown: {
            series: [{
                name: '达成情况',
                id: '达成情况',
                data: [
                    [
                        '中位数',
                        mid1
                    ],
                    [
                        '众数',
                        mode1
                    ],
                    
                    [
                        '标准差',
                        biaozuncha1
                    ]
                ]
            }, {
                name: '返利所得',
                id: '返利所得',
                data: [
                    [
                        '中位数',
                        mid2
                    ],
                    [
                        '众数',
                        mode2
                    ],

                    [
                        '标准差',
                        biaozuncha2
                    ]
                ]
            }, {
                name: '返利金额',
                id: '返利金额',
                data: [
                    [
                        '中位数',
                        mid3
                    ],
                    [
                        '众数',
                        mode3
                    ],

                    [
                        '标准差',
                        biaozuncha3
                    ]
                ]
            }]
        }
    });
});

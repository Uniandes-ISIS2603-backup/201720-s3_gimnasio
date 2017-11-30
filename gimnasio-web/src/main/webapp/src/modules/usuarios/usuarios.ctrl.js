var tip;
var med;
(function (ng) {
    var mod = ng.module("usuarioModule");
    mod.constant("usuarioContext", "api/usuarios");

    mod.controller('usuariosDetailCtrl', ['$scope', '$http', 'usuarioContext', '$state',
        function ($scope, $http, usuarioContext, $state) {
            if (($state.params.Uid !== undefined) && ($state.params.Uid !== null))
            {
                $http.get(usuarioContext + '/' + $state.params.Uid).then(function (response)
                {
                    var usuarioActua = response.data;
                    if (usuarioActua.genero === true)
                    {
                        usuarioActua.genero2 = 'M';
                        usuarioActua.imagen = "resources/images/hombre.png";
                    } else
                    {
                        usuarioActua.genero2 = 'F';
                        usuarioActua.imagen = "resources/images/mujer.png";
                    }
                    $http.get("api/tipoMedidas").then(function (response) {
                        $scope.tipos = response.data;
                    });

                    $scope.usuarioActual = usuarioActua;
                    tema();

                    Highcharts.chart('container2', {
                        chart: {
                            type: 'areaspline',
                            zoomType: 'x'
                        },
                        title: {
                            text: 'Peso'
                        }, legend: {
                            enabled: false
                        },
                        xAxis: {
                            type: 'text',
                            text: 'Tiempo',
                            categories: getDias()//['Apples', 'Bananas', 'Oranges']
                        },
                        yAxis: {
                            title: {
                                text: 'peso(KG)'
                            }
                        },
                        series: [{
                                name: 'Peso',
                                data: getPesosNvo()
                            }]
                    });
                    function getDias() {

                        var arreglo = [];
                        var estados = $scope.usuarioActual.estados;
                        var fechas = [];

                        for (var i = 0; i < estados.length; i++) {
                            var dia = new Date($scope.usuarioActual.estados[i].fecha);
                            fechas.push(darMes(dia.getMonth()) + '/' + dia.getDate());//meter fecha
                        }
                        arreglo.push(fechas);
                        return arreglo[0];
                    }
                    function getPesosNvo() {
                        if (tip === undefined || tip === null)
                        {
                            tip = 'PESO';
                        }
                        var arreglo = [];
                        var estados = $scope.usuarioActual.estados;
                        for (var i = 0; i < estados.length; i++) {
                            var temp = [];
                            var dia = new Date($scope.usuarioActual.estados[i].fecha);

                            var medidas = estados[i].medidas;
                            temp.push(dia.getMonth() + 1 + '/' + dia.getDate() + '/' + dia.getFullYear());//meter fecha
                            var ver = false;
                            for (var j = medidas.length - 1; j >= 0; j--)
                            {
                                if (medidas[j].descripcion === tip.trim())
                                {
                                    temp.push(medidas[j].medida);
                                    med = medidas[j].unidad;
                                    ver = true;
                                }
                            }
                            if (!ver)
                            {
                                temp.push(0);
                            }
                            arreglo.push(temp);
                        }

                        return arreglo;
                    }
                    $scope.obtener = function ()
                    {
                        //console.info($scope.tipo);
                        tip = $scope.tipoo;
                        var me = getPesosNvo();
                        Highcharts.chart('container2', {
                            chart: {
                                type: 'areaspline',
                                zoomType: 'x'
                            },
                            title: {
                                text: tip.toLocaleLowerCase()
                            }, legend: {
                                enabled: false
                            },
                            xAxis: {
                                type: 'text',
                                text: 'Tiempo',
                                categories: getDias()
                            },
                            yAxis: {
                                title: {
                                    text: tip.toLocaleLowerCase() + ' (' + med + ')'
                                }
                            },
                            series: [{
                                    name: tip.toLocaleLowerCase() + ' (' + med + ')',
                                    data: me
                                }]
                        });
                    };

                });
            }
        }]);

    mod.controller('usuariosCtrl', ['$scope', '$http', 'usuarioContext',
        function ($scope, $http, usuarioContext) {
            $http.get(usuarioContext).then(function (response) {
                var x = response.data;
                x.forEach(function (element) {
                    if (element.genero === true)
                    {
                        element.genero2 = 'M';
                        element.imagen = "resources/images/hombre.png";
                    } else
                    {
                        element.genero2 = 'F';
                        element.imagen = "resources/images/mujer.png";
                    }
                });
                $scope.usuarioRecords = x;
            });
            //borrar
            this.deleteRecord = function (usuario) {
                return $http.delete(usuarioContext + "/" + usuario.id)
                        .then(function () {
                            // $http.delete es una promesa
                            // cuando termine bien, cambie de estado
                            var index = $scope.usuarioRecords.indexOf(usuario);
                            if (index > -1) {
                                $scope.usuaruiRecords.splice(index, 1);
                            }
                        });
            };
        }]);

    mod.controller('usuarioscreateCtrl', ['$scope', '$http', 'usuarioContext', '$state',
        function ($scope, $http, usuarioContext, $state) {
            $scope.createUsuario = function ()
            {
                $http.post(usuarioContext, {
                    nombre: $scope.nombreUsuario,
                    fechaDeNacimiento: $scope.fechaUsuario,
                    genero: $scope.genero
                }).then(function (response) {
                    $state.go('usuariosList', {id: response.data.id}, {reload: true});
                });
            };
        }
    ]);

    function darMes(mes) {
        var meses = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'June', 'July', 'Aug', 'Sept', 'Oct', 'Nov', 'Dec'];
        return meses[mes];
    }

    function tema()
    {
        Highcharts.theme = {
            colors: ['#0B5394', '#8085e9', '#8d4654', '#7798BF', '#aaeeee',
                '#ff0066', '#eeaaee', '#55BF3B', '#DF5353', '#7798BF', '#aaeeee'],
            chart: {
                backgroundColor: '#a6a6a6',
                style: {
                    fontFamily: 'Signika, serif'
                }
            },
            title: {
                style: {
                    color: 'black',
                    fontSize: '16px',
                    fontWeight: 'bold'
                }
            },
            subtitle: {
                style: {
                    color: 'black'
                }
            },
            tooltip: {
                borderWidth: 0
            },
            legend: {
                itemStyle: {
                    fontWeight: 'bold',
                    fontSize: '13px'
                }
            },
            xAxis: {
                labels: {
                    style: {
                        color: 'black'
                    }
                }
            },
            yAxis: {
                labels: {
                    style: {
                        color: 'black'
                    }
                }
            },
            plotOptions: {
                series: {
                    shadow: true
                },
                candlestick: {
                    lineColor: '#404048'
                },
                map: {
                    shadow: false
                }
            },

            // Highstock specific
            navigator: {
                xAxis: {
                    gridLineColor: '#D0D0D8'
                }
            },
            rangeSelector: {
                buttonTheme: {
                    fill: 'white',
                    stroke: '#C0C0C8',
                    'stroke-width': 1,
                    states: {
                        select: {
                            fill: '#D0D0D8'
                        }
                    }
                }
            },
            scrollbar: {
                trackBorderColor: '#C0C0C8'
            },

            // General
            background2: '#E0E0E8'

        };
        Highcharts.setOptions(Highcharts.theme);
    }
}
)(angular);

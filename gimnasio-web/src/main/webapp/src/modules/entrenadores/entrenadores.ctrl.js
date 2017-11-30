//<!--Esta pagina ha sido desarrollada por Mateo Sicard 
//   m.sicard10 201512474  -->

var tip;
var med;
(function (ng) {
    var mod = ng.module("entrenadorModule");
    mod.constant("entrenadorContext", "api/entrenadores");
    mod.controller('entrenadoresCtrl', ['$scope', '$http', 'entrenadorContext', '$state',
        function ($scope, $http, entrenadorContext, $state) {
            $http.get(entrenadorContext).then(function (response) {
                var h = response.data;
                h.forEach(function (element) {
                    var parts = element.fechaNacimiento.split('T')[0].split('-');
                    var fecha = new Date(parts[0], parts[1] - 1, parts[2]);
                    element.edad = calcularEdad(fecha);
                });
                $scope.entrenadorRecords = h;

                if (($state.params.entId !== undefined) && ($state.params.entId !== null))
                {
                    $http.get(entrenadorContext + '/' + $state.params.entId).then(function (response)
                    {

                        $scope.entrenadorActual = response.data;
                        $scope.nombreEntrenador = $scope.entrenadorActual.name;
                        $scope.documentoEntrenador = $scope.entrenadorActual.documento;
                        var x = $scope.entrenadorActual.fechaNacimiento;
                        var parts = x.split('T');
                        parts = parts[0].split('-');
                        $scope.fechaEntrenador = new Date(parts[0], parts[1] - 1, parts[2]);
                        $scope.edadEntrenador = calcularEdad($scope.fechaEntrenador);

                    });
                }
                if (($state.params.Eid !== undefined) && ($state.params.Eid !== null))
                {
                    $http.get(entrenadorContext + '/' + $state.params.Eid).then(function (response)
                    {
                        var entrenadorActua = response.data;
                        entrenadorActua.usuarios.forEach(function (element) {
                            if (element.genero === true)
                            {
                                element.genero2 = 'M';

                            } else
                            {
                                element.genero2 = 'F';

                            }
                        });
                        $scope.entrenadorActual = entrenadorActua;
                    });
                }
                if (($state.params.Enid !== undefined) && ($state.params.Enid !== null))
                {
                    $http.get("api/tipoMedidas").then(function (response) {
                        $scope.tipos = response.data;
                    });
                    $http.get(entrenadorContext + '/' + $state.params.Enid + '/usuarios/' + $state.params.Uid).then(function (response)
                    {
                        //console.info(tip);
                        $scope.usuarioActual = response.data;
                        if ($scope.usuarioActual.genero === true)
                        {
                            $scope.usuarioActual.genero2 = 'M';
                            $scope.usuarioActual.imagen = "resources/images/hombre.png";
                        } else
                        {
                            $scope.usuarioActual.genero2 = 'F';
                            $scope.usuarioActual.imagen = "resources/images/mujer.png";
                        }
                        Highcharts.chart('container', {
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





                    });
                    tema();

                }
                
            });
            $scope.editarEntrenador = function ()
            {
                $http.put(entrenadorContext + '/' + $scope.entrenadorActual.id, {
                    name: $scope.nombreEntrenador,
                    fechaNacimiento: $scope.fechaEntrenador,
                    documento: $scope.documentoEntrenador

                }).then(function (response) {
                    //se crea exitosamente el entrenador
                    $state.go('entrenadoresList', {id: response.data.id}, {reload: true});
                });
            };


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
                tip = $scope.tipo;
                var me = getPesosNvo();
                Highcharts.chart('container', {
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
            //borrar
            this.deleteRecord = function () {
                //console.info(entrenadorContext + "/" + $state.params.entrenadorid)
                return $http.delete(entrenadorContext + "/" + $state.params.entrenadorid)
                        .then(function (response) {
                            $state.go('entrenadoresList', {id: response.data.id}, {reload: true});

                        });
            };

            this.deleteUsuarioEntrenador = function (usuario, entrenadorid) {
                return $http.delete(entrenadorContext + "/" + entrenadorid + "/usuarios/" + usuario.id)
                        .then(function () {
                            // $http.delete es una promesa
                            // cuando termine bien, cambie de estado
                            var index = $scope.entrenadorActual.usuarios.indexOf(usuario);
                            if (index > -1) {
                                $scope.entrenadorActual.usuarios.splice(index, 1);
                            }
                        });
            };
            this.genero = function (genero)
            {
                if (genero)
                {
                    return 'Femenino';
                }
                return 'masculino';
            };
        }
    ]);


    mod.controller('entrenadoresUsuarioCtrl', ['$scope', '$http', 'entrenadorContext', '$state',
        function ($scope, $http, entrenadorContext, $state) {
            $scope.createEstudiante = function ()
            {
                $http.post(
                        entrenadorContext + "/" + $state.params.Xid + "/usuarios/" + $scope.idUsuarioC, {}
                ).then(function (response) {
                    //se crea exitosamente el entrenador
                    $state.go('entrenadoresList', {id: response.data.id}, {reload: true});
                });
            };
        }]);

    mod.controller('entrenadoresCreateCtrl', ['$scope', '$http', 'entrenadorContext', '$state',
        function ($scope, $http, entrenadorContext, $state) {
            $scope.createEntrenador = function ()
            {
                $http.post(entrenadorContext, {
                    name: $scope.nombreEntrenador,
                    fechaNacimiento: $scope.fechaEntrenador,
                    documento: $scope.documentoEntrenador

                }).then(function (response) {
                    //se crea exitosamente el entrenador
                    $state.go('entrenadoresList', {id: response.data.id}, {reload: true});
                });
            };
        }]);
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
    function calcularEdad(fecha) {
        var hoy = new Date();
        var cumpleanos = new Date(fecha);
        var edad = hoy.getFullYear() - cumpleanos.getFullYear();
        var m = hoy.getMonth() - cumpleanos.getMonth();

        if (m < 0 || (m === 0 && hoy.getDate() < cumpleanos.getDate())) {
            edad--;
        }

        return edad;
    }


    function darMes(mes) {
        var meses = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'June', 'July', 'Aug', 'Sept', 'Oct', 'Nov', 'Dec'];
        return meses[mes];
    }

}
)(angular);



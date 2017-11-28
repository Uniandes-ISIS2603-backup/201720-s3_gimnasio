//<!--Esta pagina ha sido desarrollada por Mateo Sicard 
//   m.sicard10 201512474  -->
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
                    $http.get(entrenadorContext + '/' + $state.params.Enid + '/usuarios/' + $state.params.Uid).then(function (response)
                    {
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

                    });
                }

            });

            //borrar
            this.deleteRecord = function () {
                console.info(entrenadorContext + "/" + $state.params.entrenadorid);
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

            $scope.createEstudiante = function ()
            {
                console.info(entrenadorContext + "/" + $state.params.Xid + "/usuarios/" + $scope.idUsuarioC);
                $http.post(
                        entrenadorContext + "/" + $state.params.Xid + "/usuarios/" + $scope.idUsuarioC, {}
                ).then(function (response) {
                    //se crea exitosamente el entrenador
                    $state.go('entrenadoresList', {id: response.data.id}, {reload: true});
                });
            };
        }


    ]);
}
)(angular);



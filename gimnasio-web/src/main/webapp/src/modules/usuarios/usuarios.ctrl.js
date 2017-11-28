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

                    $scope.usuarioActual = usuarioActua;
                });
            }
        }]);

    mod.controller('usuariosCtrl', ['$scope', '$http', 'usuarioContext', '$state',
        function ($scope, $http, usuarioContext, $state) {
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
}
)(angular);

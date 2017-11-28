/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng) {
var mod = ng.module("usuarioModule");
        mod.constant("usuario                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                Context", "api/entrenadores");
        mod.controller('usuariosCtrl', ['$scope', '$http', 'usuarioContext', '$state',
                function ($scope, $http, usuarioContext, $state) {
                $http.get(usuarioContext).then(function (response) {
                var x = response.data;
                        x.forEach(function(element){
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
                        $scope.usuarioRecords = x

                        if (($state.params.Uid !== undefined) && ($state.params.Uid !== null))
                {
                $http.get(usuarioContext + '/' + $state.params.Uid).then(function(response)
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
                });
                        //borrar
                        this.deleteRecord = function(usuario){
                        return $http.delete(usuarioContext + "/" + usuario.id)
                                .then(function () {
                                // $http.delete es una promesa
                                // cuando termine bien, cambie de estado
                                var index = $scope.usuarioRecords.indexOf(usuario);
                                        if (index > - 1) {
                                $scope.usuaruiRecords.splice(index, 1);
                                }
                                });
                        }

                $scope.createUsuario = function()
                {
                $http.post(usuarioContext, {
                nombre: $scope.nombreUsuario,
                        fechaDeNacimiento: $scope.fechaUsuario,
                        genero: $scope.genero

                }).then(function (response) {
                //se crea exitosamente el usuario
                $state.go('usuariosList', {id: response.data.id}, {reload: true});
                });
                }
                }


        ]);
        }
)(angular);

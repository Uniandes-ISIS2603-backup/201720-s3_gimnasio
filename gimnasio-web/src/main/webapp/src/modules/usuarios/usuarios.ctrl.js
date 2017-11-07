(function (ng) {
    var mod = ng.module("usuarioModule");
    mod.constant("usuarioContext", "api/usuarios");
    mod.controller('usuariosCtrl', ['$scope', '$http', 'usuarioContext', '$state',
       function ($scope, $http, usuarioContext, $state) {
            console.info("dar datos usuario");
            $http.get(usuarioContext).then(function (response) {
                $scope.usuarioRecords = response.data;
                console.info(response.data);
            });
            
            //borrar
            this.deleteRecord = function(usuario){
                    return $http.delete(usuarioContext + "/" + usuario.id)
                            .then(function () {
                                
                                var index = $scope.usuarioRecords.indexOf(usuario);                               
                                if (index > -1) {
                                    $scope.usuarioRecords.splice(index, 1);
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
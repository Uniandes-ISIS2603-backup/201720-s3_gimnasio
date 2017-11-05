(function (ng) {
    var mod = ng.module("maquinaModule");
    mod.constant("maquinaContext", "api/maquinas");
    mod.controller('maquinasCtrl', ['$scope', '$http', 'maquinaContext', '$state',
       function ($scope, $http, maquinaContext, $state) {
            console.info("dar datos maquina");
            $http.get(maquinaContext).then(function (response) {
                $scope.maquinaRecords = response.data;
                console.info(response.data);
            });
            
            //borrar
            this.deleteRecord = function(maquina){
                    return $http.delete(maquinaContext + "/" + maquina.id)
                            .then(function () {
                                // $http.delete es una promesa
                                // cuando termine bien, cambie de estado
                                var index = $scope.maquinaRecords.indexOf(maquina);                               
                                if (index > -1) {
                                    $scope.maquinaRecords.splice(index, 1);
                                }
                            });
            }
            
            $scope.createMaquina = function()
            {
                $http.post(maquinaContext, {
                    informacion: $scope.informacionMaquina

                }).then(function (response) {
                    //se crea exitosamente la maquina
                    $state.go('maquinasList', {id: response.data.id}, {reload: true});
                });
            }
        }
        
        
    ]);
}
)(angular);



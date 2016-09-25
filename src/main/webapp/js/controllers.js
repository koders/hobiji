angular.module('personApp.controllers',[]).controller('PersonListController',function($scope,$state,popupService,$window,Person){

    $scope.persons=Person.query();

    $scope.deletePerson=function(person){
        if(popupService.showPopup('Really delete this?')){
            Person.delete(person,function(){
                $window.location.reload();
            });
        }
    }

}).controller('PersonViewController',function($scope,$stateParams,Person){

    $scope.person=Person.get({id:$stateParams.id});

}).controller('PersonCreateController',function($scope,$state,$stateParams,Person,Hobby){

    $scope.person=new Person();

    Hobby.query().$promise.then(function(hobbies) {
        $scope.hobbies = hobbies;
        $scope.checkedHobbies = [];

        hobbies.forEach(function(hobby){
            var h = {"name" : hobby.name, "checked" : $scope.hobbyAdded(hobby.name)};
            $scope.checkedHobbies.push(h);
        });
    });

    $scope.addPerson=function(){

        var h;
        $scope.person.hobbies = [];
        if($scope.checkedHobbies != null)
            $scope.checkedHobbies.forEach(function(hobby) {
                h = $scope.findHobby(hobby.name);
                if(h != null && hobby.checked)
                    $scope.person.hobbies.push(h);
            });

        Person.save($scope.person,function(){
            $state.go('persons');
        });
    }

    $scope.findHobby = function(hobby) {
        var result = null;
        if($scope.hobbies != null)
            $scope.hobbies.forEach(function(h){
                if(hobby == h.name) {
                    result = h;
                    return;
                }

            });
        return result;
    };

    $scope.hobbyAdded = function(hobby) {
        var checked = false;
        if($scope.person.hobbies != null)
            $scope.person.hobbies.forEach(function(h){
                if(h.name == hobby) {
                    checked = true;
                    return;
                }
            });
        return checked;
    };

}).controller('PersonEditController',function($scope,$state,$stateParams,Person,Hobby){

    Hobby.query().$promise.then(function(hobbies) {
        $scope.hobbies = hobbies;
        $scope.checkedHobbies = [];

        hobbies.forEach(function(hobby){
            var h = {"name" : hobby.name, "checked" : $scope.hobbyAdded(hobby.name)};
            $scope.checkedHobbies.push(h);
        });
    });

    $scope.updatePerson=function(){
        var h;
        $scope.person.hobbies = [];
        if($scope.checkedHobbies != null)
            $scope.checkedHobbies.forEach(function(hobby) {
                h = $scope.findHobby(hobby.name);
                if(h != null && hobby.checked)
                    $scope.person.hobbies.push(h);
            });

        Person.update($scope.person,function(){
            $state.go('persons');
        });

    };

    $scope.loadPerson=function(){
        $scope.person=Person.get({id:$stateParams.id});
    };

    $scope.loadPerson();

    $scope.findHobby = function(hobby) {
        var result = null;
        if($scope.hobbies != null)
        $scope.hobbies.forEach(function(h){
            if(hobby == h.name) {
                result = h;
                return;
            }

        });
        return result;
    };

    $scope.hobbyAdded = function(hobby) {
        var checked = false;
        if($scope.person.hobbies != null)
            $scope.person.hobbies.forEach(function(h){
                if(h.name == hobby) {
                    checked = true;
                    return;
                }
            });
        return checked;
    };

}).controller('HobbyListController',function($scope,$state,popupService,$window,Hobby){

    $scope.hobbies=Hobby.query();

    $scope.deleteHobby=function(hobby){
        if(popupService.showPopup('Really delete this?')){
            hobby.$delete(function(){
                $window.location.reload();
            });
        }
    }

}).controller('HobbyCreateController',function($scope,$state,$stateParams,Hobby){

    $scope.hobby=new Hobby();

    $scope.addHobby=function(){
        Hobby.save($scope.hobby,function(){
            $state.go('hobbies');
        });
    }

});
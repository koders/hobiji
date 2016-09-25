var services = angular.module('personApp.services',['ngResource']);

services.factory('Person',function($resource){
    return $resource('/rest/persons/:id',{},{
        query: { method: 'GET', isArray: true },
        update: {method: 'PUT', params: {id: '@id'}, isArray: false},
        delete: {method: 'DELETE', params: {id: '@id'}, isArray: false},
        save: {method: 'POST', isArray: false}
    });
});

services.factory('Persons',function($resource){
    return $resource('/rest/persons',{},{
        query: { method: 'GET', isArray: true }
    });
});

services.service('popupService',function($window){
    this.showPopup=function(message){
        return $window.confirm(message);
    }
});

services.factory('Hobby',function($resource){
    return $resource('/rest/hobbies/:id',{id:'@id'},{
        update: {
            method: 'PUT'
        }
    });
});

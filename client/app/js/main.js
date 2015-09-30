(function () {

'use strict';


    angular.module('MarketApp', ['ngRoute', 'ngAnimate', 'highcharts-ng'])

  .config([
    '$locationProvider',
    '$routeProvider',
    function($locationProvider, $routeProvider) {
      $locationProvider.hashPrefix('!');
      // routes
      $routeProvider
        .when("/", {
            templateUrl: "./partials/priorityViewer.html",
          controller: "MainController"
        })
        .otherwise({
           redirectTo: '/'
        });
    }
  ]);

  //Load controller
angular.module('MarketApp')

  .controller('MainController', [
    '$scope', 'priorityDataService',
    function ($scope, priorityDataService) {
        var self = this;
        $scope.highchartsNG = {};
        $scope.priorityList = {};
        $scope.currentCriteria = priorityDataService.getCurrentMarketCriteria();
        $scope.saveWeightage = function () {
            $('#weightageErrorDiv').hide();
            //Checking the validation
            var totalWeightage = $scope.currentCriteria.weightage.bestPriceWeight +
                $scope.currentCriteria.weightage.filledRatioWeight + $scope.currentCriteria.weightage.latencyWeight;

            if (totalWeightage == 1) {
                $scope.retreivePriorityData();
            } else {
                $('#weightageErrorDiv').show();
            }
        };

        $scope.retreivePriorityData = function () {
            $('#dataRetrivalError').hide();
            
            //priorityDataService.retriveData($scope.currentCriteria).then(function (priorityData) {
            //    $scope.priorityList = priorityData.priorityList;
            //    $scope.highchartsNG = priorityData.chartData;
            //    $('#priorityData').show();
            //}, function(error){
            //    $('#dataRetrivalError').show();
            //});

            var retriveData = priorityDataService.retriveData($scope.currentCriteria);
            $scope.priorityList = retriveData.priorityList;
            $scope.highchartsNG = retriveData.chartData;
            $scope.highchartsNG.options = {
                   chart: {
                    type: 'line'
                }
            };
            $('#priorityData').show();
        };
        $('#priorityData').hide();

        $scope.timeStampReferential = priorityDataService.getTimeStampReferential();        
    }   
  ])

        .service('priorityDataService', function ($http) {
           
      this.getCurrentMarketCriteria = function () {
          return {
              weightage: {
                  bestPriceWeight: 0,
                  latencyWeight: 0,
                  filledRatioWeight: 0
              },
              timestampDuration: 'Last Minute'
          }
      };    

      this.retriveData = function (priorityCriteria) {
          return {
              priorityList: [{
                  name: 'Market 1'
              },
                {
                    name: 'Market 2'
                },
                {
                    name: 'Market 3'
                },
                {
                    name: 'Market 4'
                }],
              chartData: {                  
                  series: [{
                      name: 'Market 1',
                      data: [10, 15, 12, 8, 7]
                  },
                  {
                      name: 'Market 2',
                      data: [23, 1, 14, 9, 17]
                  }],
                  title: {
                      text: 'Market Feed'
                  },
                  loading: false
              }
          };

          /*$http.post('api/marketData/retriveData', priorityCriteria)
           .success(function (marketData) {
               return marketData;
           })
           .error(function (error) {
               console.log("username check error: ", error);
               return error;
           });*/
      };

      this.getTimeStampReferential = function () {
          return ['Last Minute', 'Last Hour', 'Last Day'];
      };
      
    })

    ;
}());
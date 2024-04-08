Feature:Retrieve the booking for HerokuApp

  Background: url service
    * url 'https://restful-booker.herokuapp.com/'

  Scenario: Retrieve the booking for HerokuApp
    Given path 'booking'
    When method GET
    Then status 200
    And print 'getResponse: ', response
    And match response.[*].firstname == "#present", "#string", "#notnull"
    And match response.[*].lastname == "#present", "#string", "#notnull"
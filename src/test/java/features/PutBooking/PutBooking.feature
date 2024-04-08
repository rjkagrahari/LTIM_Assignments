Feature:Update Booking For A booking id

  Background: url service
    * url 'https://restful-booker.herokuapp.com/'
    * def authToken = call read('classpath:features/token/createToken.feature@CreateToken')

  Scenario: Update Booking For A booking id
    Given path 'booking/830'
    * header Accept = 'application/json'
    * header Cookie = 'token=' + authToken.Token
    And print authToken.accessToken
    And request read ('classpath:resources/Upbody.json')
    When method PUT
    Then status 200
    * def UpdateResponse = response
    And match response.[*].firstname == "#present", "#string", "#notnull"
    And match response.[*].lastname == "#present", "#string", "#notnull"
    And print 'UpdateResponse: ', response
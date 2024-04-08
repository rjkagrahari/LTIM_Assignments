Feature:Delete a booking from Booking List

  Background: url service
    * url 'https://restful-booker.herokuapp.com/'
    * def authToken = call read('classpath:features/token/createToken.feature@CreateToken')

  Scenario: Delete a booking from Booking List
  Given path 'booking'
    Given header Accept = 'application/json'
    And request read ('classpath:resources/body.json')
    When method POST
    Then status 200
    And def id = response.bookingid
    And print id
    Given path 'booking/'
    And path response.bookingid
    * header Accept = 'application/json'
    * header Cookie = 'token=' + authToken.Token
    And print authToken.accessToken
    When method DELETE
    Then status 201
    And print 'DeleteResponse: ', response
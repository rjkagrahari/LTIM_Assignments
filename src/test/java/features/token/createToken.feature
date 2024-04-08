@ignore
Feature:connection

  Background: url service
    * url 'https://restful-booker.herokuapp.com/'

  @CreateToken
  Scenario: Authentication test
    * def privateSettings = read('classpath:resources/privateSettings.json')
    Given path 'auth'
    Given header Accept = 'application/json'
    And request
      """
      {
                    username: '#(privateSettings.TenantCredentials.username)',
                    password: '#(privateSettings.TenantCredentials.password)'
      }
      """
    When method POST
    Then status 200
    And assert responseTime < 4000
    * def Token = response.token

    And match response.token == "#present", "#string", "#notnull"
    * print Token
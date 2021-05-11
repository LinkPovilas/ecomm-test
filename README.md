# Barbora tests

### What is it:

A test automation demo/practice project

### What was done:
* Implemented Page Object Model (POM) design pattern in the automation project.
* Implemented logging logic so actions with web elements would be logged automatically.
* Project profiles: local or server, and chrome driver or safari driver.
* For logging assertions created custom assertion class.
* Created 2 tests. One for testing landing page, one for adding cheapest pizza into shopping cart.
* Both tests are independent from each other.
* Tests are written in Arrange-Act-Assert (AAA) test pattern:
```java
    public void navigateToHomePage() 
    {
        //Arange
        String region = VILNIAUS.getValue();
        String service = BARBORA.getValue();
        String pageUrl = "https://pagrindinis.barbora.lt/";
        
        //Act
        landingPage.selectOption(region);
        landingPage.selectOption(service);
        
        //Assert
        custom.isPageOpen(pageUrl);
    }
```

### Project components:
* [Selenide](https://selenide.org) (for iteration with browser)
* [Junit 5](https://junit.org/junit5/)  (for tests execution)
* [Maven](https://maven.apache.org)  (for dependency management)
* [Log4j 2 ](https://logging.apache.org/log4j/2.x/)  (for logging what tests do)

### Whats needed to be setup: for the first time:
1. Install [Chrome](https://www.google.com/chrome/) browser.
2. Download [ChromeDriver](https://chromedriver.chromium.org) based on your Chrome Browser version.
3. Include the ChromeDriver location in your PATH environment variable.
4. Inside the file credentials.properties update the field EMAIL with the email for barbora website.
5. Inside the file credentials.properties update the field PASSWORD with the password for barbora website.
6. Execute maven command:
```
mvn clean install
```
Before launching tests select BROWSER_CHROME and LOCAL profiles.

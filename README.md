# Test Framework for Cars.com

Web browser testing with Selenium Webdriver in Java. Tests are run on [cars.com](https://cars.com) website.

## Getting started

Depending on how you want to run the tests there are a few prerequisites. Tests can be run:
1. On your local machine. See [instructions](#running-tests-locally)
2. In Docker containers with Selenium Grid. See [instructions](#running-tests-in-docker-with-selenium-grid)

### Running tests locally
#### Prerequisites
1. Download Java [13.0.1](https://www.oracle.com/java/technologies/javase/jdk13-archive-downloads.html) Any Java 8 and above should work, but I recommend Java 13.
2. Install Java. [Here](https://docs.oracle.com/en/java/javase/17/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A) you can find some instructions.
3. Add Java to your environment variables. See some instructions [here](https://www.baeldung.com/java-home-on-windows-7-8-10-mac-os-x-linux) for both Windows and MacOS.
4. Download and install [Maven Apache](https://maven.apache.org/download.cgi)
5. Add Maven to your environment variables. See some instructions [here](https://www.baeldung.com/install-maven-on-windows-linux-mac) for both Windows and MacOS.
6. Download and install [chrome browser](https://www.google.com/chrome/?brand=CHBD&geo=US&gclid=Cj0KCQiAi9mPBhCJARIsAHchl1w4h4nJ9RAvKvmd7fM0W-Rqk6WmKiQbMRARLvwurBekpSCqn__GrnwaAuKxEALw_wcB&gclsrc=aw.ds).
7. Download and install [firefox browser](https://www.mozilla.org/en-US/firefox/new/). 
6. Depending on the editor you use (I personally use [InteliJ](https://www.jetbrains.com/idea/download)) set the project SDK to Java 13. In Intelij you can set it by going to File -> Project Structure -> Project Settings -> Project
7. Set bash permissions to run shell scripts for the `run-tests` file. 
```
chmod u+x run-tests.sh
```
#### Run tests
Run the following command in the terminal from the project's root directory:
```
./run-tests chrome
```
You can run tests either in chrome or firefox. If you don't specify a browser name the tests will run by default in chrome.

Tests results can be viewed by opening `index.html` file. The file will be automatically opened after tests are ran.

### Running tests in Docker with Selenium Grid
1. Download and install [Maven Apache](https://maven.apache.org/download.cgi). Maven is needed to package your application and create a docker image.
2. Add Maven to your environment variables. See some instructions [here](https://www.baeldung.com/install-maven-on-windows-linux-mac) for both Windows and MacOS.
3. Download and install [Docker Desktop](https://www.docker.com/products/docker-desktop). Make sure docker is running.
4. Depending on the editor you use (I personally use [InteliJ](https://www.jetbrains.com/idea/download)) set the project SDK to Java 13. In Intelij you can set it by going to File -> Project Structure -> Project Settings -> Project
5. Set bash permissions to run shell scripts for the `run-tests-docker` file. 
```
chmod u+x run-tests-docker.sh
```
#### Run tests
Run the following command in the terminal from the project's root directory:
```
./run-tests-docker
```
This script will run the tests in parallel both in chrome and firefox using Selenium Grid. The grid will start on your localhost port 4444.

After running the tests run:
```
docker-compose down
```
to stop all running containers.

Test results are saved in `cars-chrome` and `cars-firefox` folders. You can see several types of html reports generated. One of them is `index.html`.
# SauceDemoTestingProject
Testing [SauceDemo](https://www.saucedemo.com/) website & building framework using Selenium, TestNG, Jenkins, Maven Surefire Plugin, ExtentReport, SeleniumGrid, Jackson DataBind, Cucumber BDD

Jenkins configuration screenshots+Data:

Git repository URL: https://github.com/Horokhov/SauceDemoTestingProject;

Build steps -> Invoke top-Maven targets -> test -P"$Profile" -Dbrowser="$browserDriver"

This project is parametrized -> Choice Parameter
name: Profile 
Choices: 
Purchase
Socials
Errors
Filter
AllTest
LogOut
RemoveProduct
AboutRedirection
ResetApp
Image

This project is parametrized -> Choice Parameter
name: browserName
Choices: 
edge
edgeheadless
firefox
firefoxheadless
chrome
chromeheadless

<img width="1272" alt="image_2023-05-13_04-04-33" src="https://github.com/Horokhov/SauceDemoTestingProject/assets/108956780/0e1f3770-a928-4fe5-af55-275500fbbe49">

<img width="1272" alt="2" src="https://github.com/Horokhov/SauceDemoTestingProject/assets/108956780/7c5e4dc2-82e7-4aad-bca8-ab5f683e3694">

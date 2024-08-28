# Project Title

Adding JIRA projects JIRA tickets through Rest API v3 end points.

## Tools Used

* Rest Assured
* Test-NG
* github
* maven
* java

## Project Structure
![restassuredprojectstructure](https://github.com/user-attachments/assets/0d2b1fc4-e999-4d82-a670-b519fb6e0489)

## Project Working Procedure
When we execute test-NG classes it will create , modify , delete projects through Rest end points , same way it also create , modify , delete tickets in a project through Rest end points. Test data will be taken from Excel and processed by ExcelHandler java class and JsonHandler will prepare payloads as per the request type before hitting the end points.

## Test-NG class to handle Jira Projects
![restassuredprojectstests](https://github.com/user-attachments/assets/8873dd71-c675-49c5-a59d-06539ba92f62)

## Test-NG class to handle Jira Tickets
![restassuredticketstest](https://github.com/user-attachments/assets/7a66dc7c-367e-42d7-9033-968c29b174b9)

## Sample Jira Projects screenshot
![Manual2](https://github.com/user-attachments/assets/4267b8f9-e31e-49bc-a073-b241feca58b2)
## Sample Jira Ticket screenshot in a Project
![Manual5ticketss](https://github.com/user-attachments/assets/1445af5c-b0aa-418a-89e6-61aae49060a5)



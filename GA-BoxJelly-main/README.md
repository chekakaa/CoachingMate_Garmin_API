# GA-BoxJelly
Repository for work by the Boxjelly team on the CoachingMate Garmin API (code: GA) project.

## Project Overview
GA-BoxJelly is a software project aimed at integrating data from Garmin watches into the CoachingMate system to enhance workout guidance for exercisers. 
The primary goal of the project is to collect and display real-time workout data from Garmin watches, enabling coaches to provide personalized feedback and tailored workout plans to exercisers. 
This will be achieved through secure connections and data storage, as well as user-friendly interfaces for both coaches and exercisers.

## Planed Key Features:
1. Secure and real-time data integration: GA-BoxJelly will establish connections between Garmin watches and the CoachingMate system, allowing for data transmission after workout sessions.

2. Organized and secure data storage: The collected data will be stored on a secure server database, ensuring privacy and easy access for analysis and guidance by coaches.

3. Comprehensive data visualization and analysis: Coaches will have access to a user-friendly dashboard that enables them to analyze exercisers' data, identify trends, and provide personalized workout guidance based on individual needs and goals.

4. Customizable data sharing: Exercisers will have control over which data they share with the CoachingMate system, allowing them to maintain their privacy while still benefiting from personalized coaching.

5. Personalized feedback and progress tracking: Exercisers will be able to view their workout data within the CoachingMate system and receive tailored feedback from coaches, helping them improve their performance and reach their fitness goals.

## Planed Project Scope

The final product of this project will be a well-designed webpage that displays the workout data for each activity and athletes,and let coaches monitor their athletes’ performance

The key criteria of this project is for users to get familiar their progress in the training program and help them analyse and improve their performance.

In-scope :
- analyse and fix the bug of existing code
- improve the frontend design
- receive data from Garmin Connect
- Let coaches monitor their athletes’ performance
- Encrypt the users' password to ciphertext. 
- Integrate with Spring security. 
- Add email service when user register. 
- Add resting password functionality.

Out-scope :
- deploy the project on AWS
- JWT on Redis
- check Garmin Connect status 
- Hierarchical Authorization

## Github Structure

```shell
├── docs/
  ├── Sprint 1 Documentations/
  ├── Sprint 2 Documentations/
  ├── Sprint 3 Documentations/
  ├── Sprint 4 Documentations/
  ├── Handpover/
├── src/
  ├── cm-backend/	
  ├── cm-frontend-react/			
├── tests/ 
├── prototypes/
  ├── high fidelity/ 
├── data samples
├── Deployment
└── README.md
```

| Path                                                       | Description                                                                                                |
|------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|
| README.md                                                  | Project Background, Team Members, GitHub Structure & Change Logs for each sprint                           |
| [docs/](./docs/)                                           | Documentation files                                                                                        |
| [src/](./src/)                                             | Project Source Code                                                                                        |
| [tests/](./tests/)                                         | User/System Tests                                                                                          |
| [prototypes/high fidelity/](./prototypes/high%20fidelity/) | High fidelity files (previous team screens, source files, etc.)                                            |
| [data samples/](./data_samples/)                           | Documents you need to generate with all the data (inputs) necessary to simulate/demonstrate your prototype |
| [Deployment/](./Deployment/)                               | Documents you need to change some config to deploy the project                                             |

## Project Management Tools
- [Confluence Space](https://confluence.cis.unimelb.edu.au:8443/pages/viewpage.action?spaceKey=COMP900822023SM1GABoxJelly&title=Home)
- [Trello](https://trello.com/b/3wup1hgd/comp90082)

## Development Environment
- Frontend:
    - node: v14.17.6(v18 or higher version doesn't work)
    - vue-cli: 2.9.6
    - react: 16.9.34
- IDE: VScode, IntelliJ
- Program: Linux, MacOS, Windows
- Framework: Frontend-backend structure
- Browser: Chrome

## Release Log
#### Sprint 1 Release
1. upload a structured project
2. understand the existing code.
3. fix the bug and change config to deploy the project on local and backend server
#### Sprint 2 Release
1. Fix the bug that prevents the map from displaying.
2. Fix the bug that same email or username would show a error page instead of a tips for users 
3. set constrain of password and do Encryption the password 
4. Previous team didn't write any test function, so we finish the test function for APIs.
5. deploy the frontend and backend (https://ga-box-jelly-ddbh.vercel.app/#/login)
#### Sprint 3 Release
1. Fix transferring data from fronted to backend 
2. Add email service when register 
3. Add resting password by email
4. Integrate with Spring Security
#### Sprint 4 Release
upload all the documents

## Version Control
1. V1.0 Released on 23 Mar 2023
- Deploy previous backend code on server connected to local frontend
  - Depoly MongoDB database and connect to server
  - Depoly backend connect to server
  - Deploy frontend locally and connect to sever backend
2. V2.0 Released on 29 Apr 2023
- Fix the entire project program
  - Solve the problem of map in the activity page cannot work.
  - Fix the bug that same email address would cause the error instead of handling the exception.
  - Solve the problem of activity Url setting
- Ensure the sustainable maintenance of the system.
  - Do code cleaning for entire project
  - Fix API Test
- Improve system security
  - Solve the problem of using plain text in the user login backend
- Functional Development
  - Add password constraints in frontend
3. V3.0 Released on 24 May 2023
- Security improvment
  - Fix transferring security issue
  - Integrate with Spring Security
  - Add SMTP email server to the backend
- System fuction improvment
  - Depoly the API to receve data from Garmin connect
  - Depoly the function (Disconnect Garmin Connect)
  - Depoly the function (Change Garmin connect account)
  - Depoly the function ( Reset password function)
- Product depolyment
  - Depoly the frontend on Vercel
4. V4.0 Released on 31 May 2023
- Handover upload  
## Deployment
#### You can check the document in Deployment folder

- Local frontend deployment

  - Download the all files and run `npm install` and `npm start` in [src/cm-frontend-react](./src/cm-frontend-react).

- Local backend deployment

  `mvn install` and `java -jar ./target/coachingmate-0.0.1-SNAPSHOT.jar` in [src/cm-backend](./src/cm-backend).
- Cloud deployment

  https://ga-box-jelly-ddbh.vercel.app/#/



## About Us

**Year**: 2023

**Supervisor**: [Paul Calverley](paul.calverley@unimelb.edu.au)

**Team Members**:

| Name          | ID      | Email                                 |
|---------------|---------| ------------------------------------- |
| Lingkang Zhou | 895717  | lingkangz@student.unimelb.edu.au      |
| Rui Liu       | 1111181 | rlli2@student.unimelb.edu.au       |
| Xiuyuan Zhu   | 1216251 | xiuzhu@student.unimelb.edu.au        |
| Yuhang Yao    | 1297297 | yuhyao1@student.unimelb.edu.au       |
| Yukun Li      | 1130155 | yukun2@student.unimelb.edu.au       |
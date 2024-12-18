
# **Quiz Application 🎯🎉✨**

This application is a Spring Boot-based RESTful quiz app where users can:
- **Add problems to the database**
- **Retrieve unanswered problems**
- **Submit answers**
- **View submission results**
- **Register and manage users**

It uses **Spring Security** for authentication, **H2** as the database, and follows clean architectural practices. 💡

---

## **Features 🌟**

### **Problem Management 📚**
- **Add Problems**: Add multiple quiz problems in JSON format.
- **Retrieve All Problems**: View all the problems available in the database.

### **Quiz Management 🧠**
- **Retrieve Unanswered Problem**: Get a random unanswered problem.
- **Submit Answers**: Submit answers to the problems and receive results.

### **User Management 🛠️**
- **Register Users**: Users can register with a username and a password.
- **View Registered Users**: Retrieve all registered users (for admin use).

### **Health Check 🩺**
- **Check if the server is running**.

---

## **Instructions for the Devs**

### **Before You Start 🏁**
1. **Clone the repository** and navigate to the project directory.
2. **Run the application**:
   - In the project directory, run the following command:
     ```
     mvn spring-boot:run
     ```
3. **Access the H2 Console** (optional):
   - **URL**: `http://localhost:8080/h2-console`
   - **JDBC URL**: `jdbc:h2:file:./data/quizdb`
   - **Username**: `sa`
   - **Password**: (leave empty)
   
---

### **Authentication Instructions 🔑**
Since **Spring Security** is enabled, you'll need to log in to access certain endpoints.

   
   to **register a new user**:
   - **POST** to `/u/register` with a JSON body:
     ```json
     {
       "username": "newuser",
       "password": "newpassword"
     }
     ```

---

### **Testing the APIs 🧪**
You can use **Postman** or any other API testing tool to interact with the endpoints. Below are instructions for testing common operations.

#### **Add Problems** 🖊️
- **POST** `/problems/add`
- **Request Body** (Example):
  ```json
  [
    {
      "problemStatement": "What is the capital of France?",
      "correctAnswer": "Paris",
      "options": ["Berlin", "Madrid", "Paris", "Lisbon"]
    },
    {
      "problemStatement": "What is 5 + 7?",
      "correctAnswer": "12",
      "options": ["10", "12", "15", "14"]
    }
  ]
  ```
  
#### **Submit Answers** 📝
- **POST** `/quizzes/submit`
- **Request Body** (Example):
  ```json
  {
    "quizId": 1,
    "answers": [
      {
        "problemId": 101,
        "selectedAnswer": "Paris"
      },
      {
        "problemId": 102,
        "selectedAnswer": "12"
      }
    ]
  }
  ```

---

### **Useful Endpoints for Testing** 🛤️

| **HTTP Method** | **Endpoint**                | **Description**                          |
|-----------------|-----------------------------|------------------------------------------|
| **POST**        | **/problems/add**           | **Add a list of problems to the database.** |
| **GET**         | **/problems**               | **Retrieve all problems.**               |
| **GET**         | **/quizzes/problem**        | **Get an unanswered problem.**           |
| **POST**        | **/quizzes/submit**         | **Submit an answer to a problem.**       |
| **POST**        | **/u/register**             | **Register a new user.**                 |
| **GET**         | **/u/register**             | **View all registered users.**           |
| **GET**         | **/quizzes/health-check**   | **Check if the server is running.**      |


### **EXAMPLE FOR EVERY ```POST``` CALL**:

```
1. **Register User**
   - **Endpoint**: /users/register
   - **HTTP Method**: POST
   - **Request Body**:
     {
       "username": "user1",
       "password": "password123"
     }
   - **Response**:
     {
       "id": 1,
       "username": "user1"
     }

2. **Add Problems**
   - **Endpoint**: /problems/add
   - **HTTP Method**: POST
   - **Request Body**:
     [
       {
         "id": 1,
         "question": "What is 2 + 2?",
         "correctAnswer": "4"
       },
       {
         "id": 2,
         "question": "What is 3 + 5?",
         "correctAnswer": "8"
       }
     ]
   - **Response**:
     [
       {
         "id": 1,
         "question": "What is 2 + 2?",
         "correctAnswer": "4"
       },
       {
         "id": 2,
         "question": "What is 3 + 5?",
         "correctAnswer": "8"
       }
     ]

3. **Submit Answers**
   - **Endpoint**: /quizzes/submit
   - **HTTP Method**: POST
   - **Request Body**:
     {
       "answers": [
         {
           "problemId": 1,
           "selectedAnswer": "4"
         },
         {
           "problemId": 2,
           "selectedAnswer": "8"
         }
       ]
     }
   - **Response**:
     {
       "totalQuestions": 2,
       "correctAnswers": 2,
       "score": 100
     }

4. **Submit Answers (Submissions Endpoint)**
   - **Endpoint**: /submissions
   - **HTTP Method**: POST
   - **Request Body**:
     {
       "answers": [
         {
           "problemId": 1,
           "selectedAnswer": "4"
         },
         {
           "problemId": 2,
           "selectedAnswer": "8"
         }
       ]
     }
   - **Response**:
     {
       "totalQuestions": 2,
       "correctAnswers": 2,
       "score": 100
     }

```


---

### **Health Check 🩺**
To ensure the server is running properly, **GET** `/quizzes/health-check` to get the server status.

---

## **Sample Data 📝**

### **Add Problems 🖊️**
**POST** `/problems/add`
```json
[
  {
    "problemStatement": "What is the capital of France?",
    "correctAnswer": "Paris",
    "options": ["Berlin", "Madrid", "Paris", "Lisbon"]
  },
  {
    "problemStatement": "What is 5 + 7?",
    "correctAnswer": "12",
    "options": ["10", "12", "15", "14"]
  }
]
```

### **Submit Answers 📝**
**POST** `/quizzes/submit`
```json
{
  "quizId": 1,
  "answers": [
    {
      "problemId": 101,
      "selectedAnswer": "Paris"
    },
    {
      "problemId": 102,
      "selectedAnswer": "12"
    }
  ]
}
```

---

## **Key Classes 🔑**

### **Controllers 🎛️**
- **ProblemController**: Manages adding and retrieving quiz problems.
- **QuizController**: Handles retrieving unanswered problems and submitting answers.
- **UserController**: Manages user registration and retrieval.
- **SubmissionController**: Evaluates submissions.

### **Services 📋**
- **ProblemService**: Business logic for problems.
- **QuizService**: Business logic for quizzes and submissions.
- **UserService**: Handles user-related operations.

### **Models 🧩**
- **Problem**: Represents a quiz problem.
- **SubmissionRequest**: Input format for answer submissions.
- **SubmissionResponse**: Output format for evaluated submissions.
- **User**: Represents a registered user.

---

## **Future Improvements 🌟**
- **Implement user authentication and authorization**.
- **Add leaderboards for users based on quiz scores**.
- **Expand the question types to include multiple-choice, true/false, and short answers**.
- **Enable persistent database storage**.

---

## **Contact 📧**
For any questions or feedback, feel free to contact the developer:
- **Piyush** 🎯

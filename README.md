# Quiz Application 🎯🎉✨

This application is a Spring Boot-based RESTful quiz app where users can:
- Add problems to the database
- Retrieve unanswered problems
- Submit answers
- View submission results
- Register and manage users

It uses Spring Security for authentication, H2 as the database, and follows clean architectural practices. 💡

---

## Features 🌟

### Problem Management 📚
- **Add Problems**: Add multiple quiz problems in JSON format.
- **Retrieve All Problems**: View all the problems available in the database.

### Quiz Management 🧠
- **Retrieve Unanswered Problem**: Get a random unanswered problem.
- **Submit Answers**: Submit answers to the problems and receive results.

### User Management 🛠️
- **Register Users**: Users can register with a username and a password.
- **View Registered Users**: Retrieve all registered users (for admin use).

### Health Check 🩺
- Check if the server is running.

---

## Endpoints 🛤️

| HTTP Method | Endpoint                | Description                          |
|-------------|-------------------------|--------------------------------------|
| POST        | `/problems/add`        | Add a list of problems to the database. |
| GET         | `/problems`            | Retrieve all problems.               |
| GET         | `/quizzes/problem`     | Get an unanswered problem.           |
| POST        | `/quizzes/submit`      | Submit an answer to a problem.       |
| POST        | `/u/register`          | Register a new user.                 |
| GET         | `/u/register`          | View all registered users.           |
| GET         | `/quizzes/health-check`| Check if the server is running.      |

---

## How to Run 🚀

### Prerequisites 🛠️
- Java 17+
- Maven
- Postman (for testing the APIs)

### Steps ✨
1. Clone the repository.
2. Navigate to the project directory.
3. Run the following command to build and start the application:
   ```
   mvn spring-boot:run
   ```
4. Access the H2 Console at:
   - URL: `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:testdb`
   - Username: `sa`
   - Password: (leave empty)

### Testing the APIs 🧪
Use Postman or any other API testing tool to test the endpoints.

---

## Sample Data 📝

### Add Problems 🖊️
POST `/problems/add`
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

### Submit Answers 📝
POST `/quizzes/submit`
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

## Key Classes 🔑

### Controllers 🎛️
- **ProblemController**: Manages adding and retrieving quiz problems.
- **QuizController**: Handles retrieving unanswered problems and submitting answers.
- **UserController**: Manages user registration and retrieval.
- **SubmissionController**: Evaluates submissions.

### Services 📋
- **ProblemService**: Business logic for problems.
- **QuizService**: Business logic for quizzes and submissions.
- **UserService**: Handles user-related operations.

### Models 🧩
- **Problem**: Represents a quiz problem.
- **SubmissionRequest**: Input format for answer submissions.
- **SubmissionResponse**: Output format for evaluated submissions.
- **User**: Represents a registered user.

---

## Future Improvements 🌟
- Implement user authentication and authorization.
- Add leaderboards for users based on quiz scores.
- Expand the question types to include multiple-choice, true/false, and short answers.
- Enable persistent database storage.

---

## Contact 📧
For any questions or feedback, feel free to contact the developer:
- **Piyush** 🎯


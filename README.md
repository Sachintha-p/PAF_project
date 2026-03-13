# Smart Campus Operations Hub

A comprehensive web system for handling facilities management, bookings, maintenance ticketing, and notifications for a smart campus environment.

## Architecture

- **Backend**: Java 17, Spring Boot 3.x, PostgreSQL, Spring Security OAuth2 (Google Sign-In)
- **Frontend**: React 18, Vite, Tailwind CSS, React Router v6

## Getting Started

### Backend Setup
1. Ensure you have Java 17 and Maven installed.
2. Have a PostgreSQL instance running on `localhost:5432` with a database named `smart_campus` (user `postgres`, password `password` - change these in `application-dev.properties` if needed).
3. Update OAuth2 placeholders in `application-dev.properties` with your Google Client ID and Secret.
4. Run `mvn spring-boot:run` inside the `backend` directory.

### Frontend Setup
1. Ensure you have Node.js 20+ installed.
2. Rename `frontend/.env.example` to `frontend/.env` if you need custom API URLs.
3. Keep default proxy mapping the API space.
4. Inside the `frontend` directory, run:
   ```bash
   npm install
   npm run dev
   ```

### CI/CD
This project configured with GitHub Actions to test the backend on `push`/`pull_request` and test building the frontend.

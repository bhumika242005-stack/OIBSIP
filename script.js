// ===== LOGIN LOGIC =====

const loginForm = document.getElementById("loginForm");
const errorMsg = document.getElementById("errorMsg");

// Demo credentials
const validUsername = "student";
const validPassword = "1234";

if (loginForm) {
    loginForm.addEventListener("submit", function (e) {
        e.preventDefault();

        const username = document.getElementById("username").value.trim();
        const password = document.getElementById("password").value.trim();

        if (username === validUsername && password === validPassword) {
            // Save login state and redirect to exam page
            localStorage.setItem("loggedIn", "true");
            localStorage.setItem("username", username);
            window.location.href = "exam.html";
        } else {
            errorMsg.textContent = "Invalid username or password. Try again.";
        }
    });
}
// ===== EXAM PAGE LOGIC =====




const questions = [
    {
        question: "What does HTML stand for?",
        options: [
            "Hyper Text Markup Language",
            "High Text Machine Language",
            "Hyperlink and Text Markup Language",
            "Home Tool Markup Language"
        ],
        answer: 0
    },
    {
        question: "Which language is used for styling web pages?",
        options: ["HTML", "JQuery", "CSS", "XML"],
        answer: 2
    },
    {
        question: "Which company developed Java?",
        options: ["Microsoft", "Sun Microsystems", "Apple", "Google"],
        answer: 1
    },
    {
        question: "Which symbol is used for comments in JavaScript (single line)?",
        options: ["<!-- -->", "//", "/* */", "#"],
        answer: 1
    },
    {
        question: "What does CPU stand for?",
        options: [
            "Central Process Unit",
            "Computer Personal Unit",
            "Central Processing Unit",
            "Central Processor Unit"
        ],
        answer: 2
    }
];

let currentQuestion = 0;
let userAnswers = new Array(questions.length).fill(null);
let timeLeft = 5 * 60; // 5 minutes in seconds
let timerInterval;

const questionContainer = document.getElementById("questionContainer");

if (questionContainer) {
    // Check if logged in
    if (localStorage.getItem("loggedIn") !== "true") {
        window.location.href = "index.html";
    }

    loadQuestion();
    startTimer();

    document.getElementById("prevBtn").addEventListener("click", function () {
        saveAnswer();
        if (currentQuestion > 0) {
            currentQuestion--;
            loadQuestion();
        }
    });

    document.getElementById("nextBtn").addEventListener("click", function () {
        saveAnswer();
        if (currentQuestion < questions.length - 1) {
            currentQuestion++;
            loadQuestion();
        }
    });

    document.getElementById("submitBtn").addEventListener("click", function () {
        saveAnswer();
        submitExam();
    });
}
// ===== HELPER FUNCTIONS =====

function loadQuestion() {
    const q = questions[currentQuestion];

    let optionsHtml = "";
    q.options.forEach(function (option, index) {
        const checked = userAnswers[currentQuestion] === index ? "checked" : "";
        optionsHtml += `
            <label>
                <input type="radio" name="option" value="${index}" ${checked}>
                ${option}
            </label>
        `;
    });

    questionContainer.innerHTML = `
        <div class="question">
            <h3>Q${currentQuestion + 1}. ${q.question}</h3>
            <div class="options">${optionsHtml}</div>
        </div>
        <p>Question ${currentQuestion + 1} of ${questions.length}</p>
    `;

    // Disable "Previous" on first question
    document.getElementById("prevBtn").disabled = (currentQuestion === 0);

    // Change "Next" to disabled on last question (use Submit instead)
    document.getElementById("nextBtn").disabled = (currentQuestion === questions.length - 1);
}

function saveAnswer() {
    const selected = document.querySelector('input[name="option"]:checked');
    if (selected) {
        userAnswers[currentQuestion] = parseInt(selected.value);
    }
}

function startTimer() {
    const timerDisplay = document.getElementById("timer");

    timerInterval = setInterval(function () {
        timeLeft--;

        const minutes = Math.floor(timeLeft / 60);
        const seconds = timeLeft % 60;

        timerDisplay.textContent =
            "Time Left: " +
            String(minutes).padStart(2, "0") + ":" +
            String(seconds).padStart(2, "0");

        if (timeLeft <= 0) {
            clearInterval(timerInterval);
            saveAnswer();
            submitExam();
        }
    }, 1000);
}

function submitExam() {
    clearInterval(timerInterval);

    let score = 0;
    questions.forEach(function (q, index) {
        if (userAnswers[index] === q.answer) {
            score++;
        }
    });

    localStorage.setItem("examScore", score);
    localStorage.setItem("examTotal", questions.length);

    window.location.href = "result.html";
}
// ===== RESULT PAGE LOGIC =====

const scoreDisplay = document.getElementById("scoreDisplay");

if (scoreDisplay) {
    const username = localStorage.getItem("username") || "Student";
    const score = localStorage.getItem("examScore");
    const total = localStorage.getItem("examTotal");

    document.getElementById("resultUser").textContent = "Candidate: " + username;
    scoreDisplay.textContent = "Score: " + score + " / " + total;

    const percentage = ((score / total) * 100).toFixed(2);
    document.getElementById("resultDetails").textContent =
        "You scored " + percentage + "%. " +
        (percentage >= 50 ? "Congratulations, you passed!" : "You did not pass. Try again next time.");

    document.getElementById("logoutBtn").addEventListener("click", function () {
        localStorage.removeItem("loggedIn");
        localStorage.removeItem("username");
        localStorage.removeItem("examScore");
        localStorage.removeItem("examTotal");
        window.location.href = "index.html";
    });
}
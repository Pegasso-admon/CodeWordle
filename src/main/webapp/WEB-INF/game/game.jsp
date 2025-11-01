<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CodeWordle - Educational Programming Word Game</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        :root {
            --correct-color: #6aaa64;
            --present-color: #c9b458;
            --absent-color: #787c7e;
            --key-color: #d3d6da;
            --border-color: #d3d6da;
        }
        
        body {
            background-color: #f6f7f8;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        
        .game-container {
            max-width: 500px;
            margin: 0 auto;
            padding: 20px;
        }
        
        .header {
            text-align: center;
            border-bottom: 1px solid var(--border-color);
            padding-bottom: 15px;
            margin-bottom: 20px;
        }
        
        .game-title {
            color: #2c3e50;
            font-weight: 700;
            font-size: 2.5rem;
            margin-bottom: 5px;
        }
        
        .game-subtitle {
            color: #7f8c8d;
            font-size: 1.1rem;
        }
        
        .game-board {
            display: grid;
            grid-template-rows: repeat(6, 1fr);
            gap: 5px;
            margin: 20px 0;
        }
        
        .game-row {
            display: grid;
            grid-template-columns: repeat(5, 1fr);
            gap: 5px;
        }
        
        .game-cell {
            width: 100%;
            aspect-ratio: 1;
            border: 2px solid var(--border-color);
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 1.5rem;
            font-weight: bold;
            text-transform: uppercase;
            background: white;
            transition: all 0.3s ease;
        }
        
        .game-cell.filled {
            border-color: #878a8c;
        }
        
        .game-cell.correct {
            background-color: var(--correct-color);
            color: white;
            border-color: var(--correct-color);
        }
        
        .game-cell.present {
            background-color: var(--present-color);
            color: white;
            border-color: var(--present-color);
        }
        
        .game-cell.absent {
            background-color: var(--absent-color);
            color: white;
            border-color: var(--absent-color);
        }
        
        .input-section {
            margin: 20px 0;
        }
        
        .attempt-input {
            font-size: 1.2rem;
            text-transform: uppercase;
            text-align: center;
            letter-spacing: 2px;
        }
        
        .keyboard {
            margin: 20px 0;
        }
        
        .keyboard-row {
            display: flex;
            justify-content: center;
            gap: 5px;
            margin: 5px 0;
        }
        
        .key {
            min-width: 40px;
            height: 50px;
            border: none;
            border-radius: 4px;
            background-color: var(--key-color);
            font-weight: bold;
            cursor: pointer;
            display: flex;
            align-items: center;
            justify-content: center;
            transition: all 0.2s ease;
        }
        
        .key:hover {
            background-color: #b8bbbf;
        }
        
        .key.wide {
            min-width: 60px;
            font-size: 0.8rem;
        }
        
        .key.correct {
            background-color: var(--correct-color);
            color: white;
        }
        
        .key.present {
            background-color: var(--present-color);
            color: white;
        }
        
        .key.absent {
            background-color: var(--absent-color);
            color: white;
        }
        
        .game-info {
            background: white;
            border-radius: 8px;
            padding: 15px;
            margin: 15px 0;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        
        .theme-selector {
            margin: 15px 0;
        }
        
        .attempt-history {
            max-height: 200px;
            overflow-y: auto;
            margin: 15px 0;
        }
        
        .feedback-message {
            padding: 10px;
            border-radius: 5px;
            margin: 10px 0;
            text-align: center;
            font-weight: 500;
        }
        
        .feedback-success {
            background-color: #d4edda;
            color: #155724;
            border: 1px solid #c3e6cb;
        }
        
        .feedback-error {
            background-color: #f8d7da;
            color: #721c24;
            border: 1px solid #f5c6cb;
        }
        
        .feedback-info {
            background-color: #d1ecf1;
            color: #0c5460;
            border: 1px solid #bee5eb;
        }
        
        .game-status {
            font-size: 1.2rem;
            font-weight: 600;
            text-align: center;
            margin: 15px 0;
        }
        
        .status-won {
            color: var(--correct-color);
        }
        
        .status-lost {
            color: #dc3545;
        }
        
        .status-in-progress {
            color: #007bff;
        }
        
        @media (max-width: 576px) {
            .game-container {
                padding: 10px;
            }
            
            .game-cell {
                font-size: 1.2rem;
            }
            
            .key {
                min-width: 30px;
                height: 45px;
                font-size: 0.9rem;
            }
            
            .key.wide {
                min-width: 50px;
            }
        }
    </style>
</head>
<body>
    <div class="game-container">
        <!-- Header -->
        <div class="header">
            <h1 class="game-title">CodeWordle</h1>
            <p class="game-subtitle">Guess the programming term!</p>
        </div>

        <!-- Game Information -->
        <div class="game-info">
            <div class="theme-selector">
                <label for="themeSelect" class="form-label">Select Theme:</label>
                <select id="themeSelect" class="form-select" <c:if test="${not empty currentGame}">disabled</c:if>>
                    <option value="">Choose a theme...</option>
                    <c:forEach var="theme" items="${themes}">
                        <option value="${theme.id}">${theme.name} (${theme.wordCount} words)</option>
                    </c:forEach>
                </select>
            </div>
            
            <div class="game-status" id="gameStatus">
                <c:choose>
                    <c:when test="${not empty currentGame}">
                        <span class="status-in-progress">Game in Progress - Theme: 
                            <c:forEach var="theme" items="${themes}">
                                <c:if test="${theme.id == currentGame.themeId}">${theme.name}</c:if>
                            </c:forEach>
                        </span>
                    </c:when>
                    <c:otherwise>
                        <span class="text-muted">Select a theme to start playing</span>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <!-- Feedback Messages -->
        <div id="feedbackArea"></div>

        <!-- Game Board -->
        <div class="game-board" id="gameBoard">
            <!-- Game board will be populated by JavaScript -->
        </div>

        <!-- Input Section -->
        <div class="input-section">
            <div class="row g-2">
                <div class="col-9">
                    <input type="text" 
                           class="form-control attempt-input" 
                           id="wordInput" 
                           maxlength="5" 
                           placeholder="Enter 5-letter word"
                           <c:if test="${empty currentGame}">disabled</c:if>>
                </div>
                <div class="col-3">
                    <button class="btn btn-primary w-100" id="submitBtn" <c:if test="${empty currentGame}">disabled</c:if>>
                        <i class="fas fa-paper-plane"></i> Submit
                    </button>
                </div>
            </div>
            <div class="row mt-2">
                <div class="col-6">
                    <button class="btn btn-outline-secondary w-100" id="newGameBtn">
                        <i class="fas fa-plus"></i> New Game
                    </button>
                </div>
                <div class="col-6">
                    <button class="btn btn-outline-info w-100" id="clearBtn">
                        <i class="fas fa-eraser"></i> Clear
                    </button>
                </div>
            </div>
        </div>

        <!-- Virtual Keyboard -->
        <div class="keyboard">
            <div class="keyboard-row">
                <div class="key" data-key="Q">Q</div>
                <div class="key" data-key="W">W</div>
                <div class="key" data-key="E">E</div>
                <div class="key" data-key="R">R</div>
                <div class="key" data-key="T">T</div>
                <div class="key" data-key="Y">Y</div>
                <div class="key" data-key="U">U</div>
                <div class="key" data-key="I">I</div>
                <div class="key" data-key="O">O</div>
                <div class="key" data-key="P">P</div>
            </div>
            <div class="keyboard-row">
                <div class="key" data-key="A">A</div>
                <div class="key" data-key="S">S</div>
                <div class="key" data-key="D">D</div>
                <div class="key" data-key="F">F</div>
                <div class="key" data-key="G">G</div>
                <div class="key" data-key="H">H</div>
                <div class="key" data-key="J">J</div>
                <div class="key" data-key="K">K</div>
                <div class="key" data-key="L">L</div>
            </div>
            <div class="keyboard-row">
                <div class="key wide" data-key="ENTER">ENTER</div>
                <div class="key" data-key="Z">Z</div>
                <div class="key" data-key="X">X</div>
                <div class="key" data-key="C">C</div>
                <div class="key" data-key="V">V</div>
                <div class="key" data-key="B">B</div>
                <div class="key" data-key="N">N</div>
                <div class="key" data-key="M">M</div>
                <div class="key wide" data-key="BACKSPACE">
                    <i class="fas fa-backspace"></i>
                </div>
            </div>
        </div>

        <!-- Attempt History -->
        <div class="attempt-history" id="attemptHistory">
            <h6>Attempt History:</h6>
            <div id="historyList"></div>
        </div>
    </div>

    <!-- Bootstrap & jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        class CodeWordleGame {
            constructor() {
                this.currentGame = null;
                this.currentAttempt = '';
                this.attempts = [];
                this.maxAttempts = 6;
                this.wordLength = 5;
                this.initializeEventListeners();
                this.initializeGameBoard();
                this.loadCurrentGame();
            }

            initializeEventListeners() {
                // Word input
                $('#wordInput').on('input', (e) => {
                    this.currentAttempt = e.target.value.toUpperCase();
                    this.updateGameBoard();
                });

                // Submit attempt
                $('#submitBtn').on('click', () => this.submitAttempt());
                $('#wordInput').on('keypress', (e) => {
                    if (e.key === 'Enter') {
                        this.submitAttempt();
                    }
                });

                // New game
                $('#newGameBtn').on('click', () => this.startNewGame());

                // Clear input
                $('#clearBtn').on('click', () => this.clearInput());

                // Theme selection
                $('#themeSelect').on('change', () => {
                    if ($('#themeSelect').val()) {
                        this.enableGame();
                    }
                });

                // Virtual keyboard
                $('.key').on('click', (e) => {
                    const key = $(e.currentTarget).data('key');
                    this.handleKeyPress(key);
                });

                // Physical keyboard
                $(document).on('keydown', (e) => {
                    if (e.key.length === 1 && e.key.match(/[a-z]/i)) {
                        this.handleKeyPress(e.key.toUpperCase());
                    } else if (e.key === 'Backspace') {
                        this.handleKeyPress('BACKSPACE');
                    } else if (e.key === 'Enter') {
                        this.handleKeyPress('ENTER');
                    }
                });
            }

            initializeGameBoard() {
                const gameBoard = $('#gameBoard');
                gameBoard.empty();

                for (let row = 0; row < this.maxAttempts; row++) {
                    const rowElement = $('<div>').addClass('game-row');
                    
                    for (let col = 0; col < this.wordLength; col++) {
                        const cell = $('<div>').addClass('game-cell').attr('id', `cell-${row}-${col}`);
                        rowElement.append(cell);
                    }
                    
                    gameBoard.append(rowElement);
                }
            }

            async loadCurrentGame() {
                try {
                    const response = await fetch('/api/games/current');
                    const result = await response.json();
                    
                    if (result.success && result.data) {
                        this.currentGame = result.data;
                        this.maxAttempts = this.currentGame.maxAttempts;
                        await this.loadAttemptHistory();
                        this.updateUI();
                    } else {
                        this.disableGame();
                    }
                } catch (error) {
                    console.error('Error loading current game:', error);
                    this.showFeedback('Error loading game', 'error');
                }
            }

            async loadAttemptHistory() {
                if (!this.currentGame) return;

                try {
                    const response = await fetch(`/api/attempts/game/${this.currentGame.id}`);
                    const result = await response.json();
                    
                    if (result.success) {
                        this.attempts = result.data;
                        this.updateGameBoard();
                        this.updateAttemptHistory();
                    }
                } catch (error) {
                    console.error('Error loading attempt history:', error);
                }
            }

            async startNewGame() {
                const themeId = $('#themeSelect').val();
                
                if (!themeId) {
                    this.showFeedback('Please select a theme first', 'error');
                    return;
                }

                try {
                    const gameRequest = {
                        themeId: parseInt(themeId),
                        maxAttempts: 6
                    };

                    const response = await fetch('/api/games', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(gameRequest)
                    });

                    const result = await response.json();

                    if (result.success) {
                        this.currentGame = { id: result.data.gameId };
                        this.attempts = [];
                        this.currentAttempt = '';
                        this.maxAttempts = 6;
                        
                        this.initializeGameBoard();
                        this.updateUI();
                        this.clearInput();
                        this.showFeedback('New game started! Good luck!', 'success');
                    } else {
                        this.showFeedback(result.message, 'error');
                    }
                } catch (error) {
                    console.error('Error starting new game:', error);
                    this.showFeedback('Error starting new game', 'error');
                }
            }

            async submitAttempt() {
                if (!this.currentGame) {
                    this.showFeedback('Please start a new game first', 'error');
                    return;
                }

                if (this.currentAttempt.length !== this.wordLength) {
                    this.showFeedback(`Word must be ${this.wordLength} letters long`, 'error');
                    return;
                }

                try {
                    const attemptRequest = {
                        gameId: this.currentGame.id,
                        word: this.currentAttempt.toLowerCase()
                    };

                    const response = await fetch('/api/attempts', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(attemptRequest)
                    });

                    const result = await response.json();

                    if (result.success) {
                        const attemptResponse = result.data;
                        this.attempts.push({
                            wordAttempt: this.currentAttempt,
                            feedback: attemptResponse.feedback,
                            attemptNumber: this.attempts.length + 1
                        });

                        this.updateGameBoard();
                        this.updateAttemptHistory();
                        this.clearInput();
                        this.updateKeyboard(attemptResponse.feedback, this.currentAttempt);

                        if (attemptResponse.won) {
                            this.showFeedback('Congratulations! You won! 🎉', 'success');
                            this.updateGameStatus('WON');
                            this.disableGame();
                        } else if (attemptResponse.gameStatus === 'LOST') {
                            this.showFeedback(`Game Over! The word was: ${this.currentGame.targetWord}`, 'error');
                            this.updateGameStatus('LOST');
                            this.disableGame();
                        } else {
                            this.showFeedback(`Attempt submitted! ${attemptResponse.remainingAttempts} attempts remaining`, 'info');
                        }
                    } else {
                        this.showFeedback(result.message, 'error');
                    }
                } catch (error) {
                    console.error('Error submitting attempt:', error);
                    this.showFeedback('Error submitting attempt', 'error');
                }
            }

            handleKeyPress(key) {
                if (!this.currentGame) return;

                if (key === 'BACKSPACE') {
                    this.currentAttempt = this.currentAttempt.slice(0, -1);
                } else if (key === 'ENTER') {
                    this.submitAttempt();
                } else if (this.currentAttempt.length < this.wordLength) {
                    this.currentAttempt += key;
                }

                $('#wordInput').val(this.currentAttempt);
                this.updateGameBoard();
            }

            updateGameBoard() {
                // Clear all cells
                $('.game-cell').removeClass('filled correct present absent');
                $('.game-cell').text('');

                // Update based on attempt history
                this.attempts.forEach((attempt, rowIndex) => {
                    const word = attempt.wordAttempt;
                    const feedback = attempt.feedback;

                    for (let colIndex = 0; colIndex < this.wordLength; colIndex++) {
                        const cell = $(`#cell-${rowIndex}-${colIndex}`);
                        cell.text(word[colIndex] || '');
                        cell.addClass('filled');

                        if (feedback && feedback[colIndex]) {
                            const feedbackChar = feedback[colIndex];
                            if (feedbackChar === '🟩') {
                                cell.addClass('correct');
                            } else if (feedbackChar === '🟨') {
                                cell.addClass('present');
                            } else if (feedbackChar === '⬜') {
                                cell.addClass('absent');
                            }
                        }
                    }
                });

                // Update current attempt
                const currentRow = this.attempts.length;
                for (let colIndex = 0; colIndex < this.currentAttempt.length; colIndex++) {
                    const cell = $(`#cell-${currentRow}-${colIndex}`);
                    cell.text(this.currentAttempt[colIndex]);
                    cell.addClass('filled');
                }
            }

            updateKeyboard(feedback, word) {
                for (let i = 0; i < word.length; i++) {
                    const letter = word[i];
                    const key = $(`.key[data-key="${letter}"]`);
                    const feedbackChar = feedback[i];

                    if (feedbackChar === '🟩') {
                        key.addClass('correct').removeClass('present absent');
                    } else if (feedbackChar === '🟨' && !key.hasClass('correct')) {
                        key.addClass('present').removeClass('absent');
                    } else if (feedbackChar === '⬜' && !key.hasClass('correct') && !key.hasClass('present')) {
                        key.addClass('absent');
                    }
                }
            }

            updateAttemptHistory() {
                const historyList = $('#historyList');
                historyList.empty();

                this.attempts.forEach((attempt, index) => {
                    const attemptElement = $('<div>').addClass('d-flex justify-content-between align-items-center border-bottom py-2');
                    attemptElement.html(`
                        <span class="fw-bold">Attempt ${attempt.attemptNumber}:</span>
                        <span class="text-uppercase">${attempt.wordAttempt}</span>
                        <span>${attempt.feedback}</span>
                    `);
                    historyList.append(attemptElement);
                });
            }

            updateGameStatus(status) {
                const statusElement = $('#gameStatus');
                statusElement.removeClass('status-won status-lost status-in-progress text-muted');

                switch (status) {
                    case 'WON':
                        statusElement.html('<span class="status-won">🎉 You Won! 🎉</span>');
                        break;
                    case 'LOST':
                        statusElement.html('<span class="status-lost">💀 Game Over 💀</span>');
                        break;
                    case 'IN_PROGRESS':
                        statusElement.html('<span class="status-in-progress">Game in Progress</span>');
                        break;
                    default:
                        statusElement.html('<span class="text-muted">Select a theme to start playing</span>');
                }
            }

            updateUI() {
                if (this.currentGame) {
                    this.enableGame();
                    this.updateGameStatus('IN_PROGRESS');
                } else {
                    this.disableGame();
                    this.updateGameStatus('');
                }
            }

            enableGame() {
                $('#wordInput').prop('disabled', false);
                $('#submitBtn').prop('disabled', false);
                $('#themeSelect').prop('disabled', true);
            }

            disableGame() {
                $('#wordInput').prop('disabled', true);
                $('#submitBtn').prop('disabled', true);
                $('#themeSelect').prop('disabled', false);
            }

            clearInput() {
                this.currentAttempt = '';
                $('#wordInput').val('');
                this.updateGameBoard();
            }

            showFeedback(message, type) {
                const feedbackArea = $('#feedbackArea');
                const feedbackClass = {
                    'success': 'feedback-success',
                    'error': 'feedback-error',
                    'info': 'feedback-info'
                }[type] || 'feedback-info';

                const feedbackElement = $('<div>').addClass(`feedback-message ${feedbackClass}`).text(message);
                feedbackArea.empty().append(feedbackElement);

                setTimeout(() => {
                    feedbackElement.fadeOut(300, () => feedbackElement.remove());
                }, 5000);
            }
        }

        // Initialize the game when the page loads
        $(document).ready(() => {
            new CodeWordleGame();
        });
    </script>
</body>
</html>
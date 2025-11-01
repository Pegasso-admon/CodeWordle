<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Game Rules - CodeWordle</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        
        .rules-container {
            max-width: 800px;
            margin: 0 auto;
            padding: 30px;
        }
        
        .rule-card {
            background: white;
            border-radius: 10px;
            padding: 25px;
            margin-bottom: 20px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            border-left: 4px solid #007bff;
        }
        
        .rule-icon {
            font-size: 2rem;
            color: #007bff;
            margin-bottom: 15px;
        }
        
        .example-cell {
            display: inline-block;
            width: 50px;
            height: 50px;
            border: 2px solid #dee2e6;
            margin: 2px;
            text-align: center;
            line-height: 46px;
            font-weight: bold;
            font-size: 1.2rem;
            border-radius: 4px;
        }
        
        .example-correct {
            background-color: #6aaa64;
            color: white;
            border-color: #6aaa64;
        }
        
        .example-present {
            background-color: #c9b458;
            color: white;
            border-color: #c9b458;
        }
        
        .example-absent {
            background-color: #787c7e;
            color: white;
            border-color: #787c7e;
        }
        
        .back-button {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="rules-container">
        <div class="text-center mb-4">
            <h1 class="display-4 text-primary">CodeWordle Rules</h1>
            <p class="lead">Learn how to play and master the game</p>
        </div>

        <div class="rule-card">
            <div class="rule-icon">
                <i class="fas fa-bullseye"></i>
            </div>
            <h3>Objective</h3>
            <p>Guess the hidden programming-related word in 6 attempts or less. Each word is 5 letters long and belongs to a specific programming theme.</p>
        </div>

        <div class="rule-card">
            <div class="rule-icon">
                <i class="fas fa-keyboard"></i>
            </div>
            <h3>How to Play</h3>
            <ol>
                <li>Select a programming theme from the dropdown menu</li>
                <li>Start a new game</li>
                <li>Type a 5-letter word and press Enter or click Submit</li>
                <li>Use the feedback to guide your next guess</li>
                <li>You have 6 attempts to guess the correct word</li>
            </ol>
        </div>

        <div class="rule-card">
            <div class="rule-icon">
                <i class="fas fa-palette"></i>
            </div>
            <h3>Color Feedback</h3>
            <p>After each attempt, you'll receive color-coded feedback:</p>
            
            <div class="mb-3">
                <div class="example-cell example-correct">C</div>
                <span class="ms-2"><strong>Green:</strong> Letter is correct and in the right position</span>
            </div>
            
            <div class="mb-3">
                <div class="example-cell example-present">L</div>
                <span class="ms-2"><strong>Yellow:</strong> Letter is in the word but in the wrong position</span>
            </div>
            
            <div class="mb-3">
                <div class="example-cell example-absent">X</div>
                <span class="ms-2"><strong>Gray:</strong> Letter is not in the word</span>
            </div>
        </div>

        <div class="rule-card">
            <div class="rule-icon">
                <i class="fas fa-lightbulb"></i>
            </div>
            <h3>Tips & Strategies</h3>
            <ul>
                <li>Start with common programming terms</li>
                <li>Use your first guess to test multiple common letters</li>
                <li>Pay attention to the theme - words are related to programming concepts</li>
                <li>Eliminate letters that appear gray in subsequent guesses</li>
                <li>Consider word patterns common in programming terminology</li>
            </ul>
        </div>

        <div class="rule-card">
            <div class="rule-icon">
                <i class="fas fa-theater-masks"></i>
            </div>
            <h3>Themes Available</h3>
            <p>CodeWordle features various programming themes:</p>
            <ul>
                <li><strong>Java:</strong> Object-oriented programming concepts</li>
                <li><strong>Spring:</strong> Spring Framework terminology</li>
                <li><strong>DevOps:</strong> Development operations tools and practices</li>
                <li><strong>Database:</strong> Database concepts and technologies</li>
            </ul>
        </div>

        <div class="text-center back-button">
            <a href="/" class="btn btn-primary btn-lg">
                <i class="fas fa-arrow-left"></i> Back to Game
            </a>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/js/all.min.js"></script>
</body>
</html>
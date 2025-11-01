<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About - CodeWordle</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            min-height: 100vh;
        }
        
        .about-container {
            max-width: 800px;
            margin: 0 auto;
            padding: 40px 20px;
        }
        
        .about-card {
            background: rgba(255, 255, 255, 0.95);
            border-radius: 15px;
            padding: 40px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.2);
            backdrop-filter: blur(10px);
        }
        
        .feature-icon {
            font-size: 3rem;
            color: #667eea;
            margin-bottom: 20px;
        }
        
        .tech-badge {
            margin: 5px;
            font-size: 0.9rem;
        }
        
        .back-button {
            margin-top: 30px;
        }
    </style>
</head>
<body>
    <div class="about-container">
        <div class="about-card">
            <div class="text-center mb-5">
                <h1 class="display-4 text-primary mb-3">About CodeWordle</h1>
                <p class="lead text-muted">An educational word game for programming enthusiasts</p>
            </div>

            <div class="row text-center mb-5">
                <div class="col-md-4 mb-4">
                    <div class="feature-icon">
                        <i class="fas fa-graduation-cap"></i>
                    </div>
                    <h4>Educational</h4>
                    <p class="text-muted">Learn programming terminology while having fun</p>
                </div>
                <div class="col-md-4 mb-4">
                    <div class="feature-icon">
                        <i class="fas fa-code"></i>
                    </div>
                    <h4>Tech-Focused</h4>
                    <p class="text-muted">Words curated from various programming domains</p>
                </div>
                <div class="col-md-4 mb-4">
                    <div class="feature-icon">
                        <i class="fas fa-gamepad"></i>
                    </div>
                    <h4>Engaging</h4>
                    <p class="text-muted">Challenge yourself with daily programming puzzles</p>
                </div>
            </div>

            <div class="mb-5">
                <h3 class="mb-3">What is CodeWordle?</h3>
                <p class="text-muted">
                    CodeWordle is an educational word game inspired by the popular Wordle game, 
                    specifically designed for programming and technology enthusiasts. Instead of 
                    common English words, CodeWordle challenges players to guess programming-related 
                    terms across different technology domains.
                </p>
            </div>

            <div class="mb-5">
                <h3 class="mb-3">Technology Stack</h3>
                <div class="text-center">
                    <span class="badge tech-badge bg-primary">Java 21</span>
                    <span class="badge tech-badge bg-success">Spring Boot 3.5.7</span>
                    <span class="badge tech-badge bg-info">H2 Database</span>
                    <span class="badge tech-badge bg-warning">JSP & JSTL</span>
                    <span class="badge tech-badge bg-danger">Bootstrap 5</span>
                    <span class="badge tech-badge bg-secondary">JavaScript</span>
                    <span class="badge tech-badge bg-dark">Spring JDBC</span>
                    <span class="badge tech-badge bg-primary">REST API</span>
                </div>
            </div>

            <div class="mb-5">
                <h3 class="mb-3">Features</h3>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">Multiple programming themes (Java, Spring, DevOps, Database)</li>
                    <li class="list-group-item">Interactive virtual and physical keyboard support</li>
                    <li class="list-group-item">Real-time feedback with color-coded hints</li>
                    <li class="list-group-item">RESTful API backend with Spring Boot</li>
                    <li class="list-group-item">Responsive design for all devices</li>
                    <li class="list-group-item">Attempt history and game statistics</li>
                    <li class="list-group-item">In-memory H2 database for fast performance</li>
                </ul>
            </div>

            <div class="text-center back-button">
                <a href="/" class="btn btn-primary btn-lg me-3">
                    <i class="fas fa-arrow-left"></i> Back to Game
                </a>
                <a href="/rules" class="btn btn-outline-primary btn-lg">
                    <i class="fas fa-book"></i> View Rules
                </a>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/js/all.min.js"></script>
</body>
</html>
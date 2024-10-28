pipeline {
    agent any
    tools {
        maven 'Maven 3.x' // Replace with the name you configured in Jenkins
    }
    stages {
        stage('Checkout Code') {
            steps {
                git url: 'https://github.com/AhmadDahlawi/TaskTest.git', branch: 'main' // Ensure branch name matches your repo
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }

        stage('Deploy') {
            steps {
                echo 'Deploying the application...'
                // Add deployment steps as needed
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully.'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}

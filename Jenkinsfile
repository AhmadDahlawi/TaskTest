pipeline {
    agent any
    tools {
        maven 'Maven 3.x' // Adjust to the Maven version configured in Jenkins
    }
    
    environment {
        SLACK_WEBHOOK_URL = credentials('slack-webhook-url') // Replace with your actual Slack webhook credential ID
    }

    stages {
        stage('Checkout Code') {
            steps {
                git url: 'https://github.com/AhmadDahlawi/TaskTest.git', branch: 'master'
            }
        }

        stage('Build') {
            steps {
                sh '/usr/local/apache-maven/bin/mvn clean install'
            }
        }

        stage('Test') {
            steps {
                sh '/usr/local/apache-maven/bin/mvn test'
            }
        }

        stage('Package') {
            steps {
                sh '/usr/local/apache-maven/bin/mvn package'
            }
        }

        stage('Deploy') {
            when {
                // Conditional execution: Deploy only if previous stages succeeded
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
            steps {
                script {
                    echo 'Building Docker image...'
                    // Build Docker image from the Dockerfile in the repo
                    sh 'docker build -t taskmanager-app .'

                    echo 'Stopping and removing old container...'
                    // Stop and remove any existing container named taskmanager-app
                    sh 'docker stop taskmanager-app || true && docker rm taskmanager-app || true'

                    echo 'Running new Docker container...'
                    // Run the Docker container, mapping port 8080 on host to 8080 in container
                    sh 'docker run -d -p 8080:8080 --name taskmanager-app taskmanager-app'
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully.'
            sendSlackNotification("Success: Jenkins pipeline completed successfully.")
        }
        failure {
            echo 'Pipeline failed.'
            sendSlackNotification("Failure: Jenkins pipeline failed. Check logs for more details.")
        }
    }
}

// Helper function for sending Slack notifications
def sendSlackNotification(String message) {
    slackSend(channel: '#your-channel', color: currentBuild.result == 'SUCCESS' ? 'good' : 'danger', message: message, webhookUrl: env.SLACK_WEBHOOK_URL)
}

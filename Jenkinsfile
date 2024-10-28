pipeline {
    agent any
    tools {
        maven 'Maven 3.x'
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
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
            }
            steps {
                script {
                    echo 'Building Docker image...'
                    sh 'docker build -t taskmanager-app .'

                    echo 'Stopping and removing old container...'
                    sh 'docker stop taskmanager-app || true && docker rm taskmanager-app || true'

                    echo 'Running new Docker container...'
                    sh 'docker run -d -p 8080:8080 --name taskmanager-app taskmanager-app'
                }
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

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
    steps {
        script {
            echo 'Building Docker image...'
            // Use the full Docker path
            sh '/usr/local/bin/docker build -t taskmanager-app .'

            echo 'Stopping and removing old container...'
            // Stop and remove any existing container named taskmanager-app
            sh '/usr/local/bin/docker stop taskmanager-app || true && /usr/local/bin/docker rm taskmanager-app || true'

            echo 'Running new Docker container...'
            // Run the Docker container, mapping port 8080 on host to 8080 in container
            sh '/usr/local/bin/docker run -d -p 8080:8080 --name taskmanager-app taskmanager-app'
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

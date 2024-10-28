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

        stage('Build Docker Image') {
            steps {
                script {
                    echo 'Building Docker image...'
                    // Build the Docker image with a tag
                    sh '/usr/local/bin/docker build -t taskmanager-app .'
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    echo 'Pushing Docker image to Docker Hub...'
                    docker.withRegistry('https://index.docker.io/v1/', 'dockerhub-credentials-id') {
                        // Tag the local image with your Docker Hub repository name
                        sh '/usr/local/bin/docker tag taskmanager-app ahmadid69/taskmanager-app:latest'
                        // Push the image to Docker Hub
                        sh '/usr/local/bin/docker push ahmadid69/taskmanager-app:latest'
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    echo 'Stopping and removing old container...'
                    // Stop and remove any existing container named taskmanager-app
                    sh '/usr/local/bin/docker stop taskmanager-app || true && /usr/local/bin/docker rm taskmanager-app || true'

                    echo 'Running new Docker container...'
                    // Run the Docker container from Docker Hub, mapping port 8080 on host to 8080 in container
                    sh '/usr/local/bin/docker run -d -p 8080:8080 --name taskmanager-app ahmadid69/taskmanager-app:latest'
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

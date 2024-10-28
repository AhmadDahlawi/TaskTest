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
                    sh '/usr/local/bin/docker build -t taskmanager-app .'

                    echo 'Stopping and removing old container...'
                    sh '/usr/local/bin/docker stop taskmanager-app || true && /usr/local/bin/docker rm taskmanager-app || true'

                    echo 'Running new Docker container...'
                    sh '/usr/local/bin/docker run -d -p 8080:8080 --name taskmanager-app taskmanager-app'
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                script {
                    echo 'Pushing Docker image to Docker Hub...'
                    sh '''
                        /usr/local/bin/docker login -u "ahmadid69" -p "Aid102005"
                        /usr/local/bin/docker tag taskmanager-app ahmadid69/taskmanager-app:latest
                        /usr/local/bin/docker push ahmadid69/taskmanager-app:latest
                    '''
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

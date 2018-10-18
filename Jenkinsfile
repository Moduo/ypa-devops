pipeline {
    environment {
        DOCKER_IMAGE_NAME = "moduo/devops"
        DOCKER_IMAGE_FULL_NAME = "${DOCKER_IMAGE_NAME}:v${BUILD_NUMBER}"
        REGISTRY_CREDENTIALS = "DockerHub"
        DOCKER_IMAGE = ''
    }
    agent any
    stages {
        stage('Build') {
            agent {
                docker {
                    image 'maven:3-jdk-11'
                    args '-u root -v /root/.m2:/root/.m2'
                    reuseNode true
                }
            }
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }
        stage('Package') {
            steps {
                script {
                    DOCKER_IMAGE = docker.build DOCKER_IMAGE_FULL_NAME
                }
            }
        }
        stage('Test') {
            agent {
                docker {
                    image 'maven:3-jdk-11'
                    args '-u root -v /root/.m2:/root/.m2'
                    reuseNode true
                }
            }
            steps {
                sh 'mvn surefire:test'
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                  docker.withRegistry('', REGISTRY_CREDENTIALS ) {
                    DOCKER_IMAGE.push()
                  }
                }
            }
        }
    }
}
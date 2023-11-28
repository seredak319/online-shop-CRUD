pipeline {
    agent any

    environment {
        // Define your Docker image details
        DOCKER_IMAGE = "test-image"
        DOCKER_TAG = "${env.BUILD_ID}"
        CONTAINER_NAME = "test-container"
        REMOTE_SERVER_IP_ID = "server-ip-non-public"
        REMOTE_PATH = "/home/jenkins"
    }

    options {
        // Configure pipeline options, including GitHub branch source
        buildDiscarder(logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '', numToKeepStr: '10'))
    }

    triggers {
        // Trigger the pipeline on pull requests to the master branch
        pullRequest {
            branches = ['master2']
        }
    }

    stages {
        stage('Build Docker Image') {
            steps {
                script {
                    // Build Docker image locally
                    docker.build("${DOCKER_IMAGE}:${DOCKER_TAG}")
                }
            }
        }

        stage('Transfer Docker Image to Remote Server') {
            steps {
                script {

                    withCredentials([string(credentialsId: REMOTE_SERVER_IP_ID, variable: 'SERVER_IP')]) {
                
                    sh "scp ${DOCKER_IMAGE}-${DOCKER_TAG}.tar.gz ${SERVER_IP}:${REMOTE_PATH}"
                    }
                }
            }
        }

        stage('Stop and Remove Existing Container') {
            steps {
                script {
                    // Stop the existing container on the remote server
                    sh "ssh ${REMOTE_SERVER} 'docker stop ${CONTAINER_NAME}' || true"
                    
                    // Remove the existing container on the remote server
                    sh "ssh ${REMOTE_SERVER} 'docker rm ${CONTAINER_NAME}' || true"
                }
            }
        }

        stage('Load and Run New Docker Image on Remote Server') {
            steps {
                script {
                    withCredentials([string(credentialsId: REMOTE_SERVER_IP_ID, variable: 'SERVER_IP')]) {
                    // Load the new Docker image on the remote server
                    sh "ssh ${SERVER_IP} 'docker load -i ${REMOTE_PATH}${DOCKER_IMAGE}-${DOCKER_TAG}.tar.gz'"
                    
                    // Run a new Docker container on the remote server
                    sh "ssh ${SERVER_IP} 'docker run -d --name ${CONTAINER_NAME} -p 8080:80 ${DOCKER_IMAGE}:${DOCKER_TAG}'"
                    }
                }
            }
        }

        stage('Clean Up Local Docker Images') {
            steps {
                script {
                    // Remove the local Docker image built in the pipeline
                    docker.image("${DOCKER_IMAGE}:${DOCKER_TAG}").remove()
                }
            }
        }
    }
}

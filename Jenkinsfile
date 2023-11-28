pipeline {
    agent any

    environment {
        MY_SECRET_CREDENTIAL = credentials('server-ip-non-public')
    }

    stages {
        stage('Example Stage') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'server-ip-non-public', variable: 'SECRET_VALUE')]) {
                        // Access the secret value within this block
                        echo "Secret Value: ${SECRET_VALUE}"

                        // Use the secret value in your pipeline steps
                        // ...
                    }
                }
            }
        }
    }
}

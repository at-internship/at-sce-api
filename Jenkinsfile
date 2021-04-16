pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh "mvn clean install"
            }
        }
        stage('Jacoco Report') {
            steps{
                jacoco exclusionPattern: '**/*Test*,**/config/**,**/domain/**,**/dto/**/,**/exception/**,**/mapper/**,**/model/**,**/repository/**', minimumInstructionCoverage: '1'
            }
        }

        stage('Deploy DEV') {
            steps{
            input 'Deploy to Dev?'
                withCredentials([string(credentialsId: 'API_KEY_DEV_B', variable: 'API_KEY')]) {
                sh('HEROKU_API_KEY="${API_KEY}" mvn heroku:deploy -P dev')
                }
            }
        }

        stage('Prepare to QA') {
            when {
                branch 'develop'
            }
            steps {
                sh "mvn clean install"
            }
        }

        stage('Deploy QA') {
            when {
                branch 'develop'
            }
            steps {
            input 'Deploy to QA?'
                withCredentials([string(credentialsId: 'API_KEY_QA_B', variable: 'API_KEY')]) {
                    sh('HEROKU_API_KEY="${API_KEY}" mvn heroku:deploy -P qa')
                }
            }
        }
    }
}

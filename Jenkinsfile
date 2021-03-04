pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh "mvn clean install -Dmaven.test.skip=true"
            }
        }

        stage('Deploy DEV') {
            when {
                branch 'develop'
            }
            steps{
            input 'Deploy to Dev?'
                withCredentials([string(credentialsId: 'API_KEY_DEV_B', variable: 'API_KEY')]) {
                sh('HEROKU_API_KEY="${API_KEY}" mvn heroku:deploy -Dheroku.appName=at-sce-api-dev')
                }
            }
        }

        stage('Prepare to QA') {
            when {
                branch 'develop'
            }
            steps {
                sh "mvn clean install -Dmaven.test.skip=true"
            }
        }

        stage('Deploy QA') {
            when {
                branch 'develop'
            }
            steps {
            input 'Deploy to QA?'
                withCredentials([string(credentialsId: 'API_KEY_QA_B', variable: 'API_KEY')]) {
                    sh('HEROKU_API_KEY="${API_KEY}" mvn heroku:deploy -Dheroku.appName=at-sce-api-qa')
                }
            }
        }
    }
}

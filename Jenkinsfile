pipeline {
    agent any

    tools {
            dockerTool 'docker-latest'
            maven 'maven-3.8'
            jdk 'jdk-17'
    }
    triggers {
            gitlab(triggerOnPush: true,
                    triggerOnMergeRequest: true,
                    acceptMergeRequestOnSuccess: false,
                    branchFilterType: 'All',
                    cancelPendingBuildsOnUpdate: true,)
    }

     environment {
        PROXY_SERVER = 'changeme'
        HTTP_PROXY = "$PROXY_SERVER"
        HTTPS_PROXY = "$PROXY_SERVER"
     }

    stages {
        stage('build') {
            steps {
                withEnv(["https_proxy=${PROXY_SERVER}", "http_proxy=${PROXY_SERVER}"]) {
                    sh 'mvn -B -DskipTests clean package'
                }
            }
        }
        stage('test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('jacoco coverage report') {
            steps {
                sh 'mvn jacoco:report'
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'target/site/*', fingerprint: true
        }
    }
}
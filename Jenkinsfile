pipeline {
  agent none
  stages {
    stage('sleep') {
      steps {
        echo 'Hello'
      }
    }
    stage('install maven') {
      steps {
        sh 'mvn clean'
      }
    }
  }
}
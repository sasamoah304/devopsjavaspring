pipeline {
  agent none
  stages {
    stage('build') {
      steps {
        sh 'mvn clean package'
      }
    }
    stage('sleep') {
      steps {
        echo 'Hello'
      }
    }
  }
}
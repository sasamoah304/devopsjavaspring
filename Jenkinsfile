pipeline {
  agent {
    docker 'maven:3.3.3'
  }
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
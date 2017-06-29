pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'Building..'
        bat 'println "sh mvn --version".execute().text'
      }
    }
    stage('Test') {
      steps {
        echo 'Testing..'
      }
    }
    stage('Deploy') {
      steps {
        echo 'Deploying....'
      }
    }
  }
}
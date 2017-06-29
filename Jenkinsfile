pipeline {
  agent any
  stages {
    stage('Example') {
      steps {
        echo '"${params.Greeting} World!"'
      }
    }
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
  parameters {
    string(name: 'Greeting', defaultValue: 'Hello', description: 'How should I greet the world?')
  }
}
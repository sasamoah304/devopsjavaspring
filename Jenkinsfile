pipeline {
  agent {
    docker {
      image 'maven'
      args '3.3.3'
    }
    
  }
  stages {
    stage('sleep') {
      steps {
        echo 'Hello'
      }
    }
  }
}
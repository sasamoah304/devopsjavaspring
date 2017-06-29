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
        sh '''   // install Maven and add it to the path
    env.PATH = "${tool 'M3'}/bin:${env.PATH}"'''
      }
    }
  }
}
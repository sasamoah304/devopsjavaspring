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

        sh '''env.PATH = "${tool 'M3'}/bin:${env.PATH}"'''

      }

    }

  }

}

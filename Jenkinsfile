node {
  stage 'Build and Test'
  env.PATH = "${tool 'Maven 3'}/bin:${env.PATH}"
  checkout scm
  sh 'mvn versions:set -DnewVersion=${BUILD_NUMBER}'
  sh 'mvn clean package org.pitest:pitest-maven:mutationCoverage'
  archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
  archiveArtifacts artifacts: '**/target/pit-reports/'
}

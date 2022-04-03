pipeline {
  agent any
  stages {
    stage('Test') {
      steps {
        sh 'echo "Hello,Post-Service"'
      }
    }

    stage('Test Shell') {
          steps {
            sh 'sudo su'
            sh 'docker images'
          }
        }

  }
}
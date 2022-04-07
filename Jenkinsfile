pipeline {
  agent any
  stages {
    stage('Test') {
      steps {
        sh 'echo "Hello,Post-Service"'
      }
    }

   
         stage('Quality Gate') {
        steps {
            withSonarQubeEnv(credentialsId: 'sonarqube', installationName: 'SonarQubeScanner') {
                withMaven {
                    sh 'mvn clean package sonar:sonar'
                }
            }
        }
    }
    
  }
  
}

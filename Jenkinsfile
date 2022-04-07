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
            withSonarQubeEnv(`sonarqube-8.3.0`) {
                withMaven {
                    sh 'mvn clean package sonar:sonar'
                }
            }
        }
    }
    
  }
  
}

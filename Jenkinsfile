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
            withSonarQubeEnv("sonarqube8.3") {
                withMaven {
                    sh 'mvn clean package sonar:sonar'
                }
            }
        }
    }
    
    stage('Quality Gate2') {
        steps {
            withSonarQubeEnv("sonarcloud") {
                withMaven {
                    sh 'mvn clean package sonar:sonar'
                }
            }
        }
    }
    
  }
  
}

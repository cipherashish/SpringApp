pipeline{
    agent any
    stages{
        stage('Checkout'){
            steps{
                withCredentials([string(credentialsId: 'ASHISH_WEB', variable: 'git')]) {
                echo "My password is '${git}'!"
                checkout([$class: 'GitSCM',
                branches: [[name: 'origin/dprocess']],
                extensions: [[$class: 'WipeWorkspace']],
                userRemoteConfigs: [[url: "${git}"]]
                ])
                }
            }
        }
     stage ('Build & Test'){
            steps{
                sh "mvn clean install"
               
            }
        }
       
       stage('Code Quality') 
         {
             environment {
           scannerHome=tool 'sonar scanner'
       }
            steps {
                sh "mvn sonar:sonar -Dsonar.host.url=http://3.14.251.87:9000"
            }
        }
         stage ('Uploading  to nexus'){
            steps{
             withCredentials([usernamePassword(credentialsId: 'Ashish_nexus', passwordVariable: 'pass', usernameVariable: 'usr')]) {
     sh label: 'uploading war file to nexus', script: 'curl -u $usr:$pass --upload-file target/springapp-${BUILD_NUMBER}.war http://3.14.251.87:8081/nexus/content/repositories/devopstraining/Ashish_Nexus/springapp-${BUILD_NUMBER}.war'
}
            
        }
         }
        stage('Deployment'){
        steps{
            withCredentials([usernamePassword(credentialsId: 'devops-tomcat', passwordVariable: 'pass', usernameVariable: 'userId')]) {
          sh label: '', script: 'curl -u  $userId:$pass --upload-file target/springapp-${BUILD_NUMBER}.war http://ec2-18-224-182-74.us-east-2.compute.amazonaws.com:8080/manager/text/deploy?config=file:/var/lib/tomcat8/springapp-${BUILD_NUMBER}.war\\&path=/Ashish_webapp'
        }

    }}
        
      }
}
       
       

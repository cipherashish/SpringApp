pipeline{
    agent any
   
    stages{
        stage('checkout'){
            steps{
                withCredentials([string(credentialsId: 'Ashish_DemoGit', variable: 'bizeet')]) {
                echo "My password is '${bizeet}'!"
                checkout([$class: 'GitSCM',
                branches: [[name: 'origin/dev']],
                extensions: [[$class: 'WipeWorkspace']],
                userRemoteConfigs: [[url: "${bizeet}"]]
                ])
                }
            }
        }
             stage ('build & Test'){
            steps{
                sh "mvn clean install"
               
            }
        }
         stage('Sonar') 
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
             withCredentials([usernamePassword(credentialsId: 'Bizeet_nexus', passwordVariable: 'pass', usernameVariable: 'usr')]) {
     sh   'curl -u $usr:$pass --upload-file target/springapp-${BUILD_NUMBER}.war http://3.14.251.87:8081/nexus/content/repositories/devopstraining/Bizeet_Nexus_Test3/springapp-${BUILD_NUMBER}.war'
}
            
        }
        }
        
         stage ('Deploy'){
            steps{
                 withCredentials([usernamePassword(credentialsId: 'devops-tomcat', passwordVariable: 'pass', usernameVariable: 'userId')]) {
        
                   
                     sh 'curl -u $userId:$pass http://ec2-18-224-182-74.us-east-2.compute.amazonaws.com:8080/manager/text/undeploy?path=/Ashis_DEMO'
                    sh 'curl -u  $userId:$pass --upload-file target/springapp-${BUILD_NUMBER}.war http://ec2-18-224-182-74.us-east-2.compute.amazonaws.com:8080/manager/text/deploy?config=file:/var/lib/tomcat8/springapp-${BUILD_NUMBER}.war\\&path=/Ashis_DEMO'
            }
        }
         
    }}
           post {
    success {
      slackSend (color: 'good', message: "Complete: Job '${JOB_NAME} with    [${BUILD_NUMBER}]'")
    }
    failure {
      slackSend (color: 'danger', message: "Breakdown: Job '${JOB_NAME} with [${BUILD_NUMBER}]'")
    }
}
}

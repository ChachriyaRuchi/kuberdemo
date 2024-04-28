pipeline{

    agent any
    tools{
        maven "maven"
    }
    environment{
              APP_NAME = "user-service"
              RELEASE_NO= "1.0.0"
              DOCKER_USER= "ruchichachriya"
              IMAGE_NAME= "${DOCKER_USER}"+"/"+"${APP_NAME}"
              IMAGE_TAG= "${RELEASE_NO}-${BUILD_NUMBER}"
       }
    stages{
        stage("SCM checkout"){
            steps{
              checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/ChachriyaRuchi/kuberdemo.git']])
            }

        }

        stage("Build Process"){
            steps{
                script{
                   bat 'mvn clean install'
                }
            }

    }
        stage("Build Image"){
                   steps{
                       script{
                            bat "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ."
                       }
                   }
               }
        stage("Deploy Image to Hub") {
                      steps {
                          withCredentials([string(credentialsId: 'dp', variable: 'dp')]) {
                              bat "docker login -u ruchichachriya -p ${dp}"
                              bat "docker push ${IMAGE_NAME}:${IMAGE_TAG}"

                          }
                      }
                  }
        stage("Deploy to kubernetes"){
                     steps{
                      sh 'kubectl apply -f k8-file.yaml'
            }
        }

        stage("Verify Deployment"){
                     steps{
                      sh 'kubectl get pods'
            }
        }
    }
     post{
        always{
            emailext attachLog: true,
            body: ''' <html>
    <body>
        <p>Build Status: ${BUILD_STATUS}</p>
        <p>Build Number: ${BUILD_NUMBER}</p>
        <p>Check the <a href="${BUILD_URL}">console output</a>.</p>
    </body>
</html>''', mimeType: 'text/html', replyTo: 'ruchichachriya@gmail.com', subject: 'Pipeline Status : ${BUILD_NUMBER}', to: 'ruchi.xworkz@gmail.com'

        }
    }
}
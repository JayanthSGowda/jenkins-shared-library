#!/usr/bin/env groovy

def call(){
    echo 'building docker image and pushing it to the repo...'
     withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
         sh 'docker build -t jaynths/jayanth:jma-3.0 .'
         sh 'echo $PASS | docker login -u $USER --password-stdin'
         sh 'docker push jaynths/jayanth:jma-3.0'
     }
}

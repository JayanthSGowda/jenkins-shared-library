package com.exaple

class Docker implements Serializable {
    def script
    Docker(script){
        this.script = script
    }
    def buildDockerImage(String imageName){
        script.echo 'building docker image and pushing it to the repo...'
        script.withCredentials([script.usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
            script.sh "docker build -t ${imageName} ."
            script.sh 'echo $PASS | docker login -u $USER --password-stdin'
            script.sh "docker push ${imageName}"
        }
    }
}

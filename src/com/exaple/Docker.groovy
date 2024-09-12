package com.exaple

class Docker implements Serializable {
    def script
    Docker(script){
        this.script = script
    }
    def buildDockerJar(String imageName){
        script.echo 'building docker image and pushing it to the repo...'
        script.sh "docker build -t ${imageName} ."
    }
    def dockerLogin(){
        script.withCredentials([script.usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
            script.sh 'echo $PASS | docker login -u $USER --password-stdin'
        }
    }
    def dockerPush(String imageName){
        script.sh "docker push ${imageName}"
    }

}

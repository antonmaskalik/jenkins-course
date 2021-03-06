job('NodeJS Docker example') {
    scm {
         git('https://github.com/antonmaskalik/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('antonmaskalik')
            node / gitConfigEmail('anton.maskalik@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('antonmaskalik/nodejs')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('antonmaskalik')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}

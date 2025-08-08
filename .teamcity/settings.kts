import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.perfmon
import jetbrains.buildServer.configs.kotlin.buildSteps.script
import jetbrains.buildServer.configs.kotlin.triggers.vcs

version = "2025.07"

project {
    buildType(Build)
}

object Build : BuildType({
    name = "Hello script"

    publishArtifacts = PublishMode.SUCCESSFUL

    vcs {
        root(DslContext.settingsRoot)
        cleanCheckout = true
    }

    steps {
        script {
            name = "Run hello.sh"
            scriptContent = """
                set -e
                chmod +x ./hello.sh || true
                ./hello.sh
            """.trimIndent()
        }
    }

    triggers {
        vcs { }
    }

    features {
        perfmon { }
    }
})

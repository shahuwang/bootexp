package com.shahuwang.bootexp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.loader.tools.JavaExecutable;
import org.springframework.boot.loader.tools.MainClassFinder;
import org.springframework.boot.loader.tools.RunProcess;

/**
 * Hello world!
 *
 */
public class Runner
{
    public static void main( String[] args ) throws IOException {
        String SPRING_BOOT_APPLICATION_CLASS_NAME = "org.springframework.boot.autoconfigure.SpringBootApplication";
        File classesDirectory = new File("C:\\share\\bootsample\\target\\classes");
        String mainClass = MainClassFinder.findSingleMainClass(classesDirectory, SPRING_BOOT_APPLICATION_CLASS_NAME);
        RunProcess runProcess = new RunProcess(classesDirectory, new JavaExecutable().toString());
        Runtime.getRuntime().addShutdownHook(new Thread(new RunProcessKiller(runProcess)));
        List<String> params = new ArrayList<>();
        params.add("-cp");
        params.add("C:\\share\\bootsample\\target\\classes;C:\\Users\\shahuwang\\.m2\\repository\\org\\springframework\\boot\\spring-boot-starter-web\\2.2.4.RELEASE\\spring-boot-starter-web-2.2.4.RELEASE.jar;C:\\Users\\shahuwang\\.m2\\repository\\org\\springframework\\boot\\spring-boot-starter\\2.2.4.RELEASE\\spring-boot-starter-2.2.4.RELEASE.jar;C:\\Users\\shahuwang\\.m2\\repository\\org\\springframework\\boot\\spring-boot\\2.2.4.RELEASE\\spring-boot-2.2.4.RELEASE.jar;C:\\Users\\shahuwang\\.m2\\repository\\org\\springframework\\boot\\spring-boot-autoconfigure\\2.2.4.RELEASE\\spring-boot-autoconfigure-2.2.4.RELEASE.jar;C:\\Users\\shahuwang\\.m2\\repository\\org\\springframework\\boot\\spring-boot-starter-logging\\2.2.4.RELEASE\\spring-boot-starter-logging-2.2.4.RELEASE.jar;C:\\Users\\shahuwang\\.m2\\repository\\ch\\qos\\logback\\logback-classic\\1.2.3\\logback-classic-1.2.3.jar;C:\\Users\\shahuwang\\.m2\\repository\\ch\\qos\\logback\\logback-core\\1.2.3\\logback-core-1.2.3.jar;C:\\Users\\shahuwang\\.m2\\repository\\org\\apache\\logging\\log4j\\log4j-to-slf4j\\2.12.1\\log4j-to-slf4j-2.12.1.jar;C:\\Users\\shahuwang\\.m2\\repository\\org\\apache\\logging\\log4j\\log4j-api\\2.12.1\\log4j-api-2.12.1.jar;C:\\Users\\shahuwang\\.m2\\repository\\org\\slf4j\\jul-to-slf4j\\1.7.30\\jul-to-slf4j-1.7.30.jar;C:\\Users\\shahuwang\\.m2\\repository\\jakarta\\annotation\\jakarta.annotation-api\\1.3.5\\jakarta.annotation-api-1.3.5.jar;C:\\Users\\shahuwang\\.m2\\repository\\org\\yaml\\snakeyaml\\1.25\\snakeyaml-1.25.jar;C:\\Users\\shahuwang\\.m2\\repository\\org\\springframework\\boot\\spring-boot-starter-json\\2.2.4.RELEASE\\spring-boot-starter-json-2.2.4.RELEASE.jar;C:\\Users\\shahuwang\\.m2\\repository\\com\\fasterxml\\jackson\\core\\jackson-databind\\2.10.2\\jackson-databind-2.10.2.jar;C:\\Users\\shahuwang\\.m2\\repository\\com\\fasterxml\\jackson\\core\\jackson-annotations\\2.10.2\\jackson-annotations-2.10.2.jar;C:\\Users\\shahuwang\\.m2\\repository\\com\\fasterxml\\jackson\\core\\jackson-core\\2.10.2\\jackson-core-2.10.2.jar;C:\\Users\\shahuwang\\.m2\\repository\\com\\fasterxml\\jackson\\datatype\\jackson-datatype-jdk8\\2.10.2\\jackson-datatype-jdk8-2.10.2.jar;C:\\Users\\shahuwang\\.m2\\repository\\com\\fasterxml\\jackson\\datatype\\jackson-datatype-jsr310\\2.10.2\\jackson-datatype-jsr310-2.10.2.jar;C:\\Users\\shahuwang\\.m2\\repository\\com\\fasterxml\\jackson\\module\\jackson-module-parameter-names\\2.10.2\\jackson-module-parameter-names-2.10.2.jar;C:\\Users\\shahuwang\\.m2\\repository\\org\\springframework\\boot\\spring-boot-starter-tomcat\\2.2.4.RELEASE\\spring-boot-starter-tomcat-2.2.4.RELEASE.jar;C:\\Users\\shahuwang\\.m2\\repository\\org\\apache\\tomcat\\embed\\tomcat-embed-core\\9.0.30\\tomcat-embed-core-9.0.30.jar;C:\\Users\\shahuwang\\.m2\\repository\\org\\apache\\tomcat\\embed\\tomcat-embed-el\\9.0.30\\tomcat-embed-el-9.0.30.jar;C:\\Users\\shahuwang\\.m2\\repository\\org\\apache\\tomcat\\embed\\tomcat-embed-websocket\\9.0.30\\tomcat-embed-websocket-9.0.30.jar;C:\\Users\\shahuwang\\.m2\\repository\\org\\springframework\\boot\\spring-boot-starter-validation\\2.2.4.RELEASE\\spring-boot-starter-validation-2.2.4.RELEASE.jar;C:\\Users\\shahuwang\\.m2\\repository\\jakarta\\validation\\jakarta.validation-api\\2.0.2\\jakarta.validation-api-2.0.2.jar;C:\\Users\\shahuwang\\.m2\\repository\\org\\hibernate\\validator\\hibernate-validator\\6.0.18.Final\\hibernate-validator-6.0.18.Final.jar;C:\\Users\\shahuwang\\.m2\\repository\\org\\jboss\\logging\\jboss-logging\\3.4.1.Final\\jboss-logging-3.4.1.Final.jar;C:\\Users\\shahuwang\\.m2\\repository\\com\\fasterxml\\classmate\\1.5.1\\classmate-1.5.1.jar;C:\\Users\\shahuwang\\.m2\\repository\\org\\springframework\\spring-web\\5.2.3.RELEASE\\spring-web-5.2.3.RELEASE.jar;C:\\Users\\shahuwang\\.m2\\repository\\org\\springframework\\spring-beans\\5.2.3.RELEASE\\spring-beans-5.2.3.RELEASE.jar;C:\\Users\\shahuwang\\.m2\\repository\\org\\springframework\\spring-webmvc\\5.2.3.RELEASE\\spring-webmvc-5.2.3.RELEASE.jar;C:\\Users\\shahuwang\\.m2\\repository\\org\\springframework\\spring-aop\\5.2.3.RELEASE\\spring-aop-5.2.3.RELEASE.jar;C:\\Users\\shahuwang\\.m2\\repository\\org\\springframework\\spring-context\\5.2.3.RELEASE\\spring-context-5.2.3.RELEASE.jar;C:\\Users\\shahuwang\\.m2\\repository\\org\\springframework\\spring-expression\\5.2.3.RELEASE\\spring-expression-5.2.3.RELEASE.jar;C:\\Users\\shahuwang\\.m2\\repository\\org\\slf4j\\slf4j-api\\1.7.30\\slf4j-api-1.7.30.jar;C:\\Users\\shahuwang\\.m2\\repository\\org\\springframework\\spring-core\\5.2.3.RELEASE\\spring-core-5.2.3.RELEASE.jar;C:\\Users\\shahuwang\\.m2\\repository\\org\\springframework\\spring-jcl\\5.2.3.RELEASE\\spring-jcl-5.2.3.RELEASE.jar");
        params.add(mainClass);
        Map<String, String> environmentVariables = new HashMap<>();
        runProcess.run(true, params, environmentVariables);
    }

    private static final class RunProcessKiller implements Runnable {

        private final RunProcess runProcess;

        private RunProcessKiller(RunProcess runProcess) {
            this.runProcess = runProcess;
        }

        @Override
        public void run() {
            this.runProcess.kill();
        }

    }
}

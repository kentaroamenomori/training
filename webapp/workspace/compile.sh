#!/bin/zsh

export CATALINA_HOME=~/documents/training/java/webapp/workspace/tomcat
export APP_DIRECTORY=$CATALINA_HOME/webapps/book/WEB-INF

javac -d $APP_DIRECTORY/classes -cp $CATALINA_HOME/lib/servlet-api.jar -sourcepath $APP_DIRECTORY/src $APP_DIRECTORY/src/$1

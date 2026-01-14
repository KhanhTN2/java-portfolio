#!/bin/sh
# Apache Maven Wrapper

MVNW_REPOURL="${MVNW_REPOURL:-https://repo.maven.apache.org/maven2}"

if [ -z "$JAVA_HOME" ]; then
  JAVA_EXE="java"
else
  JAVA_EXE="$JAVA_HOME/bin/java"
fi

WRAPPER_DIR="$(cd "$(dirname "$0")" && pwd)/.mvn/wrapper"
JAR="$WRAPPER_DIR/maven-wrapper.jar"
PROPS="$WRAPPER_DIR/maven-wrapper.properties"

if [ ! -r "$JAR" ]; then
  echo "Downloading Maven wrapper..."
  WRAPPER_URL=$(grep -E '^wrapperUrl=' "$PROPS" | cut -d'=' -f2)
  if [ -z "$WRAPPER_URL" ]; then
    WRAPPER_URL="$MVNW_REPOURL/org/apache/maven/wrapper/maven-wrapper/3.3.2/maven-wrapper-3.3.2.jar"
  fi
  mkdir -p "$WRAPPER_DIR"
  if ! curl -fsSL -o "$JAR" "$WRAPPER_URL"; then
    echo "Failed to download Maven wrapper. Falling back to mvn."
    exec mvn "$@"
  fi
fi

if ! "$JAVA_EXE" -jar "$JAR" -v >/dev/null 2>&1; then
  echo "Maven wrapper jar is invalid. Falling back to mvn."
  exec mvn "$@"
fi

exec "$JAVA_EXE" -jar "$JAR" "$@"

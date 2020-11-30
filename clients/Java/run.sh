set -ex

cd /output
java -Xmx250m -jar ./aicup2020-jar-with-dependencies.jar "$@"
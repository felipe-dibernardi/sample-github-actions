clean:
	./gradlew clean

build: clean
	./gradlew build --debug

test: clean
	./gradlew test

run-local:
	./gradlew bootRun

debug:
	./gradlew bootRun --debug-jvm



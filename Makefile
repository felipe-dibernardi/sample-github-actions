clean:
	./gradlew clean

build: clean
	./gradlew build

test: clean
	./gradlew test

integration: clean
	./gradlew integration

pipeline-integration: integration-config-up integration integration-config-down

run-local:
	./gradlew bootRun

debug:
	./gradlew bootRun --debug-jvm

local-config-up:
	docker-compose --file ./local-config/docker-compose.yml up -d

local-config-down:
	docker-compose --file ./local-config/docker-compose.yml down

integration-config-up:
	docker-compose --file ./local-config/docker-compose-integration.yml up -d

integration-config-down:
	docker-compose --file ./local-config/docker-compose-integration.yml down


